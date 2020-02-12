package org.com.zqh.rpcdemo;

import com.zqh.rpcdemo.IHelloService;
import com.zqh.rpcdemo.User;

/**
 * @Author：zhengqh
 * @date 2020/2/11 21:44
 **/
public class HelloServiceImpl implements IHelloService {
    public String sayHello(String content) {
        System.out.println("come in server sayHello :"+content);
        return "sayHello :"+content;
    }

    public String saveUer(User user) {
        System.out.println("come in server saveUer :"+user.toString());
        return "success";
    }
}
