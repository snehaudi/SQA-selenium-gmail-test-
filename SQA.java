import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.JavascriptExecutor;

public class NewTestOne {
	WebDriver driver = null;
	String actualName = null;
	String actualEmail = null;
	String expectedName = "Abesinghage Udayangani";
	String expectedEmail = "udayanganiuditha@gmail.com";
	String password = "uditha123";
	String driverDir = "C:\\geckodriver.exe"; 
  @Test
  public void f() throws InterruptedException {
	  WebDriverWait wait = new WebDriverWait(driver, 20);
		
		driver.manage().window().maximize(); //Maximise the Firefox window
		
		driver.get("https://www.google.com"); //Go to https://www.google.com
		
		
		WebElement element = driver.findElement(By.name("q"));
	    element.sendKeys("GMAIL");
	    element.submit();
		
	    Thread.sleep(2000);
		// Click on the Gmail
		driver.findElement(By.xpath("//*[@class='r']//*[text()='Gmail - Google']")).click();
		
		Thread.sleep(5000);
		// Click Sign In
		WebElement clickElement = driver.findElement(By.xpath("//*[contains(@href,'https://mail.google.com/mail/')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", clickElement);
		
		Thread.sleep(2000);
		ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabList.get(0)); // Tab 1
		driver.close(); // Close Tab 1
		driver.switchTo().window(tabList.get(1)); // Tab 2
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("identifierId")).sendKeys(this.expectedEmail); //Email login 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/span/span")).click(); //Click next button
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement password = driver.findElement(
				By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")
				); 
      wait.until(ExpectedConditions.elementToBeClickable(password));
      password.sendKeys(this.password); //Enter password
      driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/span/span")).click(); //Click next button
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		
		WebElement userName = driver.findElement(
				By.xpath(
				"//div[@class='gb_lb']" //Relative XPath 
				));
		actualEmail = userName.getAttribute("innerHTML");

		
		
		WebElement nameOfUser = driver.findElement(
				By.xpath(
				"//div[@class='gb_jb gb_kb']" //Relative XPath 
				));
		actualName = nameOfUser.getAttribute("innerHTML");
  }
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.gecko.driver", driverDir);
		driver = new FirefoxDriver(); 
  }
  
  @AfterTest
	public void afterTest() {
		
		if (actualEmail.equals(expectedEmail) && actualName.equals(expectedName)) {
			System.out.println("Test Successful!\n"); 
		}else {
			System.out.println("Test Failed!\n"); 
		}
		driver.close();
	}

}
