package Assesment;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Question2{
    private WebDriver driver;

    @BeforeClass
    public void set() {
       
        driver = new ChromeDriver();
        driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/signup");
    }

    @Test
    public void testSignupValidation() {
    	
        // Mandatory field elements
        WebElement nameInput = driver.findElement(By.name("name"));
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement confirmPasswordInput = driver.findElement(By.name("confirm_password"));
        WebElement submitButton = driver.findElement(By.name("submit"));

        // Test blank mandatory fields
        nameInput.clear();
        emailInput.clear();
        passwordInput.clear();
        confirmPasswordInput.clear();
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("This field is required."), "Required field message not found!");
                 
        // Test invalid email format
        nameInput.sendKeys("Test User");
        emailInput.sendKeys("invalid_email");
        passwordInput.sendKeys("ValidPassword123");
        confirmPasswordInput.sendKeys("ValidPassword123");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Invalid email format."),"Email format error message not found!");
        
        // Test password mismatch
        emailInput.clear();
        emailInput.sendKeys("abc@example.com");
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("Password123");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Passwords do not match."), "Password mismatch error message not found!");
        
        // Test valid inputs
        passwordInput.clear();
        passwordInput.sendKeys("ValidPassword123");
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("ValidPassword123");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Account created successfully!"),"Success message not found!");
 
        // Test special characters (e.g., special characters in name)
        nameInput.clear();
        nameInput.sendKeys("swapna@#");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Account created successfully!"), "Account creation should handle special characters");

        // Test long input in name
        nameInput.clear();
        nameInput.sendKeys("abcdefghijklmnopqrstuvwxyz");
        emailInput.clear();
        emailInput.sendKeys("test@example.com");
        passwordInput.clear();
        passwordInput.sendKeys("ValidPassword123");
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys("ValidPassword123");
        submitButton.click();
        Assert.assertTrue(driver.getPageSource().contains("Name is too long."), "Error message not found for long name!"); 
                         
    }

    @AfterClass
    public void last() 
    {
        driver.quit();
    }
    
    
    
}
