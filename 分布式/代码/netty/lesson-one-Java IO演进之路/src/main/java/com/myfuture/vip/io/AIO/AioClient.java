package com.myfuture.vip.io.AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author : fangzw
 * @ClassName AioClient
 * @Description :  AIO客户端
 * @date: 2019/6/13 18:16
 */
public class AioClient {
    private final AsynchronousSocketChannel client;

    public AioClient() throws Exception {
        client = AsynchronousSocketChannel.open();
    }

    private void connect(String host, int port) throws Exception {
        client.connect(new InetSocketAddress(host, port), null, new CompletionHandler<Void, Void>() {
            @Override
            public void completed(Void result, Void attachment) {
                try {
                    client.write(ByteBuffer.wrap("这是一条测试数据".getBytes())).get();
                    System.out.println("已发送至服务器");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println(exc);
            }
        });
        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        client.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer result, Object attachment) {
                System.out.println("IO操作完成" + " " + result);
                System.out.println("获取反馈结果" + new String(byteBuffer.array()));
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println(exc);
            }
        });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        new AioClient().connect("localhost", 8000);
    }
}
