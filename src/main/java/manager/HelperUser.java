package manager;

import com.google.common.io.Files;
import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class HelperUser extends HelperBase{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm(){
        click(By.xpath("//*[@href='/login?url=%2Fsearch']"));
    }

    public void login(User user) {

        openLoginForm();
        fillLoginForm(user);
//        submitLogin();
        submitForm();
        pause(5);
//        clockOkButton();

    }
    public void fillLoginForm(User data){
        type(By.xpath("//input[@id='email']"), data.getEmail());
        type(By.xpath("//input[@id='password']"), data.getPassword());
    }

//    public void submitLogin() {
//        click(By.xpath("//button[@type='submit']"));
//    }

    public boolean isLogged(){
        return isElementPresent(By.xpath("//a[text()=' Logout ']"));
    }

    public boolean isLoggedSuccess(){
//        WebDriverWait wait = new WebDriverWait(wd, 10);
//        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".dialog-container"))));
//        return wd.findElement(By.cssSelector(".dialog-container")).getText().contains("success");

        return isExpectedMessage(By.cssSelector(".dialog-container"),"success");
    }

    public void logout(){
        click(By.xpath("//a[text()=' Logout ']"));
    }



}
