package tests;

import enums.BrowserType;
import helpers.BrowserFabric;
import helpers.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;


import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
     WebDriver driver;
     @Parameters({"browser"})
     @BeforeMethod
     public void startUp() {
          driver = BrowserFabric.getDriver(BrowserType.CHROME);
     }
//     public void startUp(String browser){
//          BrowserType browserType = browser.equals("CHROME") ? BrowserType.CHROME : BrowserType.FIREFOX;
//          driver = BrowserFabric.getDriver(browserType);

//          public void startUp(String browserName){
//          BrowserType type;
//          switch (browserName) {
//               case "CHROME": type = BrowserType.CHROME;
//               case "EDGE": type = BrowserType.EDGE;
//               case "OPERA": type = BrowserType.OPERA;
//               default: type = BrowserType.FIREFOX;
//                    driver = BrowserFabric.getDriver(type);
//}
//}


     @AfterMethod
     public void tearDown() throws InterruptedException{
//     public void tearDown(ITestResult iTestResult) throws InterruptedException{
//          if(iTestResult.getStatus()==ITestResult.FAILURE){
//               Date date = new Date();
//               SimpleDateFormat formatter = new SimpleDateFormat("--MM-dd-yyyy--HH-mm-ss");
//               String filename = iTestResult.getName()+formatter.format(date);
//               Screenshot.getScreenshot(driver, filename);
//          }
          Thread.sleep(2000);
          driver.quit();
}
}
