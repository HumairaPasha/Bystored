package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Step3Objects {

    WebDriver driver;
    By creditCard = By.cssSelector("#ccnumber");
    By nameOnCard = By.cssSelector("#nameoncard");
    By expMonth = By.cssSelector("#cc-exp-month");
    By expYear = By.cssSelector("#cc-exp-year");
    By cvvCode = By.cssSelector("#cvv");
    // By termsCheck = By.xpath("//*[@id=\"termsAgreement\"]");
    By submitClick = By.xpath("//*[@id=\"stage3\"]");

    public Step3Objects(WebDriver driver) {

        this.driver = driver;
    }

    public void enterBillingDetails() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(creditCard).sendKeys("4111111111111111");
        driver.findElement(nameOnCard).sendKeys("Visa");
        driver.findElement(expMonth).sendKeys("12");
        driver.findElement(expYear).sendKeys("2022");
        driver.findElement(cvvCode).sendKeys("123");
        Actions actions = new Actions(driver);
        WebElement termsCheck = driver.findElement(By.xpath("//*[@id=\"termsAgreement\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",termsCheck);
        //driver.findElement(termsCheck).click();

        //  actions.moveToElement(termsCheck).click().perform();
        Thread.sleep(10000);

        // driver.findElement(termsCheck).click();
//        driver.findElement(By.cssSelector("body > div > div > div > div.main_stack.meshim_widget_components_chatWindow_MainStack.desktop.ms_bl > div > div.meshim_widget_widgets_TitleBar.title_bar.desktop.ltr.bl > div.icons.jx_ui_Widget > div.button_container.last_child.jx_ui_Widget")).click();
        termsCheck.click();
        return;

    }

    public String submitDetails() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"stage3\"]")).click();
        Thread.sleep(15000);
        String text = driver.findElement(By.cssSelector("body > section > h1")).getText();
        System.out.println(""+text);
        return text;
    }
}
