package com.woolworthspetinsurance.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getHeading() {
		return driver.findElement(By.tagName("h1")).getText().replaceAll("\n", " ");
	}

	public boolean verifyQuotationSteps(List<String> expectedSteps) throws Exception {
		List<WebElement> menuSteps = driver.findElements(By.cssSelector(".steps li"));
		if (menuSteps.size() == expectedSteps.size()) {
			for (WebElement heading: menuSteps) {
				String actualStepText = heading.getText().replaceAll("\n", " ");
				if (!expectedSteps.contains(actualStepText)) {
					return false;
				}
			}
			return true;
		}
		throw new Exception("Quotation steps are different");
	}

}
