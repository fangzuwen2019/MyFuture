package com.myfuture.vip.io.NIO.buffer;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fangzw
 * @ClassName BufferDemo
 * @Description :
 * @date: 2019/6/14 11:22
 */
public class BufferDemo {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D://test.txt");
        FileChannel fc = fileInputStream.getChannel();

        //分配一个10个大小缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        output("初始化", buffer);
        //先读一下
        fc.read(buffer);
        output("调用read()", buffer);

        //准备操作之前，先锁定操作范围
        buffer.flip();
        output("调用flip()", buffer);

        //判断有没有可读数据
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
        }
        output("调用get()", buffer);

        //可以理解为解锁
        buffer.clear();
        output("调用clear()", buffer);

        //最后把通道关闭
        fileInputStream.close();
    }

    /**
     * 把缓冲里面实时状态给打印出来
     *
     * @param step
     * @param buffer
     */
    private static void output(String step, ByteBuffer buffer) {
        System.out.println(step + " : ");
        //容量，数组大小
        System.out.println("capacity: " + buffer.capacity() + ", ");
        //当前操作数据所在的位置，也可以叫游标
        System.out.println("position: " + buffer.position() + ", ");
        //锁定值,flip,数据操作范围索引只能在position-limit之间
        System.out.println("limit: " + buffer.limit());
        System.out.println();
    }
}
