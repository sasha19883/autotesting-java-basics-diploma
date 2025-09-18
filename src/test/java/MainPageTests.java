import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class MainPageTests {


    private static WebDriver driver;
    private static WebDriverWait wait;
    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


    }
    @AfterClass
    public static void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
        driver.quit();
    }


    private By main_categoryButtonLocator = By.cssSelector("aside[id='accesspress_storemo-3'] .btn.promo-link-btn");
    private By tab_pageLocator = By.cssSelector(".entry-title.ak-container");

    @Test
    public void main_page_test()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(main_categoryButtonLocator).click();
        Assert.assertEquals("ПЛАНШЕТЫ", driver.findElement(tab_pageLocator).getText());
    }
    private By main_categoryButton2Locator = By.cssSelector("aside[id='accesspress_storemo-2'] .btn.promo-link-btn");
    private By tab_page2Locator = By.cssSelector(".entry-title.ak-container");




    @Test
    public void main_page_test2()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(main_categoryButton2Locator).click();
        Assert.assertEquals("КНИГИ", driver.findElement(tab_page2Locator).getText());
    }
    private By main_categoryButton3Locator = By.cssSelector("aside[id='accesspress_storemo-4'] .btn.promo-link-btn");
    private By tab_page3Locator = By.cssSelector(".entry-title.ak-container");




    @Test
    public void main_page_test3()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(main_categoryButton3Locator).click();
        Assert.assertEquals("ФОТО/ВИДЕО", driver.findElement(tab_page3Locator).getText());
    }

}