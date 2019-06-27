package com.myfuture.vip.io.NIO.buffer;

import java.nio.ByteBuffer;

/**
 * @author : fangzw
 * @ClassName BufferWrap
 * @Description :  手动分配缓存区
 * @date: 2019/6/14 14:00
 */
public class BufferWrap {
    public void myMethod() {
        //分配指定大小的缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(10);

        //包装一个现有的数组
        byte array[] = new byte[10];
        ByteBuffer buffer2 = ByteBuffer.wrap(array);
    }
}
