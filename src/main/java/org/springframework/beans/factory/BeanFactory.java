package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * Spring Bean�����Ļ����ӿڣ�Ϊ�ض���Ŀ�Ļ���
 * ListableBeanFactory��ConfigurableBeanFactory�ӿ�
 * 
 * ����ӿ���Ҫ�ð���bean definition����ʵ�֣�����ÿ��bean definition
 * ��һ��String���͵�nameΨһ��ʾ������bean definition���������ܷ���һ��
 * ��ɶ����ģ��������������һ������ԭ�����ģʽ�������߷���һ��Factory��������
 * �����ڹ���ĵ�������Spring 2.0��ʼ�����Ը��ݲ�ͬ��ҵ���߼�������ѡ��ͬ
 * ʵ�����������ڣ����磬��web������ʹ�á�request��,"session"��֧��ʵ����
 * �������ڣ�
 * 
 * BeanFactory��һ�����ĵǼǺ��������õ�Ӧ�������
 * 
 * ���ͨ��Setter(ע����)��Constructor(������)ʹ��DI(����ע��)�ķ�ʽȥ����
 * Ӧ�ö�������������������BeanFactory lookupһ��ȥ��ȡ����.Spring������ע��
 * ��������BeanFactory�ӿں����ӽӿ�ʵ�֡�
 * 
 * ͨ����BeanFactory����һ��������Դ����bean definitions,Ȼ����org.springframework.beans
 * ���е���ȥ������Щbean������bean definitions��δ洢��ʽû���ϸ��Լ����������LDAP, RDBMS, XML,
 * properties file�ȵȣ�����bean definitions�洢ʵ���Ƽ���֧��bean֮����໥���ã�Dependency Injection��
 * 
 * �����ListableBeanFactory�еķ��������һ��Factory��һ��HierarchicalBeanFactory�����������
 * �����в�����������丸Factory.����ڵ�ǰ��Factory�в����ҵ���Ӧ��Bean
 * ������ȥ������ֱ�Ӹ�Factory��֧�ֵ�ǰFactory�е�Bean���Ǹ�Factory��ͬ����Bean
 * 
 * Bean Factory��ʵ��Ӧ�����ܵ�֧��Bean���������ڽӿڡ�Bean�����ĳ�ʼ��������˳�����£�
 * 1.BeanNameAware��setBeanName����
 * 2.BeanClassLoaderAware��setBeanClassLoader����
 * 3.BeanFactoryAware��setBeanFactory����
 * 4.ResourceLoaderAware��setResourceLoader����
 * ��Ӧ��������������ʱ��
 * 5.ApplicationEventPublisherAware��setApplicationEventPublisher����
 * 6.MessageSourceAware��setMessageSource����
 * 7.ApplicationContextAware��setApplicationContext����
 * 8.ServletContextAware��setServletContext����
 * 9.BeanPostProcessors��postProcessBeforeInitialization����
 * 10.InitializingBean��afterPropertiesSet����
 * 11.һ���Զ���ĳ�ʼ������
 * 12.BeanPostProcessors��postProcessAfterInitialization����
 * 
 * ���ر�bean factoryʱ�������������ڷ�����ִ�У�
 * 1.DisposableBean��destroy
 * 2.һ���Զ�������ٷ���
 * 
 * @author Wendy
 *
 * @see BeanNameAware#setBeanName
 * @see BeanClassLoaderAware#setBeanClassLoader
 * @see BeanFactoryAware#setBeanFactory
 * @see org.springframework.context.ResourceLoaderAware#setResourceLoader
 * @see org.springframework.context.ApplicationEventPublisherAware#setApplicationEventPublisher
 * @see org.springframework.context.MessageSourceAware#setMessageSource
 * @see org.springframework.context.ApplicationContextAware#setApplicationContext
 * @see org.springframework.web.context.ServletContextAware#setServletContext
 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization
 * @see InitializingBean#afterPropertiesSet
 * @see org.springframework.beans.factory.support.RootBeanDefinition#getInitMethodName
 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization
 * @see DisposableBean#destroy
 * @see org.springframework.beans.factory.support.RootBeanDefinition#getDestroyMethodName
 *
 */
public interface BeanFactory {
	
	/*
	 * ��������FactoryBeanʵ���ͱ�FactoryBean������beans��
	 * ���磬һ������myJndiObject��FactoryBean,����'&myJndiObject'
	 * ��ȡʱ���������Factory,�����Ǳ����Factory������ʵ��
	 */
	String FACTORY_BEAN_PREFIX = "&";
	
	/**
	 * �Ż�һ��ʵ����������ǹ���Ļ��߶����ģ�����Spring����������
	 * 
	 * �����������Spring BeanFactory��Ϊ��������ԭ�����ģʽ������ߡ�
	 * �ڵ���ģʽ�£������߿��ܻ���з��ض����Ӧ�ã������޷���֤�̰߳�ȫ
	 * 
	 * ����������Ϊ��׼��bean���ơ�
	 * ���bean�޷��ڵ�ǰFactory�����������ڸ�Factory��������
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException �����ѯ����ָ�����Ƶ�bean definition
	 * @throws BeansException ����޷���ȡ��bean
	 */
	Object getBean(String name) throws BeansException;
	
	/**
	 * �Ż�һ��ʵ����������ǹ���Ļ��߶����ģ�����Spring����������
	 * 
	 * ������getBean(String)���Ƶģ����Ǹ÷��������Ͱ�ȫ�ġ�����Żص�bean
	 * ������Ҫ������ͻ��׳�BeanNotOfRequiredTypeException����˲���
	 * ��getBean(String)���������Է��ص�bean��������ת�����ܻ��׳�ClassCastException
	 * 
	 * ����������Ϊ��׼��bean���ơ�
	 * ���bean�޷��ڵ�ǰFactory�����������ڸ�Factory��������
	 * 
	 * 
	 * @param name
	 * @param requiredType ������ʵ�����͵Ľӿڻ��߳���
	 * @return
	 * @throws NoSuchBeanDefinitionException ���û��bean definition
	 * @throws BeanNotOfRequiredTypeException ���bean������Ҫ�������
	 * @throws BeansException ���bean���ܱ�����
	 */
	<T> T getBean(String name,Class<T> requiredType)throws BeansException;
	
	/**
	 * ���ط���Ҫ�����͵�beanʵ��
	 * @param requiredType ������ʵ��bean���͵Ľӿڻ��߳��࣬������null
	 * ��
	 * @return
	 * @throws BeansException
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;
}
