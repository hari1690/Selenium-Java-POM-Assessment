package quickstart.Pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constants.TimeOuts;

public class PlanitContactPage {

	WebDriver driver;

	By foreName = By.xpath("//input[@id='forename']");
	By SubmitButton = By.xpath("//*[contains(text(),'Submit')]");
	By BackButton = By.xpath("//a[text()='« Back']");

	String forenameError = "Forename is required";
	String emailError = "Email is required";
	String messageError = "Message is required";

	@FindBy(xpath = "//*[contains(text(),'Submit')]")
	public WebElement submitButton;

	@FindBy(xpath = "//input[@id='forename']")
	public WebElement foreNameField;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement emailField;

	@FindBy(xpath = "//textarea[@id='message']")
	public WebElement messageField;

	public PlanitContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSubmitbutton() {
		isElementDisplayed(SubmitButton);
		submitButton.click();
	}

	public boolean isSubmitElementDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		WebElement ele = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Submit')]")));
		return ele.isDisplayed();
	}

	public boolean isElementDisplayed(By xpath) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
		return ele.isDisplayed();
	}

	public void backButtonVerification() {
		isElementDisplayed(BackButton);
	}

	public boolean IsTextShowingUp(String elementName) {

		String message = "//*[contains(text(),'" + elementName + "')]";
		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(message)));
		return ele.isDisplayed();
	}

	public boolean IsElementShowingUp(String message) throws Exception {

		int ele = driver.findElements(By.xpath("//*[contains(text(),'" + message + "')]")).size();
		if (ele > 0) {
			return false;
		} else {
			return true;
		}

	}

	public boolean IsTextNotShowingUp(String message) throws Exception {
		try {
			driver.findElement(By.xpath("//*[contains(text(),'" + message + "')]"));
			System.out.println("Element Present");
			return false;

		} catch (NoSuchElementException e) {
			System.out.println("Element absent");
			return true;
		}
	}

	public void fillTheMandatoryFieldsWithoutSubmitting() {
		isElementDisplayed(foreName);
		foreNameField.sendKeys("TestUser");
		emailField.sendKeys("test@gmail.com");
		messageField.sendKeys("test message");

	}

	public void submitContactForm() {
		fillTheMandatoryFieldsWithoutSubmitting();
		clickSubmitbutton();

	}

	public void isContactPageFieldValidationErrorShowingUp(String error1) {

		IsTextShowingUp(error1);

	}

	public boolean isLinkDisplayed(String elementName) {

		String searchResultLink = "//h3[contains(.,'" + elementName + "')]";
		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultLink)));
		return ele.isDisplayed();
	}

}
