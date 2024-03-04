package com.registration.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValuationEstimate {

	@FindBy(xpath = "//body/div[@id='__next']/div[1]")
	private WebElement topContainer;

	private WebDriver driver;

	// Constructor
	public ValuationEstimate(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	 // Method to extract car details
    public String getCarDetails() {
        String carDetails = topContainer.getText().replace("Your car", "").trim();
        return carDetails;
    }
}