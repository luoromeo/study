package com.luoromeo.study.gof.ps.event;

import java.lang.reflect.Method;

public class Event {

    //委托的方法名称
    private String methodName;
    //委托者
    private Object targetObj;
    //方法参数
    private Class[] paramsType;
    //参数类型
    private Object[] params;

    public Event(String methodName, Object targetObj, Object[] params) {
        this.methodName = methodName;
        this.targetObj = targetObj;
        this.params = params;
        if (params != null) {
            createParamsByClass();
        }
    }

    /**
     * 执行方法
     *
     * @throws Exception
     * @throws SecurityException
     */
    public void invoker() throws Exception, SecurityException {
        Method method = targetObj.getClass().getMethod(this.methodName, this.paramsType);
        if (method != null) {
            method.invoke(targetObj, this.params);
        }
    }

    /**
     * 获取方法参数的类型
     */
    private void createParamsByClass() {
        this.paramsType = new Class[this.params.length];
        for (int i = 0; i < this.params.length; i++) {
            this.paramsType[i] = this.params[i].getClass();
        }
    }


}
