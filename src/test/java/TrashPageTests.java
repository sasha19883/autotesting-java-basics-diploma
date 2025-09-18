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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TrashPageTests {

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
    private By addto_trash_Button1Locator = By.cssSelector(".post-15 .button");
    private By more_button1_Locator = By.cssSelector(".post-15 .added_to_cart");
    private By total_cost = By.cssSelector(".product-subtotal bdi");
    private By order_total = By.cssSelector(".order-total bdi");
    private By coupon_code = By.cssSelector("[id='coupon_code']");
    private By button_coupon = By.cssSelector(".coupon .button");
    private By discount_coupon = By.cssSelector(".coupon-sert500 th");
    private By remove_coupon = By.cssSelector(".woocommerce-remove-coupon");
    private By checkout_button = By.cssSelector(".checkout-button");
    private By message_coupon = By.cssSelector(".woocommerce-message");
    @Test
    public void trash_page_test()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addto_trash_Button1Locator).click();
        driver.findElement(more_button1_Locator).click();
        Assert.assertEquals(driver.findElement(total_cost).getText(), driver.findElement(order_total).getText());
        var coupon = "sert500";
        driver.findElement(coupon_code).sendKeys(coupon);
        driver.findElement(button_coupon).click();
        Assert.assertEquals("Купон успешно добавлен.", driver.findElement(message_coupon).getText());
        Assert.assertEquals("СКИДКА: SERT500", driver.findElement(discount_coupon).getText());
        Assert.assertEquals("34490,00₽", driver.findElement(order_total).getText());
        driver.findElement(remove_coupon).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(String.valueOf(discount_coupon))));
        Assert.assertEquals(driver.findElement(total_cost).getText(), driver.findElement(order_total).getText());
        driver.findElement(checkout_button).click();
    }
    private By addto_trash_Button2Locator = By.cssSelector(".post-15 .button");
    private By more_button2_Locator = By.cssSelector(".post-15 .added_to_cart");
    private By total_cost2 = By.cssSelector(".product-subtotal bdi");
    private By order_total2 = By.cssSelector(".order-total bdi");
    private By button2_coupon = By.cssSelector(".coupon .button");
    private By message2_coupon = By.cssSelector(".woocommerce-error li");
    @Test
    public void trash_page_test2()
    {
        driver.navigate().to("http://intershop5.skillbox.ru/product-category/catalog/");
        driver.findElement(addto_trash_Button2Locator).click();
        driver.findElement(more_button2_Locator).click();
        Assert.assertEquals(driver.findElement(total_cost2).getText(), driver.findElement(order_total2).getText());
        driver.findElement(button2_coupon).click();
        Assert.assertEquals("Пожалуйста, введите код купона.", driver.findElement(message2_coupon).getText());
    }

}