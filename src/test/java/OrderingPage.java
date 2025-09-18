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

public class OrderingPage {
    private static WebDriver driver;
    private static WebDriverWait wait;
    @BeforeClass
    public static void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


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
    private By checkout_button = By.cssSelector(".checkout-button");
    private By message_coupon = By.cssSelector(".woocommerce-message");
    private By order_register_username = By.cssSelector("[id='username']");
    private By order_register_password = By.cssSelector("[id='password']");
    private By order_login_button = By.cssSelector(".showlogin");
    private By login_button = By.cssSelector(".woocommerce-button");
    private By order_first_name = By.cssSelector("[id='billing_first_name']");
    private By order_last_name = By.cssSelector("[id='billing_last_name']");
    private By order_address = By.cssSelector("[id='billing_address_1']");
    private By order_city = By.cssSelector("[id='billing_city']");
    private By order_state = By.cssSelector("[id='billing_state']");
    private By order_postcode = By.cssSelector("[id='billing_postcode']");
    private By order_phone = By.cssSelector("[id='billing_phone']");
    private By order_payment = By.cssSelector("li.payment_method_cod");
    private By order_button = By.cssSelector("[id='place_order']");
    private By order_Page = By.cssSelector("div.woocommerce");
    private By order_recieved = By.cssSelector(".post-title");
    @Test
    public void order_page_test() {
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
        driver.findElement(checkout_button).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(order_login_button));
        driver.findElement(order_login_button).click();
        var username = "Marwin";
        var password = "951753";
        driver.findElement(order_register_username).sendKeys(username);
        driver.findElement(order_register_password).sendKeys(password);
        driver.findElement(login_button).click();
        var firstname = "Николай";
        var lastname = "Сергеев";
        var address = "ул.Пушкина дом №20";
        var city = "Москва";
        var state = "Московская область";
        var postcode = "0080000";
        var phonenumber = "+78008000000";
        driver.findElement(order_first_name).sendKeys(firstname);
        driver.findElement(order_last_name).sendKeys(lastname);
        driver.findElement(order_address).sendKeys(address);
        driver.findElement(order_city).sendKeys(city);
        driver.findElement(order_state).sendKeys(state);
        driver.findElement(order_postcode).sendKeys(postcode);
        driver.findElement(order_phone).sendKeys(phonenumber);
        driver.findElement(order_payment).click();
        driver.findElement(order_button).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(order_button));
        Assert.assertEquals("Заказ получен", driver.findElement(order_recieved).getText());
    }
}