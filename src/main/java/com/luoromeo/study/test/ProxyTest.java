package com.luoromeo.study.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月30日 10:18
 * @modified By
 */
public class ProxyTest {

    public static void main(String[] args) {

        try(InputStream is = new FileInputStream("")) {

        } catch (IOException e) {
        }


        Object[] elements = new Object[1000];

        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandle(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
            elements[i] = proxy;
        }
        Integer key = new Random().nextInt(elements.length) + 1;

        Integer result = Arrays.binarySearch(elements, key);

        if (result > 0) {
            System.out.println(elements[result]);
        }
    }
}

class TraceHandle implements InvocationHandler {

    private Object target;

    public TraceHandle(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}
