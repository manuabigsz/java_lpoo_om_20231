/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.cc.lpoo.om.util;

import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author 20212pf.cc0010
 */
public class Util {
    public static void considerarEnterComoTab (Component comp) {  
        Set<AWTKeyStroke> keystrokes = comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);  
        Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke> (keystrokes);  
        newKeystrokes.add (AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));  
        comp.setFocusTraversalKeys (KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes);   
    } 
    
    public static void registraEnterNoBotao(JButton b) {  
        b.registerKeyboardAction(  
                b.getActionForKeyStroke(  
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),  
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),  
                JComponent.WHEN_FOCUSED);  
          
        b.registerKeyboardAction(  
                b.getActionForKeyStroke(  
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),  
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),  
                JComponent.WHEN_FOCUSED);  
    } 
}
