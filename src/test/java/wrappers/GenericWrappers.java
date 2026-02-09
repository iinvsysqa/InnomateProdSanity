package wrappers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.google.common.collect.ImmutableMap;
import org.testng.Assert;

import utils.ADBconnections;
import utils.Reporter;
import utils.logReadandWrite;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import java.util.Map;
import java.util.HashMap;

public class GenericWrappers {

	public String packages = loadProp("APP_PACKAGE");
	public static AndroidDriver driver;
	public WebDriverWait wait;
	static ExtentTest test;
	static ExtentReports report;
	public String sUrl, primaryWindowHandle, sHubUrl, sHubPort;
	public int node = Integer.parseInt(loadProp("NODE"));
	static String pkg = "com.iinvsys.caazasmart";

	public static String loadProp(String property) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(property);
	}

	public static String updateProperty(String key, String newValue) {
		Properties props = new Properties();
		try (FileInputStream in = new FileInputStream("./config.properties")) {
			props.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Update the value
		props.setProperty(key, newValue);

		try (FileOutputStream out = new FileOutputStream("./config.properties")) {
			props.store(out, null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newValue;
	}

	@SuppressWarnings("deprecation")
	public static void allowpermissions() throws IOException {
		try {
			Runtime.getRuntime()
					.exec("adb shell pm grant com.iinvsys.caazasmart android.permission.ACCESS_FINE_LOCATION");
			Runtime.getRuntime().exec("adb shell pm grant com.iinvsys.caazasmart android.permission.BLUETOOTH_SCAN");
			Runtime.getRuntime().exec("adb shell pm grant com.iinvsys.caazasmart android.permission.BLUETOOTH_CONNECT");
			Runtime.getRuntime().exec("adb shell pm grant com.iinvsys.caazasmart android.permission.CAMERA");
			Runtime.getRuntime()
					.exec("adb shell pm grant com.iinvsys.caazasmart android.permission.POST_NOTIFICATIONS");
			Runtime.getRuntime()
					.exec("adb shell pm grant com.iinvsys.caazasmart android.permission.READ_EXTERNAL_STORAGE");
			Runtime.getRuntime()
					.exec("adb shell pm grant com.iinvsys.caazasmart android.permission.WRITE_EXTERNAL_STORAGE");
			// CRITICAL for Toast/Logcat verification on Android 15
	        Runtime.getRuntime().exec("adb shell pm grant " + pkg + " android.permission.READ_LOGS");
	        Runtime.getRuntime().exec("adb shell pm grant " + pkg + " android.permission.DUMP");
	        
	        // Note: For Android 15, some permissions might need 'appops' if 'pm grant' fails
	        Runtime.getRuntime().exec("adb shell appops set " + pkg + " READ_LOGS allow");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean initAndriodDriver() throws FileNotFoundException, IOException, InterruptedException {

		boolean bReturn = false;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./config.properties")));
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", prop.getProperty("PLATFORM_NAME"));
			caps.setCapability("appium:platformVersion", prop.getProperty("PLATFORM_VERSION"));
			caps.setCapability("appium:udid", prop.getProperty("UDID"));
			caps.setCapability("appium:deviceName", prop.getProperty("DEVICE_NAME"));

			caps.setCapability("appium:automationName", "uiautomator2");
			caps.setCapability("appium:ignoreHiddenApiPolicyError", "true");
			caps.setCapability("appium:newCommandTimeout", 999999);
			caps.setCapability("appium:waitForIdleTimeout", 0);
			caps.setCapability("appium:noReset", true);
			caps.setCapability("appium:autoGrantPermissions", true);
			caps.setCapability("appium:disableWindowAnimation", true);
//			caps.setCapability("relaxedSecurity", true);

			// keepSessionAlive(driver);

			driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);

			Reporter.reportStep("Appium server started successfully ", "INFO");
			Reporter.reportStep("Platform name: " + prop.getProperty("PLATFORM_NAME") + "<br>" + "Platform version: "
					+ prop.getProperty("PLATFORM_VERSION") + "<br>" + "Device UDID: " + prop.getProperty("UDID")
					+ "<br>" + "Device Name: " + prop.getProperty("DEVICE_NAME") + "<br>" + "App Revision No: "
					+ prop.getProperty("APP_REVISION_NO") + "<br>" + "Device Revision No: "
					+ prop.getProperty("DEVICE_REVISION_NO") + "<br>" + "Router Name: " + prop.getProperty("WIFINAME")
					+ "<br>" + "Remote Router Name: " + prop.getProperty("REMOTEWIFINAME"),

					"INFO");

			String appPackage = prop.getProperty("APP_PACKAGE");
			if (driver.isAppInstalled(appPackage)) {

				System.out.println("App is already installed. Launching the app...");
				turnOnBT();
				driver.activateApp(appPackage); // Open the app
			} else {
				System.out.println("App is not installed. Installing and launching...");
				turnOnBT();
				if (loadProp("PLATFORM_VERSION").contains("15")) {
					appinstallationforhigherversion();
				} else {
					driver.installApp(prop.getProperty("APP_PATH"));
					driver.activateApp(appPackage); // Launch the app after installation

				}
//				driver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant com.iinvsys.caazasmart android.permission.ACCESS_FINE_LOCATION"));
//				driver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant com.iinvsys.caazasmart android.permission.BLUETOOTH_SCAN"));
//				driver.executeScript("mobile: shell", ImmutableMap.of("command", "pm grant com.iinvsys.caazasmart android.permission.BLUETOOTH_CONNECT"));

				allowpermissions();

			}

			if (driver.isAppInstalled(appPackage)) {
				Reporter.reportStep("The app:" + appPackage + " launched successfully", "PASS");
			} else {
				Reporter.reportStep("The app:" + appPackage + " not launched", "FAIL");

			}
			Reporter.reportStep("App opened successfully", "INFO");
			allowpermissions();
			bReturn = true;

		} catch (MalformedURLException e) {
			System.out.println("App not launched" + e.getMessage());
			e.printStackTrace();
			Reporter.reportStep("The app not launched", "FAIL");
		}
		return bReturn;
	}

	
	
	
	
	
	
	public static void keepSessionAlive(AndroidDriver driver) {
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(() -> {
			try {
				driver.currentActivity();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 0, 5, TimeUnit.MINUTES);
	}

	public static boolean launchApplication(String url) {
		boolean bReturn = false;
		try {
			driver.get(url);
			Reporter.reportStep("The browser:" + url + " launched successfully", "PASS");
			bReturn = true;
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.reportStep("The browser:" + url + " could not be launched", "FAIL");

		}
		return bReturn;
	}

	public static boolean clickbyXpath(WebElement xpath, String button) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			xpath.click();
			Reporter.reportStep(button + " is clicked Successfully.", "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The Field " + button + " could not be clicked.", "FAIL");
		}
		return bReturn;

	}

	public static boolean clickbyXpathwithoutReport(WebElement xpath, String button) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			xpath.click();
			Reporter.reportStep(button + " is clicked Successfully.", "PASS");
			bReturn = true;

		} catch (Exception e) {
			// Reporter.reportStep("The Field "+button+" could not be clicked.", "FAIL");
		}
		return bReturn;

	}

	public static boolean clickbyXpathwithoutReport(String button, WebElement xpath) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			xpath.click();
			Reporter.reportStep(button + " is clicked Successfully.", "PASS");
			bReturn = true;

		} catch (Exception e) {
			// Reporter.reportStep("The Field "+button+" could not be clicked.", "FAIL");
		}
		return bReturn;

	}

	public boolean verifyTitle(String title) {
		boolean bReturn = false;
		try {
			if (driver.getTitle().equalsIgnoreCase(title)) {
				Reporter.reportStep("The title of the page matches with the value :" + title, "PASS");
				bReturn = true;
			} else {
				Reporter.reportStep(
						"The title of the page:" + driver.getTitle() + " did not match with the value :" + title,
						"SUCCESS");
			}

		} catch (Exception e) {
			Reporter.reportStep("The title did not match", "FAIL");
		}

		return bReturn;
	}

	public boolean selectById(WebElement id, int value, String fieldName) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(id, 10);
			new Select(id).selectByIndex(value);
			Reporter.reportStep("The element with id: " + fieldName + " is selected with value :" + value, "PASS");

			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("The value: " + value + " could not be selected.", "FAIL");
		}
		return bReturn;
	}

	public boolean entervaluebyXpath(WebElement xpath, String fieldname, String value) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			xpath.sendKeys(value);
			Reporter.reportStep(fieldname + " field is entered with value : " + value, "PASS");

		} catch (Exception e) {
			Reporter.reportStep("The value: " + value + " could not be entered.", "FAIL");
		}
		return bReturn;
	}

	public boolean entertoiFrame(WebElement xpath, String fName) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			WebElement frame = xpath;
			driver.switchTo().frame(frame);
			Reporter.reportStep("iframe " + fName + " entered successfully", "PASS");
			bReturn = true;

		} catch (Exception e) {
			Reporter.reportStep("iframe could not be entered :", "FAIL");
		}
		return bReturn;
	}

	public boolean selectByVisibleText(WebElement xpath, String fieldName) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			List<WebElement> size = new Select(xpath).getOptions();
			for (WebElement s : size) {
				if (s.isEnabled()) {
					new Select(xpath).selectByVisibleText(s.getText());
					break;
				}
				Reporter.reportStep("The dropdown: " + fieldName + " is selected", "PASS");
				bReturn = true;
			}
		} catch (Exception e) {
			Reporter.reportStep("The dropdown: " + fieldName + " is not selected", "FAIL");
		}
		return bReturn;
	}

	public boolean verifyTextContainsByXpath(WebElement xpath, String text, String field) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			String sText = xpath.getText();
			System.out.println(sText);
			if (sText.trim().contains(text)) {
				Reporter.reportStep(field + "contains " + text, "PASS");
				bReturn = true;
			} else {
				Reporter.reportStep(field + " did not contain :" + text, "FAIL");
			}
		} catch (Exception e) {
			Reporter.reportStep(field + " not displayed", "FAIL&RUN");
			e.printStackTrace();
		}
		return bReturn;
	}

	public boolean verifyTextContainsByXpath_Toast(WebElement xpath, String text, String field) {
		boolean bReturn = false;
		try {
			expWaitTillElementDisplay(xpath, 10);
			String sText = xpath.getText();
			System.out.println(sText);
			if (sText.trim().contains(text)) {
				Reporter.reportStep(field + "contains " + text, "PASS");
				bReturn = true;
			} else {
				Reporter.reportStep(field + " did not contain :" + text, "WARNING");
			}
		} catch (Exception e) {
			Reporter.reportStep(field + " not displayed", "INFO");
			e.printStackTrace();
		}
		return bReturn;
	}

	public static void quitBrowser() {
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			Reporter.reportStep("The browser could not be closed.", "FAIL");
		}

	}

	public static boolean expWaitTillElementDisplay(WebElement xpath, int seconds) {
		boolean bReturn = false;
		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(seconds));
			wait.pollingEvery(Duration.ofMillis(500));
			wait.ignoring(NoSuchElementException.class);
			wait.ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.visibilityOf(xpath));
			bReturn = true;
		} catch (Exception e) {
			System.out.println(e);

		}
		return bReturn;

	}

	// ========================================

	public int extractintvalue(String str) {
		// Use regular expression to remove all non-digit characters
		String numbersOnly = str.replaceAll("\\D+", "");

		// Convert the extracted string to an integer (optional)
		int extractedValue = Integer.parseInt(numbersOnly);

		// System.out.println("Extracted numbers: " + numbersOnly);
		System.out.println("Extracted integer value: " + extractedValue);
		return extractedValue;
	}

	public static int extractMinutes(String timeText) {
		// Regular expression to find digits followed by 'm'
		java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("(\\d+)m").matcher(timeText);

		if (matcher.find()) {
			// group(1) is the captured number before 'm'
			return Integer.parseInt(matcher.group(1));
		} else {
			// If 'm' not found, you can decide what to return (0 or -1)
			return 0;
		}
	}

	public static String randomCharacters(int num, int mode) {
		String numbers = "123456789";
		String alphabetscaps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String alphabetsmall = "abcdefghijklmnopqrstuvwxyz";
		String specialcharacters = "@&*";
		String modes = null;
		switch (mode) {
		case 1:
			modes = alphabetscaps;
			break;
		case 2:
			modes = alphabetsmall;
			break;
		case 3:
			modes = numbers;
			break;
		case 4:
			modes = specialcharacters;
			break;
		default:
			System.out.println("Enter mode from 1 -3");
		}

		// Create a StringBuilder to store the random numbers
		StringBuilder sb = new StringBuilder();

		// Create an object of Random class
		Random random = new Random();

		// Specify the length of the random string
		int length = num;

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(modes.length());
			char randomNum = modes.charAt(index);
			sb.append(randomNum);
		}
		String randomString = sb.toString();
		return randomString;
	}

	@SuppressWarnings("deprecation")
	public static boolean turnOnBT() {
		boolean bReturn = false;

		try {
			Runtime.getRuntime().exec("adb shell svc bluetooth enable");
			// Reporter.reportStep("Bluetooth turned on successfully", "PASS");
			bReturn = true;
		} catch (IOException e) {
			e.printStackTrace();
			Reporter.reportStep("iframe could not be entered :", "FAIL");
		}
		return bReturn;

	}

	@SuppressWarnings("deprecation")
	public boolean turnOffBT() throws Exception {

		boolean bReturn = false;
		try {
			Runtime.getRuntime().exec("adb shell svc bluetooth disable");
			Reporter.reportStep("Bluetooth turned OFF successfully", "PASS");
			bReturn = true;
		} catch (IOException e) {
			e.printStackTrace();
			Reporter.reportStep("iframe could not be entered :", "FAIL");
		}

		return bReturn;
	}

	public void closeApp() {

		try {
			if (driver != null) {
				// Kill the app (terminate it)
				driver.terminateApp(packages);
				Reporter.reportStep("The app was killed successfully.", "PASS");
			}
		} catch (Exception e) {
			Reporter.reportStep("The app could not killed .", "FAIL");
		}
	}

	public void openapp() {
		try {

			// Kill the app (terminate it)
			driver.activateApp(packages);
			allowpermissions();
			Reporter.reportStep("The app was opened successfully.", "PASS");

		} catch (Exception e) {
			Reporter.reportStep("The app  not opened .", "FAIL");
		}
	}

	public void killAndReopenApp() {
		try {
			if (driver != null) {
				// Kill the app (terminate it)
				driver.terminateApp(packages);
				Reporter.reportStep("The app was killed successfully.", "PASS");

				// Wait for a few seconds before reopening the app
				Thread.sleep(3000);

				// Reopen the app, it should maintain its previous state (same page)
				driver.activateApp(packages);
				allowpermissions();
				Thread.sleep(3000);
				Reporter.reportStep("The app was reopened successfully.", "PASS");
			}
		} catch (Exception e) {
			Reporter.reportStep("The app could not be killed and reopened.", "FAIL");
		}
	}

	@SuppressWarnings("deprecation")
	public void enableWiFi() {

		try {
			// Runtime.getRuntime().exec("adb shell svc bluetooth disable");
			Runtime.getRuntime().exec("adb shell svc wifi enable");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void disableWiFi() {

		try {
			// Runtime.getRuntime().exec("adb shell svc bluetooth disable");
			Runtime.getRuntime().exec("adb shell svc wifi disable");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void switchToSpecificWifiUsingCommand(String wifi, String Password) {
		try {
			String command = "nmcli dev wifi connect '" + wifi + "' password '" + Password + "'";
			@SuppressWarnings("deprecation")
			Process process = Runtime.getRuntime().exec(command);
			process.waitFor();
			System.out.println("Switched to Wi-Fi network: YourWiFiSSID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Boolean yes = true;

	@SuppressWarnings("deprecation")
	public void connectToWiFi(String wifiName, String wifiPassword) throws Exception {
		try {

			// Open WiFi settings on the Android device
			Runtime.getRuntime().exec("adb shell svc wifi enable");
			Runtime.getRuntime().exec("adb shell am start -a android.settings.WIFI_SETTINGS");
			// Wait for the WiFi settings to open
			Thread.sleep(3000);

			// Scroll to the WiFi network by name
			WebElement wifiElement = driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\""
							+ wifiName + "\"))"));

			// Click on the WiFi network
			clickbyXpath(wifiElement, "Clicked on " + wifiName + " on Wi-Fi page");

			// Check if the password entry field is displayed
			try {
				Thread.sleep(3000);
				WebElement enterPasswordField = driver.findElement(
						AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.settings:id/password\"]")); // Replace
																															// with
																															// the
																															// actual
																															// XPath
				WebElement enterPasswordFieldOnePlus = driver.findElement(AppiumBy.xpath(
						"(//android.widget.LinearLayout[@resource-id=\"com.oplus.wirelesssettings:id/edittext_container\"])[1]")); // Replace
																																	// with
																																	// the
																																	// actual
																																	// XPath
				if (isElementDisplayedCheck(enterPasswordField)) {
					// Enter the WiFi password
					enterValueByXpathwifipage(enterPasswordField, "Wi-Fi password", wifiPassword);

					// Click on the connect button
					WebElement connectButton = driver
							.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Connect\"]"));
					// Replace with the actual XPath
					if (isElementDisplayedCheck(connectButton)) {

						clickbyXpath(connectButton, "Connect button");

						Thread.sleep(3000);
					}

				}

				else if (isElementDisplayedCheck(enterPasswordFieldOnePlus)) {
					enterValueByXpathwifipage(enterPasswordFieldOnePlus, "Wi-Fi password", wifiPassword);
					WebElement savebutton = driver.findElement(AppiumBy.xpath(
							"//android.widget.TextView[@resource-id=\"com.oplus.wirelesssettings:id/menu_save\"]"));
					if (isElementDisplayedCheck(savebutton)) {
						clickbyXpath(savebutton, "save button");

						Thread.sleep(3000);
					}

				} else {
					System.out.println("Already connected or password is saved.");

				}
			} catch (NoSuchElementException e) {
				System.out.println("WIFI Password is Already Provided, continuing to the next step.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		checkappinforeground();
	}

	// Helper method to check if the element is displayed
	public boolean isElementDisplayed(WebElement element, String Field) {
		try {
			expWaitTillElementDisplay(element, 10);// Introduce a small delay before checking visibility

			if (element.isDisplayed()) {

				Reporter.reportStep(Field + "  Element displayed", "PASS");
			} else {
				Reporter.reportStep(Field + "Element not displayed", "FAIL");

			}
			return true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep(Field + "Element not displayed", "FAIL");
			return false;
		}
	}

	public boolean isElementDisplayednext(WebElement element, String Field) {
		try {
			expWaitTillElementDisplay(element, 10);// Introduce a small delay before checking visibility

			if (element.isDisplayed()) {

				Reporter.reportStep(Field + "  Element displayed", "PASS");
			} else {
				Reporter.reportStep(Field + "Element not displayed", "FAIL");

			}
			return true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep(Field + "Element not displayed", "FAIL");
			return false;
		}
	}

//	public boolean retryWait(WebElement element) {
//		try {
//			Thread.sleep(80*1000);  // Introduce a small delay before checking visibility
//			Reporter.reportStep(element+"Element displayed", "PASS");
//			return element.isDisplayed();
//		} catch (NoSuchElementException | InterruptedException e) {
////			Reporter.reportStep(element+"Element not displayed", "INFO");
//			return false;
//		}
//	}

	public void enterValueByXpathwifipage(WebElement element, String fieldName, String value) {
		element.sendKeys(value);
		System.out.println("Entered value in " + fieldName + ": " + value);
	}

	public static void runPythonScript() {
		try {
			// Update the path to the Python interpreter and the Python script
			ProcessBuilder processBuilder = new ProcessBuilder("C:/Python312/python.exe",
					"C:/Users/Invcuser_106/Desktop/Python code/serialport.py");
			// Start the process
			Process process = processBuilder.start();

			// Capture the script output (stdout)
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			System.out.println("Output of the Python script:");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			// Wait for the process to complete
			int exitCode = process.waitFor();
			System.out.println("Python script exited with code: " + exitCode);

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		driver.terminateApp(packages);
		driver.quit();
	}

	public void checkappinforeground() throws Exception {
		if (driver.queryAppState(packages) != ApplicationState.RUNNING_IN_FOREGROUND) {
			driver.activateApp(packages); // Bring it back
			Thread.sleep(3000);
		}
	}

	public boolean connectivitycheck(WebElement element, String field) {

		try {
			expWaitTillElementDisplay(element, 20);// Introduce a small delay before checking visibility

			if (element.isDisplayed()) {

				Reporter.reportStep(field + "  Element displayed", "PASS");
			} else {
				Reporter.reportStep(field + "Element not displayed", "INFO");

			}
			return true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep(field + "Element not displayed", "INFO");
			return false;
		}

	}

	public boolean isiconDisplayed(WebElement element, String field) {
		try {
			expWaitTillElementDisplay(element, 10);// Introduce a small delay before checking visibility

			if (element.isDisplayed()) {

				Reporter.reportStep(field + "  Element displayed", "PASS");
			} else {
				Reporter.reportStep(field + "Element not displayed", "INFO");

			}
			return true;
		} catch (NoSuchElementException e) {
			Reporter.reportStep(field + "Element not displayed", "INFO");
			return false;
		}
	}

	public WebElement scrollToText(String text) {
		try {
			return driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + text
							+ "\"));"));

		} catch (Exception e) {
			System.out.println("Unable to scroll to text: " + text);
			Reporter.reportStep("Unable to scroll to Field" + text, "FAIL");
			return null;
		}
	}

	private FTPClient ftpClient;

	String server2 = "ftp.iinvsys.com";
	int port2 = 2121;

	// Constructor to connect and login to FTP server
	public void FTPUploader(String server, int port, String user, String pass) throws IOException {

		ftpClient = new FTPClient();
		if (!pingServer(server)) {
			System.out.println(server + " is not reachable. Trying " + server2);
			connectToServer(server2, port2, user, pass);
		} else {
			connectToServer(server, port, user, pass);
		}

	}

	private void connectToServer(String server, int port, String user, String pass) throws IOException {
		ftpClient.connect(server, port);
		boolean login = ftpClient.login(user, pass);

		if (!login) {
			throw new IOException("FTP login failed for server: " + server);
		}

		ftpClient.enterLocalPassiveMode(); // Set passive mode for FTP
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE); // Use binary file type
	}

	private boolean pingServer(String server) {
		try {
			InetAddress address = InetAddress.getByName(server);
			return address.isReachable(2000); // Timeout after 2000 ms
		} catch (IOException e) {
			return false; // If there's an exception, the server is not reachable
		}
	}

	// Method to create a subdirectory and change the working directory to it
	public void createAndNavigateToSubdirectory(String existingDirectory, String newSubDir) throws IOException {
		// Navigate to the existing directory
		if (ftpClient.changeWorkingDirectory(existingDirectory)) {
			System.out.println("Navigated to directory: " + existingDirectory);

			// Create a new subdirectory
			if (ftpClient.makeDirectory(newSubDir)) {
				System.out.println("Created new subdirectory: " + newSubDir);

				// Change the working directory to the new subdirectory
				if (ftpClient.changeWorkingDirectory(newSubDir)) {
					System.out.println("Changed to new subdirectory: " + newSubDir);
				} else {
					throw new IOException("Failed to change to the new subdirectory");
				}
			} else {
				throw new IOException("Failed to create new subdirectory: " + newSubDir);
			}
		} else {
			throw new IOException("Failed to change directory to: " + existingDirectory);
		}
	}

	// Method to upload a file to the current directory
	public void uploadFile(String localFilePath, String remoteFileName) throws IOException {
		try (FileInputStream fis = new FileInputStream(new File(localFilePath))) {
			boolean success = ftpClient.storeFile(remoteFileName, fis);
			if (success) {
				System.out.println("File uploaded successfully to FTP: " + remoteFileName);

			} else {
				System.out.println("File upload failed.");
			}
		}
	}

	// Close the FTP connection
	public void disconnect() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.logout();
			ftpClient.disconnect();
		}

	}

	public void getLatestApk(String baseRemotePath, String localDirectory, String newFileName) throws IOException {
		// Add current week to the path
		String weekFolder = getCurrentWeekFolder();
		String remotePathWithWeek = baseRemotePath + weekFolder + "/";
		System.out.println("Looking in directory: " + remotePathWithWeek);

		// Get the latest folder within the week directory
		String latestFolder = getLatestFolder(ftpClient, remotePathWithWeek);
		if (latestFolder != null) {
			String targetDirectory = remotePathWithWeek + latestFolder + "/";
			System.out.println("Latest folder found: " + targetDirectory);

			File localFolder = new File(localDirectory);
			deleteAllFilesInFolder(localFolder);

			// Search for the file containing "szhephyr" in the latest folder
			FTPFile[] files = ftpClient.listFiles(targetDirectory);
			for (FTPFile file : files) {
				System.out.println(file);//////////////////////
				if (file.isFile() && file.getName().contains("Android_SZephyr")) {
					String downloadedFileName = file.getName();
					File localFile = new File(localDirectory + File.separator + downloadedFileName);

					// Download the file
					try (FileOutputStream outputStream = new FileOutputStream(localFile)) {
						boolean success = ftpClient.retrieveFile(targetDirectory + downloadedFileName, outputStream);
						if (success) {
							System.out.println("Downloaded: " + downloadedFileName);
						} else {
							System.out.println("Failed to download: " + downloadedFileName);
						}
					}

					// Rename the downloaded file
					File renamedFile = new File(localDirectory + File.separator + newFileName);
					boolean renamed = localFile.renameTo(renamedFile);
					if (renamed) {
						System.out.println("File renamed to: " + newFileName);
					} else {
						System.out.println("Failed to rename file.");
					}

					break;
				} else {
					System.out.println("APK file not found at: " + localDirectory);
					// Fail the entire suite if APK is missing
					Assert.fail("APK file is required to run the test suite but was not found.");
				}
			}
		} else {
			System.out.println("APK file not found at: " + localDirectory);
			// Fail the entire suite if APK is missing
			Assert.fail("APK file is required to run the test suite but was not found.");
			System.out.println("No latest folder found for the week.");
		}
	}

	// Get the latest folder from a given directory on the FTP server
	private static String getLatestFolder(FTPClient ftpClient, String directoryPath) throws IOException {

		FTPFile[] directories = ftpClient.listDirectories(directoryPath);

		if (directories.length == 0) {
			return null;
		}

		FTPFile latestDir = null;
		for (FTPFile dir : directories) {
			if (dir.isDirectory()) {
				if (latestDir == null || dir.getTimestamp().getTime().after(latestDir.getTimestamp().getTime())) {
					latestDir = dir;
				}
			}
		}

		return latestDir != null ? latestDir.getName() : null;
	}

	private static String getCurrentWeekFolder() {
		Calendar calendar = Calendar.getInstance();
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

		return "W" + (weekOfYear - 1);
	}

	private static void deleteAllFilesInFolder(File folder) {
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					if (file.isFile()) {
						file.delete();
					}
				}
			}
		}
	}

	public void fail(Exception e) {
		System.err.println("Failure occurred: " + e.getMessage());
		Reporter.reportStep(e + "Testcase failed", "FAIL");
		throw new RuntimeException(e);
	}

	public boolean isElementDisplayedCheck(WebElement element) {
		try {
			expWaitTillElementDisplay(element, 10);

			return element.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean verifyDynamicContentByXpath(String xpath, String text, String field) {

		boolean bReturn = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			String sText = driver.findElement(By.xpath(xpath)).getText();
			if (sText.trim().contains(text)) {
				Reporter.reportStep(field + " contains " + text, "PASS");
				bReturn = true;
			} else {
				Reporter.reportStep(field + " did not contain :" + text, "FAIL");
			}
		} catch (Exception e) {
			//
		}
		return bReturn;
	}

	public void ABDconnection() {
		try {
			if (!ADBconnections.isDeviceConnected()) {
				String errorMsg = "No ADB devices connected. Test execution stopped.";
				System.out.println(errorMsg);
				if (test != null) {
					Reporter.reportStep("No ADB devices connected. Test execution stopped.", "WARNING");
					Reporter.endResult();
				}
				throw new RuntimeException(errorMsg);
			} else {
				List<String> devices = ADBconnections.getConnectedDevices();
				System.out.println(devices);
				if (test != null) {
					Reporter.reportStep("Connected devices: " + String.join(", ", devices), "INFO");
				}
			}
		} catch (Exception e) {
			if (test != null) {
				Reporter.reportStep(e.getMessage(), "WARNING");
				Reporter.endResult();
			}
			throw new RuntimeException("ADB device check failed", e);

		}
	}

	public boolean scrollToTextSafe(String text, int maxScrolls) {

		try {
			// ✅ Step 1: Check current view first
			if (isElementPresent(text)) {
				System.out.println("✅ Found element with text: " + text + " in current view.");
				return true;
			}

			// ✅ Step 2: Scroll forward
			for (int attempt = 0; attempt < maxScrolls; attempt++) {
				scrollForwardOnce();
				if (isElementPresent(text)) {
					System.out.println("✅ Found element with text: " + text + " after scrolling forward.");
					return true;
				}
			}

			// ✅ Step 3: Scroll backward
			for (int attempt = 0; attempt < maxScrolls; attempt++) {
				scrollBackwardOnce();
				if (isElementPresent(text)) {
					System.out.println("✅ Found element with text: " + text + " after scrolling backward.");
					return true;
				}
			}

			System.out.println("❌ Element not found after scrolling in both directions: " + text);
			return false;
		} catch (Exception e) {
			System.out.println("Error during scroll to text: " + text);
			return false;
		}

	}

	private boolean isElementPresent(String text) {
		List<WebElement> elements = driver
				.findElements(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text + "\")"));
		return !elements.isEmpty();
	}

	private void scrollForwardOnce() {
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward();"));
	}

	private void scrollBackwardOnce() {
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward();"));
	}

	@SuppressWarnings("deprecation")
	public void uninstall_reinstall() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("./config.properties")));

		if (driver.isAppInstalled(packages)) {
			Runtime.getRuntime().exec("adb uninstall com.iinvsys.caazasmart");

			if (loadProp("PLATFORM_VERSION").contains("15")) {
				appinstallationforhigherversion();
			} else {
				driver.installApp(prop.getProperty("APP_PATH"));
				driver.activateApp(packages);
				Thread.sleep(3000);
			}

			allowpermissions();
		} else {
			if (loadProp("PLATFORM_VERSION").contains("15")) {
				appinstallationforhigherversion();

			} else {
				driver.installApp(prop.getProperty("APP_PATH"));
				driver.activateApp(packages);

			}
			allowpermissions();
		}
	}

	public boolean Waitandverifytexttoast(WebElement xpath, String input) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.pollingEvery(Duration.ofMillis(500));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(StaleElementReferenceException.class);

		try {
			// Option A: wait until next-screen element is visible (preferred)
			wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//android.widget.Toast[@text=\"" + input + "\"]")));
			verifyTextContainsByXpath(xpath, "Credentials updated successfully", "Credentials updated Toast");
			return true;
		} catch (TimeoutException e) {
			// Option B fallback: wait until verifying header is not visible
			return false;
		}
	}

	public static String getUDID() {
		String udid = null;
		try {
			// 1. Run 'adb devices' command to get the list of connected devices
			Process process = Runtime.getRuntime().exec("adb devices");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				// Skip the first line that contains "List of devices attached"
				if (line.contains("\tdevice")) {
					udid = line.split("\t")[0]; // Extract the UDID (before the tab character)
					break;
				}
			}

			// Check if a device was found
			if (udid == null) {
				System.out.println("No devices connected.");
				return null; // Return null if no device is connected
			}

			System.out.println("Found UDID: " + udid);

			// Optional: Update the property or configuration file with the UDID
			updateProperty("UDID", udid);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return udid; // Return the UDID or null if no device found
	}

	public static void checkWiFiAndContinue() {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user to turn on WiFi and enable auto-connect
		System.out.println("Please turn on the WiFi on your mobile and enable 'Connect Automatically'.");
		System.out.println(
				"In config Properties file ,set the correct wifi Password example-WIFINAME=TP-Link_C75A,WIFIPASSWORD=38172946");
		System.out.println("Please check in app that user is already signed in and app is on Home page");
		System.out.println("Please Turn ON the Device Power supply");
		System.out.println("Once done, enter 'yes' to continue or 'no' to stop.");

		String input = scanner.nextLine().trim().toLowerCase(); // Read user input and normalize it

		// Continue or stop based on user input
		if (input.equals("yes") || input.equals("y")) {
			System.out.println("WiFi is enabled. Continuing with the script...");
		} else {
			System.out.println("WiFi is not enabled. Please turn on WiFi and enable auto-connect.");
			System.out.println("If WiFi is enabled, enter 'Yes' to continue or 'No' to stop.");

			// Keep prompting the user until the correct input is given
			while (true) {
				input = scanner.nextLine().trim().toLowerCase();
				if (input.equals("yes") || input.equals("y")) {
					System.out.println("WiFi is enabled. Continuing with the script...");
					break; // Exit the loop and continue the script
				} else if (input.equals("no")) {
					System.out.println("Exiting the script. Please enable WiFi and try again.");
					System.exit(0); // Exit the program if user doesn't enable WiFi
				} else {
					System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
				}
			}
		}

		scanner.close(); // Close the scanner
	}

	public static void appinstallationforhigherversion() {
		String apkPath = loadProp("APP_PATH");
		String appPackage = loadProp("APP_PACKAGE"); // replace with your actual package name

		try {
			// Step 1: Install the APK with -r (reinstall) and -g (grant all runtime
			// permissions)
			Process process = Runtime.getRuntime().exec("adb install -r -g " + apkPath);

			// Wait for the install command to complete and check result
			int exitCode = process.waitFor();

			if (exitCode == 0) {
				System.out.println("APK installed successfully with permissions granted.");
			} else {
				// Read error output
				java.util.Scanner scanner = new java.util.Scanner(process.getErrorStream()).useDelimiter("\\A");
				String error = scanner.hasNext() ? scanner.next() : "Unknown error";
				throw new RuntimeException("APK installation failed: " + error);
			}

			// Step 2: Wait until the package is fully installed and appears in pm list
			System.out.println("Waiting for app to be fully installed...");
			boolean installed = false;
			for (int i = 0; i < 30; i++) { // max 30 seconds
				Process checkProcess = Runtime.getRuntime().exec("adb shell pm list packages " + appPackage);
				checkProcess.waitFor();

				java.util.Scanner outputScanner = new java.util.Scanner(checkProcess.getInputStream())
						.useDelimiter("\\A");
				String output = outputScanner.hasNext() ? outputScanner.next() : "";

				if (output.contains("package:" + appPackage)) {
					installed = true;
					break;
				}
				Thread.sleep(1000); // wait 1 second before retry
			}

			if (!installed) {
				throw new RuntimeException("Timeout: App package not detected after installation.");
			}

			System.out.println("App is installed and ready.");

			// Optional: Small delay to let system settle
			Thread.sleep(2000);

			// Step 3: Now safely activate the app
			driver.activateApp(appPackage);
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to install and launch app: " + e.getMessage());
		}
	}

	public boolean isElementVisible(By locator) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			for (WebElement element : elements) {
				if (element.isDisplayed()) {
					// Get the location of the element
					Point location = element.getLocation();
					int x = location.getX();
					int y = location.getY();

					// Get the size of the element
					Dimension size = element.getSize();

					// STRICT CHECK:
					// 1. Must have width/height > 0
					// 2. X and Y coordinates must be positive (not hidden off-screen)
					// 3. Usually, Home buttons aren't at (0,0). Adjust these values if needed.
					if (size.getWidth() > 0 && size.getHeight() > 0 && x >= 0 && y >= 0) {

						// Optional: Print coordinates for debugging
						System.out.println("Found visible element at: X=" + x + ", Y=" + y);
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void toggleNetwork(boolean enable) {
		if (enable) {
			// Enables both WiFi and Data
			driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().withDataEnabled().build());
		} else {
			// Disables both
			driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().withDataDisabled().build());
		}
	}

	

	
	
	
	public boolean verifyAndSaveLogs(String expectedMessage) {
	    List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
	    
	    // ... (Your directory and writer setup remains the same)
	    String directoryPath = System.getProperty("user.dir") + File.separator + "logs";
		String filePath = directoryPath + File.separator + "logcat_output.txt";

		// 3. Create the directory if it doesn't exist
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        for (LogEntry entry : logEntries) {
	            String logLine = entry.getMessage();
	            writer.write(entry.getTimestamp() + " " + entry.getLevel() + ": " + logLine);
	            writer.newLine();

	            // FIX: Convert everything to lowercase before comparing
	            String lowerLog = logLine.toLowerCase();
	            
	            if (lowerLog.contains("reactnativejs") || lowerLog.contains("api response:")) {
	                if (lowerLog.contains(expectedMessage.toLowerCase())) {						System.out.println("Verified message in logs"+ expectedMessage);
	                    Reporter.reportStep("Verified Message in Logs: " + expectedMessage, "PASS");
	                    return true;
	                }
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error: " + e.getMessage());
	    }
	    Assert.fail("Toast not found: The screen does not match the template image.");
	    Reporter.reportStep("Verified Message not in Logs: " + expectedMessage, "FAIL");
	    return false;
	}
	
	
	
	
	
	
	
//		public void verifyToastByImage() throws Exception {
//			// 1. Path to your reference image (a small crop of just the toast)
//			// You should create this file once manually from a screenshot
//			String templatePath = "C:\\Users\\Invcuser_71\\eclipse-workspace\\CaazaProdSanity\\Toast_screenshots\\switchlocktoast.jpg";
//
//			// 2. Convert that image file into a Base64 String
//			byte[] fileContent = Files.readAllBytes(Paths.get(templatePath));
//			String base64ImageString = Base64.getEncoder().encodeToString(fileContent);
//
//			// 3. Set visual matching settings (Optional but recommended)
//			// This helps Appium find the image even if the resolution or contrast is
//			// slightly different
//			driver.setSetting("visualThreshold", 0.4); // 0.0 to 1.0 (lower is more relaxed)
//
//			try {
//				// 4. Find the element by the image string
//				WebElement toast = driver.findElement(AppiumBy.image(base64ImageString));
//
//				// 5. Validation
//				Assert.assertTrue(toast.isDisplayed(), "App Switch Lock has been enabled successfully");
//				System.out.println("Success: Toast found via Image Recognition!");
//
//			} catch (org.openqa.selenium.NoSuchElementException e) {
//				Assert.fail("Toast not found: The screen does not match the template image.");
//			}
//		}

}