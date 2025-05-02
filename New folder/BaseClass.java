package testCases;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LogoutPage;
import utilities.ExtentReportManager;
import utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public String captcha=readconfig.getCaptcha();
	public String checkLoginFailPopMsg=readconfig.checkLoginFailPopMsg();
	public static WebDriver driver;
	public Logger logger;
	public ExtentReportManager reportManager;

	@BeforeClass
	public void setup()
	{
		reportManager = new ExtentReportManager();
		reportManager.onStart(null);

		logger=LogManager.getLogger(this.getClass());
		//		System.setProperty("webdriver.chrome.driver",=System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe";
		//		driver=new ChromeDriver();

		//		WebDriverManager.chromedriver().setup();
		//		driver = new ChromeDriver();

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		logger.info("Browser launch successfuly !");
		Assert.assertTrue(true);
		driver.manage().window().maximize();
		logger.info("Browser Maximize successfuly !");
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		logger.info("URLs launch successfuly !");
		Assert.assertTrue(driver.getCurrentUrl().contains("nextgen.ehospital.nic.in"), "Base URL did not load correctly");
	}

	@AfterClass
	public void tearDown() {
		LogoutPage logoutP = new LogoutPage(driver);
		logoutP.clickUserProfile();
		logger.info("Skip UserProfile Tab clicked successfuly !");
		logoutP.clickLogoutBtn();
		logger.info("Skip Logout button clicked successfuly !");
		logoutP.clickFeedbackSkipBtn();
		logger.info("Skip Feedback button clicked successfuly !");
		Assert.assertTrue(true);
		System.out.println("Logout Successfully !");
		driver.quit();
		logger.info("Close Browser");
		reportManager.onFinish(null);
	}


	public void captureScreen(WebDriver driver, String tname) throws IOException {
		// Take screenshot and save it to the target location
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken for: " + tname);
	}


}
