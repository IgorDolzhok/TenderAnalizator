package Pages;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.XMLCreator;

public class Results {
     
	 WebDriver driver;
	 
	@FindBy(className="spinner")
	WebElement spinner;
	
	@FindBy(css="#result > button")
	WebElement buttonMore;
	
	@FindBy(id="result")
	WebElement result;

	public Results(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * waiter must be optimizated
	 * @param wait
	 * @throws InterruptedException
	 */
	public void clickMore() throws InterruptedException {
	    buttonMore.click();
	    Thread.sleep(1000);
	    
	}
	
	public List<Element> getAllResultsDocument() throws InterruptedException {
		for(int i=0; i<5; i++ ) {
			clickMore();
		}
		String html = result.getAttribute("innerHTML");
		Document doc = Jsoup.parse(html);
		List<Element> items = doc.select("div[class='items-list']");
		//System.out.println(items.get(0).select("li[class='marked']").text());
		//System.out.println(items.get(0).select("a[class='items-list--header']").attr("href"));
		return items;
	}
	
	public void sortingresults(XMLCreator xml) throws InterruptedException {
		List<Element> items = getAllResultsDocument();
		List<String> completedLinks= new ArrayList();
		List<String> qualified = new ArrayList();
		List<String> viewedPropositions = new ArrayList();
		for(Element e: items) {
			if(e.select("li[class='marked']").text().contains("авершено")==true) {
			     completedLinks.add(e.select("a[class='items-list--header']").attr("href"));
			}else if(e.select("li[class='marked']").text().contains("валіфікація переможця")==true) {
			     qualified.add(e.select("a[class='items-list--header']").attr("href"));
			}else if(e.select("li[class='marked']").text().contains("опозиції розглянуті")==true) {
			     viewedPropositions.add(e.select("a[class='items-list--header']").attr("href"));
		}
		xml.createXMLfinished(completedLinks);
		xml.createXMLQualified(qualified);
		xml.createXMLPropositionsViewed(viewedPropositions);
		
	}
   
	
	
	
	
	
	
}
}
