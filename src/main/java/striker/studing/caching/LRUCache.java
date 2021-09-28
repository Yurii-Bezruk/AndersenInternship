package striker.studing.caching;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LRUCache<K, V> extends Cache<K, V>{
    private final HashMap<OldenedKey<K>, V> storage = new HashMap<>();
    private PriorityQueue<OldenedKey<K>> queue =
            new PriorityQueue<>(Comparator.comparing((OldenedKey<K> x) -> x.old).reversed());


    public LRUCache(int maxSize) {
        super(maxSize);
    }

    public LRUCache(int maxSize, Supplier<V> valueSupplier) {
        super(maxSize, valueSupplier);
    }

    public V get(K key){
        OldenedKey<K> oldenedKey = findKey(key);
        if (oldenedKey != null){
            increaseOld();
            oldenedKey.old = 0;
            refreshQueue();
            return storage.get(oldenedKey);
        }
        V value = super.get(key);
        oldenedKey = new OldenedKey<>(key);
        storage.put(oldenedKey, value);
        queue.add(oldenedKey);
        return value;
    }

    public V put(K key, V value){
        increaseOld();
        if (storage.size() == maxSize){
            remove(queue.poll());
        }
        OldenedKey<K> oldenedKey = findKey(key);
        oldenedKey = (oldenedKey == null ? new OldenedKey<>(key) : oldenedKey);
        queue.add(oldenedKey);
        return storage.put(oldenedKey, value);
    }

    public int size(){
        return storage.size();
    }

    public V remove(K key){
        return remove(new OldenedKey<>(key));
    }

    public V remove(OldenedKey<K> key){
        V value = storage.remove(key);
        refreshQueue();
        return value;
    }

    public Map<K, V> snapShot(){
        Map<K, V> snapShot = new HashMap<>();
        for (Map.Entry<OldenedKey<K>, V> entry : storage.entrySet()) {
            snapShot.put(entry.getKey().key, entry.getValue());
        }
        return snapShot;
    }

    private OldenedKey<K> findKey(K key){
        Optional<OldenedKey<K>> presentKey = storage.keySet().stream().filter(k -> k.key.equals(key)).findFirst();
        if (presentKey.isPresent()){
            OldenedKey<K> oldenedKey = presentKey.get();
            oldenedKey.old = 0;
            refreshQueue();
            return oldenedKey;
        }
        return null;
    }

    private void increaseOld(){
        for (Map.Entry<OldenedKey<K>, V> entry : storage.entrySet()) {
            OldenedKey<K> key = entry.getKey();
            key.old++;
        }
    }

    private void refreshQueue(){
        queue = new PriorityQueue<>(Comparator.comparing((OldenedKey<K> x) -> x.old).reversed());
        queue.addAll(storage.keySet());
    }

    @Override
    public String toString() {
        return "{" + storage.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().old))
                .map(Map.Entry::toString)
                .collect(Collectors.joining(", ")) + "}";
    }

    private static class OldenedKey<K> {
        K key;
        int old;

        public OldenedKey(K key) {
            this.key = key;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            return key.equals(((OldenedKey<K>)o).key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return "{key=" + key + ", old=" + old + '}';
        }
    }
}
