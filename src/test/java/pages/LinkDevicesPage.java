package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import wrappers.MobileAppWrappers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class LinkDevicesPage extends MobileAppWrappers {
		
		
		
		@FindBy(xpath = "//*[@resource-id='Start PairingButton']")
		private WebElement startpairingbutton;
		
		
		@FindBy(xpath = "//*[@resource-id='UnlinkedDevice_Button2']")
		private WebElement selectDeviceCheckbox;
		
		@FindBy(xpath = "//*[@resource-id='AssignButton']")
		private WebElement assignButton;
		
		@FindBy(xpath = "//android.view.ViewGroup[@resource-id='drop_down_comp_Container']")
		private WebElement dropDownbuttonContainer;
		
		//@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Select Apartment\"]")
		@FindBy(xpath = "//android.view.ViewGroup[@resource-id='drop_down_comp_view']")
		private WebElement dropDownbutton;
		
		@FindBy(xpath = "//*[@resource-id='drop_down_comp_Container_0']")
		private WebElement selectFirstApartment;
		
		@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_all_button\"]")
		private WebElement Clickallowall;
		
		@FindBy(xpath = "//android.widget.ImageView[@resource-id=\"com.google.android.providers.media.module:id/icon_thumbnail\"])[2]")
		private WebElement selectphotos;
		
		@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.google.android.providers.media.module:id/button_add\"]")
		private WebElement Clickaddoption;
		
		@FindBy(xpath = "//*[@resource-id='UnlinkedDevice_Button7']")
		private WebElement assignDeviceConfirmButton;
		
		@FindBy(xpath = "//*[@resource-id='SingleButton_Button']")
		private WebElement resetConfirmationOkButton;
		
		

		private AndroidDriver driver;
		

			
			public LinkDevicesPage(AndroidDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver, this);
			}

			public void clickStartPairingButton() {	
				clickbyXpath(startpairingbutton, " Start Pairing button ");
			}
			
			public void clickUnlikeDeviceCheckBox() {	
				clickbyXpath(selectDeviceCheckbox, " Click unlinked Device Check box");
			}
			
			public void clickAssignButton() {	
				clickbyXpath(assignButton, " Click Assign buttons ");
			}
			
			public void clickDropDown() {	
				clickbyXpath(dropDownbutton, " Click Hierachy select drop down ");
			}
			
			public void selectFirstApartment() {	
				clickbyXpath(selectFirstApartment, " Select first drop down hierarchy ");
			}
			
			
			public void selectHierachy(int index) throws InterruptedException {
				selectDropdownByIndex(dropDownbuttonContainer, index);
			}
			
			public void clickAssignDeviceConfirmButton() {	
				clickbyXpath(assignDeviceConfirmButton, " Click Assign Device confirm button ");
			}
			
			
			public void clickResetConfirmationOkButton() {
				clickbyXpath(resetConfirmationOkButton, "Ok Button on reset");
			}
		
			
			public void Clickallowall() {	
				clickbyXpath(Clickallowall, " Click Allow ALL ");
			}
			
			public void selectphotos() {	
				clickbyXpath(selectphotos, " Select Photos ");
			}
			

			public void Clickaddoption() {	
				clickbyXpath(Clickaddoption, " Select Add Option ");
			}
			
			public void scrollpage() {

				driver.findElement(AppiumBy.androidUIAutomator(
					    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"+\"));"));
				driver.findElement(By.xpath("//*[@resource-id='Report_UploadScreenshot_plus']"))
			    .click();
			}
			
		    public void selectApartmentByIndex(int optionIndex) throws InterruptedException {

		    	Rectangle rect = dropDownbuttonContainer.getRect();

		        // Step 4: Calculate option coordinates
		        int itemHeight = rect.getHeight();
		        int x = rect.getX() + (rect.getWidth() / 2);
		        int y = rect.getY() + rect.getHeight() + (itemHeight * optionIndex) - (itemHeight / 2);

		        //System.out.println("Dropdown container: x=" + rect.getX() + ", y=" + rect.getY()
		        //        + ", w=" + rect.getWidth() + ", h=" + rect.getHeight());
		        //System.out.println("Tapping option " + optionIndex + " at: x=" + x + ", y=" + y);

		        // Step 5: Tap using W3C mobile gesture
		        Map<String, Object> tapParams = new HashMap<>();
		        tapParams.put("x", x);
		        tapParams.put("y", y);
		        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture", tapParams);

		        Thread.sleep(500);
		    }
		    
		    

	}
