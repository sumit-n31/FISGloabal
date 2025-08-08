package com.uitest;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils; 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UIAutomation {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = WebDriverManager.chromedriver().create();
		Thread.sleep(20);
		driver.get("https://www.ebay.com");
		driver.manage().window().maximize();
		String parent = driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(600));
		WebElement searchfield = driver.findElement(By.cssSelector("input#gh-ac"));
		wait.until(ExpectedConditions.visibilityOf(searchfield));
		searchfield.sendKeys("Books");
		Thread.sleep(10);
		WebElement searchbutton = driver.findElement(By.cssSelector("span.gh-search-button__label"));
		searchbutton.click();
		Thread.sleep(10);
		WebElement firstbook = driver
				.findElement(By.cssSelector("ul[class$='clearfix']>li:first-of-type>div>div:first-of-type"));
		wait.until(ExpectedConditions.visibilityOf(firstbook));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", firstbook);
		WebElement booktitle = driver.findElement(By.xpath("(//div[@class='s-card__title'])[1]/span"));
		wait.until(ExpectedConditions.visibilityOf(booktitle));
		String book = booktitle.getText();
		firstbook.click();
		Set<String> windowhandles = driver.getWindowHandles();
		Iterator<String> itr = windowhandles.iterator();
		
		while (itr.hasNext()) {
			String handle = itr.next();
			driver.switchTo().window(handle);
			Thread.sleep(50);
			if (driver.getTitle().equals(book)) {
				System.out.print(driver.getTitle());
				break;
			}
		}
		driver.navigate().refresh();
		WebElement addtocart = driver.findElement(By.cssSelector("a#atcBtn_btn_1"));
		wait.until(ExpectedConditions.visibilityOf(addtocart));
		js.executeScript("arguments[0].scrollIntoView(true)", addtocart);
		addtocart.click();
		Thread.sleep(20);
		WebElement closebutton = driver.findElement(By.xpath(
				"//body/div[3]/main[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[6]/ul[1]/li[2]/div[1]/div[1]/div[2]/div[2]/button[1]"));
		wait.until(ExpectedConditions.visibilityOf(closebutton));
		closebutton.click();
		WebElement cartitem = driver.findElement(By.cssSelector("div[class^='gh-cart']>div>a>span>span"));
		String item = cartitem.getText();
		WebElement cartwithicon = driver.findElement(By.cssSelector("div[class^='gh-cart']"));
		Thread.sleep(20);
		cartwithicon.click();
		Thread.sleep(20);SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String baseFileName = "image";
        String fileExtension = ".png";
        String fileName = baseFileName + timestamp + fileExtension;
        String directoryPath ="C:\\Users\\admin\\eclipse-workspace\\FISGlobal\\src\\";
        String fullpath = directoryPath+fileName;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(fullpath);
		try {
			FileUtils.copyFile(srcFile, destFile);
			System.out.println("Screenshot saved successfully!");
		} catch (IOException e) {
			System.err.println("Failed to save screenshot: " + e.getMessage());
		}

		Assert.assertEquals(item, "1");
	}

}
