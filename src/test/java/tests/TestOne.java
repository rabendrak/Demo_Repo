package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.Base;

public class TestOne extends Base{
public WebDriver driver;
@Test
public void openApplication() throws IOException {
	driver=initializeDriver();
	driver.get("https://ok.xxx/");
	driver.close();
}
}
