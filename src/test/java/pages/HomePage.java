package pages;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import wrappers.GenericWrappers;

public class HomePage extends GenericWrappers{
	private static AndroidDriver driver;

	public String userName = loadProp("USERNAME");	

	@FindBy(xpath="//*[@resource-id='home_main_on_off_swch']")
			private WebElement deviceONOFFButton;
//	@FindBy(xpath = "//android.widget.TextView[@text=\"sanity07_1\"]")
//	private WebElement devicename;
	
//	@FindBy(xpath = "//*[@resource-id='Options_Icon']")
//	private WebElement menuBarButton;
//	@FindBy(xpath = "//android.widget.TextView[@content-desc=\"com.szephyr:id/undefined\" and @text=\"\"]")
//	private WebElement menuBarButtonafterpairing;
//	@FindBy(xpath = "//android.widget.TextView[@content-desc=\"com.szephyr:id/undefined\"]")
//	private WebElement menuBarButtonafterpairing_withoutconnectivity;
	@FindBy(xpath = "//*[@resource-id='menu_bar']")
	private WebElement menuBarButton;
	
	@FindBy(xpath = "//*[@resource-id='Tab_Settings_Container']")
	private WebElement settingsButton;
	
	@FindBy(xpath = "//*[@resource-id='menu_bar']")
	private WebElement menuBarButtonafterpairing_withoutconnectivity;
	
	@FindBy(xpath = "//*[@resource-id='menu_icon_accounts']")
	private WebElement Accountinfobutton;
	
	
	@FindBy(xpath = "//*[@resource-id='menu_icon_sharelog']")
	private WebElement sharelog ;
	
	@FindBy(xpath = "//*[@resource-id='Home_StandByIndication']")
	private WebElement Acturnondesc;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Please ensure sZephyr is switched ON prior to operating your AC remote\"]")
	private WebElement ActurnOFFdesc;
	
	@FindBy(xpath = "//android.widget.TextView[@text=\"Searching for sZephyr to establish connection\"]")
	private WebElement Acofflinedesc;
	
	@FindBy(xpath = "//*[@resource-id='Home_StandByIndication']")
	private WebElement Acturnonwithloaddesc;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'V')]")
	private WebElement voltValue;
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'W')]")
	private WebElement wattValue;
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'A')]")
	private WebElement currentValue;
	
	@FindBy(xpath = "//*[@resource-id='PairedGeyser_Img_svg_ble_0_blue']")
	private WebElement bleSymbol;
	
	@FindBy(xpath = "//*[@resource-id='Device_on_off_btn_temp_degree']")
	private WebElement tempDegree;
	
	@FindBy(xpath = "//*[@resource-id='Create HierarchyButton']")
	private WebElement createHierarchyBtn;

	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddButton']")
	private WebElement hierarchyAddButton;
	
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddInput']")
	private WebElement hierarchyAddInput;
	
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddConfirm']")
	private WebElement hierarchyAddConfirm;
	
	@FindBy(xpath = "//*[@resource-id='Hierarchy_ItemInput_0']")
	private WebElement hierarchyItemInput;
	
	@FindBy(xpath = "//*[@resource-id='Hierarchy_AddCategoryDropdown']")
	private WebElement hierarchyAddCategoryDropdown;
	
	@FindBy(xpath = "//*[@resource-id='Hierarchy_DropdownOption_0']")
	private WebElement hierarchyFirstDropdownOption;
	
	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement hierarchySaveButton;
	
	@FindBy(xpath = "//*[@resource-id='FLoorCard_innercard']")
	private WebElement floorSelectionButton;
	
	@FindBy(xpath = "//*[@resource-id='FloorCardName_0']")
	private WebElement floorFirstcard;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;
	
//	@FindBy(xpath = "//*[@resource-id='Tab_Home_Container']")
//	private WebElement Homebutton;
	@FindBy(xpath = "//*[contains(@resource-id,'Tab_Home_Container')]")
	private WebElement Homebutton;

	@FindBy(xpath = "//*[@resource-id='AreaList_HeaderText']")
	private WebElement Premisestitle;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Panel1, 2 Switches\"]")
	private WebElement switchespagetwo;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Panel1, 1 Switch\"]")
	private WebElement switchespageone;
	
	@FindBy(xpath = "//*[@resource-id='Header_UnlinkedDevice_Button']")
	private WebElement unLinkDeviceButton;
	
	@FindBy(xpath = "//*[@resource-id='DashBoard_AddNewDevice_Button']")
	private WebElement startPairingButton;
	
	@FindBy(xpath = "//*[@resource-id='Tab_Apartment_Icon']")
	private WebElement apartmentIcon;
	
	
	private WebElement devicenameDeviceSettingsPage(String username) {
		return driver.findElement(By.xpath("//android.widget.TextView[@text='"+username+"']"));
		
	}
	private WebElement Hierarchyinputtext(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyInput_Level_"+level+"']"));
		
	}
	private WebElement HierarchyAddbutton(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyButtonWrapper_Level_"+level+"']"));
		
	}
	private WebElement HierarchyListItemContainer(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='Hierarchy_ListItemContainer_"+level+"']"));
		
	}
	private WebElement Panel(int panel) {
		return driver.findElement(By.xpath("//*[@resource-id='panel-item-"+panel+"']"));
		
	}
	
JavascriptExecutor js = (JavascriptExecutor) driver;
	
	// Constructor to initialize the driver and instantiate elements using
	
	public HomePage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor) driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickFloorSelctionBtn() {
		clickbyXpath(floorSelectionButton, "Floor selction button");
	}
	
	public void clickStartPairingButton() {
		clickbyXpath(startPairingButton, " Click Start Pairings button");
	}
	
	public void clickApartmentIcon() {
		clickbyXpath(apartmentIcon, " Click apartment icon");
	}
	
	public void clickUnlikDeviceButton() {
		clickbyXpath(unLinkDeviceButton, " Click Link Device button");
	}
	
	public void clickPanel(int panel) {
		if(isiconDisplayed(Panel(panel), "panel page")) {
		clickbyXpath(Panel(panel), "Panel");
		}
//		 if(isiconDisplayed(switchespageone, "panel page")){
//			clickbyXpath(switchespageone, "Panel");
//			}else {
//				clickbyXpath(switchespagetwo, "Panel");
//				
//			}
	}
	
	public void addHierarchyOption() {
		
		   clickbyXpath(hierarchyAddButton, "Level 1 Add button ");
		   entervaluebyXpath(hierarchyAddInput, "Level 1 Input", "Apartment");
		   clickbyXpath(hierarchyAddConfirm, "Level 1 Confirm button ");
		   
		   clickbyXpath(HierarchyListItemContainer(0), "Hierarchy 1st item");
		   clickbyXpath(hierarchyAddButton, "Level 2 Add button ");
		   entervaluebyXpath(hierarchyAddInput, "Level 2 Input", "Block");
		   clickbyXpath(hierarchyAddConfirm, "Level 2 Confirm button ");
		   
		   
		   clickbyXpath(HierarchyListItemContainer(0), "Hierarchy 1st item");
		   clickbyXpath(hierarchyAddButton, "Level 3 Add button ");
		   entervaluebyXpath(hierarchyAddInput, "Level 3 Input", "Floor");
		   clickbyXpath(hierarchyAddConfirm, "Level 3 Confirm button ");
		   
		   
		   clickbyXpath(HierarchyListItemContainer(0), "Hierarchy 1st item");
		   clickbyXpath(hierarchyAddButton, "Level 4 Add button ");
		   entervaluebyXpath(hierarchyAddInput, "Level 4 Input", "Room");
		   clickbyXpath(hierarchyAddCategoryDropdown,"Select room type drop down");
		   clickbyXpath(hierarchyFirstDropdownOption, "Select first Room option");
		   clickbyXpath(hierarchyAddConfirm, "Level 4 Confirm button ");
		   clickbyXpath(hierarchySaveButton, " Save Button ");	
		   		   
	   }
	public void addHierarchy_oneOption() {
		
		   clickbyXpath(hierarchyAddButton, "Level 1 Add button ");
		   entervaluebyXpath(hierarchyAddInput, "Level 1 Input", "Apartment");
		   clickbyXpath(hierarchyAddCategoryDropdown,"Select room type drop down");
		   clickbyXpath(hierarchyFirstDropdownOption, "Select first Room option");
		   clickbyXpath(hierarchySaveButton, " Save Button ");
	}
	
	
	
	
	
	
	public void clickCreateHierarchybtn() {
		
		clickbyXpath(createHierarchyBtn, "Create Hierarchy Button");
		
	}
	
//	public void navigateHomepage() throws InterruptedException {
//		String pageSource = driver.getPageSource();
//		
//		Set<String> contexts = driver.getContextHandles();
//		System.out.println("Available contexts: " + contexts);
//
//		 driver.getPageSource();
//		Thread.sleep(3000);
//	
//		while (Homebutton.isDisplayed()) {
//			
//			System.out.println(Homebutton.isDisplayed());
//			driver.navigate().back();
//			 driver.getPageSource();
//			 if (Homebutton.isDisplayed()) {
//				break;
//			}
//		}
//		
//	}
	
	public void navigateback() {
		driver.navigate().back();
	}
	public void enterFirstcard() {


		if (isElementDisplayedCheck(floorFirstcard)) {
			clickbyXpath(floorFirstcard, "Floor First card");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(floorFirstcard, "Floor First card");
				}
	}
	
	public void enterHierarchyText(int level, String text) {
		
			
//			driver.findElement(By.xpath("//*[@resource-id='HierarchyInput_Level_"+ level + "']")).sendKeys("Apartment");
			
//			WebDriverWait wait = new WebDriverWait(driver,10);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@resource-id='HierarchyInput_Level_"+ level + "']"))));
		hidekeyboard();
		entervaluebyXpath(Hierarchyinputtext(level), "Hierarchy name", text);
		hidekeyboard();
    }
	
	public void clickHierarchyAddButton(int level) {
//			WebDriverWait wait = new WebDriverWait(driver,10);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@resource-id='HierarchyButtonWrapper_Level_"+ level + "']"))));
			clickbyXpath(HierarchyAddbutton(level), "Hierarchy Add button");
		
    }

	public void clickONOFFButton() {
		expWaitTillElementDisplay(deviceONOFFButton,10);
		clickbyXpath(deviceONOFFButton, " Device ON OFF Button ");
	}
	
	
	
	public void clickAccountinfobutton() {
		clickbyXpath(Accountinfobutton, " Account info");
	}
	

	 public void clicksharcelog() {
		expWaitTillElementDisplay(sharelog,10);
		clickbyXpath(sharelog, " sharelog button ");
	 }
	 
	 public void VerifyONdesc()
	 {
	  verifyTextContainsByXpath(Acturnondesc, "Your AC unit is either in standby or powered OFF at the moment","Device on description ");
	 }
	 public void VerifyOFFdesc()
	 {
		 verifyTextContainsByXpath(ActurnOFFdesc, "Please ensure sZephyr is switched ON prior to operating your AC remote","Device off description");
	 }
	   public void killandopen() 
	   {
		   killAndReopenApp();
	   }
	   
	   public void verifyusername() {
		   verifyTextContainsByXpath(devicenameDeviceSettingsPage(loadProp("USERNAMEINAPP")), loadProp("USERNAMEINAPP"),"Username");
	}
	   public void disableBLE() throws Exception 
	   {
		   turnOffBT();
	   }
	   public void enableBLE() 
	   {
		   turnOnBT();
	   }
	   public void enableWIFI() 
	   {
		   enableWiFi();
	   }
	   public void disableWIFI() 
	   {
		   disableWiFi();
	   }
	   
	   public void WifiSwitch(String Wifiname,String Wifipassword) throws Exception 
	   {
		   
		   connectToWiFi(Wifiname, Wifipassword);
			Runtime.getRuntime().exec("adb shell am force-stop com.android.settings");
		   if (driver.queryAppState(packages) != ApplicationState.RUNNING_IN_FOREGROUND) {
				driver.activateApp(packages); 
			}
	   }

	   
	   public void getCurrentvalue() throws InterruptedException {
		   
		   verifyTextContainsByXpath(currentValue, "A","Home page current  value" );
	}
	   public void getVoltvalue() throws InterruptedException {
		   verifyTextContainsByXpath(voltValue, "V","Home page Volt value" );
		   
		   
	   }
	   public void getPowervalue() throws InterruptedException {
		   verifyTextContainsByXpath(wattValue, "W","Home page WATT value" );
		   
	   }
	   public void getbleSymbol() throws InterruptedException {
		   String attribute = bleSymbol.getText();
		   System.out.println(attribute);
	   }
	   
	   public void clickSettingsButton() {
		   clickbyXpath(settingsButton, "Click on Settings button");
	   }
	   
		public static void hidekeyboard() {
//			 Scroll up
			Map<String, Object> params = new HashMap<>();
			params.put("direction", "up");
			driver.executeScript("mobile: hideKeyboard", params);
		}
	     

}
