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
public class RegistrationNegative extends base.TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){

        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test(groups = {"wrongEmail"})
    public void registrationExistUserTest(){

        User user = User.builder().name("Ted"+app.getUser().random())
                .lastName("Smith"+app.getUser().random())
                .email("612test@mail.com")
                .password("1425Asd@")
                .build();

        app.getNewUser().openRegistrationForm();
        app.getNewUser().fillRegistrationForm(user);
        app.getNewUser().submitForm();

        app.getNewUser().pause(3);
        Assert.assertTrue(app.getNewUser().isExpectedMessage(By.cssSelector(".dialog-container"), "User already exists"));

    }

    @Test(groups = {"wrongEmail"})
    public void registrationWrongEmailWithoutStrudelTest(){

        User user = User.builder()
                .name("Ted"+app.getUser().random())
                .lastName("Smith"+app.getUser().random())
                .email(app.getUser().random()+"testmail.com")
                .password(app.getUser().random()+"Zxcvb@")
                .build();
        app.getNewUser().openRegistrationForm();
        app.getNewUser().fillRegistrationForm(user);
        app.getNewUser().submitForm();

        Assert.assertTrue(app.getNewUser().isElementPresent(By.xpath("//div[text()='Wrong email format']")));
        Assert.assertFalse(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']")));
    }

    @Test
    public void registrationWrongPasswordWithoutSymbolTest() {
        User user = User.builder()
                .name("Ted"+app.getUser().random())
                .lastName("Smith"+app.getUser().random())
                .email(app.getUser().random()+"test@mail.com")
                .password(app.getUser().random()+"Zxcvb")
                .build();
        app.getNewUser().openRegistrationForm();
        app.getNewUser().fillRegistrationForm(user);
        app.getNewUser().submitForm();

        Assert.assertTrue(app.getNewUser().isElementPresent(
                By.xpath("//div[text()='Password must contain 1 uppercase letter," +
                        " 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]']")));
        Assert.assertFalse(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']")));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']"))) {
            app.getUser().clockOkButton();
        }
    }
}
