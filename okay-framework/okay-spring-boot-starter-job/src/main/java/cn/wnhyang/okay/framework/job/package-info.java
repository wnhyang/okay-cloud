package cn.wnhyang.okay.framework.job;

/**
 * 异步线程池如下配置
 * spring:
 *   task:
 *     # Spring 执行器配置，对应 TaskExecutionProperties 配置类。对于 Spring 异步任务，会使用该执行器。
 *     execution:
 *       thread-name-prefix: task- # 线程池的线程名的前缀。默认为 task- ，建议根据自己应用来设置
 *       pool: # 线程池相关
 *         core-size: 8 # 核心线程数，线程池创建时候初始化的线程数。默认为 8 。
 *         max-size: 20 # 最大线程数，线程池最大的线程数，只有在缓冲队列满了之后，才会申请超过核心线程数的线程。默认为 Integer.MAX_VALUE
 *         keep-alive: 60s # 允许线程的空闲时间，当超过了核心线程之外的线程，在空闲时间到达之后会被销毁。默认为 60 秒
 *         queue-capacity: 200 # 缓冲队列大小，用来缓冲执行任务的队列的大小。默认为 Integer.MAX_VALUE 。
 *         allow-core-thread-timeout: true # 是否允许核心线程超时，即开启线程池的动态增长和缩小。默认为 true 。
 *       shutdown:
 *         await-termination: true # 应用关闭时，是否等待定时任务执行完成。默认为 false ，建议设置为 true
 *         await-termination-period: 60 # 等待任务完成的最大时长，单位为秒。默认为 0 ，根据自己应用来设置
 */
