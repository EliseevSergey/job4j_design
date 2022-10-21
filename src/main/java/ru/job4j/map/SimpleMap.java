package ru.job4j.map;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V> [] table = new MapEntry[capacity];


    public int size() {
        return count;
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = true;
        MapEntry<K, V> target = table[indexFor(hash(key.hashCode()))];
        if (target == null) {
                target = new MapEntry<>(key, value);
                modCount++;
                count++;
            } else {
                rsl = false;
            }
        return rsl;
    }

    private int hash(int hashCode) {
        int rsl = Integer.hashCode(hashCode);
        return rsl;
    }

    private  int indexFor(int hash) {
        int index = hash & (capacity - 1);
        System.out.println("hash: " + hash + ". индекс: " + index);
        return index;
    }

    private void expand() {
    }

    @Override
    public V get(K key) {
        V rsl;
        if (Objects.isNull(table[indexFor(hash(key.hashCode()))])) {
            rsl = null;
        } else {
            rsl = table[indexFor(hash(key.hashCode()))].getValue();
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int point = 0;
            @Override
            public boolean hasNext() {
                return count < point;
            }

            @Override
            public K next() {
                return table[point].getKey();
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapEntry<K, V> mapEntry = (MapEntry<K, V>) o;
            return Objects.equals(key, mapEntry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
