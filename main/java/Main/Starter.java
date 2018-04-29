package Main;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class Starter {

	public static void main(String[] args) throws InterruptedException {
	 
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 50, 1000);		
		Handler handl = new Handler();
		CompletedResult res = new CompletedResult(driver);
		Connector sqlCon = new Connector();
		FirstPage firstPage = new FirstPage(driver);
		Results resultsOfSearch = new Results(driver);	
		XMLCreator xmlwriter = new XMLCreator();
		//firstPage.launchPage();
		//firstPage.search("20915546", wait);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"result\"]/div[1]/div/div[1]/div/div")));
		//List <String> accepts = resultsOfSearch.get100results();
		//xmlwriter.createXMLCompletedTendersLinks(accepts);
		List<String> accepts = xmlwriter.readXMLsavedCompletedtendersLinks();
		List<ResultEntry> results = new ArrayList();
		for(int x=0; x<accepts.size(); x++) {
			res.enterPage(accepts.get(x), wait);
			results.add(res.createEntry());
		}
		System.out.println(results.size());
		for(ResultEntry r: results) {
			System.out.println(r.getVinner_company_code());
			System.out.println(r.getItem_of_tender(handl));
			System.out.println(r.getVinner_company_name(handl));
		}
		//sqlCon.insertDataIntoResults(results, driver, wait, handl);
		driver.close();
	}
}

/*
 *  System.out.println(res.getId());
		 System.out.println(res.getAcceptedCompanyName());
		 System.out.println(res.getAcceptedCompanyCode());
		 System.out.println(res.getGoodName());
		 System.out.println(res.getPrice());
		 System.out.println(res.getDate());
		 System.out.println(res.detExpectedPrice());
		 System.out.println(res.getCurrency());*/



/*for(int x=0; x<accepts.size(); x++) {

res.enterPage(accepts.get(x), wait);		
sqlCon.insertDataIntoResults(res.getId(), res.getAcceptedCompanyName(), res.getAcceptedCompanyCode(), res.getGoodName(),
		                      res.getPrice(), res.getDate(), res.detExpectedPrice(), res.getCurrency());
}*/