package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_Browser_Commands {
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
    public void TC_01_Url() {
        // Mở ra 1 URL bất kỳ
        driver.get("https://www.facebook.com/");

        // Nhiều hơn 1 thì nó sẽ đóng cái nó đang active
        driver.close(); // *

        // Đóng browser (ko care bao nhiêu tab/ window)
        driver.quit(); // **

        // 2 hàm này bị ảnh hưởng timeout của implicitWait
        // findElement/ findElements

        // Nó sẽ đi tìm vs loại By nào và trả về 1 element nếu như được tìm thấy
        // ko được tìm thấy: Fail tại step này - throw exception: NoSuchElement
        // Trả về 1 element - nhiều thì chỉ trả thằng đầu tiên
        WebElement emailAddressTextbox = driver.findElement(By.id("email")); // **

        // Nó sẽ đi tìm vs loại By nào và trả về nhiều element nếu như được tìm thấy (List WebElement)
        // ko được tìm thấy - ko bị fail - trả về 1 List rỗng (0 element)
        List<WebElement> checkboxs = driver.findElements(By.xpath("//input[@type='checkbox']")); // **

        // Tại sao lại cần lấy dữ liệu ra làm gì ?
        // Dùng để lấy ra Url của màn hình/ page hiện tại đang dùng
        // Home Page
        driver.getCurrentUrl(); // **

        // Lấy ra page sourse HTML/ CSS/ JS của page hiện tại
        // Verify 1 cách tương đối
        driver.getPageSource();
        driver.getPageSource().contains("The Apple and Google Play logos are trademarks of their respective owners");
        Assert.assertTrue( driver.getPageSource().contains("The Apple and Google Play logos are trademarks of their respective owners"));

        // Lấy ra title của page hiện tại
        driver.getTitle();

        // Lấy ra ID của cửa sổ/ tab hiện tại
        // Handle Window/ Tab
        driver.getWindowHandle(); // *
        driver.getWindowHandles(); // *

        // Cookies - Framwork
        driver.manage().getCookies(); // *

        // Get ra những log ở Dev Tool - Framwork
        driver.manage().logs().get(LogType.DRIVER); // *

        // Apply cho việc tìm element (findElement và findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // **

        // Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(0));

        // Set trước khi dùng vs thư viện JavascriptExecute
        // Inject 2 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getScriptTimeout();
        driver.manage().timeouts().getPageLoadTimeout();

        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize(); // **
        driver.manage().window().minimize();

        // Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));

        driver.manage().window().getSize();

        // Set cho browser ở vị trí nào so với độ phân giải màn hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().getPosition();

        // Điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác vs history của web page (back/ forward)
        driver.navigate().to("https://www.facebook.com/");

        // Alert/ Window (Tab)/  Frame (iFrame) // *
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // Lấy ra ID của cửa sổ/ tab hiện tại // *
        // Handle Window/ Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iframe) // *
        // Index/ ID (name)/ ELement
        driver.switchTo().frame(0);
        driver.switchTo().frame("686");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();
    }

}