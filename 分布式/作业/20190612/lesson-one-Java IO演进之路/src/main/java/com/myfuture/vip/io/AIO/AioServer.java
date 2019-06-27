package com.myfuture.vip.io.AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : fangzw
 * @ClassName AioServer
 * @Description :  AIO服务端
 * @date: 2019/6/13 17:54
 */
public class AioServer {
    private final int port;

    public AioServer(int port) {
        this.port = port;
    }

    //监听
    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(threadGroup);
            server.bind(new InetSocketAddress(port));
            System.out.println("服务已经启动，监听端口是:" + " " + port);

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                //实现completed方法来回调
                //由操作系统触发
                //回调有两个状态，成功
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    System.out.println("IO操作成功，开始获取数据");
                    try {
                        buffer.clear();
                        result.read(buffer).get();
                        buffer.flip();
                        result.write(buffer);
                        buffer.flip();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                    }
                    System.out.println("操作完成");
                }

                //回调有两个状态，失败
                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("IO操作是失败：" + " " + exc);
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new AioServer(8000).listen();
    }
}
