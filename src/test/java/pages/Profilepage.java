package pages;

import java.time.Duration;
import java.util.List;
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

public class Profilepage extends GenericWrappers{

	private AndroidDriver driver;
	public static String ConfiguredRouter;
	SettingsPage settingspage ;
	HomePage homepage ;
	
	public Profilepage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		settingspage = new SettingsPage(driver);
		homepage = new HomePage(driver);
	}

	
	
	// Locate all elements on the page

	@FindBy(xpath = "//*[@resource-id='Tab_Settings_Icon']")
	private WebElement settingsicon;
	@FindBy(xpath = "//*[@resource-id='SettingScreen_Username']")
	private WebElement settingspage_Username;
	@FindBy(xpath = "//*[@resource-id='BackIcon_Component_Title']")
	private WebElement profilepage_Title;
	@FindBy(xpath = "//*[@resource-id='Profile_EditButton']")
	private WebElement profilepage_editBtn;

	@FindBy(xpath = "//*[@resource-id='Profile_UserNameText']")
	private WebElement profilepage_name;
	@FindBy(xpath = "//*[@resource-id='Profile_DOBLabel']")
	private WebElement profilepage_DOB;
	@FindBy(xpath = "//*[@resource-id='Profile_GenderLabel']")
	private WebElement profilepage_Gender;
	@FindBy(xpath = "//*[@resource-id='Profile_ChangePasswordText']")
	private WebElement profilepage_chnagepasswordbtn;
	@FindBy(xpath = "//*[@resource-id='Profile_DeleteAccountText']")
	private WebElement profilepage_DeleteAccountBtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"T\"]")
	private WebElement ProfilepageProfile_EditBtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"DOB\"]")
	private WebElement ProfilepageProfile_DOBbtn;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Gender\"]")
	private WebElement ProfilepageProfile_Genderbtn;
	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement ProfilepageProfile_SaveBtn;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Choose from gallery\"]")
	private WebElement profilepageprofile_choosefromgallery;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Take photo\"]")
	private WebElement profilepageprofile_takephotoBtn;
	@FindBy(xpath = "//android.widget.TextView[@text=\"ok\"]")
	private WebElement profilepageprofile_popupokBtn;
	@FindBy(xpath = "//android.widget.TextView[@text=\"Male\"]")
	private WebElement profilepageprofile_male;
	@FindBy(xpath = "//*[@resource-id='']")
	private WebElement profilepageprofile_female;
	@FindBy(xpath = "//*[@resource-id='']")
	private WebElement profilepageprofile_other;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_CloseIcon']")
	private WebElement ChangePassword_CloseIcon;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_CurrentPassword']")
	private WebElement ChangePassword_CurrentPassword;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_NewPassword']")
	private WebElement ChangePassword_NewPassword;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_ConfirmPassword']")
	private WebElement ChangePassword_ConfirmPassword;
	@FindBy(xpath = "//*[@resource-id='ChangePassword_ForgotPasswordText']")
	private WebElement ChangePassword_ForgotPasswordText;
	@FindBy(xpath = "//*[@resource-id=' Change PasswordButton']")
	private WebElement  ChangePasswordBTN;
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Text_ConfirmDelete']")
	private WebElement ConfirmDelete;
	@FindBy(xpath = "//*[@resource-id='Alert_SecondaryButtonText']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//*[@resource-id='Tab_Home_Container']")
	private WebElement Homecontainer;
	@FindBy(xpath = "//*[@resource-id='Add SwitchboardText']")
	private WebElement AddSwitchboardText;
	@FindBy(xpath = "//*[@resource-id='Tab_Apartment_Container']")
	private WebElement Apartment_Icon;
	@FindBy(xpath = "//*[@resource-id='Header_MenuButton']")
	private WebElement MenuButton;
	@FindBy(xpath = "//*[@resource-id='MenuItemText_0']")
	private WebElement Add_editbtn;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_Title']")
	private WebElement Hierarchytitle;
	@FindBy(xpath = "//*[@resource-id='Tab_Home_Icon']")
	private WebElement Home_Icon;
	@FindBy(xpath = "//*[@resource-id='Hierarchy_BackButton']")
	private WebElement Hierarchy_BackButton;
	@FindBy(xpath = "//*[@resource-id='BackIcon_Component_Button']")
	private WebElement profileBackbutton;
	
	@FindBy(xpath = "//*[@resource-id='SignInText']")
	private WebElement SignInText;
	@FindBy(xpath = "//*[@resource-id='SignInText']")
	private WebElement SignInButton;
	@FindBy(xpath = "//*[@resource-id='logoutMenu']")
	private WebElement Logoutbtn;
	@FindBy(xpath = "//*[@resource-id='ThemedButton_Logout']")
	private WebElement ThemedButton_Logout;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
	
	
	
	
	private WebElement HierarchyroomList(int container) {
		 return driver.findElement(By.xpath("//*[@resource-id='Hierarchy_ItemMenu_"+container+"']"));
	}
	private WebElement RemoveButton(int container) {
		return driver.findElement(By.xpath("//*[@resource-id='Hierarchy_RemoveOption_"+container+"']"));
	}
	private WebElement Toastcheck(String string) {
		return driver.findElement(By.xpath("//android.widget.Toast[@text=\""+string+"\"]"));
	}
	
//	Waitandverifytext();
	public void navigateSettingsbtn() {

		if (isElementDisplayedCheck(settingsicon)) {
			clickbyXpath(settingsicon, "Settings Icon");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(settingsicon, "Settings Icon");
				}

	
		
	}
	public void verifySettingsPageUsername(String text) {
		verifyTextContainsByXpath(settingspage_Username, text, "SettingspageUsername");
	}
	public void navigateProfileSettingsPage() {
		clickbyXpath(settingspage_Username, "ProfileSettingsBtn");
	}
	public void verifyProfilename(String text) {
		verifyTextContainsByXpath(profilepage_name, text, "Profilename");
	}
	
	public void navigateProfileSettingsProfileeditpage() {
		clickbyXpath(profilepage_editBtn, "Profilepage Profile Edit button");
	}
	
	String imagepath="C:\\\\Users\\\\QA Automation\\\\eclipse-workspace\\\\Caaza_Automation\\\\profilimagepng.png";
	
	public void SetProfileimage() {
		
		clickbyXpath(ProfilepageProfile_EditBtn,"Profilepage profile Edit btn" ) ;
//		clickbyXpath(profilepageprofile_choosefromgallery,"Choose from gallery btn" ) ;
		profilepageprofile_choosefromgallery.sendKeys(imagepath);
		
	}
	public void setDOB() {
		ProfilepageProfile_DOBbtn.sendKeys("31/03/2001");
	}
	public void openChangePasswordpage() {
		clickbyXpath(profilepage_chnagepasswordbtn,"Change password btn" );
	}
	public void entercurrentpassword(String oldpassword) {
		entervaluebyXpath(ChangePassword_CurrentPassword, "current password",oldpassword );
	}
	public void changepassword(String GeneratedPassword) {
		entervaluebyXpath(ChangePassword_NewPassword, "New  password", GeneratedPassword);
		entervaluebyXpath(ChangePassword_ConfirmPassword, "Confirm password", GeneratedPassword);
		clickbyXpath(ChangePasswordBTN, "Change password btn");
	}
	
	public void removeAddeddevice() throws InterruptedException {
		
		
	 if (isElementDisplayedCheck(Homecontainer)) {
		clickbyXpath(Homecontainer, "Home page container");
	}
	 homepage.enterFirstcard();
	 if (!isElementDisplayed(AddSwitchboardText, "Add SwitchBoard Btn")) {
		 settingspage.openMenuPage();
		 settingspage.navigateSettingspage();
		 settingspage.resetDevice();
		 settingspage.navigateback();
	}else {
		driver.navigate().back();
	}
	 
	 
	}
	public void deleteAccount() {
		clickbyXpath(profilepage_DeleteAccountBtn, "Delete account");
	}
	public void confirmDelete() {
		clickbyXpath(ConfirmDelete, "Confirm Delete");
		
	}
	public void clickApartmentIcon() {




		if (isElementDisplayedCheck(Apartment_Icon)) {
			clickbyXpath(Apartment_Icon,"Apartmenticon");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(Apartment_Icon,"Apartmenticon");
				}
	
	
	}
	public void clickMenubaricon() {
		clickbyXpath(MenuButton, "Menubutton");
	}
	public void clickAddEditbtn() {
		clickbyXpath(Add_editbtn, "Add & Edit btn");
	}
	public void DeleteHierarchy(int createdRoomlist,String string) throws InterruptedException {// Locate the Hierarchy Tab Container
//		WebElement hierarchyTabContainer = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='com.CaaZa_Smart:id/Hierarchy_TabContainer']"));
//		;
		for (int i = 1; i <= createdRoomlist; i++) {
			
			clickbyXpath(HierarchyroomList(i-1), "Created Room list");
			clickbyXpath(RemoveButton(i-1),"Hierarchy list Remove button" );
//			verifyTextContainsByXpath_Toast(Toastcheck(string+" delete successfully"), "room delete successfully", "Room deleted Toast");
		}
	}
		
//		public void DeleteHierarchy(int createdRoomlist,List<String> string) throws InterruptedException {// Locate the Hierarchy Tab Container
////		WebElement hierarchyTabContainer = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='com.CaaZa_Smart:id/Hierarchy_TabContainer']"));
////		;
//			for (int i = 1; i <= createdRoomlist; i++) {
//				
//				clickbyXpath(HierarchyroomList(i-1), "Created Room list");
//				clickbyXpath(RemoveButton(i-1),"Hierarchy list Remove button" );
////			verifyTextContainsByXpath_Toast(Toastcheck(string+" delete successfully"), "room delete successfully", "Room deleted Toast");
//			}
//		
//	}
	public void clickHierarchy_BackButton() {
		clickbyXpath(Hierarchy_BackButton, "Hierarchy_BackButton");
	}
	public void clickHomepage() {
		clickbyXpath(Home_Icon, "Home icon");
	}
	public void clickProfilepageBackbutton() {
		clickbyXpath(profileBackbutton, "ProfileBackbutton");
	}
	public void verifySignin() {
		verifyTextContainsByXpath(SignInText, "Sign In", "Sign In Text");
	}
	public void clicklogoutbtn() {
clickbyXpath(Logoutbtn, "Logout Btn");
	}
	public void logoutConfirmationBtn() {
clickbyXpath(ThemedButton_Logout, "Logout Confirm button");
	}
	
	public void checkSignInButton() {
verifyTextContainsByXpath(SignInButton,"Sign In" , "SignIn Button");
	}
}
