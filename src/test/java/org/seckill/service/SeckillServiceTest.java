package org.seckill.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> list = seckillService.getSeckillList();
		logger.info("list={}",list);
	}

	@Test
	public void testGetById() throws Exception {
		long id = 1000L;
		Seckill seckill = seckillService.getById(id);
		logger.info("seckill={}", seckill);
	}

	@Test
	public void testExportSeckillUrl() throws Exception {
		long id = 1000L;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		logger.info("exposer={}",exposer);
		//		exposed=true, md5=83bd13d8e01e98bf8dde8f38be6d201d, seckillId=1000, now=0, start=0, end=0
	}

	@Test
	public void testExecuteSeckill() throws Exception {
		long id = 1000L;
		long phone = 18814383360L;
		String md5 = "83bd13d8e01e98bf8dde8f38be6d201d";
		SeckillExecution seckillExecution;
		try {
			seckillExecution = seckillService.executeSeckill(id, phone, md5);
			logger.info("seckillExecution={}",seckillExecution);
		} catch (RepeatKillException e) {
			logger.info(e.getMessage());
		} catch (SeckillCloseException e) {
			logger.info(e.getMessage());
		} catch (SeckillException e) {
			logger.info(e.getMessage());
		}
	}

}
