import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	String Url = "https://www.almosafer.com/ar";
	WebDriver driver = new ChromeDriver();

	@BeforeTest

	public void myBeforeTest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.get(Url);
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();
	}

	@Test
	public void checktheLanguage() {
		String LanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
		System.out.println(LanguageofTheWebsite);
		Assert.assertEquals(LanguageofTheWebsite, "ar");

	}
	
	@Test
	public void thisisToTestTheCurrency() {
		String ActualCurrencyonTheWebSite = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();
		Assert.assertEquals(ActualCurrencyonTheWebSite, "SAR");
		
		
	}

	@AfterTest
	public void myAfterTest() {
	}

}
