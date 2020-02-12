package com.zqh.rpcdemo;

import java.io.Serializable;

/**
 * @Author：zhengqh
 * @date 2020/2/11 22:01
 **/

//封装的参数信息  支持序列化  远程传输
public class RpcRequest implements Serializable {
    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
