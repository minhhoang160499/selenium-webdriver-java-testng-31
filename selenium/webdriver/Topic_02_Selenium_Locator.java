package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
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
        driver.get("https://demo.nopcommerce.com/register");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Keane");
    }

    @Test
    public void TC_02_Class_Name() {
       driver.findElement(By.className("title"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("FirstName"));
    }

    @Test
    public void TC_04_Tag_Name() {
        driver.findElement(By.tagName("input"));
    }

    @Test
    public void TC_05_Link_Text() {
        driver.findElement(By.linkText("Register"));
    }

    @Test
    public void TC_06_Partial_Link_Text() {
        driver.findElement(By.partialLinkText("Register"));
    }

    @Test
    public void TC_07_Css() {
        driver.findElement(By.cssSelector("input[name='FirstName']"));
    }

    @Test
    public void TC_08_Xpath() {
        driver.findElement(By.xpath("//input[@name='FirstName']"));
    }
}