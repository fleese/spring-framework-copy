package org.springframework.core;

/**
 * 封装运行时的 Exceptions和一个异常根源
 * 
 * 类中的getMessage方法将返回嵌套的异常信息；如果有被封装的异常，printStackTrace方法 即其相似的方法会委托给封装的异常。
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
	 * 用制定的异常信息构造该异常实例
	 * 
	 * @param msg
	 */
	public NestedRuntimeException(String msg) {
		super(msg);
	}

	/**
	 * 用指定的异常消息和嵌套异常去构造该异常实例
	 * 
	 * @param msg
	 * @param cause
	 */
	public NestedRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * 返回具体的异常信息和嵌套类型的异常信息
	 */
	@Override
	public String getMessage() {
		return NestedExceptionUtils.buildMessge(super.getMessage(), getCause());
	}
	
	/**
	 * 返回最深层的异常原因
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
	 * 返回最深层的异常原因
	 * 跟getRootCause（）相似，只不过，如果getRootCause方法可放回空时，
	 * 而该方法在没有根本异常时返回的是该异常本身
	 * @return
	 */
	public Throwable getMostSpecificCause(){
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}
	
	/**
	 * 检查该异常即其嵌套异常中是否包含特定的异常类型
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
