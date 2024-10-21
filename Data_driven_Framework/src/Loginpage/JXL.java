package Loginpage;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class JXL {
	
	String [][] data= null;
    WebDriver driver;
	@DataProvider(name="login data")
	public String[][] dataprovider() throws BiffException, IOException {
		data=Login_data();
		return data;
	}
	
	public String[][] Login_data() throws IOException, BiffException {
		FileInputStream excel = new FileInputStream("C:\\Users\\HP\\Documents\\Login data.xls");
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowcount=sheet.getRows();
		int columncount=sheet.getColumns();
		String[][] data = new String[rowcount-1] [columncount];
		for(int i=1;i<rowcount;i++) {
			for(int j=0;j<columncount;j++) {
				data[i-1][j]=sheet.getCell(j, i).getContents();
			}
		}
		return data;
	}
	
	@BeforeTest
	public void beforetest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test(dataProvider = "login data")
	public void login(String name,String pwd) {
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys(name);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pwd);
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();
		
	}
	
	@AfterTest
	public void aftertest() {
		driver.quit();
	}
}

