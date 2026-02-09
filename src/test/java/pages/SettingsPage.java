package pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class SettingsPage extends GenericWrappers{

	private static AndroidDriver driver;
	AddDevicePage adddevicepage;
	
	
	
	public SettingsPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		adddevicepage = new AddDevicePage(driver);
	}

	
	// Locate all elements on the page

	@FindBy(xpath = "//*[@resource-id='logoutLabel']")
	private WebElement logoutButton;
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Text_Logout']")
	private WebElement logoutConfirmationButton;
	@FindBy(xpath = "//*[@resource-id='Header_MenuButton']")
	private WebElement devicepage_menuButton;

	@FindBy(xpath = "//*[@resource-id='DevicesHeading']")
	private WebElement devicesTitle;
	
	@FindBy(xpath = "//*[@resource-id='Close_Icon_Svg']")
	private WebElement menu_CloseIcon;
	
	
	@FindBy(xpath = "//*[@resource-id='Tab_Settings_Icon']")
	private WebElement menu_Settingsbtn;
	@FindBy(xpath = "//*[@resource-id='MenuItem_Settings_0']")
	private WebElement MenuItem_Settings;
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_Settings_1']")
	private WebElement switchpagemenu_settingsButton;
	
	@FindBy(xpath = "//*[@resource-id='MenuItem_AddandEditSwitchboard_0']")
	private WebElement Menu_AddandEditSwitchboard;
	@FindBy(xpath = "//*[@resource-id='MenuItem_FirmwareUpdate_2']")
	private WebElement Menu_FirmwareUpdate;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_Label_HighVoltageCutoff']")
	private WebElement SettingsItem_HighVoltageCuttoff;
	
	@FindBy(xpath = "//*[@resource-id='SettingsItem_Label_LowVoltageCutoff']")
	private WebElement SettingsItem_LowVoltageCuttoff;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_TimeZoneConfiguration']")
	private WebElement SettingsItem_TimeZoneConfiguration;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ResetDevice']")
	private WebElement SettingsItem_ResetDevice;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_ModifyRouter']")
	private WebElement SettingsItem_ModifyRouter;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_SubText_ConfiguredRouter']")
	private WebElement SettingsItem_SubText_ConfiguredRouter;
	@FindBy(xpath = "//*[@resource-id='Header_Title']")
	private WebElement Header_Title;
	@FindBy(xpath = "//*[@resource-id='HighVoltage_Toggle']")
	private WebElement HighVoltage_Toggle;
	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement SaveText;
	@FindBy(xpath = "//*[@resource-id='HighVoltage_Input']")
	private WebElement HighVoltage_Input;
	@FindBy(xpath = "//*[@resource-id='LowVoltage_Toggle']")
	private WebElement LowVoltage_Toggle;
	@FindBy(xpath = "//*[@resource-id='LowVoltage_Input']")
	private WebElement LowVoltage_Input;
	@FindBy(xpath = "//*[@resource-id='CustomModal_ConfirmButton_Text']")
	private WebElement Reset_ConfirmButton_Text;
	@FindBy(xpath = "//*[@resource-id='CustomModal_CancelButton_Text']")
	private WebElement Reset_CancelButton_Text;
	@FindBy(xpath = "//android.widget.EditText[@text=\"Enter Password\"]")
	private WebElement WIFI_EnterPasswordTextbox;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Cancel\"]")
	private WebElement WIFI_Cancelbtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Submit\"]")
	private WebElement WIFI_Submitbtn;
	@FindBy(xpath = "//android.widget.Toast[@text=\"high voltage threshold value saved\"]")
	private WebElement HighVoltagesavedToast;
	@FindBy(xpath = "//android.widget.Toast[@text=\"Low voltage threshold value saved\"]")
	private WebElement LowVoltagesavedToast;
	@FindBy(xpath = "//android.widget.Toast[@text=\"Your device reset successfully\"]")
	private WebElement DeviceresetToast;
	@FindBy(xpath = "//android.widget.Toast[@text=\"Credentials updated successfully\"]")
	private WebElement CredentialsupdatedToast;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Device couldn't connect with router\"]")
	private WebElement CouldntConnectrouterPopUp;
	@FindBy(xpath = "//*[@resource-id='Single_Button']")
	private WebElement CouldntConnectrouter_OKPopUp;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;
	@FindBy(xpath = "//*[@resource-id='Header_BackIcon']")
	private WebElement Header_Back_Button;
	@FindBy(xpath = "//*[@resource-id='BackIcon_Component_Button']")
	private WebElement Profile_Back_Button;
	@FindBy(xpath = "//*[@resource-id='SettingsItem_EnergyMonitor']")
	private WebElement SettingsItem_EnergyMonitor;
	@FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"com.CaaZa_Smart:id/SettingsScreen_ScrollView\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
	private WebElement EnergymonitorToggle;
	@FindBy(xpath = "//*[@resource-id='routerListIcon']")
	private WebElement RouterConfiguration;

	
	
	
	public void clickLogOutButton() {
		clickbyXpath(logoutButton, "Click Log out Button");
	}

	public void clickLogOutConfirmButton() {
		clickbyXpath(logoutConfirmationButton, "Click Log out Button");
	}

	public void openMenuPage() {


		if (isElementDisplayedCheck(devicepage_menuButton)) {
			clickbyXpath(devicepage_menuButton, "Menu button");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(devicepage_menuButton, "Menu button");
				}

	
		
	
	}
	public void navigateSettingspage() {
		clickbyXpath(MenuItem_Settings, "Settings button");
	}
	public void navigateMenuitemSettings() {
		clickbyXpath(MenuItem_Settings, "Menuitem settings ");
	}
	public void navigateHighVoltCutoff() {
		clickbyXpath(SettingsItem_HighVoltageCuttoff, "HighVoltageBTN");
	}
	public void navigateLowVoltCutoff() {
		clickbyXpath(SettingsItem_LowVoltageCuttoff, "LowVoltageBTN");

	}
	public void clickHighVoltToggle() {
		clickbyXpath(HighVoltage_Toggle, "High Volt Toggle");
	}
	public void clickLowVoltToggle() {
		clickbyXpath(LowVoltage_Toggle, "Low Voltage Toggle");

	}
	
	public void enterHighVoltagevalue(String input) {
		
		entervaluebyXpath(HighVoltage_Input, "High Voltage Input textbox",input);
//		adddevicepage.hidekeyboard();
	}
	public void enterLowVoltagevalue(String input) {
		entervaluebyXpath(LowVoltage_Input, "High Voltage Input textbox",input);
//		adddevicepage.hidekeyboard();	
	}
	public void verifyHighVoltValues(String input) {
		verifyTextContainsByXpath(HighVoltage_Input, input, "High Volt value");
//		adddevicepage.hidekeyboard();
	}
	public void verifyLowVoltValues(String input) {
		verifyTextContainsByXpath(LowVoltage_Input, input, "Low Volt value");
//		adddevicepage.hidekeyboard();
		
	}
	
	public void clickSavebutton() {
		clickbyXpath(SaveText, "Save Button");
	}
	public void verifyHighvoltToast() {
		verifyTextContainsByXpath_Toast(HighVoltagesavedToast, "high voltage threshold value saved","High Volt Toast");
	}
	public void verifyLowvoltToast() {
		verifyTextContainsByXpath_Toast(LowVoltagesavedToast, "Low voltage threshold value saved","Low Volt Toast");
	}
	public void navigateback() {
		driver.navigate().back();
	}
	
	public void Profilebackbutton() {
		clickbyXpath(Profile_Back_Button, "Profile back button");
	}
	public void clickheaderbackbutton() {
		clickbyXpath(Header_Back_Button, "back button");
	}
	
	public void resetDevice() throws InterruptedException {
		clickbyXpath(SettingsItem_ResetDevice, "ResetDevice");
		clickbyXpath(Reset_ConfirmButton_Text, "ResetDevice_confirmationBTN");
		Thread.sleep(3000);
//		verifyTextContainsByXpath(DeviceresetToast, "Your device reset successfully", "Device reset successfully ");
	}
	public void resetDeviceCancelBTN() {
		clickbyXpath(Reset_CancelButton_Text, "ResetDeviceCancelBtn");
	}
	
	
	public void verifyConfiguredRouter(String router) {
		verifyTextContainsByXpath( SettingsItem_SubText_ConfiguredRouter,router , "Configured router name");
	}
	
	public void clickModifyRouterbtn() {
		clickbyXpath(SettingsItem_ModifyRouter,"Modify Router Btn");
	}
	public void enterRouterPassword(String Password) {
		entervaluebyXpath(WIFI_EnterPasswordTextbox,"WIFI_EnterPasswordTextbox", Password);

	}
	public void clickModifyRouterSubmitBtn() {
		clickbyXpath(WIFI_Submitbtn, "Wifi Submit btn");
	}
	public void clickModifyRouterCancelBtn() {
		clickbyXpath(WIFI_Cancelbtn, "Router Cancel button");
	}
	public boolean verifyCouldntConnectPopup() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);

        try {
            // Option A: wait until next-screen element is visible (preferred)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text=\\\"Device couldn't connect with router\\\"]")));
            verifyTextContainsByXpath(CouldntConnectrouterPopUp, "Device couldn't connect with router", "Could connect to router ");
            return true;
        } catch (TimeoutException e) {
            // Option B fallback: wait until verifying header is not visible
        	return false;
        }
    
	}
	public void clickCouldntconnectrouterOKpopup() {
		clickbyXpath(CouldntConnectrouter_OKPopUp, "Couldnt connect router OK pop-up");
	}
	public boolean verifyCredentialsToast() {
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
	        wait.pollingEvery(Duration.ofMillis(500));
	        wait.ignoring(NoSuchElementException.class);
	        wait.ignoring(StaleElementReferenceException.class);

	        try {
	            // Option A: wait until next-screen element is visible (preferred)
	            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.Toast[@text=\\\"Credentials updated successfully\\\"]")));
	            verifyTextContainsByXpath(CredentialsupdatedToast,"Credentials updated successfully", "Credentials updated Toast");
	            return true;
	        } catch (TimeoutException e) {
	            // Option B fallback: wait until verifying header is not visible
	        	return false;
	        }
	}
	public void navigateswitchmenuSettingspage() {
		clickbyXpath(switchpagemenu_settingsButton, "switchmenusettingsbutton");
	}
	
	public void navigateEnergyMonitorpage() {
		clickbyXpath(SettingsItem_EnergyMonitor,"Energy Monitor button" );
	}
	
	public void clickEnergyMonitorToggle() {
		clickbyXpath(EnergymonitorToggle, "Energy Monitor Toggle");
	}
	public void clickRouterConfiguration() {
		clickbyXpath(RouterConfiguration, "Router configuration");
	}
}

