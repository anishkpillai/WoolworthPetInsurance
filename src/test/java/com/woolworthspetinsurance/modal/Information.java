package com.woolworthspetinsurance.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Information {
	
	protected WebElement element;

	public Information (WebElement element) {
		this.element = element;
	}
	
	public boolean isPopupDisplayed() {
		// Issue related to popup, so checking the size of element is > 0
		if (element.findElements(By.linkText("Continue")).size() > 0) {
			return true;
		}
		return false;
	}
	public void clickContinueButton(){
		element.findElement(By.linkText("Continue")).click();
	}
	
	public String getTitle() {
		return element.findElement(By.className("modal-title")).getText();
	}
	
	public String getContent() {
		return element.findElement(By.tagName("p")).getText();
	}
}
