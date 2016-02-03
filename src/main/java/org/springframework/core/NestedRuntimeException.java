package org.springframework.core;

/**
 * ��װ����ʱ�� Exceptions��һ���쳣��Դ
 * 
 * ���е�getMessage����������Ƕ�׵��쳣��Ϣ������б���װ���쳣��printStackTrace���� �������Ƶķ�����ί�и���װ���쳣��
 * 
 * @author Wendy
 * @see #getMessage
 * @see #printStackTrace
 */
public abstract class NestedRuntimeException extends RuntimeException {
	/** Use serialVersionUID from Spring 1.2 for interoperability */
	private static final long serialVersionUID = 5439915454935047936L;

	static {
		// Eagerly load the NestedExceptionUtils class to avoid classloader
		// deadlock
		// issues on OSGi when calling getMessage(). Reported by Don Brown;
		// SPR-5607.
		NestedExceptionUtils.class.getName();
	}

	/**
	 * ���ƶ����쳣��Ϣ������쳣ʵ��
	 * 
	 * @param msg
	 */
	public NestedRuntimeException(String msg) {
		super(msg);
	}

	/**
	 * ��ָ�����쳣��Ϣ��Ƕ���쳣ȥ������쳣ʵ��
	 * 
	 * @param msg
	 * @param cause
	 */
	public NestedRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * ���ؾ�����쳣��Ϣ��Ƕ�����͵��쳣��Ϣ
	 */
	@Override
	public String getMessage() {
		return NestedExceptionUtils.buildMessge(super.getMessage(), getCause());
	}
	
	/**
	 * �����������쳣ԭ��
	 * @return
	 */
	public Throwable getRootCause(){
		Throwable rootCause = null;
		Throwable cause = getCause();
		while(cause != null && cause != rootCause){
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}
	
	/**
	 * �����������쳣ԭ��
	 * ��getRootCause�������ƣ�ֻ���������getRootCause�����ɷŻؿ�ʱ��
	 * ���÷�����û�и����쳣ʱ���ص��Ǹ��쳣����
	 * @return
	 */
	public Throwable getMostSpecificCause(){
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}
	
	/**
	 * �����쳣����Ƕ���쳣���Ƿ�����ض����쳣����
	 * @param exType
	 * @return
	 */
	public boolean contains(Class exType){
		if(exType == null){
			return false;
		}
		if(exType.isInstance(this)){
			return true;
		}
		Throwable cause = getCause();
		if(cause == this){
			return false;
		}
		if(cause instanceof NestedRuntimeException){
			return ((NestedRuntimeException)cause).contains(exType);
		}
		else{
			while(cause != null){
				if(exType.isInstance(cause)){
					return true;
				}
				if(cause.getCause() == cause){
					break;
				}
				cause = cause.getCause();
			}
			return false;
		}
	}

}
