package striker.studing.sortings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import striker.studing.caching.TwoQCacheTest;
import striker.studing.sortings.Sortings;

import java.util.Arrays;
import java.util.Random;

public class SortingsTest {
    private Integer[] testedArr;
    private Integer[] sortedArr;

    @Before
    public void generateTestedArr(){
        Random random = new Random(System.currentTimeMillis());
        testedArr = new Integer[random.nextInt(90) + 10];
        for (int i = 0; i < testedArr.length; i++) {
            testedArr[i] = random.nextInt(1000);
        }
        sortedArr = Arrays.copyOf(testedArr, testedArr.length);
        Arrays.sort(sortedArr);
    }
    @Test
    public void bubbleSortTest(){
        Assert.assertArrayEquals(Sortings.bubbleSort(testedArr), sortedArr);
    }
    @Test
    public void bubbleSortTestEmptyArray(){
        Integer[] arr = new Integer[0];
        Assert.assertArrayEquals(Sortings.bubbleSort(arr), arr);
    }
    @Test
    public void selectionSortTest(){
        Assert.assertArrayEquals(Sortings.selectionSort(testedArr), sortedArr);
    }
    @Test
    public void selectionSortTestEmptyArray(){
        Integer[] arr = new Integer[0];
        Assert.assertArrayEquals(Sortings.selectionSort(arr), arr);
    }
    @Test
    public void insertionSortTest(){
        Assert.assertArrayEquals(Sortings.insertionSort(testedArr), sortedArr);
    }
    @Test
    public void insertionSortTestEmptyArray(){
        Integer[] arr = new Integer[0];
        Assert.assertArrayEquals(Sortings.insertionSort(arr), arr);
    }
    @Test
    public void mergingSortTest(){
        Assert.assertArrayEquals(Sortings.mergingSort(testedArr), sortedArr);
    }
    @Test
    public void mergingSortTestEmptyArray(){
        Integer[] arr = new Integer[0];
        Assert.assertArrayEquals(Sortings.mergingSort(arr), arr);
    }
    @Test
    public void quickSortTest(){
        Assert.assertArrayEquals(Sortings.quickSort(testedArr), sortedArr);
    }
    @Test
    public void quickSortTestEmptyArray(){
        Integer[] arr = new Integer[0];
        Assert.assertArrayEquals(Sortings.quickSort(arr), arr);
    }
}
