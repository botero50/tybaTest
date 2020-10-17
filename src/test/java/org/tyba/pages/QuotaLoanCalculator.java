package org.tyba.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.tyba.common.BasePage;
import org.tyba.common.dataCalculationMethods;

public class QuotaLoanCalculator extends BasePage {

	public QuotaLoanCalculator()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".vercuotas")
	public WebElement menuQuota;
	
	@FindBy(name="loadAmount")
	public WebElement loadAmount;
	
	@FindBy(css =".form-group.error .validacion.ng-binding")
	public WebElement LoanAmountError;
	
	@FindBy(css = "select[ng-model='termInYearsByQuota']")
	public WebElement termInYears;
	
	@FindBy(css =".btn.btn-upload.cuotas")
	public WebElement calculateCredit;
	
	@FindBy(css =".resultados.cuotas > .datos_superior > dl:nth-child(1) > dd")
	public WebElement toAdquireCreditOf;
	
	@FindBy(css =".resultados.cuotas > .datos_superior > dl:nth-child(2) > dd")
	public WebElement youShouldHaveMontlyIncome;
	
	@FindBy(css =".resultados.cuotas > .datos_superior > dl:nth-child(3) > dd")
	public WebElement initialPayment;
	
	@FindBy(css =".resultados.cuotas > .datos_superior > dl:nth-child(4) > dd")
	public WebElement maxBuyingHousePrice;
	
	@FindBy(css =".valorCuotas.tab-pane.ng-scope.active tr.ng-scope.inactivo > td:nth-child(2)")
	public WebElement AvertageRate_MontlyQuota;
	
	@FindBy(css =".valorCuotas.tab-pane.ng-scope.active tr.ng-scope.inactivo > td:nth-child(3)")
	public WebElement AvertageRate_TotalLoan;
	
	@FindBy(css =".valorCuotas.tab-pane.ng-scope.active tr.ng-scope.inactivo  > td:nth-child(4)")
	public WebElement AvertageRate_EAResult;
	
	@FindBy(css =".valorCuotas.tab-pane.ng-scope.active  .lqn-bar-btn.general-lightbox-trigger")
	public WebElement processCredit;
	
	@FindBy(css =".lightbox-lqn-container.form-lqn[style*='display: block']")
	public List<WebElement> requestCreditPopup;
	
	public void calculateLoan(int smonthlyIncome, String stermInYears)
	{
		loadAmount.sendKeys(String.valueOf(smonthlyIncome));
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