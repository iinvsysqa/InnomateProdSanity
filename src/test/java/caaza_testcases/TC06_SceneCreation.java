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

public class TC06_SceneCreation extends MobileAppWrappers{

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
		testCaseName = "TC_06_SceneCreation";
		testDescription = "Scene Creation for Switches ";
		dataSheetName="Automation";
	}

	
	@Test(priority = 5)
	public void TC_06_SceneCreation() throws Exception {
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
			
			
//			BCDCC BCCCC BDBBD
			homepage.enterFirstcard();
			adddevicepage.pair(2);
			adddevicepage.EnterNode(switchNames);
			homepage.navigateback();
			
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			schedular.enter_Switchpage(1);
			analytics.getenergydurationvalue();
			homepage.navigateback();
			homepage.navigateback();
			homepage.navigateback();
			
			scenecreation.navigateScenecreationpage();
			scenecreation.enterSceneName("A");			
			scenecreation.setSceneCreation_Allswitches(1, "On");//it should be in this format On ,Off
			scenecreation.clickScenecreationBtn();
//			scenecreation.checkSceneCreation_successToast("Scene Created Successfully");
			scenecreation.navigateScenecreationpage();
			scenecreation.enterSceneName("B");			
			scenecreation.setSceneCreation_Allswitches(1, "Off");//it should be in this format On ,Off
			scenecreation.clickScenecreationBtn();
//			scenecreation.checkSceneCreation_successToast("Scene Created Successfully");
			scenecreation.clickAllSceneStartButtons();
			
			
//			check scene by analytics page
			homepage.enterFirstcard();
			homepage.clickPanel(0);
			schedular.enter_Switchpage(1);
			analytics.checkenrgyduration(1);
			homepage.navigateback();
			homepage.navigateback();
			homepage.navigateback();
			
			
			//edit switchboardname
			homepage.enterFirstcard();
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
			
			
			//reset device
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
