package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * Spring Bean容器的基础接口，为特定的目的还有
 * ListableBeanFactory和ConfigurableBeanFactory接口
 * 
 * 这个接口需要用包含bean definition的类实现，其中每个bean definition
 * 用一个String类型的name唯一标示。依据bean definition，容器可能返回一个
 * 完成独立的，脱离容器管理的一个对象（原型设计模式），或者返回一个Factory容器生命
 * 周期内共享的单例。从Spring 2.0开始，可以根据不同的业务逻辑上下文选择不同
 * 实例的生命周期（比如，在web环境中使用“request”,"session"来支持实例的
 * 生命周期）
 * 
 * BeanFactory是一个中心登记和中心配置的应用组件。
 * 
 * 最好通过Setter(注射器)和Constructor(构造器)使用DI(依赖注入)的方式去配置
 * 应用对象间的依赖，而不是像BeanFactory lookup一样去拉取对象.Spring的依赖注入
 * 功能是由BeanFactory接口和其子接口实现。
 * 
 * 通常，BeanFactory将从一个配置资源加载bean definitions,然后用org.springframework.beans
 * 包中的类去配置这些bean。关于bean definitions如何存储格式没有严格的约束，可以是LDAP, RDBMS, XML,
 * properties file等等，但是bean definitions存储实现推荐能支持bean之间的相互引用（Dependency Injection）
 * 
 * 相对于ListableBeanFactory中的方法，如果一个Factory是一个HierarchicalBeanFactory本身或其子孙
 * 其所有操作都将检查其父Factory.如果在当前的Factory中不能找到对应的Bean
 * ，它会去请求其直接父Factory。支持当前Factory中的Bean覆盖父Factory中同名的Bean
 * 
 * Bean Factory的实现应尽可能的支持Bean的生命周期接口。Bean完整的初始化方法和顺序如下：
 * 1.BeanNameAware的setBeanName方法
 * 2.BeanClassLoaderAware的setBeanClassLoader方法
 * 3.BeanFactoryAware的setBeanFactory方法
 * 4.ResourceLoaderAware的setResourceLoader方法
 * 在应用上下文中运行时：
 * 5.ApplicationEventPublisherAware的setApplicationEventPublisher方法
 * 6.MessageSourceAware的setMessageSource方法
 * 7.ApplicationContextAware的setApplicationContext方法
 * 8.ServletContextAware的setServletContext方法
 * 9.BeanPostProcessors的postProcessBeforeInitialization方法
 * 10.InitializingBean的afterPropertiesSet方法
 * 11.一个自定义的初始化方法
 * 12.BeanPostProcessors的postProcessAfterInitialization方法
 * 
 * 当关闭bean factory时，下属生命周期方法将执行：
 * 1.DisposableBean的destroy
 * 2.一个自定义的销毁方法
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
	 * 用于区分FactoryBean实例和被FactoryBean构建的beans。
	 * 比如，一个名叫myJndiObject的FactoryBean,当用'&myJndiObject'
	 * 获取时将返回这个Factory,而不是被这个Factory构建的实例
	 */
	String FACTORY_BEAN_PREFIX = "&";
	
	/**
	 * 放回一个实例，其可能是共享的或者独立的（不受Spring容器管理）。
	 * 
	 * 这个方法允许Spring BeanFactory作为单例或者原型设计模式的替代者。
	 * 在单例模式下，调用者可能会持有返回对象的应用，所以无法保证线程安全
	 * 
	 * 将别名翻译为标准的bean名称。
	 * 如果bean无法在当前Factory搜索到，将在父Factory中搜索。
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException 如果查询不到指定名称的bean definition
	 * @throws BeansException 如果无法获取该bean
	 */
	Object getBean(String name) throws BeansException;
	
	/**
	 * 放回一个实例，其可能是共享的或者独立的（不受Spring容器管理）。
	 * 
	 * 基本跟getBean(String)相似的，但是该方法是类型安全的。如果放回的bean
	 * 不是这要求的类型会抛出BeanNotOfRequiredTypeException。因此不会
	 * 像getBean(String)方法，当对返回的bean进行类型转化可能会抛出ClassCastException
	 * 
	 * 将别名翻译为标准的bean名称。
	 * 如果bean无法在当前Factory搜索到，将在父Factory中搜索。
	 * 
	 * 
	 * @param name
	 * @param requiredType 可以是实际类型的接口或者超类
	 * @return
	 * @throws NoSuchBeanDefinitionException 如果没有bean definition
	 * @throws BeanNotOfRequiredTypeException 如果bean不是这要求的类型
	 * @throws BeansException 如果bean不能被创建
	 */
	<T> T getBean(String name,Class<T> requiredType)throws BeansException;
	
	/**
	 * 返回符合要求类型的bean实例
	 * @param requiredType 可以是实际bean类型的接口或者超类，不允许null
	 * 该
	 * @return
	 * @throws BeansException
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;
}
