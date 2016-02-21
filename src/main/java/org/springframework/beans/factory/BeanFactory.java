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
	 * ����Ψһ����Ҫ�����͵�beanʵ��
	 * @param requiredType ������ʵ��bean���͵Ľӿڻ��߳��࣬������null
	 * �÷�����ListableBeanFactory�п����������Ͳ��ң�Ҳ�����������͵����ֲ���
	 * ListableBeanFactory��BeanFactoryUtils�����˴�����bean��ȡ����
	 * @return һ��Ψһ���ϸ������͵�bean��ʵ��
	 * @throws NoSuchBeanDefinitionException ��������ҵ���Ӧ��bean definition��
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;
	
	/**
	 * �Ż��ƶ���Beanʵ�����������ǹ���Ļ��߶�����
	 * ����ָ������������/��������������������bean definition�е��ض���Ĭ�ϲ���������еĻ���
	 * @param name
	 * @param args �þ�̬������������һ��ԭ��ʱʹ�øò���������������£����args�ǷǿյĻ���������Ч�ġ�
	 * @return
	 * @throws NoSuchBeanDefinitionException ����Ҳ�����bean definition
	 * @throws BeanDefinitionStoreException argsΪ�ǿյģ�������ص�bean����ԭ��ģʽ��
	 * @throws BeansException ���bean���ܱ�������
	 */
	Object getBean(String name, Object... args) throws BeansException;
	
	/**
	 * ��⵱ǰ��bean factory�Ƿ����һ���������Ƶ�bean����
	 * ����ڵ�ǰFactory���޷��ҵ���Ӧ��bean,���������丸Factory��Ѱ�ҡ�
	 * @param name
	 * @return
	 */
	boolean containsBean(String name);
	
	/**
	 * ָ�����Ƶ�bean�Ƿ��ǵ����ġ�
	 * ע�⣺�ⷽ���Ż�false�����������һ��������ԭ��ģʽ��bean.
	 * ����false��ָʾ���Ƿǵ����ģ������������bean(���硰request��).
	 * ��isPrototype����ȥ����Ƿ��Ƕ�����ԭ��ģʽ��bean
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException ����޷��ҵ���Ӧ���Ƶ�bean
	 */
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
	
	/**
	 * ָ����bean�Ƿ���ԭ��ģʽ�ģ�����˵��{@link #getBean}���������Ƿ���һ��������ʵ��
	 * 
	 * <p>ע�⣺�÷�������<code>false</code>����ζ��bean���ǵ����ģ�singleton��.
	 * ��������ʾ��һ���Ƕ�����bean,�����Ƿ�Χbean(���磺��request����Χ�ڵ�bean).
	 * ��{@link #isSingleton}����ȥ��ȷ�ļ��bean�Ƿ��ǹ���ĵ���bean.
	 * <p>����bean�ı�������׼��bean����
	 * ����ڵ�ǰ����ʵ�����޷��ҵ���bean�����ڸ�����ʵ���м�������
	 * 
	 * @param name ȥ��ѯ��bean������
	 * @return
	 * @throws NoSuchBeanDefinitionException ����ñ�����bean�����޷��ҵ���Ӧ��bean�׳�
	 * @see #getBean
	 * @see #isSingleton
	 */
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

	/**
	 * ���ָ����bean���ɲ���nameָ�������ϸ��������͡�
	 * ������˵�����Ǽ��{@link #getBean} ����ͨ��bean���Ʒ��ص�beanʵ��
	 * �Ƿ���ϸ�bean��������͡�
	 * <p>����bean�ı�������׼��bean����
	 *  ����ڵ�ǰ����ʵ�����޷��ҵ���bean�����ڸ�����ʵ���м�������
	 * @param name ��Ҫ����bean�����ơ�
	 * @param targetType bean��Ҫ���ϵ�Ŀ������
	 * @return �������name�������bean����Ŀ��������<code>true</code>�����򷵻�<code>false</code>��
	 * @throws NoSuchBeanDefinitionException ����ñ�����bean�����޷��ҵ���Ӧ��bean�׳�
	 * @see #getBean
	 * @see #getType
	 */
	boolean isTypeMatch(String name,Class targetType) throws NoSuchBeanDefinitionException;
	
	/**
	 * ȷ������name�������bean�����͡������˵������ȷ��
	 * ��{@link #getBean}�����������Ʒ��ص�bean�ľ������͡�
	 * <p>��Ϊ{@link FactoryBean},����{@link FactoryBean#getObjectType()}
	 * ���Է������ͣ���������FactoryBean�����bean������
	 * <p>����bean�ı�������׼��bean����
	 * ����ڵ�ǰ����ʵ�����޷��ҵ���bean�����ڸ�����ʵ���м�������
	 * @param name ��Ҫ����bean�����ơ�
	 * @return bean�����ͣ����ߵ�����ȷ��bean������ʱ����<code>null</code>
	 * @throws NoSuchBeanDefinitionException ����ñ�����bean�����޷��ҵ���Ӧ��bean�׳�
	 */
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;
}
