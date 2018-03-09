package com.woolworthspetinsurance.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.woolworthspetinsurance.modal.Information;
import com.woolworthspetinsurance.pet.FormDetail;
import com.woolworthspetinsurance.pet.QuotePackage;
import com.woolworthspetinsurance.ui.Panel;



public class QuotationFormPage extends BasePage {
	
	public QuotationFormPage (WebDriver driver) {
		super(driver);
	}

	public boolean verifyPetHeading (String expectedPetHeading,int petOrder) {
		return (expectedPetHeading.equalsIgnoreCase(getPet(petOrder).getHeading()));
	}

	public void selectPetSpecies(String selectedSpecies,int petOrder) {
		getPet(petOrder).setSelectedSpecies(selectedSpecies);
	}
	
	public void setPetName(String petName,int petOrder) {
		getPet(petOrder).setPetName(petName);
	}

	
	public void clickAddMorePetButton() {
		driver.findElement(By.linkText("Add Pet")).click();
	}
	
	protected FormDetail getPet(int petOrder) {
		List<WebElement> petHolders = driver.findElements(By.cssSelector(".box-holder.ng-scope"));
		WebElement petHolder = petHolders.get(petOrder - 1);
		return new FormDetail(petHolder);
	}

	public void setBreedName(String breedName,int petOrder) {
		getPet(petOrder).setBreedName(breedName);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("dropdown-menu")));
		driver.findElement(By.cssSelector(".dropdown-menu li:first-child")).click();
	}

	public void setDateOfBirth(int day, int month, int year,int petOrder) {
		getPet(petOrder).setDateOfBirth(day,month,year);
		
	}

	public void setGender(String gender,int petOrder) {
		getPet(petOrder).setGender(gender);
		
	}

	public void setDeSexed(String deSexed,int petOrder) {
		getPet(petOrder).setIsDesexed(deSexed);
		
	}



	public void setYourDateOfBirth(Integer day, Integer month, Integer year) {
		driver.findElement(By.id("txtDOBDay")).sendKeys(String.format("%02d", day));
		driver.findElement(By.id("txtDOBMonth")).sendKeys(String.format("%02d", month));
		driver.findElement(By.id("txtDOBYear")).sendKeys(String.format("%02d", year));
	}

	public void setYourPostCode(int postCode) {
		driver.findElement(By.id("txtPostcode")).sendKeys(Integer.toString(postCode));
	}

	public void clickGetQuoteButton() {
		driver.findElement(By.linkText("Get Quote")).click();
		
	}

	public void setPromocode(String promoCode) {
		driver.findElement(By.id("txtPromotionCode")).sendKeys(promoCode);
	}

	public String getPromocode() {
		return driver.findElement(By.id("txtPromotionCode")).getAttribute("value");
	}

	public void setEmailAddress(String emailAddress) {
		driver.findElement(By.id("txtEmail")).sendKeys(emailAddress);
	}

	public boolean verifyModalPopupDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("modal-content")));
		Information info = new Information(driver.findElement(By.className("modal-content")));
		return info.isPopupDisplayed();
	}

	public void clickPopupContinueButton() {
		Information info = new Information(driver.findElement(By.className("modal-content")));
		info.clickContinueButton();
	}
	
	public String getModalPopupTitle() {
		Information info = new Information(driver.findElement(By.className("modal-content")));
		return info.getTitle();
	}

	public List<QuotePackage> getPetQuoteDetails(String petName, String breed) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("mandatory")));
		List<QuotePackage> quotePackages = new ArrayList<QuotePackage>();
		List <WebElement> panels = driver.findElements(By.cssSelector(".panel"));
		System.out.println("Processing for petname: "+petName);
		for(WebElement element: panels) {
			boolean foundPetName = false;
			Panel panel = new Panel(element);
			System.out.println("Panel is Active: "+panel.isActive());
			System.out.println("Panel is getCaption: "+panel.getCaption());
			if (panel.isActive() && panel.getCaption().contains(petName)) {
					foundPetName = true;
			}
			System.out.println("Panel is getTitle: "+panel.getTitle());
			if (panel.getTitle().equals(String.format("%s (%s)", petName,breed)))
			{
				panel.clickHeadingLink();
				foundPetName = true;
			}
			System.out.println("Found: "+foundPetName);
			if (foundPetName) {
				List<WebElement> packages = panel.getPackages();
				for (WebElement quotePackage:packages) {
					quotePackages.add(new QuotePackage(quotePackage));
				}
				return quotePackages;
			}
		}
		throw new Exception("Quote Details not found for pet: "+petName);
	}

}
