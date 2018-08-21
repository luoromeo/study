package com.luoromeo.study.gof.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月20日 17:43
 * @modified By
 */
public class InterpreterTest {

    public static void main(String[] args) {
        Context context = new Context();
        List<AbstractExpression> list = new ArrayList<>();
        list.add(new TerminalExpression());
        list.add(new TerminalExpression());
        list.add(new NonterminalExpression());

        for (AbstractExpression abstractExpression : list) {
            abstractExpression.interpret(context);
        }
    }
}
