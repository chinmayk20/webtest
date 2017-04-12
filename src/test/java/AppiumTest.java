import io.appium.java_client.AppiumDriver;
import org.apache.xpath.SourceTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by ckulkarn on 19/03/2017.
 */
public class AppiumTest {

    WebDriver driver;
    //public AppiumDriver driver;

    int a = 4;
    int b = 0;

    @Before
    public void Init_appium(){

        System.out.println("Before");

        System.setProperty("webdriver.chrome.driver","/Users/ckulkarn/Documents/Asset/chromedriver");

        WebDriver driver = new ChromeDriver();


    }

    @After
    public void Tear_down(){

        System.out.println("TEar down");

        driver.quit();
    }

    @Test
    public void test_01(){

        driver.get("http://www.bbc.co.uk");
        System.out.println("Test 01");

        try{
            int c = a/b;
            System.out.println("add is " + c);
        }
        catch (ArithmeticException e){
            System.out.println("throws e" + e);
        }
    }
}
