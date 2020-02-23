package org.springframework.core;

/**
 * 帮助类，他能够帮助持有嵌套的异常的异常实现类。
 * 
 * 主要用在框架中
 * @author Wendy
 *
 */
public class NestedExceptionUtils {
	
	/**
	 * 用指定的信息和异常根源去构造一个异常信息
	 * @param message
	 * @param cause
	 * @return
	 */
	public static String buildMessge(String message,Throwable cause){
		if(cause != null){
			StringBuilder sb = new StringBuilder();
			if(message != null){
				sb.append(message).append("; ");
			}
            //test
			sb.append("nested exception is ").append(cause);
			return sb.toString();
		}
		else{
			return message;
		}
	}
}
