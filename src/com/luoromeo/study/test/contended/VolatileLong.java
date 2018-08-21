package com.luoromeo.study.test.contended;

import sun.misc.Contended;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年06月19日 09:53
 * @modified By
 */
public class VolatileLong {

//    @Contended
    public volatile long value1 = 0L;

    @Contended
    public volatile long value2 = 0L;

    @Contended("group1")
    public volatile long value3 = 0L;

    @Contended("group1")
    public volatile long value4 = 0L;
}
