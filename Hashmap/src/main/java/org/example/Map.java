package org.example;

public interface Map<K,V> {
    public V get(K k);
    public void put(K k, V v);
    public void remove(K k);
}
