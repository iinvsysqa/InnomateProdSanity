package caaza_testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.DeviceMenuPage;
import pages.HierarchyPage;
import pages.HomePage;
import pages.LandingPage;
import pages.OtpPage;
import pages.Profilepage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import pages.SwitchPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC04_RemoveSwitchBoardandRepair extends MobileAppWrappers {

	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	OtpPage otppage;
	SettingsPage settingspage;
	AccountsInfoPage accountinfopage;
	DeviceMenuPage devicesettingpage;
	StoreLogPage logpage;
	AddDevicePage adddevicepage;
	SwitchPage switchpage;
	HierarchyPage hierarchypage;
	Profilepage profilepage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_04_removeswitchboard";
		testDescription = "TC_04_removeswitchboard";
		dataSheetName="Automation";
	}
	
	
	logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
	String switches = loadProp("SWITCHES_NAMES"); // returns "Switch1,Switch2,Switch3,Switch4" 
	List<String> switchNames = Arrays.asList(switches.split(","));
	String Hierarchyname="apartment";
	String Oldpassword =loadProp("PASSWORD");
	String GeneratedPassword=updateProperty("PASSWORD", randomCharacters(3, 1)+randomCharacters(2, 2)+randomCharacters(3, 3)+randomCharacters(2, 4));
	String userName = updateProperty("USERNAME", randomCharacters(4,2 ));
	
	@Test(priority = 3)
	public void TC04_RemoveSwitchBoardandRepair_Check() throws Exception {
		initAndriodDriver();
		functionaCheck();
	}


	public void functionaCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		adddevicepage= new AddDevicePage(driver);
		switchpage = new SwitchPage(driver);
		hierarchypage = new HierarchyPage(driver);
		profilepage = new Profilepage(driver);
		logpage= new StoreLogPage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
			readwrite.openPort();
			uninstall_reinstall();
			landingpage.clickLandingPageNextBtn();
			landingpage.clickSignUpLink();
			signuppage.enterUserName(userName);
			signuppage.enterName(userName);
			signuppage.enterPassword(Oldpassword);
			signuppage.enterConfirmPassword(Oldpassword);
			signuppage.clickSignUpcheckbox();
			signuppage.clickSignUpNextButton();
			signuppage.enteranswer1("demo");
			signuppage.enteranswer2("demo");
			signuppage.clickSignUpButton();
			hierarchypage.enterHierarchyText(1, Hierarchyname);
			hierarchypage.clickCreateHierarchybtn();
			hierarchypage.addHierarchy_oneOption();
			
			homepage.enterFirstcard();
			adddevicepage.pair(2);
			adddevicepage.EnterNode(switchNames);	
			homepage.navigateback();
			
			homepage.clickFloorSelctionBtn();
			homepage.clickPanel(0);
			Thread.sleep(5000);
			switchpage.clickOnOffButton();
			Thread.sleep(2000);
			switchpage.clickOnOffButton();
			switchpage.clickMenuButton();
			switchpage.clickSettingsButton();
			switchpage.clickRemoveDeviceButton();
			switchpage.clickResetConfirmationButton();
			switchpage.checkForpopup();
			
			
			adddevicepage.pair(2);
			adddevicepage.EnterNode(switchNames);	
			homepage.navigateback();
			
			homepage.clickFloorSelctionBtn();
			homepage.clickPanel(0);
			Thread.sleep(5000);
			switchpage.clickOnOffButton();
			Thread.sleep(2000);
			switchpage.clickOnOffButton();
			switchpage.clickMenuButton();
			switchpage.clickSettingsButton();
			settingspage.resetDevice();
			
			//smartconfig
			
			adddevicepage.pair(2);
			adddevicepage.EnterNode(switchNames);	
			homepage.navigateback();
			
			homepage.clickFloorSelctionBtn();
			homepage.clickPanel(0);
			Thread.sleep(5000);
			for(int i=0 ;i<=20;i++) {
				switchpage.clickOnOffButton();
				Thread.sleep(2000);
			}
			
			for(int i=0 ;i<=20;i++) {
				readwrite.write("button_press\r");
				Thread.sleep(2000);
			}
			
			
			
			switchpage.clickMenuButton();
			switchpage.clickSettingsButton();
			settingspage.resetDevice();
			driver.navigate().back();
			
			//remove hierarchy and delete account
			profilepage.clickApartmentIcon();
			profilepage.clickMenubaricon();
			profilepage.clickAddEditbtn();
			profilepage.DeleteHierarchy(1,Hierarchyname);
			profilepage.clickHierarchy_BackButton();
			profilepage.navigateSettingsbtn();
			profilepage.navigateProfileSettingsPage();
			profilepage.deleteAccount();
			profilepage.confirmDelete();
			profilepage.checkSignInButton();
			readwrite.closePort();
		}
		catch (Exception e) {
			readwrite.closePort();
			logpage.CollectLogOnFailure(testCaseName,testDescription);
			fail(e);
		}
	}


}	