package CloudThing.Assignment;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.base.utilities.BaseClass;
import com.base.utilities.TestListeners;
import com.data.TestData;
import com.pageObjects.ClooudThingWelcomePage;

@Listeners(TestListeners.class)
public class TestCloudThings {
	
	 private static  Float cloudThings;
	 WebDriver driver;

	    @DataProvider(name = "dataProvider2")
	    public Object[][] dataProvider2(){
	        return new TestData().getTestData("src/test/resources/cloudThingTest.json");
	    }

	   @Test(dataProvider = "dataProvider2",enabled=false)
	    public void getCloudThing(HashMap<String,String> testData)  {
	    	
	        ClooudThingWelcomePage welcomePage = new ClooudThingWelcomePage("src/main/resourses/objectLocators/cloudThingWelcomePage.json");
	 

	        welcomePage.getTheWelcomePage(BaseClass.getInstance().getGlobalData().getUrl());
	        welcomePage.printAllCookiesAndDelete();
	        welcomePage.clickOnCarrersField();
	  
	     //   welcomePage.clickOnSearchField();
	     //   welcomePage.enterSearchText(testData.get("searchText"));
	     //   cloudThings = productPage.getProductPrice();
	    }
	   
	   @Test(dataProvider = "dataProvider2",priority = 1)
	   public void alertTest(HashMap<String,String> testData)
	   {
		   ClooudThingWelcomePage welcomePage = new ClooudThingWelcomePage("src/main/resourses/objectLocators/cloudThingWelcomePage.json");
		   
		   driver.get("https://www.toolsqa.com/handling-alerts-using-selenium-webdriver/");
           driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		   
	   }
}
