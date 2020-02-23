package org.springframework.core;

/**
 * �����࣬���ܹ���������Ƕ�׵��쳣���쳣ʵ���ࡣ
 * 
 * ��Ҫ���ڿ����
 * @author Wendy
 *
 */
public class NestedExceptionUtils {
	
	/**
	 * ��ָ������Ϣ���쳣��Դȥ����һ���쳣��Ϣ
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
