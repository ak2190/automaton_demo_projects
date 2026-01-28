package qamarvel.framework.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import qamarvel.framework.config.ConfigReader;
import qamarvel.framework.driver.DriverFactory;
import qamarvel.framework.session.SessionManager;
import qamarvel.framework.session.SessionStore;
import qamarvel.pageObjects.LoginPage;

public abstract class BaseTest {

	protected WebDriver driver;

	/*
	 * ===================================================== SUITE-LEVEL AUTH
	 * SESSION (RUNS ONCE) =====================================================
	 */
	@BeforeSuite(alwaysRun = true)
	public void initializeAuthSession() {

		if (SessionStore.isInitialized()) {
			return;
		}

		WebDriver setupDriver = null;

		try {
			DriverFactory.initDriver();
			setupDriver = DriverFactory.getDriver();

			setupDriver.get(ConfigReader.get("baseUrl"));

			LoginPage loginPage = new LoginPage(setupDriver);
			loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
			
			Thread.sleep(5000);

			  // 🔍 DEBUG: verify auth exists BEFORE capture
	        System.out.println("AFTER LOGIN");
	        System.out.println("Cookies: " + setupDriver.manage().getCookies().size());
	        System.out.println("LocalStorage size: " +
	                ((JavascriptExecutor) setupDriver)
	                        .executeScript("return window.localStorage.length;"));
			SessionManager.captureSession(setupDriver);
			
			System.out.println("Cookies: " + driver.manage().getCookies().size());
			System.out.println("LocalStorage size: " +
			    ((JavascriptExecutor)driver)
			        .executeScript("return window.localStorage.length;"));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DriverFactory.quitDriver();
		}
	}

	/*
	 * ===================================================== TEST-LEVEL DRIVER SETUP
	 * (RUNS PER TEST) =====================================================
	 */
	@BeforeTest(alwaysRun = true)
    public void setUp() {

        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();

        // Inject authenticated session
       // SessionManager.injectSession(driver, ConfigReader.get("baseUrl"));
        SessionManager.injectSession(driver,ConfigReader.get("DashboardUrl"));
       
                
                try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
       
    }

	/*
	 * ===================================================== CLEANUP
	 * =====================================================
	 */
	@AfterTest(alwaysRun = true)
	public void tearDown() {

		DriverFactory.quitDriver();
	}
}
