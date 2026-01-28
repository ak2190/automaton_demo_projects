package qamarvel.framework.session;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SessionManager {

    private SessionManager() {}

    /* =====================
       CAPTURE SESSION
       ===================== */
    public static void captureSession(WebDriver driver) {

        AuthSession session = new AuthSession();

        // 1. Cookies
        Set<Cookie> cookies = driver.manage().getCookies();
        session.setCookies(cookies);

        // 2. localStorage
        session.setLocalStorage(readStorage(driver, "localStorage"));

        // 3. sessionStorage
        session.setSessionStorage(readStorage(driver, "sessionStorage"));

        SessionStore.save(session);
    }

    /* =====================
       RESTORE SESSION
       ===================== */
	/*
	 * public static void injectSession(WebDriver driver, String baseUrl) {
	 * 
	 * AuthSession session = SessionStore.get();
	 * 
	 * driver.get(baseUrl);
	 * 
	 * // Cookies must be added AFTER domain load for (Cookie cookie :
	 * session.getCookies()) { driver.manage().addCookie(cookie); }
	 * 
	 * writeStorage(driver, "localStorage", session.getLocalStorage());
	 * writeStorage(driver, "sessionStorage", session.getSessionStorage());
	 * 
	 * driver.navigate().refresh(); }
	 */
    
    public static void injectSession(WebDriver driver, String baseUrl) {

        AuthSession session = SessionStore.get();

        // 1️⃣ Load base domain (blank state)
        driver.get(baseUrl);

        // 2️⃣ Inject cookies
        for (Cookie cookie : session.getCookies()) {
            driver.manage().addCookie(cookie);
        }

        // 3️⃣ Inject storage
        writeStorage(driver, "localStorage", session.getLocalStorage());
        writeStorage(driver, "sessionStorage", session.getSessionStorage());

        // 4️⃣ Reload app WITH auth present
        driver.navigate().refresh();
        
        System.out.println("Cookies: " + driver.manage().getCookies().size());
        System.out.println("LocalStorage size: " +
            ((JavascriptExecutor)driver)
                .executeScript("return window.localStorage.length;"));
    }


    /* =====================
       STORAGE HELPERS
       ===================== */
    private static Map<String, String> readStorage(WebDriver driver, String type) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Map<String, String> storage = new HashMap<>();

        String script =
                "var items = {}; " +
                "for (var i = 0; i < window." + type + ".length; i++) {" +
                "  var key = window." + type + ".key(i);" +
                "  items[key] = window." + type + ".getItem(key);" +
                "} return items;";

        storage.putAll((Map<String, String>) js.executeScript(script));
        return storage;
    }

    private static void writeStorage(WebDriver driver, String type, Map<String, String> data) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (Map.Entry<String, String> entry : data.entrySet()) {
            js.executeScript(
                "window." + type + ".setItem(arguments[0], arguments[1]);",
                entry.getKey(), entry.getValue()
            );
        }
    }
}
