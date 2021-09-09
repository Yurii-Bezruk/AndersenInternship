package striker.studing.collections;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LinkedListTest {
    
    @Test
    public void testCreationWithDefaultConstructor() throws NoSuchFieldException, IllegalAccessException {
        List<String> list = new LinkedList<>();
        Assert.assertNotNull(list);
    }
    @Test
    public void testCreationFromOtherCollection() throws NoSuchFieldException, IllegalAccessException {
        String[] testArr = {"rtrt", "test", "string"};
        List<String> collection = Stream.of(testArr).collect(Collectors.toList());
        List<String> list = new LinkedList<>(collection);
        Assert.assertArrayEquals(testArr, list.toArray());
    }
    @Test
    public void sizeTest(){
        List<Integer> list = new LinkedList<>();
        list.add(1);
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void isEmptyTest(){
        List<Integer> list = new LinkedList<>();
        Assert.assertTrue(list.isEmpty());
        list.add(1);
        Assert.assertFalse(list.isEmpty());
    }
    @Test
    public void containsTest() {
        int testElem = 6;
        LinkedList<Integer> list = new LinkedList<>();
        list.add(testElem);
        Assert.assertTrue(list.contains(testElem));
    }
    @Test
    public void notContainsTest() {
        int testElem = 6;
        LinkedList<Integer> list = new LinkedList<>();
        list.add(testElem);
        Assert.assertFalse(list.contains(1));
    }
    @Test
    public void toArrayWithParameterTestIfEnoughSpace() {
        String[] testArr = {"rtrt", "test", "string"};
        List<String> collection = Stream.of(testArr).collect(Collectors.toList());
        List<String> list = new LinkedList<>(collection);
        String[] newArray = new String[5];
        Assert.assertSame(newArray, list.toArray(newArray));
    }
    @Test
    public void toArrayWithParameterTestIfNotEnoughSpace() {
        String[] testArr = {"rtrt", "test", "string"};
        List<String> collection = Stream.of(testArr).collect(Collectors.toList());
        List<String> list = new LinkedList<>(collection);
        String[] newArray = new String[2];
        Assert.assertNotSame(newArray, list.toArray(newArray));
    }
    @Test
    public void removeByIndexTest() {
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        list.remove(0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIndexTestIfIndexLessThanZero() {
        List<Integer> list = new LinkedList<>();
        list.remove(-1);
    }
    @Test
    public void removeByObjectTest() {
        List<String> list = new LinkedList<>();
        String testString = "test";
        list.add(testString);
        int startSize = list.size();
        Assert.assertTrue(list.remove(testString));
        Assert.assertEquals(startSize - 1, list.size());
    }
    @Test
    public void removeByObjectTestIfNotContainsElement() {
        List<String> list = new LinkedList<>();
        String testString = "test";
        list.add(testString);
        int startSize = list.size();
        Assert.assertFalse(list.remove("another"));
        Assert.assertNotEquals(startSize - 1, list.size());
    }
    @Test
    public void indexOfTestIfContainsElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        Assert.assertEquals(0, list.indexOf(1));
    }
    @Test
    public void indexOfTestIfNotContainsElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        Assert.assertEquals(-1, list.indexOf(2));
    }
    @Test
    public void lastIndexOfTestIfContainsElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        Assert.assertEquals(2, list.lastIndexOf(1));
    }
    @Test
    public void lastIndexOfTestIfNotContainsElement() {
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        list.addAll(col);
        Assert.assertTrue(list.containsAll(col));
    }
    @Test
    public void addAllTestWithIndex() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        LinkedList<Integer> list = new LinkedList<>();
        list.add(-4);
        list.add(-2);
        list.addAll(1, col);
        System.out.println(list.toString());
        Assert.assertTrue(list.containsAll(col));
    }
    @Test
    public void getTest() {
        List<String> list = new LinkedList<>();
        String testString = "test";
        list.add(testString);
        Assert.assertEquals(testString, list.get(0));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void getTestIfIndexOutOfBounds() {
        List<String> list = new LinkedList<>();
        list.get(0);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void getTestIfIndexLessThanZero() {
        List<String> list = new LinkedList<>();
        list.get(-1);
    }
    @Test
    public void setTest() {
        List<String> list = new LinkedList<>();
        String testString = "test";
        list.add(testString);
        String another = "t";
        list.set(0, another);
        Assert.assertEquals(another, list.get(0));
    }
    @Test
    public void addByIndexTest() {
        List<String> list = new LinkedList<>();
        String testString = "test";
        list.add(testString);
        String another = "t";
        list.add(0, another);
        Assert.assertEquals(another, list.get(0));
        Assert.assertEquals(testString, list.get(1));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void addTestIfIndexOutOfBounds() {
        List<String> list = new LinkedList<>();
        list.add(0, "another");
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIndexTestIfIndexLessThanZero() {
        List<String> list = new LinkedList<>();
        list.add(-1, "another");
    }
    @Test
    public void removeTest() {
        List<String> list = new LinkedList<>();
        list.add("hello");
        list.add("bye");
        Assert.assertEquals("bye", list.remove(1));
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void removeAllTest() {
        Collection<Integer> col = new HashSet<>();
        col.add(1);
        col.add(2);
        col.add(3);
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(6);
        list.add(7);
        int expectedSize = list.size();
        Assert.assertFalse(list.retainAll(col));
        Assert.assertEquals(expectedSize, list.size());
    }
    @Test
    public void clearTest() {
        List<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(6);
        list.add(3);
        int expectedSize = 0;
        list.clear();
        Assert.assertEquals(expectedSize, list.size());
    }
    @Test
    public void subListTest() {
        List<String> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        list.iterator().next();
    }
    @Test
    public void iteratorRemoveTest() {
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        iterator.remove();
    }
    @Test
    public void listIteratorTestCreating() {
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        ListIterator<Integer> iterator = list.listIterator(1);
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorTestCreatingWithGivenIndexIfIndexLessThanZero() {
        List<Integer> list = new LinkedList<>();
        ListIterator<Integer> iterator = list.listIterator(-1);
    }
    @Test
    public void listIteratorTestReverseOrder() {
        List<Integer> list = new LinkedList<>();
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
        List<Integer> list = new LinkedList<>();
        list.add(1);
        ListIterator<Integer> iterator = list.listIterator();
        iterator.previous();
        iterator.previous();
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void listIteratorTestReverseOrderIfInTheBeginningOfTheList() {
        List<Integer> list = new LinkedList<>();
        list.listIterator().previous();
    }
    @Test
    public void listIteratorTestNextIndex() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(-2);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator();
        iterator.next();
        Assert.assertEquals(1, iterator.nextIndex());
    }
    @Test
    public void listIteratorTestPreviousIndex() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(-2);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator(1);
        iterator.previous();
        Assert.assertEquals(0, iterator.previousIndex());
    }
    @Test
    public void listIteratorTestSet() {
        List<String> list = new LinkedList<>();
        list.add("test");
        ListIterator<String> iterator = list.listIterator();
        iterator.next();
        String expectedElem = "another";
        iterator.set(expectedElem);
        Assert.assertEquals(expectedElem, list.get(0));
    }
    @Test(expected = IllegalStateException.class)
    public void listIteratorTestSetWithoutNextOrPrevious() {
        List<String> list = new LinkedList<>();
        list.listIterator().set("");
    }
    @Test
    public void listIteratorTestAdd() {
        List<String> list = new LinkedList<>();
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
        List<String> list = new LinkedList<>();
        list.listIterator().add("");
    }
    @Test
    public void cloneTest() throws CloneNotSupportedException {
        LinkedList<String> list = new LinkedList<>();
        list.add("");
        Assert.assertEquals(list, list.clone());
    }
    @Test
    public void equalsTest() throws CloneNotSupportedException {
        LinkedList<String> list = new LinkedList<>();
        list.add("");
        list.add("3");
        LinkedList<String> emptyList = new LinkedList<>();
        LinkedList<String> anotherList = new LinkedList<>();
        emptyList.add("another");
        LinkedList<String> anotherTailList = new LinkedList<>();
        anotherTailList.add("");
        anotherTailList.add("7");
        LinkedList<String> cloneList = (LinkedList<String>) list.clone();
        Assert.assertTrue(list.equals(list));
        Assert.assertFalse(list.equals(null));
        Assert.assertFalse(list.equals(new String()));
        Assert.assertFalse(list.equals(emptyList));
        Assert.assertFalse(list.equals(anotherList));
        Assert.assertFalse(list.equals(anotherTailList));
        Assert.assertTrue(list.equals(cloneList));
    }
    @Test
    public void descendingIteratorTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        list.add("test2");
        list.add("test3");
        Iterator<String> iterator = list.descendingIterator();
        for (int i = list.size() - 1; i >= 0 && iterator.hasNext(); i--) {
            Assert.assertEquals(list.get(i), iterator.next());
        }
    }
    @Test
    public void descendingIteratorTestRemove() {
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        list.add("test2");
        list.add("test3");
        Iterator<String> iterator = list.descendingIterator();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(2, list.size());
        Assert.assertFalse(list.contains("test3"));
    }
    @Test
    public void addFirstTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addFirst("test");
        list.addFirst("test2");
        list.addFirst("test3");
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("test3", list.get(0));
    }
    @Test
    public void addLastTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        list.addLast("test3");
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("test3", list.get(2));
    }
    @Test
    public void offerFirstTest() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertTrue(list.offerFirst("test"));
        Assert.assertTrue(list.offerFirst("test2"));
        Assert.assertTrue(list.offerFirst("test3"));
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("test3", list.get(0));
    }
    @Test
    public void offerLastTest() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertTrue(list.offerLast("test"));
        Assert.assertTrue(list.offerLast("test2"));
        Assert.assertTrue(list.offerLast("test3"));
        Assert.assertEquals(3, list.size());
        Assert.assertEquals("test3", list.get(2));
    }
    @Test
    public void removeFirstTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        list.addLast("test3");
        Assert.assertEquals("test", list.removeFirst());
        Assert.assertEquals("test2", list.removeFirst());
        Assert.assertEquals("test3", list.removeFirst());
        Assert.assertEquals(0, list.size());
    }
    @Test
    public void removeLastTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        list.addLast("test3");
        Assert.assertEquals("test3", list.removeLast());
        Assert.assertEquals("test2", list.removeLast());
        Assert.assertEquals("test", list.removeLast());
        Assert.assertEquals(0, list.size());
    }
    @Test
    public void pollFirstTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        list.addLast("test3");
        Assert.assertEquals("test", list.pollFirst());
        Assert.assertEquals("test2", list.pollFirst());
        Assert.assertEquals("test3", list.pollFirst());
        Assert.assertNull(list.pollFirst());
        Assert.assertEquals(0, list.size());
    }
    @Test
    public void pollLastTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        list.addLast("test3");
        Assert.assertEquals("test3", list.pollLast());
        Assert.assertEquals("test2", list.pollLast());
        Assert.assertEquals("test", list.pollLast());
        Assert.assertNull(list.pollLast());
        Assert.assertEquals(0, list.size());
    }
    @Test
    public void getFirstTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        Assert.assertEquals("test", list.getFirst());
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void getLastTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        Assert.assertEquals("test2", list.getLast());
        Assert.assertEquals(2, list.size());
    }
    @Test
    public void getLastTestIfOneElem() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        Assert.assertEquals("test", list.getLast());
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void peekFirstTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        Assert.assertEquals("test", list.peekFirst());
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void peekFirstTestIfEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertNull(list.peekFirst());
    }
    @Test
    public void peekLastTest() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        list.addLast("test2");
        Assert.assertEquals("test2", list.peekLast());
        Assert.assertEquals(2, list.size());
    }
    @Test
    public void peekLastTestIfEmptyList() {
        LinkedList<String> list = new LinkedList<>();
        Assert.assertNull(list.peekLast());
    }
    @Test
    public void peekLastTestIfOneElem() {
        LinkedList<String> list = new LinkedList<>();
        list.addLast("test");
        Assert.assertEquals("test", list.peekLast());
        Assert.assertEquals(1, list.size());
    }
    @Test
    public void removeFirstOccurrenceTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        list.add("test2");
        list.add("test");
        Assert.assertTrue(list.removeFirstOccurrence("test"));
        Assert.assertNotEquals("test", list.get(0));
        Assert.assertNotEquals(3, list.size());
        Assert.assertTrue(list.contains("test"));
    }
    @Test
    public void removeLastOccurrenceTest() {
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        list.add("test2");
        list.add("test");
        Assert.assertTrue(list.removeLastOccurrence("test"));
        Assert.assertEquals("test", list.get(0));
        Assert.assertNotEquals(3, list.size());
        Assert.assertTrue(list.contains("test"));
    }
    @Test
    public void offerTest(){
        LinkedList<String> list = new LinkedList<>();
        Assert.assertTrue(list.offer("test"));
        Assert.assertTrue(list.contains("test"));
    }
    @Test
    public void removeWithoutArgsTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        Assert.assertEquals("test", list.remove());
        Assert.assertFalse(list.contains("test"));
    }
    @Test
    public void pollTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        Assert.assertEquals("test", list.poll());
        Assert.assertFalse(list.contains("test"));
    }
    @Test
    public void peekTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        Assert.assertEquals("test", list.peek());
        Assert.assertTrue(list.contains("test"));
    }
    @Test
    public void elementTest(){
        LinkedList<String> list = new LinkedList<>();
        list.add("test");
        Assert.assertEquals("test", list.element());
        Assert.assertTrue(list.contains("test"));
    }
    @Test
    public void pushAndPopTest(){
        LinkedList<String> list = new LinkedList<>();
        list.push("test");
        Assert.assertEquals("test", list.pop());
        Assert.assertFalse(list.contains("test"));
    }
}
