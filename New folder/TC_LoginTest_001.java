package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest()
	{
		reportManager.test = reportManager.extent.createTest("Login Test");

		HomePage hp = new HomePage(driver);
		hp.clickLoginPage();  // Navigate to the Login page

		LoginPage lp = new LoginPage(driver);

		try {
			// Attempt login
			boolean loginSuccessful = lp.attemptLogin(username, password, captcha);

			if (loginSuccessful) {
				logger.info("Login successful!");
			} else {
				logger.error("Login failed!");
				captureScreen(driver, "LoginFailure");  // Capture screenshot if login failed
				Assert.fail("Login failed");
			}
		} catch (Exception e) {
			// If any exception occurs, capture the screenshot and fail the test
			try {
				captureScreen(driver, "LoginException");  // Capture screenshot in case of an exception
			} catch (Exception ex) {
				logger.error("Failed to capture screenshot", ex);
			}
			logger.error("Login failed due to exception: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}

}
