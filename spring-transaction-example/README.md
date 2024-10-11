事务源码探究代码
参考链接
https://www.cnblogs.com/xshan/p/17810179.html
https://www.cnblogs.com/dennyzhangdd/p/9602673.html


# 编程式事务

## TransactionTemplate.execute()

1.getTransaction()获取事务，源码见3.3.1
2.doInTransaction()执行业务逻辑，这里就是用户自定义的业务代码。如果是没有返回值的，就是doInTransactionWithoutResult()。
3.commit()事务提交：调用AbstractPlatformTransactionManager的commit，rollbackOnException()异常回滚：调用AbstractPlatformTransactionManager的rollback()，事务提交回滚

# 声明式事务

![kfJUVB](https://cdn.jsdelivr.net/gh/jaclon-m/Image@main/2024/10/kfJUVB.png)

## TransactionAutoConfiguration
org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration（多数据源事务）

EnableTransactionManagementConfiguration支持2种代理方式：
- JdkDynamicAutoProxyConfiguration
- CglibAutoProxyConfiguration

## EnableTransactionManagement

TransactionManagementConfigurationSelector

**最终会执行selectImports方法导入需要加载的类，我们只看proxy模式下，载入了AutoProxyRegistrar、ProxyTransactionManagementConfiguration2个类。
AutoProxyRegistrar：给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件；利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用；
ProxyTransactionManagementConfiguration：就是一个配置类，定义了事务增强器。**


### 何时生成代理

InfrastructureAdvisorAutoProxyCreator

postProcessAfterInitialization - wrapIfNecessary

## ProxyTransactionManagementConfiguration

核心方法 TransactionInterceptor()

如上图TransactionInterceptor复写MethodInterceptor接口的invoke方法，并在invoke方法中调用了父类TransactionAspectSupport的invokeWithinTransaction()方法

申明式事务，核心流程如下：

1.createTransactionIfNecessary():如果有必要，创建事务
2.InvocationCallback的proceedWithInvocation()：InvocationCallback是父类的内部回调接口，子类中实现该接口供父类调用，子类TransactionInterceptor中invocation.proceed()。回调方法执行
3.异常回滚completeTransactionAfterThrowing()

不管是编程式事务，还是声明式事务，最终源码都是调用事务管理器的PlatformTransactionManager接口的3个方法：
1. getTransaction
2. commit
3. rollback

# 核心方法实现

![IBTJeq](https://cdn.jsdelivr.net/gh/jaclon-m/Image@main/2024/10/IBTJeq.png)

## getTransaction()

![irgyy3](https://cdn.jsdelivr.net/gh/jaclon-m/Image@main/2024/10/irgyy3.png)

doSuspend(),挂起事务，AbstractPlatformTransactionManager抽象类doSuspend()会报错：不支持挂起，如果具体事务执行器支持就复写doSuspend()，DataSourceTransactionManager实现如
1.把当前事务的connectionHolder数据库连接持有者清空。

2.当前线程解绑datasource.其实就是ThreadLocal移除对应变量（TransactionSynchronizationManager类中定义的private static final ThreadLocal<Map<Object, Object>> resources = new NamedThreadLocal<Map<Object, Object>>("Transactional resources");）

开启新事务的准备工作doBegin()的核心操作就是：

1.DataSourceTransactionObject“数据源事务对象”，设置ConnectionHolder，再给ConnectionHolder设置各种属性：自动提交、超时、事务开启、隔离级别。

2.给当前线程绑定一个线程本地变量，key=DataSource数据源  v=ConnectionHolder数据库连接。

## commit

cleanupAfterCompletion(status);
1）设置事务状态为已完成。
2)  如果是新的事务同步，解绑当前线程绑定的数据库资源，重置数据库连接
3）如果存在挂起的事务（嵌套事务），唤醒挂起的老事务的各种资源：数据库资源、同步器。
    
# 问题

nested 线程释放后如何再重复回来
