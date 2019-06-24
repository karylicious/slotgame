/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


/**
 *
 * @author Carla
 */

// This JFrame allows the user play the game
public class NewGameFrame extends MainFrame implements ActionListener{
    private JPanel containerPanel = new JPanel(new BorderLayout());
    private JPanel contentPanel = new JPanel();
    private JPanel firstPanel = new JPanel();
    private JPanel row1FirstPanel = new JPanel(new BorderLayout());
    private JPanel row2FirstPanel = new JPanel(new BorderLayout());
    private JPanel secPanel = new JPanel(new GridLayout(1,3));
    private JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));    
    private JPanel fourthPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));   
    private JScrollPane jScrollPane;    
    private JLabel lbCred = new JLabel("Credits");    
    private JLabel lbCredVal = new JLabel();
    private JLabel lbLogo = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/images/logo-min.png"));
    private JLabel lbBett = new JLabel("Betting");
    private JLabel lbBettVal = new JLabel("0");    
    private JButton btnAddCoin =  createButton("Add Coin", 31, false);
    private JButton btnBetOne=  createButton("Bet One", 31, false);
    private JButton btnBetMax =  createButton("Bet Max", 31, false);
    private JButton btnReset =  createButton("Reset", 31, false);
    private JButton btnSpin =  createButton("Spin", 31, false);    
    private JButton btnStatist =  createButton("Statistics", 31, false);
    private JButton btnBack =  createButton("Menu", 31, false);  
    private JButton btnReel1 = new JButton (new ImageIcon(System.getProperty("user.dir")+"/src/images/coins.jpeg"));
    private JButton btnReel2 = new JButton (new ImageIcon(System.getProperty("user.dir")+"/src/images/coins.jpeg"));
    private JButton btnReel3 =  new JButton (new ImageIcon(System.getProperty("user.dir")+"/src/images/coins.jpeg"));    
    private ArrayList<GameResult> resultsList = new ArrayList(); 
    private int currCredits, credBett = 0;
    private boolean btnBetMaxPressed = false;
    private static final int BET_MAX = 3; 
    private Thread thread1 = new Thread();
    private Thread thread2 = new Thread();
    private Thread thread3 = new Thread();
    private boolean wasBtnSpinPressed = false;
    
    NewGameFrame(ArrayList<GameResult> _gamesResults, int _currCredits){
        super();
        resultsList = _gamesResults;
        currCredits = _currCredits; 
        
        //Setting Elements
        mainPanel.setLayout(new BorderLayout());
        
        containerPanel.setOpaque(false);
        
        contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);        
        contentPanel.setBorder(new EmptyBorder(0,100,0,100)); // This adds margin on the left and right side of the panel (top,left,bottom,right)   
        
        firstPanel.setLayout(new BoxLayout(firstPanel,BoxLayout.Y_AXIS));     
        firstPanel.setBackground(Color.black);
        firstPanel.setMaximumSize( new Dimension(Integer.MAX_VALUE,200));        
        firstPanel.setBorder(new EmptyBorder(0,50,10,50)); // This adds margin on the left, bottom, and right side of the panel (top,left,bottom,right)      
       
        row1FirstPanel.setOpaque(false);
        row2FirstPanel.setOpaque(false);
        
        secPanel.setOpaque(false);
        secPanel.setBorder(BorderFactory.createLoweredBevelBorder());
                
        thirdPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirdPanel.setBackground(Color.black);
        thirdPanel.setBorder(new EmptyBorder(30,0,5,0)); // This adds margin on the top and bottom of the panel (top,left,bottom,right)        
             
        fourthPanel.setMaximumSize( new Dimension(Integer.MAX_VALUE,150));
        fourthPanel.setOpaque(false);
       
        lbCred.setOpaque(false);
        lbCred.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
        lbCred.setForeground(Color.orange);        
        
        lbCredVal.setText(Integer.toString(currCredits));
        lbCredVal.setOpaque(false);
        lbCredVal.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        lbCredVal.setForeground(Color.GREEN);        
        lbCredVal.setBorder(new EmptyBorder(0,40,0,0)); // This adds margin on the left side of the label (top,left,bottom,right)
        
        lbLogo.setOpaque(false);       
        lbLogo.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
        lbLogo.setForeground(Color.orange);
        lbLogo.setHorizontalAlignment(JLabel.CENTER); // This will center this JLabel
        
        lbBett.setOpaque(false);       
        lbBett.setFont(new Font("Comic Sans MS", Font.PLAIN, 34));
        lbBett.setForeground(Color.orange);
        
        lbBettVal.setOpaque(false);       
        lbBettVal.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        lbBettVal.setForeground(Color.GREEN);
        lbBettVal.setBorder(new EmptyBorder(0,0,0,50)); // This adds margin on the right side of the label (top,left,bottom,right)        
        
        btnReel1.setPreferredSize(new Dimension(400,400));
        btnReel1.addActionListener(this);             

        btnReel2.setPreferredSize(new Dimension(400,400));
        btnReel2.addActionListener(this);   
        
        btnReel3.setPreferredSize(new Dimension(400,400));
        btnReel3.addActionListener(this);
        
        btnAddCoin.setBackground(new Color(92, 150, 245));
        btnAddCoin.addActionListener(this);
        
        btnBetOne.setBackground(new Color(92, 150, 245));
        btnBetOne.addActionListener(this);
        
        btnBetMax.setBackground(new Color(92, 150, 245));
        btnBetMax.addActionListener(this);
        
        btnReset.setBackground(new Color(92, 150, 245));
        btnReset.addActionListener(this);
        
        btnSpin.setBackground(Color.red);
        btnSpin.addActionListener(this);
        
        btnStatist.addActionListener(this);
        
        btnBack.addActionListener(this);
        
        // Adding Elements
        row1FirstPanel.add(lbCred,  BorderLayout.WEST); 
        row1FirstPanel.add(lbLogo,  BorderLayout.CENTER);
        row1FirstPanel.add(lbBett,  BorderLayout.EAST);  
       
        row2FirstPanel.add(lbCredVal,  BorderLayout.WEST); 
        row2FirstPanel.add(lbBettVal,  BorderLayout.EAST); 
       
        firstPanel.add(row1FirstPanel);
        firstPanel.add(row2FirstPanel);
                
        secPanel.add(btnReel1, BorderLayout.LINE_START);
        secPanel.add(btnReel2, BorderLayout.CENTER);
        secPanel.add(btnReel3, BorderLayout.LINE_END);

        thirdPanel.add(btnAddCoin);
        thirdPanel.add(btnBetOne);
        thirdPanel.add(btnBetMax);
        thirdPanel.add(btnReset);
        thirdPanel.add(btnSpin);
                  
        fourthPanel.add(btnStatist);
        fourthPanel.add(btnBack);
        
        contentPanel.add(firstPanel);
        contentPanel.add(secPanel);
        contentPanel.add(thirdPanel);
        contentPanel.add(fourthPanel);       
       
        containerPanel.add(contentPanel);
       
        mainPanel.add(containerPanel);
        
        jScrollPane=new JScrollPane(mainPanel); // This makes the panel scrollable   
        jScrollPane.getViewport().setOpaque(false); // The JViewport provides a window, or "viewport" onto a data source
        jScrollPane.setOpaque(false);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //This will always show the vertical scrollbar
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //This will always show the horizontal scrollbar 

        add(jScrollPane);
        setVisible(true);
    }        
        
    public void actionPerformed(ActionEvent e){          
        if(e.getSource()==btnAddCoin){ // This lets the user to add a coin to the credit
            if(wasBtnSpinPressed){ // This forces the user to stop the spin first, before doing something else
                JOptionPane.showMessageDialog(new JFrame(),"While spinning you cannot add credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;                
            }
            lbCredVal.setText(Integer.toString(++currCredits));
        }
        else if(e.getSource()==btnBetOne){ // This allows the user to bet a single credit
            if(wasBtnSpinPressed){ // This forces the user to stop the spin first before doing something else
                JOptionPane.showMessageDialog(new JFrame(),"While spinning you cannot bet!","Message", JOptionPane.WARNING_MESSAGE);
                return;                
            }
            if(currCredits == 0 && credBett == 0){
                JOptionPane.showMessageDialog(new JFrame(),"You cannot play with no credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if(currCredits == 0){
                JOptionPane.showMessageDialog(new JFrame(),"There is no enough credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            lbCredVal.setText(Integer.toString(--currCredits));
            lbBettVal.setText(Integer.toString(++credBett));        
        }
        else if(e.getSource()==btnBetMax){ // This allows the user to add the maximum number of credits in the bet
            if(wasBtnSpinPressed){ // This forces the user to stop the spin first before doing something else
                JOptionPane.showMessageDialog(new JFrame(),"While spinning you cannot bet!","Message", JOptionPane.WARNING_MESSAGE);
                return;                
            }
            if(currCredits == 0 && credBett == 0){
                JOptionPane.showMessageDialog(new JFrame(),"You cannot play with no credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if(currCredits < BET_MAX){
                JOptionPane.showMessageDialog(new JFrame(),"There is no enough credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!btnBetMaxPressed){ // This prevents the user to press the bet max button more than once
                credBett += BET_MAX;
                currCredits -= BET_MAX;
                lbCredVal.setText(Integer.toString(currCredits));
                lbBettVal.setText(Integer.toString(credBett));  
                btnBetMaxPressed = true;
                btnBetMax.setBackground(Color.LIGHT_GRAY);
            }
            else 
                JOptionPane.showMessageDialog(new JFrame(),"You have already bet the maximum number of credits!","Message", JOptionPane.WARNING_MESSAGE);
        }
        else if(e.getSource()==btnReset){ // This allows the user to return the amount that he/she has bet to the credits that he/she still has
            if(wasBtnSpinPressed){  // This forces the user to stop the spin first before doing something else
                JOptionPane.showMessageDialog(new JFrame(),"While spinning you cannot reset your bet!","Message", JOptionPane.WARNING_MESSAGE);
                return;                
            }
            if(currCredits == 0 && credBett == 0){
                JOptionPane.showMessageDialog(new JFrame(),"You cannot play with no credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else{
                currCredits += credBett;
                credBett = 0;
                lbCredVal.setText(Integer.toString(currCredits));
                lbBettVal.setText(Integer.toString(credBett)); 
                if(btnBetMaxPressed){
                    btnBetMaxPressed = false;
                    btnBetMax.setBackground(new Color(92, 150, 245));
                }
            }
        } 
        else if(e.getSource()==btnSpin){ // This causes the three reels to spin  
            if(wasBtnSpinPressed) // This prevents the program crash by not creating new other threads  
                return;  
            
            if(currCredits == 0 && credBett == 0){
                JOptionPane.showMessageDialog(new JFrame(),"You cannot play with no credits!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else if(credBett == 0){
                JOptionPane.showMessageDialog(new JFrame(),"Please bet a credit first!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            else{
                wasBtnSpinPressed = true;
                thread1 = new Thread(new Runnable() {
                    public void run() {
                        while (!Thread.currentThread().isInterrupted()) {
                            int index = new Random().nextInt(6); // This will generate a random number between 0-5            
                            btnReel1.setIcon(new Reel().spin()[index].getImage()); // This shows different random sequence of symbols                              
                        }                        
                    }
                });
                thread1.start();
                
                thread2 = new Thread(new Runnable() {
                    public void run() {
                        while (!Thread.currentThread().isInterrupted()) {
                            int index = new Random().nextInt(6); // This will generate a random number between 0-5              
                            btnReel2.setIcon(new Reel().spin()[index].getImage()); // This shows different random sequence of symbols                              
                        }
                    }
                });
                thread2.start();
                
                thread3 = new Thread(new Runnable() {
                    public void run() {
                        while (!Thread.currentThread().isInterrupted()) {
                            int index = new Random().nextInt(6); // This will generate a random number between 0-5              
                            btnReel3.setIcon(new Reel().spin()[index].getImage()); // This shows different random sequence of symbols                            
                        }
                    }
                });
                thread3.start();
            }
        }
        else if(e.getSource()==btnStatist) // This allows the user to check his/her number of wins, losses, and the average number of credits that she/he has netted per game. 
            new StatistDialog(NewGameFrame.this, "Statistics", true, resultsList);        
        
        else if(e.getSource()==btnBack){ // This allows the user to returnt to menu
            if(wasBtnSpinPressed){ // This forces the user to stop the spin first before doing something else
                JOptionPane.showMessageDialog(new JFrame(),"Please stop the spin first!","Message", JOptionPane.WARNING_MESSAGE);
                return;                
            }
            new MenuFrame(resultsList, currCredits);
            setVisible(false);
        }  
        else if(wasBtnSpinPressed && ((e.getSource()==btnReel1) || (e.getSource()==btnReel2) || (e.getSource()==btnReel3))){
            thread1.interrupt();
            thread2.interrupt();
            thread3.interrupt();               
            new java.util.Timer().schedule( // This runs a function after a specific number of seconds
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        checkResult(); // This will display the result
                    }
                }, 
                600 
            );  
        }
    }
    
    private void checkResult(){       
        int prevCurrCredits = currCredits;
        // All if statements compare the image on each reels to find similarity
        // The currCredits variable receives the credits that the user bet multiplied by the credits that the symbols are worth
        if( btnReel1.getIcon().toString().equals(btnReel2.getIcon().toString()) && btnReel2.getIcon().toString().equals(btnReel3.getIcon().toString())) // they all match
            currCredits += Reel.getPayout(btnReel1.getIcon().toString()) * credBett;

        else if(btnReel1.getIcon().toString().equals(btnReel2.getIcon().toString()) && !btnReel2.getIcon().toString().equals(btnReel3.getIcon().toString())) // only two of them match
            currCredits += Reel.getPayout(btnReel1.getIcon().toString()) * credBett;

        else if(!btnReel1.getIcon().toString().equals(btnReel2.getIcon().toString()) && btnReel2.getIcon().toString().equals(btnReel3.getIcon().toString())) // only two of them match
            currCredits += Reel.getPayout(btnReel2.getIcon().toString()) * credBett;

         else if(!btnReel1.getIcon().toString().equals(btnReel2.getIcon().toString()) && btnReel1.getIcon().toString().equals(btnReel3.getIcon().toString())) // only two of them match
            currCredits += Reel.getPayout(btnReel3.getIcon().toString()) * credBett;

        if(currCredits != prevCurrCredits){
            lbCredVal.setText(Integer.toString(currCredits));
            JOptionPane.showMessageDialog(new JFrame(),"Congratulations! You won " + Integer.toString(currCredits) + " credits.");            
            resultsList.add(new GameResult(currCredits, 'w')); // This adds the results on the list
        }
        else{
            JOptionPane.showMessageDialog(new JFrame(),"You lose!");
            resultsList.add(new GameResult(credBett, 'l')); // This adds the results on the list
        }
        
        // This will 'Restart the game'
        credBett = 0;
        lbBettVal.setText(Integer.toString(credBett));
        if(btnBetMaxPressed){
            btnBetMaxPressed = false;
            btnBetMax.setBackground(new Color(92, 150, 245));
        }
        btnReel1.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/images/coins.jpeg"));
        btnReel2.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/images/coins.jpeg"));
        btnReel3.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/images/coins.jpeg")); 
        wasBtnSpinPressed = false;
    }}