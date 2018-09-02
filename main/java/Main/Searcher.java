package Main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.ParticipantEntry;
import Common.ResultEntry;
import Common.XMLCreator;
import Pages.CompletedResult;
import Pages.FirstPage;
import Pages.Results;
import SQL.Connector;
import SQL.QueryMaker;

public class Searcher {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 50, 1000);		
		FirstPage firstPage = new FirstPage(driver);
		XMLCreator xml = new XMLCreator();
		Handler handler = new Handler();
		CompletedResult result = new CompletedResult(driver);
		String edrpouAES = "20915546";				
		Results res = new Results(driver);
		Connector con= new Connector();		
		StringBuilder stringBuilder = new StringBuilder();
		QueryMaker queryMaker = new QueryMaker(stringBuilder);
		
		getXMLwithResults(firstPage, res, xml, edrpouAES, wait, driver);
				List<ResultEntry> results = new ArrayList();
				List<ParticipantEntry> loosers = new ArrayList();
				List<String> links = xml.readXMLPropositionsViewed();
				links.addAll(xml.readXMLQualified());
				links.addAll(xml.readXMLfinished());
				for(String link: links) {
					System.out.println(link);
					result.launch(link);
				    loosers.addAll(result.getLoosersName(handler, result.getVinnerName(), link));
				    results.add(result.createResult(handler, link));
				}
				con.insertResultAndParticipants(queryMaker, results, loosers, handler);

	}
	
	public static void getXMLwithResults(FirstPage first, Results results, XMLCreator xml, String code, WebDriverWait wait, WebDriver driver) 
			throws InterruptedException {
				first.launchPage();		 
				first.search(code, wait);
				results.sortingresults(xml);
	}	 

}
