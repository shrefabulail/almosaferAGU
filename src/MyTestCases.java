import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	String Url = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();

	@BeforeTest

	public void myBeforeTest() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.get(Url);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/button[1]")).click();

	}

	@Test(enabled = false)
	public void checktheLanguage() {
		String LanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
		System.out.println(LanguageofTheWebsite);
		Assert.assertEquals(LanguageofTheWebsite, "en");

	}

	@Test(enabled = false)
	public void thisisToTestTheCurrency() {
		String ActualCurrencyonTheWebSite = driver.findElement(By.xpath("//button[normalize-space()='SAR']")).getText();
		Assert.assertEquals(ActualCurrencyonTheWebSite, "SAR");
	}

	@Test(enabled = false)
	public void ContactNumberCheck() {
		WebElement MobileNumberOnTheWebsite = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));

		String ActualMobileNumber = MobileNumberOnTheWebsite.getText();
		String ExpectedActualMobile = "+966554400000";
		Assert.assertEquals(ActualMobileNumber, ExpectedActualMobile);
	}

	@Test(enabled = false)
	public void checQitafLogoIsTher() {
		WebElement Footr = driver.findElement(By.tagName("footer"));
		boolean isQitafDisplayed = Footr.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']//*[name()='svg']"))
				.isDisplayed();

		Assert.assertEquals(isQitafDisplayed, true);

	}

	@Test(enabled = false)
	public void CheckHotelTabIsNotSelectrd() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValie = HotelTab.getAttribute("aria-selected");
		Assert.assertEquals(ActualValie, "false");

	}

	@Test(invocationCount = 10)
	public void ChangTheLanguageOfTheWebsiteRandomly() throws InterruptedException {
		String[] myWebsite = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		Random rand = new Random();

		int randomNumber = rand.nextInt(myWebsite.length);
		driver.get(myWebsite[randomNumber]);
		String myWebsiteURL = driver.getCurrentUrl();
		if (myWebsiteURL.contains("ar")) {

			String LanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
			System.out.println(LanguageofTheWebsite);
			Assert.assertEquals(LanguageofTheWebsite, "ar");

		}

		else if ((myWebsiteURL.contains("en"))) {
			String LanguageofTheWebsite = driver.findElement(By.tagName("html")).getAttribute("lang");
			Assert.assertEquals(LanguageofTheWebsite, "en");
		}
	}

	@AfterTest
	public void myAfterTest() {
	}

}
