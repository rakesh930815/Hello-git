package testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import pageObjects.DashboardPage;
import pageObjects.OPDRegistrationPage;
import utilities.ExcelUtils;

public class TC_OPD_NewRegistration_001 extends TC_LoginTest_001 {
	
	
	@Test(priority=1)
	public void OPDRegistration() throws InterruptedException {
		DashboardPage dashboard = new DashboardPage(driver);
		Thread.sleep(2000);
		dashboard.clickOPDRegistration();
		
	}
	
	
	
	@Test(priority=2)
	public void newRegistrationMandatoryFields() throws InterruptedException, IOException {
		OPDRegistrationPage opdr = new OPDRegistrationPage(driver);
		Thread.sleep(5000);
		opdr.clicknpra();
		String filePath=System.getProperty("user.dir")+"\\src\\test\\java\\testData\\patientRegistration.xlsx";
		int rows=ExcelUtils.getRowCount(filePath, "Sheet1");
		
		for(int i=1;i<=rows;i++){
			//read data from excel
			String patientMobile=ExcelUtils.getCellData(filePath, "Sheet1", i,2);
			String patientTitle=ExcelUtils.getCellData(filePath, "Sheet1", i,3);
			String patientFirstname=ExcelUtils.getCellData(filePath, "Sheet1", i,4);
			String patientGender=ExcelUtils.getCellData(filePath, "Sheet1", i,7);
			String patientAgeYear=ExcelUtils.getCellData(filePath, "Sheet1", i,9);
			String patientAddress=ExcelUtils.getCellData(filePath, "Sheet1", i,15);
			String patientCitizenship=ExcelUtils.getCellData(filePath, "Sheet1", i,16);
			String patientState=ExcelUtils.getCellData(filePath, "Sheet1", i,17);
			String patientDistrict=ExcelUtils.getCellData(filePath, "Sheet1", i,18);
			String patientBillingType=ExcelUtils.getCellData(filePath, "Sheet1", i,19);
			String patientDepartment=ExcelUtils.getCellData(filePath, "Sheet1", i,21);
			String patientClinic=ExcelUtils.getCellData(filePath, "Sheet1", i,22);
			

			//pass above data into application
			opdr.patientMobile(patientMobile);
			opdr.patientTitle(patientTitle);
			opdr.patientFirstname(patientFirstname);
			opdr.patientGender(patientGender);
			opdr.patientAgeYear(patientAgeYear);
			opdr.patientAddress(patientAddress);
			opdr.patientCitizenship(patientCitizenship);
			opdr.patientState(patientState);
			opdr.patientDistrict(patientDistrict);
			opdr.patientBillingType(patientBillingType);
			opdr.patientDepartment(patientDepartment);
			opdr.patientClinic(patientClinic);
			opdr.patientPractitioner();
			opdr.patientRegisterBtn();

			//Validation
			
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
			                System.out.println("Found: " + reg);
			                ExcelUtils.setCellData(filePath, "Sheet1",i,23,"Passed");
							ExcelUtils.fillGreenColor(filePath, "Sheet1",i,23);
							ExcelUtils.setCellData(filePath, "Sheet1",i,24,reg);
							driver.findElement(By.xpath("//button[text()='Cancel']")).click();
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
			    System.out.println("Registration No not found.");
			    ExcelUtils.setCellData(filePath, "Sheet1",i,23,"Fiailed");
				ExcelUtils.fillRedColor(filePath, "Sheet1",i,7);
			}


		}

	}

}
