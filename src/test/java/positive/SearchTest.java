package positive;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class SearchTest extends base.TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        app.getSearch().click(By.xpath("//a[@id='0']"));

    }

    @Test(groups = {"addition"})
    public void searchTestDaysFromNow() {
        String startDate = LocalDate.now().toString();
        String endDate = String.valueOf(LocalDate.now().plusDays(3));
        app.getSearch().fillSearchForm("Tel-Aviv", startDate,endDate);
        app.getSearch().submitForm();

        Assert.assertTrue(app.getSearch().isElementPresent(By.className("search-results")));
    }

    @Test
    public void searchTestMonthsFromNow() {
        String startDate = LocalDate.now().toString();
        String endDate = String.valueOf(LocalDate.now().plusMonths(3));
        app.getSearch().fillSearchForm("Tel-Aviv", startDate,endDate);
        app.getSearch().submitForm();

        Assert.assertTrue(app.getSearch().isElementPresent(By.className("search-results")));
    }

    @Test
    public void searchTestMonths() {
        String startDate = String.valueOf(LocalDate.now().plusMonths(3));
        String endDate = String.valueOf(LocalDate.now().plusMonths(7));
        app.getSearch().fillSearchForm("Tel-Aviv", startDate,endDate);
        app.getSearch().submitForm();

        Assert.assertTrue(app.getSearch().isElementPresent(By.className("search-results")));
    }

    @Test
    public void searchTestMonthsYears() {
        String startDate = String.valueOf(LocalDate.now().plusMonths(10));
        String endDate = String.valueOf(LocalDate.now().plusMonths(11).plusDays(27));
        app.getSearch().fillSearchForm("Tel-Aviv", startDate,endDate);
        app.getSearch().submitForm();

        Assert.assertTrue(app.getSearch().isElementPresent(By.className("search-results")));
    }

    @AfterMethod
    public void tearDown(){

    }
}
