package com.woolworthspetinsurance.pet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class QuotePackage {
	protected WebElement element;

	public QuotePackage(WebElement element) {
		this.element = element;
	}
	
	public String getTitle() {
		return element.findElement(By.className("title")).getText().trim();
	}

	public String getSubTitle() {
		return element.findElement(By.className("sub-title")).getText().replaceAll("\n", " ").trim();
	}
	
	public String getPrice() {
		return element.findElement(By.cssSelector(".price-box .price")).getText().trim();
	}
	
	public String getPeriod() {
		return element.findElement(By.cssSelector(".price-box .value")).getText().trim();
	}

}
