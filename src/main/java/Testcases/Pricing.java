package Testcases;

import General.MainClass;
import Objects.PricingObjects;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Pricing extends MainClass{

    PricingObjects pricing;

 //   String[] unitNames = {"Closet Space","Studio Flat","1 Bed Flat","2 Bed Flat","3 Bed Flat","Custom Plan"};

    @Test
    public void pricingTest() throws InterruptedException{

        driver.navigate().to(baseURL+"/pricing?");
        driver.manage().window().maximize();
        pricing = new PricingObjects(driver,wait);
        Boolean result = pricing.priceUnits();
        Assert.assertTrue(result);

    }
}
