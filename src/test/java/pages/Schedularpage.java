package pages;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import utils.Reporter;
import wrappers.GenericWrappers;

public class Schedularpage extends GenericWrappers {
	public static AndroidDriver driver;
	Analytics analytics;
	

//	public AndroidElement element;

	public Schedularpage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.js = (JavascriptExecutor) driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	JavascriptExecutor js = (JavascriptExecutor) driver;

	@FindBy(xpath = "//*[@resource-id='Home_Navigation_Button']")
	private WebElement Schedulebutton;

	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]")
	private WebElement Scehduletitle;

	@FindBy(xpath = "//*[@resource-id='Add ScheduleButton']")
	private WebElement plusIcon;

	@FindBy(xpath = "//*[@resource-id='Header']")
	private WebElement yourScheduleheader;

	@FindBy(xpath = "//*[@resource-id='Scheduler_OtherUsersSchedules']")
	private WebElement otherScheduleheader;

	@FindBy(xpath = "//*[@resource-id='Scheduler_YouDoNotHaveAnySchedules']")
	private WebElement schedulePlaceholder;

	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement savebtn;

	@FindBy(xpath = "//*[@resource-id='Edit_Schedule_Cancel_Button']")
	private WebElement cancelbtn;

	@FindBy(xpath = "//android.widget.TextView[@text='Delete']")
	private WebElement deleteBtn;

	@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
	private WebElement locationpermissionpopup;

	@FindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
	private WebElement devicepermission;

	@FindBy(xpath = "//*[@resource-id='Edit_Schedule_HeatingDuration']")
	private WebElement durationtext;

	@FindBy(xpath = "//*[@resource-id='Scheduler_Switch']")
	private WebElement disableschedule;

	@FindBy(xpath = "//android.widget.TextView[@text=\"sZephyr and AC turned ON\"]")
	private WebElement Acturnondesc;

	@FindBy(xpath = "//android.widget.TextView[@text=\"Please ensure sZephyr is switched ON prior to operating your AC remote\"]")
	private WebElement acturnoffdesc;

	@FindBy(xpath = "//*[@resource-id='Edit_Schedule_Minutes1']")
	private WebElement minute1;

	@FindBy(xpath = "//*[@resource-id='Edit_Schedule_Duration_Hours_Minutes']")
	private WebElement duration;

	@FindBy(xpath = "//android.widget.Toast[@text=\"Schedule has been deleted\"]")
	private WebElement scheduleDeletedToast;

//	@FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup")
//	private List allSchedules;
	@FindBy(xpath = "//*[@resource-id='Scheduler_Time']")
	private WebElement createdSchedule;
	@FindBy(xpath = "//*[@resource-id='Device_BackIcon']")
	private WebElement backButton;

	@FindBy(xpath = "//*[@resource-id='PairedGeyser_Img_svg_name_0']")
	private WebElement userName;
	@FindBy(xpath = "//android.widget.TextView[@text=\" There are no schedules available from other users.\"]")
	private WebElement placeholdeofSchedulepage;
	@FindBy(xpath = "//*[@resource-id='Edit_Schedule_RepeatEveryWeek_Icon']")
	private WebElement repeateveryweekcheckbox;
	@FindBy(xpath = "//*[@resource-id='Header_MenuButton']")
	private WebElement Header_MenuButton;
	@FindBy(xpath = "//*[@resource-id='MenuItem_Timer_1']")
	private WebElement MenuItem_Timer;
	@FindBy(xpath = "//*[@resource-id='Duration_Minutes_Input']")
	private WebElement Timer_Duration_Minutes_Input;
	@FindBy(xpath = "//*[@resource-id='Duration_Toggle']")
	private WebElement Timer_Duration_Toggle;
	@FindBy(xpath = "//*[@resource-id='SaveButton']")
	private WebElement SaveButton;
	@FindBy(xpath = "//*[@resource-id='LockIcon_1']")
	private WebElement LockIconimage;
	@FindBy(xpath = "//*[@resource-id='Menu_CloseBtn']")
	private WebElement Menu_CloseBtn;
	@FindBy(xpath = "//*[@resource-id='Header_Back_Button']")
	private WebElement SwitchBackButton;
	@FindBy(xpath = "//*[@resource-id='MenuItem_SwitchLock_2']")
	private WebElement MenuItem_SwitchLock;
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Others\"]")
	private WebElement othersSchedulePage;
	@FindBy(xpath = "//android.view.ViewGroup[starts-with(@content-desc, 'Duration')]")
	private WebElement othersSchedulePage_schedule;
	@FindBy(xpath = "//android.widget.ScrollView[@content-desc=\"com.CaaZa_Smart:id/SettingsScreen_ScrollView\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
	private WebElement switchLockToggle;
	
	@FindBy(xpath = "//*[@resource-id='Scheduler_Time']")
	private WebElement alreadyExistingSchedule;
	

	private WebElement deviceName(int username) {
		return driver.findElement(By.xpath("//*[@resource-id='SwitchName_" + username + "\']"));

	}

	private WebElement weekdays(int no) {
		return driver.findElement(By.xpath(
				"//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/Edit_Schedule_WeekDays" + no + "\"]"));

	}

	private WebElement userName(String username) {
		return driver.findElement(By.xpath("//android.widget.TextView[@text='" + username + "']"));

	}
	private WebElement switchToggle(int switchcount) {
		return driver.findElement(By.xpath("//*[@resource-id='SwitchToggle_" + switchcount+ "']"));
		
	}

	public String scheduleDeletedtoast = loadProp("thisScheduleHasBeenDeleted");

//		com.iinvsys.szephyr:id/ScrollPicker_Hours
	String x1;
	String x2;
	String x3;
	String resourseId1;
	String resourseId2;
	String resourseId3;

	public void setContext(String type) {
		this.x1 = "//*[@resource-id='ScrollPicker_Hours_" + type + "']";
		this.x2 = "//*[@resource-id='ScrollPicker_Minutes_" + type + "']";
		this.x3 = "//*[@resource-id='ScrollPicker_AM_PM_" + type + "']";
		this.resourseId1 = "ScrollPicker_Hours_" + type + "";
		this.resourseId2 = "ScrollPicker_Minutes_" + type + "";
		this.resourseId3 = "ScrollPicker_AM_PM_" + type + "";
	}

	public void clickandverifyOtherSchedulespage() {

		clickbyXpath(otherScheduleheader, "Other user schedule");
		verifyTextContainsByXpath(placeholdeofSchedulepage, "There are no schedules available from other users.",
				"Placeholder of other user schedule");
	}

	public void addScheduleButton() {
		clickbyXpath(plusIcon, "plusbutton");
	}
	public void checkPartialAccessSchedulepage() {
		if (!isElementDisplayedCheck(plusIcon)) {
			Reporter.reportStep( "Plusicon not displayed.", "PASS");
		}else {
			Reporter.reportStep( "Plusicon  displayed.", "FAIL");
			
		}
	}
	

	public void createSchedules(int timetostart, int intervals, int gapBetweenNextSchedule) {
		
		// Get the current time and calculate the start time for the first schedule

		LocalTime currentTime = LocalTime.now();
		LocalTime timet = currentTime.plusMinutes(timetostart);

		// Generate schedule times based on intervals and gap
		List<LocalTime> scheduleTimes = generateSchedule(timet, intervals, gapBetweenNextSchedule);
		// List<LocalTime> scheduleTimesEnd = generateSchedule(timet, intervals,
		// gapBetweenNextSchedule);

		// Loop through each time and create the schedule
		for (LocalTime time : scheduleTimes) {
			// Split time into hour, minute, and AM/PM
			int hour = time.getHour() % 12;
			if (hour == 0) {
				hour = 12; // Convert 0 hour to 12 for 12-hour format
			}

			// Format the minute as two digits
			int minute = time.getMinute();
			String formattedMinute = String.format("%02d", minute);
			String amPm = time.getHour() >= 12 ? "PM" : "AM";
			clickbyXpath(plusIcon, "plusbutton");
			System.out.println("Creating schedule for: " + hour + ":" + formattedMinute + " " + amPm);

			// Navigate to the screen to create a schedule

			// Scroll to the desired time using the method that scrolls until the element is
			// visible
			setContext("start");
			selectTimeUsingBounds(hour, minute, amPm);

			// clickonDuration();

			System.out.println("Schedule created for: " + hour + ":" + formattedMinute + " " + amPm);
			LocalTime Endtime = time.plusMinutes(gapBetweenNextSchedule);
			int hourEnd = Endtime.getHour() % 12;
			if (hourEnd == 0) {
				hourEnd = 12; // Convert 0 hour to 12 for 12-hour format
			}

			// Format the minute as two digits
			int minuteEnd = Endtime.getMinute();
			String formattedMinuteEnd = String.format("%02d", minute);

			setContext("end");

			// Scroll to the desired time using the method that scrolls until the element is
			// visible
			selectTimeUsingBounds(hourEnd, minuteEnd, amPm);
			// Save the schedule
			saveSchedule();
			System.out.println("Schedule created for: " + hour + ":" + formattedMinuteEnd + " " + amPm);

		}
	}
	
	

	public void createSchedules(int switchCount, int timeToStart, int intervals, int gapBetweenNextSchedule,String scheduletype) {
		analytics = new Analytics(driver);
		// Get current time and calculate when first schedule should start
		LocalTime currentTime = LocalTime.now();
		LocalTime startTime = currentTime.plusMinutes(timeToStart);

//		String configuredTime = "12:57";  // can come from properties file
//		LocalTime currentTime = LocalTime.parse(configuredTime);
//		LocalTime startTime = currentTime.plusMinutes(timeToStart);

		System.out.println("Starting schedule creation after " + timeToStart + " minutes...");
		System.out.println("Total switches: " + switchCount);
		System.out.println("Schedules per switch: " + intervals);
		System.out.println("Gap between schedules: " + gapBetweenNextSchedule + " minutes");

		// Loop for each switch
		for (int i = 1; i <= switchCount; i++) {
//			scrollToTextSafe("Switch" + i, 3);
			String deviceName = deviceName(i).getText().trim();
			System.out.println("🟢 Creating schedules for device: " + deviceName);

			// Open that switch screen
			clickbyXpath(deviceName(i),"switchcard" );
			
			
			
			// Generate schedule times based on intervals and gap
			List<LocalTime> scheduleTimes = generateSchedule(startTime, intervals, gapBetweenNextSchedule);

			// Loop through each time and create the schedule
			for (LocalTime time : scheduleTimes) {
				// Split time into hour, minute, and AM/PM
				int hour = time.getHour() % 12;
				if (hour == 0) {
					hour = 12; // Convert 0 hour to 12 for 12-hour format
				}

				// Format the minute as two digits
				int minute = time.getMinute();
				String formattedMinute = String.format("%02d", minute);
				String amPm = time.getHour() >= 12 ? "PM" : "AM";
				
				if(scheduletype.contains("NewSchedule")) {
					clickbyXpath(plusIcon, "plusbutton");
				}else {
					clickbyXpath(alreadyExistingSchedule, "Already existing schedule");
				}
				
				System.out.println("Creating schedule for: " + hour + ":" + formattedMinute + " " + amPm);

				

				// Scroll to the desired time using the method that scrolls until the element is visible
				setContext("start");
				selectTimeUsingBounds(hour, minute, amPm);

				// clickonDuration();
				

				System.out.println("Schedule created for: " + hour + ":" + formattedMinute + " " + amPm);
				LocalTime Endtime=time.plusMinutes(gapBetweenNextSchedule);
				int hourEnd = Endtime.getHour() % 12;
				if (hourEnd == 0) {
					hourEnd = 12; // Convert 0 hour to 12 for 12-hour format
				}

				// Format the minute as two digits
				int minuteEnd = Endtime.getMinute();
				String formattedMinuteEnd = String.format("%02d", minute);
				
				setContext("end");
				
				
					// Scroll to the desired time using the method that scrolls until the element is
					// visible
					selectTimeUsingBounds(hourEnd, minuteEnd, amPm);
					// Save the schedule
					saveSchedule();
					System.out.println("Schedule created for: " + hour + ":" + formattedMinuteEnd + " " + amPm);
			}

			backToHomepage();
			System.out.println("🏠 Returned to switch for next switch\n");
		}

		System.out.println("🎯 All schedules created successfully!");
	}

	private List<LocalTime> generateSchedule(LocalTime startTime, int intervals, int gap) {
		List<LocalTime> schedule = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			schedule.add(startTime.plusMinutes(i * gap));
			System.out.println("schedules:" + schedule);
		}
		return schedule;
	}

	private void selectTimeUsingBounds(int hour, int minute, String amPm) {

		// Create an ExecutorService with 3 threads for the three scroll tasks
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		// Convert Runnable tasks to Callable<Void> to make it compatible with invokeAll
		Callable<Void> scrollHoursTask = () -> {
			scrollColumnUntilValueAtIndex0Hour(x1, resourseId1, String.valueOf(hour));
			return null;
		};

		Callable<Void> scrollMinutesTask = () -> {
			scrollColumnUntilValueAtIndex0Min(x2, resourseId2, String.valueOf(minute));
			return null;
		};

		Callable<Void> scrollAmPmTask = () -> {
			scrollColumnUntilValueAtIndex1(x3, resourseId3, String.valueOf(amPm));
			return null;
		};

		// Create a list of Callable tasks
		List<Callable<Void>> tasks = Arrays.asList(scrollHoursTask, scrollMinutesTask, scrollAmPmTask);

		try {
			// Run all scrolling tasks in parallel
			executorService.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
	}

	public void saveSchedule() {
		// Click the save button after setting the time
//		 scrollToText("Save");
		scrollOntop();
		clickbyXpath(savebtn, "saveschedule");

		// Validate if the schedule is saved successfully
//		verifyTextContainsByXpath(yourScheduleheader, "Schedule", "Schedule page header");

	}

//	private void scrollColumnUntilValueAtIndex0Hour(String xpath, String resourceId, String expectedValue) {
//	    WebElement columnElement = driver.findElement(By.xpath(xpath));
//	    boolean valueSet = false;
//
//	    while (!valueSet) {
//	        List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));
//
//	        // ✅ determine center index dynamically
//	        int centerIndex = (elements.size() == 2 ) ? 0 : elements.size() / 2;
//
//	        WebElement centerEl = elements.get(centerIndex);
//	        String currentValue = centerEl.getText().trim();
//
//	        System.out.println("Current hour at center: " + currentValue + " | Expected: " + expectedValue);
//
//	        int current = extractintvalue(currentValue);
//	        int expected = extractintvalue(expectedValue);
//
//	        if (current == expected) {
//	            System.out.println("✅ Hour " + expectedValue + " aligned at center index " + centerIndex);
//	            valueSet = true;
//	        } else {
//	            int compare = comparePickerValues(resourceId, currentValue, expectedValue);
//	            if (compare < 0) {
//	                swipeElement(xpath, true); // scroll forward
//	            } else {
//	                swipeElement(xpath, false); // scroll backward
//	            }
//	        }
//	    }
//	}
//	private void scrollColumnUntilValueAtIndex0Min(String xpath, String resourceId, String expectedValue) {
//	    WebElement columnElement = driver.findElement(By.xpath(xpath));
//	    boolean valueSet = false;
//
//	    while (!valueSet) {
//	        List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));
//
//	        // ✅ determine center index dynamically
//	        int centerIndex = (elements.size() == 2) ? 0 : elements.size() / 2;
//	        WebElement centerEl = elements.get(centerIndex);
//	        String currentValue = centerEl.getText().trim();
//
//	        System.out.println("Current minute at center: " + currentValue + " | Expected: " + expectedValue);
//
//	        int current = extractintvalue(currentValue);
//	        int expected = extractintvalue(expectedValue);
//
//	        if (current == expected) {
//	            System.out.println("✅ Minute " + expectedValue + " aligned at center index " + centerIndex);
//	            valueSet = true;
//	        } else {
//	            int compare = comparePickerValues(resourceId, currentValue, expectedValue);
//	            if (compare < 0) {
//	                swipeElement(xpath, true);
//	            } else {
//	                // Optional faster scroll for small minute values
//	                if (expected <= 5) {
//	                    fastswipe(xpath, false);
//	                } else {
//	                    swipeElement(xpath, false);
//	                }
//	            }
//	        }
//	    }
//	}

	private void scrollColumnUntilValueAtIndex1(String xpath, String resourceId, String expectedValue) {
		// Find the container element using resourceId
		WebElement columnElement = driver.findElement(By.xpath(xpath));

		boolean valueAtIndex1 = false;

		while (!valueAtIndex1) {
			// Get the list of elements in the column (assuming TextView contains the
			// displayed values)
			List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));

			int centerIndex = elements.size();
			System.out.println(centerIndex);
			WebElement elAtCenter = elements.get(centerIndex);
			String currentValue = elAtCenter.getText();
			System.out.println("current value  " + currentValue);

			if (currentValue.equals(expectedValue)) {
				valueAtIndex1 = true;
				System.out.println("Element with value " + expectedValue + " is now at index 1.");
			} else {
				// Compare current value with expected value to decide scroll direction
				int compareResult = comparePickerValues(resourceId, currentValue, expectedValue);

				if (compareResult < 0) {
					// Scroll forward (expected value is greater)
					System.out.println("Scrolling forward for: " + resourceId);
					swipeElement(xpath, true);
				} else {
					// Scroll backward (expected value is smaller)
					System.out.println("Scrolling backward for: " + resourceId);
					swipeElement(xpath, false);
				}
			}
		}
	}

	private int comparePickerValues(String resourceId, String currentValue, String expectedValue) {
		if (resourceId.equals(resourseId1)) { // Hour comparison
			int currentHour = Integer.parseInt(currentValue);
			int expectedHour = Integer.parseInt(expectedValue);
			return Integer.compare(currentHour, expectedHour);
		} else if (resourceId.equals(resourseId2)) { // Minute comparison
			int currentMinute = Integer.parseInt(currentValue);
			int expectedMinute = Integer.parseInt(expectedValue);
			return Integer.compare(currentMinute, expectedMinute);
		} else if (resourceId.equals(resourseId3)) { // AM/PM comparison
			// Handle AM/PM comparison manually
			return compareAmPm(currentValue, expectedValue);
		} else {
			throw new IllegalArgumentException("Unknown resource ID: " + resourceId);
		}
	}

	private int compareAmPm(String currentValue, String expectedValue) {
		if (currentValue.equals("AM") && expectedValue.equals("PM")) {
			return -1; // AM is less than PM
		} else if (currentValue.equals("PM") && expectedValue.equals("AM")) {
			return 1; // PM is greater than AM
		} else {
			return 0; // Both are the same
		}
	}

	public void disableschedule(int min) throws Exception, IOException, InterruptedException {

		clickbyXpath(disableschedule, "disable schedule");
		Thread.sleep(min * 60 * 1000);
		driver.navigate().back();
		verifyTextContainsByXpath(acturnoffdesc,
				"Please ensure sZephyr is switched ON prior to operating your AC remote", "AC truned OFF description");

	}

	public void clickSchedulebtn() {

		clickbyXpath(Schedulebutton, "schedulebutton");
	}

	public void clickonDuration() {

		clickbyXpath(minute1, "1 min on duration minutes ");

	}

	public void clickondeletebutton() {

		clickbyXpath(deleteBtn, "Delete schedule button ");

	}

	public void checktoast() {
		verifyTextContainsByXpath_Toast(scheduleDeletedToast, scheduleDeletedtoast, "schedule Deleted Toast");
	}

	public void clickplusbtn() {
		clickbyXpath(plusIcon, "plusicon");
	}

	public void deleteschedule() {

		try {
			while (createdSchedule.isDisplayed()) {
				clickbyXpath(createdSchedule, "created schedules");
				scroll2();
				clickbyXpath(deleteBtn, "deleteButton");
				checktoast();

			}

		} catch (Exception e) {
			System.out.println("Problem in deleting scedule");
		}

	}

	public void backToHomepage() {
		driver.navigate().back();

	}

	public void swipeElement(String columnXPath, boolean forward) {
		WebElement columnElement = driver.findElement(By.xpath(columnXPath));

		// Get the location and size of the column
		int columnCenterX = columnElement.getLocation().getX() + (columnElement.getSize().getWidth() / 2);
		int startY = forward ? columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.6) // Swipe
																														// up
																														// if
																														// forward
				: columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.2); // Swipe down
																											// if
																											// backward
		int endY = forward ? columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.2) // End
																													// at
																													// top
																													// for
																													// forward
																													// swipe
				: columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.6); // End at
																											// bottom
																											// for
																											// backward
																											// swipe

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1)
				.addAction(
						finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), columnCenterX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
						.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), columnCenterX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));

	}

	private void fastswipe(String columnXPath, boolean forward) {

		WebElement columnElement = driver.findElement(By.xpath(columnXPath));

		// Get the location and size of the column
		int columnCenterX = columnElement.getLocation().getX() + (columnElement.getSize().getWidth() / 2);
		int startY = forward ? columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.6) // Swipe
																														// up
																														// if
																														// forward
				: columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.2); // Swipe down
																											// if
																											// backward
		int endY = forward ? columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.2) // End
																													// at
																													// top
																													// for
																													// forward
																													// swipe
				: columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.6); // End at
																											// bottom
																											// for
																											// backward
																											// swipe

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1)
				.addAction(
						finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), columnCenterX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg())).addAction(finger
						.createPointerMove(Duration.ofMillis(50), PointerInput.Origin.viewport(), columnCenterX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));

	}

	public void checkOffState() {

		verifyTextContainsByXpath(acturnoffdesc,
				"Please ensure sZephyr is switched ON prior to operating your AC remote", "OFF state");
	}

	public void scroll2() {
		int startX = driver.manage().window().getSize().getWidth() / 8;
		int startY = driver.manage().window().getSize().getHeight() / 2;
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0);
		int endY = (int) (driver.manage().window().getSize().getHeight());
		scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, 0));
		scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(List.of(scroll));

	}
	
	public void scrollOntop() {
		int screenWidth = driver.manage().window().getSize().getWidth();
	    int screenHeight = driver.manage().window().getSize().getHeight();

	    // Start X near the center horizontally
	    int startX = screenWidth / 2;

	    // Start Y near the top (say 20% of the screen height)
	    int startY = (int) (screenHeight * 0.20);

	    // End Y further down (say 80% of the screen height)
	    int endY = (int) (screenHeight * 0.80);

	    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	    Sequence scroll = new Sequence(finger, 0);

	    scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
	    scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	    scroll.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), startX, endY));
	    scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	    driver.perform(List.of(scroll));
	}
	public void SetTimerForSwitches(int Switches) {
		
		for(int i=1;i<=Switches;i++) {
			
			clickbyXpath(deviceName(i), "Switchcard");
			clickbyXpath(Header_MenuButton, "Header MenuButton");
			clickbyXpath(MenuItem_Timer, "MenuTimer");
			clickbyXpath(Timer_Duration_Toggle, "Timer Duration Toggle");
			entervaluebyXpath(Timer_Duration_Minutes_Input, "Duration_Minutes_Input", "1");
			clickbyXpath(SaveButton, "Save button");
//			clickbyXpath(Menu_CloseBtn, "MenuClose Button");
			clickbyXpath(SwitchBackButton, "Switch Backbutton");
			clickbyXpath(switchToggle(i), "Switch Toggle");
			
		}
	}
	
	public void enter_Switchpage(int switches) {
		clickbyXpath(deviceName(switches), "Switchcard");
	}
	public void clickSwitchMenuButton() {
		clickbyXpath(Header_MenuButton, "Header MenuButton");

	}
	public void clickSwitchLockButton() {
clickbyXpath(MenuItem_SwitchLock, "menuItem SwitchLock button");
	}
	public void clickSwitchlockToggle() {
clickbyXpath(switchLockToggle, "SwitchlockToggle");
	}
	
	public void clickSwitchsaveBtn() {
		clickbyXpath(SaveButton, "save button");
	}

	
	
	public void NavigateOtherSchedulepage() {
		clickbyXpath(othersSchedulePage, "othersScheduleButton");
	}
	public void verifyOtherUserscheduleSchedules() {
		if(!isElementDisplayedCheck(othersSchedulePage_schedule)) {
			 Reporter.reportStep("Disconnected Badge Displayed", "FAIL");
		}
	}
	
	private void scrollColumnUntilValueAtIndex0Hour(String xpath, String resourceId, String expectedValue) {
	    WebElement columnElement = driver.findElement(By.xpath(xpath));
	    boolean valueSet = false;
	    int expectedHour = extractintvalue(expectedValue);

	    if (expectedHour < 1 || expectedHour > 12) {
	        System.out.println("❌ Invalid hour: " + expectedValue + ". Must be between 1 and 12.");
	        return;
	    }

	    while (!valueSet) {
	        List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));
	        int elementsCount = elements.size();

	        // ✅ Custom center index for hour=1/12 edge cases
	        int centerIndex;
	        if (resourceId.equals(resourseId1)) { // Hour column
	            if (expectedHour == 1 && elementsCount == 2) {
	                centerIndex = 0; // Force center to index 0 when desired is 1 and size=2
	            } else if (expectedHour == 12 && elementsCount == 3) {
	                centerIndex = 2; // Force center to index 2 when desired is 12 and size=3
	            } else {
	                centerIndex = elementsCount / 2; // Default calculation
	            }
	        } else {
	            centerIndex = elementsCount / 2; // For non-hour columns
	        }

	        WebElement centerEl = elements.get(centerIndex);
	        String currentValue = centerEl.getText().trim();
	        int currentHour = extractintvalue(currentValue);

	        System.out.println("Current hour at center: " + currentValue + " | Expected: " + expectedValue);

	        if (currentHour == expectedHour) {
	            System.out.println("✅ Hour " + expectedValue + " aligned at center index " + centerIndex);
	            valueSet = true;
	        } else {
	            int compare = comparePickerValues(resourceId, currentValue, expectedValue);
	            if (compare < 0) {
	                if (currentHour == 12) {
	                    System.out.println("⚠️ Already at max hour (12). Cannot scroll up further.");
	                    break;
	                }
	                swipeElement(xpath, true);
	            } else {
	                if (currentHour == 1) {
	                    System.out.println("⚠️ Already at min hour (1). Cannot scroll down further.");
	                    break;
	                }
	                swipeElement(xpath, false);
	            }
	        }
	    }
	}
	private void scrollColumnUntilValueAtIndex0Min(String xpath, String resourceId, String expectedValue) {
	    WebElement columnElement = driver.findElement(By.xpath(xpath));
	    boolean valueSet = false;
	    int expectedMinute = extractintvalue(expectedValue);

	    if (expectedMinute < 0 || expectedMinute > 59) {
	        System.out.println("❌ Invalid minute: " + expectedValue + ". Must be between 00 and 59.");
	        return;
	    }

	    while (!valueSet) {
	        List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));
	        int elementsCount = elements.size();

	        // ✅ Custom center index for minute=00/59 edge cases
	        int centerIndex;
	        if (resourceId.equals(resourseId2)) { // Minute column
	            if (expectedMinute == 0 && elementsCount == 2) {
	                centerIndex = 0; // Force center to index 0 when desired is 00 and size=2
	            } else if (expectedMinute == 59 && elementsCount == 3) {
	                centerIndex = 2; // Force center to index 2 when desired is 59 and size=3
	            } else {
	                centerIndex = elementsCount / 2; // Default calculation
	            }
	        } else {
	            centerIndex = elementsCount / 2; // For non-minute columns
	        }

	        WebElement centerEl = elements.get(centerIndex);
	        String currentValue = centerEl.getText().trim();
	        int currentMinute = extractintvalue(currentValue);

	        System.out.println("Current minute at center: " + currentValue + " | Expected: " + expectedValue);

	        if (currentMinute == expectedMinute) {
	            System.out.println("✅ Minute " + expectedValue + " aligned at center index " + centerIndex);
	            valueSet = true;
	        } else {
	            int compare = comparePickerValues(resourceId, currentValue, expectedValue);
	            if (compare < 0) {
	                if (currentMinute == 59) {
	                    System.out.println("⚠️ Already at max minute (59). Cannot scroll up further.");
	                    break;
	                }
	                swipeElement(xpath, true);
	            } else {
	                if (currentMinute == 0) {
	                    System.out.println("⚠️ Already at min minute (00). Cannot scroll down further.");
	                    break;
	                }
	                swipeElement(xpath, false);
	            }
	        }
	    }
	    
	}
	public void clickonexistingSchedule() {
		clickbyXpath(alreadyExistingSchedule, "Already existing schedule");
	}
	
 
}
