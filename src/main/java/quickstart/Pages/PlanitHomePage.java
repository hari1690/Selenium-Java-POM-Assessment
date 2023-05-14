package quickstart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constants.TimeOuts;

public class PlanitHomePage {

	WebDriver driver;

	@FindBy(xpath = "//li/a[@href='#/home']")
	public WebElement homePageLink;

	@FindBy(xpath = "//li/a[@href='#/shop']")
	public WebElement shopPageLink;

	@FindBy(xpath = "//li/a[@href='#/contact']")
	public WebElement ContactPageLink;
	
	@FindBy(xpath = "//*[contains(text(),'Start Shopping')]")
	public WebElement StartShoppingButton;

	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBox;

	@FindBy(xpath = "//div[@class='g']//h3/span")
	WebElement firstResult;

	/**
	 * Constructor of the Page class to set the driver. It is also used to
	 * initialize all the elements.
	 * 
	 * @param driver
	 */
	public PlanitHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void contactPageTopLinkAccess() {

		ContactPageLink.click();

	}

	public void clickStartShopping() {
		StartShoppingButton.click();
	}
	

	public void waitForResultLink(String keyword) {
		String searchResultLink = "//h3[contains(.,'" + keyword + "')]";
		WebDriverWait wait = new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchResultLink)));
	}

}
