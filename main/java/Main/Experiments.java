package Main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.XMLCreator;
import Pages.CompletedResult;
import Pages.FirstPage;
import Pages.Results;

public class Experiments {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 50, 1000);		
		FirstPage firstPage = new FirstPage(driver);
		XMLCreator xml = new XMLCreator();
		Handler handler = new Handler();
		CompletedResult result = new CompletedResult(driver);
		List<String> links = xml.readXMLQualified();
		//for(String link: links) {
			result.launch(links.get(3));
			System.out.println(result.getVinnerName(handler));
		//};
		 
	}

}
