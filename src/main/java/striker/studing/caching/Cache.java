package striker.studing.caching;
import java.util.Map;
import java.util.function.Supplier;

public abstract class Cache<K, V> {
    protected final Supplier<V> valueSupplier;
    protected final int maxSize;

    public Cache(int maxSize) {
        this(maxSize, () -> null);
    }

    public Cache(int maxSize, Supplier<V> valueSupplier) {
        this.maxSize = maxSize;
        this.valueSupplier = valueSupplier;
    }

    public V get(K key){
        return valueSupplier.get();
    }

    public abstract V put(K key, V value);

    public abstract int size();

    public abstract V remove(K key);

    public abstract Map<K, V> snapShot();
}
