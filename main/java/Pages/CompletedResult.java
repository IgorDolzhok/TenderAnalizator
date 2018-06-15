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

public class CompletedResult {
	
	WebDriver driver;	 

	@FindBy(className="container")
	WebElement container;

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
	
	public WebElement getVinnerTable() {
		List <WebElement> tables = driver.findElements(By.cssSelector("table[class*='striped']"));
		
		return tables.get(tables.size()-1);//driver.findElement(By.cssSelector("table[class*='striped']"));
		 
	}   
	 
	public String getVinnerName(Handler handler) {
		 WebElement table = getVinnerTable();		 
		 String outerHtml = table.getAttribute("outerHTML");
		 String code = handler.getVinnerCodeFromOuterHTML(outerHtml);		
		 return code;
	}
	
	
	
    

}
