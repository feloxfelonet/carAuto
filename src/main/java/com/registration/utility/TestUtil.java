package com.registration.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.registration.constant.AppConstants;

public class TestUtil {

	// Method to extract registration numbers from a text file
	public static List<String> extractRegistrationNumbers(String filePath) {
		// Read the contents of the text file
		List<String> lines = readTextFile(filePath);

		// Define the patterns for vehicle registration numbers
		Pattern pattern1 = Pattern.compile(AppConstants.INPUTCARNUMBERPATTERN1);
		Pattern pattern2 = Pattern.compile(AppConstants.INPUTCARNUMBERPATTERN2);

		// List to store extracted registration numbers
		List<String> registrationNumbers = new ArrayList<>();

		// Iterate through each line of the text file
		for (String line : lines) {
			// Find matches using the first pattern
			Matcher matcher1 = pattern1.matcher(line);
			while (matcher1.find()) {
				// Add matched registration number to the list
				registrationNumbers.add(matcher1.group());
			}

			// Find matches using the second pattern
			Matcher matcher2 = pattern2.matcher(line);
			while (matcher2.find()) {
				// Add matched registration number to the list
				registrationNumbers.add(matcher2.group().replaceAll("\\s+", ""));
			}
		}

		return registrationNumbers;
	}

	// Method to read the contents of a text file
	private static List<String> readTextFile(String filePath) {
		List<String> lines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static String formatCarString(String carString) {

		String[] lines = carString.split("\n");
		String[] details = lines[1].split("\\|");

		String registration = details[1].trim().replace(" ", "");
		String make = lines[0].split(" ")[0];
		String modelAndSpecs = lines[0].replace(make, "").trim() + " " + details[0].trim();
		String fuelAndTransmission = lines[2].split("\\|")[1].trim() + " " + lines[2].split("\\|")[2].trim() + " "
				+ lines[2].split("\\|")[3].trim() + " " + lines[2].split("\\|")[4].trim();

		return registration + "," + make + "," + modelAndSpecs + " " + fuelAndTransmission;
	}

	public static boolean checkCarRegistrationDetails(String filePath, String convertedString,
			String carregistrationnumber) {
		boolean isTestCasePassed = false;
		String matchline = null;
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(carregistrationnumber.replace(" ", ""))) {
					matchline = line;
					if (line.contains(convertedString)) {
						// If the line contains the car data to compare, pass the test case
						isTestCasePassed = true;
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (isTestCasePassed) {
			System.out.println("Test Result: Passed : " + carregistrationnumber);
		} else {
			System.out.println("Test Result: Failed : " + carregistrationnumber
					+ " Car Registration details is mismatches\nActual  :" + convertedString + "\nExpected:"
					+ matchline);
		}

		return isTestCasePassed;
	}
}
