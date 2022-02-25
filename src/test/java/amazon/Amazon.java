package amazon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon<WorkBook> {
	
	static WebDriver driver;
	static long startTime;
	
	//@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void launch() {
		System.out.println("Browser launch before executing the class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
	
	@AfterClass
	public static void browserQuit() {
		System.out.println("Browser quit after executing the class");
		//driver.quit();
		}
	
	@Before
	public void beforeMethod() {
		startTime = System.currentTimeMillis();
		System.out.println("Start time: " +startTime);
	}
	
	@After
	public void afterMethod() {
	   long endTime = System.currentTimeMillis();
	   System.out.println("End time :" +endTime);
	   System.out.println("Timetaken :" +(endTime - startTime));
	}
	
	@Test
	public void test1() {
		WebElement search = driver.findElement(By.xpath("//input[@type='text']"));
		search.sendKeys("iphone",Keys.ENTER);
		}
	
	@Test 
	public void test2() throws IOException {
		WebElement mobile = driver.findElement(By.xpath("(//span[contains(text(),'Apple')])[1]"));
		String name = mobile.getText();
		System.out.println("First mobile name :" +name);
		mobile.click();
		
		File f = new File("C:\\Users\\God\\OneDrive\\Desktop\\amazon\\Local Files\\sheet1.xlsx");
		Workbook w = new XSSFWorkbook();
		Sheet s1 = w.createSheet("sheet1");
		Row row1 = s1.createRow(0);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue(name);
		FileOutputStream f1 = new FileOutputStream(f);
		w.write(f1);
		System.out.println("Excel work completed");
	}		
		
	
	
	@Test
	public void test3() throws IOException {
		System.out.println("Start Test 3");
		
 		String parent = driver.getWindowHandle();
		System.out.println(parent);
		Set<String> allWindow = driver.getWindowHandles();
		List<String> l = new ArrayList<String>(allWindow);
		driver.switchTo().window(l.get(0));
		
		WebElement mobile1 = driver.findElement(By.id("productTitle"));
		String name1 = mobile1.getText();
		System.out.println("Second Mobile name :" +name1);
		mobile1.click();
			
		File f = new File("C:\\Users\\God\\OneDrive\\Desktop\\amazon\\Local Files\\sheet1.xlsx");
		FileInputStream f2 = new FileInputStream(f);
		Workbook w1 = new XSSFWorkbook(f2);
		Sheet s1 = w1.getSheet("sheet1");
		Row row2 = s1.createRow(1);
		Cell cell2 = row2.createCell(2);
		cell2.setCellValue(name1);
		FileOutputStream o = new FileOutputStream(f);
		w1.write(o);
	}
	
	@Test
	public void test4() throws IOException {
		System.out.println("take ScreenShot");
		
		TakesScreenshot tk = (TakesScreenshot)driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\God\\OneDrive\\Desktop\\amazon\\Local Files\\ss.png");
		FileUtils.copyFile(source, destination);
}
}	
	
	
	
	
	
	
	
	
	
	
	
	

