package org.tyba.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.tyba.common.BasePage;
import org.tyba.common.dataCalculationMethods;
import org.tyba.common.publicData;
import org.tyba.pages.QuotaLoanCalculator;

public class QuotaLoanCalculationTests extends BasePage{

	final static Logger log = Logger.getLogger(QuotaLoanCalculationTests.class);
	final static int montlyIncome = 100000000;
	final static int termInYears = 10;
	final static String AverageEA = "10.25%";

	@AfterMethod
	public void testSetUp()
	{
		getUrl(publicData.ENVIRONMENT_URL);
	}
	
	@Test(testName = "calculate quota with default data", description =  "Check if the user can calculate the payment quota", priority = 1)
	public void calculateQuotaWithDefaultData()  {
		QuotaLoanCalculator feesCalculator = new QuotaLoanCalculator();
		feesCalculator.menuQuota.click();
		
		feesCalculator.calculateLoan(montlyIncome, termInYears + " Años");

		assertEquals(feesCalculator.toAdquireCreditOf.getText(), dataCalculationMethods.formatPriceValue(montlyIncome), "toAdquireCreditOf is not presenting the expected value");
		assertEquals(feesCalculator.youShouldHaveMontlyIncome.getText(), "$ 4,367,789", "youShouldHaveMontlyIncome is not presenting the expected value");
		assertEquals(feesCalculator.initialPayment.getText(), "$ 42,857,143", "initialPayment is not presenting the expected value");
		assertEquals(feesCalculator.maxBuyingHousePrice.getText(), "$ 142,857,143", "maxBuyingHousePrice is not presenting the expected value");
		
		assertEquals(feesCalculator.AvertageRate_MontlyQuota.getText(), "$ 1,310,337", "AvertageRate_MontlyQuota is not presenting the expected value");
		assertEquals(feesCalculator.AvertageRate_TotalLoan.getText(), "$ 98,265,424", "AvertageRate_TotalLoan is not presenting the expected value");
		assertEquals(feesCalculator.AvertageRate_EAResult.getText(), AverageEA, "AvertageRate_EAResult is not presenting the expected value");
	}
	
	@Test(testName= "check if credit value is required", description =  "Check if the calculate button is disable when the user only set text in credit value field", priority = 2)
	public void checCreditValueRequired()  {
		QuotaLoanCalculator feesCalculator = new QuotaLoanCalculator();
		feesCalculator.menuQuota.click();
		feesCalculator.calculateLoan(0, termInYears + " Años");
		assertTrue(feesCalculator.calculateCredit.getAttribute("disabled").equals("true"), "Credit value is not required");
		assertEquals(feesCalculator.LoanAmountError.getText(), "El valor del crédito debe ser mayor o igual a $15,000,000.", "LoanAmountError is not presenting the expected value");
	}
	
	@Test(testName= "check if The user can process credit", description =  "Check if the user can click on process credit and the pop up is located", priority = 3)
	public void checkUserCanProcessCredit()  {
		QuotaLoanCalculator feesCalculator = new QuotaLoanCalculator();
		feesCalculator.menuQuota.click();
		feesCalculator.calculateLoan(montlyIncome, termInYears + " Años");
		feesCalculator.processCredit.click();
		assertTrue(feesCalculator.requestCreditPopup.size()>0, "popup is not present");
	}
}
