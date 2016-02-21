package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

/**
 * ����BeanFactory����Beanʵ�����޷��ҵ���Ӧ��Bean����ʱ�׳����쳣��
 * 
 * @author Wendy
 *
 */
public class NoSuchBeanDefinitionException extends BeansException {

	/** �޷��ҵ���Bean���� */
	private String beanName;
	/** Ҫ���Bean���� */
	private Class beanType;

	/**
	 * ����һ���µ�NoSuchBeanDefinitionExceptionʵ����
	 * 
	 * @param name
	 *            �Ҳ��������Bean����
	 */
	public NoSuchBeanDefinitionException(String name) {
		super("No bean named '" + name + "' is defined");
		this.beanName = name;
	}

	/**
	 * ����һ���µ�NoSuchBeanDefinitionExceptionʵ����
	 * 
	 * @param name
	 *            �Ҳ��������Bean����
	 * @param message
	 *            ��������ľ�����Ϣ
	 */
	public NoSuchBeanDefinitionException(String name, String message) {
		super("No bean named '" + name + "' is defined: " + message);
		this.beanName = name;
	}

	/**
	 * ����һ���µ�NoSuchBeanDefinitionExceptionʵ����
	 * 
	 * @param type
	 *            Ҫ���Bean����
	 */
	public NoSuchBeanDefinitionException(Class type) {
		super("No unique bean of type [" + type.getName() + "] is defined");
		this.beanType = type;
	}

	/**
	 * ����һ���µ�NoSuchBeanDefinitionExceptionʵ����
	 * @param type
	 *           Ҫ���Bean����
	 * @param message
	 *            ��������ľ�����Ϣ
	 */
	public NoSuchBeanDefinitionException(Class type, String message) {
		super("No unique bean of type[" + type.getName() + "] is defined: " + message);
		this.beanType = type;
	}
	
	/**
	 * ����һ���µ�NoSuchBeanDefinitionExceptionʵ����
	 * @param type Ҫ���Bean����
	 * @param dependencyDescription ԭʼ������������Ϣ����������ע���ʱ��
	 * @param message ��������ľ�����Ϣ
	 */
	public NoSuchBeanDefinitionException(Class type,String dependencyDescription,String message){
		super("No matching bean of type ["+type.getName()+"] found for dependency"+
				(StringUtils.hasLength(dependencyDescription)?" [" + dependencyDescription+"]":"")+
				": "+message
				);
	}
	
	/**
	 * ���ز��Ҳ�����bean������
	 * @return
	 */
	public String getBeanName(){
		return this.beanName;
	}
	
	/**
	 * ���ز��Ҳ�����bean����
	 * @return
	 */
	public Class getBeanType(){
		return this.beanType;
	}

}
