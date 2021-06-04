package blazeDemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class BlazeDemoUITest {
	public static WebDriver driver;
	private static Select select;

	public static void main(String[] args) throws Exception {
		baseTest();
		blazeDemoPageTest();
		blazeDemoReversePageTest();
		blazeDemoPurchasePageTest();
		blazeDemoConfirmationPageTest();
	}

	/*Base Test with Application Launch*/
	public static void baseTest() throws Exception{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.blazedemo.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/*BlazeDemo Page*/
	public static void blazeDemoPageTest() throws Exception{
		Assert.assertEquals(driver.getTitle(), "BlazeDemo");
		System.out.println("Page Title '"+driver.getTitle() +"'Verified");
		WebElement fromPortDD= driver.findElement(By.xpath("//select[@name='fromPort']"));
		Select select=new Select(fromPortDD);
		select.selectByValue("Boston");
		WebElement toPortDD= driver.findElement(By.xpath("//select[@name='toPort']"));
		select=new Select(toPortDD);
		select.selectByValue("London");
		WebElement findFlightBtn= driver.findElement(By.xpath("//input[@type='submit']"));
		Thread.sleep(3000);
		findFlightBtn.click();
	}

	/*BlazeDemo-reserve Page*/
	public static void blazeDemoReversePageTest() throws Exception{
		Assert.assertEquals(driver.getTitle(), "BlazeDemo - reserve");
		System.out.println("Page Title '"+driver.getTitle() +"'Verified");
		WebElement searchConfirmationTxt=driver.findElement(By.xpath("//h3"));
		Assert.assertEquals(searchConfirmationTxt.getText(),"Flights from Boston to London:");

		List<WebElement> searchRowData= driver.findElements(By.xpath("//tbody/tr"));
		int searchTableRow= searchRowData.size();
		List<WebElement> airlines=driver.findElements(By.xpath("//tbody/tr/td[3]"));
		List<WebElement> flightNumbers=driver.findElements(By.xpath("//tbody/tr/td[2]"));
		List<WebElement> chooseFlightBtnList=driver.findElements(By.xpath("//tbody/tr/td[1]"));
		System.out.println("Airline: "+airlines.get(3).getText());
		for(int i=0; i<searchTableRow; i++) {
			if (((airlines.get(i).getText()).equals("Virgin America")) && ((flightNumbers.get(i).getText()).equals("12"))) {
				System.out.println("Position of airline: "+ (i+1) );
				Thread.sleep(3000);
				chooseFlightBtnList.get(i).click();
				break;
			}
		}
	}

	/*BlazeDemo Purchase Page*/
	public static void blazeDemoPurchasePageTest() throws Exception{
		Assert.assertEquals(driver.getTitle(), "BlazeDemo Purchase");
		System.out.println("Page Title '"+driver.getTitle() +"'Verified");
		WebElement flightSelectConfirmationTxt=driver.findElement(By.xpath("//h2"));
		Assert.assertEquals(flightSelectConfirmationTxt.getText(),"Your flight from TLV to SFO has been reserved.");

		WebElement nameFld=driver.findElement(By.xpath("//input[@id='inputName']"));
		WebElement addressFld=driver.findElement(By.xpath("//input[@id='address']"));
		WebElement cityFld=driver.findElement(By.xpath("//input[@id='city']"));
		WebElement stateFld=driver.findElement(By.xpath("//input[@id='state']"));
		WebElement zipFld=driver.findElement(By.xpath("//input[@id='zipCode']"));
		WebElement cardTypeList=driver.findElement(By.xpath("//select[@name='cardType']"));
		WebElement cardNumberFld=driver.findElement(By.xpath("//input[@id='creditCardNumber']"));
		WebElement monthFld=driver.findElement(By.xpath("//input[@id='creditCardMonth']"));
		WebElement yearFld=driver.findElement(By.xpath("//input[@id='creditCardYear']"));
		WebElement nameOnCardFld=driver.findElement(By.xpath("//input[@id='nameOnCard']"));
		WebElement purchaseFlightBtn=driver.findElement(By.xpath("//input[@type='submit']"));

		nameFld.sendKeys("Sheetesh Rath");
		addressFld.sendKeys("1234 Appartment");
		cityFld.sendKeys("Bengaluru");
		stateFld.sendKeys("Karnataka");
		zipFld.sendKeys("560067");
		select = new Select(cardTypeList);
		select.selectByVisibleText("American Express");
		cardNumberFld.sendKeys("1234567890123456");
		monthFld.sendKeys("12"); 
		yearFld.sendKeys("2021");
		nameOnCardFld.sendKeys("Sheetesh Kumar Rath");
		Thread.sleep(3000);
		purchaseFlightBtn.click();
	}

		/*BlazeDemo confirmation Page*/
		public static void blazeDemoConfirmationPageTest() throws Exception{
			Assert.assertEquals(driver.getTitle(), "BlazeDemo Confirmation");
			System.out.println("Page Title '"+driver.getTitle() +"'Verified");
			WebElement confirmationTxt= driver.findElement(By.xpath("//h1"));
			Assert.assertEquals(confirmationTxt.getText(),"Thank you for your purchase today!");
			List<WebElement> confirmationRowData= driver.findElements(By.xpath("//tbody/tr"));
			int confirmationTableRow= confirmationRowData.size();
			List<WebElement> confirmationItems=driver.findElements(By.xpath("//tbody/tr/td[1]"));
			List<WebElement> confirmationItemValues=driver.findElements(By.xpath("//tbody/tr/td[2]"));
			for(int i=0; i<confirmationTableRow; i++) {
				if ((confirmationItems.get(i).getText()).equals("Id")){
					System.out.println("Flight Reservation Confirmation ID is: "+confirmationItemValues.get(i).getText());
					break;
				}
			}
			System.out.println("******* Flight Reservation for User has been PASSED ********");
			System.out.println("****************** Test Execution Completed ****************");
			Thread.sleep(3000);
			driver.quit();
		}

	}

