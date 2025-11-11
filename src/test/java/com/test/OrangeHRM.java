package com.test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM {
	WebDriver driver;
	

	@Test
@BeforeTest
@Parameters("browser")
	public void initializeBrowser(@Optional("chrome") String browser)
{
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			 driver=new EdgeDriver();
			break;
		default:
			break;
		}
	
	driver.manage().window().maximize();
}
	@Test
	@AfterTest
public void tearDown()
{
	driver.quit();
}
	@Test
	@Parameters("url")
public void login(String url) throws Exception
{
	driver.get(url);
	Thread.sleep(2000);
}
	@Test
	@Parameters({"username","password"})
public void loginCred(String username,String password) throws Exception
{
		
	driver.findElement(By.name("username")).sendKeys(username);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(2000);
}
	@Test
public void verifyLogin()
{
	WebElement welverify=	driver.findElement(By.xpath("//h6[text()='Dashboard']"));
    assertTrue(welverify.isDisplayed());
   // assertTrue(welverify.getText());
    System.out.println(welverify.getText());
	}
	@Test
public void navigatetoMyInfo() throws Exception
{Thread.sleep(2000);
	driver.findElement(By.xpath("//*[text()='My Info']")).click();
}
}
