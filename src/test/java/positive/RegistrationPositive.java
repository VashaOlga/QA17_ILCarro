package positive;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationPositive extends base.TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){

        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test(groups = {"auth"})
    public void registrationPositiveTest(){

//        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
//        User user = User.builder().name("Ted"+i).lastName("Smith"+i).email(i+"test@mail.com").password(i+"Zxcvb@").build();
        User user = User.builder()
                .name("Ted"+app.getUser().random())
                .lastName("Smith"+app.getUser().random())
                .email(app.getUser().random()+"test@mail.com")
                .password(app.getUser().random()+"Zxcvb@")
                .build();
        logger.info("registrationPositiveTest with email: " + user.getEmail()
                + " password: " + user.getPassword());
        app.getNewUser().openRegistrationForm();
        app.getNewUser().fillRegistrationForm(user);
        app.getNewUser().submitForm();

        Assert.assertTrue(app.getNewUser().isRegistrationSuccess());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if(app.getUser().isElementPresent(By.xpath("//button[text()='Ok']"))) {
            app.getUser().clockOkButton();
        }
    }
}
