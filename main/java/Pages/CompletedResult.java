package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.ResultEntry;

public class CompletedResult {
	
	WebDriver driver;
	String tableOffersXpath = "/html/body/main/div[1]/div[2]/div/div/div/div[11]/div/div/table/tbody";
	String offersTRXpath1 = "/html/body/main/div[1]/div[2]/div/div/div/div[11]/div/div/table/tbody/tr[";
	
	@FindBy(xpath= "/html/body/main/div[1]/div[2]/div/div/div/div[11]/div/div/table/tbody")
	private WebElement tableOffers;
	
	@FindBy(xpath="//table[@class='table table-striped margin-bottom small-text contract']/tbody/tr/td[1]")
	//@FindBy(xpath="/html/body/main/div[1]/div[2]/div/div/div/div[11]/div/div[1]/table/tbody/tr/td[1]")
	//@FindBy(xpath = "/html/body/main/div[1]/div[2]/div/div/div/div[12]/div/div/table/tbody/tr[2]/td[1]")
	private WebElement acceptedCompany;
	
	@FindBy(xpath ="")
	private WebElement acceptedSingleCompany;
	 
	@FindBy(xpath="//table[@class='table table-striped margin-bottom small-text long']/tbody/tr/td[1]")
	private WebElement acceptedSingleCompany1;
	
	@FindBy(xpath="/html/body/main/div[1]/div[1]/div/div/div[1]")
	WebElement good;
	
	//@FindBy(xpath="/html/body/main/div[1]/div[2]/div/div/div/div[11]/div/div[1]/table/tbody/tr/td[2]")
	@FindBy(css="div.tender--offers.margin-bottom-xl > table > tbody > tr > td:nth-child(3)")
	WebElement price;
	
	@FindBy(xpath="/html/body/main/div[1]/div[1]/div/div/div[2]/div/div/strong")
	WebElement expectedPrice;
	
	@FindBy(xpath="/html/body/main/div[1]/div[2]/div/div/div/div[11]/div/div[1]/p")
	//@FindBy(css="div.tender--offers.margin-bottom-xl > table > tbody > tr > td:nth-child(1)")
	WebElement dataAccept;	
	
	public CompletedResult(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterPage(String url, WebDriverWait wait) {
		driver.get(url);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/div[1]/div[1]/div/div/div[1]")));
		 
	}
	
    public void getOffers() {
		List<WebElement> offers = tableOffers.findElements(By.tagName("tr"));
		for(int i=0; i<offers.size(); i++) {
			System.out.println("Participant #"+(i+1)); 
			System.out.print(driver.findElement(By.xpath(offersTRXpath1+(i+1)+"]/td[1]")).getText());
			System.out.print(driver.findElement(By.xpath(offersTRXpath1+(i+1)+"]/td[3]")).getText()); 
		}
	}
    
    public String getAcceptedCompanyName() {  
        String result;
    	if(driver.findElements(By.xpath("//table[@class='table table-striped margin-bottom small-text contract']/tbody/tr/td[1]"))
           .isEmpty()!=true) {
    	              result = acceptedCompany.getText();
    	}else if(driver.findElements(By.xpath("//table[@class='table table-striped margin-bottom small-text']/tbody/tr/td[1]"))
    			.isEmpty()!=true){    		
    		    result = acceptedSingleCompany.getText();
    	}else {
    		     result = acceptedSingleCompany1.getText();
    	}
    	if(result.indexOf("#")>=0) {    		 
    		result=result.substring(0, result.indexOf("#"));
    	}
    	return result;
    }
    
    public String getAcceptedCompanyCode() {
    	String result;
    	if(driver.findElements(By.xpath("//table[@class='table table-striped margin-bottom small-text contract']/tbody/tr/td[1]"))
    	           .isEmpty()!=true) {
    	result = acceptedCompany.getText();
    	}else  if(driver.findElements(By.xpath("//table[@class='table table-striped margin-bottom small-text']/tbody/tr/td[1]"))
    			.isEmpty()!=true){
	          result = acceptedSingleCompany.getText();
        }else {
	          result = acceptedSingleCompany1.getText();
        }
    	if(result.indexOf("#")<0) {
    		result="111";
    	}else {
    		result=result.substring(result.indexOf("#")+1);
    	}
    	 
    	 
    	return result;
    }
    
    public String getItemOfTender() {
    	return good.getText();
    }
    
    public int getAmount() {
    	System.out.println(price.getText());
    	return Integer.parseInt(price.getText().substring(0, price.getText().indexOf(",")).replaceAll("\\s",""));
    }
    
    public String getCurrency() {
    	String currency;
    	if(price.getText().contains("UAH")) {
    		currency = "UAH";
    	}else {
    		currency= "XXX";
    	}
    	return  currency;
    }
    
    public String getTenderDate() {
    	return getId().substring(3, 13);    	 
    }
    
    public String getId() {    	
    	return driver.getCurrentUrl().substring(31);
    }
    
    public int getExpectedValue() {
    	return Integer.parseInt(expectedPrice.getText().substring(0, expectedPrice.getText().indexOf(",")).replaceAll("\\s",""));        	
    }
    
    public ResultEntry createEntry() {
    	return new ResultEntry(getId(), getAcceptedCompanyName(), getAcceptedCompanyCode(), getItemOfTender(), getAmount(),
    			               getTenderDate(), getExpectedValue(), getCurrency());
    }
    
    
    
    
	
    

}
