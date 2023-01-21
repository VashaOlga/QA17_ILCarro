package negative;

import manager.NGListener;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NGListener.class)
public class LoginNegativeTests extends base.TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){

        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    @Test(groups = {"wrongEmail"})
    public void loginWrongEmailTest() {

        User user = User.builder().email("612test.mail.com").password("1425Asd@").build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitForm();


        Assert.assertFalse(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']")));
    }

    @Test
    public void loginWrongPasswordTest() {

        User user = User.builder().email("612test@mail.com").password("1425Asd").build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitForm();

        Assert.assertFalse(app.getUser().isLogged());
        Assert.assertTrue(app.getUser().isExpectedMessage(By.cssSelector(".dialog-container"),"Login failed"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']"))) {
            app.getUser().clockOkButton();
        }
    }

}
