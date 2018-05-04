package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Step2Objects {

    WebDriver driver;
    By firstName = By.xpath("//*[@id=\"fName\"]");
    By lastName = By.xpath("//*[@id=\"lName\"]");
    By emailAddress = By.xpath("//*[@id=\"email\"]");
    By phoneNumber = By.xpath("//*[@id=\"pNumber\"]");
    By nextButton = By.xpath("//*[@id=\"stage2\"]");
    By existingUser = By.xpath("//*[@id=\"logInModal\"]/a");
    By existingUsername = By.cssSelector("#emailLogin");
    By existingPassword = By.cssSelector("#passwordLogin");
    By existinglogin = By.xpath("//*[@id=\"btnLoginSubmit\"]");

    public Step2Objects(WebDriver driver) {

        this.driver = driver;
    }

    public void enterPersonalDetails (String fName, String lName, String email, String pNumber)

    {
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(emailAddress).sendKeys(email);
        driver.findElement(phoneNumber).sendKeys(pNumber);

        goToStep3();

    }

    public void goToStep3()
    {
        driver.findElement(nextButton).click();
        return;
    }

    public void userExisting () throws InterruptedException {
        WebElement signInLink = driver.findElement(existingUser);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",signInLink);
        Thread.sleep(2500);
        signInLink.click();
        Thread.sleep(1500);
        driver.findElement(existingUsername).sendKeys("humairapasha95@gmail.com");
        driver.findElement(existingPassword).sendKeys("venture");
        driver.findElement(existinglogin).click();

    }

}
