package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_Css {
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
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register_Emty_Data() {

        //Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Register_With_Invalid_Data() {

        //Action
        driver.findElement(By.id("txtFirstname")).sendKeys("ABC");
        driver.findElement(By.id("txtEmail")).sendKeys("123@123@123");
        driver.findElement(By.id("txtCEmail")).sendKeys("123@123@123");
        driver.findElement(By.id("txtPassword")).sendKeys("123456@");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456@");
        driver.findElement(By.id("txtPhone")).sendKeys("0913232342");
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Register_With_Incorrect_Confirm_Email() {

        //Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("ABC");
        driver.findElement(By.id("txtEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtCEmail")).sendKeys("email@email.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123456@");
        driver.findElement(By.id("txtCPassword")).sendKeys("123456@");
        driver.findElement(By.id("txtPhone")).sendKeys("0913232342");
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Register_With_Password_Less_Than_6_Keyword() {

        //Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("ABC");
        driver.findElement(By.id("txtEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtCEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtPassword")).sendKeys("123");
        driver.findElement(By.id("txtCPassword")).sendKeys("123");
        driver.findElement(By.id("txtPhone")).sendKeys("0913232342");
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_With_ConfirmPassword_Is_Incorrect() {

        //Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();

        driver.findElement(By.id("txtFirstname")).sendKeys("ABC");
        driver.findElement(By.id("txtEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtCEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtPassword")).sendKeys("123456");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("0913232342");
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();
        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Register_With_Phone_Is_Incorrect() {

        //Action
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        
        driver.findElement(By.id("txtFirstname")).sendKeys("ABC");
        driver.findElement(By.id("txtEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtCEmail")).sendKeys("email@email.net");
        driver.findElement(By.id("txtPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
        driver.findElement(By.id("txtPhone")).sendKeys("123");
        driver.findElement(By.xpath("//button[@type='submit' and text()='ĐĂNG KÝ']")).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}