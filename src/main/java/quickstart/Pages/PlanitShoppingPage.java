package quickstart.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Constants.TimeOuts;

public class PlanitShoppingPage {

	WebDriver driver;

	@FindBy(xpath = "//div/h4[contains(text(),'Teddy Bear')]")
	public WebElement teddyBearText;

	@FindBy(xpath = "//li[@id='product-1']/div/p/a")
	public WebElement TeddyBear;

	@FindBy(xpath = "//div/h4[contains(text(),'Stuffed Frog')]")
	public WebElement stuffedFrogText;

	@FindBy(xpath = "//li[@id='product-2']/div/p/a")
	public WebElement StuffedFrog;

	@FindBy(xpath = "//div/h4[contains(text(),'Handmade Doll')]")
	public WebElement handmadeDollText;

	@FindBy(xpath = "//li[@id='product-3']/div/p/a")
	public WebElement HandmadeDoll;

	@FindBy(xpath = "//div/h4[contains(text(),'Fluffy Bunny')]")
	public WebElement fluffyBunnyText;

	@FindBy(xpath = "//li[@id='product-4']/div/p/a")
	public WebElement FluffyBunny;

	@FindBy(xpath = "//div/h4[contains(text(),'Smiley Bear')]")
	public WebElement smileyBearText;

	@FindBy(xpath = "//li[@id='product-5']/div/p/a")
	public WebElement SmileyBear;

	@FindBy(xpath = "//div/h4[contains(text(),'Funny Cow')]")
	public WebElement funnyCowText;

	@FindBy(xpath = "//li[@id='product-6']/div/p/a")
	public WebElement FunnyCow;

	@FindBy(xpath = "//div/h4[contains(text(),'Valentine Bear')]")
	public WebElement valentineBearText;

	@FindBy(xpath = "//li[@id='product-7']/div/p/a")
	public WebElement ValentineBear;

	@FindBy(xpath = "//div/h4[contains(text(),'Smiley Face')]")
	public WebElement smileyFaceText;

	@FindBy(xpath = "//li[@id='product-8']/div/p/a")
	public WebElement SmileyFace;

	@FindBy(xpath = "//li[@id='nav-cart']")
	public WebElement Cart;

	@FindBy(xpath = "//li/a[@href='#/shop']")
	public WebElement shopPageLink;

	@FindBy(xpath = "//li/a[@href='#/contact']")
	public WebElement ContactPageLink;

	@FindBy(xpath = "//*[contains(text(),'Start Shopping')]")
	public WebElement StartShoppingButton;
	
	By TeddyBearXpath = By.xpath("//li[@id='product-1']/div/p/a");
	By CartpageFirstXpath = By.xpath("//table/tbody/tr/td[2]");

	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBox;


	/**
	 * Constructor of the Page class to set the driver. It is also used to
	 * initialize all the elements.
	 * 
	 * @param driver
	 */
	public PlanitShoppingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void contactPageTopLinkAccess() {
		ContactPageLink.click();
	}

	public void clickStartShopping() {
		StartShoppingButton.click();	
	}
	
	public boolean isElementDisplayed(By xpath) {
		WebDriverWait wait= new WebDriverWait(driver, TimeOuts.DEFAULT_TIMEOUT);
		WebElement ele= wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
		return ele.isDisplayed();
	}

	public void clickCart() {
		Cart.click();
	}

	public void buyProducts(WebElement element, int numberOfProducts) {

		for (int i = 0; i <= numberOfProducts-1; i++) {
			element.click();

		}

	}

	public void AddProductsToCart() throws InterruptedException {

		// 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
		
		isElementDisplayed(TeddyBearXpath);
		
		buyProducts(StuffedFrog, 2);
		buyProducts(FluffyBunny, 5);
		buyProducts(ValentineBear, 3);
		clickCart();

	}

	public void productsValueVerification() {
		
		isElementDisplayed(CartpageFirstXpath);
		
		String stuffedFrogProductPrice = driver.findElement(
				By.xpath("//table/tbody/tr/td[2]")).getText(); 
		String FluffYBunnyProductPrice = driver.findElement(
				By.xpath("//table/tbody/tr[2]/td[2]")).getText();
		String ValentineBearProductPrice = driver.findElement(
				By.xpath("//table/tbody/tr[3]/td[2]")).getText();
		
		String stuffedFrogProductSubTotal = driver.findElement(
				By.xpath("//table/tbody/tr/td[4]")).getText(); 
		String FluffYBunnyProductSubTotal = driver.findElement(
				By.xpath("//table/tbody/tr[2]/td[4]")).getText();
		String ValentineBearProductSubTotal = driver.findElement(
				By.xpath("//table/tbody/tr[3]/td[4]")).getText();
		
		
		//Individual product price verification
		Assert.assertEquals(stuffedFrogProductPrice, "$10.99");
		Assert.assertEquals(FluffYBunnyProductPrice, "$9.99");
		Assert.assertEquals(ValentineBearProductPrice, "$14.99");
		
		//Individual Subtotals Verification
		Assert.assertEquals(stuffedFrogProductSubTotal, "$21.98");
		Assert.assertEquals(FluffYBunnyProductSubTotal, "$49.95");
		Assert.assertEquals(ValentineBearProductSubTotal, "$44.97");
		
		//totals Verification	- Without String split
		/*
		 * int SBsubtotal = Integer.parseInt(stuffedFrogProductSubTotal); int FBsubtotal
		 * = Integer.parseInt(FluffYBunnyProductSubTotal); int VBsubtotal =
		 * Integer.parseInt(ValentineBearProductSubTotal);
		 * 
		 * int Total = SBsubtotal+FBsubtotal+VBsubtotal; String ExpectedTotal =
		 * Integer.toString(Total);
		 */
		
		String ActualTotal = driver.findElement(
				By.xpath("//table/tfoot/tr[1]/td/strong")).getText(); 
		
		//totals Verification
		
		Assert.assertEquals(ActualTotal, "Total: 116.9");
		
		
		
		/*
		 * To print the entire table
		 * 
		 * 
		 * WebElement table = driver.findElement(By.
		 * xpath("//table[contains(@class, 'table table-striped cart-items')]"));
		 * List<WebElement> rowsList = table.findElements(By.tagName("tr"));
		 * 
		 * List<WebElement> columnsList = null;
		 * 
		 * for (WebElement row : rowsList) { columnsList =
		 * row.findElements(By.tagName("td"));
		 * 
		 * for (WebElement column : columnsList) { System.out.println("column text" +
		 * column.getText() + ", "); // here is is just printing number of rows, // like
		 * 1, 2 }
		 * 
		 * }
		 */
	}


}
