//存放主要交互逻辑js代码
//javascript 模块化
var seckill = {
		//封装秒杀相关ajax的URL
		URL : {
			now : function() {
				return '/seckill/seckill/time/now';
			},
			
			exposer: function(seckillId) {
				return '/seckill/seckill/'+seckillId+"/exposer";
			},
			
			execution: function(seckillId,md5) {
				return "/seckill/seckill/" + seckillId + "/" + md5 + "/" + "execution";
			}
		},
		
		//处理秒杀逻辑
		handleSeckillkill: function(seckillId,node) {
			//获取秒杀地址，控制显示逻辑,执行秒杀
			node.hide()
				.html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
			$.post(seckill.URL.exposer(seckillId), {}, function(result) {
				if(result && result['success']) {
					var exposer = result['data'];
					if(exposer['exposed']) {
						//开启秒杀
						//获取秒杀的地址
						var md5 = exposer['md5'];
						var killUrl = seckill.URL.execution(seckillId,md5);
						console.log("killUrl:"+killUrl);
						//绑定一次点击事件
						$("#killBtn").one('click',function() {
							//禁用按钮
							$(this).addClass('disabled'); 
							//执行秒杀操作
							$.post(killUrl,{},function(result) {
								
								if(result && result['success']) {
									var killResult = result['data'];
									var state = killResult['state'];
									var stateInfo = killResult['stateInfo'];
									//显示秒杀结果
									node.html('<span class="label label-success">'+ stateInfo + '</span>');
								}else {
									var killResult = result['data'];
									node.html('<span class="label label-danger">'+ killResult['stateInfo'] + '</span>');
								}
							});
						});
						node.show();
						
					}else {
						//未开启秒杀
						var now = exposer['now'];
						var start = exposer['start'];
						var end = exposer['end'];
						//重新倒计时
						seckill.countdown(seckillId,now,start,end);
					}
				}else {
					console.log('result:'+result);
				}
			});
		},
		
		//验证手机号
		validatePhone : function(phone) {
			if(phone && phone.length == 11 && !isNaN(phone)) {
				return true;
			}else {
				return false;
			}
		},
		
		//时间判断
		countdown: function(seckillId,nowTime,startTime,endTime) {
			var seckillBox = $("#seckill-box");
			if(nowTime > endTime) {
				seckillBox.html('秒杀结束！');
			}else if(nowTime < startTime) {
				//秒杀未开始，记时事件绑定
				var killTime = new Date(startTime + 1000);
				seckillBox.countdown(killTime,function(event){
					var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
					seckillBox.html(format);
				}).on("finish,countdown", function() {
					//时间完成后回调事件
					seckill.handleSeckillkill(seckillId,seckillBox);
				});
			}else {
				seckill.handleSeckillkill(seckillId,seckillBox);
			}
		},
		
		//详情页秒杀逻辑
		detail : {
			//详情页初始化
			init : function(params) {
				//手机验证和登陆，记时交互
				//规划交互流程
				//在cookie中查找手机号
				var killPhone = $.cookie("killPhone");
				
				//验证手机号
				if(!seckill.validatePhone(killPhone)) {
					var killPhoneModal = $('#killPhoneModal');
					killPhoneModal.modal({
						show: true,  //显示模态框
						backdrop: 'static',    //禁止位置关闭
						keyboard: false  //关闭键盘事件
					});
					$('#killPhoneBtn').click(function() {
						var inputPhone = $.trim($("#killPhoneKey").val());
						if(seckill.validatePhone(inputPhone)) {
							//将电话写入cookie
							$.cookie('killPhone', inputPhone, {expires:7,path:'/seckill'});
							window.location.reload();
						}else{
							$("#killPhoneMessage").hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
						}
					});
				}
				
				//已经登陆
				//记时交互
				var startTime = params["startTime"];
				var endTime = params["endTime"];
				var seckillId = params["seckillId"];
				$.get(seckill.URL.now(), {}, function(result) {
					if(result && result['success']) {
						var nowTime = result['data'];
						//时间判断
						seckill.countdown(seckillId,nowTime,startTime,endTime);
					}else{
						console.log('result:'+result);
					}
				},'json');
			}
		}
}