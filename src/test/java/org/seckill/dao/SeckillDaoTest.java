package org.seckill.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessKilled;
import org.seckill.enums.SeckillStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	//注入Dao依赖
	@Resource
	private SeckillDao seckillDao;
	
	@Autowired
	private SuccessKilledDao successKilledDao;

	@Test
	public void testReduceNumber() throws Exception {
		Date killTime = new Date();
		int updateCount = seckillDao.reduceNumber(1000L, killTime);
		System.out.println("-------------------");
		System.out.println("updateCount="+updateCount);
	}

	@Test
	public void testQueryById() throws Exception {
		long id= 1000;
		Seckill seckill = seckillDao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill.toString());
	}

	@Test
	public void testQueryAll() throws Exception {
		List<Seckill> seckills = seckillDao.queryAll(0, 20);
		for (Seckill seckill : seckills) {
			System.out.println(seckill.toString());
		}
	}
	
	//测试存储过程
	@Test
	public void testKillByProcedure() throws Exception {
		long seckillId = 1000L;
		long userPhone = 18814383370L;
		// 获取当前系统的时间
        Date date = new Date();
        Map<String, Object> params = new HashMap<String, Object>();
        // 将要调用存储过程的参数放入到map中
        params.put("seckillId", seckillId);
        params.put("phone", userPhone);
        params.put("killTime", date);
        params.put("result", null);
        try {
            // 执行秒杀的逻辑
            seckillDao.killByProcedure(params);
            int result = MapUtils.getIntValue(params, "result", -2);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}

}
