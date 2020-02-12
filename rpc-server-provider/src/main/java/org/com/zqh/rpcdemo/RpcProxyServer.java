package org.com.zqh.rpcdemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author：zhengqh
 * @date 2020/2/11 21:46
 **/

// 代理服务 暴露服务
public class RpcProxyServer {
    // 缓存线程池 核心线程0   最大线程 Integer.max
    ExecutorService executorService = Executors.newCachedThreadPool();
    public void publisher(Object service,int port) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            //不断接受请求
            while(true){
               Socket socket = serverSocket.accept();//接收 阻塞
                //每一个socket 一个线程处理
               executorService.execute(new ProcessHandler(service,socket));//bio
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
