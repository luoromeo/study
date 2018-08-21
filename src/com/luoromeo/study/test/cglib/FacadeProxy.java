package com.luoromeo.study.test.cglib;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月04日 17:50
 * @modified By
 */
@SuppressWarnings("unchecked")
public class FacadeProxy implements InvocationHandler {

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("接口方法调用开始");
        //执行方法
        System.out.println("method toGenericString:" + method.toGenericString());
        System.out.println("method name:" + method.getName());
        System.out.println("method args:" + args[0]);
        System.out.println("接口方法调用结束");
        return "调用返回值";
    }

    public static <T> T newMapperProxy(Class<T> mapperInterface) {
        ClassLoader classLoader = mapperInterface.getClassLoader();
        Class<?>[] interfaces = new Class[]{mapperInterface};
        FacadeProxy proxy = new FacadeProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}
