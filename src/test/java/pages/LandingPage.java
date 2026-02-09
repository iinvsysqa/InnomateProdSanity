package pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import wrappers.GenericWrappers;

public class LandingPage extends GenericWrappers{
	    
	    private AndroidDriver driver;
	    
	 // Locate all elements on the page
	    
	    @FindBy(xpath = "//*[@resource-id='Sign InButton']")
	    private WebElement signInButton;
	    
	    @FindBy(xpath = "//*[@resource-id='SignUp_SignUpText']")
	    private WebElement signUpButton;
	    
	    @FindBy(id = "loginButton")
	    private WebElement loginButton;
	
	    @FindBy(xpath = "//*[@resource-id='SignUpButtonText']")
		private WebElement signUpLink;
	    
	    //div[text()='Home']//following-sibling::div[1]
	    
	    // Constructor
	    @FindBy(xpath = "//android.widget.TextView[@text=\"Next\"]")
	  		private WebElement landingPageNextBtn;
	    
	    @FindBy(xpath = "//android.widget.TextView[@text=\"Get Started\"]")
  		private WebElement landingPageGetStartedBtn;
	    
	    @FindBy(xpath = "//*[@resource-id='UserNameInput']")
  		private WebElement userNameTextbox;
	    
	    @FindBy(xpath = "//*[@resource-id='passwordInput']")
  		private WebElement passwordTextBox;
	  
	    @FindBy(xpath = "//*[@resource-id='SignInText']")
  		private WebElement signInTitle;
	  
	    
	    public LandingPage(AndroidDriver driver) {
	        this.driver=driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	    // Methods to interact with elements
	    
	    public void clickSignInButton() {
	    	clickbyXpath(signInButton, " Sign In Button " );
	    }
	    
	    public void checkSignInPageTitle() {
	    	verifyTextContainsByXpath(signInTitle, "Sign In", "User in Sign In Page and It ");
	    }
	    
	    public void clickLandingPageNextBtn() {
	    	try {
	    		if(expWaitTillElementDisplay(landingPageNextBtn,10)){
	    		clickbyXpath(landingPageNextBtn, " Landing page next button " );
	    		clickbyXpath(landingPageNextBtn, " Landing page next button " );
	    		clickbyXpath(landingPageGetStartedBtn, " Landing page Get started button " );
	    		}
	    	}catch (Exception e) {
				// TODO: handle exception
			}
	    	
	    }
	    
	    
	    public void enterUserName(String username) {
	    	entervaluebyXpath(userNameTextbox, " Enter User Name ", username );
	    }
	    
	    public void enterPassword(String password) {
	    	entervaluebyXpath(passwordTextBox, " Enter Password ", password );
	    }
	    
	    public void clickSignUpLink() {
	    	clickbyXpath(signUpLink, " Sign Up Button " );
	    }
	    public void clickSignUpButton() {
	    	clickbyXpath(signUpButton, " Sign Up Button " );
	    }
	    
}
