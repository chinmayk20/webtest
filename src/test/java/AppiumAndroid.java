import java.io.File;

import java.net.MalformedURLException;

import java.net.URL;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;


public class AppiumAndroid {

    //protected static AndroidDriver driver;
    WebDriver driver;

    @Before
    public void init_appium() throws MalformedURLException {

        try {
        File classpathRoot = new File(System.getProperty("user.dir"));
        System.out.println("USer dir pathh found" + classpathRoot.getAbsolutePath());
        String apkpath = "selendroid.apk";
        File app = new File(classpathRoot, apkpath);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus 5");
        capabilities.setCapability("platformVersion", "5.0");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "io.selendroid.testapp");
        capabilities.setCapability("appActivity", "HomeScreenActivity");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

    } catch (Exception e){

        }
    }

    @After
     public void Tear_down(){

        System.out.println("MVN build job is completed");

        driver.quit();

    }

    @Test
    public void test_01() {

        System.out.println("Test 01");

        driver.findElement(By.id("my_text_field")).sendKeys("Ishaan is a mad boy and he is silly");

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        //driver.findElement(By.id("waitingButtonTest")).click();
    }


}