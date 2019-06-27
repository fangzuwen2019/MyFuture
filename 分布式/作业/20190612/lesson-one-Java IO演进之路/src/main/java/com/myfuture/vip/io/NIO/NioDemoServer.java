package com.myfuture.vip.io.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : fangzw
 * @ClassName NioDemoServer
 * @Description :
 * @date: 2019/6/14 8:40
 */
public class NioDemoServer {
    private int port = 8080;
    //轮询器Selector
    private Selector selector;

    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NioDemoServer(int port) {
        try {
            this.port = port;
            ServerSocketChannel sever = ServerSocketChannel.open();
            sever.bind(new InetSocketAddress(this.port));
            sever.configureBlocking(false);//NIO模型默认采用的是阻塞式
            //准备好
            selector = Selector.open();
            //服务准备好，监听中
            sever.register(selector, SelectionKey.OP_ACCEPT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        System.out.println("listen on" + " : " + this.port + ".");
        try {
            while (true) {
                selector.select();
                //拿到所有的号
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(SelectionKey key) throws IOException {
        //针对每一种状态给一个反应
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();

            SocketChannel channel = server.accept();

            channel.configureBlocking(false);

            key = channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int len = channel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                key = channel.register(selector, SelectionKey.OP_WRITE);
                key.attach(content);
                System.out.println("读取的内容是：" + " " + content);
            }
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String content = (String) key.attachment();
            channel.write(ByteBuffer.wrap(("输出：" + content).getBytes()));
            channel.close();
        }
    }

    public static void main(String[] args) {
        new NioDemoServer(8080).listen();
    }

}
