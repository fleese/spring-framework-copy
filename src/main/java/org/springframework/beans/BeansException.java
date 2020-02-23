package org.springframework.beans;

import org.springframework.core.NestedRuntimeException;
import org.springframework.util.ObjectUtils;

/**
 * ��beans���к����Ӱ��������׳��쳣�ĳ�����
 * 
 * ע�⣬����һ������ʱ�쳣����ΪBeans�쳣ͨ���������ġ�
 * 
 * @author Wendy
 *
 */
public class BeansException extends NestedRuntimeException {

	/**
	 * ���ƶ����쳣��Ϣ����BeansExceptionʵ��
	 * 
	 * @param msg
	 */
	public BeansException(String msg) {
		super(msg);
	}

	/**
	 * ���ƶ����쳣��Ϣ�͸��쳣ԭ����BeansExceptionʵ��
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
