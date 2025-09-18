import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuthorizationPageTests {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


    }

    @AfterClass
    public static void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
        driver.quit();
    }

    private By auth_page = By.cssSelector(".menu-item-30 a[href='http://intershop5.skillbox.ru/my-account/']");
    private By auth_username = By.cssSelector("[id='username']");
    private By auth_password = By.cssSelector("[id='password']");
    private By login_button = By.cssSelector(".woocommerce-button");
    private By my_account_page = By.cssSelector(".post-title");
    private By orders = By.cssSelector("a[href='http://intershop5.skillbox.ru/my-account/orders/']");
    private By edit_account_button = By.cssSelector("a[href='http://intershop5.skillbox.ru/my-account/edit-account/']");
    private By orders_message = By.cssSelector(".woocommerce-message");
    private By content = By.cssSelector(".woocommerce-MyAccount-content");
    private  By error_message = By.cssSelector(".woocommerce-error li");


    @Test
    public void registration_tests() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(auth_page).click();
        var username = "Marwin965";
        var password = "951753";
        driver.findElement(auth_username).sendKeys(username);
        driver.findElement(auth_password).sendKeys(password);
        driver.findElement(login_button).click();
        Assert.assertEquals("Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.", driver.findElement(error_message).getText());
    }
    @Test
    public void registration_tests4() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(auth_page).click();
        var username = "Marwin9";
        var password = "951753";
        driver.findElement(auth_username).sendKeys(username);
        driver.findElement(auth_password).sendKeys(password);
        driver.findElement(login_button).click();
        Assert.assertEquals("Веденный пароль для пользователя Marwin9 неверный. Забыли пароль?", driver.findElement(error_message).getText());
    }
    @Test
    public void registration_tests2() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(auth_page).click();
        var username = "Marwin9";
        var password = "951753";
        driver.findElement(auth_username).sendKeys(username);
        driver.findElement(auth_password).sendKeys(password);
        driver.findElement(login_button).click();
        Assert.assertEquals("Мой аккаунт", driver.findElement(my_account_page).getText());
        driver.findElement(orders).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(content));
        var actualText = getTextNode(driver.findElement(orders_message));
        Assert.assertEquals("Заказов еще нет.", actualText);
    }

    private String getTextNode(WebElement e)
    {
        String text = e.getText().trim();
        List<WebElement> children = e.findElements(By.xpath("./*"));
        for (WebElement child : children) {
            text = text.replaceFirst(child.getText(), "").trim();
        }
        return text;
    }
    private By first_name = By.cssSelector("[id = 'account_first_name']");
    private By last_name = By.cssSelector("[id = 'account_last_name']");
    private By password1 = By.cssSelector("[id='password_current']");
    private By password_new1 = By.cssSelector("[id='password_1']");
    private By password_new_replace = By.cssSelector("[id='password_2']");
    private By save_button = By.cssSelector(".woocommerce-Button");
    private By quit_button = By.cssSelector(".logout");
    private By successfully_message = By.cssSelector(".woocommerce-message");

    @Test
    public void registration_tests3() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(auth_page).click();
        var username = "Marwin9";
        var password = "Qasw75321!@!";
        driver.findElement(auth_username).sendKeys(username);
        driver.findElement(auth_password).sendKeys(password);
        driver.findElement(login_button).click();
        Assert.assertEquals("Мой аккаунт", driver.findElement(my_account_page).getText());
        driver.findElement(edit_account_button).click();
        var name = "Павел";
        var surname = "Сергеев";
        var password_new = "75321";
        driver.findElement(first_name).sendKeys(name);
        driver.findElement(last_name).sendKeys(surname);
        driver.findElement(password1).sendKeys(password);
        driver.findElement(password_new1).sendKeys(password_new);
        driver.findElement(password_new_replace).sendKeys(password_new);
        driver.findElement(save_button).click();
        Assert.assertEquals("Account details changed successfully.", driver.findElement(successfully_message).getText());
        driver.findElement(quit_button).click();
    }
}