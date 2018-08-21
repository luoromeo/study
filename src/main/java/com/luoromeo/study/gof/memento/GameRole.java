package com.luoromeo.study.gof.memento;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年08月21日 17:50
 * @modified By
 */
public class GameRole {

    /** 生命力 */
    private int vit;

    /** 攻击力 */
    private int atk;

    /** 防御力 */
    private int def;

    //初始状态
    public void init() {
        setAtk(100);
        setDef(100);
        setVit(100);
    }

    //战斗
    public void fight() {
        setAtk(0);
        setDef(0);
        setVit(0);
    }

    public RoleStateMemento saveState() {
        return new RoleStateMemento(vit, atk, def);
    }

    public void recoveryState(RoleStateMemento memento) {
        this.vit = memento.getVit();
        this.atk = memento.getAtk();
        this.def = memento.getDef();
    }

    @Override
    public String toString() {
        return "GameRole{" +
                "vit=" + vit +
                ", atk=" + atk +
                ", def=" + def +
                '}';
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
