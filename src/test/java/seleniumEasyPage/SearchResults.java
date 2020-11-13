package seleniumEasyPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResults {
    WebDriver driver;

    public SearchResults(WebDriver driver) {
        this.driver = driver;
    }

    public String getText(){
        String actual = driver.findElement(By.xpath("//div[@class='mw-parser-output']")).getText();
        return  actual;
    }


}
