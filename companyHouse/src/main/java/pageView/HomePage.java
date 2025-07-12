package pageView;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static utils.helpers.waitForElementVisible;
import static utils.helpers.waitForSeconds;

public class HomePage {
    @FindBy(tagName="h1")
    public WebElement welcome;

    @FindBy(tagName="h2")
    public WebElement ourRoomSection;

    @FindBy(tagName="h3")
    public WebElement checkAvailability;

    @FindBy(xpath="//a[contains(text(), 'Book Now')]")
    public WebElement bookNowBtn;

    @FindBy(xpath="//label[text()= \"Check In\"]/parent::div/div/div/input")
    public WebElement checkInDate;

    @FindBy(xpath="//label[text()= \"Check Out\"]/parent::div/div/div/input")
    public WebElement checkOutDate;

    @FindBy(xpath="//button[text()='Check Availability']")
    public WebElement checkAvailabilityButton;

    @FindBy(xpath="//h5[text()=\"Single\"]/../following-sibling::div/a[text()=\"Book now\"]")
    public WebElement chooseSingleRoom;

    @FindBy(xpath="//h5[@class=\"card-title\"]")
    public List<WebElement> roomTitleList;

    @FindBy(xpath="//h2[contains(text(), 'Our Location')]")
    public WebElement ourLocation;


    WebDriver driver;

    public HomePage(WebDriver driver){

        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
        
    public String getWelcomeMessage() throws InterruptedException {
        waitForElementVisible(driver, welcome,5);
       String message= welcome.getText();
       System.out.println("verify message"+message);
       return message;
    }

    public void tapBookNowButton() throws InterruptedException {
        waitForElementVisible(driver, bookNowBtn,5);
        bookNowBtn.click();
    }

    public String getOurRoomsText(){
        String ourRoomText= ourRoomSection.getText();
        System.out.println("verify message"+ourRoomText);
        return ourRoomText;
    }

    public String getCheckAvailabilityText(){
        String checkAvailabilityText= checkAvailability.getText();
        System.out.println("verify message"+checkAvailabilityText);
        return checkAvailabilityText;
    }

    public void setCheckInDate(String startDate) throws InterruptedException {
        waitForElementVisible(driver, checkInDate,5);
        for (int i = 0; i < 10; i++) {
            checkInDate.sendKeys(Keys.BACK_SPACE);
        }
        checkInDate.sendKeys(startDate);
    }

    public void setCheckOutDate(String endDate) throws InterruptedException {
        waitForElementVisible(driver, checkOutDate,5);
        for (int i = 0; i < 10; i++) {
            checkOutDate.sendKeys(Keys.BACK_SPACE);
        }
        checkOutDate.sendKeys(endDate);
    }

    public void clickCheckAvailabilityButton() throws InterruptedException {
        waitForElementVisible(driver, checkAvailabilityButton,5);
        checkAvailabilityButton.click();
    }

    public void chooseAndBookSingleRoom() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        waitForElementVisible(driver, chooseSingleRoom,5);
        js.executeScript("arguments[0].scrollIntoView(false);", chooseSingleRoom);
       waitForSeconds(5);
        chooseSingleRoom.click();
    }

    public List<String> getRoomTypes(){
        waitForElementVisible(driver, chooseSingleRoom,5);
        List<String> actualRoomTitle= new ArrayList<>();
        for (WebElement roomTitle : roomTitleList) {
            actualRoomTitle.add(roomTitle.getText().trim());
        }
        return actualRoomTitle;
    }

    }
