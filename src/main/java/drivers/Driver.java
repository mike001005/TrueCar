package drivers;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by JRNorris on 9/30/15.
 */

public class Driver {

	public static final int DEFAULT_TIMEOUT = 10;

	public static WebDriver getDriver(String driverName)  {

		WebDriver driver = null;

		switch (driverName) {

			case "firefox":
				driver = FireFox.fireFoxDriver();
				break;
		}

		driver.manage().window().setSize(new Dimension(1920, 1080));
		return driver;
	}
	
	public static WebElement findElement(WebDriver driver,By locatorKey) {
		   
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		try{
			wait.until(ExpectedConditions.presenceOfElementLocated(locatorKey));
			element = driver.findElement(locatorKey);
			
		}catch(Exception e){
			element = null;
		}
		 
	    return element;
	}


	public static List<WebElement> findElements(WebDriver driver,By locatorKey){
		
		List<WebElement> elements = new ArrayList<WebElement>();
		WebDriverWait wait = new WebDriverWait(driver,10);

		
		try{
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorKey));
			elements = driver.findElements(locatorKey);
		}catch(Exception e){
			
			elements = null;
		}
					
				
		return elements;
			
	}
	
	
	public static void sendkeys(WebElement element, String keys){
			
		element.click();
		element.clear();
		element.sendKeys(keys);
		
	}

	public static void click(WebElement element){

			element.click();
	}


	public static void clickWait(WebElement element, long time){


			element.click();

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}
