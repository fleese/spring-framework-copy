package org.springframework.beans;

import org.springframework.core.NestedRuntimeException;
import org.springframework.util.ObjectUtils;

/**
 * 在beans包中和其子包中所有抛出异常的抽象超类
 * 
 * 注意，这是一个运行时异常，因为Beans异常通常是致命的。
 * 
 * @author Wendy
 *
 */
public class BeansException extends NestedRuntimeException {

	/**
	 * 用制定的异常消息构建BeansException实例
	 * 
	 * @param msg
	 */
	public BeansException(String msg) {
		super(msg);
	}

	/**
	 * 用制定的异常消息和根异常原因构造BeansException实例
	 * 
	 * @param msg
	 * @param cause
	 */
	public BeansException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	@Override
	public boolean equals(Object other){
		if(this == other){
			return true;
		}
		if(!(other instanceof BeansException)){
			return false;
		}
		BeansException otherBe = (BeansException) other;
		return (getMessage().equals(otherBe.getMessage())&&
				ObjectUtils.nullSafeEquals(getCause(), otherBe.getCause()));
	}
	
	@Override
	public int hashCode(){
		return getMessage().hashCode();
	}
}
