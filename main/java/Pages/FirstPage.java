package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * this is first page opened browser
 * @author WORK-06
 *
 */
public class FirstPage {
	
	WebDriver driver;
	private String url = "https://prozorro.gov.ua/";
	
	@FindBy(xpath ="//*[@id=\"query\"]") //this is search field where we input code of necessary company 
	WebElement searchField;
	
	@FindBy(xpath="//*[@id=\"suggest\"]/div[1]") //this is the first item of suggested pop up which appears after input code of company into search field. It must be clicked in order to confirm searching 
	WebElement keyWordSuggest;
	
	
	
	public FirstPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}	
	
	public void launchPage() {
		driver.get(url);		 
	}
	
	/**
	 * This method starts process of results searching in direct company
	 * @param edrpouCode - code of direct company
	 * @param wait - necessary to wait appearance of pop up with suggest of keyword 
	 */
	public void search(String edrpouCode, WebDriverWait wait) {
	 	searchField.sendKeys(edrpouCode);
	    wait.until(ExpectedConditions.visibilityOf(keyWordSuggest));
		keyWordSuggest.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#result > button")));
	}
	
	public void directLaunch(String edrpou) {
		driver.get(url+"tender/search?query="+edrpou);
	}
	
	
	
	

}
