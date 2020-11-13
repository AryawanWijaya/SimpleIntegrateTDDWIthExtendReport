package futureProgram;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import seleniumEasyPage.HomePage;
import seleniumEasyPage.SearchResults;
import seleniumEasyPage.Utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver driver;
    ExtentHtmlReporter reporter ;
    ExtentReports extent;

    @Before
    public void initiate() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver();
        reporter =  new ExtentHtmlReporter(System.getProperty("user.dir")+"/target/report.html");
        extent=  new ExtentReports();
        reporter.setAppendExisting(true); // kalau sdh ada file reportnya maka dia akan ditambahkan di file itu
        extent.attachReporter(reporter);
        extent.setReportUsesManualConfiguration(false);
    }

    @After
    public void end() {
        extent.flush();
        driver.close();
    }

    /**
     * Rigorous Test :-)
     */
    String[] buah = new String[]{"apple", "banana", "kiwi", "melon"};
    String[] expected = new String[]
            {
                    "From Middle English appel, from Old English æppel (“apple, any kind of fruit, fruit in general, apple of the eye, ball, anything round, bolus, pill”), from Proto-Germanic *aplaz (“apple”) (compare Scots aipple, West Frisian apel, Dutch appel, German Apfel, Swedish äpple, Danish æble), from Proto-Indo-European *h₂ébōl, *h₂ébl̥ (“apple”) (compare Welsh afal, Irish úll, Lithuanian óbuolỹs, Russian я́блоко (jábloko), possibly Ancient Greek ἄμπελος (ámpelos, “vine”)).[1][2]",
                    "From Wolof banaana, via Spanish or Portuguese.",
                    "Borrowed from Maori kiwi.",
                    "Borrowed from Old French melon, from Late Latin melonem, from Latin melopeponem (“type of pumpkin”), from Ancient Greek μηλοπέπων (mēlopépōn), from μῆλον (mêlon, “apple”) + πέπων (pépōn, “ripe”)."
            };
    @Test
    public void testWikitionaryApple() throws IOException {
        Utility util = new Utility();
        ExtentTest test = extent.createTest("Test1");

        FileReader reader = new FileReader(System.getProperty("user.dir")+"/target/coba.properties");
        Properties p = new Properties();
        p.load(reader);

        HomePage homePage = new HomePage(driver);
        SearchResults search = new SearchResults(driver);
        homePage.openHomePage();
        String temp = util.getScreenShot(driver);

        test.log(Status.PASS, "Berhasil buka wikitionary");
        test.pass("Berhasil", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        homePage.insertWord(p.getProperty("buah1"));
//        assertThat("Data tidak cocok",search.getText().toLowerCase(), containsString(expected[0].toLowerCase()));
        temp = util.getScreenShot(driver);
        if (search.getText().toLowerCase().contains(expected[0].toLowerCase())) {
            test.pass("search berhasil", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        } else {
            test.fail("search Tidak berhasil", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
    }
        //equal bisa diganti containString, tapi harus di lowercase smua aja
        @Test
        public void testWikirionaryKiwi() throws IOException {
        Utility util = new Utility();
        HomePage homePage = new HomePage(driver);
        SearchResults search = new SearchResults(driver);
        ExtentTest test =extent.createTest("Test2");

        homePage.openHomePage();
        homePage.insertWord(buah[2]);
        String temp;
        temp=util.getScreenShot(driver);
        if(search.getText().toLowerCase().contains(expected[2].toLowerCase())){
            test.pass("Berhasil mencari Kiwi",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        else{
            test.fail("Tidak berhasil mencari kiwi",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }

//        assertThat("Tidak sesuai hasilnya", search.getText().toLowerCase(), containsString(expected[2].toLowerCase()));
        //equal bisa diganti containString, tapi harus di lowercase smua aja
    }
    @Test
    public void testWikitionaryMelon() {
        HomePage homePage = new HomePage(driver);
        SearchResults search = new SearchResults(driver);
        homePage.openHomePage();
        homePage.insertWord(buah[3]);
        assertThat("Tidak sesuai hasilnya", search.getText().toLowerCase(), containsString(expected[3].toLowerCase()));
        //equal bisa diganti containString, tapi harus di lowercase smua aja
    }
    @Test
    public void testWikitionaryBanana() {
        HomePage homePage = new HomePage(driver);
        SearchResults search = new SearchResults(driver);
        homePage.openHomePage();
        homePage.insertWord(buah[1]);
        assertThat("Tidak sesuai hasilnya", search.getText().toLowerCase(), containsString(expected[1].toLowerCase()));
        //equal bisa diganti containString, tapi harus di lowercase smua aja
    }
}



