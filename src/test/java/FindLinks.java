
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.xpath.SourceTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FindLinks {
    private WebDriver driver;
    private String baseUrl;
    public static WebElement element = null;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/Users/ckulkarn/Documents/Asset/chromedriver");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //baseUrl = "https://www.expedia.com/";
        baseUrl = "http://es.devserver.dev.dkids.mercury.dnitv.com:3000/";
        System.out.println("Before statement executed and commit from GIT");

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFindLinks() {
        driver.get(baseUrl);
        //driver.findElement(By.id("header-history")).click();
        //element = driver.findElement(By.id("tab-flight-tab"));
        //element.click();

        List<WebElement> linksList = clickableLinks(driver);
        for (WebElement link : linksList) {
            String href = link.getAttribute("href");
            try {
                System.out.println("URL " + href + " returned " + linkStatus(new URL(href)));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static List<WebElement> clickableLinks(WebDriver driver) {
        List<WebElement> linksToClick = new ArrayList<WebElement>();
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        elements.addAll(driver.findElements(By.tagName("img")));

        for (WebElement e : elements) {
            if (e.getAttribute("href") != null) {
                linksToClick.add(e);
            }
        }
        return linksToClick;
    }

    public static String linkStatus(URL url) {
        // http://download.java.net/jdk7/archive/b123/docs/api/java/net/HttpURLConnection.html#getResponseMessage%28%29
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.connect();
            String responseMessage = http.getResponseMessage();
            http.disconnect();
            return responseMessage;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }


    @Test
    public void getelements() throws IOException {

        driver.get(baseUrl);

        /* working bit returns Size list as 0
        List<WebElement> myElements = driver.findElements(By.xpath("some/path//a"));
        System.out.println("Size of List: "+myElements.size());
        for(WebElement e : myElements)
        {
            System.out.print("Text within the Anchor tab"+e.getText()+"\t");
            System.out.println("Anchor: "+e.getAttribute("href"));
        }
        */

        /*
        List<WebElement> myElements = driver.findElements(By.xpath("some/path//a"));
        System.out.println("Size of List: "+myElements.size());
        for(WebElement e : myElements)
        {
            System.out.print("Text within the Anchor tab"+e.getText()+"\t");
            System.out.println("Anchor: "+e.getAttribute("href"));
        }
        */

        /*  THIS WORKS PERFECT
        List<WebElement> list=driver.findElements(By.xpath("//*[@href or @src]"));

        for(WebElement e : list){
            String link = e.getAttribute("href");
            if(null==link)
                link=e.getAttribute("src");
            System.out.println(e.getTagName() + "=" + link);
        }
        */

            List<WebElement> links=driver.findElements(By.tagName("a"));
            for(WebElement ele:links)
                System.out.println(ele.getAttribute("href"));

    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
