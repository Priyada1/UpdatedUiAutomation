package com.base.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class WebdriverActions {

    private static WebdriverActions instance = new WebdriverActions();

    public static WebdriverActions getInstance(){
        return instance;
    }

    public WebElement performAction(WebDriver driver, Map<String, String> locator){
        String locatorType = locator.keySet().iterator().next();
        switch (locatorType.trim()){
            case "xpath" :  return driver.findElement(By.xpath(locator.get(locatorType)));
            case "id" :  return driver.findElement(By.id(locator.get(locatorType)));
            case "css" :  return driver.findElement(By.cssSelector(locator.get(locatorType)));
            default:throw new UnsupportedCommandException("Invalid locator type "+locatorType+" was passed in the objectRepo");
        }
    }
}
