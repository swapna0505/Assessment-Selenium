package Assesment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Question4
{
    private WebDriver driver;

    @BeforeClass
    public void set() {
       
        driver = new ChromeDriver();
        driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/forgotpassword");
    }

    @Test
    public void testForgotPasswordValidation() {
        // Input field element
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        // Test blank email field
        emailInput.clear();
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("This field is required."),"Required field message not found!"); 
        // Test invalid email format
        emailInput.sendKeys("invalid_email");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Invalid email format."),"Email format error message not found!"); 
        // Test non-registered email
        emailInput.clear();
        emailInput.sendKeys("nonregistered@example.com");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Email not registered."), "Error message for non-registered email not found!");
 
        // Test valid registered email
        emailInput.clear();
        emailInput.sendKeys("registered@example.com"); // Change it to the registered email for testing
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Reset link sent to your email."),"Success message not found!");
    } 
                          

    @AfterClass
    public void last() {
        driver.quit();
    }
}