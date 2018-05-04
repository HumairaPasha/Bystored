package General;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.lang.reflect.Method;
//import static General.InitMethods.Environment;

public class MainClass {
    public static ChromeDriver driver;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static String baseURL="http://qa1.bystored.com/";
    public static WebDriverWait wait;


    @BeforeSuite
    public void startReport() {
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/test-output/BystoredAutomation.html", true);
        extentReports
                .addSystemInfo("Host Name", "Bystored")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Humaira Pasha");
        extentReports.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
    }


    @BeforeMethod()
    public void StartDriver(Method method) {
       // driver = WebDriverFactory.getInstance();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Humaira Pasha\\Desktop\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);

        extentTest = extentReports.startTest(method.getName(), "");
    }


    @AfterMethod(alwaysRun = true)
    public void QuitDriver(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(LogStatus.FAIL, "Test Case Failed reason is: " + result.getThrowable());
           // extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(Screenshots.takeScreenshot(result.getMethod().getMethodName())));

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(LogStatus.SKIP, "Test Case Skipped is: " + result.getName());
        } else {
            extentTest.log(LogStatus.PASS, result.getMethod().getMethodName() + " is Passed");
        }

        extentReports.endTest(extentTest);
        driver.quit();
    }

    @AfterSuite
    public void endReport() {
        extentReports.flush();
        extentReports.close();
    }
}

