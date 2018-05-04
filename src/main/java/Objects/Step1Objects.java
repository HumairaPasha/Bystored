package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Step1Objects {

    WebDriver driver;
    By manualAddressLink = By.cssSelector("#manual-input");
    By findMyUnit = By.xpath("//*[@id=\"find-my-unit-storage\"]");
    By checkAvailability = By.xpath("//*[@id=\"detail-area-12\"]/div[2]/div/button");
    By searchAddress = By.xpath("//*[@id=\"search-address\"]");
    By addressList1 = By.xpath("//*[@id=\"search-results-container\"]/ul");
    By addressListSec = By.xpath("//*[@id=\"search-results-container\"]/ul");
    By stairs = By.xpath("//*[@id=\"stairs\"]");
    By stairsList = By.xpath("//*[@id=\"stairs\"]/ul");
    By packingAssisstance = By.xpath("//*[@id=\"assistance\"]");
    By packingList = By.xpath("//*[@id=\"assistance\"]/ul");
    By dateBooking = By.xpath("//*[@id=\"datepicker\"]/div/table");
    By timeslotBooking = By.xpath("//*[@id=\"afternoonTime\"]");
    By step2Button = By.xpath("//*[@id=\"stage1\"]");
    By postcode = By.cssSelector("#postalCode");
    By manualAddress1 = By.cssSelector("#addressLine1");
    By manualAddress2 = By.cssSelector("#addressLine2");
    By deliveryLink = By.cssSelector("#notAvailable");
    By deliveryEmailAddress = By.cssSelector("#postcode-unavailable-email");
    By deliveryPostCode = By.cssSelector("#postcode-unavailable-postcode");
    By deliveryPhoneNumber = By.cssSelector("#postcode-unavailable-phone");
    By deliverySubmit = By.cssSelector("#submit-postcodeunavailable-form");
    By messagePrompt = By.cssSelector("#postcode-unavailable-success");
    By getMessagePrompt1 = By.cssSelector("#postcode-not-available > div > div > div.modal-body > span");


    public Step1Objects(WebDriver driver) {
        this.driver = driver;
    }

    public void bookNow() throws InterruptedException {
        driver.findElement(findMyUnit).click();
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(checkAvailability);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        Thread.sleep(2500);
        button.click();
    }
    public void searchAddress(String search,int pos) throws InterruptedException
    {
        driver.findElement(searchAddress).sendKeys(""+search);
        Thread.sleep(10000);

        WebElement elements = driver.findElement(addressList1);

        List<WebElement> addressList = elements.findElements(By.tagName("li"));

        addressList.get(pos).click();

        Thread.sleep(500);

        Thread.sleep(1000);
        WebElement elements2 = driver.findElement(addressListSec);
        List<WebElement> addressList2 = elements2.findElements(By.tagName("li"));

        System.out.println(addressList2.get(pos).getText());
        addressList2.get(pos).click();
    }

    public void enterManualAddress(){
        driver.findElement(manualAddressLink).click();
        driver.findElement(postcode).sendKeys("sw66af");
        driver.findElement(manualAddress1).sendKeys("rowallan road");
        driver.findElement(manualAddress2).sendKeys("london");

    }

    public void bookingDetails() throws InterruptedException {

        //  WebElement element = driver.findElement(By.xpath("//*[@id=\"stairs\"]"));
        driver.findElement(stairs).click();
        WebElement elementStairInfo = driver.findElement(stairsList);
        List<WebElement> stairsInfo = elementStairInfo.findElements(By.tagName("li"));
        stairsInfo.get(0).click();

        driver.findElement(packingAssisstance).click();

        WebElement elementPackingInfo = driver.findElement(packingList);
        List<WebElement> packingInfo = elementPackingInfo.findElements(By.tagName("li"));
        packingInfo.get(0).click();
        Thread.sleep(11000);
        WebElement table = driver.findElement(dateBooking);
        List<WebElement> columns = table.findElements(By.tagName("td"));
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        for (WebElement cell : columns) {


            if(cell.getText().equals(""+date))

            {
                cell.click();
                break;
            }

        }

        Thread.sleep(4500);


        WebElement timeslot = driver.findElement(timeslotBooking);
        List<WebElement> timeslotSelection = timeslot.findElements(By.tagName("li"));

        timeslotSelection.get(0).click();

        goToStep2();
//  System.out.println(""+ timeslotSelection.get(0).getText());
        // Thread.sleep(1000);

    }

    public void goToStep2() throws InterruptedException {
        driver.findElement(step2Button).click();

        return;
    }

    public String notAvailableAddress(String email, String postcode, String phoneNumber) throws InterruptedException {

        driver.findElement(deliveryLink).click();
        Thread.sleep(1000);
        driver.findElement(deliveryEmailAddress).sendKeys(""+email);
        driver.findElement(deliveryPostCode).sendKeys(""+postcode);
        driver.findElement(deliveryPhoneNumber).sendKeys(""+phoneNumber);
        driver.findElement(deliverySubmit).click();
        Thread.sleep(1500);


        //System.out.println("%%"+text);
        if (email==null||postcode==null||phoneNumber==null)
        {
            String text2 = driver.findElement(getMessagePrompt1).getText();
            System.out.println(""+text2);
            return text2;
        }
        else
        {
            Thread.sleep(1500);
            String text = driver.findElement(messagePrompt).getText();
            System.out.println("********"+text);
            return text;
        }


    }
}
