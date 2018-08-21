package com.luoromeo.study.gof.interpreter;

/**
 * @description 非终结符表达式
 * @author zhanghua.luo
 * @date 2018年08月20日 17:41
 * @modified By
 */
public class NonterminalExpression extends AbstractExpression {
    @Override
    public void interpret(Context context) {
        System.out.println("非终结符表达式");
    }
}
