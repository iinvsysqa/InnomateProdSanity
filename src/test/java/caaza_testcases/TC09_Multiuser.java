package caaza_testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
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
import pages.UsersPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class TC09_Multiuser  extends MobileAppWrappers{

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
	SwitchPage switchpage;
	UsersPage userpage;
	@BeforeClass
	public void startTestCase() {
		testCaseName = "TC09_Multiuser";
		testDescription = "Multiuser -Device sharing fullaccess,Partial access,View only access";
		dataSheetName="Automation";
	}

	
	@Test(priority = 8)
	public void TC_09_Multiuser_check() throws Exception {
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
		switchpage= new SwitchPage(driver);
		userpage = new UsersPage(driver);
		
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
			Thread.sleep(3000);
			hierarchypage.enterHierarchyText(1, Hierarchyname);
			hierarchypage.clickCreateHierarchybtn();
			hierarchypage.addHierarchy_oneOption();
			
			
			 
			
			
//			//logout
			homepage.enterFirstcard();
			adddevicepage.pair(2);
			adddevicepage.EnterNode(switchNames);
			homepage.navigateback();
			
			System.out.println("Moving to Full access");
			
			//==========================share full access to user
			userpage.NavigateUserpage();
			userpage.clickSharebutton();
			userpage.clickFullAccessradioButton();
			userpage.SelectApartmentDevices("Apartment");
			userpage.clickdeviceShareButton();
			userpage.enterUsername(loadProp("DUMMYUSER"));
			userpage.clickSendbutton();
			userpage.checkAddedUser(loadProp("DUMMYUSER"));
			userpage.navigateBack();
			
			profilepage.navigateSettingsbtn();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			
			
			
			//loginanotheruser
			landingpage.enterUserName(loadProp("DUMMYUSER"));
			landingpage.enterPassword("Welcome@123");
			landingpage.clickSignInButton();
            
//			navigate to that shared users device and check on/off
			userpage.clickOtherUsersHierarchycontainer();
			userpage.sharedUserHierarchyBox();
			//get analytics value
			
			
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			schedular.enter_Switchpage(1);
			analytics.getenergydurationvalue();
			homepage.navigateback();
			
			//turn on app for 1 min
			Thread.sleep(5000);
			switchpage.checkforDisconnectedBadge("FAIL");
			switchpage.clickSwitchToggle();
			Thread.sleep(60000);
			switchpage.clickSwitchToggle();
			
			//check analytics value updated or not 
			schedular.enter_Switchpage(1);
			analytics.checkenrgyduration(1);
			homepage.navigateback();
			homepage.navigateback();
			
//          navigate to shared user and check created schedule showing to main user or not
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			schedular.enter_Switchpage(1);
			schedular.createSchedules(1, 3, 1, 1,"NewSchedule");//mention switches count ,mention the time to start ,how many schedules need to keep,schedule duration like 1 min or 2 min
			homepage.navigateback();
			homepage.navigateback();
			profilepage.navigateSettingsbtn();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			landingpage.enterUserName(userName);
			landingpage.enterPassword(Oldpassword);
			landingpage.clickSignInButton();
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			schedular.enter_Switchpage(1);
			switchpage.NavigatetoSwitches(1);
			schedular.NavigateOtherSchedulepage();
			schedular.verifyOtherUserscheduleSchedules();
			homepage.navigateback();
			homepage.navigateback();
			homepage.navigateback();
			Thread.sleep(60000*2);//to complete that old schedule
			
			System.out.println("Moving to partial access");
			//==========================change the access to partial access 
			
			userpage.NavigateUserpage();
			userpage.clickSharebutton();
			userpage.clickPartialAccessRadioButton();
			userpage.SelectApartmentDevices("Apartment");
			userpage.clickdeviceShareButton();
			userpage.enterUsername(loadProp("DUMMYUSER"));
			userpage.clickSendbutton();
			userpage.clickContinueButton();
			userpage.checkAddedUser(loadProp("DUMMYUSER"));
			userpage.navigateBack();
			
			profilepage.navigateSettingsbtn();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			
			
			
			//loginanotheruser
			landingpage.enterUserName(loadProp("DUMMYUSER"));
			landingpage.enterPassword("Welcome@123");
			landingpage.clickSignInButton();
            
//			navigate to that shared users device and check on/off
			userpage.clickOtherUsersHierarchycontainer();
			userpage.sharedUserHierarchyBox();
			//get analytics value
			
		
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			schedular.enter_Switchpage(1);
			analytics.getenergydurationvalue();
			homepage.navigateback();
			
			//turn on app for 1 min
			Thread.sleep(5000);
			switchpage.checkforDisconnectedBadge("FAIL");
			switchpage.clickSwitchToggle();
			Thread.sleep(60000);
			switchpage.clickSwitchToggle();
			
			//check analytics value updated or not 
			switchpage.NavigatetoSwitches(1);
			analytics.checkenrgyduration(1);
			
			schedular.checkPartialAccessSchedulepage();
			homepage.navigateback();
			homepage.navigateback();
			homepage.navigateback();

			profilepage.navigateSettingsbtn();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			
			System.out.println("Moving to View only access");
			//==============================change the access to view only access
			landingpage.enterUserName(userName);
			landingpage.enterPassword(Oldpassword);
			landingpage.clickSignInButton();
			
			
			userpage.NavigateUserpage();
			userpage.clickSharebutton();
			userpage.clickViewOnlyAcessRadioButton();
			userpage.SelectApartmentDevices("Apartment");
			userpage.clickdeviceShareButton();
			userpage.enterUsername(loadProp("DUMMYUSER"));
			userpage.clickSendbutton();
			userpage.clickContinueButton();
			userpage.checkAddedUser(loadProp("DUMMYUSER"));
			userpage.navigateBack();
			
			profilepage.navigateSettingsbtn();
			profilepage.clicklogoutbtn();
			profilepage.logoutConfirmationBtn();
			
			
			landingpage.enterUserName(loadProp("DUMMYUSER"));
			landingpage.enterPassword("Welcome@123");
			landingpage.clickSignInButton();
            
//			navigate to that shared users device and check on/off
			userpage.clickOtherUsersHierarchycontainer();
			userpage.sharedUserHierarchyBox();
			
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			Thread.sleep(5000);
			switchpage.checkforDisconnectedBadge("FAIL");
			switchpage.clickSwitchToggle();
//			userpage.checkViewonlyAccesson_off_Toast();
			switchpage.NavigatetoSwitches(1);
			schedular.checkPartialAccessSchedulepage();
			userpage.navigateBack();
			userpage.navigateBack();
			userpage.navigateBack();
			
			//deleting device and account 
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
