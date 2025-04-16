package JavaTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến được khai báo ở bên ngoài hàm -> phạm vi là Class
    // Biến Global (toàn cục)
    // Có thể dùng cho tất cả các hàm trong 1 Class đó

    WebDriver driver;

    String homePageUrl; // Khai báo: Declare

    String fullName = "Automation FC";  // Khai báo + khởi tạo (Initial)

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_() {
        // Các biến khai báo ở trong 1 hàm/ block code -> phạm vi cục bộ (local)
        // Dùng trong cái hàm được khai báo/ block code được sinh ra
        String homePageUrl = "https://www.facebook.com/";

        // Trong 1 hàm nếu có 2 biến cùng tên (Global/ Local) sẽ ưu tiên lấy biến Local dùng
        // 1 biến local nếu như chưa gọi tới mà chưa được khởi tạo thì sẽ bị lỗi
        // Biến local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (compile code)
        driver.get(homePageUrl);

        // Nếu trong 1 hàm có 2 biến trùng tên (Global/ local) mà mình muốn lấy biến Global ra dùng
        // Từ khóa this
        // Biến Global chưa khởi tạo mà gọi ra dùng thì ko báo lỗi ở level (compile code)\
        // Level runtime sẽ lỗi
        driver.get(this.homePageUrl);
    }

    @Test
    public void TC_02_() {

    }

    @Test
    public void TC_03_() {

    }

    @Test
    public void TC_04_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
