package com.myfuture.vip.io.bio;

import java.io.OutputStream;
import java.net.Socket;

/**
 * @author : fangzw
 * @ClassName BioClient
 * @Description :  BIO客户端
 * @date: 2019/6/13 17:43
 */
public class BioClient {
    public static void main(String[] args) throws Exception {
        //填写的是服务端的IP和端口
        Socket client = new Socket("localhost", 8080);

        OutputStream outputStream = client.getOutputStream();

        String msg = "喔，我是中国人！";

        System.out.println("客户端传递的信息是:" + " " + msg);
        outputStream.write(msg.getBytes());
        outputStream.close();
        client.close();
    }
}
