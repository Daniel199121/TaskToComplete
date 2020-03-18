import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class B2BNetwork {

	public static void main(String[] args) throws InterruptedException {

		// Initializing variables used in Test Case
		double max = 0;
		double doubleValue;
		double maxGrossGross;
		int counterSponsored;
		int counterSearched;
		String element;

		// Configuring browser
		System.setProperty("webdriver.chrome.driver", "D:\\private_repository\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// Task 1
		driver.get("https://www.allegro.pl/");
		driver.findElement(By.cssSelector("button[data-role='accept-consent']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("img[itemprop='logo'][alt*='Allegro']")).isDisplayed());

		// Task 2
		driver.findElement(By.cssSelector("input[type='search']")).sendKeys("Iphone 11");
		driver.findElement(By.cssSelector("button[data-role='search-button']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Iphone 11')]")).isDisplayed());

		// Task 3
		driver.findElement(By.cssSelector("a[href*='kolor=czarny']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'czarny')]")).isDisplayed());

		// Task 4
		counterSearched = driver.findElements(By.cssSelector("div[id='opbox-listing--base'] section:nth-child(2) article")).size();
		counterSponsored = driver.findElements(By.cssSelector("div[id='opbox-listing--base'] section article")).size();
		System.out.println("Liczba telefonów na liście (bez sponsorowanych) na tej stronie: " + counterSearched);
		System.out.println("Liczba telefonów na liście (wraz ze sponsorowanymi) na tej stronie: " + counterSponsored);

		// Task 5
		for (int i = 1; i <= counterSearched; i++) {
			// To print element[i] -> System.out.println("Cena nr: " + i + " to: " + driver.findElement(By.cssSelector("div[id='opbox-listing--base'] section:nth-child(2) article:nth-child(" + i + ") div:nth-child(1) div:nth-child(1) div:nth-child(2) div div:nth-child(1) div:nth-child(1) span:nth-child(1)")).getText());
			element = driver.findElement(By.cssSelector("div[id='opbox-listing--base'] section:nth-child(2) article:nth-child(" + i + ")  div:nth-child(1) div:nth-child(1) div:nth-child(2) div div:nth-child(1) div:nth-child(1) span:nth-child(1)")).getText();
			//Prepare String to convert to Double
			element = element.replace(" zł", "").trim();
			element = element.replace(" ", "").trim();
			element = element.replace(",", ".").trim();
			//Convert to Double
			doubleValue = Double.valueOf(element);
			//If doubleValue is higher than max then doubleValue is max 
			if (doubleValue > max) {
				max = doubleValue;
			}
		}
		System.out.println("Największa cena (bez sponsorowanych) - to: " + String.format("%.2f", max) + " zł");

		// Task 6
		maxGrossGross = max * 1.23;
		System.out.println("Największa cena + 23% (bez sponsorowanych) - to: " + String.format("%.2f", maxGrossGross) + " zł");
	}
}
