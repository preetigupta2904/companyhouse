package uiTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageView.HomePage;
import pageView.ReservationPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static utils.helpers.getDate;

public class Tests_homePage extends BaseTestUi {
    HomePage homepageobj;
    ReservationPage reservationpageobj;
    User testUser = ReadUserData.loadUserFromFile("src/main/resources/user.json");


   @Test(groups={"Regression"},priority=0,testName="TC-001 - Verify Home Page Opened Properly",description="Test verifies the Home content")
    public void VerifyHomePageContents() throws InterruptedException {
        test = extent.createTest("TC-001 - Verify Home Page Opened Properly");
        homepageobj = new HomePage(driver);
        String welcome_Message = "Welcome to ";
        String welcome_Message2 = "Shady Meadows B&B";
        String message =  homepageobj.getWelcomeMessage();
        Assert.assertTrue(message.equals(welcome_Message+welcome_Message2));
    }

    @Test(groups={"Regression"}, priority=1,testName="TC-002 - Verify Book Now Button Functionality",description="Test verifies that user is taken to Our Rooms section when he clicks Book Now")
    public void VerifyBookNowTakesUserToOurRoomsSection() throws InterruptedException {
        test = extent.createTest("TC-002 - Verify Book Now Button Functionality");
       homepageobj = new HomePage(driver);
        homepageobj.tapBookNowButton();
        String ourRoomText= homepageobj.getOurRoomsText();
        Assert.assertTrue(ourRoomText.equals("Our Rooms"));
    }

    @Test(groups={"Regression"},priority=3, testName="TC-003 - Verify Check Availability & Book Your Stay section ",description="Test verifies that user is able to view Single, Double and Suite Rooms")
    public void verifyUserIsAbleToViewThreeRoomTypes() throws InterruptedException {
        test = extent.createTest("TC-003 - Verify Check Availability & Book Your Stay section");
       homepageobj = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        reservationpageobj = new ReservationPage(driver);

        homepageobj.tapBookNowButton();
        String ourRoomText = homepageobj.getOurRoomsText();
        Assert.assertTrue(ourRoomText.equals("Our Rooms"));
        List<String> actualRoomTitle = homepageobj.getRoomTypes();
        List<String> expectedRoomTitle = Arrays.asList("Single", "Double", "Suite");
        Assert.assertEquals(actualRoomTitle, expectedRoomTitle, "Titles do not match!");
    }

    @Test(groups={"E2E"}, priority=4, testName="TC-004 - verify User Is Able To Book Rooms for selected Date Range",description="Test verifies that user is able to book Room for selected dates")
    public void verifyUserIsAbleToBookRoomForDateRange() throws InterruptedException {
        test = extent.createTest("TC-004 - verify User Is Able To Book Rooms for selected Date Range");
       homepageobj = new HomePage(driver);
        reservationpageobj = new ReservationPage(driver);

        homepageobj.tapBookNowButton();
        SoftAssert softAssert = new SoftAssert();
        String checkAvailabilityText= homepageobj.getCheckAvailabilityText();
        softAssert.assertEquals(checkAvailabilityText,"Check Availability & Book Your Stay","Text Matched");

        LocalDate today = getDate();
        LocalDate threeDays = today.plusDays(3);
        LocalDate sixDays = today.plusDays(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String startDate = threeDays.format(formatter);
        String endDate = sixDays.format(formatter);
        homepageobj.setCheckInDate(String.valueOf(startDate));
        homepageobj.setCheckOutDate(String.valueOf(endDate));

        homepageobj.clickCheckAvailabilityButton();
        String ourRoomText= homepageobj.getOurRoomsText();
        Assert.assertTrue(ourRoomText.equals("Our Rooms"));
        homepageobj.chooseAndBookSingleRoom();

        String title=reservationpageobj.getSingleRoomTitle();
        softAssert.assertEquals(title,"Single Room");
        String url = driver.getCurrentUrl();
        System.out.println("url for date:"+ driver.getCurrentUrl());
        Assert.assertTrue(url.contains("checkin="+threeDays.format(formatter1)));
        Assert.assertTrue(url.contains("checkout="+sixDays.format(formatter1)));
        Assert.assertTrue(reservationpageobj.selectedRange());
        reservationpageobj.enterDetailsAndReserve(testUser.getFirstName(), testUser.getLastName(), testUser.getEmail(),testUser.getPhone());
        String bookingDate=reservationpageobj.bookingConfirmationMessage();
        Assert.assertEquals(bookingDate,threeDays.format(formatter1)+" - "+sixDays.format(formatter1));
    }

    @Test(groups={"Regression"}, priority=5, testName="TC-005 - Verify that Reservation page shows correct details",description="Test verifies that user is able to view Details of the Rooms")
    public void verifyUserIsAbleToViewRoomDetails() throws InterruptedException {
        test = extent.createTest("TC-005 - Verify that Reservation page shows correct details");
        homepageobj = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        reservationpageobj = new ReservationPage(driver);

        homepageobj.tapBookNowButton();
        String ourRoomText= homepageobj.getOurRoomsText();
        Assert.assertTrue(ourRoomText.equals("Our Rooms"));
        homepageobj.chooseAndBookSingleRoom();

        String title=reservationpageobj.getSingleRoomTitle();

        Assert.assertEquals(title,"Single Room");
        Assert.assertTrue(reservationpageobj.roomFeatures.isDisplayed());
        Assert.assertTrue(reservationpageobj.roomPolicies.isDisplayed());
        Assert.assertTrue(reservationpageobj.priceSummary.isDisplayed());
    }

}
