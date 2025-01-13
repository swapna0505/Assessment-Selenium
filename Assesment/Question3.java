package Assesment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Question3 
{
	WebDriver driver=null;
		
	@BeforeClass
		 
		public void set() 
		{
		        driver = new ChromeDriver();
		        driver.get("https://app-staging.nokodr.com/super/apps/auth/v1/index.html#/login");
		 }
		 @Test
		 public void testLoginValidation() 
		  {
		       // Mandatory field validation
		        WebElement usernameInput = driver.findElement(By.name("username"));
		        WebElement passwordInput = driver.findElement(By.name("password"));
		        WebElement submitButton = driver.findElement(By.name("submit"));

		        // Test blank fields
		        usernameInput.clear();
		        passwordInput.clear();
		        submitButton.click();
		        Assert.assertTrue(driver.getPageSource().contains("This field is required."), "Required field message not found!");
		         // Test special characters
		        usernameInput.sendKeys("Swapna!@#");
		        passwordInput.sendKeys("Swapna!@#");
		        submitButton.click();
		        Assert.assertTrue(driver.getPageSource().contains("Invalid username or password"),  "Error message not found!");
		                         
		        // Test valid inputs
		        usernameInput.clear();
		        passwordInput.clear();
		        usernameInput.sendKeys("valid_username");
		        passwordInput.sendKeys("valid_password");
		        submitButton.click();
		        Assert.assertTrue(driver.getPageSource().contains("Welcome to your dashboard"),"Dashboard not found!");

		        // Test invalid password
		        usernameInput.clear();
		        passwordInput.clear();
		        usernameInput.sendKeys("valid_username");
		        passwordInput.sendKeys("wrong_password");
		        submitButton.click();
		        Assert.assertTrue(driver.getPageSource().contains("Invalid username or password"),"Error message not found!");
		              
		    }

		    @AfterClass
		    public void last() {
		        driver.quit();
		    }
		

	}


