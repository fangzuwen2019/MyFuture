package com.myfuture.vip.io.NIO.channel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fangzw
 * @ClassName FileInputDemo
 * @Description :
 * @date: 2019/6/14 10:35
 */
public class FileInputDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("D://test.txt");
        if (!file.exists()) {
            file.mkdir();
        }
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取通道
        FileChannel channel = fileInputStream.getChannel();
        //创建数据缓存区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //读取数据到缓存区
        channel.read(buffer);
        buffer.flip();
        if (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println((char) b);
        }
        fileInputStream.close();
    }
}
