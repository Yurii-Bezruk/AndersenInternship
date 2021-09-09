package striker.studing.collections;

import java.util.*;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] buckets;
    private int size;
    private int threshold; //предел заполненности, требующий увеличения
    private final float loadFactor; //предельный коэффициент загруженности
    private Set<Map.Entry<K, V>> entrySet;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    //TODO
//    public HashMap(Map<? extends K, ? extends V> map) {
//
//    }
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity, float loadFactor) {
        buckets = new Node[initialCapacity];
        this.loadFactor = loadFactor;
        refreshThreshold();
        entrySet = new HashSet<>();
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> buckets.length);
    }
    private void ensureCapacity(){
        if (size >= threshold){
            buckets = Arrays.copyOf(buckets, buckets.length * 2);
            refreshThreshold();
        }
    }
    private void refreshThreshold(){
        threshold = (int) (buckets.length * loadFactor);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public boolean containsKey(Object key) {
        return buckets[(buckets.length - 1) & hash(key)] != null;
    }
    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> entry : entrySet) {
            if(entry.getValue().equals(value))
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    private Node<K, V> findNode(Object key) {
        Node<K, V> bucket = buckets[(buckets.length - 1) & hash(key)];
        if(bucket == null){
            return null;
        }
        Node<K, V> cursor = bucket;
        while (cursor != null){
            if(cursor.key.equals(key)){
                return cursor;
            }
            cursor = cursor.next;
        }
        return null;
    }


    @Override
    public V put(K key, V value) {
        ensureCapacity();
        V previous = null;
        int hash = hash(key);
        int index = (buckets.length - 1) & hash;
        Node<K, V> newNode = new Node<>(hash, key, value);
        if(buckets[index] == null){
            buckets[index] = newNode;
            entrySet.add(newNode);
            size++;
        }
        else {
            previous = addToBucket(buckets[index], newNode);
        }
        return previous;
    }

    private V addToBucket(Node<K, V> cursor, Node<K, V> newNode) {
        V oldValue = null;
        boolean replaced = false;
        while (cursor.next != null){
            if(Objects.equals(cursor.key, newNode.key)){
                oldValue = replaceNode(cursor, newNode);
                replaced = true;
                break;
            }
            cursor = cursor.next;
        }
        if(! replaced){
            if(Objects.equals(cursor.key, newNode.key)){
                oldValue = replaceNode(cursor, newNode);
            }
            else {
                cursor.next = newNode;
                entrySet.add(newNode);
                size++;
            }
        }
        return oldValue;
    }
    private V replaceNode(Node<K, V> cursor, Node<K, V> newNode){
        entrySet.remove(cursor);
        V oldValue = cursor.value;
        cursor.value = newNode.value;
        entrySet.add(cursor);
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet;
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            Node<K, V> cursor = this;
            while (cursor != null){
                builder.append("[")
                        .append(cursor.key)
                        .append("(")
                        .append(cursor.hash)
                        .append(") - ")
                        .append(cursor.value)
                        .append("]");
                cursor = cursor.next;
            }
            return builder.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<K, V> bucket : buckets) {
            builder.append(bucket).append("\n");
        }
        return builder.toString();
    }
}
