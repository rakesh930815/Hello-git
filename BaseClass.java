package testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public String captcha=readconfig.getCaptcha();
	public String checkLoginFailPopMsg=readconfig.checkLoginFailPopMsg();
	public static WebDriver driver;

	@BeforeClass
	public void setup()
	{
//		System.setProperty("webdriver.chrome.driver",=System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe";
//		driver=new ChromeDriver();
		
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	//@AfterClass
	public void tearDown() {
		driver.quit();
	}

	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

}
