package com.luoromeo.study.test.concurrent.sample.puzzles;

import java.util.Set;

/**
 * @description 表示搬箱子之类谜题的抽象类 P表示位置类 M表示移动类
 * @author zhanghua.luo
 * @date 2018年06月28日 09:53
 * @modified By
 */
public interface Puzzle<P, M> {

    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);


}
