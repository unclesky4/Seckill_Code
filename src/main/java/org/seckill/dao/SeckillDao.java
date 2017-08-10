package org.seckill.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

/**
 * 
 * @author unclesky4 on 2017/08/03
 *
 */

public interface SeckillDao {
	
	//java没有保存形参的记录： queryAll(int offset, int limit) ->queryAll(arg0, arg1)  解决办法：@Param("参数名")
	/**
	 * 减库存
	 * @param seckillId
	 * @param killTime
	 * @return  返回影响的行数
	 */
	int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
	
	/**
	 * 根据id查询秒杀对象
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询秒杀商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
	
	/**
     *  通过存储过程执行秒杀的逻辑.
     * */
    void killByProcedure(Map params);

}
