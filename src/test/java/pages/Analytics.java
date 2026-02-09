package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utils.Reporter;
import wrappers.GenericWrappers;

public class Analytics  extends GenericWrappers {

	private static AndroidDriver driver;
	public Analytics(AndroidDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor) driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	@FindBy(xpath = "//*[@resource-id='energy_used_live_units_watts']")
	private WebElement energyUsedunit;
	@FindBy(xpath = "//*[@resource-id='acitve_today_energy']")
	private WebElement enrgyDurationmin;
	@FindBy(xpath = "//*[@resource-id='duration_expand_icon']")
	private WebElement durationExpandicon;
	
	@FindBy(xpath = "//*[@resource-id='EnergyDuration_TouchableOpacity']")
	private WebElement energyDuration;
	
	@FindBy(xpath="//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[7]/com.horcrux.svg.SvgView/com.horcrux.svg.GroupView/com.horcrux.svg.CircleView[1]")
	private WebElement deviceONOFFButton;
	@FindBy(xpath = "//*[@resource-id='Tab_Analytics_Icon']")
	private WebElement analyticsButton;
	@FindBy(xpath = "//*[@resource-id='Device_BackIcon']")
	private WebElement BackButton;
	@FindBy(xpath = "//*[@resource-id='RetryButton']")
	private WebElement RetryButton;
//	@FindBy(xpath = "")
//	private WebElement ;
//	@FindBy(xpath = "")
//	private WebElement ;
//	@FindBy(xpath = "")
//	private WebElement ;

	
	
	public void navigateAnalyticsPage() {



		if (isElementDisplayedCheck(analyticsButton)) {
			clickbyXpath(analyticsButton, "analytics button");
			}else if(isElementDisplayedCheck(RetryButton)) {
				clickbyXpathwithoutReport("Retry button", RetryButton);
				clickbyXpath(analyticsButton, "analytics button");
				}
	
	}
	
	public void navigateswitchpage_getenergyduration() {
		
	}
	String oldvalue;
	public String getenergydurationvalue() {

		waitForElementToDisplayNumber(enrgyDurationmin,20);
		 oldvalue = enrgyDurationmin.getText();
		 oldvalue.contentEquals("Fetching");
		 Reporter.reportStep("Analytics value before Start of the session : " + oldvalue, "PASS");
		System.out.println(oldvalue);
	return oldvalue;
	
	}
	
	
	
	// --- NEW ROBUST WAIT METHOD ---
    public static String waitForElementToDisplayNumber(WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);

        // This defines the custom condition: "Keep waiting until text contains a digit"
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    String text = element.getText();
                    // Regex checks if text contains at least one digit (0-9)
                    // It returns true ONLY if text is not "Fetching", not empty, and has numbers.
                    return text != null && text.matches(".*\\d+.*"); 
                } catch (StaleElementReferenceException e) {
                    return false; // Element refreshed, try again
                }
            }
        });

        // Once wait is over, return the valid text
        return element.getText();
    }

	
	public void navigatehomepage() {

		clickbyXpath(BackButton, "Back button");
	}
	public boolean checkenrgyduration(int value) throws Exception {	
		boolean bReturn = false;
		waitForElementToDisplayNumber(enrgyDurationmin,15);
//		clickbyXpath(enrgyDurationmin, "energy duration");
		
		int newvalue=extractMinutes(oldvalue)+value;
		System.out.println("new value :"+newvalue);
		String text = enrgyDurationmin.getText();
		System.out.println("after analytics :"+text);
		if (extractMinutes( enrgyDurationmin.getText())==newvalue) {
			
			Reporter.reportStep("Analytics value updated after session : " + text, "PASS");
			bReturn = true;
		}
		else {
			Reporter.reportStep("Wrong Analytics value updated: " + text, "FAIL");
			
		}
		return bReturn;
		
	}
	
//	private int parseTimeToSeconds(String timeStr) {
//	    String[] parts = timeStr.split("m\\s*|s"); // Split by "m" and "s" (regex)
//	    int minutes = Integer.parseInt(parts[0].trim());
//	    int seconds = Integer.parseInt(parts[1].trim());
//	    return (minutes * 60) + seconds;
//	}
//	private int parseTimeToMinutes(String timeStr) {
//	    String[] parts = timeStr.split("m\\s*"); // Split at "m" (e.g., "5m 30s" → ["5", "30s"]
//	    return Integer.parseInt(parts[0].trim()); // Return only the minutes part
//	}
//	
//	private String formatSecondsToTime(int totalSeconds) {
//	    int minutes = totalSeconds / 60;
//	    int seconds = totalSeconds % 60;
//	    return String.format("%dm %02ds", minutes, seconds);
//	}
}
