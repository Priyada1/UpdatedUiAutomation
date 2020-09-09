package com.base.utilities;


import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ITestListener {
    BaseClass baseClass;

    public void onTestStart(ITestResult result) {
        try {
            baseClass.getExtentReports().getStartedReporters();
            baseClass.setTestClassName(result.getInstanceName().split("[.]")[result.getInstanceName().split("[.]").length - 1]);
            baseClass.setTestName(result.getName());
            baseClass.setUpDriver();
        }catch (Exception e){
            throw new RuntimeException("Failed execute procedure on test start "+e.getMessage());
        }
    }

    public void onTestSuccess(ITestResult result) {
        try {
        baseClass.setTestStatus(Status.PASS,result.getName());
        baseClass.closeWebdriver();
        }catch (Exception e){
            throw new RuntimeException("Failed execute procedure on test success "+e.getMessage());
        }
    }

    public void onTestFailure(ITestResult result) {
        try{
        baseClass.setTestStatus(Status.FAIL,result.getName(),result.getThrowable());
        baseClass.closeWebdriver();
        }catch (Exception e){
            throw new RuntimeException("Failed execute procedure on test failure "+e.getMessage());
        }
    }

    public void onTestSkipped(ITestResult result) {
        try{
        baseClass.setTestStatus(Status.SKIP,result.getName());
        baseClass.closeWebdriver();
        }catch (Exception e){
            throw new RuntimeException("Failed execute procedure on test skipped "+e.getMessage());
        }
    }

    public void onStart(ITestContext context) {
        try{
        baseClass = BaseClass.getInstance();
        }catch (Exception e){
            throw new RuntimeException("Failed start the test run "+e.getMessage());
        }
    }

    public void onFinish(ITestContext context) {
        try{
        baseClass.getExtentReports().flush();
        baseClass.closeAllBrowsers();
        }catch (Exception e){
            throw new RuntimeException("Failed execute procedure on Finish "+e.getMessage());
        }
    }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
    
}
