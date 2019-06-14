package com.myfuture.vip.io.NIO.buffer;

import java.nio.ByteBuffer;

/**
 * @author : fangzw
 * @ClassName ReadOnlyBuffer
 * @Description :  只读缓冲区
 * @date: 2019/6/14 14:38
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        //缓冲区中的的数据0-9
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //创建只读缓冲区
        ByteBuffer readOnly = buffer.asReadOnlyBuffer();

        //改变原缓冲区的内容
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readOnly.position(0);
        readOnly.limit(buffer.capacity());

        //只读缓冲区的内容也随之改变
        while (readOnly.remaining() > 0) {
            System.out.println(readOnly.get());
        }

    }
}
