package striker.studing.caching;

import org.junit.Test;

public class TwoQCacheTest {
    @Test
    public void twoQTest(){
        TwoQCache<String, Integer> cache = new TwoQCache<>(5);
        cache.put("1", 1);
        cache.put("2", 2);
        cache.put("3", 3);
        cache.put("4", 4);
        System.out.println(cache);
    }

}
