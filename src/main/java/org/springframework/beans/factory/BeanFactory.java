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
	 * 返回唯一符合要求类型的bean实例
	 * @param requiredType 可以是实际bean类型的接口或者超类，不允许null
	 * 该方法在ListableBeanFactory中可能依照类型查找，也可能依据类型的名字查找
	 * ListableBeanFactory和BeanFactoryUtils包含了大量的bean获取方法
	 * @return 一个唯一符合给定类型的bean的实例
	 * @throws NoSuchBeanDefinitionException 如果不能找到对应的bean definition。
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;
	
	/**
	 * 放回制定的Bean实例，它可能是共享的或者独立的
	 * 允许指定构造器参数/工厂方法参数，覆盖在bean definition中的特定的默认参数（如果有的话）
	 * @param name
	 * @param args 用静态工厂方法创建一个原型时使用该参数。在其他情况下，如果args是非空的话，它是无效的。
	 * @return
	 * @throws NoSuchBeanDefinitionException 如果找不到该bean definition
	 * @throws BeanDefinitionStoreException args为非空的，但是相关的bean不是原型模式的
	 * @throws BeansException 如果bean不能被构建。
	 */
	Object getBean(String name, Object... args) throws BeansException;
	
	/**
	 * 检测当前的bean factory是否包含一个给定名称地bean定义
	 * 如果在当前Factory中无法找到对应的bean,将继续在其父Factory中寻找。
	 * @param name
	 * @return
	 */
	boolean containsBean(String name);
	
	/**
	 * 指定名称的bean是否是单例的。
	 * 注意：这方法放回false不代表这就是一个独立的原型模式的bean.
	 * 返回false仅指示其是非单例的，其可能上下文bean(比如“request”).
	 * 用isPrototype方法去检查是否是独立的原型模式的bean
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException 如果无法找到对应名称的bean
	 */
	boolean isSingleton(String name) throws NoSuchBeanDefinitionException;
	
	/**
	 * 指定的bean是否是原型模式的？即是说，{@link #getBean}方法将总是返回一个独立的实例
	 * 
	 * <p>注意：该方法返回<code>false</code>不意味该bean就是单例的（singleton）.
	 * 它仅仅表示是一个非独立的bean,可能是范围bean(例如：“request”范围内的bean).
	 * 用{@link #isSingleton}方法去明确的检查bean是否是共享的单例bean.
	 * <p>翻译bean的别名到标准的bean名称
	 * 如果在当前工厂实例中无法找到该bean，则在父工厂实例中继续查找
	 * 
	 * @param name 去查询的bean的名称
	 * @return
	 * @throws NoSuchBeanDefinitionException 如果用被给的bean名称无法找到对应的bean抛出
	 * @see #getBean
	 * @see #isSingleton
	 */
	boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

	/**
	 * 检查指定的bean（由参数name指定）符合给定的类型。
	 * 具体来说，就是检查{@link #getBean} 方法通过bean名称返回的bean实例
	 * 是否符合该bean真真的类型。
	 * <p>翻译bean的别名到标准的bean名称
	 *  如果在当前工厂实例中无法找到该bean，则在父工厂实例中继续查找
	 * @param name 需要检查的bean的名称。
	 * @param targetType bean需要符合的目标类型
	 * @return 如果参数name所代表的bean符合目标类型则<code>true</code>，否则返回<code>false</code>。
	 * @throws NoSuchBeanDefinitionException 如果用被给的bean名称无法找到对应的bean抛出
	 * @see #getBean
	 * @see #getType
	 */
	boolean isTypeMatch(String name,Class targetType) throws NoSuchBeanDefinitionException;
	
	/**
	 * 确定参数name所代表的bean的类型。具体的说，就是确定
	 * 由{@link #getBean}方法根据名称返回的bean的具体类型。
	 * <p>作为{@link FactoryBean},方法{@link FactoryBean#getObjectType()}
	 * 可以返回类型，该类型是FactoryBean创造的bean的类型
	 * <p>翻译bean的别名到标准的bean名称
	 * 如果在当前工厂实例中无法找到该bean，则在父工厂实例中继续查找
	 * @param name 需要检查的bean的名称。
	 * @return bean的类型，或者当不能确定bean的类型时返回<code>null</code>
	 * @throws NoSuchBeanDefinitionException 如果用被给的bean名称无法找到对应的bean抛出
	 */
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;
}
