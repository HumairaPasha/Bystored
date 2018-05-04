package Testcases;

import General.MainClass;
import Objects.ContactUsObjects;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;

public class ContactUs extends MainClass {

    public String firstName = "test";
    public String lastName = "venturedive";
    public String email = "humaira.pasha@venturedive.com";
    public String phoneNumber = "03218529696";
    public String message = "test message";
    public String inquiryOptions = "Removals enquiry";

    public static ContactUsObjects contactUs;
    String expectedText = "Thank you for your enquiry. We will get back to you within one business day.";




    @Test
    public void contactUs () throws InterruptedException {

        driver.navigate().to(baseURL+"/contact-us");
        contactUs = new ContactUsObjects(driver,wait);
        contactUs.enterInformation(firstName,lastName,email,phoneNumber);
        contactUs.selectInquiry(inquiryOptions);
        contactUs.enterMessage(message);
        contactUs.submitInformation();
     //   Thread.sleep(3000);



        String close = contactUs.close();
        System.out.println("result: "+close);
        System.out.println("expectedText: "+expectedText);
        Assert.assertEquals(""+expectedText,""+close);






    }

}
