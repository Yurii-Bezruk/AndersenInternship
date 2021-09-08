package striker.studing.collections;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
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
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexTestIfIndexIsOutOfBounds() {
        List<Integer> list = new ArrayList<>();
        list.remove(0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexTestIfIndexLessThanZero() {
        List<Integer> list = new ArrayList<>();
        list.remove(-1);
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
    @Test
    public void containsAllTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        Assert.assertTrue(list.containsAll(col));
    }
    @Test
    public void containsAllTestIfNotContains() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        Assert.assertFalse(list.containsAll(col));
    }
    @Test
    public void addAllTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.addAll(col);
        Assert.assertTrue(list.containsAll(col));
    }
    @Test
    public void addAllTestWithIndex() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.add(-4);
        list.addAll(0, col);
        Assert.assertTrue(list.containsAll(col));
    }
    @Test
    public void getTest() {
        List<String> list = new ArrayList<>();
        String testString = "test";
        list.add(testString);
        Assert.assertEquals(testString, list.get(0));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void getTestIfIndexOutOfBounds() {
        List<String> list = new ArrayList<>();
        list.get(0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void getTestIfIndexLessThanZero() {
        List<String> list = new ArrayList<>();
        list.get(-1);
    }
    @Test
    public void setTest() {
        List<String> list = new ArrayList<>();
        String testString = "test";
        list.add(testString);
        String another = "t";
        list.set(0, another);
        Assert.assertEquals(another, list.get(0));
    }
    @Test
    public void addByIndexTest() {
        List<String> list = new ArrayList<>();
        String testString = "test";
        list.add(testString);
        String another = "t";
        list.add(0, another);
        Assert.assertEquals(another, list.get(0));
        Assert.assertEquals(testString, list.get(1));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void addTestIfIndexOutOfBounds() {
        List<String> list = new ArrayList<>();
        list.add(0, "another");
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexTestIfIndexLessThanZero() {
        List<String> list = new ArrayList<>();
        list.add(-1, "another");
    }
    @Test
    public void removeAllTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.addAll(col);
        Assert.assertTrue(list.removeAll(col));
        Assert.assertEquals(0, list.size());
    }
    @Test
    public void removeAllIfNotContainsTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        int expectedSize = list.size();
        Assert.assertFalse(list.removeAll(col));
        Assert.assertEquals(expectedSize, list.size());
    }
    @Test
    public void retainAllTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.addAll(col);
        list.add(3);
        Assert.assertTrue(list.retainAll(col));
        Assert.assertEquals(list.size(), col.size());
        Assert.assertTrue(list.containsAll(col));
    }
    @Test
    public void retainAllIfNotContainsTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        int expectedSize = list.size();
        Assert.assertFalse(list.retainAll(col));
        Assert.assertEquals(expectedSize, list.size());
    }
    @Test
    public void clearTest() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(6);
        list.add(3);
        int expectedSize = 0;
        list.clear();
        Assert.assertEquals(expectedSize, list.size());
    }
    @Test
    public void subListTest() {
        List<String> list = new ArrayList<>();
        String testSrt1 = "test1";
        String testSrt2 = "test2";
        String testSrt3 = "test3";
        list.add(testSrt1);
        list.add(testSrt2);
        list.add(testSrt3);
        List<String> subList = list.subList(0, 2);
        Assert.assertEquals(2, subList.size());
        Assert.assertEquals(testSrt1, subList.get(0));
        Assert.assertEquals(testSrt2, subList.get(1));
    }
    @Test
    public void iteratorTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int sum = 0;
        for (Integer elem : list) {
            sum += elem;
        }
        Assert.assertEquals(6, sum);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void iteratorTestNextIfCursorInTheEndOfList() {
        List<Integer> list = new ArrayList<>();
        list.iterator().next();
    }
    @Test
    public void iteratorRemoveTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        int sum = 0;
        for (Integer elem : list) {
            sum += elem;
        }
        Assert.assertEquals(5, sum);
    }
    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveWithoutNextTest() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterator = list.iterator();
        iterator.remove();
    }
    @Test
    public void listIteratorTestCreating() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(19);
        list.add(0);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()){
            sum += iterator.next();
        }
        Assert.assertEquals(18, sum);
    }
    @Test
    public void listIteratorTestCreatingWithGivenIndex() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(19);
        list.add(0);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator(1);
        while (iterator.hasNext()){
            sum += iterator.next();
        }
        Assert.assertEquals(17, sum);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorTestCreatingWithGivenIndexIfIndexOutOfBounds() {
        List<Integer> list = new ArrayList<>();
        ListIterator<Integer> iterator = list.listIterator(1);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorTestCreatingWithGivenIndexIfIndexLessThanZero() {
        List<Integer> list = new ArrayList<>();
        ListIterator<Integer> iterator = list.listIterator(-1);
    }
    @Test
    public void listIteratorTestReverseOrder() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        list.add(19);
        list.add(3);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator(3);
        while (iterator.hasPrevious()){
            sum += iterator.previous();
        }
        Assert.assertEquals(21, sum);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorTestReverseOrderIfInTheZeroPosition() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        ListIterator<Integer> iterator = list.listIterator();
        iterator.previous();
        iterator.previous();
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorTestReverseOrderIfInTheBeginningOfTheList() {
        List<Integer> list = new ArrayList<>();
        list.listIterator().previous();
    }
    @Test
    public void listIteratorTestNextIndex() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator();
        iterator.next();
        Assert.assertEquals(1, iterator.nextIndex());
    }
    @Test
    public void listIteratorTestPreviousIndex() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-2);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator(1);
        iterator.previous();
        Assert.assertEquals(0, iterator.previousIndex());
    }
    @Test
    public void listIteratorTestSet() {
        List<String> list = new ArrayList<>();
        list.add("test");
        ListIterator<String> iterator = list.listIterator();
        iterator.next();
        String expectedElem = "another";
        iterator.set(expectedElem);
        Assert.assertEquals(expectedElem, list.get(0));
    }
    @Test(expected = IllegalStateException.class)
    public void listIteratorTestSetWithoutNextOrPrevious() {
        List<String> list = new ArrayList<>();
        list.listIterator().set("");
    }
    @Test
    public void listIteratorTestAdd() {
        List<String> list = new ArrayList<>();
        list.add("test");
        ListIterator<String> iterator = list.listIterator();
        iterator.next();
        String expectedElem = "another";
        iterator.add(expectedElem);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(expectedElem, list.get(0));
    }
    @Test(expected = IllegalStateException.class)
    public void listIteratorTestAddWithoutNextOrPrevious() {
        List<String> list = new ArrayList<>();
        list.listIterator().add("");
    }
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        Assert.assertEquals(list, list.clone());
    }
    @Test
    @SuppressWarnings("unchecked")
    public void equalsTest() throws CloneNotSupportedException {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        ArrayList<String> emptyList = new ArrayList<>();
        ArrayList<String> anotherList = new ArrayList<>();
        emptyList.add("another");
        Assert.assertTrue(list.equals(list));
        Assert.assertFalse(list.equals(null));
        Assert.assertFalse(list.equals(new String()));
        Assert.assertFalse(list.equals(emptyList));
        Assert.assertFalse(list.equals(anotherList));
    }
    @Test
    public void trimToSizeTest() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.trimToSize();
        Assert.assertEquals(getListCapacity(list), list.size());
    }
    private int getListCapacity(List<?> list) throws NoSuchFieldException, IllegalAccessException {
        Field field = list.getClass().getDeclaredField("array");
        field.setAccessible(true);
        return ((Object[]) field.get(list)).length;
    }
}
