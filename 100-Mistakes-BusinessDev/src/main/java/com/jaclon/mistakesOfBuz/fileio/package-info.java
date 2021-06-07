/**
 * 1. 如果需要读写字符流，那么需要确保文件中字符的字符集和字符流的字符集是一致
 * 的，否则可能产生乱码
 * 2. 使用 Files 类的一些流式处理操作，注意使用 try-with-resources 包装 Stream，确 保底层文件资源可以释放，
 * 避免产生 too many open files 的问题
 * 3. 进行文件字节流操作的时候，一般情况下不考虑进行逐字节操作，使用缓冲区进行批 量读写减少 IO 次数，性能会好很多。
 * 一般可以考虑直接使用缓冲输入输出流 BufferedXXXStream，追求极限性能的话可以考虑使用 FileChannel 进行流转发
 * 4.  Java 的 File 类和 Files 类提供的文件复制、重命名、删除等操作不是原子性的，需要调用方进行事务控制。
 *
 * @author jaclon
 * @since 2021/6/7 20:31
 */
package com.jaclon.mistakesOfBuz.fileio;