package caaza_testcases;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AddDevicePage;
import pages.Analytics;
import pages.HierarchyPage;
import pages.HomePage;
import pages.LandingPage;
import pages.Profilepage;
import pages.ScenecreationPage;
import pages.Schedularpage;
import pages.Schedulartestpage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import pages.SwitchPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC07_DeviceSettingspage extends MobileAppWrappers{

	LandingPage landingpage;
	SignUpPage signuppage;
	HomePage homepage;
	SettingsPage settingspage;
	StoreLogPage logpage;
	Schedularpage schedular;
	Schedulartestpage test;
	Analytics analytics;
	AddDevicePage adddevicepage;
	ScenecreationPage scenecreation;
	HierarchyPage hierarchypage;
	Profilepage profilepage;
	SwitchPage switchpage;
	
	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC_07_Device settings ";
		testDescription = "Device settings page features check";
		dataSheetName="Automation";
	}

	
	@Test(priority = 6)
	public void TC_07_DeviceSettingspagecheck() throws Exception {
		initAndriodDriver();
		landingPageCheck();
    }
	
	public void landingPageCheck() throws Exception {
		landingpage= new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage= new HomePage(driver);
		test= new Schedulartestpage(driver);
		logpage= new StoreLogPage(driver);
		schedular= new Schedularpage(driver);
		analytics = new Analytics(driver);
		adddevicepage = new AddDevicePage(driver);
		scenecreation =new ScenecreationPage(driver);
		hierarchypage = new HierarchyPage(driver);
		profilepage = new Profilepage(driver);
		switchpage = new SwitchPage(driver);
		
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		String switches = loadProp("SWITCHES_NAMES"); // returns "Switch1,Switch2,Switch3,Switch4" 
		List<String> switchNames = Arrays.asList(switches.split(","));
		String Hierarchyname="apartment";
		String Oldpassword =loadProp("PASSWORD");
		String GeneratedPassword=updateProperty("PASSWORD", randomCharacters(3, 1)+randomCharacters(2, 2)+randomCharacters(3, 3)+randomCharacters(2, 4));
		String userName = updateProperty("USERNAME", randomCharacters(4,2 ));
		
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
			
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			settingspage.openMenuPage();
			settingspage.navigateSettingspage();
			
			settingspage.navigateHighVoltCutoff();
			settingspage.clickHighVoltToggle();
			settingspage.enterHighVoltagevalue("270");
			settingspage.clickSavebutton();
//			settingspage.verifyHighvoltToast();
			
//			killAndReopenApp();
//			homepage.enterFirstcard();
//			homepage.clickPanel(0);
//			settingspage.openMenuPage();
//			settingspage.navigateSettingspage();
			settingspage.navigateHighVoltCutoff();
			settingspage.verifyHighVoltValues("270");
			settingspage.navigateback();
			settingspage.clickheaderbackbutton();
			settingspage.clickheaderbackbutton();
			settingspage.openMenuPage();
			settingspage.navigateMenuitemSettings();
			settingspage.navigateLowVoltCutoff();
			settingspage.clickLowVoltToggle();
			settingspage.enterLowVoltagevalue("170");
			settingspage.clickSavebutton();
//			settingspage.verifyLowvoltToast();
			
//			killAndReopenApp();
//			homepage.enterFirstcard();
//			homepage.clickPanel(0);
//			settingspage.openMenuPage();
//			settingspage.navigateSettingspage();
			
			settingspage.navigateLowVoltCutoff();
			settingspage.verifyLowVoltValues("170");
			settingspage.navigateback();
			settingspage.clickheaderbackbutton();
			
//modify router		
			settingspage.verifyConfiguredRouter(loadProp("WIFINAME"));
			connectToWiFi("TP-Link_6D38-with_Internet", "12345678906");
			settingspage.clickModifyRouterbtn();
			settingspage.clickModifyRouterSubmitBtn();
			settingspage.verifyCouldntConnectPopup();
			settingspage.clickCouldntconnectrouterOKpopup();
			settingspage.clickModifyRouterbtn();
			settingspage.enterRouterPassword("12345678906");
			settingspage.clickModifyRouterSubmitBtn();
			settingspage.verifyCredentialsToast();
			settingspage.verifyConfiguredRouter("TP-Link_6D38-with_Internet");
			settingspage.clickheaderbackbutton();
			settingspage.clickheaderbackbutton();
			
			settingspage.openMenuPage();
			switchpage.clickAddandEditSwitchboard();
			switchpage.clickSwitchboardmenuButton(0);
			switchpage.clickEditoptionText(0);
//			switchpage.clickSwitchboardMenuEdit();
			switchpage.changeSwitchBoardname("Panel");
			switchpage.clickConfirmButton(0);
			switchpage.verifySwitchBoardname("Panel");
//			switchpage.clickBackButton();
			settingspage.navigateback();
			
			
			
//logout and login check the details remain same in all page================
		
			profilepage.navigateSettingsbtn();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			landingpage.enterUserName(userName);
			landingpage.enterPassword(Oldpassword);
			landingpage.clickSignInButton();
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			settingspage.openMenuPage();
			settingspage.navigateSettingspage();
			settingspage.navigateHighVoltCutoff();
			settingspage.verifyHighVoltValues("270");
			settingspage.navigateback();
			settingspage.clickheaderbackbutton();
			settingspage.navigateLowVoltCutoff();
			settingspage.verifyLowVoltValues("170");
			settingspage.navigateback();
			settingspage.clickheaderbackbutton();
			settingspage.verifyConfiguredRouter("TP-Link_6D38-with_Internet");
			settingspage.clickheaderbackbutton();
			settingspage.clickheaderbackbutton();
			
			settingspage.openMenuPage();
			switchpage.clickAddandEditSwitchboard();
			switchpage.clickSwitchboardmenuButton(0);
			switchpage.verifySwitchBoardname("Panel");
			settingspage.clickheaderbackbutton();
			
			
			
			profilepage.navigateSettingsbtn();
			
			
			homepage.clickPanel(0);
			settingspage.openMenuPage();
			settingspage.navigateSettingspage();
			settingspage.resetDevice();
			settingspage.navigateback();
			
			
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
