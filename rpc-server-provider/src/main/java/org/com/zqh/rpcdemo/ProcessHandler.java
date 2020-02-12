package org.com.zqh.rpcdemo;

import com.zqh.rpcdemo.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @Author：zhengqh
 * @date 2020/2/11 21:55
 **/
public class ProcessHandler implements  Runnable{
    private Socket socket;
    private Object service;

    public ProcessHandler(Object service,Socket socket) {
        this.service = service;
        this.socket = socket;
    }
    //处理流信息
    public void run() {
        ObjectInputStream objectInputStream=null;
        ObjectOutputStream objectOutputStream = null;
        try {
             // 类 方法名称
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            //定义一个对象封装输入参数
            //从输入流读取 一个输入参数信息类
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //拿到信息后通过反射调用对应的方法 获取返回值
            Object result = invoke(rpcRequest);
            //将返回值  写出去
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // 反射调用对应服务
    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //获取参数
        Object[] args = rpcRequest.getParameters();
        //获取参数对应的类型
        Class<?>[] types = new Class[args.length];
        for (int i = 0; i <args.length ; i++) {
             types[i] = args[i].getClass();
        }
        // 加载对应类
        Class clazz = Class.forName(rpcRequest.getClassName());
        //获取方法
        Method method = clazz.getMethod(rpcRequest.getMethodName(),types);
        //反射调用
        return method.invoke(service,args);
    }
}
