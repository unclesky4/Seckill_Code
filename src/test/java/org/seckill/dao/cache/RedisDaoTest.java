package org.seckill.dao.cache;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dao.SeckillDao;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
	
	private long id = 1000;
	
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private SeckillDao seckillDao;

	@Test
	public void testGetSeckill() {
		//get and put
		Seckill seckill = redisDao.getSeckill(id);
		if(seckill == null) {
			seckill = seckillDao.queryById(id);
			System.out.println(seckill);
			if(seckill != null) {
				String result = redisDao.putSeckill(seckill);
				System.out.println(result);
				Seckill seckill2 = redisDao.getSeckill(id);
				System.out.println(seckill2);
			}
		}
	}

}
