package Testcases;

import General.MainClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import Objects.Step1Objects;
import Objects.Step2Objects;
import Objects.Step3Objects;

//import static General.MainClass.getDriver;


public class Wizard extends MainClass{


   // public static WebDriver driver;
    public static Step1Objects booking,step1;
    public static Step2Objects personalInfo,step2;
    public static Step3Objects billingDetails,step3;
    public String text = "You are all set, now sit back and relax!";
    public String messageDisplayed = "You are all set, now sit back and relax!";
    public static String exectedResult = "Thanks! We'll be in touch.";
    String expectedResult2="Please fill in all the fields!";
    By logout = By.xpath("//*[@id=\"new-header-links\"]/li[8]/a");


 /*   @Before
    public void launchBrowser ()
    {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Humaira Pasha\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Humaira Pasha\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://stage.bystored.com");
        driver.manage().window().maximize();

    }
*/
    public void navigateToWizard(){

        driver.navigate().to(baseURL);
        driver.manage().window().maximize();
    }

    public void logout() throws InterruptedException{
      /*  WebElement button = driver.findElement(logout);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        Thread.sleep(15000);
        button.click();
        driver.findElement(logout).click();*/
      Thread.sleep(1500);
      driver.navigate().to(baseURL+"/logout");

    }

    @Test
    public void bookStorageUnit() throws InterruptedException {

        navigateToWizard();
        booking = new Step1Objects(driver);
        booking.bookNow();
        booking.searchAddress("sw66af",0);
        booking.bookingDetails();

        personalInfo=new Step2Objects(driver);
        Thread.sleep(1500);
        String email = "test"+(int)(Math.random()*100)+"@bystored.com";
        personalInfo.enterPersonalDetails("Test","Bystored",email,"03329618585");

        billingDetails = new Step3Objects(driver);
        billingDetails.enterBillingDetails();
        String msgDisplayed= billingDetails.submitDetails();
        Assert.assertEquals(""+text,""+msgDisplayed);
        logout();

    }

    @Test
    public void logInExistingUser() throws InterruptedException
    {
        navigateToWizard();
        step1 = new Step1Objects(driver);
        step1.bookNow();
        step1.searchAddress("sw66af",0);
        step1.bookingDetails();
        step2=new Step2Objects(driver);
        step2.userExisting();
        Thread.sleep(1000);
        Boolean result = driver.findElements(By.linkText("MY ACCOUNT")).size() < 1;
        System.out.println(""+result);
        Thread.sleep(2500);
        logout();

        Assert.assertTrue(result);


    }

    @Test
    public void enterManualAddress() throws InterruptedException {

        navigateToWizard();
        step1 = new Step1Objects(driver);
        step1.bookNow();
        step1.enterManualAddress();
        step1.bookingDetails();
        step2=new Step2Objects(driver);
        Thread.sleep(1500);
        step2.enterPersonalDetails("Test","Bystored","test96"+(Math.random()*100)+"@bystored.com","03329618585");

        step3 = new Step3Objects(driver);
        step3.enterBillingDetails();
        String text = step3.submitDetails();
        logout();
        Assert.assertEquals(""+messageDisplayed,""+text);

    }

    @Test
    public void deliveriesToAreaLimited() throws InterruptedException{

        navigateToWizard();
        step1 = new Step1Objects(driver);
        step1.bookNow();
        step1.searchAddress("123 hastings",2);
        Thread.sleep(2000);
        String messageDisplayed= step1.notAvailableAddress("test@bystored.com","sw66af","03319618585");
        System.out.println(""+messageDisplayed);
        Boolean res;
        if (messageDisplayed.equals("Thanks! We'll be in touch."))
            res= true;
        else
            res=false;
        Assert.assertTrue(res);


    }

    @Test
    public void deliveriesToArea_NegativeCase() throws InterruptedException
    {
        navigateToWizard();
        step1=new Step1Objects(driver);
        step1.bookNow();
        step1.searchAddress("123 hastings",2);
        Thread.sleep(1000);
        String messageDisplayed = step1.notAvailableAddress("test@bystored.com","sw66af",null);

        Assert.assertEquals(""+expectedResult2,""+messageDisplayed);

    }









}
