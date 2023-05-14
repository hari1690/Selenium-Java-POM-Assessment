package quickstart.Tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import functionLibrary.ReportLog;
import functionLibrary.TestData;
import io.github.bonigarcia.wdm.WebDriverManager;
import quickstart.Pages.PlanitContactPage;
import quickstart.Pages.PlanitHomePage;
import quickstart.Pages.PlanitShoppingPage;

public class TestCases{
	WebDriver driver = null;
	PlanitHomePage planitHomePage;
	PlanitContactPage planitContactPage;
	PlanitShoppingPage planitShoppingPage;

	ReportLog logger;

	TestData testdata;

	@BeforeSuite
	public void startTestSuite() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger = new ReportLog(driver);
		String currDir = System.getProperty("user.dir");
		try {
			testdata = new TestData(currDir + "\\TestData\\searchData.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Issue in Test data file opening; you can do some operation here, may be exit test");
		}
	}

	@BeforeMethod
	public void startTest(Method m) {
		logger.startTest(m.getName());
		driver.get("http://jupiter.cloud.planittesting.com");
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void endTest() {
		logger.endTest();

	}

	@AfterSuite
	public void endTestSuite() {
		logger.endTestSuite();
		driver.quit();
	}

	@Test
	public void contactFormErrorValidation() throws Exception {

		planitHomePage = new PlanitHomePage(driver);
		planitContactPage = new PlanitContactPage(driver);

		planitHomePage.contactPageTopLinkAccess();
		planitContactPage.clickSubmitbutton();

		// Error message validation before filling the field
		if (planitContactPage.IsTextShowingUp("Forename is required") == true) {
			logger.logPass("ForeName Error is displayed on Contact Page");
		} else {
			logger.logFail("ForeName Error is not displayed on Contact Page");
		}
		if (planitContactPage.IsTextShowingUp("Email is required") == true) {
			logger.logPass("Email Error is displayed on Contact Page");
		} else {
			logger.logFail("Email Error is not displayed on Contact Page");
		}
		if (planitContactPage.IsTextShowingUp("Message is required") == true) {
			logger.logPass("Message Error is displayed on Contact Page");
		} else {
			logger.logFail("Message Error is not displayed on Contact Page");
		}

		planitContactPage.fillTheMandatoryFieldsWithoutSubmitting();

		// Error message validation after filling up the fields
		if (planitContactPage.IsElementShowingUp("Forename is required") == true) {
			logger.logPass("ForeName Error is not displayed on Contact Page after filling the field");
		} else {
			logger.logFail("ForeName Error is displayed on Contact Page after filling the field");
		}

		if (planitContactPage.IsElementShowingUp("Email is required") == true) {
			logger.logPass("Email Error is not displayed on Contact Page after filling the field");
		} else {
			logger.logFail("Email Error is displayed on Contact Page after filling the field");
		}
		if (planitContactPage.IsElementShowingUp("Message is required") == true) {
			logger.logPass("Message Error is not displayed on Contact Page after filling the field");
		} else {
			logger.logFail("Message Error is displayed on Contact Page after filling the field");
		}

		System.out.println("Testcase - Contact form error message validation is successfully passed");

	}

	@Test(invocationCount = 5)
	public void FormSubmissionTest() throws IOException {

		planitHomePage = new PlanitHomePage(driver);
		planitContactPage = new PlanitContactPage(driver);
		planitHomePage.contactPageTopLinkAccess();
		planitContactPage.fillTheMandatoryFieldsWithoutSubmitting();
		planitContactPage.clickSubmitbutton();

		// Success message validation after submitting the form
		planitContactPage.backButtonVerification();
		if (planitContactPage.IsTextShowingUp("Thanks") == true) {
			logger.logPass("Success message is displayed on Contact Page");
		} else {
			logger.logFail("Success message is not displayed on Contact Page");
		}

		System.out.println("Testcase - Contact form Submitted and success message validation is successfully passed");

	}

	@Test
	public void testCase3() throws IOException, InterruptedException {

		planitHomePage = new PlanitHomePage(driver);
		planitShoppingPage = new PlanitShoppingPage(driver);

		planitHomePage.clickStartShopping();
		planitShoppingPage.AddProductsToCart();
		planitShoppingPage.productsValueVerification();

	}

}
