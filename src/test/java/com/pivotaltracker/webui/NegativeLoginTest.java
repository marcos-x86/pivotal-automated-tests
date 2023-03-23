package com.pivotaltracker.webui;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeLoginTest {

    private WebDriver driver;

    @Test
    public void loginTestWithValidCredentials() {
        int numberOfExecutions = 10;
        int currentExecution = 0;

        while (currentExecution < numberOfExecutions) {
            Faker faker = new Faker();
            String loginUrl = "https://www.pivotaltracker.com/signin";
            String email = faker.internet().emailAddress();
            String password = faker.name().firstName();

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

            WebElement errorMessageElement = driver.findElement(By.cssSelector("#login_type_check_form > div.app_signin_error"));
            String actualErrorMessage = errorMessageElement.getText();
            Assert.assertEquals(actualErrorMessage, "Invalid username/password");

            if (driver != null) {
                driver.quit();
            }

            currentExecution++;
        }

    }
}
