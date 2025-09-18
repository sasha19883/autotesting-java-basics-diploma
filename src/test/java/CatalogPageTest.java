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

public class CatalogPageTests {


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
    private By catalog_categoryButtonLocator = By.xpath("//*[text()='Телевизоры']");
    private By products_pageLocator = By.cssSelector(".entry-title.ak-container");
    @Test
    public void catalog_page_test()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElements(catalog_categoryButtonLocator).get(1).click();
        Assert.assertEquals("ТЕЛЕВИЗОРЫ", driver.findElement(products_pageLocator).getText());
    }
    private By search_ButtonLocator = By.cssSelector(".searchsubmit");
    private By product_name_Locator = By.cssSelector(".search-field");
    private By product_name_Result = By.cssSelector(".product_title.entry-title");
    @Test
    public void search_product_test()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        var name_Product = "Apple Watch 6";
        driver.findElement(product_name_Locator).sendKeys(name_Product);
        driver.findElement(search_ButtonLocator).click();
        Assert.assertEquals(name_Product, driver.findElement(product_name_Result).getText());
    }
    private By addto_trash_ButtonLocator = By.cssSelector(".post-15 .button");
    private By more_button_Locator = By.cssSelector(".post-15 .added_to_cart");
    private By product_trash_Result = By.cssSelector(".current");
    @Test
    public void catalog_product_addto_trash_test()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addto_trash_ButtonLocator).click();
        driver.findElement(more_button_Locator).click();
        Assert.assertEquals("Корзина", driver.findElement(product_trash_Result).getText());
    }

}