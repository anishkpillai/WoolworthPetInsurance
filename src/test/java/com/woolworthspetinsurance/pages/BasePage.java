package com.woolworthspetinsurance.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage navigateTo() {
		driver.navigate().to("https://www.woolworthspetinsurance.com.au/");
		return new HomePage(driver);
	}
}
