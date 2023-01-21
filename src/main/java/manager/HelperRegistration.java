package manager;

import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HelperRegistration extends HelperBase{

    public HelperRegistration(WebDriver wd) {
        super(wd);
    }

    public void openRegistrationForm(){
        click(By.xpath("//*[@href='/registration?url=%2Fsearch']"));
    }

    public void fillRegistrationForm(User data){
        type(By.xpath("//input[@id='name']"), data.getName());
        type(By.xpath("//input[@id='lastName']"), data.getLastName());
        type(By.xpath("//input[@id='email']"), data.getEmail());
        type(By.xpath("//input[@id='password']"), data.getPassword());
//        click(By.xpath("//div[@class='checkbox-container']"));
//        click(By.xpath("//input[@class='ng-dirty ng-touched ng-valid']"));
        checkPolicy();

    }

    public void checkPolicy() {
        if (!isElementPresent(By.xpath("//input[@class='ng-dirty ng-touched ng-valid']"))) {
//            variant 1
//            click(By.xpath("//div[@class='checkbox-container']"));
//            variant 2
//            JavascriptExecutor js = (JavascriptExecutor) wd;
//            js.executeScript("document.querySelector('#terms-of-use').click();");
//            variant 3
            Rectangle rect = wd.findElement(By.cssSelector(".checkbox-container")).getRect();
            int x = rect.getX() + 5;
            int y = rect.getY() + 1 / 4 * rect.getHeight();
            Actions actions = new Actions(wd);
            actions.moveByOffset(x, y).click().perform();
        }
    }
//    public void submitRegistration() {
//        click(By.xpath("//button[@type='submit']"));
//    }

    public boolean isRegistrationSuccess(){
        return isExpectedMessage(By.cssSelector(".dialog-container"),"success");
    }
}
