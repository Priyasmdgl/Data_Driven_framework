package Loginpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Correct_UP {
	@Test
	@Parameters({"username","password"})
	public void correct_UsernameandPassword(String name,String pwd) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys(name);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pwd);
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();
		
		driver.quit();
	}

}
