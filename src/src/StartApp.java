/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Carla
 */

// This class is the start point of the program
public class StartApp{   
    public static void main(String[] args){
        JFrame myFrame = new JFrame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When the close button is pressed the application will stop running
        myFrame.setMinimumSize(new Dimension(787,470));              
        myFrame.setLocationRelativeTo(null); // This centers the JFrame 
        myFrame.setUndecorated(true); // This removes the title bar    
        myFrame.add(new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/images/intro-bg.jpg")));
        myFrame.setVisible(true);
        
        new java.util.Timer().schedule( // This runs a function after a specific number of seconds
            new java.util.TimerTask() {
                @Override
                public void run() {
                    // Every time the game starts the initial credit is 10 
                    new MenuFrame(new ArrayList<GameResult>(), 10); 
                    myFrame.setVisible(false);
                }
            }, 
            3000 
        );        
    }
}