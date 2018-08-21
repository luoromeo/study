package com.luoromeo.study.test.javassist;

import java.util.Collection;
import java.util.Set;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月08日 10:05
 * @modified By
 */
public interface CompoundKeyMap<K, P, V> {

    V get(K key, P param);

    V get(K key, P param, V defValue);

    V put(K key, P param, V value);

    V putIfAbsent(K key, P param, V value);

    Set<java.util.Map.Entry<CompoundKey<K, P>, V>> entrySet();

    Set<CompoundKey<K, P>> keys();

    Collection<V> values();

    int size();

    boolean isEmpty();

    public interface CompoundKey<K, P> {
        K getKey();

        P getParam();
    }
}
