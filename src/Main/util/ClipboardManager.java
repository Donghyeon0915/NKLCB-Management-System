/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main.util;

/**
 *
 * @author Donghyeon <20183188>
 */
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class ClipboardManager {
    public static void setClipboard(String str){
        StringSelection data = new StringSelection(str);
        
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(data, data);
    }
    
    public static String getClipboard(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(clipboard);

        try {
            return (String)(contents.getTransferData(DataFlavor.stringFlavor));
        } catch (IOException | UnsupportedFlavorException e) {
            e.printStackTrace();
        }
        
        return "";
    }
}
