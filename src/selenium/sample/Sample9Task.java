package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;
import selenium.pages.LoadingColor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
    static LoadingColor loadingColor;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
        loadingColor = PageFactory.initElements(driver, LoadingColor.class);
//         TODO:
//         * 1) click on start loading green button
        loadingColor.clickStartGreen();
//         * 2) check that button does not appear,
        assertFalse(loadingColor.isStartGreenDisplayed());
//         * but loading text is seen instead   "Loading green..."
        assertTrue(loadingColor.isLoadingGreenDisplayed());
        assertEquals("Loading green...", loadingColor.getLoadingGreenText());
//         * 3) check that both button and loading text is not seen,
//         * success is seen instead "Green Loaded"
        Thread.sleep(5000);
        assertFalse(loadingColor.isStartGreenDisplayed());
        assertFalse(loadingColor.isLoadingGreenDisplayed());
        assertTrue(loadingColor.isFinishGreenDisplayed());
        assertEquals("Green Loaded", loadingColor.getFinishGreenText());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:

//        We also can put this wait at the beginning
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
        assertFalse(startGreen.isDisplayed());

//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());

//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);
//         * 1) click on start loading green button
        WebElement startGreen = driver.findElement(By.id("start_green"));
        startGreen.click();
//         * 2) check that button does not appear,
        assertFalse(startGreen.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());
        assertEquals("Loading green...", loadingGreen.getText());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        WebElement finishGreen = driver.findElement(By.id("finish_green"));
        assertFalse(startGreen.isDisplayed());
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(finishGreen.isDisplayed());
        assertEquals("Green Loaded", finishGreen.getText());
    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }

}