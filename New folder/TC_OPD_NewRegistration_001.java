package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.OPDRegistrationPage;
import utilities.ExcelUtils;

public class TC_OPD_NewRegistration_001 extends BaseClass {

	private String filePath;

	@BeforeMethod
	public void OPDRegistration(){

		reportManager.test = reportManager.extent.createTest("OPD Registration");

		logger.info("******* Starting TC_OPD_NewRegistration_001 ********");
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.isHomePageLoaded(), "Home page did not load correctly");
		logger.info("NextGen Home Page Display Properly !");
		hp.clickLoginPage();

		LoginPage lp = new LoginPage(driver);
		//Assert.assertTrue(lp.isLoginPageLoaded(), "Login page did not load correctly");
		//lp.login(username, password, captcha);
		lp.attemptLogin(username, password, captcha);
		Assert.assertTrue(lp.isDashboardPageDisplayed(), "Dashboard page is not displayed");
		logger.info("Enter username, password, and captcha. Login successfully!");

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.clickOPDRegistration();
		Assert.assertTrue(dashboard.isOPDRegistrationPageDisplayed(), "OPDRegistration page is not displayed");
		logger.info("OPD Registration Module Displayed Properly!");

		this.filePath = System.getProperty("user.dir") + "\\src\\test\\java\\testData\\patientRegistration.xlsx";

		Assert.assertTrue(true);

	}


	@Test(priority=1)
	public void newRegistrationMandatoryFields() throws IOException {

		reportManager.test = reportManager.extent.createTest("New Registration with Mandatory Fields");

		logger.info("******* New Registration with Mandatory Fields without ABHA ********");

		OPDRegistrationPage opdr = new OPDRegistrationPage(driver);
		opdr.clicknpra();
		logger.info("Cancel ABHA Registration Link");

		int rows = ExcelUtils.getRowCount(filePath, "Sheet1");

		for (int i = 1; i <= rows; i++) {
			// Read data from Excel
			String patientMobile = ExcelUtils.getCellData(filePath, "Sheet1", i, 0);
			String patientTitle = ExcelUtils.getCellData(filePath, "Sheet1", i, 1);
			String patientFirstname = ExcelUtils.getCellData(filePath, "Sheet1", i, 2);
			String patientGender = ExcelUtils.getCellData(filePath, "Sheet1", i, 3);
			String patientAgeYear = ExcelUtils.getCellData(filePath, "Sheet1", i, 4);
			String patientAddress = ExcelUtils.getCellData(filePath, "Sheet1", i, 5);
			String patientCitizenship = ExcelUtils.getCellData(filePath, "Sheet1", i, 6);
			String patientState = ExcelUtils.getCellData(filePath, "Sheet1", i, 7);
			String patientDistrict = ExcelUtils.getCellData(filePath, "Sheet1", i, 8);
			String patientBillingType = ExcelUtils.getCellData(filePath, "Sheet1", i, 9);
			String patientDepartment = ExcelUtils.getCellData(filePath, "Sheet1", i, 10);
			String patientClinic = ExcelUtils.getCellData(filePath, "Sheet1", i, 11);

			// Pass data into application
			opdr.patientMobile(patientMobile);
			logger.info("Enter Mobile Number");
			opdr.patientTitle(patientTitle);
			logger.info("Select Title");
			opdr.patientFirstname(patientFirstname);
			logger.info("Enter First Name");
			opdr.patientGender(patientGender);
			logger.info("Select Gender");
			opdr.patientAgeYear(patientAgeYear);
			logger.info("Enter Age in years");
			opdr.patientAddress(patientAddress);
			logger.info("Enter Patient Address");
			opdr.patientCitizenship(patientCitizenship);
			logger.info("Select Citizenship");
			opdr.patientState(patientState);
			logger.info("Select State");
			opdr.patientDistrict(patientDistrict);
			logger.info("Select District");
			opdr.patientBillingType(patientBillingType);
			logger.info("Select Billing Type");
			opdr.patientDepartment(patientDepartment);
			logger.info("Select Department");
			opdr.patientClinic(patientClinic);
			logger.info("Select Clinic");
			opdr.patientPractitioner();
			logger.info("Select Doctor from the roaster");
			opdr.patientRegisterBtn();
			logger.info("Submit Patient Registration");

			// Validation
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement tableBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody")));
			List<WebElement> listOfRows = tableBody.findElements(By.xpath("./tr"));

			boolean found = false;

			for (WebElement rowElement : listOfRows) {
				List<WebElement> tdcells = rowElement.findElements(By.tagName("td"));
				for (WebElement cellElement : tdcells) {
					String cellText = cellElement.getText();
					if (cellText.contains("Registration No")) {
						// Use regex to extract only "Registration No : 20250000602"
						Pattern pattern = Pattern.compile("(Registration No\\s*:\\s*\\d+)");
						Matcher matcher = pattern.matcher(cellText);
						if (matcher.find()) {
							String reg = matcher.group(1);
							logger.info("Patient Registration Done!");
							ExcelUtils.setCellData(filePath, "Sheet1", i, 12, "Passed");
							ExcelUtils.fillGreenColor(filePath, "Sheet1", i, 12);
							ExcelUtils.setCellData(filePath, "Sheet1", i, 13, reg);
							driver.findElement(By.xpath("//button[text()='Cancel']")).click();
							logger.info("Cancel Slip!");
							found = true;
							break;
						}
					}
				}
				if (found) {
					break;
				}
			}

			if (!found) {
				logger.info("Patient Registration not done!");
				captureScreen(driver, "RegistrationFailed_" + i);  // Capture screenshot when registration fails
				ExcelUtils.setCellData(filePath, "Sheet1", i, 12, "Failed");
				ExcelUtils.fillRedColor(filePath, "Sheet1", i, 12);
				Assert.fail("Patient Registration Failed!");

			}


		}

	}



}
