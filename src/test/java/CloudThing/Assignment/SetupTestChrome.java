package CloudThing.Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SetupTestChrome {
	
	WebDriver driver;
	
	@BeforeMethod
	public void init() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "src/main/resourses/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		Thread.sleep(6000);
		
		
	}
	
	@Test(enabled=true)
	public void chromeTest()
	{
		driver.get("https://www.toolsqa.com/handling-alerts-using-selenium-webdriver/");
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println("test Passed");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement simpleAlert = driver.findElement(By.xpath("//*[@id='content']/p[3]/button[contains(text(),'Simple Alert')]"));
       // js.executeScript("arguments[0].scrollIntoView();",simpleAlert);
        js.executeScript("window.scrollBy(0,490)");
		simpleAlert.click();
		WebDriverWait wait= new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert a1=driver.switchTo().alert();
		System.out.println(a1.getText());
		a1.accept();
		
		WebElement confirmatioAlert = driver.findElement(By.xpath("//*[@id='content']/p[7]/button[contains(text(),'Confirm Pop up')]"));
	
	    System.out.println(confirmatioAlert.getText());
	    confirmatioAlert.click();
	    Alert a2=driver.switchTo().alert();
		System.out.println(a2.getText());
		a2.dismiss();
		
		
		WebElement promptAlert = driver.findElement(By.xpath("//*[@id='content']/p[10]/button[contains(text(),'Prompt Pop up')]"));
		
	    System.out.println(promptAlert.getText());
	    promptAlert.click();
	    Alert a3=driver.switchTo().alert();
	    a3.sendKeys("shushu");
		a3.accept();

	    
	}
	
	
	@Test()
	public void dropdownTest()
	{
		driver.get("https://demoqa.com/automation-practice-form/");
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,300)");
		 WebElement element = driver.findElement(By.xpath("//div[@id='state']//div[contains(@class,'css-1hwfws3')]/div[contains(@class,' css-1uccc91-singleValue')]"));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
		Select dd=new Select(element);
		dd.selectByIndex(1);
		
	}
	
	@AfterTest
	public void shutDwn()
	{
		driver.quit();
	}

}
