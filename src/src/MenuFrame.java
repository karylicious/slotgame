/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 *
 * @author Carla
 */

// This JFrame shows a menu 
public class MenuFrame extends MainFrame implements ActionListener{
    private JLabel lbLogo =  new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/images/logo.png"));
    private JButton btnNewGame =  createButton("New Game", 50, true);
    private JButton btnStatist=  createButton("Statistics", 50, true);
    private JButton btnHelp =  createButton("How To Play", 50, true);
    private JButton btnExitGame =  createButton("Exit", 50, true);       
    private JScrollPane jScrollPane;
    private ArrayList<GameResult> resultsList = new ArrayList(); 
    private int currCredits, credBett = 0;
    
    MenuFrame(ArrayList<GameResult> _gamesResults, int _currCredits){
        super();       
        resultsList = _gamesResults;  
        currCredits = _currCredits;
        
        //Setting Elements
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        lbLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewGame.addActionListener(this);
        btnStatist.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStatist.addActionListener(this);
        btnHelp.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHelp.addActionListener(this);
        btnExitGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExitGame.addActionListener(this);

        //Adding Elements
        mainPanel.add(lbLogo); 
        mainPanel.add(Box.createRigidArea(new Dimension(0,50))); // This adds space between components
        mainPanel.add(btnNewGame);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20))); // This adds space between components
        mainPanel.add(btnStatist);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20))); // This adds space between components
        mainPanel.add(btnHelp);
        mainPanel.add(Box.createRigidArea(new Dimension(0,20))); // This adds space between components
        mainPanel.add(btnExitGame);       
       
        jScrollPane=new JScrollPane(mainPanel); // This makes the panel scrollable
        jScrollPane.getViewport().setOpaque(false); // The JViewport provides a window, or "viewport" onto a data source
        jScrollPane.setOpaque(false);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // This will always show the vertical scrollbar         
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // This will always show the horizontal scrollbar 
    
        add(jScrollPane);   
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e){ 
        if(e.getSource()==btnNewGame){
            new NewGameFrame(resultsList, currCredits);
            setVisible(false);
        }
        else if(e.getSource()==btnStatist)
            new StatistDialog(MenuFrame.this,"Statistics",true,resultsList);
        
        else if(e.getSource()==btnHelp)
            new HelpDialog(MenuFrame.this,"How To Play");     
        
        else if(e.getSource()==btnExitGame){
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit game?","Exit confirmation",JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                System.exit(0); // This will stops the application
        }  
    }         
}