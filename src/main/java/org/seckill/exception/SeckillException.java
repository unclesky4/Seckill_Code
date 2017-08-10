package org.seckill.exception;

/**
 * 秒杀相关业务异常
 * @author unclesky4 on 2017/08/04
 *
 */
public class SeckillException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillException(String message) {
		super(message);
	}

}
