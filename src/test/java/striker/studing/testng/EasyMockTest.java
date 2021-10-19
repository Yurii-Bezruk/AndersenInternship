package striker.studing.testng;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class EasyMockTest {

    @Test
    public void easyMockTest(){
        A a = EasyMock.mock(A.class);
        EasyMock.expect(a.m()).andReturn(-1);
        EasyMock.replay(a);
        Assert.assertEquals(-1, a.m());
        EasyMock.verify(a);
    }
    static class A {
        public int m(){
            System.out.println("test");
            return 0;
        }
    }
}
