package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Common.Handler;

public class Results {
     
	WebDriver driver;
	private String resultsXpath = "//*[@id=\"result\"]";
	private String itemClass = "items-list";
	private String itemCss1 = "#result > div:nth-child(";
	private String itemCssForMark =") > div > div > div > div.col-md-8 > ol > li.marked";
	private String itemCssForHref =") > div > div > div > div.col-md-8 > a";
	
	
	@FindBy(xpath="//*[@id=\"result\"]/button")
	WebElement buttonMore;

	public Results(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getItems(){
		List<WebElement> items = driver.findElements(By.className(itemClass));
		return items;
	}
	
	public void getResults() {
		Handler handler = new Handler();
		List<WebElement> results = getItems();
		for(int i = 0; i<2; i++) {
			System.out.println("Output result #"+(i+1));
			System.out.println("Href: "+results.get(i).findElement(By.className("items-list--header")).getAttribute("href"));
			System.out.println("Name of good: "+ results.get(i).findElement(By.className("items-list--header")).getText());
			System.out.println("Status: "+results.get(i).findElement(By.className("marked")).getText());
			System.out.println("Price: "+handler.getPrice(results.get(i).findElement(By.className("items-list--item--price")).getText()));
			System.out.println("Currency: "+handler.getCurrency(results.get(i).findElement(By.className("items-list--item--price")).getText()));
			System.out.println("Id: "+handler.getID(results.get(i).findElement(By.className("items-list--tem-id")).getText()));
		}
	}
	
	public void getMoreResults() throws InterruptedException {
		buttonMore.click();
		Thread.sleep(1000);
		System.out.println(getItems().size());
	}
	
	/**
	 * this method works after output results of direct company on the screen. It collect Url all the results which have a condition "���������" 
	 * among x*10 results of direct company
	 * @return List of URL of all results with "���������"
	 * @throws InterruptedException
	 */
	public List<String> get100results() throws InterruptedException {
		List <String> linksFinished = new ArrayList();
		//List <String> linksViewed = new ArrayList();
		//List <String> linksQualification =  new ArrayList();
		for(int i=0; i<30; i++) { //����� ���� ��������� ����� �����. ���� ���� ������������ ��� 
			buttonMore.click();
			Thread.sleep(1000);
		}
		for(int x=0; x<300; x++) {
			String status = driver.findElement(By.cssSelector(itemCss1+(x+2)+itemCssForMark)).getText(); 
			if(status.contains("�������")) { //||status.contains("��������� ���������")||status.contains("���������� ��������")) {
				linksFinished.add(driver.findElement(By.cssSelector(itemCss1+(x+2)+itemCssForHref)).getAttribute("href"));
			}
			/*if(status.contains("��������� ���������")) {
				linksViewed.add(driver.findElement(By.cssSelector(itemCss1+(x+2)+itemCssForHref)).getAttribute("href"));
			}
			if(status.contains("���������� ��������")) {
				linksQualification.add(driver.findElement(By.cssSelector(itemCss1+(x+2)+itemCssForHref)).getAttribute("href"));
			}*/
		}		
		return linksFinished; 		
	}
    
	
	
	
	
}
