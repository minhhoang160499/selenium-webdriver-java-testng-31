package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import java.util.concurrent.TimeUnit;

public class Topic_07_Browser_Commands_01 {
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
    public void TC_01_Element() {
        driver.findElement(By.id("")).clear(); // *

        // Dùng để nhập dữ liệu vào các field bên trên
        driver.findElement(By.id("")).sendKeys(""); // **

        // Dùng để click lên element
        driver.findElement(By.id("")).click(); // **

        driver.findElement(By.xpath("//dkfjsdkfjd"));

        // Tìm từ node cha vào node con
        driver.findElement(By.id("form-validate")).findElement(By.id("first-name"));
        driver.findElement(By.id("form-validate")).findElement(By.cssSelector("input"));

        driver.findElement(By.cssSelector("form#form-validate input#firstname"));

        // Trả về 1 element khớp vs điều kiện
        WebElement fullNameTextbox = driver.findElement(By.id(""));

        // Trả về nhiều elements khớp vs điều kiện
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        // Dùng để verify 1 checkbox/ radio/ dropdown đã được chọn hay chưa
        Assert.assertTrue(driver.findElement(By.id("")).isSelected()); // *
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/ custom)
        Select select = new Select(driver.findElement(By.id("")));

        // Dùng để verify 1 element bất kì có hiển thị hay ko
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed()); // **
        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify 1 element có được thao tác lên hay không (không phải read-only)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled()); // *
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        // HTML Element
        // <input type="text" id="firstname" name="firstname" value="">
        driver.findElement(By.id("")).getAttribute("name"); // *
        driver.findElement(By.id("")).getAttribute("id");

        // Tab Accesibility/ Properties trong Element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURI");
        driver.findElement(By.id("")).getDomProperty("outerURI");

        // tab Styles trong Elements
        // Font/ Size/ Color/ Background
        driver.findElement(By.id("")).getCssValue("background-color"); // *
        // rgb(46, 138, 184)
        driver.findElement(By.id("")).getCssValue("font-size");
        driver.findElement(By.id("")).getCssValue("text-transform");

        // Vị trí của element so vs độ phân giải màn hình
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        // Chiều cao + rộng
        Dimension addressSize = driver.findElement(By.id("")).getSize();

        // Location + size
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();
        nameTextboxRect.getX();
        nameTextboxRect.getY();

        // Location
        Point namePoint = nameTextboxRect.getPoint();

        // Size
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        // Shadow Element (JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        // Từ id/ class/ name/ css/ xpath có thể truy ra ngược
        driver.findElement(By.cssSelector("#firstname")).getTagName(); //input
        driver.findElement(By.id("#firstname")).getTagName(); //input
        driver.findElement(By.className("form-instructions")).getTagName(); //p

        // Element A -> đầu ra của nó là tagname của element A
        String formListTag = driver.findElement(By.xpath("//*[@class='form-list']")).getTagName(); //ul
        // ul

        // Đầu vào của Element B sẽ nhận tagname của element A là tham số
        driver.findElement(By.xpath("//div[@class='remember-me-popup']/preceding-sibling::" + formListTag));

        driver.findElement(By.id("address.copyright")).getText(); // **
        //© 2015 Magento Demo Store. All Rights Reserved.

        // Chụp màn hình bị lỗi và lưu dưới dạng nào
        // BYTE
        // FILE (lưu trữ 1 hình có kích thước ở trong ổ cứng: .png/ .jpg...)
        // BASE64 (Hình dạng text)
        driver.findElement(By.id("address.copyright")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.id("address.copyright")).getScreenshotAs(OutputType.BASE64); // *
        driver.findElement(By.id("address.copyright")).getScreenshotAs(OutputType.BYTES);

        // Form (element nào là thẻ form hoặc nằm trong thẻ form)
        // Hành vi giống phím Enter
        // Register/ Login/ Search/..
        driver.findElement(By.id("")).submit();
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