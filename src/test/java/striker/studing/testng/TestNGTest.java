package striker.studing.testng;

import org.testng.annotations.*;

public class TestNGTest {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }
    @BeforeGroups("group2")
    public void beforeGroups(){
        System.out.println("beforeGroups");
    }
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }
    @Test(groups = "group1")
    public void test1(){
        System.out.println("test1");
    }
    @Test(groups = "group1")
    public void test2(){
        System.out.println("test2");
    }
    @Test(groups = "group2", dataProvider = "data", invocationCount = 2)
    public void test3(String data){
        System.out.println("test3 "+data);
    }

    @DataProvider
    public Object[][] data(){
        return new Object[][]{{"hello"},{"hi"}};
    }
}
