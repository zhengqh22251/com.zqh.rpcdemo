package com.zqh.rpcdemo;

/**
 * @Author：zhengqh
 * @date 2020/2/11 21:33
 **/
public interface IHelloService {

    String sayHello(String content);

    String saveUer(User user);
}
