package StepDefinations;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import common.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import pages.restaurantPage;

public class Steps {

	public static WebDriver driver = null;
	public static JSONObject dataobj,configobj;
	public static Logger logger;
	
	@Given("User completed the initial setup")
	public void initial_setup() throws InterruptedException, IOException, ParseException {
		
		logger = Logger.getLogger("Steps");
		PropertyConfigurator.configure("log4j.properties");
		
		configobj = common.readJSON(System.getProperty("user.dir")+"\\src\\test\\java\\data\\configuration.json");
		dataobj = common.readJSON(System.getProperty("user.dir")+"\\src\\test\\java\\data\\testdata.json"); 
		
		driver = common.openBrowser(driver, (String)configobj.get("browser"));
		
		driver.get((String)configobj.get("url"));
		common.waitForTime();
	    restaurantPage.getCookieBtn().click();
	    logger.info("initial setup completed successfully");
	}

	@When("User search and select restaurant name")
	public void selectRestaurant() throws InterruptedException {		
		restaurantPage.getSearchBtn().sendKeys((String)dataobj.get("restaurantName"));
		common.waitForTime();
		restaurantPage.getRestaurant((String)dataobj.get("restaurantName")).click();
		logger.info("user click on restaurant name = "+(String)dataobj.get("restaurantName"));
	}

	@And("User add order into basket")
	public void addOrderToBasket() throws InterruptedException {
		common.waitForTime();	
		restaurantPage.getAddToBasket();
		logger.info("user added burger to basket");
	}

	@And("User enter incorrect city information")
	public void enterIncorrectDeliverInformation() throws InterruptedException {
		common.waitForTime();
		restaurantPage.getCheckOutBtn().click();
		common.waitForTime();
		restaurantPage.getStreetNameTxt().sendKeys((String)dataobj.get("streetName"));
		restaurantPage.getStreetNumberTxt().sendKeys((String)dataobj.get("streetNo"));
		restaurantPage.getPostalCodeTxt().clear();
		restaurantPage.getPostalCodeTxt().sendKeys((String)dataobj.get("postalCode"));
		restaurantPage.getCityTxt().clear();
		restaurantPage.getCityTxt().sendKeys((String)dataobj.get("incorrectCity"));
		logger.info("user enter all delivery information");
	}
	@And("User enter correct city information")
	public void enterCorrectDeliverInformation() throws InterruptedException {
		common.waitForTime();
		restaurantPage.getCheckOutBtn().click();
		common.waitForTime();
		restaurantPage.getStreetNameTxt().sendKeys((String)dataobj.get("streetName"));
		restaurantPage.getStreetNumberTxt().sendKeys((String)dataobj.get("streetNo"));
		restaurantPage.getPostalCodeTxt().clear();
		restaurantPage.getPostalCodeTxt().sendKeys((String)dataobj.get("validpostalCode"));
		restaurantPage.getCityTxt().clear();
		restaurantPage.getCityTxt().sendKeys((String)dataobj.get("correctCity"));
		logger.info("user enter all delivery information");
	}
	@And("User enter personal information")
	public void enterPersonalInformation() throws InterruptedException {
		restaurantPage.getFullName().sendKeys((String)dataobj.get("fullName"));
		restaurantPage.getEmailTxt().sendKeys((String)dataobj.get("email"));
		restaurantPage.getPhoneTxt().sendKeys((String)dataobj.get("phoneNo"));
		logger.info("user enter all personal information");
	}
	@Then("User submit the order")
	public void submitOrder() throws InterruptedException {
		common.waitForTime();
		restaurantPage.getPayWithBtn().click();
		restaurantPage.getCashBtn().click();
		restaurantPage.getDoneBtn().click();
		restaurantPage.getSubmitOrderBtn().click();
		logger.info("user submit the order");
	}
	
	@And("User able to see delivery error message")
	public void verifyDeliveryErrorMsg() throws InterruptedException {
		common.waitForTime();
		Assert.assertTrue(restaurantPage.getErrorMsg().contains((String)dataobj.get("errorMsg")));
		logger.info("delivery error message displayed successfully = "+restaurantPage.getErrorMsg());
	}
	@And("User select menu {string} from navigation bar")
	public void selectMenu(String menu) throws InterruptedException {
		common.waitForTime();
		restaurantPage.getMenu(menu).click();
		logger.info("menu selected successfully = "+menu);
	}
	@And("^User select ([^\"]*) language$")
	public void selectLanguage(String lang) throws InterruptedException {
		restaurantPage.getLanguage(driver, lang);
		logger.info("language selected successfully = "+lang);
	}
	@And("^User select ([^\"]*) country$")
	public void selectCountry(String country) throws InterruptedException {
		restaurantPage.getCountry(driver, country);
		logger.info("country selected successfully = "+country);
	}
	@Then("User completed teardown")
	public void teardown() throws InterruptedException {
		common.closeBrowser(driver);
	}
}
