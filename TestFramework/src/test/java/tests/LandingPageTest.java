package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import qamarvel.framework.base.BaseTest;
import qamarvel.pageObjects.LandingPage;
import qamarvel.pageObjects.LoginPage;

public class LandingPageTest extends BaseTest {

	LoginPage lp;
	LandingPage lp1;
	
	@BeforeMethod
	public void setup() {
		lp = new LoginPage(driver);
		lp1 = new LandingPage(driver);
		
	}
	
	@Test
	public void goToDashboard() {
		System.out.println("Go To DashBoard");
		lp1.goTo();
		
	}
}
