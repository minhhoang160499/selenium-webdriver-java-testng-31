package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String uuid = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
    String user_name = "user" + uuid;
    String email = "user" + uuid + "@email.com";
    String password = "1234567";
    String day = "7", month = "February", year = "1999";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.linkText("Register")).click();
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Select monthDropdown = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Select yearDropdown = new Select(driver.findElement(By.name("DateOfBirthYear")));

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(user_name);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(user_name);
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);

        // Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(dayDropdown.isMultiple());

        // Verify số lượng item trong Dropdown này là 32 item
        Assert.assertEquals(dayDropdown.getOptions().size(), 32);

        dayDropdown.selectByVisibleText(day);
        monthDropdown.selectByVisibleText(month);
        yearDropdown.selectByVisibleText(year);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
//        Actions action = new Actions(driver);
//        action.moveToElement(driver.findElement(By.xpath("//input[@id='register-button']"))).perform();
        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

//        driver.findElement(By.linkText("My Account")).click();
//        Assert.assertEquals(day.getFirstSelectedOption().toString(), "7");
//        Assert.assertEquals(month.getFirstSelectedOption().toString(), "February");
//        Assert.assertEquals(year.getFirstSelectedOption().toString(), "1999");
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");

        // Logout
        driver.findElement(By.cssSelector("a.ico-logout")).click();

        // Login
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        // Verify
        driver.findElement(By.className("ico-account")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), user_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), user_name);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}