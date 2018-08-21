package com.luoromeo.study.gof.interpreter;

/**
 * @description 终结符表达式，实现与文法中的终结符相关的解释操作。
 * @author zhanghua.luo
 * @date 2018年08月20日 17:40
 * @modified By
 */
public class TerminalExpression extends AbstractExpression {
    @Override
    public void interpret(Context context) {
        System.out.println("终端解释器!");
    }
}
