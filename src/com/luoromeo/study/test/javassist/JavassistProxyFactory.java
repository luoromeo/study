package com.luoromeo.study.test.javassist;

import java.lang.reflect.Method;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月08日 11:12
 * @modified By
 */
public class JavassistProxyFactory implements InvocationHandler {


    //被代理类的对象
    private Object target;

    public JavassistProxyFactory(Object target) {
        this.target = target;
    }

    public JavassistProxyFactory() {
    }

    /*
     * @see cc.lixiaohui.demo.javassist.proxy.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("接口方法调用开始");
        //执行方法
        System.out.println("method toGenericString:" + method.toGenericString());
        System.out.println("method name:" + method.getName());
        System.out.println("method args:" + args[0]);
        System.out.println("接口方法调用结束");
        return "调用返回值";
    }

    // 获取代理类的对象
    public Object getProxy() throws Exception {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass(), this);
    }

}
