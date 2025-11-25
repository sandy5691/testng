package in.ey.demotestng;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DemoTest {
	
	
	@Test
	public void test1() throws InterruptedException, AWTException, IOException {
		
		LocalDateTime date = LocalDateTime.now();
		String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
		
		ChromeOptions options = new ChromeOptions();
		Map<String,Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption(
			    "excludeSwitches",
			    Arrays.asList("--enable-automation")
			);
		options.setExperimentalOption(
			    "prefs",
			    prefs
			);
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File desFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+formattedDate+".png");
		driver.get("https://the-internet.herokuapp.com/");
		WebElement fileUploadLink = driver.findElement(By.linkText("File Upload"));
		jse.executeScript("arguments[0].click()", fileUploadLink);
		new WebDriverWait(driver, Duration.ofSeconds(5))
			.until(ExpectedConditions.visibilityOfElementLocated(By.id("file-upload")));
		//jse.executeScript("document.getElementById('file-submit').click()");
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.id("file-upload"))).click().perform();
		Thread.sleep(3000);
		
		Robot robot = new Robot();
		String path = "C:\\Users\\CW934NG\\Downloads\\20250908_153939.jpg";
		StringSelection str = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		jse.executeScript("document.getElementById('file-submit').click()");
		Thread.sleep(1000);
		driver.quit();
		driver.close();
	}
}
