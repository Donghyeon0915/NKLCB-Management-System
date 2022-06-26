/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.crawler;

import Main.util.WindowController;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Donghyeon <20183188>
 */
public class Crawler {
    
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //드라이버 ID
	private static final String WEB_DRIVER_PATH = "./src/Library/chromedriver.exe"; //드라이버 경로
    protected static final String USER_AGENT = "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";
    
    protected static WebDriver driver;
    protected final WebDriverWait wait;
    protected WindowController winController;
    
    public Crawler() {
        this.winController = new WindowController();
        
        // 드라이버 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
				
        //  WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        
        options.addArguments("--start-maximized");
        options.addArguments("--window-size=1920x1080");
        options.addArguments("--disable-popup-blocking");                       // 팝업 안띄움
        options.addArguments("-no-sandbox");    
        options.addArguments("--disable-gpu");                                   // GPU 비활성화
        options.addArguments("--blink-settings=imagesEnabled=false");         // 이미지 다운 안받음
        options.addArguments("lang=ko_KR");                                      // 한국어 설정
        options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
            
        driver = new ChromeDriver(options);
        
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
        
    public WebElement explicitWait(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebDriver getDriver() { return driver; }
}
