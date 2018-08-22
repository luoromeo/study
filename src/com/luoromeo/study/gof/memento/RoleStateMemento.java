package com.luoromeo.study.gof.memento;

/**
 * @description 角色状态备忘录
 * @author zhanghua.luo
 * @date 2018年08月21日 17:53
 * @modified By
 */
public class RoleStateMemento {

    /** 生命力 */
    private int vit;

    /** 攻击力 */
    private int atk;

    /** 防御力 */
    private int def;

    public RoleStateMemento(int vit, int atk, int def) {
        this.vit = vit;
        this.atk = atk;
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
