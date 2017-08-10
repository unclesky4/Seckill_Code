package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * 
 * @author unclesky4 on 2017/08/03
 *
 */

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可考虑重复
	 * @param seckillId
	 * @param userPhone
	 * @return  返回影响的行数
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
	
	/**
	 * 根据id查询SuccessKilled并携带秒杀产品对象实体
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
