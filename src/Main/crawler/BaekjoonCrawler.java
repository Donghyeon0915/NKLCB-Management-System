/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.crawler;

import static Main.crawler.Crawler.driver;
import Main.entity.Group;
import Main.util.ClipboardManager;
import Main.util.CookieManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

@Setter
@Getter
@NoArgsConstructor
public class BaekjoonCrawler extends Crawler {
    // 다른 사이트 크롤러가 추가되는 경우 쿠키를 개별적으로 사용해야 할 수도 있으므로 BeakjoonCrawler 클래스에 선언
    protected Map<String, String> cookies;  
    private static WebElement element;
    
    public WebDriver login(String id, String pw) {
        try {
            String url = "https://www.acmicpc.net/login?next=%2F";
            driver.get(url);
            
            // 아이디 입력
            element = driver.findElement(By.name("login_user_id"));
            
            ClipboardManager.setClipboard(id);
            element.sendKeys(Keys.CONTROL + "v");
            
            Thread.sleep(500);
            // 패스워드 입력
            element = driver.findElement(By.name("login_password"));
            
            ClipboardManager.setClipboard(pw);
            element.sendKeys(Keys.CONTROL + "v");
            
            Thread.sleep(500);
            //로그인 버튼 클릭
            driver.findElement(By.id("submit_button")).click();
            
            // 로그인 후 쿠키 저장 (Map 형태로 변환)
            this.cookies = CookieManager.convertCookieMap(driver.manage().getCookies());
            
            Thread.sleep(500);
            driver.quit();
            
            return driver;
        } catch (InterruptedException ex) {
            Logger.getLogger(BaekjoonCrawler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    // 내가 속한 그룹의 리스트를 가져오는 메소드
    public ArrayList<Group> getGroupList(){
        try {
            // 그룹 페이지 연결
            Document doc = Jsoup.connect("https://www.acmicpc.net/group/list")
                                .cookies(cookies)
                                .get();
            // 그룹 테이블 tr 태그 가져오기
            Elements tr = doc.select("#ranklist").select("tbody").select("tr");
            
            ArrayList<Group> groupList = new ArrayList<>();
            // 그룹 정보 추출
            for (Element e : tr) {
                Elements aTag = e.select("a");
                groupList.add(Group.builder()
                                   .groupNumber(aTag.get(0).attr("href").split("/group/")[1])
                                   .groupName(aTag.get(0).text())
                                   .groupMaster(aTag.get(1).text())
                                   .members(Integer.parseInt(e.select("td").get(2).text()))
                                   .build());
            }
            
            return groupList;
        } catch (IOException ex) {
            Logger.getLogger(BaekjoonCrawler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    // 게시글을 등록하는 메소드
    public void createArticle(String groupNumber, String title, String content){
        try {
            // 게시글 작성 페이지 연결
            Document writePage = Jsoup.connect("https://www.acmicpc.net/group/board/write/" + groupNumber)
                                      .cookies(cookies)
                                      .get();
            // csrf 토큰 가져오기
            // 게시글 작성할 때마다 csrf 토큰이 갱신 됨 (게시글 데이터와 같이 보내줘야 함)
            String csrf = writePage.select("input[name=csrf_key]").attr("value");
            
            // 게시글 작성
            Jsoup.connect("https://www.acmicpc.net/group/board/write/12975")
                    .cookies(cookies)
                    .data("board-subject", title)
                    .data("board-content", content)
                    .data("csrf_key", csrf)
                    .post();
        } catch (IOException ex) {
            Logger.getLogger(BaekjoonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // 종료된 연습의 결과를 가져오는 메소드
    public void getPracticeResult(String groupNumber, String practiceNumber){
        try {
            String url = "https://www.acmicpc.net/group/practice/view/" + groupNumber + "/" + practiceNumber;
            
            Document doc = Jsoup.connect(url)
                                .cookies(cookies)
                                .get();
            
            Elements table = doc.select("#contest_scoreboard").select("tbody").select("tr");
            
            for (Element tr : table) {
                for (Element td : tr.select("td")) {
                    System.out.println("".equals(td.className()) ? "None" : td.className());
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(BaekjoonCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
