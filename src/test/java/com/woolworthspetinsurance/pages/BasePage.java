package com.woolworthspetinsurance.pages;

import com.woolworthspetinsurance.GlobalHooks;
import org.openqa.selenium.WebDriver;

public class BasePage {
	public WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = GlobalHooks.globalDriver;
	}

	public HomePage navigateTo() {
		driver.get("https://www.woolworthspetinsurance.com.au/");
		return new HomePage(driver);
	}
}
