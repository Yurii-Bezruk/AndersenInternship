package striker.studing.testng;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MockitoTest {
    @Test
    public void mockitoTest(){

        final A a = Mockito.mock(A.class);
        Assert.assertFalse(a.m());
        Mockito.when(a.m()).thenAnswer(invocationOnMock -> {
            System.out.println("hi");
            return null;
        });
        a.m();
        Mockito.doAnswer(invocationOnMock -> {
            System.out.println("bye");
            return false;
        }).when(a).m2();
        a.m2();
        a.m2();
        Mockito.verify(a, Mockito.times(2)).m2();
    }
    static class A{
        public boolean m(){
            System.out.println("hello");
            return true;
        }
        public void m2(){}
    }
}
