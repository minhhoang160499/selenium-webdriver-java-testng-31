package JavaTest;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

import java.io.File;

public class Topic_03_System_Info {
    public static void main(String args[]) {

        // Lấy ra đường dẫn tương đối tại thư mục hiện tại
        String projectPath = System.getProperty("user.dir");

        String fileName = "tho.jpg";

        System.out.println(projectPath + "\\uploadFiles\\" + fileName);

        System.out.println(projectPath + "\\browserDrivers");

        String osName = System.getProperty("os.name");
        Keys keys;

        if (osName.startsWith("Windows"))
            keys = Keys.CONTROL;
        else
            keys = Keys.COMMAND;

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        // String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
        String character = File.separator;

        String hcmName = "tho.jpg";
        String hnName = "avatar.jpg";
        String dnName = "sua.jpg";

        String hcmFilePath = projectPath + character + "uploadFiles" + character + hcmName;
        String hnFilePath = projectPath + character + "uploadFiles" + character + hnName;
        String dnFilePath = projectPath + character + "uploadFiles" + character + dnName;

        System.out.println(hcmFilePath);
        System.out.println(hnFilePath);
        System.out.println(dnFilePath);
    }
}
