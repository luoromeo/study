package com.luoromeo.study.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年07月26日 09:55
 * @modified By
 */
public class ReflectionTest {

    public static void main(String[] args) {
        String name;

        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }

        try {
            Class cl = Class.forName(name);
            Class superCl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print("class" + name);
            if (superCl != null && superCl != Object.class) {
                System.out.print(" extends " + superCl.getName());
            }

            System.out.print("\n{\n");
            printConstrtctors(cl);
            System.out.println();
            printMethod(cl);
            System.out.println();
            printFields(cl);
            System.out.println();
        } catch (Exception ignore) {

        }
    }

    public static void printConstrtctors(Class cl) {
        Constructor[] constructors = cl.getConstructors();

        for (Constructor c : constructors) {
            String name = c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");

        }
    }

    public static void printMethod(Class cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
            Class retType = method.getReturnType();
            String name = method.getName();

            System.out.print("    ");
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void printFields(Class cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
