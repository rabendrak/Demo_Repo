package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id="input-email")
	private WebElement emailAdressTextField;
	
	@FindBy(id="input-password")
	private WebElement passwordTestField;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	public WebElement emailAdressTextField() {
		return emailAdressTextField;
	}
	public WebElement passwordTextField() {
		return passwordTestField;
	}
	public WebElement loginButton() {
		return loginButton;
	}
}
