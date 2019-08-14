package com.myfuture.vip.io.NIO.buffer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author : fangzw
 * @ClassName MappedBuffer
 * @Description :  IO映射缓冲区
 * @date: 2019/6/14 14:29
 */
public class MappedBuffer {
    private static final int start = 0;
    private static final int size = 26;

    public static void main(String[] args) throws Exception {
        File file = new File("D://test.txt");
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        FileChannel fc = raf.getChannel();

        //把缓冲区跟文件系统进行一个映射关联
        //只要操作缓冲区里面的内容，文件内容也会跟着改变
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);

        mbb.put(0, (byte) 97);
        mbb.put(25, (byte) 122);
        System.out.println(mbb);
        raf.close();

    }
}
