package pages;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class Schedulartestpage extends GenericWrappers {
	public static AndroidDriver driver;

//	public AndroidElement element;

	public Schedulartestpage(AndroidDriver driver) {
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

	private WebElement deviceName(String username) {
		return driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + username + "\"]"));

	}

	private WebElement weekdays(int no) {
		return driver.findElement(By.xpath(
				"//android.view.ViewGroup[@content-desc=\"com.CaaZa_Smart:id/Edit_Schedule_WeekDays" + no + "\"]"));

	}

	public String scheduleDeletedtoast = loadProp("thisScheduleHasBeenDeleted");

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

//	public void createSchedulesFromConfig() throws IOException {
//		
//		
//
//
//		
//		int count =Integer.parseInt( loadProp("switchescount"));
//		// Loop over each device
//		for (int i = 1; i<=count ; i++) {
//			
//			
//			scrollToTextSafe("Switch"+i, 3);
//			String deviceName = deviceName(loadProp("Switch"+i)).getText().trim();
//			System.out.println("Creating schedule for device: " + deviceName);
//
//			// Fetch times from config
//			String fromTime = loadProp(deviceName + ".from");
//			String toTime = loadProp(deviceName + ".to");
//			
//			
//			System.out.println(fromTime);
//			System.out.println(toTime);
//			
//			if (fromTime == null || toTime == null) {
//				System.out.println("⚠ No schedule times found for " + deviceName + ", skipping...");
//				continue;
//			}
//
//			// Open device's schedule screen
//			
//			
//			deviceName(loadProp("Switch"+i)).click();
//			addScheduleButton();
//
//			// Parse start time
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);
//
//			// Parse start time
//			LocalTime start = LocalTime.parse(fromTime.trim(), formatter);
//			setContext("start");
//			selectTimeUsingBounds(
//			    start.getHour() % 12 == 0 ? 12 : start.getHour() % 12,
//			    start.getMinute(),
//			    start.getHour() >= 12 ? "PM" : "AM"
//			);
//
//			// Parse end time
//			LocalTime end = LocalTime.parse(toTime.trim(), formatter);
//			setContext("end");
//			selectTimeUsingBounds(
//			    end.getHour() % 12 == 0 ? 12 : end.getHour() % 12,
//			    end.getMinute(),
//			    end.getHour() >= 12 ? "PM" : "AM"
//			);
//
//			for (int j = 0; j <=5; j++) {
//				
//				clickbyXpath(weekdays(j), "selecting on all weeks days");
//			}
//			clickbyXpath(repeateveryweekcheckbox, "Repeat everyweek");
//			// Save schedule
//			saveSchedule();
//
//			// Return to device list
//			backToHomepage();
//		}
//	}

	public void createSchedulesFromConfig() throws IOException {
		int count = Integer.parseInt(loadProp("switchescount"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH);

		for (int i = 1; i <= count; i++) {
			scrollToTextSafe("Switch " + i, 3);
			String deviceName = deviceName(loadProp("Switch" + i)).getText().trim();
			System.out.println("Creating schedule(s) for device: " + deviceName);

			// Open the device's schedule screen
			deviceName(loadProp("Switch" + i)).click();

			int period = 1;
			while (true) {
				String fromKey = String.format("%s.period%d.from", deviceName, period);
				String toKey = String.format("%s.period%d.to", deviceName, period);

				String fromTime = loadProp(fromKey);
				String toTime = loadProp(toKey);

				if (fromTime == null || toTime == null) {
					// No more periods for this switch → break inner loop
					break;
				}

				System.out.println("  Period " + period + ": " + fromTime + " → " + toTime);

				// Add schedule
				addScheduleButton();

				// Start time
				LocalTime start = LocalTime.parse(fromTime.trim(), formatter);
				setContext("start");
				selectTimeUsingBounds(start.getHour() % 12 == 0 ? 12 : start.getHour() % 12, start.getMinute(),
						start.getHour() >= 12 ? "PM" : "AM");

				// End time
				LocalTime end = LocalTime.parse(toTime.trim(), formatter);
				setContext("end");
				selectTimeUsingBounds(end.getHour() % 12 == 0 ? 12 : end.getHour() % 12, end.getMinute(),
						end.getHour() >= 12 ? "PM" : "AM");

				// Weekday selection
				for (int j = 0; j <= 5; j++) {
					clickbyXpath(weekdays(j), "selecting on all weekdays");
				}
				clickbyXpath(repeateveryweekcheckbox, "Repeat every week");

				// Save schedule
				saveSchedule();

				period++;
			}

			// Back to home after all periods for this switch
			backToHomepage();
		}
	}
	
	


	

		private List<LocalTime> generateSchedule(LocalTime startTime, int intervals, int gap) {
			List<LocalTime> schedule = new ArrayList<>();
			for (int i = 0; i < intervals; i++) {
				schedule.add(startTime.plusMinutes(i * gap));
				System.out.println("schedules:"+schedule);
			}
			return schedule;
		}

	public void backToHomepage() {
		driver.navigate().back();
//			clickbyXpath(backButton, "back button");
//			verifyTextContainsByXpath(userName(loadProp("USERNAMEINAPP")), loadProp("USERNAMEINAPP"), " DeviceName ");

	}

	public void saveSchedule() {
		// Click the save button after setting the time
		// scrollToText("Save");
		scroll2();
		clickbyXpath(savebtn, "saveschedule");

		// Validate if the schedule is saved successfully
//			verifyTextContainsByXpath(yourScheduleheader, "Schedule", "Schedule page header");

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

	public void addScheduleButton() {
		clickbyXpath(plusIcon, "plusbutton");
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

	private void scrollColumnUntilValueAtIndex0Hour(String xpath, String resourceId, String expectedValue) {

		// Find the container element using resourceId
		WebElement columnElement = driver.findElement(By.xpath(xpath));

		boolean valueAtIndex1 = false;

		while (!valueAtIndex1) {
			// Get the list of elements in the column (assuming TextView contains the
			// displayed values)
			List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));

			// Check if the expected value is at index 1 (typically the visible element)
			int centerIndex = elements.size() / 2;
			WebElement elAtCenter = elements.get(centerIndex);
			String currentValue = elAtCenter.getText();

			WebElement firstIndex = elements.get(0);
			String elFirst = firstIndex.getText();
			System.out.println("First index 0 value" + elFirst);

			if (extractintvalue(currentValue) == extractintvalue(expectedValue)) {
				valueAtIndex1 = true;
				System.out.println("Element with value " + expectedValue + " is now at centre index ");
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

			if (extractintvalue(expectedValue) == 1) {
				valueAtIndex1 = true;
				System.out.println("Element with value " + expectedValue + " is now at index 1.");
			}

		}

	}

	private void scrollColumnUntilValueAtIndex0Min(String xpath, String resourceId, String expectedValue) {
		WebElement columnElement = driver.findElement(By.xpath(xpath));
		boolean valueAtCenter = false;

		int target = Integer.parseInt(expectedValue);

		while (!valueAtCenter) {
			List<WebElement> elements = columnElement.findElements(By.className("android.widget.TextView"));

			// ✅ If only 2 elements, take the lower one as center (common in cyclic wheels)
			int centerIndex = (elements.size() == 2) ? 0 : elements.size() / 2;

			String centerText = elements.get(centerIndex).getText();
			int centerValue = extractintvalue(centerText);

			if (centerValue == target) {
				System.out.println("Element with value " + expectedValue + " is now at center.");
				valueAtCenter = true;
				break;
			}

			// Calculate swipe direction
			int compareResult = comparePickerValues(resourceId, centerText, expectedValue);

			// Handle cyclic difference
			int distance = Math.abs(centerValue - target);
			if (distance > 30)
				distance = 60 - distance;

			if (compareResult < 0) {
				smartSwipe(xpath, true, distance);
			} else {
				smartSwipe(xpath, false, distance);
			}
		}
	}

	private void scrollColumnUntilValueAtIndex1(String xpath, String resourceId, String expectedValue) {
		WebElement column = driver.findElement(By.xpath(xpath));
		expectedValue = expectedValue.trim().toUpperCase();

		int tries = 0;
		boolean aligned = false;

		// If this is the AM/PM column (resourceId for AM/PM wheel)
		if (resourceId.equals(resourseId3)) {
			while (!aligned && tries < 4) {
				// 1) Get visible AM/PM items
				List<WebElement> items = column.findElements(By.className("android.widget.TextView"));
				if (items.size() < 2) {
					throw new RuntimeException("AM/PM picker must have 2 items, found: " + items.size());
				}

				// 2) Column center
				int colTop = column.getLocation().getY();
				int colHeight = column.getSize().getHeight();
				int colCenterY = colTop + (colHeight / 2);

				// Helper: center Y of element
				java.util.function.ToIntFunction<WebElement> centerY = el -> el.getLocation().getY()
						+ (el.getSize().getHeight() / 2);

				// 3) Determine which item is actually selected (closest to column center)
				WebElement selected = items.get(0);
				int bestDist = Math.abs(centerY.applyAsInt(selected) - colCenterY);
				for (int i = 1; i < items.size(); i++) {
					int d = Math.abs(centerY.applyAsInt(items.get(i)) - colCenterY);
					if (d < bestDist) {
						bestDist = d;
						selected = items.get(i);
					}
				}

				String selectedText = selected.getText().trim().toUpperCase();
				if (selectedText.equals(expectedValue)) {
					System.out.println("✅ AM/PM aligned: " + expectedValue);
					aligned = true;
					break;
				}

				// 4) Find the target (expected value)
				WebElement target = null;
				for (WebElement el : items) {
					if (el.getText().trim().toUpperCase().equals(expectedValue)) {
						target = el;
						break;
					}
				}
				if (target == null) {
					throw new RuntimeException("Expected AM/PM value not visible: " + expectedValue);
				}

				// 5) Swipe: if target is below → forward (down); if above → backward (up)
				boolean forward = centerY.applyAsInt(target) > colCenterY;
				swipeElement(xpath, forward);
				tries++;
			}
		}
		// Fallback for hour/minute pickers (numeric wheels)
		else {
			while (!aligned && tries < 5) {
				List<WebElement> elements = column.findElements(By.className("android.widget.TextView"));

				// Find center index
				int centerIndex = (elements.size() == 2) ? 0 : elements.size() / 2;
				String centerValue = elements.get(centerIndex).getText().trim().toUpperCase();

				if (centerValue.equals(expectedValue)) {
					System.out.println("✅ Value aligned at center: " + expectedValue);
					aligned = true;
					break;
				}

				// Compare numeric values
				int compareResult = comparePickerValues(resourceId, centerValue, expectedValue);
				swipeElement(xpath, compareResult < 0); // true = forward, false = backward

				tries++;
			}
		}

		if (!aligned) {
			throw new RuntimeException("❌ Could not align value to " + expectedValue);
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
		if (currentValue.equals(expectedValue)) {
			return 0;
		}
		// If current is AM and expected is PM → need forward scroll
		if (currentValue.equals("AM") && expectedValue.equals("PM")) {
			return -1;
		}
		// If current is PM and expected is AM → need backward scroll
		if (currentValue.equals("PM") && expectedValue.equals("AM")) {
			return 1;
		}
		return 0;
	}

	public void disableschedule(int min) throws Exception, IOException, InterruptedException {

		clickbyXpath(disableschedule, "disable schedule");
		Thread.sleep(min * 60 * 1000);
		driver.navigate().back();
		verifyTextContainsByXpath(acturnoffdesc,
				"Please ensure sZephyr is switched ON prior to operating your AC remote", "AC truned OFF description");

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

	private void smartSwipe(String columnXPath, boolean forward, int distance) {
		WebElement columnElement = driver.findElement(By.xpath(columnXPath));

		int columnCenterX = columnElement.getLocation().getX() + (columnElement.getSize().getWidth() / 2);
		int startY = forward ? columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.6)
				: columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.2);
		int endY = forward ? columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.2)
				: columnElement.getLocation().getY() + (int) (columnElement.getSize().getHeight() * 0.6);

		// ⏱ Adjust swipe speed: small distance → slower swipe, big distance → faster
		// swipe
		int swipeDuration = (distance <= 2) ? 400 : (distance <= 5) ? 200 : 80;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1)
				.addAction(
						finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), columnCenterX, startY))
				.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerMove(Duration.ofMillis(swipeDuration), PointerInput.Origin.viewport(),
						columnCenterX, endY))
				.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(swipe));
	}

}