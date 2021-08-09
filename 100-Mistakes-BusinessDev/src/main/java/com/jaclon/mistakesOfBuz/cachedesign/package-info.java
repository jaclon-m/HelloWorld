/**
 * 第一，我们不能把诸如 Redis 的缓存数据库完全当作数据库来使用。
 * 我们不能假设缓存始 终可靠，也不能假设没有过期的数据必然可以被读取到，需要处理好缓存的回源逻辑;
 * 而且 要显式设置 Redis 的最大内存使用和数据淘汰策略，避免出现 OOM 的问题。
 *
 * 第二，缓存的性能比数据库好很多，我们需要考虑大量请求绕过缓存直击数据库造成数据库 瘫痪的各种情况。
 * 对于缓存瞬时大面积失效的缓存雪崩问题，可以通过差异化缓存过期时间 解决;
 * 对于高并发的缓存 Key 回源问题，可以使用锁来限制回源并发数;
 * 对于不存在的数 据穿透缓存的问题，可以通过布隆过滤器进行数据存在性的预判，或在缓存中也设置一个值 来解决。
 *
 * 第三，当数据库中的数据有更新的时候，需要考虑如何确保缓存中数据的一致性。我们看到，“先更新数据库再删除缓存，访问的时候按需加载数据到缓存”的策略是最为妥当的，
 * 并且要尽量设置合适的缓存过期时间，这样即便真的发生不一致，也可以在缓存过期后数据得到及时同步。
 *
 * 最后，我要提醒你的是，在使用缓存系统的时候，要监控缓存系统的内存使用量、命中率、对象平均过期时间等重要指标，以便评估系统的有效性，并及时发现问题。
 *
 * @author jaclon
 * @since 2021/8/9 12:39
 */
package com.jaclon.mistakesOfBuz.cachedesign;

/**
 *在聊到缓存并发问题时，我们说到热点 Key 回源会对数据库产生的压力问题，
 * 如果 Key 特别热的话，可能缓存系统也无法承受，毕竟所有的访问都集中打到了一台缓存服务 器。
 * 如果我们使用 Redis 来做缓存，那可以把一个热点 Key 的缓存查询压力，分散到多 个 Redis 节点上吗
 *
 * 大 Key 也是数据缓存容易出现的一个问题。如果一个 Key 的 Value 特别大，那么可能 会对 Redis 产生巨大的性能影响，
 * 因为 Redis 是单线程模型，对大 Key 进行查询或删除 等操作，可能会引起 Redis 阻塞甚至是高可用切换。
 *你知道怎么查询 Redis 中的大 Key，以及如何在设计上实现大 Key 的拆分吗
 *
 * 给hotkey加上后缀，让这些hotkey打散到不同的redis实例上;空间换效率，同一个key保留2份，1个不带后缀，1个带后缀
 *
 * https://www.infoq.cn/article/3L3zAQ4H8xpNoM2glSyi
 **/

/**
 *先更新数据库再删缓存，如果并发查询发生在删缓存之前更新数据库之后，查到的不都是旧数据吗?
 * 不是应该先删除缓存，向队列中插入一个数据的修改标识，并发查询发现缓存为空把查询
 * 数据库的标识也放入队列中，等修改的处理完了再处理查询的请求。
 *
 * 我们的目标是避免长期出现不一致(读取到了旧值进入缓存属于长期不一致，因为又需 要等一个缓存周期了)。
 * 先更新数据库再删缓存，如果并发查询发生在删缓存之前更新数据库之 后，查到的不都是旧数据吗?是旧数据但是这是非常短暂的，下次查询就是新数据了。
 *  你说的如何实现绝度一致。先删除缓存，向队列中插入一个数据的修改标识，我们如何确保删除
 *  缓存后向队列插入数据修改标识之前又有请求过来读取数据了呢?绝对一致或许考虑锁的方案。
 *   不过反过来思考，既然已经缓存了，真的需要绝对一致吗?
 **/