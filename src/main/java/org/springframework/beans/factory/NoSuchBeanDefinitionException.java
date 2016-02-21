package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

/**
 * 当在BeanFactory中用Bean实例名无法找到相应的Bean定义时抛出该异常。
 * 
 * @author Wendy
 *
 */
public class NoSuchBeanDefinitionException extends BeansException {

	/** 无法找到的Bean名称 */
	private String beanName;
	/** 要求的Bean类型 */
	private Class beanType;

	/**
	 * 构造一个新的NoSuchBeanDefinitionException实例。
	 * 
	 * @param name
	 *            找不到定义的Bean名称
	 */
	public NoSuchBeanDefinitionException(String name) {
		super("No bean named '" + name + "' is defined");
		this.beanName = name;
	}

	/**
	 * 构造一个新的NoSuchBeanDefinitionException实例。
	 * 
	 * @param name
	 *            找不到定义的Bean名称
	 * @param message
	 *            描述问题的具体信息
	 */
	public NoSuchBeanDefinitionException(String name, String message) {
		super("No bean named '" + name + "' is defined: " + message);
		this.beanName = name;
	}

	/**
	 * 构造一个新的NoSuchBeanDefinitionException实例。
	 * 
	 * @param type
	 *            要求的Bean类型
	 */
	public NoSuchBeanDefinitionException(Class type) {
		super("No unique bean of type [" + type.getName() + "] is defined");
		this.beanType = type;
	}

	/**
	 * 构造一个新的NoSuchBeanDefinitionException实例。
	 * @param type
	 *           要求的Bean类型
	 * @param message
	 *            描述问题的具体信息
	 */
	public NoSuchBeanDefinitionException(Class type, String message) {
		super("No unique bean of type[" + type.getName() + "] is defined: " + message);
		this.beanType = type;
	}
	
	/**
	 * 构造一个新的NoSuchBeanDefinitionException实例。
	 * @param type 要求的Bean类型
	 * @param dependencyDescription 原始依赖的描述信息，例如依赖注入的时候
	 * @param message 描述问题的具体信息
	 */
	public NoSuchBeanDefinitionException(Class type,String dependencyDescription,String message){
		super("No matching bean of type ["+type.getName()+"] found for dependency"+
				(StringUtils.hasLength(dependencyDescription)?" [" + dependencyDescription+"]":"")+
				": "+message
				);
	}
	
	/**
	 * 返回查找不到的bean的名字
	 * @return
	 */
	public String getBeanName(){
		return this.beanName;
	}
	
	/**
	 * 返回查找不到的bean类型
	 * @return
	 */
	public Class getBeanType(){
		return this.beanType;
	}

}
