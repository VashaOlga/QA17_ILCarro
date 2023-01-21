package base;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    public static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

     public Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        app.init();
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method m){
        logger.info("Start test " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void stopTest(Method m){
        logger.info("The enf of test " + m.getName());
    }
    @AfterSuite(alwaysRun = true)
    public void tearDown() {

//        app.stop();
    }
}
