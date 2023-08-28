package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {
	
	public WebDriver driver;
	Logger log;
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver=initializeDriver();
		log.debug("browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("navigate to URL application");
		
		
	}

	@Test(dataProvider = "getLoginData")
	public void loginTest(String email,String password,String expectedResult) throws IOException, InterruptedException {
		
		
		
	LandingPage landingPage = new LandingPage(driver);
	landingPage.myAccountDropdown().click();
	log.debug("Clicked on My Account dropdown");
	landingPage.loginOption().click();
	log.debug("Clicked on login option");
	
	LoginPage login = new LoginPage(driver);
	login.emailAdressTextField().sendKeys(email);
	log.debug("email adress got entered");
	login.passwordTextField().sendKeys(password);
	log.debug("password got entered");
	login.loginButton().click();
	log.debug("click on login button");
	
	Thread.sleep(3000);
	
	
	AccountPage accountPage = new AccountPage(driver);
	log.debug("User got logged in");
	
	
	String actualResult=null;
	try {
		if(accountPage.editYourAccountInformation().isDisplayed()) {
			log.debug("User got logged in");
			actualResult="success";
		}
	}catch(Exception e) {
		log.error("Login Test got failed");
		actualResult="failure";
	}
	
	Assert.assertEquals(actualResult, expectedResult);
		
	
	
	
	
			
		Thread.sleep(3000);
		
		
	}
	
	@AfterMethod
	public void closer() {
		driver.close();
	}
	@DataProvider
	public Object[][] getLoginData() {
		Object [][]data= {{"ravi001@gmail.com","12345","success"},{"dummy@gmail.com","54321","failure"}};
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
