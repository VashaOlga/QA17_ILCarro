package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;



public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public void type(By locator, String text){
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }

    public void pause(int time){

        time = time*1000;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExpectedMessage(By locator, String message){

        WebDriverWait wait = new WebDriverWait(wd, 10);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(locator)));
        return wd.findElement(locator).getText().contains(message);
    }

    public void takeScreenShot(String link){
        File tmp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File(link);

        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clockOkButton(){
        click(By.xpath("//button[text()='Ok']"));
    }

    public void submitForm(){

        click(By.xpath("//button[@type='submit']"));
    }

    public int random(){
        int i = 0;
        return i = (int) (System.currentTimeMillis() / 1000) % 3600;
    }

}
