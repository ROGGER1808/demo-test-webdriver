package com.genson;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AppTest {
    public WebDriver driver;


    @Test(description = "testcase: login - để trống tất cả các field", priority = 1)
    public void testAllFieldEmpty() {
        String expected = "Không được để trống tên đăng nhập và mật khẩu";

        // -------------------------- handler element ---------------------------------------------
        WebElement username = driver.findElement(By.cssSelector("input#UserName"));
        username.sendKeys("");

        WebElement password = driver.findElement(By.cssSelector("input#Password"));
        password.sendKeys("");

        WebElement btnSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        btnSubmit.click();

        WebElement err = driver.findElement(By.cssSelector("label[for='inputPassword6']"));
        // -----------------------------------------------------------------------------------------

        Assert.assertEquals(err.getText(), expected);
    }


    @Test(description = "testcase: login - có password và username empty", priority = 2)
    public void testWithUsernameEmpty() {
        String expected = "Không được để trống tên đăng nhập và mật khẩu";

        // -------------------------- handler element ---------------------------------------------
        WebElement username = driver.findElement(By.cssSelector("input#UserName"));
        username.sendKeys("");

        WebElement password = driver.findElement(By.cssSelector("input#Password"));
        password.sendKeys("iuwehrqiuqwr");

        WebElement btnSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        btnSubmit.click();

        WebElement err = driver.findElement(By.cssSelector("label[for='inputPassword6']"));
        // -----------------------------------------------------------------------------------------

        Assert.assertEquals(err.getText(), expected);
    }


    @Test(description = "testcase: login - có username và password empty", priority = 3)
    public void testWithPasswordEmpty() {
        String expected = "Không được để trống tên đăng nhập và mật khẩu";

        // -------------------------- handler element ---------------------------------------------
        WebElement username = driver.findElement(By.cssSelector("input#UserName"));
        username.sendKeys("13928712424");

        WebElement password = driver.findElement(By.cssSelector("input#Password"));
        password.sendKeys("");

        WebElement btnSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        btnSubmit.click();

        WebElement err = driver.findElement(By.cssSelector("label[for='inputPassword6']"));
        // -----------------------------------------------------------------------------------------

        Assert.assertEquals(err.getText(), expected);
    }


    @Test(description = "testcase: login - có username và password nhưng không chính xác", priority = 4)
    public void testIncludePasswordAndUsernameNotExact() {
        String expected = "Vui lòng kiểm tra lại thông tin đăng nhập !";

        // -------------------------- handler element ---------------------------------------------
        WebElement username = driver.findElement(By.cssSelector("input#UserName"));
        username.sendKeys("13928712424");

        WebElement password = driver.findElement(By.cssSelector("input#Password"));
        password.sendKeys("iuwehrqiuqwr");

        WebElement btnSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        btnSubmit.click();

        WebElement err = driver.findElement(By.cssSelector("label[for='inputPassword6']"));
        // -----------------------------------------------------------------------------------------

        Assert.assertEquals(err.getText(), expected);
    }


    @Test(description = "testcase: login - có username và password nhưng chính xác", priority = 5)
    public void testIncludePasswordAndUsernameExact() {
        String expectedUrl = "https://tinchi.hau.edu.vn/SinhVien/Home";

        // -------------------------- handler element ---------------------------------------------
        WebElement username = driver.findElement(By.cssSelector("input#UserName"));
        // ở đây cần username chính xác
        username.sendKeys(Secret.USERNAME.getValue());

        WebElement password = driver.findElement(By.cssSelector("input#Password"));
        // ở đây cần password chính xác
        password.sendKeys(Secret.PASSWORD.getValue());

        WebElement btnSubmit = driver.findElement(By.cssSelector("button[type='submit']"));
        btnSubmit.click();
        // -----------------------------------------------------------------------------------------

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, expectedUrl);
    }


    @BeforeTest
    public void starting() {

        // config before run test
        // using chrome driver and test login tinchi.hau.edu.vn
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();

        this.driver.manage().window().fullscreen();
        this.driver.get("https://tinchi.hau.edu.vn/");
    }

    @AfterTest
    public void finish() {
        // close chrome when finish
        this.driver.close();
    }
}
