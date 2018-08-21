package com.luoromeo.study.test.javassist;

import java.lang.reflect.Method;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月08日 09:32
 * @modified By
 */
public interface InvocationHandler {

    /**
     * 业务逻辑填充
     * @param proxy 生成的代理对象
     * @param method 调用的方法
     * @param args 调用该方法的参数
     * @return 调用该方法的返回值
     * @throws Throwable
     */
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
