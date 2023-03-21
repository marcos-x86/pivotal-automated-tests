package com.pivotaltracker.webui;

import com.pivotaltracker.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginTest {

    private ConfigReader configReader = new ConfigReader();
    private WebDriver driver;

    @Test
    public void loginTestWithValidCredentials() {
        String loginUrl = "https://www.pivotaltracker.com/signin";
        String email = configReader.getUserEmail();
        String password = configReader.getUserPassword();

//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);

        driver.manage().window().maximize();
        driver.get(loginUrl);

        WebElement emailInput = driver.findElement(By.cssSelector("#credentials_username"));
        emailInput.sendKeys(email);

        WebElement nextButton = driver.findElement(By.cssSelector("#login_type_check_form > input.app_signin_action_button"));
        nextButton.click();

        WebElement passwordInput = driver.findElement(By.cssSelector("#credentials_password"));
        passwordInput.sendKeys(password);

        WebElement sigInButton = driver.findElement(By.cssSelector("#login_type_check_form > input.app_signin_action_button"));
        sigInButton.click();

        WebElement createProjectButton = driver.findElement(By.cssSelector("#create-project-button"));
        Assert.assertTrue(createProjectButton.isDisplayed());

        WebElement pivotalLogo = driver.findElement(By.xpath("//*[@id=\"shared_header\"]/div/div/header/ul/li[2]/div/button/div"));
        Assert.assertTrue(pivotalLogo.isDisplayed());
    }

    @AfterMethod
    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
