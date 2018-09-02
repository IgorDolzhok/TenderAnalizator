package Pages;

import java.util.ArrayList;
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
import Common.ParticipantEntry;
import Common.ResultEntry;
/**
 * presents Page with QualificationOfVinner ("Кваліфікація переможця")
 * to be optimized
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
	
	public String getId(String url) {		 
		return url;		
	}	
	
	/**
	 * little bit risky method to define table containing data about winner. But it works still.
	 * @return last table in division "Container" (conta
	 */
	public WebElement getVinnerTable() {
		List <WebElement> tables = driver.findElements(By.cssSelector("table[class*='striped']"));
		if(!tables.get(tables.size()-1).findElement(By.cssSelector("thead > tr > th:nth-child(1)")).getText().contains("онтракт")) {
			return tables.get(tables.size()-1);
		}
		return tables.get(tables.size()-2);
	}  
	
	/**
	 * little bit risky method to define table containing data about participants and their offers. But it works still. 
	 * @return first table from the page with results 
	 */
	public WebElement getParticipantsTable() {
		List <WebElement> tables = driver.findElements(By.cssSelector("table[class*='striped']"));	
		return tables.get(0);
	}
	/**
	 * 	 
	 * @param handler
	 * @return
	 */
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
    		if(columns.get(x-1).getText().contains("ропозиція")==true) {
    			index=x;
    		}
    	}
    	String value = table.findElement(By.cssSelector("tbody > tr > td:nth-child("+index+")")).getText();		
		return value;
	}
	
	/**
	 * method for handling table with data about participants
	 * @param handler
	 * @return array with name and price offered all participants (including winner)
	 */
	public Object [][] getParticipants(Handler handler) {
		WebElement table = getParticipantsTable();
		String cssForPrice = getCssValueForPriceFromParticipantTable(table);
		List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		Object[][] results = new Object[rows.size()][2];
		for(int x=0; x<rows.size();x++) {
			results[x][0] = rows.get(x).findElement(By.cssSelector("td:nth-child(1)")).getText();
			results[x][1] = handler.getPrice(rows.get(x).findElement(By.cssSelector(cssForPrice)).getText()); 
		}
		return results;	
	}
	/**
	 * method detect number of column containing information about offered prices
	 * handling of incorrect results has to be created. 
	 * @param participantsTable
	 * @return String with css-selector for method getParticipants
	 */
	public String getCssValueForPriceFromParticipantTable(WebElement participantsTable) {
		List<WebElement> headCells = participantsTable.findElements(By.tagName("th"));
		String css = new String();
		for(int x = headCells.size()-1; x>0; x--) {			
			if(headCells.get(x).getText().contains("ропозиція")) {
				return "td:nth-child("+(x+1)+")";				
			} 
		}
		return "Kiss my ass";
	}
	/**
	 * method handle Object[][] received from getParticipants - define entry with winnner
	 * @param handler
	 * @param vinnername
	 * @return entries about participants without winner(or particular result when there are not any participants except winner). Contains name and price
	 */
	public List<ParticipantEntry> getLoosersName(Handler handler, String vinnername, String link){
		Object [][] participants = getParticipants(handler);
		String substring = vinnername.substring(3, vinnername.length()-3);
		List<ParticipantEntry> result = new ArrayList();
		if(participants.length<=1) {
			result.add(new ParticipantEntry(link, "Без конкурентов", 0));
			return result; 
		}else {		 		 
		for(int x=0; x<participants.length; x++) {
			System.out.println("Loop #"+x);
			String name = (String) participants[x][0];
			//System.out.println("Name length: "+name.length());
			if(name.contains(substring)==false) {
			    result.add(new ParticipantEntry(link, name, (Integer) participants[x][1]));
		 } 
		 }
		 if(result.size()==participants.length||(participants.length -result.size())>1) {
			 System.out.println("Something wrong with link "+link);
		 }	 
		 
		return result;
		}
	}	 
	
	public ResultEntry createResult(Handler handler, String url) {
		return new ResultEntry(getId(url), getVinnerName(), getVinnerCode(handler), getItemOfTender(), getPrice(handler),
				   getDate(url, handler), getExpectedPrice(handler), getCurrency(handler));
	}
	
	
    

}
