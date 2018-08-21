package com.luoromeo.study.test.javassist;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年04月08日 09:59
 * @modified By
 */
public class CompoundKeyWeakHashMap<K, P, V> implements CompoundKeyMap<K, P, V> {

    private Map<KeyHolder<CompoundKey<K, P>>, V> map = new HashMap<>();


    @Override
    public V get(K key, P param) {
        key = Objects.requireNonNull(key, "key cannot be null");
        param = Objects.requireNonNull(param, "param cannot be null");

        return map.get(newKey(key, param));
    }

    private KeyHolder<CompoundKey<K, P>> newKey(K key, P param) {
        return new KeyHolder<CompoundKey<K, P>>(new CompoundKeyImpl<K, P>(key, param));
    }

    @Override
    public V get(K key, P param, V defValue) {
        key = Objects.requireNonNull(key, "key cannot be null");
        param = Objects.requireNonNull(param, "param cannot be null");

        V value = get(key, param);
        return value == null ? defValue : value;
    }

    @Override
    public V put(K key, P param, V value) {
        return map.put(newKey(key, param), value);
    }

    @Override
    public V putIfAbsent(K key, P param, V value) {
        return map.putIfAbsent(newKey(key, param), value);
    }


    @Override
    public Set<Map.Entry<CompoundKey<K, P>, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<CompoundKeyMap.CompoundKey<K, P>> keys() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    static class KeyHolder<T> extends WeakReference<T> {

        public KeyHolder(T referent) {
            super(referent);
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean equals(Object obj) {
            return get().equals(((KeyHolder<T>) obj).get());
        }

        @Override
        public int hashCode() {
            return get().hashCode();
        }
    }

    static class CompoundKeyImpl<K, P> implements CompoundKey<K, P> {

        private K key;

        private P param;

        CompoundKeyImpl(K key, P param) {
            super();
            this.key = key;
            this.param = param;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public P getParam() {
            return param;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((key == null) ? 0 : key.hashCode());
            result = prime * result + ((param == null) ? 0 : param.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            CompoundKeyImpl<?, ?> other = (CompoundKeyImpl<?, ?>) obj;
            if (key == null) {
                if (other.key != null) {
                    return false;
                }
            } else if (!key.equals(other.key)) {
                return false;
            }
            if (param == null) {
                return other.param == null;
            } else {
                return param.equals(other.param);
            }
        }


    }

}
