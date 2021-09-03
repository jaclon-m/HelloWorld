package com.jaclon.netty.demo.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * ByteBuffer 支持类型化的 put 和 get，put 放入的是什么数据类型，get 就应该使用相应的数据类型来取出，否则可能有 BufferUnderflowException 异常
 *  可以将一个普通 Buffer 转成只读 Buffer  ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
 * @author jaclon
 * @since 2021/9/3 12:19
 */
public class NioFileChannel {
    public static void main(String[] args) throws Exception {
//        read();
//        write();
        mappedByteBuffer();
    }

    /**
     * Flips this buffer. The limit is set to the current position and then the position is set to zero. If the mark is defined then it is discarded.
     * After a sequence of channel-read or put operations, invoke this method to prepare for a sequence of channel-write or relative get operations. For example:
     *        buf.put(magic);    // Prepend header
     *        in.read(buf);      // Read data into rest of buffer
     *        buf.flip();        // Flip buffer
     *        out.write(buf);    // Write header + data to channel
     * This method is often used in conjunction with the compact method when transferring data from one place to another.
     * Returns:
     * This buffer
     * @throws Exception
     */
    public static final void read() throws Exception {
        String str = "Hello World";
        FileOutputStream fileOutputStream = new FileOutputStream("file/file01.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    public static final void write()  throws Exception {
        //创建文件的输入流
        File file = new File("file/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过 fileInputStream 获取对应的 FileChannel -> 实际类型 FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

        //将通道的数据读入到 Buffer
        fileChannel.read(byteBuffer);

        //将 byteBuffer 的字节数据转成 String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    public static final void readAndWrite() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("file/1.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("file/2.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //循环读取
        while (true) {
            //这里有一个重要的操作，一定不要忘了
            /*
            public final Buffer clear() {
                position = 0;
                limit = capacity;
                mark = -1;
                return this;
            }
            */
            byteBuffer.clear(); //清空 buffer
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read = " + read);
            if (read == -1) { //表示读完
                break;
            }
            //将 buffer 中的数据写入到 fileChannel02--2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static final void transferFrom() throws Exception{
        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("file/a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("file/a2.jpg");

        //获取各个流对应的 FileChannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用 transferForm 完成拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        //关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * MappedByteBuffer 可让文件直接在内存（堆外内存）修改,操作系统不需要拷贝一次
     */
    public static final void mappedByteBuffer() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("file/1.txt", "rw");
        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
        //IndexOutOfBoundsException
//        mappedByteBuffer.put(5, (byte) 'Y');
        randomAccessFile.close();
        System.out.println("修改成功~~");
    }

}
