package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Textbox_Textarea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void Login_01_Emty_Email_and_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']//following-sibling::div")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']//following-sibling::div")).getText(), "This is a required field.");
    }

    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123@123.123");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']//following-sibling::div")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hoang@email.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']//following-sibling::div")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_04_Incorrect_Password_and_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hoang@email.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12344567546");
        driver.findElement(By.id("send2")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
    }

    @Test
    public void Login_05_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        String uuid = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
        String user_name = "user" + uuid;
        String email = "user" + uuid + "@email.com";
        String password = "1234567";
        String new_email = getEmailAddress();
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(user_name);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(user_name);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + user_name + " " + user_name + "!");

        String contactInfo = driver.findElement(By.xpath("//div[@class='box-content']//p")).getText();
        Assert.assertTrue(contactInfo.contains(user_name + " " + user_name));
        Assert.assertTrue(contactInfo.contains(email));

        // Logout
        driver.findElement(By.cssSelector("a.skip-account")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();

        // Login
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        

        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);

        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome-msg strong")).getText(), "Hello, " + user_name + " " + user_name + "!");

        contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(user_name + " " + user_name));
        Assert.assertTrue(contactInfo.contains(email));

        // Verify Account
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();


        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), user_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), user_name);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), email);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "automation" + rand.nextInt(99999) + "@gmail.net";
    }
}