package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class TestFour extends Base{
	public WebDriver driver;
	@Test
	public void openApplication() throws IOException {
		Logger log = LogManager.getLogger(TestFour.class.getName());
		driver=initializeDriver();
		log.debug("inside class four");
		driver.get("https://www.amazon.in/");
		Assert.assertTrue(false);
		
		
		
	}
	@AfterMethod
	public void closer() {
		driver.close();
	}
	

}
