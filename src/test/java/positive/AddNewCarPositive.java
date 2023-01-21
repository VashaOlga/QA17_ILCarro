package positive;

import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewCarPositive extends base.TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){

        if(!app.getUser().isLogged()){
            app.getUser().login(User.builder().email("612test@mail.com").password("1425Asd@").build());
            app.getUser().clockOkButton();

        }
    }

    @Test(groups = {"addition"})
    public void addNewCarPositiveTest(){
        Car car = Car.builder()
                .address("Bat-Yam")
                .make("KIA")
                .model("Sportage")
                .year("2020")
                .fuel("Petrol")
                .seats("4")
                .carClass("5")
                .carRegNumber("100-200-" + app.getCar().random())
                .price("150")
                .build();

        app.getCar().openCarForm();
        app.getCar().fillCarForm(car);
        app.getCar().submitForm();

        Assert.assertTrue(app.getCar().isCarAdded());
        logger.info("Car added with: \n" + car.toString());

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){

        if(app.getCar().isCarAdded()){
            app.getCar().click(By.xpath("//button[text() = 'Add another car']"));
        }
    }
}
