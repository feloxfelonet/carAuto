package com.registration.testcase;

import java.util.ArrayList;
import java.util.List;

import com.registration.pageobject.ValuationEstimate;
import com.registration.pageobject.ValueMyCar;
import com.registration.testbase.TestBase;
import com.registration.utility.*;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class Registration extends TestBase {

	@Test
	public void CarRegistrationCheck() {

		List<String> registrationNumbers = new ArrayList<String>();		
		registrationNumbers = TestUtil.extractRegistrationNumbers(inputfilepath);
		
		 //Output the extracted registration numbers
		 //System.out.println("Extracted Vehicle Registration Numbers:");
		 //for (String regNumber : registrationNumbers) {
		 //System.out.println(regNumber);
		 //}

		// Create an object of the ValueMyCar class
		ValueMyCar valueMyCarPage = new ValueMyCar(driver);
		// Create an object of the ValuationEstimate class
		ValuationEstimate valuationestimate = new ValuationEstimate(driver);

		// Accept all cookies
		valueMyCarPage.acceptAllCookies();

		Map<String, Boolean> carresult = new HashMap<>();
		
		// Iterate through each extracted registration number and perform search
		for (String regNumber : registrationNumbers) {

			navigateToWebsite(appConfigProp.getProperty("url"));

			// Enter registration number and submit
			valueMyCarPage.searchWithRegistrationNumber(regNumber);

			// Wait for 5 seconds (for demonstration purposes)
			valueMyCarPage.waitFor(5000);			
			
			if (!valueMyCarPage.isAlertElementPresent()) {
				
				String cardetails = valuationestimate.getCarDetails();

				// System.out.println("Vehicle Details: " + cardetails);

				String convertedString = TestUtil.formatCarString(cardetails);

				// System.out.println("Converted String: " + convertedString);

				boolean isTestCasePassed = TestUtil.checkCarRegistrationDetails(outputfilepath, convertedString, regNumber);
				// boolean isTestCasePassed =
				// TestUtility.checkCarRegistrationDetails("d:/car_output_v1.txt",
				// convertedString);
				if (!isTestCasePassed) {
					carresult.put(regNumber + " : Car Registration is mismatches" , false);
				}
			}
			else
			{
				System.out.println("Test Result: Failed : " + regNumber + " Car Registration is not found");
				carresult.put(regNumber + " : Car Registration is not found", false);
			}

			// Navigate back to the homepage for the next search
			driver.navigate().back();
		}
		
		if (carresult.size() > 0) {
            // If the map size is greater than zero, fail the test case
            throw new AssertionError("Test case failed.");
        }
	}
}
