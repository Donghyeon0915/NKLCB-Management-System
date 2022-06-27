/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Main.crawler.BaekjoonCrawler;
import Main.crawler.Crawler;
import Main.entity.Group;
import Main.util.CookieManager;
import java.awt.datatransfer.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author Donghyeon <20183188>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static WebDriver driver;
    private static WebElement element;
    private static Clipboard clipboard;
    
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        BaekjoonCrawler crawler = new BaekjoonCrawler();
        
		try {
            driver = crawler.login("dongdong99", "blue795132486");

            for (Group group : crawler.getGroupList()) {
                System.out.println(group);
            }
            
            crawler.getPracticeResult("12975", "2");
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
