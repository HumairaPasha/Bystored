package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StudentStorageObjects {
    ChromeDriver driver;
    By button = By.cssSelector("#find-my-unit-storage");
    By incrementCrate = By.xpath("//*[@id=\"student-storage-items-container\"]/div/div[1]/ul/li/div/div[3]/div/button[1]");
    By incrementItems = By.xpath("//*[@id=\"student-storage-items-container\"]/div/div[2]/ul/li/div/div[3]/div/button[1]");
    By incrementItems2 = By.xpath("//*[@id=\"student-storage-items-container\"]/div/div[3]/ul/li/div/div[3]/div/button[1]");
    By closetText = By.cssSelector("#detail-area-25 > div.card__back > div > h5");


    public StudentStorageObjects(ChromeDriver driver)
    {
        this.driver = driver;
    }

    //returns calculated storage unit
    public String calculateStorage() throws InterruptedException
    {
        driver.findElement(button).click();
        Thread.sleep(1500);
        driver.findElement(incrementCrate).click();
        driver.findElement(incrementCrate).click();
        driver.findElement(incrementCrate).click();
        driver.findElement(incrementItems).click();
        driver.findElement(incrementItems2).click();
        Thread.sleep(5000);

        WebElement element = driver.findElement(closetText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        String result = driver.findElement(closetText).getText();
        return result;
    }
}
