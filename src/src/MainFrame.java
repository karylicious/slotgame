/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 *
 * @author Carla
 */

// This class sets the preferable layout of a JFrame
public class MainFrame extends JFrame{
    protected JLabel mainPanel = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/images/main-bg.jpg")); 
    
    MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the close button is pressed the application will stop running
        setMinimumSize(new Dimension(800,200));              
        setExtendedState(JFrame.MAXIMIZED_BOTH); // This will maximise the JFrame
        setLocationRelativeTo(null); // This will center the JFrame 
        UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 18)); // This will set the message Font
        UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 18)); // This will set the button Font        
        mainPanel.setPreferredSize(new Dimension(700,900));
        add(mainPanel);        
    } 
    
    public static JButton createButton(String txt, int fontSize, boolean setMaxSize){
        JButton btn = new JButton(txt);
        btn.setBackground(Color.orange);
        btn.setForeground(Color.white);
        btn.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));
        btn.setOpaque(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(179,117,2), 3));
        if(setMaxSize)
            btn.setMaximumSize(new Dimension(420,80));
        else
            btn.setPreferredSize(new Dimension(240,50));
        btn.setFocusPainted(false);
        return btn;
    }
}