package striker.studing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
            testedArr[i] = random.nextInt();
        }
        sortedArr = Arrays.copyOf(testedArr, testedArr.length);
        Arrays.sort(sortedArr);
    }

    @Test
    public void bubbleSortAverageCaseTests(){
        System.out.println(testedArr.length);
        Assert.assertArrayEquals(Sortings.bubbleSort(testedArr), sortedArr);
    }
}
