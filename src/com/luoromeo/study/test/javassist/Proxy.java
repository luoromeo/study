package com.luoromeo.study.test.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月08日 10:34
 * @modified By
 */
public class Proxy {

    private static final Logger logger = LoggerFactory.getLogger(Proxy.class);

    /**
     * 生成的代理类名前缀
     */
    private static final String PROXY_CLASSNAME_PREFIX = "$Proxy";

    /**
     * 类后缀数字生成器
     */
    private static final AtomicInteger SUFFIX_GENERATOR = new AtomicInteger();

    private static final boolean SHOULD_BE_FINAL = true;

    private static final boolean SHOULD_BE_ABSTRACT = false;

    private static final boolean SHOULD_BE_PUBLIC = true;

    private InvocationHandler invocationHandler;

    private static CompoundKeyWeakHashMap<ClassLoader, Class<?>, Class<?>> proxyClassCache = new CompoundKeyWeakHashMap<>();

    protected Proxy(InvocationHandler invocationHandler) {
        this.invocationHandler = invocationHandler;
    }

    public static Object newProxyInstance(ClassLoader classLoader, Class<?> targetClass, InvocationHandler invocationHandler) throws Exception {

        //check not null
        classLoader = Objects.requireNonNull(classLoader, "classLoader cannot be null");
        targetClass = Objects.requireNonNull(targetClass, "targetClass cannot be null");
        invocationHandler = Objects.requireNonNull(invocationHandler, "invocationHandler cannot be null");

        Class<?> proxyClass = proxyClassCache.get(classLoader, targetClass);
        //有缓存
        if (proxyClass != null) {
            logger.debug("get proxy from cache");
            return proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);
        }

        //singleton instance of classpool
        ClassPool pool = ClassPool.getDefault();
        //生成代理类的全限定名
        String qualifiedName = generateQualifiedName(targetClass);
        //创建代理类
        CtClass proxy = pool.makeClass(qualifiedName);
        //设被代理类继承至proxy
        setSuperClass(pool, proxy);
        //获取被代理类的所有接口
        CtClass[] interfaces = pool.get(targetClass.getName()).getInterfaces();

        int methodIndex = 0;
        // 遍历这些接口
        for (CtClass parent : interfaces) {
            proxy.addInterface(parent);
            // 获取该接口的所有方法
            CtMethod[] methods = parent.getDeclaredMethods();
            for (int j = 0; j < methods.length; ++j) {
                CtMethod method = methods[j];
                String fieldSrc = String.format("private static java.lang.reflect.Method method%d = Class.forName(\"%s\").getDeclaredMethods()[%d];"
                        , methodIndex, parent.getName(), j);
                logger.debug("field src for method {}: {}", method.getName(), fieldSrc);
                // 生成字段
                CtField ctField = CtField.make(fieldSrc, proxy);
                // 添加字段
                proxy.addField(ctField);
                // 生成对应的Method
                generateMethod(pool, proxy, method, methodIndex);

                ++methodIndex;
            }
        }
        // 设置代理类的类修饰符
        setModifiers(proxy, SHOULD_BE_PUBLIC, SHOULD_BE_FINAL, SHOULD_BE_ABSTRACT);
        // 生成构造方法
        generateConstructor(pool, proxy);
        // 持久化class到硬盘, for use of debug
        proxy.writeFile(".");
        // to java.lang.Class
        proxyClass = proxy.toClass(classLoader, null);
        // 缓存
        proxyClassCache.put(classLoader, targetClass, proxyClass);
        return proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);

    }

    /**
     * 生成代理类的全限定名
     */
    private static String generateQualifiedName(Class<?> targetClass) throws Exception {
        CtClass theInterface = null;
        for (CtClass parent : ClassPool.getDefault().get(targetClass.getName()).getInterfaces()) {
            if (theInterface == null) {
                theInterface = parent;
            }
            if (!Modifier.isPublic(parent.getModifiers())) {
                theInterface = parent;
                break;
            }
        }
        return theInterface.getPackageName() + "." + PROXY_CLASSNAME_PREFIX + SUFFIX_GENERATOR.getAndIncrement();
    }


    /**
     * 设置类的修饰符
     */
    private static void setModifiers(CtClass proxy, boolean shouldBePublic, boolean shouldBeFinal, boolean shouldBeAbstract) {
        int modifier = 0;
        modifier = shouldBePublic ? modifier | Modifier.PUBLIC : modifier;
        modifier = shouldBeFinal ? modifier | Modifier.FINAL : modifier;
        modifier = shouldBeAbstract ? modifier | Modifier.ABSTRACT : modifier;
        logger.error(Modifier.toString(modifier));
        proxy.setModifiers(modifier);
    }

    /**
     * 生成构造函数
     */
    private static void generateConstructor(ClassPool pool, CtClass proxy) throws NotFoundException, CannotCompileException {
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{pool.get(InvocationHandler.class.getName())}, proxy);
        String methodBodySrc = String.format("super(%s);", "$1");
        logger.debug("constructor body for constructor {}: {}", ctConstructor.getName(), methodBodySrc);
        ctConstructor.setBody(methodBodySrc);
        proxy.addConstructor(ctConstructor);
    }


    /**
     * 生成代理方法
     */
    private static void generateMethod(ClassPool pool, CtClass proxy, CtMethod method, int methodIndex) throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = new CtMethod(method.getReturnType(), method.getName(), method.getParameterTypes(), proxy);
        String methodBodySrc = String.format("return super.invocationHandler.invoke(this, method%d, $args);", methodIndex);
        logger.debug("method body for method {}: {}", method.getName(), methodBodySrc);
        ctMethod.setBody(methodBodySrc);
        proxy.addMethod(ctMethod);
    }

    /**
     * 把proxy类的父类设置为Proxy
     *
     */
    private static void setSuperClass(ClassPool pool, CtClass proxy) throws CannotCompileException, NotFoundException {
        proxy.setSuperclass(pool.get(Proxy.class.getName()));
    }

}
