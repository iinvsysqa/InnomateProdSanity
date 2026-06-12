package caaza_testcases;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AccountsInfoPage;
import pages.AddDevicePage;
import pages.DeviceMenuPage;
import pages.HomePage;
import pages.LandingPage;
import pages.LinkDevicesPage;
import pages.OtpPage;
import pages.SettingsPage;
import pages.SignUpPage;
import pages.StoreLogPage;
import pages.SwitchPage;
import utils.logReadandWrite;
import wrappers.MobileAppWrappers;

public class ProductionSanity_sZphyer extends MobileAppWrappers {

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
	LinkDevicesPage linkdevicespage;
	int enterNode;
	String fetchSerailnumber_Dual_Threenode;

	@BeforeClass
	public void startTestCase() {
		testCaseName = "Innomate Production device Sanity Check";
		testDescription = "Innomate  deviceSanity Check";

	}

	@Test(priority = 0)
	public void ProductionSanity_Check() throws Exception {
		initAndriodDriver();
		functionaCheck();
	}

	List<String> switchNames = Arrays.asList("Switch1", "Switch2", "Switch3", "Switch4", "Switch5");

	void functionaCheck() throws Exception {
		landingpage = new LandingPage(driver);
		signuppage = new SignUpPage(driver);
		settingspage = new SettingsPage(driver);
		homepage = new HomePage(driver);
		adddevicepage = new AddDevicePage(driver);
		switchpage = new SwitchPage(driver);
		linkdevicespage= new LinkDevicesPage(driver);

		logReadandWrite readwrite = logReadandWrite.getInstance(loadProp("COM"));
		try {
//			readwrite.openPort();

//			landingpage.clickLandingPageNextBtn();			
//			landingpage.enterUserName("Demouserauto");
//			landingpage.enterPassword("Welcome@123");
//			landingpage.clickSignInButton();
			homepage.clickStartPairingButton();
			
			
			
			//homepage.clickFloorSelctionBtn();
			adddevicepage.pair(2);
			enterNode = adddevicepage.EnterNode(switchNames);
			
			//homepage.clickFloorSelctionBtn();
			
			linkdevicespage.clickUnlikeDeviceCheckBox();
			linkdevicespage.clickAssignButton();
			linkdevicespage.clickDropDown();
			//linkdevicespage.selectHierachy(1);
			linkdevicespage.selectFirstApartment();
			//linkdevicespage.selectApartmentByIndex(1);
			linkdevicespage.clickAssignDeviceConfirmButton();
			driver.navigate().back();
			homepage.clickApartmentIcon();
			switchpage.clickSelectPanelbutton();
			switchpage.clickSwitchPageBackButton();
			switchpage.clickSelectPanelbutton();
			//homepage.clickPanel(0);
			Thread.sleep(2000);
			switchpage.ThreenodeclickOnOffButton(enterNode);
			switchpage.ThreenodeclickOnOffButton(enterNode);

			fetchSerailnumber_Dual_Threenode = switchpage.FetchSerailnumber_SingleNode();// newly added

			switchpage.clickNewMenuButton();
			switchpage.clickNewSettingsButton();
			switchpage.clickEnergyMonitoringButton();
			switchpage.clickEnergyMonitoringToggleButton();
			switchpage.clickSaveButton();
			driver.navigate().back();
			switchpage.clickDeviceCard();
			switchpage.temperatureSensorCheck();
			driver.navigate().back();
			switchpage.clickNewMenuButton();
			switchpage.clickNewSettingsButton();
			switchpage.clickResetDeviceButton();
			switchpage.clickResetConfirmationButton();
			linkdevicespage.clickResetConfirmationOkButton();
			driver.navigate().back();
			// switchpage.clickBackButton();

			if (enterNode == 1) {
				nodetype = "SingleNode/15A Device";
			} else if (enterNode == 2) {
				nodetype = "DualNode";
			} else if (enterNode == 3) {
				nodetype = "ThreeNode";
			}
			serialNo = fetchSerailnumber_Dual_Threenode;

//			readwrite.closePort();
		} catch (Exception e) {
			//readwrite.closePort();
			//logpage.CollectLogOnFailure(testCaseName, testDescription);
			fail(e);
		}
	}

}