/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.Cookie;

/**
 *
 * @author Donghyeon <20183188>
 */
public class CookieManager {
    public static Map<String, String> convertCookieMap(Set<Cookie> cookie){
        // HashMap에 쿠키 세팅
        Map<String, String> cookies = new HashMap<>();
        
        for (Cookie c : cookie) cookies.put(c.getName(), c.getValue());
        
        return cookies;
    }
}
