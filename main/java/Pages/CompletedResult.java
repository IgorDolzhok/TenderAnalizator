package Pages;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.ResultEntry;
/**
 * presents Page with QualificationOfVinner (" вал≥ф≥кац≥€ переможц€")
 * @author WORK-06
 *
 */
public class CompletedResult {
	
	WebDriver driver;	 

	@FindBy(className="container")
	WebElement container;
	
	@FindBy(xpath="/html/body/main/div[1]/div[1]/div/div/div[1]")
	WebElement itemOfTender;
	
	@FindBy(xpath="/html/body/main/div[1]/div[1]/div/div/div[2]/div/div/strong")
	WebElement expectedPrice;
	

	public CompletedResult(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	public void launch(String url) {
		driver.get("https://prozorro.gov.ua"+url);		
	}
	
	public String getId() {
		 return driver.getCurrentUrl();		
	}	
	/**
	 * @return last table in division "Container"
	 */
	public WebElement getVinnerTable() {
		List <WebElement> tables = driver.findElements(By.cssSelector("table[class*='striped']"));		
		return tables.get(tables.size()-1);		 
	}   
	 
	public String getVinnerCode(Handler handler) {
		 WebElement table = getVinnerTable();		 
		 String outerHtml = table.getAttribute("outerHTML");
		 String code = handler.getVinnerCodeFromOuterHTML(outerHtml);		
		 return code;
	}
	
	public String getVinnerName() {
		WebElement table = getVinnerTable();
		String name = table.findElement(By.cssSelector("tbody > tr > td:nth-child(1)")).getText();
		if(name.contains("#")) {
			name=name.substring(0, name.indexOf("#"));
		}
		return name;
	}
	
    public String getItemOfTender() {
    	String item = itemOfTender.getText();      	
    	return item;
    }
    
    public int getPrice(Handler handler) {
    	String value = getPriceAndCurrency();
    	return handler.getPrice(value);
    }
	
	public String getDate(String url, Handler handler) {
		return handler.getDateFromUrl(url);
	}
	
	public int getExpectedPrice(Handler handler) {
		String result = expectedPrice.getText();
		return  handler.getPrice(result);
	}
	
	public String getCurrency(Handler handler) {
		 String value = getPriceAndCurrency();
		 return handler.getCurrency(value);
	}
	
	public String getPriceAndCurrency() {
		WebElement table = getVinnerTable();
    	List<WebElement> columns = table.findElements(By.tagName("th"));
    	int index=0;
    	for(int x= columns.size(); x>0; x--) {
    		if(columns.get(x-1).getText().contains("ропозиц≥€")==true) {
    			index=x;
    		}
    	}
    	String value = table.findElement(By.cssSelector("tbody > tr > td:nth-child("+index+")")).getText();		
		return value;
	}
    

}
