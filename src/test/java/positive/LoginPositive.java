package positive;

import manager.NGListener;
import manager.ProviderData;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NGListener.class)
public class LoginPositive extends base.TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){

        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

//    @Test(dataProvider = "loginModelDto",dataProviderClass = ProviderData.class)
    @Test(groups = {"auth"})
//    public void loginPositiveTest(User user){
    public void loginPositiveTest(){

        User user = User.builder().email("612test@mail.com").password("1425Asd@").build();
        app.getUser().openLoginForm();
        logger.info("User: " + user.toString());
        app.getUser().login(user);

        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']"))) {
            app.getUser().clockOkButton();
        }
    }
}
