/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Main.crawler.BaekjoonCrawler;
import Main.crawler.Crawler;
import Main.util.CookieManager;
import java.awt.datatransfer.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
            driver = crawler.loginToBaekjoon("dongdong99", "blue795132486");
            
            Map<String, String> cookies = CookieManager.convertCookieMap(crawler.getCookie());
            
            Document doc = Jsoup.connect("https://www.acmicpc.net/group/list")
                 .cookies(cookies)
                 .get();
            
            Elements tr = doc.select("#ranklist").select("tbody").select("tr");
            
            for (Element e : tr) {
                Elements aTag = e.select("a");
                System.out.println("Group No. : " + aTag.get(0).attr("href"));
                System.out.println("Group Name : " + aTag.get(0).text());
                System.out.println("Group Master : " + aTag.get(1).text());
                System.out.println("Memeber : " + e.select("td").get(2).text());
            }
            
            // 게시글 작성할 때마다 csrf 토큰이 갱신 됨 (게시글 데이터와 같이 보내줘야 함)
            Document writePage = Jsoup.connect("https://www.acmicpc.net/group/board/write/12975").cookies(cookies).get();
            String csrf = writePage.select("input[name=csrf_key]").attr("value");
            
            Jsoup.connect("https://www.acmicpc.net/group/board/write/12975")
                 .cookies(cookies)
                 .data("board-subject", "jsoup으로 작성2")
                 .data("board-content", "jsoup으로 작성2")
                 .data("csrf_key", csrf)
                 .post();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
