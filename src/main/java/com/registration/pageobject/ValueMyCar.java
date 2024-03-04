package com.registration.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ValueMyCar {

	/** The driver. */
	WebDriver driver;

	@FindBy(xpath = "//span[text()='Accept All']")
    private WebElement acceptAllSpan;
	
	@FindBy(id = "vrm")
	private WebElement searchBox;

	@FindBy(xpath = "//span[text()='Get started']")
	private WebElement submitButton;

	@FindBy(xpath = "//div[@role='alert']")
    private WebElement alertElement;
	
	public ValueMyCar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	// Method to accept all cookies
    public void acceptAllCookies() {
        acceptAllSpan.click();
    }
    
	// Method to enter registration number and submit
    public void searchWithRegistrationNumber(String regNumber) {
        searchBox.clear();
        searchBox.sendKeys(regNumber);
        submitButton.click();
    }
    
    // Method to wait for a specified amount of time (in milliseconds)
    public void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    // Method to check if the alert element exists
    public boolean isAlertElementPresent() {
        try {
            return alertElement.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
