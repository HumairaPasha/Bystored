package Testcases;

import General.MainClass;
import Objects.LandingPageObjects;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LandingPage extends MainClass {
    LandingPageObjects landing;
    String discountCode = "50";



    @Test
    public void applyDiscountCode() throws InterruptedException
    {

        driver.navigate().to(baseURL+"?v=2");
        driver.manage().window().maximize();
        landing = new LandingPageObjects(driver,wait);
        landing.getStarted();
        landing.enterDiscountCode(""+discountCode);
        Boolean result = landing.submitDiscountCode();
        Assert.assertTrue(result);

    }

}
