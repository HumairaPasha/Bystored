package Testcases;

import General.MainClass;
import Objects.StudentStorageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentStorage extends MainClass{




    StudentStorageObjects student;
    String closetUnit = "25 sq ft unit";
    public static int i=0;
    public static List<String> values;
    public static String URL = "http://qa1.bystored.com/start-storing?self_storage_unit_selected=12&promotion=1&move_to_step=2#step/2";

    //check if particular increments give defined storage unit i.e. storage calculator works fine
    @Test
    public void studentStorage() throws InterruptedException
    {
        driver.navigate().to("http://qa1.bystored.com/student-storage?");
        student = new StudentStorageObjects(driver);
        String res = student.calculateStorage();
        Assert.assertEquals(""+closetUnit,""+res);
    }

    //check whether same storage unit is displayed in url as selected
    @Test
    public void bookStorage() throws InterruptedException
    {
        driver.navigate().to(baseURL+"/student-storage?");
        driver.manage().window().maximize();
        student = new StudentStorageObjects(driver);
        String res = student.calculateStorage();
        String unit = res.split("\\s+")[0]; //unit on the display
       // Thread.sleep(3500);
        String checkAvailability = "#detail-area-"+unit+" > div.card__back > div > button";
        WebElement element = driver.findElement(By.cssSelector(checkAvailability));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        //Thread.sleep(4500);
        driver.findElement(By.cssSelector(checkAvailability)).click();
        //Thread.sleep(3000);
        String result = getQueryParams(driver.getCurrentUrl());
        Assert.assertEquals(unit,result);



    }

    public static String getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {

                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {

                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                    if (i==0)
                        return value; //return first parameter
                    //  System.out.println(values);
                    i++;
                }
            }

            //System.out.println(""+values.size());
            return values.get(1);
        } catch (Exception ex) {
            throw new AssertionError(ex);
        }
    }
}
