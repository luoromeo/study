package com.luoromeo.study.test.translations.api.design.core.filter;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月24日 13:51
 * @modified By
 */
public interface Specification<T> {
    Boolean test(T item);

}
