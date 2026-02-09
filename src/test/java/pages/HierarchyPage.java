package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class HierarchyPage  extends GenericWrappers{

	private AndroidDriver driver;
	
	public HierarchyPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	@FindBy(xpath = "//*[@resource-id='Create HierarchyButton']")
	private WebElement createHierarchyBtn;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddButton']")
	private WebElement hierarchyAddButton;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddInput']")
	private WebElement hierarchyAddInput;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddCategoryDropdown']")
	private WebElement hierarchyAddCategoryDropdown;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_DropdownOption_0']")
	private WebElement hierarchyFirstDropdownOption;
	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement hierarchySaveButton;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddConfirm']")
	private WebElement hierarchyAddConfirm;
	@FindBy(xpath = "//*[@resource-id='Start a New SmartEcoButton']")
	private WebElement StartaNewHomeButton;
	@FindBy(xpath = "//*[@resource-id='SkipButton_Text']")
	private WebElement SkipButton_Text;
	@FindBy(xpath = "//*[@resource-id='Header_Back_Button']")
	private WebElement Hierarchy_Back_Button;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;
	
	@FindBy(xpath = "(//android.widget.TextView[@text=\"Start a New SmartEco\"])[2]")
	private WebElement StartaNewHomeText;
	
	private WebElement devicenameDeviceSettingsPage(String username) {
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+username+"']"));
		
	}
	private WebElement Hierarchyinputtext(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyInput_Level_"+level+"']"));
		
	}
	private WebElement HierarchyAddbutton(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyButtonWrapper_Level_"+level+"']"));
		
	}
	
	public void enterHierarchyText(int level, String text) {
		
		entervaluebyXpath(Hierarchyinputtext(level), "Hierarchy name", text);
		AddDevicePage.hidekeyboard();
		
	}
		public void clickCreateHierarchybtn() {
			
			clickbyXpath(createHierarchyBtn, "Create Hierarchy Button");
			
		}
		public void addHierarchy_oneOption() throws InterruptedException {
				Thread.sleep(2000);
			   clickbyXpath(hierarchyAddButton, "Level 1 Add button ");
			   entervaluebyXpath(hierarchyAddInput, "Level 1 Input", "Apartment");
			   clickbyXpath(hierarchyAddCategoryDropdown,"Select room type drop down");
			   clickbyXpath(hierarchyFirstDropdownOption, "Select first Room option");
			   clickbyXpath(hierarchyAddConfirm, "Level 1 Confirm button ");
			   clickbyXpath(hierarchySaveButton, " Save Button ");
		}
		
		
		public void clickStartaNewHomeButton() {

//			if (isElementDisplayedCheck(StartaNewHomeButton)) {
				clickbyXpath(StartaNewHomeButton, "Start a new Home button");
//				}else if(isElementDisplayedCheck(RetryButton)) {
//					clickbyXpathwithoutReport("Retry button", RetryButton);
//					clickbyXpath(StartaNewHomeButton, "Start a new Home button");
//					}

		
			
		}
		public void clickStartanewhometext() {
			clickbyXpath(StartaNewHomeText, "StartaNewHomeText");
			
		}
		public void clickHierarchy_Back_Button() {
			clickbyXpath(Hierarchy_Back_Button, "Hierachy back button");
		}
}
