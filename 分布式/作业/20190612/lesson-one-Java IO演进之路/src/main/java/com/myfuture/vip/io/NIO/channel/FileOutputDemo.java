package com.myfuture.vip.io.NIO.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fangzw
 * @ClassName FileOutputDemo
 * @Description :
 * @date: 2019/6/14 10:35
 */
public class FileOutputDemo {
    static private final byte message[] = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("D://test.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < message.length; ++i) {
            buffer.put(message[i]);
        }
        buffer.flip();
        channel.write(buffer);
        fileOutputStream.close();
    }
}
