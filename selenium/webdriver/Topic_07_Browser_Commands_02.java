package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Browser_Commands_02 {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.id("email")).isDisplayed()){
            driver.findElement(By.id("email")).sendKeys("Automation Testing");
            System.out.print("Email textbox is displayed");
        }
        else {
            System.out.print("Email textbox is not displayed");
        }
        if (driver.findElement(By.id("edu")).isDisplayed()){
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
            System.out.print("Education textbox is displayed");
        }
        else {
            System.out.print("Education textbox is not displayed");
        }
        if (driver.findElement(By.id("under_18")).isDisplayed()){
            driver.findElement(By.id("under_18")).click();
            System.out.print("Under 18 checkbox is displayed");
        }
        else {
            System.out.print("Under 18 checkbox is not displayed");
        }
        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            System.out.println("Name User5 text is displayed");
        } else {
            System.out.println("Name User5 text is not displayed");
        }
    }

    @Test
    public void TC_02_isEnabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.id("email")).isEnabled()){
            System.out.print("Email textbox is enabled");
        }
        else {
            System.out.print("Email textbox is disable");
        }
        if (driver.findElement(By.id("edu")).isEnabled()){
            System.out.print("Education textbox is enabled");
        }
        else {
            System.out.print("Education textbox is disable");
        }
        if (driver.findElement(By.id("under_18")).isEnabled()){
            System.out.print("Under 18 checkbox is enabled");
        }
        else {
            System.out.print("Under 18 checkbox is disable");
        }
        if (driver.findElement(By.xpath("//label[text()='Password']//input")).isEnabled()){
            System.out.println("Password text is enabled");
        } else {
            System.out.println("Password User5 text is disable");
        }
    }

    @Test
    public void TC_03_isSelected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.xpath("//input[@value='java']")).click();
        if (driver.findElement(By.id("under_18")).isSelected()){
            System.out.print("Under 18 checkbox is selected");
        }
        else {
            System.out.print("Under 18 checkbox is not selected");
        }
        if (driver.findElement(By.xpath("//input[@value='java']")).isSelected()){
            System.out.print("java checkbox is selected");
        }
        else {
            System.out.print("java checkbox is not selected");
        }
        driver.findElement(By.xpath("//input[@value='java']")).click();
        if (driver.findElement(By.id("under_18")).isSelected()){
            System.out.print("Under 18 checkbox is selected");
        }
        else {
            System.out.print("Under 18 checkbox is not selected");
        }
        if (driver.findElement(By.xpath("//input[@value='java']")).isSelected()){
            System.out.print("java checkbox is selected");
        }
        else {
            System.out.print("java checkbox is not selected");
        }
    }

    @Test
    public void TC_04_Page_Navigation() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement(By.id("email")).sendKeys("minhhoang160499@gmail.com");
        WebElement input_password = driver.findElement(By.id("new_password"));

        // Case 1 - Number
        input_password.sendKeys("123");

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // Case 2 - Lowercase
        input_password.clear();
        input_password.sendKeys("abc");

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // Case 3 - Upercase
        input_password.clear();
        input_password.sendKeys("ABC");

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // Case 4 - Special Character
        input_password.clear();
        input_password.sendKeys("@@@");

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // Case 5 - 8-char
        input_password.clear();
        input_password.sendKeys("123456789");

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

        // Case 6 - Valid
        input_password.clear();
        input_password.sendKeys("Auto@1234!#");

        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='username-check completed']")).isDisplayed());

//        // Case 6 - Emty
//        input_password.clear();
//        driver.findElement(By.xpath("//button[@id='create-account-enabled']")).click();
//
//        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='username-check not-completed']")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }

}