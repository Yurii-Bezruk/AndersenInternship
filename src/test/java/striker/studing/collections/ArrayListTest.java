package striker.studing.collections;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListTest {
    @Test
    public void testCreationWithGivenCapacity() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 5;
        List<String> list = new ArrayList<>(expectedCapacity);
        int actualCapacity = getListCapacity(list);
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreationWithGivenCapacityIfCapacityLessThanZero() throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new ArrayList<>(-1);
    }
    @Test
    public void testCreationWithDefaultConstructor() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 10;
        List<String> list = new ArrayList<>();
        int actualCapacity = getListCapacity(list);
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }
    @Test
    public void testCreationFromOtherCollection() throws NoSuchFieldException, IllegalAccessException {
        String[] testArr = {"rtrt", "test", "string"};
        List<String> collection = Stream.of(testArr).collect(Collectors.toList());
        int expectedCapacity = testArr.length;
        List<String> list = new ArrayList<>(collection);
        int actualCapacity = getListCapacity(list);
        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertArrayEquals(testArr, list.toArray());
    }
    @Test
    public void sizeTest(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void isEmptyTest(){
        List<Integer> list = new ArrayList<>();
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        Assert.assertFalse(list.isEmpty());
    }
    @Test
    public void ensureCapacityTestIfEnoughSpace() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 5;
        ArrayList<Integer> list = new ArrayList<>(expectedCapacity);
        list.ensureCapacity(2);
        int actualCapacity = getListCapacity(list);
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }
    @Test
    public void ensureCapacityTestIfNotEnoughSpace() throws NoSuchFieldException, IllegalAccessException {
        int expectedCapacity = 1;
        ArrayList<Integer> list = new ArrayList<>(expectedCapacity);
        list.ensureCapacity(2);
        int actualCapacity = getListCapacity(list);
        Assert.assertNotEquals(expectedCapacity, actualCapacity);
    }
    @Test
    public void containsTest() throws NoSuchFieldException, IllegalAccessException {
        int testElem = 6;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(testElem);
        Assert.assertTrue(list.contains(testElem));
    }
    @Test
    public void notContainsTest() throws NoSuchFieldException, IllegalAccessException {
        int testElem = 6;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(testElem);
        Assert.assertFalse(list.contains(1));
    }
    @Test
    public void toArrayWithParameterTestIfEnoughSpace() {
        String[] testArr = {"rtrt", "test", "string"};
        List<String> collection = Stream.of(testArr).collect(Collectors.toList());
        List<String> list = new ArrayList<>(collection);
        String[] newArray = new String[5];
        Assert.assertSame(newArray, list.toArray(newArray));
    }
    @Test
    public void toArrayWithParameterTestIfNotEnoughSpace() {
        String[] testArr = {"rtrt", "test", "string"};
        List<String> collection = Stream.of(testArr).collect(Collectors.toList());
        List<String> list = new ArrayList<>(collection);
        String[] newArray = new String[2];
        Assert.assertNotSame(newArray, list.toArray(newArray));
    }
    @Test
    public void removeByIndexTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(-9);
        int startSize = list.size();
        list.remove(1);
        Assert.assertEquals(startSize - 1, list.size());
        Assert.assertFalse(list.contains(4));
    }
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void removeByIndexTestIfIndexIsOutOfBounds() {
        List<Integer> list = new ArrayList<>();
        list.remove(0);
    }
    @Test
    public void removeByObjectTest() {
        List<String> list = new ArrayList<>();
        String testString = "test";
        list.add(testString);
        int startSize = list.size();
        Assert.assertTrue(list.remove(testString));
        Assert.assertEquals(startSize - 1, list.size());
    }
    @Test
    public void removeByObjectTestIfNotContainsElement() {
        List<String> list = new ArrayList<>();
        String testString = "test";
        list.add(testString);
        int startSize = list.size();
        Assert.assertFalse(list.remove("another"));
        Assert.assertNotEquals(startSize - 1, list.size());
    }
    @Test
    public void indexOfTestIfContainsElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Assert.assertEquals(0, list.indexOf(1));
    }
    @Test
    public void indexOfTestIfNotContainsElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Assert.assertEquals(-1, list.indexOf(2));
    }
    @Test
    public void lastIndexOfTestIfContainsElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        Assert.assertEquals(2, list.lastIndexOf(1));
    }
    @Test
    public void lastIndexOfTestIfNotContainsElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        Assert.assertEquals(-1, list.lastIndexOf(3));
    }
    private int getListCapacity(List<?> list) throws NoSuchFieldException, IllegalAccessException {
        Field field = list.getClass().getDeclaredField("array");
        field.setAccessible(true);
        return ((Object[]) field.get(list)).length;
    }
}
