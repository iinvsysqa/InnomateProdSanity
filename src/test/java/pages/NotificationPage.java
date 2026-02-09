package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utils.Reporter;
import wrappers.GenericWrappers;

public class NotificationPage extends GenericWrappers {

	private AndroidDriver driver;

	public NotificationPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//*[@resource-id='Notification_button']")
	private WebElement Notification_button;
	

	@FindBy(xpath = "//*[@resource-id='Notification_Tab_Users']")
	private WebElement Notification_Tab_Users;
	@FindBy(xpath = "//*[@resource-id='Notification_Tab_Devices']")
	private WebElement Notification_Tab_Devices;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement;

	private WebElement SharePagedeviceselection(String apartmentname) {
		return driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='" + apartmentname + "']"));

	}

	private WebElement Hierarchyinputtext(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyInput_Level_" + level + "']"));

	}

	private WebElement HierarchyAddbutton(int level) {
		return driver.findElement(By.xpath("//*[@resource-id='HierarchyButtonWrapper_Level_" + level + "']"));

	}

	private WebElement sharedUsername(String username, int level) {
		return driver.findElement(By.xpath("//*[@resource-id='UserControl_UserName_" + username + "_" + level + "']"));

	}

	private WebElement checkViewonlyaccessToast() {
		return driver.findElement(
				By.xpath("//android.widget.Toast[@text=\"You don't have permission to perform this action\"]"));

	}

	public void NavigateNotificationPage() {
		if (isElementDisplayedCheck(Notification_button)) {
			clickbyXpath(Notification_button, "Notification Button");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(Notification_button, "Notification Button");
				}

	}
public void clickNotificationTabDevices() {
	clickbyXpath(Notification_Tab_Devices, "Notification tab devices");
}
public void clickNotificationTabUsers() {
	clickbyXpath(Notification_Tab_Users, "Notification tab devices");
}
	
	public void navigateBack() {
		driver.navigate().back();
		}


		public void CheckSharedUserNotification(String Username) {
			By firstNotificationTitle = By.xpath(
				    "(//*[starts-with(@resource-id, 'Notification_Title_')])[1]"
				);

				WebElement titleElement = driver.findElement(firstNotificationTitle);
				
				verifyTextContainsByXpath(titleElement, Username+" has granted you Full Access to \"Apartment\".", "Shared User notification");
		}
		public void checkResetDeviceNotification() {
			
			By firstNotificationTitle = By.xpath(
					"(//*[starts-with(@resource-id, 'Notification_Title_')])[1]"
					);
			
			WebElement titleElement = driver.findElement(firstNotificationTitle);
			
			verifyTextContainsByXpath(titleElement, "The Panel1 has been factory reset. As a result, all previously paired users have been removed. Please pair again to continue using the device.", "Reset device notification");
			
		}
}
