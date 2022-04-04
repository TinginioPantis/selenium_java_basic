package selenium.sample.extra;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;


public class extra2Task {
    WebDriver driver;
    String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
    String base_url = "https://kristinek.github.io/site/examples/po";

    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void runningOnFirefox() throws Exception {
        System.setProperty("webdriver.gecko.driver", libWithDriversLocation + "geckodriver");
        driver = new FirefoxDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
        driver.get(base_url);
//        check the background color of h1 element
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.tagName("h1")).getCssValue("background-color"));
    }

    @Test
    public void runningOnChrome() throws Exception {
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
//        TODO
//        go to page https://kristinek.github.io/site/examples/po
        driver.get(base_url);
//        check the background color of h1 element
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.tagName("h1")).getCssValue("background-color"));
    }

    @Test
    public void runningOnIE() throws Exception {
        System.setProperty("webdriver.ie.driver", libWithDriversLocation + "IEDriverServer.exe");
        driver = new InternetExplorerDriver();
//        I am not able to do this part as I am using Linux.
//        go to page https://kristinek.github.io/site/examples/po
        driver.get(base_url);
//        check the background color of h1 element
        assertEquals("rgba(0, 0, 0, 0)", driver.findElement(By.tagName("h1")).getCssValue("background-color"));
    }
}
