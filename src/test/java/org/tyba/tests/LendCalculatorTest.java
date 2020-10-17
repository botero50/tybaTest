package org.tyba.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.tyba.common.BasePage;
import org.tyba.common.dataCalculationMethods;
import org.tyba.common.publicData;
import org.tyba.pages.LendCalculator;

public class LendCalculatorTest extends BasePage{

	final static Logger log = Logger.getLogger(LendCalculatorTest.class);
	final static int montlyIncome = 1000000;
	final static int termInYears = 10;
	final static String AverageEA = "10.25%";

	@AfterMethod
	public void testSetUp()
	{
		getUrl(publicData.ENVIRONMENT_URL);
	}
	
	@Test(testName = "calculate loan with default data", description =  "Check if the user can calculate a loan", priority = 1)
	public void calculateLoanWithDefaultData()  {
		LendCalculator loanCalculator = new LendCalculator();
		loanCalculator.calculateLoan(montlyIncome, termInYears + " Años");
		List<Integer> loanValues =  loanCalculator.calculateLoanValuesBasedOn10Years(montlyIncome);
		assertEquals(loanCalculator.montlyIncomeResult.getText(), dataCalculationMethods.formatPriceValue(loanValues.get(0)), "montlyIncomeResult is not presenting the expected value");
		assertEquals(loanCalculator.bankMaxLoanResult.getText(), dataCalculationMethods.formatPriceValue(loanValues.get(1)), "bankMaxLoanResult is not presenting the expected value");
		assertEquals(loanCalculator.minimumInitialResult.getText(), dataCalculationMethods.formatPriceValue(loanValues.get(2)), "minimumInitialResult is not presenting the expected value");
		assertEquals(loanCalculator.maxBuyingPriceWebElementResult.getText(), dataCalculationMethods.formatPriceValue(loanValues.get(3)), "maxBuyingPriceWebElementResult is not presenting the expected value");
		
		assertEquals(loanCalculator.AvertageRate_CreditValueResult.getText(), dataCalculationMethods.formatPriceValue(loanValues.get(1)), "AvertageRate_CreditValueResult is not presenting the expected value");
		assertEquals(loanCalculator.AvertageRate_MontlyPaymentResult.getText(), dataCalculationMethods.formatPriceValue(loanValues.get(4)), "AvertageRate_MontlyPaymentResult is not presenting the expected value");
		assertEquals(loanCalculator.AvertageRate_EAResult.getText(), AverageEA, "AvertageRate_EAResult is not presenting the expected value");
	}
	
	@Test(testName= "check if montly income is required", description =  "Check if the calculate button is disable when the user only set text in term in years field", priority = 2)
	public void checkMontlyIncomeRequired()  {
		LendCalculator loanCalculator = new LendCalculator();
		loanCalculator.calculateLoan(0, termInYears + " Años");
		assertTrue(loanCalculator.calculateCredit.getAttribute("disabled").equals("true"), "Montly income is not required");
		assertEquals(loanCalculator.MontlyIncomeError.getText(), "Los ingresos deben ser mayores o iguales a $737,717.", "MontlyIncomeError is not presenting the expected value");
	}
	
	@Test(testName= "check if The user can process credit", description =  "Check if the user can click on process credit and the pop up is located", priority = 3)
	public void checkUserCanProcessCredit()  {
		LendCalculator loanCalculator = new LendCalculator();
		loanCalculator.calculateLoan(montlyIncome, termInYears + " Años");
		loanCalculator.processCredit.click();
		assertTrue(loanCalculator.requestCreditPopup.size()>0, "popup is not present");
	}
}
