package com.base.utilities;

import com.data.GlobalData;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverClass {
    private GlobalData globalData;

    @Getter
    private WebDriver webdriver;

    public DriverClass(GlobalData globalData){
        this.globalData = globalData;
    }

    public void setUpDriver(){
        switch (globalData.getPlatform()){
            case "pc":  setPcPlatform();break;
            case "mobile": throw new UnsupportedOperationException("Mobile platform testing is in development");
            default: throw new IllegalStateException("Failed to get valid Platform type. Unexpected OS "+globalData.getOs()+". Try 'mac' or 'windows");
        }
    }

    private void setPcPlatform() {
        String driverPath = globalData.getDriverPath();
        switch (globalData.getDriver().toLowerCase()){
            case "chrome":
                System.setProperty("webdriver.chrome.driver",driverPath);
                this.webdriver = new ChromeDriver();break;
            case "safari":
                System.setProperty("webdriver.safari.driver",driverPath);
                this.webdriver = new ChromeDriver();break;
            default: throw new IllegalStateException("Failed to instantiate driver. Unexpected driver "+globalData.getDriver());
        }
    }

    private void setMobilePlatform() {
        switch (globalData.getDriver().toLowerCase()){
            case "android":throw new UnsupportedOperationException("Android Mobile platform testing is in development");
            case "ios":throw new UnsupportedOperationException("IOS Mobile platform testing is in development");
            default: throw new IllegalStateException("Failed to instantiate driver. Unexpected mobile platform "+globalData.getDriver());
        }
    }


}


