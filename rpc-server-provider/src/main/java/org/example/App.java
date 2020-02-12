package org.example;

import com.zqh.rpcdemo.IHelloService;
import org.com.zqh.rpcdemo.HelloServiceImpl;
import org.com.zqh.rpcdemo.RpcProxyServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //发布服务
        IHelloService iHelloService =new HelloServiceImpl();
        RpcProxyServer rpcProxyServer =new RpcProxyServer();
        rpcProxyServer.publisher(iHelloService,8090);
    }
}
