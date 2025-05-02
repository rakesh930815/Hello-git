package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OPDRegistrationPage extends BasePage {
	
	 public OPDRegistrationPage(WebDriver driver) {
	        super(driver); // initializes driver and PageFactory
	    }
	
	
	@FindBy(css="button[mat-dialog-close] .fa.fa-close")
	WebElement npra;
	
	@FindBy(id="patmobilenumber")
	WebElement pdmobile;
	
	@FindBy(id="appellationcode")
	WebElement pdtitle;
	
	@FindBy(id="patfirstname")
	WebElement pdfirst_name;
	
	@FindBy(id="patmiddlename")
	WebElement pdmiddle_name;
	
	@FindBy(id="patlastname")
	WebElement pdlast_name;
	
	@FindBy(id="mat-select-2")
	WebElement pdgender;
	
	@FindBy(id="patdateofbirth")
	WebElement pddob;
	
	@FindBy(id="patientageinyrs")
	WebElement pdagey;
	
	@FindBy(id="patageinmonths")
	WebElement pdagem;
	
	@FindBy(id="patageindays")
	WebElement pdaged;
	
	@FindBy(id="patmothername")
	WebElement pdmother_name;
	
	@FindBy(id="patfathername")
	WebElement pdfather_name;
	
	@FindBy(id="relationdata")
	WebElement pdpatient_guardian_relationship;
	
	@FindBy(id="patientaddressline")
	WebElement pdaddress;
	
	@FindBy(id="citizenshipdata")
	WebElement pdcitizenship;
	
	@FindBy(id="patstatecode")
	WebElement pdstate;
	
	@FindBy(id="patientdistcode")
	WebElement pddistrict;
	
	@FindBy(id="billingdata")
	WebElement pdbilling_type;
	
	@FindBy(id="verifiedDetails")
	WebElement pdverified_details;
	
	@FindBy(id="departmentdata")
	WebElement vddepartment;
	
	@FindBy(id="clinicdata")
	WebElement vdclinic;
	
	@FindBy(xpath="//mat-chip[contains(., 'Rakesh Kumar')]")
	WebElement vdpractitioner;
	
	@FindBy(xpath="//button[text()='Register']")
	WebElement vdregister_button;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement cancelReceipt_button;
	
//	public boolean isRegistrationFormVisible() {
//	    try {
//	        return driver.findElement(By.xpath("//b[contains(text(), 'New Patient Registration Using ABHA')]")).isDisplayed();
//	    } catch (NoSuchElementException e) {
//	        return false;
//	    }
//	}
	
	public void clicknpra() {
		waitForClickable(npra).click();
	}
	
	public void patientMobile(String mobile) {
		waitForVisibility(pdmobile).sendKeys(mobile);
	}
	
	public void patientTitle(String title) {
	    // Ensure the dropdown is clicked to reveal the options
		waitForClickable(pdtitle).click();

	    // Wait until the dropdown options are visible (you can use WebDriverWait to ensure visibility)
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));

	    // Locate the dropdown options
	    List<WebElement> pdtitleValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));

	    // Loop through the dropdown options and click the one that matches the title
	    for (WebElement option : pdtitleValues) {
	        if (option.getText().equals(title)) {
	            option.click();  // Click the option with the matching title
	            break;  // Exit loop after selecting the title
	        }
	    }
	}

	
	public void patientFirstname(String firstname) {
		waitForVisibility(pdfirst_name).sendKeys(firstname);
	}
	
	public void patientMiddlename(String middlename) {
		waitForVisibility(pdmiddle_name).sendKeys(middlename);
	}
	
	public void patientLastname(String lastname) {
		waitForVisibility(pdlast_name).sendKeys(lastname);
	}
	
	public void patientGender(String gender) {
		waitForClickable(pdgender).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> pdgenderValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : pdgenderValues) {
	        if (option.getText().equals(gender)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientDOB(String pdob) {
		waitForVisibility(pddob).sendKeys(pdob);
	}
	
	public void patientAgeYear(String ageyear) {
		waitForVisibility(pdagey).sendKeys(ageyear);
	}
	
	public void patientAgeMonth(String agemonth) {
		waitForVisibility(pdagem).sendKeys(agemonth);
	}
	
	public void patientAgeDays(String agedays) {
		waitForVisibility(pdaged).sendKeys(agedays);
	}
	
	public void patientMothername(String mothername) {
		waitForVisibility(pdmother_name).sendKeys(mothername);
	}
	
	public void patientFathername(String fathername) {
		waitForVisibility(pdfather_name).sendKeys(fathername);
	}
	
	public void patientGuardianRelationship(String pgr) {
		waitForVisibility(pdpatient_guardian_relationship).sendKeys(pgr);
	}
	
	public void patientAddress(String address) {
		waitForVisibility(pdaddress).sendKeys(address);
	}
	
	public void patientCitizenship(String citizenship) {
		waitForClickable(pdcitizenship).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> pdcitizenshipValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : pdcitizenshipValues) {
	        if (option.getText().equals(citizenship)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientState(String state) {
		waitForClickable(pdstate).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> pdstateValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : pdstateValues) {
	        if (option.getText().equals(state)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientDistrict(String district) {
		waitForClickable(pddistrict).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> pddistrictValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : pddistrictValues) {
	        if (option.getText().equals(district)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientBillingType(String billingtype) {
		waitForClickable(pdbilling_type).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> pdbillingtypeValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : pdbillingtypeValues) {
	        if (option.getText().equals(billingtype)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientVerifiedDetails(String verifieddetails) {
		waitForVisibility(pdverified_details).sendKeys(verifieddetails);
	}
	
	public void patientDepartment(String department) {
		vddepartment.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> vddepartmentValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : vddepartmentValues) {
	        if (option.getText().equals(department)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientClinic(String clinic) {
		vdclinic.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//mat-option[contains(@id, 'mat-option-')]")));
	    List<WebElement> vdclinicValues = driver.findElements(By.xpath("//mat-option[contains(@id, 'mat-option-')]"));
	    for (WebElement option : vdclinicValues) {
	        if (option.getText().equals(clinic)) {
	            option.click(); 
	            break; 
	        }
	    }
	}
	
	public void patientPractitioner() {
		vdpractitioner.click();
		//Thread.sleep(5000);
	}
	
	public void patientRegisterBtn() {
		waitForClickable(vdregister_button).click();
		System.out.println("registration done !");
	}
	
	public void closeReceiptSlip() {
		WebElement element = cancelReceipt_button;
		((RemoteWebDriver) driver).executeScript("arguments[0].click();", element);
		//waitForClickable(cancelReceipt_button).click();
	}
	
	

}
