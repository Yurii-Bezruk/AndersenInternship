package striker.studing.collections;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

public class HashMapTest {

    @Test
    public void defaultConstructorTest() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 16;
        float expectedLoadFactor = 0.75f;
        Map<Number, String> map = new HashMap<>();
        Field buckets = map.getClass().getDeclaredField("buckets");
        Field loadFactor = map.getClass().getDeclaredField("loadFactor");
        buckets.setAccessible(true);
        loadFactor.setAccessible(true);
        Assert.assertEquals(expectedCapacity, ((Object[]) buckets.get(map)).length);
        Assert.assertEquals(expectedLoadFactor, loadFactor.get(map));
    }
    @Test
    public void constructorWithInitialCapacityTest() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 32;
        float expectedLoadFactor = 0.75f;
        Map<Number, String> map = new HashMap<>(expectedCapacity);
        Field buckets = map.getClass().getDeclaredField("buckets");
        Field loadFactor = map.getClass().getDeclaredField("loadFactor");
        buckets.setAccessible(true);
        loadFactor.setAccessible(true);
        Assert.assertEquals(expectedCapacity, ((Object[]) buckets.get(map)).length);
        Assert.assertEquals(expectedLoadFactor, loadFactor.get(map));
    }
    @Test
    public void constructorWithInitialCapacityAndLoadFactorTest() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 32;
        float expectedLoadFactor = 0.75f;
        Map<Number, String> map = new HashMap<>(expectedCapacity, expectedLoadFactor);
        Field buckets = map.getClass().getDeclaredField("buckets");
        Field loadFactor = map.getClass().getDeclaredField("loadFactor");
        buckets.setAccessible(true);
        loadFactor.setAccessible(true);
        Assert.assertEquals(expectedCapacity, ((Object[]) buckets.get(map)).length);
        Assert.assertEquals(expectedLoadFactor, loadFactor.get(map));
    }
    @Test
    public void putTest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "two");
        Assert.assertEquals("two", map.put(2, "2"));
        Assert.assertEquals(2, map.size());
    }
    @Test
    public void putIntoBucketTest() {
        Map<Object, String> map = new HashMap<>();
        class Test {
            int equals;
            public Test(int equals) {
                this.equals = equals;
            }
            @Override
            public boolean equals(Object o) {
                return equals == ((Test) o).equals;
            }
            @Override
            public int hashCode() {
                return 1;
            }
        }
        map.put(new Test(1), "1");
        map.put(new Test(3), "3");
        map.put(new Test(2), "two");
        map.put(new Test(2), "two");
        map.put(new Test(3), "3");
        System.out.println(map);
        Assert.assertEquals(3, map.size());
    }
    @Test
    public void putNullTest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(null, "two");
        Assert.assertEquals("two", map.put(null, "2"));
        Assert.assertEquals(2, map.size());
    }
    @Test
    public void ensureCapacityTest() throws NoSuchFieldException, IllegalAccessException {
        Map<Integer, String> map = new HashMap<>(4);
        map.put(1, "1");
        map.put(2, "1");
        map.put(3, "1");
        map.put(4, "1");
        Field buckets = map.getClass().getDeclaredField("buckets");
        buckets.setAccessible(true);
        int capacity = ((Object[]) buckets.get(map)).length;
        Assert.assertTrue(capacity > 4);
    }
    @Test
    public void isEmptyTest() {
        Map<Integer, String> map = new HashMap<>(4);
        Assert.assertTrue(map.isEmpty());
        map.put(1, "1");
        Assert.assertFalse(map.isEmpty());
    }
    @Test
    @SuppressWarnings("unchecked")
    public void entryTest() {
        Map<String, String> map = new HashMap<>(4);
        map.put("1", "one");
        Map.Entry<String, String> entry = (Map.Entry<String, String>) map.entrySet().toArray()[0];
        Assert.assertEquals("1", entry.getKey());
        Assert.assertEquals("one", entry.getValue());
        Assert.assertEquals("one", entry.setValue("two"));
    }
    @Test
    public void containsKeyTest() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        Assert.assertTrue(map.containsKey("1"));
        Assert.assertFalse(map.containsKey("2"));
    }
    @Test
    public void containsValueTest() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        Assert.assertTrue(map.containsValue("one"));
        Assert.assertFalse(map.containsValue("two"));
    }
    @Test
    public void getTest() {
        Map<Object, String> map = new HashMap<>();
        class Test {
            int equals;
            public Test(int equals) {
                this.equals = equals;
            }
            @Override
            public boolean equals(Object o) {
                return equals == ((Test) o).equals;
            }
            @Override
            public int hashCode() {
                return 1;
            }
        }
        map.put(new Test(1), "1");
        map.put(new Test(2), "3");
        map.put(new Test(3), "3");
        Assert.assertNull(map.get(10));
        Assert.assertEquals("3", map.get(new Test(3)));
        Assert.assertNull(map.get(new Test(4)));
    }
}
