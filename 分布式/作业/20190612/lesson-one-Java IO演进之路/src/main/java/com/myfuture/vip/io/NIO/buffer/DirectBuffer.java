package com.myfuture.vip.io.NIO.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fangzw
 * @ClassName DirectBuffer
 * @Description :  直接缓冲区
 * @date: 2019/6/14 14:02
 */
public class DirectBuffer {
    public static void main(String[] args) throws Exception {
        File file = new File("D://test.txt");
        FileInputStream in = new FileInputStream(file);
        FileChannel channelIn = in.getChannel();

        String fileCopy = String.format("D://testcopy.txt");
        FileOutputStream out = new FileOutputStream(fileCopy);
        FileChannel channelOut = out.getChannel();

        //使用allocateDirect，而不是allocate
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while (true) {
            buffer.clear();
            int read = channelIn.read(buffer);
            if (read == -1) {
                break;
            }

            buffer.flip();
            channelOut.write(buffer);
        }
    }
}
