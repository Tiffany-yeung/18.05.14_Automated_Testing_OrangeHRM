
import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Assessment {
	
	private WebDriver driver;
	private Actions actions;
	private ExtentTest testReport;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "/Users/Tiffany/Development/Chrome_Driver/chromedriver");
		driver = new ChromeDriver();
		actions = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.pathTestData + Constants.fileTestData, 0);	
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Given("^the Add Employee Tab$")
	public void the_Add_Employee_Tab() throws InterruptedException{
		driver.get(Constants.loginURL);
		WebElement adminUser = driver.findElement(By.xpath(
				"//*[@id=\"txtUsername\"]"));
		adminUser.sendKeys("Admin");
		WebElement adminPassword = driver.findElement(By.xpath(
				"//*[@id=\"txtPassword\"]"));
		adminPassword.sendKeys("admin");
		WebElement adminLogin = driver.findElement(By.xpath(
				"//*[@id=\"btnLogin\"]"));
		adminLogin.click();
		Thread.sleep(2000);
		WebElement pim = driver.findElement(By.xpath(
				"//*[@id=\"menu_pim_viewPimModule\"]"));
		pim.click();
		Thread.sleep(2000);
		WebElement addEmployee = driver.findElement(By.xpath(
				"//*[@id=\"menu_pim_addEmployee\"]"));
		addEmployee.click();
		Thread.sleep(2000);
	}

	@When("^I fill out the Employee Details correctly$")
	public void i_fill_out_the_Employee_Details_correctly(){
		WebElement addFirstName = driver.findElement(By.xpath(
				"//*[@id=\"firstName\"]"));
		addFirstName.sendKeys("Tiffany");
		WebElement addLastName = driver.findElement(By.xpath(
				"//*[@id=\"lastName\"]"));
		addLastName.sendKeys("Yeung");
		WebElement addId = driver.findElement(By.xpath(
				"//*[@id=\"employeeId\"]"));
		addId.clear();
		addId.sendKeys("001");
	}

	@When("^I choose to create Login Details$")
	public void i_choose_to_create_Login_Details() throws InterruptedException{
		WebElement tickLoginDetails = driver.findElement(By.xpath(
				"//*[@id=\"chkLogin\"]"));
		tickLoginDetails.click();
		Thread.sleep(2000);
	}

	@When("^I fill out the Login Details correctly$")
	public void i_fill_out_the_Login_Details_correctly() throws InterruptedException{
		WebElement addUserName = driver.findElement(By.xpath(
				"//*[@id=\"user_name\"]"));
		addUserName.sendKeys("Tiffany");
		WebElement addPassword = driver.findElement(By.xpath(
				"//*[@id=\"user_password\"]"));
		addPassword.sendKeys("Password1");
		WebElement confirmPassword = driver.findElement(By.xpath(
				"//*[@id=\"re_password\"]"));
		confirmPassword.sendKeys("Password1");
	}

	@When("^I click the Save button$")
	public void i_click_the_Save_button() throws InterruptedException{
		WebElement adminLogin = driver.findElement(By.xpath(
				"//*[@id=\"btnSave\"]"));
		adminLogin.click();
		Thread.sleep(2000);
	}

	@Then("^I can see information about the user$")
	public void i_can_see_information_about_the_user() throws Throwable {
		WebElement checkUserName = driver.findElement(By.xpath(
				"//*[@id=\"profile-pic\"]/h1"));
		assertEquals("Tiffany Yeung", checkUserName.getText());
		Thread.sleep(2000);
	}
}
