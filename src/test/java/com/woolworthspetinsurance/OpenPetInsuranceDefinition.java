package com.woolworthspetinsurance;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.planittesting.ioc.ObjectContainer;
import com.woolworthspetinsurance.pages.HomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class OpenPetInsuranceDefinition {
	private ObjectContainer container;
	WebDriver driver;
	HomePage homePage;
	

	public OpenPetInsuranceDefinition(ObjectContainer container) {
		this.container = container;
		driver = this.container.resolve(WebDriver.class);
	}
	

	@Given("^the Woolworths PetInsurance website$")
	public void the_Woolworths_PetInsurance_website() throws Throwable {
		homePage = new HomePage(driver);
		homePage.navigateTo();
	}
	
	@Then("^verify page title is blank$")
	public void verify_page_title_is_blank() throws Throwable {
		homePage = new HomePage(driver);
		Assert.assertEquals("Verify blank title", "", homePage.getTitle());
		
	}

	@Then("^verify heading has text \"([^\"]*)\"$")
	public void verify_heading_has_text (String expectedHeading) throws Throwable {
		homePage = new HomePage(driver);
		String actualHeading = homePage.getHeading();
		System.out.println("Actual: "+actualHeading);
		System.out.println("expectedHeading: "+expectedHeading);
		
		Assert.assertTrue("Verify Pet Insurance Page Heading", actualHeading.contains(expectedHeading));
	}
}
