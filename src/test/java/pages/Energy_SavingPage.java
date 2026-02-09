package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class Energy_SavingPage extends GenericWrappers{

	private AndroidDriver driver;
	
	public Energy_SavingPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
	@FindBy(xpath = "//*[@resource-id='energy_save_setting']")
	private WebElement ESMBtn;

	@FindBy(xpath = "//*[@resource-id='Header']")  //Energy Saving Modes
	private WebElement ESMheader;
	
	@FindBy(xpath = "//*[@resource-id='AutoPowerON/OFF_Switch']")
	private WebElement ESMToggle;
	
	@FindBy(xpath = "//*[@resource-id='DeviceON/OFF_Ok_Button']")
	private WebElement ESMOkBtn;
	@FindBy(xpath = "//*[@resource-id='Device_BackIcon']")
	private WebElement ESMbackBtn;
	
	@FindBy(xpath = "//*[@resource-id='Energy_save_svg_1']")
	private WebElement ESMSixty;
	@FindBy(xpath = "//*[@resource-id='Energy_save_svg_4']")
	private WebElement ESMForty;
	@FindBy(xpath = "//*[@resource-id='Energy_save_svg_6']")
	private WebElement ESMTwenty;
	
	@FindBy(xpath = "//android.widget.Toast[@text=\"Energy saving mode updated successfully\"]")
	private WebElement ESMSuccessfullyToast;
	
	
	public void ClickEsmButton() {	
		clickbyXpath(ESMBtn, " Click energy Saving Mode ");
	}
	public void ClickEsmToggle() {	
		clickbyXpath(ESMToggle, " Click energy Saving Mode ToggleButton ");
	}
	public void ClickUpto60percent() {	
		clickbyXpath(ESMSixty, " Click energy Saving Mode 60% saving ");
	}
	public void ClickUpto40percent() {	
		clickbyXpath(ESMForty, " Click energy Saving Mode 40% saving ");
	}
	public void ClickUpto20percent() {	
		clickbyXpath(ESMTwenty, " Click energy Saving Mode 20% saving ");
	}
	public void ClickOkbutton() {	
		clickbyXpath(ESMOkBtn, " Click energy Saving Mode OK button ");
	}
	public void Clickbackbutton() {	
		clickbyXpath(ESMbackBtn, " Click energy Saving Mode Backbutton ");
	}
	public void CheckEsmHeader() {	
		verifyTextContainsByXpath(ESMheader, "Energy Saving Modes", "ESM header");
	}
	public void checkToast() {
		verifyTextContainsByXpath_Toast(ESMSuccessfullyToast, "Energy saving mode updated successfully", "Energy saving mode updated successfully Toast message");
	}
	
	
	
}
