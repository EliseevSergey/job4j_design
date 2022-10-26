package ru.job4j.map;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public int size() {
        return count;
    }

    public List<K> keyList() {
        return Arrays.stream(table)
                .filter(Objects::nonNull)
                .map(item -> item.getKey())
                .collect(Collectors.toList());
    }

    private int hash(int hashCode) {
        int before = hashCode;
        System.out.println("before " + before);
        int after = before >>> 7;
        System.out.println("after " + after);
        int rsl = before ^ after;
        System.out.println("rsl AFTER AFTER " + rsl);
        return rsl;
    }

    /*private int hash(int hashCode) {
        int rsl = Integer.hashCode(hashCode);
        return rsl;
    }
*/
    private  int indexFor(int hash) {
        int index = hash & (capacity - 1);
        //System.out.println("hash: " + hash + ". index: " + index);
        return index;
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

        public void setKey(K key){
            this.key = key;
        }

        public void setValue(V value){
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        /*@Override
        public int hashCode() {
            return Objects.hash(key, value);
        }*/

        /*@Override
        public int hashCode () {
            int rsl = key.hashCode();
            System.out.println(rsl);
            rsl = 31 * rsl + value.hashCode();
            return rsl;
        }*/
        @Override
        public int hashCode() {
            return key == null ? 0 : key.hashCode();
        }
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = true;
        MapEntry<K, V> in = new MapEntry<>(key, value);
        int keyHash = in.hashCode();
        int hashTable = hash(keyHash);
        int index = indexFor(hashTable);
            if (table[index] == null) {
                table[index] = in;
                modCount++;
                count++;
                System.out.println("PUT index " + index + ". PUT count " + count + ". keyHash " + keyHash + ". hastable " + hashTable);
            } else {
            rsl = false;
        }
        return rsl;
    }

    /*private int hash(int hashCode) {
        int rsl = Integer.hashCode(hashCode);
        System.out.println("hascode " + rsl);
        return rsl;
    }*/

    private void expand() {
    }

    @Override
    public V get(K key) {
        V rsl;
        int index;
        if (key == null) {
            index = 0;
        } else {
            index = indexFor(hash(key.hashCode()));
            System.out.println(index);
        }
        if (table[index] == null) {
            rsl = null;
        } else {
            rsl = table[index].getValue();
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = true;
        if (!keyList().contains(key)) {
                rsl = false;
        } else {
            int indexToDel = 0;
            if (key != null) {
                indexToDel = indexFor(hash(key.hashCode()));
            }
            table[indexToDel].setValue(null);
            table[indexToDel].setKey(null);
            table[indexToDel] = null;
            count--;
            modCount++;
            System.out.println("remove + count" + count);
        }
        return rsl;
    }

    /*public Set<K> keySet() {
        return Arrays.stream(table)
                .filter(Objects::nonNull)
                .map(item -> item.getKey())
                .collect(Collectors.toSet());
    }*/


    @Override
    public Iterator<K> iterator() {
        int expectedModCount = modCount;
        Iterator<K> itrKeyList = keyList().iterator();
        int size = keyList().size();
        return new Iterator<K>() {
            int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                    K rsl = keyList().get(point);
                    point++;
                return rsl;
            }
        };
    }



}
