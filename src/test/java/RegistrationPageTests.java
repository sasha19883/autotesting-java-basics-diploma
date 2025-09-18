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

public class RegistrationPageTests {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


    }

    @AfterClass
    public static void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
        driver.quit();
    }

    private By register_page = By.cssSelector(".menu-item-30 a[href='http://intershop5.skillbox.ru/my-account/']");
    private By button_register = By.cssSelector(".custom-register-button");
    private By registr_username = By.cssSelector("[id='reg_username']");
    private By registr_email = By.cssSelector("[id='reg_email']");
    private By registr_password = By.cssSelector("[id='reg_password']");
    private By register_done = By.cssSelector(".content-page div");
    private By register_button2 = By.cssSelector(".woocommerce-form-register__submit");
    private By error_register = By.cssSelector(".woocommerce-error");

    @Test
    public void registration_tests() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(register_page).click();
        driver.findElement(button_register).click();
        var username = "Marwin9";
        var email = "marwin9@test.ru";
        var password = "951753";

        driver.findElement(registr_username).sendKeys(username);
        driver.findElement(registr_email).sendKeys(email);
        driver.findElement(registr_password).sendKeys(password);
        driver.findElement(register_button2).click();
        Assert.assertEquals("Регистрация завершена", driver.findElement(register_done).getText());
    }

    @Test
    public void registration_tests2() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(register_page).click();
        driver.findElement(button_register).click();
        var email = "marwin2@test.ru";
        var password = "951753";
        driver.findElement(registr_email).sendKeys(email);
        driver.findElement(registr_password).sendKeys(password);
        driver.findElement(register_button2).click();
        Assert.assertEquals("Error: Пожалуйста введите корректное имя пользователя.", driver.findElement(error_register).getText());
    }

    @Test
    public void registration_tests3() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(register_page).click();
        driver.findElement(button_register).click();
        var username = "Marwin3";
        var password = "951753";
        driver.findElement(registr_username).sendKeys(username);
        driver.findElement(registr_password).sendKeys(password);
        driver.findElement(register_button2).click();
        Assert.assertEquals("Error: Пожалуйста, введите корректный email.", driver.findElement(error_register).getText());
    }

    @Test
    public void registration_tests4() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(register_page).click();
        driver.findElement(button_register).click();
        var username = "Marwin4";
        var email = "marwin4@test.ru";
        driver.findElement(registr_username).sendKeys(username);
        driver.findElement(registr_email).sendKeys(email);
        driver.findElement(register_button2).click();
        Assert.assertEquals("Error: Введите пароль для регистрации.", driver.findElement(register_done).getText());
    }
    @Test
    public void registration_tests5() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(register_page).click();
        driver.findElement(button_register).click();
        var username = "Marwin";
        var email = "marwin1@test.ru";
        var password = "951753";
        driver.findElement(registr_username).sendKeys(username);
        driver.findElement(registr_email).sendKeys(email);
        driver.findElement(registr_password).sendKeys(password);
        driver.findElement(register_button2).click();
        Assert.assertEquals("Error: Учетная запись с такой почтой уже зарегистировавана. Пожалуйста авторизуйтесь.", driver.findElement(register_done).getText());
    }
    @Test
    public void registration_tests6() {
        driver.navigate().to("http://intershop5.skillbox.ru/");
        driver.findElement(register_page).click();
        driver.findElement(button_register).click();
        var username = "Marwin";
        var email = "marwin6@test.ru";
        var password = "951753";
        driver.findElement(registr_username).sendKeys(username);
        driver.findElement(registr_email).sendKeys(email);
        driver.findElement(registr_password).sendKeys(password);
        driver.findElement(register_button2).click();
        Assert.assertEquals("Error: Учетная запись с таким именем пользователя уже зарегистрирована.", driver.findElement(register_done).getText());
    }
}