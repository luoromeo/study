package com.luoromeo.study.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月04日 16:56
 * @modified By
 */
public class test {

//    public static void main(String[] args) {
//        // DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
//        // Hello helloProxy = dynamicProxy.getProxy();
//        // helloProxy.say("hallo");
//
//        // CGLibProxy cgLibProxy = new CGLibProxy();
//        // HelloImpl helloProxy = cgLibProxy.getProxy(HelloImpl.class);
//        // helloProxy.say("hello");
//
//        // Hello hello = FacadeProxy.newMapperProxy(Hello.class);
//        // hello.say("hello");
//
//        System.out.println(10 >> 1);
//
//    }

    private static int SIZE = 111111;



    private static void loopList(List<Integer> list)

    {

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < list.size(); i++)

        {

            list.get(i);

        }

        System.out.println(list.getClass().getSimpleName() + "使用普通for循环遍历时间为" +

                (System.currentTimeMillis() - startTime) + "ms");



        startTime = System.currentTimeMillis();

        for (Integer i : list)

        {



        }

        System.out.println(list.getClass().getSimpleName() + "使用foreach循环遍历时间为" +

                (System.currentTimeMillis() - startTime) + "ms");

    }



    public static void main(String[] args)

    {

        List<Integer> arrayList = new ArrayList<Integer>(SIZE);

        List<Integer> linkedList = new LinkedList<Integer>();



        for (int i = 0; i < SIZE; i++)

        {

            arrayList.add(i);

            linkedList.add(i);

        }



        loopList(arrayList);

        loopList(linkedList);

        System.out.println();

    }
}
