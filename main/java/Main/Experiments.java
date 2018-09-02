package Main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.ParticipantEntry;
import Common.Report;
import Common.ResultEntry;
import Common.XMLCreator;
import Pages.CompletedResult;
import Pages.FirstPage; 
import Pages.Results;
import SQL.Connector;
import SQL.GetQueryMaker;
import SQL.QueryMaker;
 

/**
 *  
 * @author WORK-06
 *
 */
public class Experiments {
    
	public static void main(String[] args) throws InterruptedException, SQLException, IOException {		 
		
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
		GetQueryMaker getQueryMaker = new GetQueryMaker(stringBuilder);
		Report report = new Report();
		
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
	    /*String query = getQueryMaker.getParticipants("‘≤«»◊Õ¿ Œ—Œ¡¿-œ≤ƒœ–»™Ã≈÷‹ ‘≈Œ‘≤À¿ “Œ¬¿ Ã¿–»Õ¿ ¬≤ “Œ–≤¬Õ¿");
	    ResultSet resultofQuery= con.selectQuery(query);	     
	    report.createTableParticipants(resultofQuery);*/		
		con.closeConnection();
		}
	   

		//con.insertDataIntoParticipants(loosers, handler);
		//con.insertDataIntoResults(results, handler);
		 
		/*List<ParticipantEntry> loosers= new ArrayList();
		List<ResultEntry> results = new ArrayList();
		for(String link: links) {
		result.launch(link);
		loosers.addAll(result.createLoosers(handler, result.getVinnerName(), link));
		results.add(result.createResult(handler, link));		 
		} 
		con.insertDataIntoParticipants(loosers, handler);
        */		
//}
	
	
	
	
	public static void getXMLwithResults(FirstPage first, Results results, XMLCreator xml, String code, WebDriverWait wait, WebDriver driver) 
	throws InterruptedException {
		first.launchPage();		 
		first.search(code, wait);
		results.sortingresults(xml);
	}	 
	
	public static void printListResults(CompletedResult result, List<String> links, Handler handler, String url) {
		System.out.println(links.size());
		for(String link: links) {
			printResult(result, link, handler, url);			
	   }
	}
	
	public static void printResult(CompletedResult result, String link, Handler handler, String url) {
		result.launch(link);
		//List<ParticipantEntry> loosers = result.getLoosersName(handler, result.getVinnerName(), url);
		System.out.println(result.getId(url));
		System.out.println(result.getVinnerCode(handler));
		System.out.println(result.getVinnerName());
		System.out.println(result.getItemOfTender());
		System.out.println(result.getCurrency(handler));
		System.out.println(result.getPrice(handler));
	    System.out.println("*******************************************************");
	   /* for(int x=0; x<loosers.size(); x++) {
	    	System.out.println(loosers+"   "+loosers[x][1]);
	    }*/
	    System.out.println("*******************************************************");
	}

}

