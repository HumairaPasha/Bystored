package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LandingPageObjects {
    By getStartedButton = By.cssSelector("#find-my-unit-storage");
    By discountInput = By.cssSelector("#coupon-code");
    By discountSubmit = By.cssSelector("#detail-area-12 > div.card__back.detailed-view > div > a");
    By discountText = By.cssSelector("#your-selection-panel > div.card__content.day-month-price > div.sub-details.discount > p > span");
    By storageEstimator = By.cssSelector("body > section.upsell > div > div > div:nth-child(2) > a.btn.bs-button.button--outlined.button--dark");
    ChromeDriver driver;
    WebDriverWait wait;


    public LandingPageObjects(ChromeDriver driver, WebDriverWait wait)
    {

        this.driver = driver;
        this.wait = wait;
    }

    public void getStarted() {
        driver.findElement(getStartedButton).click();

    }

    public void enterDiscountCode(String discount) throws InterruptedException
    {

        driver.findElement(discountInput).sendKeys(""+discount);

    }

    public Boolean submitDiscountCode () throws InterruptedException
    {

        // Thread.sleep(2000);
        WebElement button = driver.findElement(discountSubmit);
        driver.executeScript("arguments[0].scrollIntoView(true);", button);
        Thread.sleep(2000);
       // wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(discountSubmit)));
        button.click();


       // driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

       // Thread.sleep(1500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#your-selection-panel > div.card__content.day-month-price > div.sub-details.discount > p > span")));
     // wait.until(ExpectedConditions.presenceOfElementLocated(discountInput));
       // Boolean result = driver.findElements(By.linkText("Discount code applied.")).size() < 1;
        Boolean result = driver.findElement(By.cssSelector("#your-selection-panel > div.card__content.day-month-price > div.sub-details.discount > p > span")).isDisplayed();
        System.out.println(""+result);
        return result;
    }
}
