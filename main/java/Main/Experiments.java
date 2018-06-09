package Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Pages.CompletedResult;
import Pages.Results;

public class Experiments {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 50, 1000);		
		driver.get("https://prozorro.gov.ua/tender/UA-2018-03-27-000891-b");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/div[1]/div[1]/div/div/div[1]")));
		WebElement suka = driver.findElement(By.xpath("//class[contains (text(), 'striped']"));
		System.out.println(suka.getSize().height+" "+suka.getLocation()+" "+suka.getText());
		driver.close();
		 
	}

}
