package com.automation.justEat;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class Scenarios {
	

	public static void main(String[] args) {
		
		String myUrl = "https://www.just-eat.co.uk/";
		//implement WebDriver interface
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		  driver.manage().deleteAllCookies();
		  //driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);	
		
		  
		  searchByPostalCode(driver,myUrl);
		  dishSearch(driver,myUrl);
	}
	
	public static void searchByPostalCode(WebDriver dr, String url) {
		String pCode="AR51 1AA";
		String title;
		dr.get(url);
		WebElement el = dr.findElement(By.xpath("//input[@name='postcode']"));
		el.sendKeys(pCode);
		el.findElement(By.xpath("//button[@type='submit']")).click();
		
		title=dr.getTitle();
		//Assert.assertTrue(title.contains(pCode));
		
		
	}
	
	public static void dishSearch(WebDriver dr, String url) {

		//dr.get("https://www.just-eat.co.uk/account/login?returnurl=%2F");
		WebElement el = dr.findElement(By.id("dish-search"));
		el.sendKeys("Pizza Hut");
		el.findElement(By.xpath("//span[@class='u-showAboveMid c-searchInput-button-text']")).click();
		
		List<WebElement> links = dr.findElements(By.tagName("a"));
		
		for (int i=0; i < links.size();i++) {
			String linkText = links.get(i).getText();
			if (linkText.contains("Pizza Hut - Shell Menu"))
			{
				links.get(i).click();
				break;
			}
		}
		//System.out.println("here");
		dr.findElement(By.xpath("//form[@data-product-id='27884899']//button")).click();
		dr.findElement(By.xpath("//form[@data-product-id='27884898']//button")).click();
		dr.findElement(By.xpath("//form[@data-product-id='27884903']//button")).click();
		dr.findElement(By.xpath("//form[@data-product-id='27884902']//button")).click();
	

		dr.findElement(By.xpath("//form[@id='menuCheckout']/button")).click();;
	}

}
