package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
        WebElement nameField = driver.findElement(By.id("fb_name"));
        assertEquals("", nameField.getText());

        WebElement ageField = driver.findElement(By.id("fb_age"));
        assertEquals("", ageField.getText());

        WebElement commentField = driver.findElement(By.name("comment"));
        assertEquals("", commentField.getText());

        List<WebElement> checkBoxes = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
//         "Don't know" is selected in "Genre"
        WebElement radio1 = driver.findElements(By.cssSelector(".w3-radio")).get(0);
        WebElement radio2 = driver.findElements(By.cssSelector(".w3-radio")).get(1);
        WebElement radio3 = driver.findElements(By.cssSelector(".w3-radio")).get(2);
        assertFalse(radio1.isSelected());
        assertFalse(radio2.isSelected());
        assertFalse(radio3.isEnabled());

//         "Choose your option" in "How do you like us?"
        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropdownSelect = new Select(dropdown);
        List<WebElement> allSelections = dropdownSelect.getAllSelectedOptions();
        assertEquals("Choose your option", allSelections.get(0).getText());

//         check that the button send is blue with white letters
        WebElement button = driver.findElement(By.className("w3-blue"));
        assertEquals("rgba(33, 150, 243, 1)", button.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", button.getCssValue("color"));

    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        WebElement button = driver.findElement(By.className("w3-blue"));
        button.click();

//         check fields are empty or null
        WebElement nameText = driver.findElement(By.id("name"));
        assertEquals("", nameText.getText());

        WebElement ageText = driver.findElement(By.id("age"));
        assertEquals("", ageText.getText());

        WebElement languageText = driver.findElement(By.id("language"));
        assertEquals("", languageText.getText());

        WebElement genderText = driver.findElement(By.id("gender"));
        assertNull(genderText.getAttribute("value"));

        WebElement opinionText = driver.findElement(By.id("option"));
        assertNull(opinionText.getAttribute("value"));

        WebElement commentText = driver.findElement(By.id("comment"));
        assertEquals("", commentText.getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));

        WebElement noButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
        String nameToEnter = "Ana";
        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.sendKeys(nameToEnter);

        String ageToEnter = "25";
        WebElement ageField = driver.findElement(By.id("fb_age"));
        ageField.sendKeys(ageToEnter);

        WebElement englishCheckBox = driver.findElements(By.cssSelector(".w3-check[type='checkbox']")).get(0);
        englishCheckBox.click();

        WebElement radio2 = driver.findElements(By.cssSelector(".w3-radio")).get(1);
        radio2.click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Ok, i guess");

        String commentToEnter = "Everything is ok";
        WebElement commentField = driver.findElement(By.name("comment"));
        commentField.sendKeys(commentToEnter);

        WebElement button = driver.findElement(By.className("w3-blue"));
        button.click();

//         check fields are filled correctly
        WebElement nameText = driver.findElement(By.id("name"));
        assertEquals(nameToEnter, nameText.getText());

        WebElement ageText = driver.findElement(By.id("age"));
        assertEquals(ageToEnter, ageText.getText());

        WebElement languageText = driver.findElement(By.id("language"));
        assertEquals("English", languageText.getText());

        WebElement genderText = driver.findElement(By.id("gender"));
        assertEquals("female", genderText.getText());

        WebElement opinionText = driver.findElement(By.id("option"));
        assertEquals("Ok, i guess", opinionText.getText());

        WebElement commentText = driver.findElement(By.id("comment"));
        assertEquals(commentToEnter, commentText.getText());

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));

        WebElement noButton = driver.findElement(By.className("w3-red"));
        assertEquals("rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
        String nameToEnter = "Ana";
        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.sendKeys(nameToEnter);

//         click "Send"
        WebElement button = driver.findElement(By.className("w3-blue"));
        button.click();

//         click "Yes"
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        yesButton.click();

//         check message text: "Thank you, NAME, for your feedback!"
        WebElement message = driver.findElement(By.id("message"));
        assertEquals("Thank you, " + nameToEnter + ", for your feedback!", message.getText());

//         color of text is white with green on the background
        WebElement messageArea = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", messageArea.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messageArea.getCssValue("color"));

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        WebElement button = driver.findElement(By.className("w3-blue"));
        button.click();

//         click "Yes"
        WebElement yesButton = driver.findElement(By.className("w3-green"));
        yesButton.click();

//         check message text: "Thank you for your feedback!"
        String expectedMessage = "Thank you for your feedback!";
        WebElement message = driver.findElement(By.id("message"));
        assertEquals(expectedMessage, message.getText());

//         color of text is white with green on the background
        WebElement messageArea = driver.findElement(By.className("w3-green"));
        assertEquals("rgba(76, 175, 80, 1)", messageArea.getCssValue("background-color"));
        assertEquals("rgba(255, 255, 255, 1)", messageArea.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
        String nameToEnter = "Ana";
        WebElement nameField = driver.findElement(By.id("fb_name"));
        nameField.sendKeys(nameToEnter);

        String ageToEnter = "25";
        WebElement ageField = driver.findElement(By.id("fb_age"));
        ageField.sendKeys(ageToEnter);

        WebElement englishCheckBox = driver.findElements(By.cssSelector(".w3-check[type='checkbox']")).get(0);
        englishCheckBox.click();

        WebElement radio2 = driver.findElements(By.cssSelector(".w3-radio")).get(1);
        radio2.click();

        WebElement dropdown = driver.findElement(By.id("like_us"));
        Select dropdownSelect = new Select(dropdown);
        dropdownSelect.selectByVisibleText("Ok, i guess");

        String commentToEnter = "Everything is ok";
        WebElement commentField = driver.findElement(By.name("comment"));
        commentField.sendKeys(commentToEnter);

//         click "Send"
        WebElement button = driver.findElement(By.className("w3-blue"));
        button.click();

//         click "No"
        WebElement noButton = driver.findElement(By.className("w3-red"));
        noButton.click();

//         check fields are filled correctly
        assertEquals(nameToEnter, driver.findElement(By.id("fb_name")).getAttribute("value"));
        assertEquals(ageToEnter, driver.findElement(By.id("fb_age")).getAttribute("value"));

        assertTrue(driver.findElements(By.cssSelector(".w3-check[type='checkbox']")).get(0).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='checkbox']")).get(1).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='checkbox']")).get(2).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-check[type='checkbox']")).get(3).isSelected());

        assertFalse(driver.findElements(By.cssSelector(".w3-radio")).get(0).isSelected());
        assertTrue(driver.findElements(By.cssSelector(".w3-radio")).get(1).isSelected());
        assertFalse(driver.findElements(By.cssSelector(".w3-radio")).get(2).isSelected());

        assertEquals(commentToEnter, driver.findElement(By.name("comment")).getAttribute("value"));

        assertEquals("Ok, i guess", driver.findElement(By.id("like_us")).getAttribute("value"));
    }
}
