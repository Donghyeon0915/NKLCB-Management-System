/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.crawler;

import static Main.crawler.Crawler.driver;
import Main.util.ClipboardManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Donghyeon <20183188>
 */
public class BaekjoonCrawler extends Crawler {
    private static WebElement element;
    private Set<Cookie> cookie;
    public BaekjoonCrawler() { }

    public WebDriver loginToBaekjoon(String id, String pw) throws InterruptedException, IOException {
        String url = "https://www.acmicpc.net/login?next=%2F";
        driver.get(url);
        
        // 아이디 입력
        element = driver.findElement(By.name("login_user_id"));
        Thread.sleep(30);

        ClipboardManager.setClipboard(id);
        element.sendKeys(Keys.CONTROL + "v");
        element.sendKeys(Keys.TAB);
        
        // 패스워드 입력
        element = driver.findElement(By.name("login_password"));
        Thread.sleep(60);
        
        ClipboardManager.setClipboard(pw);
        element.sendKeys(Keys.CONTROL + "v");
        
        //로그인 버튼 클릭
        driver.findElement(By.id("submit_button")).click();
        
        // 로그인 후 쿠키 저장
        this.cookie = driver.manage().getCookies();
        
        Thread.sleep(1000);
        driver.quit();
        
        return driver;
    }

    public Set<Cookie> getCookie() { return cookie; }
}
