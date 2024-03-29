/**
 * 三类超时： 连接池等待超时，连接超时、数据返回超时
 * 连接超时配置事件要短，
 * 第三个误区:认为超时时间越长任务接口成功率就越高，将读取超时参数配置得太长。
 *
 * 默认情况下 Feign 的读取超时是 1 秒
 * 如果要配置 Feign 的读取超时，就必须同时配置连接超时，才能生 效。
 * 同时配置 Feign 和 Ribbon 的超时，以 Feign 为准
 *  Ribbon 会自动重试请求
 *  根据 HTTP 协议的规范，Get 请求用于数据查询，而 Post 才是把数据提交到服务端用于修改或新增，，短信接口可 以设计为支持幂等调用的
 *
 *  并发限制了爬虫的抓取能力，defaultMaxPerRoute=2，也就是同一个主机 / 域名的最大并发请求数为 2（HttpClient）
 *
 *  服务端 499
 *  虽然表现为服务端(一般为代理，比如nginx)记录和返回499状态 码，但是其实是因为处理时间太长，客户端超时主动关闭连接，排查两点:
 *  1、客户端读取超时时间多久
 *  2、服务端为什么处理这么慢超过了客户端的读取超时
 *
 *
 * 为什么很少见到写入超时，客户端发送数据到服务端，
 * 首先接力连接(TCP)，然后写 入TCP缓冲区，TCP缓冲区根据时间窗口，发送数据到服务端，
 * 因此写入操作可以任务是自 己本地的操作，本地操作是不需要什么超时时间的，
 * 如果真的有什么异常，那也是连接(TCP)不上，或者超时的问题，连接超时和读取超时就能覆盖这种场景。
 * @author jaclon
 * @since 2021/5/17 17:41
 */
package com.jaclon.mistakesOfBuz.httpinvoke;