import org.testng.annotations.*;

/**
 * Created by hutao on 16/5/5.
 * 下午3:51
 */
public class A {
    @BeforeGroups("shopping")
    public void beforeGroups() {
        System.out.println("@BeforeGroups");
    }

    @AfterGroups("shopping")
    public void afterGroups() {
        System.out.println("@AfterGroups");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("@BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("@AfterClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("@AfterMethod");
    }

    @Test(groups = "shopping")
    public void runTest1() {
        System.out.println("@Test - runTest1");
    }

    @Test(invocationCount = 100, threadPoolSize = 5)
    public void runTest2() {
        System.out.println("@Test - runTest2");
    }
}
