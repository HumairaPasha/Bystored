package Objects;

import General.MainClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Checks whether the selected storage unit is displayed
 * on the wizard
 */

public class PricingObjects {

    ChromeDriver driver;
    WebDriverWait wait;
    By unitList = By.xpath("//*[@id=\"storageplanslider\"]/li");

    By checkAvailability;

    //By unitTitle = By.xpath("//*[@id=\"detail-area-25\"]/div[2]/div/h4");

    public static WebElement allElements;



    public PricingObjects(ChromeDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
    }

    public Boolean priceUnits () throws InterruptedException
    {

        //  List<WebElement> allElements = driver.findElements(unitList);
        //  System.out.println(""+driver.findElements(unitList).size());
        int items = driver.findElements(unitList).size();
        String[] unitNameArray = new String[items];
        int[] arr = {12,25,50,75,100,0};
        String[] nameArrayWiz = new String[items];
        Boolean res=true;
        for (int i=0;i<items;i++)
        {
            String unitTitle= "#detail-area-"+arr[i]+"> div.card__back > div > h4";
            driver.findElements(unitList).get(i).click();
            unitNameArray[i]=driver.findElement(By.cssSelector(unitTitle)).getText();
            //System.out.println("*****************"+driver.findElement(By.cssSelector(unitTitle)).getText());
           // Thread.sleep(1500);
            String button = "#detail-area-12 > div.card__back > div > button";
            String buttonCheckAvailability = "#detail-area-"+arr[i]+" > div.card__back > div > button";
            if (i==(items-1))
                break;
            driver.findElement(By.cssSelector(buttonCheckAvailability)).click();
            Thread.sleep(1000);

         //   System.out.println(driver.findElement(By.cssSelector("#selectedPlanText")).getText());
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#selectedPlanText")));
            nameArrayWiz[i] = driver.findElement(By.cssSelector("#selectedPlanText")).getText();
            driver.navigate().back();
            //Thread.sleep(6000);
            System.out.println(""+nameArrayWiz[i]);
            if(!nameArrayWiz[i].equalsIgnoreCase(unitNameArray[i]))
                res=false;
        }
        System.out.println(""+res);
        return res;

    }
    public WebElement getElementWithIndex(By by, int pos) throws InterruptedException {
        //Thread.sleep(1000);
        System.out.println("position"+pos);
        System.out.println(""+driver.findElements(by).get(pos).getText());

        return driver.findElements(by).get(pos);
    }

}
