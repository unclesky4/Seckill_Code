<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!-- 引入jstl -->
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>秒杀详情页</title>
    <%@include file="common/head.jsp" %>
    <link href="https://cdn.bootcss.com/jquery-countdown/2.0.2/jquery.countdown.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${seckill.name}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger"></h2>
				<!-- 显示time图标 -->
				<span class="glyphicon glyphicon-time"></span>
				<!-- 倒计时 -->
				<span class="glyphicon" id="seckill-box">ssssssssss</span>
			</div>
		</div>
	</div>
	
	<!-- 登陆弹出层，输入电话 -->
	<div id="killPhoneModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>
					</h3>
				</div>
				<div id="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" placeholder="请输入手机号^o^" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- 验证信息 -->
					<span id="killPhoneMessage" class="glyphicon"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>
						Submit
					</button>
				</div>
			</div>
		</div>
	
	</div>

    
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- cookie操作插件 -->
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<!-- jquery.countdown 倒计时插件 -->
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<!-- 交互逻辑 -->
<script type="text/javascript" src="/seckill/resources/script/seckill.js"></script>
<script type="text/javascript">
	$(function(){
		seckill.detail.init({
			seckillId: ${seckill.seckillId}, 
			startTime: ${seckill.startTime.time}, 
			endTime: ${seckill.endTime.time}
		});
	});
</script>
</html>