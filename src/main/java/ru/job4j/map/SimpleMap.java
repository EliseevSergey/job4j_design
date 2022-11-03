package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] tbl = new MapEntry[capacity];

    @Override
    public int size() {
        return count;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (this.capacity - 1);
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

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key);
        }
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = true;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (tbl[index] == null) {
            tbl[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            if (count * 100 / capacity >= LOAD_FACTOR * 100) {
                expand();
            }
        } else {
            rsl = false;
        }
        return rsl;
    }

    private void expand() {
        SimpleMap<K, V> sizeUp = new SimpleMap<>();
        sizeUp.tbl = new MapEntry[capacity * 2];
        sizeUp.capacity = capacity * 2;
        for (MapEntry<K, V> cell : tbl) {
            if (cell != null) {
                sizeUp.put(cell.getKey(), cell.getValue());
            }
        }
        tbl = sizeUp.tbl;
        capacity = sizeUp.capacity;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (tbl[index] != null && Objects.equals(key, tbl[index].getKey())) {
            rsl = tbl[index].getValue();
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (tbl[index] != null && Objects.equals(key, tbl[index].getKey())) {
            tbl[index].setValue(null);
            tbl[index].setKey(null);
            tbl[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (point < tbl.length) {
                    while (tbl[point] == null) {
                    point++;
                    if (point == tbl.length) {
                        break;
                        }
                    }
                }
                return point < tbl.length;
                }

                @Override
                public K next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    K rsl = tbl[point].getKey();
                    point++;
                    return rsl;
            }
        };
    }
}
