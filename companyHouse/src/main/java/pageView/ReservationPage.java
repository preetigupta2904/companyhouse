package pageView;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utils.helpers.waitForElementVisible;
import static utils.helpers.waitForSeconds;

public class ReservationPage {

    @FindBy(xpath="//div[@title=\"Selected\"]")
    public WebElement rangeSelected;

    @FindBy(xpath="//div[@title=\"Selected\"]/../../../../div/div/button[@class=\"rbc-button-link\"]")
    public WebElement rowSelected;

    @FindBy(tagName="h1")
    public WebElement singleRoomTitle;

    @FindBy(xpath="//button[text()=\"Reserve Now\"]")
    public WebElement reserveNowButton;

    @FindBy(name="firstname")
    public WebElement firstName;

    @FindBy(name="lastname")
    public WebElement lastName;

    @FindBy(name="email")
    public WebElement email;

    @FindBy(name="phone")
    public WebElement phoneNumber;

    @FindBy(xpath="//h2[text()=\"Booking Confirmed\"]/../p[2]/strong")
    public WebElement bookingConfirmDate;

    @FindBy(xpath="//h2[contains(text(), 'Room Description')]")
    public WebElement roomDescription;

    @FindBy(xpath="//h2[contains(text(), 'Room Features')]")
    public WebElement roomFeatures;

    @FindBy(xpath="//h2[contains(text(), 'Room Policies')]")
    public WebElement roomPolicies;

    @FindBy(xpath="//h3[contains(text(), 'Price Summary')]")
    public WebElement priceSummary;


    WebDriver driver;

    public ReservationPage(WebDriver driver){

        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public boolean selectedRange(){
        rangeSelected.isDisplayed();
        return true;
    }

    public String getFirstDayOfWeek(){
        String date= rowSelected.getText();
        System.out.println("verify message"+date);
        return date;
    }

    public String getSingleRoomTitle() throws InterruptedException {
        //Thread.sleep(5000);
        waitForElementVisible(driver, singleRoomTitle,5);
        String title= singleRoomTitle.getText();
        System.out.println("verify message"+title);
        return title;
    }

    public void clickReserveNowButton() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", reserveNowButton);
        reserveNowButton.isDisplayed();
        waitForSeconds(5);
        waitForElementVisible(driver, reserveNowButton,5);
        reserveNowButton.click();
    }

    public void enterDetailsAndReserve(String FName, String LName, String userEmail, String phone) throws InterruptedException {
        clickReserveNowButton();
        firstName.sendKeys(FName);
        lastName.sendKeys(LName);
        email.sendKeys(userEmail);
        phoneNumber.sendKeys(phone);
        reserveNowButton.click();
    }

    public String bookingConfirmationMessage() throws InterruptedException {
        //Thread.sleep(5000);
        waitForElementVisible(driver, bookingConfirmDate,5);
       String bookingDate =  bookingConfirmDate.getText();
       return bookingDate;
    }

}
