package striker.studing.caching;

import java.util.*;
import java.util.function.Supplier;

public class TwoQCache<K, V> extends Cache<K, V>{
    private final HashMap<K, V> storage = new HashMap<>();
    Queue<K> in;
    Queue<K> out;
    LRUCache<K, V> hot;


    public TwoQCache(int maxSize) {
        this(maxSize, () -> null);
    }

    public TwoQCache(int maxSize, Supplier<V> valueSupplier) {
        super(maxSize, valueSupplier);
        int inMaxSize = (int) (maxSize * 0.25);
        int outMaxSize = inMaxSize * 2;
        int hotMaxSize = maxSize - inMaxSize - outMaxSize;
        in = new InQueue(inMaxSize);
        out = new OutQueue(outMaxSize);
        hot = new LRUCache<>(hotMaxSize);
    }

    public V get(K key){
        if(storage.containsKey(key)){
            if(in.contains(key)){
                return storage.get(key);
            }
            if(out.contains(key)){
                out.remove(key);
                hot.put(key, storage.get(key));
            }
        }
        V value = super.get(key);
        storage.put(key, value);
        in.add(key);
        return value;
    }

    public V put(K key, V value){
        if(storage.containsKey(key)){
            hot.get(key);
            return storage.replace(key, value);
        }
        storage.put(key, value);
        in.add(key);
        return null;
    }

    public int size(){
        return storage.size();
    }

    public V remove(K key){
        if (! storage.containsKey(key))
            return null;
        V value = storage.remove(key);
        in.remove(key);
        out.remove(key);
        hot.remove(key);
        return value;
    }

    @Override
    public Map<K, V> snapShot() {
        return storage;
    }

    @Override
    public String toString() {
        return "in: " + in +
               "\nout: " + out +
               "\nhot: " + hot;
    }
    interface Queue<K> {
        void add(K key);
        boolean contains(K key);
        void remove(K key);
    }
    private class InQueue implements Queue<K>{
        private Deque<K> queue = new LinkedList<>();
        private final int maxSize;

        public InQueue(int maxSize) {
            this.maxSize = maxSize;
        }
        @Override
        public void add(K key){
            if(queue.size() == maxSize){
                out.add(queue.poll());
            }
            queue.add(key);
        }
        @Override
        public boolean contains(K key) {
            return queue.contains(key);
        }

        @Override
        public void remove(K key) {
            queue.remove(key);
        }

        @Override
        public String toString() {
            return queue.toString();
        }
    }
    private class OutQueue implements Queue<K> {
        private Deque<K> queue = new LinkedList<>();
        private final int maxSize;

        public OutQueue(int maxSize) {
            this.maxSize = maxSize;
        }
        @Override
        public void add(K key){
            if(queue.size() == maxSize){
                queue.poll();
                storage.remove(key);
            }
            queue.add(key);
        }
        @Override
        public boolean contains(K key) {
            return queue.contains(key);
        }
        @Override
        public void remove(K key) {
            queue.remove(key);
        }
        @Override
        public String toString() {
            return queue.toString();
        }
    }
}
