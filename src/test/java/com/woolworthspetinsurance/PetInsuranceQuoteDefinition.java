package com.woolworthspetinsurance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.woolworthspetinsurance.datatable.Cover;
import com.woolworthspetinsurance.datatable.Pet;
import com.woolworthspetinsurance.pages.HomePage;
import com.woolworthspetinsurance.pages.QuotationFormPage;
import com.woolworthspetinsurance.pet.QuotePackage;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PetInsuranceQuoteDefinition {
	private WebDriver driver;
	HomePage homePage;
	QuotationFormPage quotePage;
	private Map<String, Pet> petsMap;

	@Then("^verify list of steps in quote page$")
	public void verify_list_of_steps_in_quote_page(List<String> steps) throws Throwable {
		homePage = new HomePage(driver);
		boolean stepsPresent = homePage.verifyQuotationSteps(steps);
		Assert.assertTrue("Verify different steps in home page", stepsPresent);
	}

	@Then("^verify (\\d+) pet heading as \"([^\"]*)\"$")
	public void verify_pet_heading_as(int order, String expectedPetHeading) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		boolean headingPresent = quotePage.verifyPetHeading(expectedPetHeading,order);
		Assert.assertTrue("Verify Pet Heading: "+expectedPetHeading, headingPresent);
	}
		
	@When("^add more pet button is clicked$")
	public void add_more_pet_button_is_clicked() throws Throwable {
		quotePage = new QuotationFormPage(driver);
		quotePage.clickAddMorePetButton();
	}
	
	@When("^get quote button is clicked$")
	public void get_quote_button_is_clicked() throws Throwable {
		quotePage = new QuotationFormPage(driver);
		quotePage.clickGetQuoteButton();
	}

	@When("^the pet quotation form is filled with below details$")
	public void the_pet_quotation_form_is_filled_with_below_details(List<Pet> pets) throws Throwable {
		petsMap = new HashMap<String, Pet>();
		int petCount = 1;
		quotePage = new QuotationFormPage(driver);
		for (Pet pet : pets) {
			String key = String.format("%s(%s)", pet.getName(),pet.getBreed());
			if (petsMap.containsKey(key)) {
				key = String.format("%s(%s)_%d", pet.getName(),pet.getBreed(),petCount);
			}
			petsMap.put(key, pet);

			quotePage.selectPetSpecies(pet.getSpecies(),petCount);
			quotePage.setPetName(pet.getName(),petCount);
			quotePage.setBreedName(pet.getBreed(),petCount);
			quotePage.setDateOfBirth(pet.getDay(),pet.getMonth(),pet.getYear(),petCount);
			quotePage.setGender(pet.getGender(),petCount);
			quotePage.setDeSexed(pet.getDeSexed(),petCount);
			if (petCount != pets.size()) {
				quotePage.clickAddMorePetButton();
			}
			petCount++;
		}
	}

	@When("^enter your date of birth as (\\d+)/(\\d+)/(\\d+)$")
	public void enter_your_date_of_birth_as(Integer day, Integer month, Integer year) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		quotePage.setYourDateOfBirth(day,month,year);
	}

	@When("^enter your postcode as (\\d+)$")
	public void enter_your_postcode_as(int postCode) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		quotePage.setYourPostCode(postCode);
	}

	@When("^promocode is entered as \"([^\"]*)\" if not prefilled$")
	public void promocode_is_entered_as_if_not_prefilled(String promoCode) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		if (quotePage.getPromocode().equals("")) {
			quotePage.setPromocode(promoCode);
		}
	}

	@When("^email is filled as \"([^\"]*)\"$")
	public void email_is_filled_as(String emailAddress) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		quotePage.setEmailAddress(emailAddress);
	}

	@Then("^promotion modal popup is displayed$")
	public void promotion_modal_popup_is_displayed() throws Throwable {
		quotePage = new QuotationFormPage(driver);
		boolean modalDisplayed = quotePage.verifyModalPopupDisplayed();
		Assert.assertTrue("Verify promocode message modal",modalDisplayed);
	}

	@Then("^verify the popup title as \"([^\"]*)\"$")
	public void verify_the_popup_title_as(String expectedTitle) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		Assert.assertEquals("Verify promocode message title",expectedTitle, quotePage.getModalPopupTitle());
	}

	@When("^the continue button is clicked in popup$")
	public void the_continue_button_is_clicked() throws Throwable {
		quotePage = new QuotationFormPage(driver);
		quotePage.clickPopupContinueButton();
	}

	@Then("^verify cover options for pet named \"([^\"]*)\" and breed \"([^\"]*)\" with below details$")
	public void verify_cover_options_for_pet_named_and_breed_with_below_details(String petName, String breed, List<Cover> verifyDetails) throws Throwable {
		quotePage = new QuotationFormPage(driver);
		List<QuotePackage> quotePackages = quotePage.getPetQuoteDetails(petName,breed);
		for (Cover verifyCover : verifyDetails) {
			String verifyCoverTitle = verifyCover.getTitle();
			for (QuotePackage quote: quotePackages) {
				if(!quote.getTitle().equalsIgnoreCase(verifyCoverTitle)) {
					continue;
				}
				Assert.assertEquals("Verify Title",verifyCoverTitle,quote.getTitle());
				Assert.assertEquals("Verify Sub Title",verifyCover.getSubTitle(),quote.getSubTitle());
				Assert.assertEquals("Verify Price",verifyCover.getPrice(),quote.getPrice());
				Assert.assertEquals("Verify Period",verifyCover.getPeriod(),quote.getPeriod());
			}

		}
	}
}
