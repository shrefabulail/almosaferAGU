import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.KebabCaseStrategy;

import io.opentelemetry.sdk.metrics.data.Data;

public class MyTestCases {

	String Url = "https://www.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert myAssertion = new SoftAssert();

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

	@Test(invocationCount = 10, enabled = false)
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

	@Test(enabled = false)
	public void CheckTheDateOfTheWebSite() {

		LocalDate today = LocalDate.now();

		int expectedDepatureDate = today.plusDays(1).getDayOfMonth();
		int expectedReturnDate = today.plusDays(2).getDayOfMonth();

		WebElement ActualDepatureDateonTheWebSite = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement ActualReturuDateTheWebSite = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));

		String ExpectedWelcomMsg = "Let’s book your next trip!mmm";
		String ActualWelcomeMsg = driver.findElement(By.xpath("//h1[contains(text(),'Let’s book your next trip!')]"))
				.getText();

		myAssertion.assertEquals(Integer.parseInt(ActualDepatureDateonTheWebSite.getText()), 66);
		myAssertion.assertEquals(Integer.parseInt(ActualReturuDateTheWebSite.getText()), 77);
		myAssertion.assertEquals(ActualWelcomeMsg, ExpectedWelcomMsg);

		myAssertion.assertAll();

	}

	@Test()
	public void HotelTabSwitch() throws InterruptedException {
		Random rand = new Random();
		String[] arabicCities = { "جدة", "دبي" };
		String[] englishCities = { "jeddah", "riyadh", "dubai" };

		int RandomArabicCities = rand.nextInt(arabicCities.length);
		int RandomEnglishCities = rand.nextInt(englishCities.length);

		String[] myWebsite = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar" };
		Random rand1 = new Random();

		int randomNumber = rand1.nextInt(myWebsite.length);
		driver.get(myWebsite[randomNumber]);
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();

		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("ar")) {
			WebElement SearchAboutHotelTab = driver
					.findElement(By.xpath("//input[@placeholder='البحث عن فنادق أو وجهات']"));

			SearchAboutHotelTab.sendKeys(arabicCities[RandomArabicCities] + Keys.ENTER);
			driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();

			
			{

				Thread.sleep(2000);
				WebElement mySelectElemwnt = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
				Select selector = new Select(mySelectElemwnt);

				selector.selectByIndex(rand.nextInt(2));
				String resultsFound= driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();

				Assert.assertEquals(resultsFound.contains("وجدنا"),true);

			}

		} else {

			WebElement SearchAboutHotelTab = driver
					.findElement(By.xpath("//input[@placeholder='Search for hotels or places']"));
			SearchAboutHotelTab.sendKeys(englishCities[RandomEnglishCities] + Keys.ENTER);

			driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
			

			Thread.sleep(2000);
			WebElement mySelectElemwnt = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
			Select selector = new Select(mySelectElemwnt);
			selector.selectByIndex(rand.nextInt(2));
			
		String resultsFound= driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();

		Assert.assertEquals(resultsFound.contains("Found"),true);
		}

	}

	@AfterTest
	public void myAfterTest() {
	}

}