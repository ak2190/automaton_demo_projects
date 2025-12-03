package webElements;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class StaticDropDowns {
	
	

	public static void main(String[] args) {
		
		// reduce noisy selenium/http logs
        Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE);
        Logger.getLogger("org.openqa.selenium.remote.http.WebSocket").setLevel(Level.SEVERE);
        Logger.getLogger("jdk.internal.net.http").setLevel(Level.SEVERE);
		// TODO Auto-generated method stub
		
		WebDriver driver = null;
		
		
		System.setProperty("web.driver.chrome","I://drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		WebElement currencyDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown = new Select(currencyDropdown);
		dropdown.selectByIndex(3);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		dropdown.selectByValue("AED");
		dropdown.selectByContainsVisibleText("INR");
		System.out.println(dropdown.getFirstSelectedOption().getText());
		driver.close();
		System.out.println("program ended..!");
	}

}
