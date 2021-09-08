package striker.studing.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LinkedListTest {
    @Test
    public void addTest(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        Assert.assertEquals(2, list.size());
    }
}
