package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    EventFiringWebDriver wd;
    HelperUser user;
    HelperRegistration newUser;
    HelperCar car;
    HelperSearch search;

     String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
//        wd = new EventFiringWebDriver(new ChromeDriver());
        if(browser.equals(BrowserType.FIREFOX)){
            wd=new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Test on Firefox");
        }else if(browser.equals(BrowserType.CHROME)){
            wd=new EventFiringWebDriver(new ChromeDriver());
            logger.info("Test on Chrome");
        }
        wd.register(new MyListener());
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.navigate().to("https://ilcarro.web.app/search");
        user = new HelperUser(wd);
        newUser = new HelperRegistration(wd);
        car = new HelperCar(wd);
        search = new HelperSearch(wd);
    }

    public HelperUser getUser() {
        return user;
    }

    public HelperRegistration getNewUser() {
        return newUser;
    }

    public HelperCar getCar() {
        return car;
    }

    public HelperSearch getSearch() {
        return search;
    }

    public void stop() {
//        wd.quit();
    }
}
