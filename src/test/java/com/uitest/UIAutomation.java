package com.uitest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UIAutomation {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = WebDriverManager.chromedriver().create();
		driver.get("https://www.ebay.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement searchfield = driver.findElement(By.cssSelector("input#gh-ac"));
		wait.until(ExpectedConditions.visibilityOf(searchfield));
		searchfield.sendKeys("Books");
		Thread.sleep(10);
		WebElement searchbutton = driver.findElement(By.cssSelector("span.gh-search-button__label"));
		searchbutton.click();
		Thread.sleep(10);
		WebElement firstbook = driver.findElement(By.cssSelector("ul[class$='clearfix']>li:first-of-type"));
		wait.until(ExpectedConditions.visibilityOf(firstbook));
		firstbook.click();
		Thread.sleep(10);
		

	}

}
