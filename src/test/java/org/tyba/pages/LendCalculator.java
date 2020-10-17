package org.tyba.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.tyba.common.BasePage;
import org.tyba.common.dataCalculationMethods;

public class LendCalculator extends BasePage {

	public LendCalculator()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".verprestamo")
	public WebElement menuLend;
	
	@FindBy(name="monthlyIncome")
	public WebElement monthlyIncome;
	
	@FindBy(css ="p.validacion.ingresos_mensuales.ng-binding")
	public WebElement MontlyIncomeError;
	
	@FindBy(css = "select[ng-model='termInYears']")
	public WebElement termInYears;
	
	@FindBy(css =".btn.btn-upload.credito")
	public WebElement calculateCredit;
	
	@FindBy(css =".resultados.credito > .datos_superior > dl:nth-child(1) > dd")
	public WebElement montlyIncomeResult;
	
	@FindBy(css =".resultados.credito > .datos_superior > dl:nth-child(2) > dd")
	public WebElement bankMaxLoanResult;
	
	@FindBy(css =".resultados.credito > .datos_superior > dl:nth-child(3) > dd")
	public WebElement minimumInitialResult;
	
	@FindBy(css =".resultados.credito > .datos_superior > dl:nth-child(4) > dd")
	public WebElement maxBuyingPriceWebElementResult;
	
	@FindBy(css ="tr.ng-scope.inactivo > td:nth-child(2)")
	public WebElement AvertageRate_CreditValueResult;
	
	@FindBy(css ="tr.ng-scope.inactivo > td:nth-child(3)")
	public WebElement AvertageRate_MontlyPaymentResult;
	
	@FindBy(css ="tr.ng-scope.inactivo > td:nth-child(4)")
	public WebElement AvertageRate_EAResult;
	
	@FindBy(css =".prestamo.tab-pane.ng-scope.active .lqn-bar-btn.general-lightbox-trigger")
	public WebElement processCredit;
	
	@FindBy(css =".lightbox-lqn-container.form-lqn[style*='display: block']")
	public List<WebElement> requestCreditPopup;
	
	public void calculateLoan(int smonthlyIncome, String stermInYears)
	{
		monthlyIncome.sendKeys(String.valueOf(smonthlyIncome));
		new Select(termInYears).selectByVisibleText(stermInYears);
		calculateCredit.click();
	}
	public List<Integer> calculateLoanValuesBasedOn10Years(double iMontlyIncome)
	{	
		double bankPointValue = 22.8948781;
		double initialFeePointValue = 9.8120906;
		double maxHousePointValue = 32.7069687;
		double MontlyPaymentPointValue = 0.3;
		
		List<Integer> LoanValues = new ArrayList<Integer>();
		
		LoanValues.add((int)iMontlyIncome);
		LoanValues.add((int)dataCalculationMethods.round( iMontlyIncome * bankPointValue, 0));
		LoanValues.add((int)dataCalculationMethods.round( iMontlyIncome * initialFeePointValue, 0));
		LoanValues.add((int)dataCalculationMethods.round( iMontlyIncome * maxHousePointValue, 0));
		LoanValues.add((int)dataCalculationMethods.round( iMontlyIncome * MontlyPaymentPointValue, 0));
		return LoanValues;
	}
}