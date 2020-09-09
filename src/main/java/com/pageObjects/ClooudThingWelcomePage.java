package com.pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.Status;
import com.base.utilities.BaseClass;
import com.base.utilities.WebdriverActions;

public class ClooudThingWelcomePage {
	
	BaseClass base ;
    Map<String,Map<String,String>> objectLocators;
    WebDriver driver;
    WebdriverActions actions;

    public ClooudThingWelcomePage(String pageLocatorFile){
        this.base = BaseClass.getInstance();
        this.actions = WebdriverActions.getInstance();
        try {
            objectLocators = base.getMapper().readValue(new File(pageLocatorFile).getAbsoluteFile(), HashMap.class);
            driver = base.getWebDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public void getTheWelcomePage(String url) {
        try {
            driver.get(url);
            base.getTestCase().log(Status.INFO, "Landed on "+url);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       
            
        }catch (Exception e){
            throw new RuntimeException("failed to navigate to"+url+" : " +e);
        }
    }
    
    public void printAllCookiesAndDelete()
    {
        Set<Cookie> list=driver.manage().getCookies(); 
        for(Cookie s:list)
        {
        	System.out.println(">>>>>>"+s);
        }
        driver.manage().deleteAllCookies();
    }
    
    public void clickOnCarrersField() {
    	
    	// Create instance of Javascript executor
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element=driver.findElement(By.xpath("//a[contains(text(),'careers@cloudthing.com')]"));
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		element.click();
		element.click();
//        try {
//            actions.performAction(driver,objectLocators.get("carrers")).click();
//            base.getTestCase().log(Status.INFO, "Clicked on search field ");
//        }catch (Exception e){
//            throw new RuntimeException("failed to click on search field " +e);
//        }
    }

}
