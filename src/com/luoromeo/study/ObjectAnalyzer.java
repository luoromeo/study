package com.luoromeo.study;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @description 通过反射提供通用的toString方法。这是一种公认的提供toString方法的手段，在编写程序时会发现，他是非常有用的
 * @author zhanghua.luo
 * @date 2018年07月26日 11:35
 * @modified By
 */
public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (obj == null) {
            return null;
        }

        if (visited.contains(obj)) {
            return "...";
        }

        visited.add(obj);

        Class cl = obj.getClass();
        if (cl == String.class) {
            return (String) obj;
        }
        if (cl.isArray()) {

            StringBuilder r = new StringBuilder(cl.getComponentType() + "[]{");

            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) {
                    r.append(",");
                }
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) {
                    r.append(val);
                } else {
                    r.append(toString(val));
                }
            }
            return r.append("}").toString();
        }

        StringBuilder r = new StringBuilder(cl.getName());
        do {
            r.append("[");
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.toString().endsWith("[")) {
                        r.append(",");
                    }
                    r.append(field.getName()).append("=");
                    try {
                        Class t = field.getType();
                        Object val = field.get(obj);
                        if (t.isPrimitive()) {
                            r.append(val);
                        } else {
                            r.append(toString(val));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            r.append("]");
            cl = cl.getSuperclass();
        } while (cl != null);
        return r.toString();
    }

    @SuppressWarnings("all")
    public static Object goodCopyOf(Object o, int newLength) {
        Class cl = o.getClass();
        if (!cl.isArray()) {
            return null;
        }
        Class componentType = cl.getComponentType();
        int length = Array.getLength(o);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(o, 0, newArray, 0, Math.min(length, newLength));
        return newArray;

    }
}
