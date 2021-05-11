/**
 * 一是，只知道使用并发工具，但并不清楚当前线程的来龙去脉，解决多线程问题却不了解线程。比如，使用 ThreadLocal 来缓存数据，以为
 * ThreadLocal 在线程之间做了隔离不会有线程安全问题，没想到线程重用导致数据串了。请务必记得，在业务逻辑结束之前清理 ThreadLocal
 * 中的数据。二是，误以为使用了并发工具就可以解决一切线程安全问题，期望通过把线程不安全的类替换为线程安全的类来一键解决问题。比如，认为使用了
 * ConcurrentHashMap
 * 就可以解决线程安全问题，没对复合逻辑加锁导致业务逻辑错误。如果你希望在一整段业务逻辑中，对容器的操作都保持整体一致性的话，需要加锁处理。三是，没有充分了解并发工具的特性，还是按照老方式使用新工具导致无法发挥其性能。比如，使用了
 * ConcurrentHashMap，但没有充分利用其提供的基于 CAS
 * 安全的方法，还是使用锁的方式来实现逻辑。你可以阅读一下ConcurrentHashMap 的文档，看一下相关原子性操作 API
 * 是否可以满足业务需求，如果可以则优先考虑使用。四是，没有了解清楚工具的适用场景，在不合适的场景下使用了错误的工具导致性能更差。比如，没有理解
 * CopyOnWriteArrayList 的适用场景，把它用在了读写均衡或者大量写操作的场景下，导致性能问题。对于这种场景，你可以考虑是用普通的
 * List。
 *
 * @author jaclon
 * @since 2021/5/11 15:24
 */
/**
 * 今天我们多次用到了 ThreadLocalRandom，你觉得是否可以把它的实例设置到静态变量中，在多线程情况下重用呢？ConcurrentHashMap
 * 还提供了 putIfAbsent 方法，你能否通过查阅JDK 文档，说说 computeIfAbsent 和 putIfAbsent 方法的区别？
 */
/**
 * 先说结论：不可以，结果是除了初始化 ThreadLocalRandom
 * 的主线程获取的随机值是无模式的（调用者不可预测下个返回值，满足我们对伪随机的要求）之外，其他线程获得随机值都不是相互独立的（本质上来说，是因为他们用于生成随机数的种子
 * seed 的值可预测的，为 i*gamma，其中 i 是当前线程调用随机数生成方法次数，而 gamma 是 ThreadLocalRandom 类的一个
 * long 静态字段值）。例如，一个有趣的现象是，所有非初始化 ThreadLocalRandom 实例的线程如果调用相同次数的 nextInt
 * () 方法，他们得到的随机数串是完全相同的。 造成这样现象的原因在于，ThreadLocalRandom 类维护了一个类单例字段，线程通过调用
 * ThreadLocalRandom#current() 方法来获取 ThreadLocalRandom 单例，然后以线程维护的实例字段
 * threadLocalRandomSeed 为种子生成下一个随机数和下一个种子值。
 * 那么既然是单例模式，为什么多线程共用主线程初始化的实例就会出问题呢。问题就在于 current 方法，线程在调用 current
 * () 方法的时候，会根据用每个线程的 thread 的一个实例字段 threadLocalRandomProbe 是否为 0
 * 来判断是否当前线程实例是否为第一次调用随机数生成方法，从而决定是否要给当前线程初始化一个随机的 threadLocalRandomSeed
 * 种子值。因此，如果其他线程绕过 current 方法直接调用随机数方法，那么它的种子值就是 0, 1*gamma, 2*gamma...
 * 因此也就是可预测的了
 */
/**
 * 1.参数不一样，putIfAbsent是值，computeIfAbsent是mappingFunction
 * 2.返回值不一样，putIfAbsent是之前的值，computeIfAbsent是现在的值
 * 3.putIfAbsent可以存入null，computeIfAbsent计算结果是null只会返回null，不会写入。
 */
package com.jaclon.mistakesOfBuz.concurrenttool;
