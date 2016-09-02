package com.woolworthspetinsurance.pet;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FormDetail {

	protected WebElement element;

	public FormDetail(WebElement element) {
		this.element = element;
	}

	public String getHeading() {
		return element.findElement(By.tagName("h2")).getText().trim();
	}

	public void setSelectedSpecies(String selectedSpecies) {
		List<WebElement> species= element.findElements(By.cssSelector(".pet button"));
		for(WebElement button: species) {
			if(button.getText().equalsIgnoreCase(selectedSpecies))
			{
				button.click();
			}
		}
	}

	public void setPetName(String petName) {
		element.findElement(By.id("txtPetName")).sendKeys(petName);
	}

	public void setBreedName(String breedName) {
		element.findElement(By.id("txtBreed")).sendKeys(breedName);
	}

	public void setDateOfBirth(int day, int month, int year) {
		setDayOfBirth(day);
		setMonthOfBirth(month);
		setYearOfBirth(year);
	}

	public void setDayOfBirth(int day) {
		element.findElement(By.cssSelector("input.day")).sendKeys(String.format("%02d", day));
	}

	public void setMonthOfBirth(int month) {
		element.findElement(By.cssSelector("input.month")).sendKeys(String.format("%02d", month));
	}

	public void setYearOfBirth(int year) {
		element.findElement(By.cssSelector("input.year")).sendKeys(String.format("%02d", year));
	}

	public void setGender(String gender) {
		List<WebElement> genderGroups = element.findElements(By.cssSelector("div[data-model='pet.gender'] button"));
		for(WebElement element: genderGroups) {
			if(element.getText().equalsIgnoreCase(gender))
			{
				element.click();
			}
		}
	}
	
	public void setIsDesexed(String deSexed) {
		List<WebElement> deSexedOptions = element.findElements(By.cssSelector("div[data-model='pet.selectedIsDesexed'] button"));
		for(WebElement element: deSexedOptions) {
			if(element.getText().equalsIgnoreCase(deSexed))
			{
				element.click();
			}
		}
		
	}

}
