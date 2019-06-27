package com.myfuture.vip.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : fangzw
 * @ClassName BioServer
 * @Description :  BIO服务端
 * @date: 2019/6/13 17:34
 */
public class BioServer {

    ServerSocket serverSocket;

    public BioServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务已经启动，监听端口是" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        new BioServer(8080).listen();
    }

    private void listen() throws Exception {
        //循环监听
        while (true) {
            //等待客户端链接，阻塞方法
            Socket client = serverSocket.accept();
            System.out.println(client.getPort());

            InputStream inputStream = client.getInputStream();

            byte[] bytes = new byte[1024];
            int len = inputStream.read(bytes);
            if (len > 0) {
                String msg = new String(bytes, 0, len);
                System.out.println("服务端收到的信息是:" + " " + msg);
            }
        }
    }
}
