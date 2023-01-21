package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.time.LocalDate;

public class HelperSearch extends HelperBase {

    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public String selectDay(String selectDate) {
        String[] days = selectDate.split("-");
        int day = Integer.parseInt(days[2]);
        String locatorDay = String.format("//div[.= ' %d ']", day);
        return locatorDay;
    }

    public int selectMonth(String selectDate) {
        String[] date = selectDate.split("-");
        int month = Integer.parseInt(date[1]);
        return month;
    }

    public int selectYear(String selectDate) {
        String[] years = selectDate.split("-");
        int year = Integer.parseInt(years[0]);
        return year;
    }

    public void fillSearchForm(String city, String dateFrom, String dateTo) {
        fillCity(city);
        click(By.id("dates"));

        if(selectYear(dateFrom)!=selectYear(dateTo)){
            fillPeriodYears(dateFrom,dateTo);
        }else {
            selectPeriodMonths(dateFrom,dateTo);
        }

    }

    public void startCountingMonth(String dateFrom){
        int nowToStartMonth = 0;
        if (LocalDate.now().getMonthValue() != selectMonth(dateFrom)) {
            nowToStartMonth = selectMonth(dateFrom) - LocalDate.now().getMonthValue();
        }
        for (int i = 0; i < nowToStartMonth; i++) {
            clickNextMonth();
        }
    }
    private void selectPeriodMonths(String dateFrom, String dateTo) {

        startCountingMonth(dateFrom);
        click(By.xpath(selectDay(dateFrom)));

        startCountingMonth(dateTo);
        click(By.xpath(selectDay(dateTo)));

    }

    public void clickNextMonth() {
        click(By.xpath(("//button[@aria-label='Next month']")));
    }

    private void fillPeriodYears(String dateFrom, String dateTo) {

        startCountingMonth(dateFrom);
        click(By.xpath(selectDay(dateFrom)));

        int countOfClicks = 12;
        if(selectMonth(dateFrom)>selectMonth(dateTo)){
            countOfClicks = countOfClicks-selectMonth(dateFrom)+selectMonth(dateTo);
        }
        for (int i=0; i<countOfClicks;i++){
            clickNextMonth();
        }

        click(By.xpath(selectDay(dateTo)));


    }


    private void fillCity(String city) {
        type(By.id("city"), city);
        pause(3);
        click(By.cssSelector("div.pac-item"));

    }

}
