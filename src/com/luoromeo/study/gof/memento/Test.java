package com.luoromeo.study.gof.memento;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 17:56
 * @modified By
 */
public class Test {

    public static void main(String[] args) {

        //大战boss前
        GameRole lixiaoyao = new GameRole();
        lixiaoyao.init();
        System.out.println(lixiaoyao.toString());

        //保存进度
        RoleStateCaretaker admin = new RoleStateCaretaker();
        admin.setMemento(lixiaoyao.saveState());

        //大战Boss时，损耗严重
        lixiaoyao.fight();
        System.out.println(lixiaoyao.toString());

        //还原
        lixiaoyao.recoveryState(admin.getMemento());
        System.out.println(lixiaoyao.toString());

    }
}
