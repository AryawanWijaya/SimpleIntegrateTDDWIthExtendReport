package seleniumEasyPage;

import com.aventstack.extentreports.utils.FileUtil;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static String getScreenShot(WebDriver driver){
        TakesScreenshot ss = ((TakesScreenshot)driver);
        File src = ss.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"/target/ss/"+ System.currentTimeMillis() +".png";
        File destination = new File(path);
        try{
            FileUtils.copyFile(src, destination);
        }catch (IOException e){
            System.out.print("ScreenShot Gagal"+e.getStackTrace());
        }
        return path;
    }

}
