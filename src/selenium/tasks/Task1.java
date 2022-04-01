package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.*;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() {
//        TODO
//        enter a text instead of a number, check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        String textInsteadNumber = "one";
        inputField.clear();
        inputField.sendKeys(textInsteadNumber);
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        assertEquals("Please enter a number", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        String smallNumber = "1";
        inputField.clear();
        inputField.sendKeys(smallNumber);
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        assertEquals("Number is too small", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        WebElement inputField = driver.findElement(By.id("numb"));
        String bigNumber = "120";
        inputField.clear();
        inputField.sendKeys(bigNumber);
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        assertEquals("Number is too big", driver.findElement(By.id("ch1_error")).getText());
    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        WebElement inputField = driver.findElement(By.id("numb"));
        String number = "64";
        inputField.clear();
        inputField.sendKeys(number);
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 64 is 8.00", alert.getText());
        alert.accept();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());


    }

    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        WebElement inputField = driver.findElement(By.id("numb"));
        String number = "88";
        inputField.clear();
        inputField.sendKeys(number);
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Square root of 88 is 9.38", alert.getText());
        alert.accept();
        assertEquals("", driver.findElement(By.id("ch1_error")).getText());
    }
}
