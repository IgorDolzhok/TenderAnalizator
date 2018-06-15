package Main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.ResultEntry;
import Common.XMLCreator;
import Pages.CompletedResult;
import Pages.FirstPage;
import Pages.Results;
import SQL.Connector;
/**
 * TO DO: 1.Methods to get all necessary values from CompletedResultsPage
 * @author WORK-06
 *
 */
public class Starter {

	public static void main(String[] args) throws InterruptedException {
	 
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 50, 1000);			 
		FirstPage firstPage = new FirstPage(driver);
		XMLCreator xml = new XMLCreator();
		Results results = new Results(driver);
		String edrpouAES = "20915546";				
		firstPage.launchPage();
		firstPage.search(edrpouAES, wait);
		results.sortingresults(xml);
		 
		 
		 
		
		
		 
		 
	}
}

 