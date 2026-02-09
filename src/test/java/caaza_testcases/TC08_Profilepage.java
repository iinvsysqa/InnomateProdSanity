package caaza_testcases;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.logging.profiler.HttpProfilerLogEntry;
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
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC08_Profilepage extends MobileAppWrappers{

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
	Profilepage profilepage;
	HierarchyPage hierarchypage;

	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC08_Profilepage";
		testDescription = "Profilepage_logoutlogin_deleteAccount_changePassword";
		dataSheetName="Automation";
	}

	
	@Test(priority = 7)
	public void TC08_Profilepage_check() throws Exception {
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
		profilepage= new Profilepage(driver);
		hierarchypage= new HierarchyPage(driver);
		
		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		String switches = loadProp("SWITCHES_NAMES"); // returns "Switch1,Switch2,Switch3,Switch4" 
		List<String> switchNames = Arrays.asList(switches.split(","));
		String Hierarchyname="apartment";
		String Oldpassword =loadProp("PASSWORD");
		String GeneratedPassword=updateProperty("PASSWORD", randomCharacters(3, 1)+randomCharacters(2, 2)+randomCharacters(3, 3)+randomCharacters(2, 4));
		
		String userName = updateProperty("USERNAME", randomCharacters(4,2 ));
		/*
		 * " 1. Gointo profile section. 2. Check user able edit name,DOB and chane
		 * password. 3. Logout and login with new password and check user details
		 * updated properly. "
		 */	
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

		
//			homepage.enterFirstcard();
//			adddevicepage.pair(2);
//			adddevicepage.EnterNode(1,switchNames);
//			homepage.enterFirstcard();
//			profilepage.navigateProfileSettingsProfileeditpage();
			// //unable to do 
//			profilepage.SetProfileimage();
//			profilepage.setDOB();
			
			
//			profilepage.removeAddeddevice();
			profilepage.clickApartmentIcon();
			profilepage.clickMenubaricon();
			profilepage.clickAddEditbtn();
			profilepage.DeleteHierarchy(1,Hierarchyname);
			profilepage.clickHierarchy_BackButton();
			
			profilepage.navigateSettingsbtn();
			profilepage.navigateProfileSettingsPage();
			
			
			profilepage.openChangePasswordpage();
			profilepage.entercurrentpassword(Oldpassword);
			profilepage.changepassword(GeneratedPassword);
			
			settingspage.Profilebackbutton();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			landingpage.enterUserName(userName);
			landingpage.enterPassword(GeneratedPassword);
			landingpage.clickSignInButton();
			killAndReopenApp();
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
