package webElements;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UpdatedDropdown {

	public static void main(String[] args) throws InterruptedException {
		// reduce noisy selenium/http logs
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
        Logger.getLogger("jdk.internal.net.http").setLevel(Level.SEVERE);
		// TODO Auto-generated method stub
		
		WebDriver driver = null;
		
		
		System.setProperty("web.driver.chrome","I://drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();
		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(2000);
			
		for(int i=0;i<4;i++) {
			driver.findElement(By.id("hrefIncAdt")).click();	
		}
		
		driver.findElement(By.id("btnclosepaxoption")).click();
		String adultCount = driver.findElement(By.id("divpaxinfo")).getText();
		System.out.println(adultCount);
		
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		
		driver.findElement(By.xpath("//a[@value='BLR']")).click();
		
		driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
		
		driver.close();
		
		
			
		

	}

}
