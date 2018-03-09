package com.woolworthspetinsurance.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Panel {

	protected WebElement element;

	public Panel(WebElement element) {
		this.element = element;
	}
	
	public String getCaption() {
		return element.findElement(By.className("panel-title")).findElement(By.tagName("span")).getText();
	}
	
	public String getTitle() {
		return element.findElement(By.className("panel-title")).getText();
	}
	
	public boolean isActive() {
		return element.getAttribute("class").contains("active");

	}
	
	public void clickHeadingLink() {
		element.findElement(By.cssSelector(".panel-heading a")).click();
	}
	
	public List<WebElement> getPackages() {
		return element.findElements(By.cssSelector(".packages.ng-scope"));
	}

}
