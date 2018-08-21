package com.luoromeo.study.gof.interpreter;

/**
 * @description 抽象表达式，声明一个抽象的解释操作，这个接口为抽象语法树中所有的节点所共享。
 * @author zhanghua.luo
 * @date 2018年08月20日 17:37
 * @modified By
 */
public abstract class AbstractExpression {

    public abstract void interpret(Context context);
}
