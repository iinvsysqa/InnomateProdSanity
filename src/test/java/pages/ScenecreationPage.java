package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class ScenecreationPage extends GenericWrappers {
	public static AndroidDriver driver;
	

//	public AndroidElement element;

	public ScenecreationPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor) driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/ProfileImageContainer, com.CaaZa_Smart:id/HierarchyTextContainer, com.CaaZa_Smart:id/SCH_ADD_PLUS_ICON\"]")
	private WebElement ScenePlusbtn	;
	@FindBy(xpath = "//*[@resource-id='AddNewScene_Icon']")
	private WebElement ScenePlusIcon	;
	@FindBy(xpath = "//*[@resource-id='sceneNameInput']")
	private WebElement sceneNametextbox;
	@FindBy(xpath = "//*[@resource-id='create_Scene_HeaderTitle']")
	private WebElement ScenepageTitle;
	@FindBy(xpath = "//*[@resource-id='create_Scene_RoomList']")
	private WebElement ScenepageRoomlist;
	@FindBy(xpath = "//*[@resource-id='Scene_RoomBreadcrumb_68e63925c828e20e93ccac8f']")
	private WebElement Roomsubtitle;
	@FindBy(xpath = "//*[@resource-id='create_Scene_ButtonContainer']")
	private WebElement Createscenebutton;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
//	@FindBy(xpath = "//*[@resource-id='']")
//	private WebElement ;
	
	private WebElement Scenepage_Switchcontainer(int container) {
	    // Use content-desc instead of resource-id
	    return driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='com.CaaZa_Smart:id/Scene_SwitchContainer_" + container + "']"));
	}

	private WebElement scenepageSwitchRadiobtn(int switchId, String switchMode) {
	    // Update the XPath to correctly reference the resource ID
	    return driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='com.CaaZa_Smart:id/Scene_Switch_" + switchId + "_" + switchMode + "']"));
	}
	
	private WebElement scenecreated_SuccessToast() {
		return driver.findElement(By.xpath("//android.widget.Toast[@text=\"Scene Created Successfully\"]"));
	}
	private WebElement sceneTriggered_SuccessToast() {
		return driver.findElement(By.xpath("//android.widget.Toast[@text=\"Scene Created Successfully\"]"));
	}
	
	public void navigateScenecreationpage() {
		if(isElementDisplayedCheck(ScenePlusbtn)) {
		clickbyXpath(ScenePlusbtn, "SceneCreationBtn");
		}else {
			clickbyXpath(ScenePlusIcon, "SceneCreationBtn");
			
		}
	}
	public void enterSceneName(String sceneName) throws InterruptedException {
		if(isElementDisplayedCheck(sceneNametextbox)) {
		entervaluebyXpath(sceneNametextbox, "SceneNameTextbox", sceneName);}
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().back();
		navigateScenecreationpage();
		entervaluebyXpath(sceneNametextbox, "SceneNameTextbox", sceneName);
	}
	public void clickScenecreationBtn() {
		clickbyXpath(Createscenebutton, "CreateSceneBtn");
	}
	
	public void checkSceneCreation_successToast(String Toast) {
		verifyTextContainsByXpath_Toast(scenecreated_SuccessToast(), Toast , "SceneCreation_successToast");
	}
	public void checkSceneTriggered_successToast(String Toast) {
		verifyTextContainsByXpath_Toast(sceneTriggered_SuccessToast(), Toast , "sceneTriggered_SuccessToast");
	}
	
	//set node count and switch mode either on/off the switch 
	public void setSceneCreation_Allswitches(int node,String switchMode) {
		for (int i = 1; i < node+1; i++) {
			// Find the switch container for the current node
            WebElement container = Scenepage_Switchcontainer(i);

            // Click the "On" radio button within the container
            WebElement onRadioButton = scenepageSwitchRadiobtn(i,switchMode);
            if (isElementDisplayedCheck(onRadioButton)) {
                clickbyXpath(onRadioButton, "Radio button");
            }
		}
		
	}
//	public void clickAllSceneStartButtons() throws InterruptedException {
//        // Locate all scene cards
//        List<WebElement> sceneCards = driver.findElements(By.xpath("//android.widget.LinearLayout[contains(@resource-id, 'SceneCard_')]"));
//
//        // Iterate through each scene card and click the corresponding start button
//        for (WebElement card : sceneCards) {
//            // Find the start button within the current card
//            WebElement startButton = card.findElement(By.xpath(".//android.widget.Button[contains(@resource-id, 'SceneStartBtn_')]"));
//
//            // Click the start button if it's displayed
//            if (startButton.isDisplayed()) {
//                startButton.click();
//                checkSceneTriggered_successToast("Scene triggered successfully");
//                Thread.sleep(3000);
//            }
//        }
//	}
	public void clickAllSceneStartButtons() throws InterruptedException {
	    // Locate all scene card containers dynamically using content-desc
	    List<WebElement> sceneCards = driver.findElements(
	        By.xpath("//android.view.ViewGroup[contains(@content-desc, 'SceneCardContainer_')]")
	    );

	    System.out.println("Found " + sceneCards.size() + " scene cards.");

	    // Iterate through each card
	    for (WebElement card : sceneCards) {
	        try {
	            // Use double quotes for the text to handle the apostrophe in "Let's Start"
	            WebElement startButton = card.findElement(
	                By.xpath(".//android.widget.TextView[contains(@content-desc, 'SceneName_') and @text=\"Let's Start\"]")
	            );

	            if (startButton.isDisplayed()) {
	                System.out.println("Clicking on: " + startButton.getAttribute("content-desc"));
	                // Ensure your clickbyXpath method accepts a WebElement or use standard click
	                startButton.click(); 
	                Thread.sleep(60000);
	            }
	        } catch (Exception e) {
	            // It's helpful to print the actual error while debugging
	            System.out.println("Button not found in this card. Error: " + e.getMessage());
	        }
	    }
	}

	
}
