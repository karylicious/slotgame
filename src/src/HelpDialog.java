/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Carla
 */

// This is a modal JDialog which informs the user how to play the game
// As a modal JDialog, the user will not be able to interact outside the dialog while it is opened
public class HelpDialog extends JDialog implements ActionListener{    
    private JLabel mainPanel = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/images/dialg-bg.jpg"));
    private JLabel lbTitle = new JLabel("How To Play");
    private JLabel lbInfo = new JLabel();
    private String text;
    private JButton btnClose = MainFrame.createButton("Close",30,false);
    private JPanel scrollablePanel = new JPanel();
    private JScrollPane jScrollPane;
    
    HelpDialog(MenuFrame dialogOwner,String title){
        super(dialogOwner,title,true); // The parameter 'true' specifies the JDialog as modal    
        setSize(800,600); // It sets the size of the JDialog
        setLocationRelativeTo(dialogOwner); // This will center the JDialog over the owner
        setUndecorated(true); // This removes the JDialog's title bar
        
        // Setting Elements
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        
        lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        lbTitle.setForeground(Color.orange);
        lbTitle.setOpaque(false);
        
        lbInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        lbInfo.setForeground(Color.white);
        lbInfo.setOpaque(false);
        lbInfo.setBorder(new EmptyBorder(20,20,20,20));// This adds margin into the JLabel  by setting an empty border (top,left,bottom,right)
        text ="<html>To win a game, the symbols on two or all three reels must be the same when the reels are stopped. The payout in credits for each of the symbols is as follows.<br/><br/>" +                       
                        "Seven: 7<br/>Bell: 6<br/>Watermelon: 5<br/>Plum: 4<br/>Lemon: 3<br/>Cherry: 2<br/><br/>"+
                        "So, for example, say you start the game and you have your initial 10 credits. You bet 3 credits, so you now have 7 credits (your bet was deposited in the machine). You spin the reels, and when they stop, they are all watermelons. You have won 15 credits (the 3 you bet multiplied by the 5 credits that a watermelon is worth). The 15 credits are then added to your 7 credits, giving you a total of 22 credits.<br/>" +    
                        "When none of the reels match, you lose. The credits that you bet are left in the machine.<br/><br/>" +                
                        "A game is over after all three reels have been clicked and have stopped spinning. The results of the game will be displayed.<br/><br/>" +
                        "Note that you cannot play if your credit is zero or if your bet is zero.</html>";
        lbInfo.setText(text);   

        btnClose.setMaximumSize(new Dimension(240,50));
        btnClose.addActionListener(this);
        
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel,BoxLayout.Y_AXIS));
        scrollablePanel.setOpaque(false);  
        scrollablePanel.setPreferredSize(new Dimension(800,1100));         
        scrollablePanel.add(lbTitle);
        scrollablePanel.add(lbInfo);
        scrollablePanel.add(btnClose);          
             
        jScrollPane=new JScrollPane(scrollablePanel); // This makes the panel scrollable
        jScrollPane.getViewport().setOpaque(false); // The JViewport provides a window, or "viewport" onto a data source
        jScrollPane.setOpaque(false);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // This will always show the vertical scrollbar        
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // This will hide the horizontal scrollbar 
        jScrollPane.setPreferredSize(new Dimension(1800,1600));        
        
        mainPanel.add(jScrollPane);
        add(mainPanel);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){  
        setVisible(false);
    }
}