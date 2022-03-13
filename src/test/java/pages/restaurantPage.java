package pages;

import java.util.List;
import common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import StepDefinations.Steps;

public class restaurantPage {

	public static WebElement getCookieBtn()
	{
		return Steps.driver.findElement(By.xpath("//button[text()='OK']"));
	}
	public static WebElement getSearchBtn()
	{
		return Steps.driver.findElement(By.name("search"));
	}
	public static WebElement getRestaurant(String restaurantName)
	{
		WebElement name = null;
		List<WebElement> getAllRestaurants = Steps.driver.findElements(By.xpath("//div[@id='search-results-panel-restaurants']"));
		for(WebElement restaurant:getAllRestaurants)
		{
			if(restaurant.getText().contains(restaurantName))
			{
				name = Steps.driver.findElement(By.xpath("//h3[text()='"+restaurantName+"']")); 
			}
		}
		return name;
	}
	public static void getAddToBasket()
	{
		List<WebElement> burgers = Steps.driver.findElements(By.xpath("//div[@data-qa='item-element' and @role='button']"));
		for(WebElement add:burgers)
		{
			add.click();
			break;
		}
	}
	public static WebElement getCheckOutBtn()
	{
		return Steps.driver.findElement(By.xpath("//button[@data-qa='sidebar-action-checkout']"));
	}
	public static WebElement getStreetNameTxt()
	{
		return Steps.driver.findElement(By.name("streetName"));
	}
	public static WebElement getStreetNumberTxt()
	{
		return Steps.driver.findElement(By.name("streetNumber"));
	}
	public static WebElement getPostalCodeTxt()
	{
		return Steps.driver.findElement(By.name("postalCode"));
	}
	public static WebElement getCityTxt()
	{
		return Steps.driver.findElement(By.name("city"));
	}
	public static WebElement getFullName()
	{
		return Steps.driver.findElement(By.name("fullName"));
	}
	public static WebElement getEmailTxt()
	{
		return Steps.driver.findElement(By.name("email"));
	}
	public static WebElement getPhoneTxt()
	{
		return Steps.driver.findElement(By.name("phoneNumber"));
	}
	public static WebElement getPayWithBtn()
	{
		return Steps.driver.findElement(By.xpath("//b[text()='Pay with']"));
	}
	public static WebElement getCashBtn()
	{
		return Steps.driver.findElement(By.xpath("//span[text()='Cash']"));
	}
	public static WebElement getDoneBtn()
	{
		return Steps.driver.findElement(By.xpath("//button[text()='Done']"));
	}
	public static WebElement getSubmitOrderBtn()
	{
		return Steps.driver.findElement(By.xpath("//button[@data-qa='multi-step-checkout-action-submit-order']"));
	}
	public static String getErrorMsg()
	{
		return Steps.driver.findElement(By.xpath("//div[@data-qa='local-error-banner-delivery']")).getText();
	}
	public static WebElement getMenu(String menu)
	{
		return Steps.driver.findElement(By.xpath("//span[text()='"+menu+"']"));
	}
	public static WebElement getCountrySelector()
	{
		return Steps.driver.findElement(By.xpath("//span[@data-qa='header-country-and-language-selector-action-toggle']"));
	}
	public static WebDriver getCountry(WebDriver driver,String CountryName) throws InterruptedException
	{
		restaurantPage.getCountrySelector().click();
		common.waitForTime();
		
		List<WebElement> countries = Steps.driver.findElements(By.xpath("//div[@data-qa='header-country-and-language-selector-panel']//* [@data-qa='country']"));

		for(WebElement country:countries)
		{
			if(country.getText().contains(CountryName))
			{
				country.click();
				break;
			}
		}
		return Steps.driver;
	}
	public static WebDriver getLanguage(WebDriver driver,String languageName) throws InterruptedException
	{
		restaurantPage.getCountrySelector().click();
		common.waitForTime();
		List<WebElement> languages = Steps.driver.findElements(By.xpath("//div[@data-qa='language']"));

		for(WebElement language:languages)
		{
			if(language.getText().contains(languageName))
			{
				language.click();
				common.waitForTime();
				break;
			}
		}
		return Steps.driver;
	}
}
