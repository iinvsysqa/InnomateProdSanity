package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utils.Reporter;
import wrappers.GenericWrappers;

public class UsersPage extends GenericWrappers {

	private AndroidDriver driver;

	public UsersPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//*[@resource-id='UsersCountContainer']")
	private WebElement UsersCard;
	@FindBy(xpath = "//*[@resource-id='ShareButtonText']")
	private WebElement Userpage_ShareButtonText;
	@FindBy(xpath = "//*[@resource-id='ShareControl_RadioOuter_0']")
	private WebElement Share_fullaccess_Radiobtn;
	@FindBy(xpath = "//*[@resource-id='ShareControl_RadioOuter_1']")
	private WebElement Share_partialaccess_Radiobtn;
	@FindBy(xpath = "//*[@resource-id='ShareControl_RadioOuter_2']")
	private WebElement Share_viewonlyaccess_Radiobtn;
	@FindBy(xpath = "//*[@resource-id='ShareButton']")
	private WebElement ShareButton;
	@FindBy(xpath = "//*[@resource-id='ShareControl_CloseButton']")
	private WebElement ShareControl_CloseButton;
	@FindBy(xpath = "//*[@resource-id='usernameInput']")
	private WebElement SharePageUsernameInput;
	@FindBy(xpath = "//*[@resource-id='SendButton']")
	private WebElement SharePageSendButton;
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Continue']")
	private WebElement SharePageContinueButton;
	@FindBy(xpath = "//*[@resource-id='EnableScreenSharingCard']")
	private WebElement OtherUsersHierarchy;
	@FindBy(xpath = "//*[@resource-id='SharedScreen_MainUser']")
	private WebElement MainUser;
	@FindBy(xpath = "//*[@resource-id='SharedScreen_User_0']")
	private WebElement shareduser;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;

//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement;

	private WebElement SharePagedeviceselection(String apartmentname) {
		return driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='" + apartmentname + "']"));

	}

	private WebElement Hierarchyinputtext(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyInput_Level_" + level + "']"));

	}

	private WebElement HierarchyAddbutton(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyButtonWrapper_Level_" + level + "']"));

	}

	private WebElement sharedUsername(String username, int level) {
		return driver.findElement(By.xpath("//*[@resource-id='UserControl_UserName_" + username + "_" + level + "']"));

	}

	private WebElement checkViewonlyaccessToast() {
		return driver.findElement(
				By.xpath("//android.widget.Toast[@text=\"You don't have permission to perform this action\"]"));

	}

	public void NavigateUserpage() {
		if (isElementDisplayedCheck(UsersCard)) {
			clickbyXpath(UsersCard, "Usercard");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(UsersCard, "Usercard");
				}

	}

	public void clickSharebutton() throws Exception {
		if(isElementDisplayedCheck(Userpage_ShareButtonText)) {
		clickbyXpath(Userpage_ShareButtonText, "Share button");}
		else {

			driver.navigate().back();
			Thread.sleep(2000);
			driver.navigate().back();
			NavigateUserpage();
			clickbyXpath(Userpage_ShareButtonText, "Share button");}
		}
	
	public void navigateBack() {
		driver.navigate().back();
		

	}

	public void clickFullAccessradioButton() {
		clickbyXpath(Share_fullaccess_Radiobtn, "Full access radio button");
	}

	public void clickPartialAccessRadioButton() {
		clickbyXpath(Share_partialaccess_Radiobtn, "Partial acess radio button");
	}

	public void clickViewOnlyAcessRadioButton() {
		clickbyXpath(Share_viewonlyaccess_Radiobtn, "View only access radio button");
	}

	public void SelectApartmentDevices(String Roomname) {
		clickbyXpath(SharePagedeviceselection(Roomname), "Room list Device Dropdown");
	}

	public void clickdeviceShareButton() {
		clickbyXpath(ShareButton, "Share button");
	}

	public void enterUsername(String username) {
		entervaluebyXpath(SharePageUsernameInput, "Username textbox", username);
	}

	public void clickSendbutton() {
		clickbyXpath(SharePageSendButton, "send button");
	}

	public void clickContinueButton() {
		clickbyXpath(SharePageContinueButton, "Share page Continue Button");
	}

	public void checkAddedUser(String username) throws InterruptedException {
		Thread.sleep(5000);
		verifyTextContainsByXpath(sharedUsername(username, 0), username, "Shared user name");
	}

	public void clickOtherUsersHierarchycontainer() {
		clickbyXpath(OtherUsersHierarchy, "Other users Hierarchy button");
	}

	public void clickMainuserbox() {
		clickbyXpath(MainUser, "Main user Hierarchy list");
	}

	public void sharedUserHierarchyBox() {
		clickbyXpath(shareduser, "Other users Hierarchy list");
	}

	public void checkViewonlyAccesson_off_Toast() {
		verifyTextContainsByXpath(checkViewonlyaccessToast(), "You don't have permission to perform this action",
				"View only access permisson denied toast");
	}
}
