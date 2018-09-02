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
/**
 * this class represents the page appearing after searching by particular parameters and containing list of last tenders results
 *  @author WORK-06
 *
 */
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
	 * clicking on button more in order to go down in the page with results
	 * waiter must be optimizated
	 * @param wait
	 * @throws InterruptedException
	 */
	public void clickMore() throws InterruptedException {
	    buttonMore.click();
	    Thread.sleep(1000);
	    
	}
	/**
	 * 
	 * have to be optimised, because it would repeat searching between results which are already found
	 * @return whole the page with all results after particular amount of clicks
	 * @throws InterruptedException
	 */
	public List<Element> getAllResultsDocument() throws InterruptedException {
		for(int i=0; i<15; i++ ) {
			clickMore();
		}
		String html = result.getAttribute("innerHTML");
		Document doc = Jsoup.parse(html);
		List<Element> items = doc.select("div[class='items-list']");
		//System.out.println(items.get(0).select("li[class='marked']").text());
		//System.out.println(items.get(0).select("a[class='items-list--header']").attr("href"));
		return items;
	}
	
	/**
	 * record 3 xml-files with following types of results: "Завершен", "Кваліфікація переможця", "Пропозиції розглянуті"
	 * recording occurs with help XMLCreator methods: createXMLfinished, createXMLQualified, createXMLPropositionsViewed
	 * @param xml
	 * @throws InterruptedException
	 */
	public void sortingresults(XMLCreator xml) throws InterruptedException {
		List<Element> items = getAllResultsDocument();
		
		List<String> completedLinksOld = xml.readXMLfinished();
		List<String> qualifiedOld = xml.readXMLQualified();
		List<String> viewedPropositionsOld = xml.readXMLPropositionsViewed();
		 
		List<String> completedLinksNew= new ArrayList();
		List<String> qualifiedNew = new ArrayList();
		List<String> viewedPropositionsNew = new ArrayList();
		for(Element e: items) {
			String link = getLinkFromElement(e);
			String typeOfTender = getTypeOfTender(e);		 
			if(typeOfTender.contains("авершен")&&completedLinksOld.contains(link)==false){
			    completedLinksNew.add(link);
			}else if(typeOfTender.contains("валіфікація переможця")&&qualifiedOld.contains(link)==false) {
			     qualifiedNew.add(link);
			}else if(typeOfTender.contains("опозиції розглянуті")&&viewedPropositionsOld.contains(link)==false) {
			     viewedPropositionsNew.add(link);
		}		 
		xml.createXMLfinished(completedLinksNew);		 
		xml.createXMLQualified(qualifiedNew);		 
		xml.createXMLPropositionsViewed(viewedPropositionsNew);		 	
	}	
  }
	
  public String getLinkFromElement(Element element) {
	  return element.select("a[class='items-list--header']").attr("href");
  }
  
  public String getTypeOfTender(Element element) {
	  return element.select("li[class='marked']").text();
  }
}
