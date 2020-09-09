package com.base.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.data.GlobalData;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseClass {

    private static BaseClass instance = new BaseClass();
    private List<String> testClasses;

    @Getter
    private ObjectMapper mapper;

    @Getter
    private WebDriver webDriver;

    @Getter
    GlobalData globalData;

    @Getter @Setter
    private ExtentReports extentReports;

    @Getter
    ExtentTest testClass;

    @Getter
    ExtentTest testCase;

    private BaseClass(){
        this.testClasses = new ArrayList();
        this.mapper = new ObjectMapper();
        try {
            this.globalData = this.mapper.readValue(new File("src/main/resourses/suiteSetUpDetails.json").getAbsoluteFile(),GlobalData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.extentReports = getExtentReporter(globalData.getReport());

    }


    @SuppressWarnings("deprecation")
	private ExtentReports getExtentReporter(String reportPath){
        ExtentReports extentReports = new ExtentReports();

        extentReports.setSystemInfo("OS : ", "os.name");
        extentReports.setSystemInfo("OS Architecture : ", "os.arch");
        extentReports.setSystemInfo("Java Version : ", "java.version");
        extentReports.setSystemInfo("Java Vendor : ", "java.vendor");

        @SuppressWarnings("deprecation")
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportPath);
        extentHtmlReporter.config().setDocumentTitle("Regression Test");
        extentHtmlReporter.config().setReportName("Regression");
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setReportUsesManualConfiguration(true);
        return extentReports;
    }

    public void setTestClassName(String testClassName){
        if(!testClasses.contains(testClassName)) {
            this.testClass = this.extentReports.createTest(testClassName);
            testClasses.add(testClassName);
        }
    }

    public void setTestName(String testCaseName){
        this.testCase = this.testClass.createNode(testCaseName);
    }

    public void setTestStatus(Status status, String testcaseName){
        switch (status.name().toLowerCase()){
            case "pass" : this.testCase.pass(testcaseName+" : PASSED");break;
            case "fail" : this.testCase.fail(testcaseName+": FAILED");break;
            case "skip" : this.testCase.fail(testcaseName+" :SKIPPED");break;
            //case "info" : this.testCase.info(testcaseName+" :INFO"+ msg);
            default:
                throw new IllegalStateException("Unexpected test status: " + status.name()+" was tried to be set in "+testcaseName);
        }
    }

    public void setTestStatus(Status status, String testcaseName,Throwable t){
        switch (status.name().toLowerCase()){
            case "fail" : this.testCase.fail(testcaseName+": FAILED  "+ t.getMessage());break;
            case "skip" : this.testCase.fail(testcaseName+" :SKIPPED  "+t.getMessage());break;
            default:
                throw new IllegalStateException("Unexpected test status: " + status.name()+" was tried to be set in "+testcaseName);
        }
    }

    public void setTestStatus(Status status, String testcaseName,String msg){
        this.testCase.info(testcaseName+" :INFO : "+ msg);

    }

    public void setUpDriver(){
        DriverClass driverClass = new DriverClass(this.globalData);
        driverClass.setUpDriver();
        this.webDriver = driverClass.getWebdriver();
    }

    public void closeWebdriver(){
        this.webDriver.close();
    }

    public static BaseClass  getInstance(){
        return instance;
    }

    public void closeAllBrowsers() {
        for(String window : this.webDriver.getWindowHandles()) {
            this.webDriver.switchTo().window(window);
            this.webDriver.close();
        }
        this.webDriver.quit();
    }
   

}
