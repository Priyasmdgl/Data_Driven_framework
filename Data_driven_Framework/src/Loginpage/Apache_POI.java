package Loginpage;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class Apache_POI {
    WebDriver driver;

    @DataProvider(name = "login data")
    public String[][] Login_data() throws IOException {
        FileInputStream excel = new FileInputStream("C:\\Users\\HP\\Documents\\Login data.xls");
        Workbook workbook = new HSSFWorkbook(excel);
        Sheet sheet = workbook.getSheetAt(0);
        
        int rowCount = sheet.getPhysicalNumberOfRows();
        String[][] data = new String[rowCount - 1][2]; // Declare as String[][]
        
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            data[i - 1][0] = row.getCell(0).getStringCellValue(); // username
            data[i - 1][1] = row.getCell(1).getStringCellValue(); // password
        }
        
        workbook.close();
        excel.close();
        return data;
    }

    @Test(dataProvider = "login data")
    public void login(String name, String pwd) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
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
