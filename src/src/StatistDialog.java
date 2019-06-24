/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Carla
 */

// This is a modal JDialog which allows the user to check his/her number of wins, losses, and the average number of credits that she/he has netted per game
// As a modal JDialog, the user will not be able to interact outside the dialog while it is opened
public class StatistDialog extends JDialog implements ActionListener{    
    private JLabel mainPanel = new JLabel(new ImageIcon(System.getProperty("user.dir")+"/src/images/dialg-bg.jpg"));
    private JLabel lbTitle = new JLabel("Statistics");
    private JButton btnSave =  MainFrame.createButton("Save Statistics",30,false);
    private JButton btnReset =  MainFrame.createButton("Reset",30,false);
    private JButton btnClose =  MainFrame.createButton("Close",30,false);
    private JPanel panelBtns =  new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
    private JPanel panelTable =  new JPanel();
    private JScrollPane jScrollPane;
    private JLabel lbInfo = new JLabel("<html>Total Wins: 0<br/>Total Losses: 0<br/>Average of Credits: 0</html>");
    private DefaultTableModel model;
    private JTable scrollableTable;
    private DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private String[] columns = {"Game", "Wins", "Losses", "Credits", "Date"};
    private String[][] data = {};
    private ArrayList<GameResult> resultsList = new ArrayList(); 
    private int totalWins = 0;
    private int totalLosses = 0;
    private int totalCredits = 0;
    private int avgCred = 0;
    
    StatistDialog(JFrame dialogOwner,String title,boolean modal,ArrayList<GameResult> _gamesResults){
        super(dialogOwner,title,modal);
        resultsList = _gamesResults;        
        
        setSize(800,600);
        setLocationRelativeTo(dialogOwner); // This will center the JDialog over the parent
        setUndecorated(true);
        
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        lbTitle.setForeground(new Color(255, 188, 3));
        lbTitle.setOpaque(false);
        
        lbInfo.setBorder(new EmptyBorder(20,20,20,20));// This adds margin on the top, left, bottom and right side of the JLabel (top,left,bottom,right) 
        lbInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        lbInfo.setForeground(Color.white);
        lbInfo.setOpaque(false);
        lbInfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        
        panelBtns.setOpaque(false);
        
        // The data array is receiving the results of each played game, in order to be used on the following JTable
        if(resultsList.size() > 0){
            int i=0;            
            data = new String[resultsList.size()][columns.length] ;
            for(GameResult currGame : resultsList){
                data[i][0] = Integer.toString(i+1);
                data[i][1] = (currGame.getResult() == 'w')? "x":"";
                data[i][2] = (currGame.getResult() == 'l')? "x":"";
                data[i][3] = Integer.toString(currGame.getCredits());
                data[i][4] = currGame.getDateTimeStarted();
                i++;
            }
        }
       
        model = new DefaultTableModel(data, columns){ 
            public boolean isCellEditable(int row, int column) {return false;}
        };
        
        scrollableTable = new JTable(model);
        scrollableTable.setOpaque(false);
        scrollableTable.setFont(new Font("Arial", Font.PLAIN, 20));
        scrollableTable.setFillsViewportHeight(true);
        scrollableTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollableTable.setShowGrid(false);
        
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer.setOpaque(false);
        centerRenderer.setForeground(Color.white);
        for (int i = 0; i < columns.length; i++)
            scrollableTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        // This will increase the width of the Date column
        scrollableTable.getColumnModel().getColumn(4).setPreferredWidth(200);

        jScrollPane = new JScrollPane(scrollableTable);
        jScrollPane.setPreferredSize(new Dimension(520,270));
        jScrollPane.setVerticalScrollBarPolicy(jScrollPane.VERTICAL_SCROLLBAR_ALWAYS);         
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setOpaque(false);
        
        panelTable.setOpaque(false);        
        
        if(resultsList.size() > 0) {            
            for (GameResult currGame: resultsList){
                totalCredits += currGame.getCredits();
                if(currGame.getResult() == 'w')
                    totalWins++;                    
                else if (currGame.getResult() == 'l')
                    totalLosses++;                     
            }            
            avgCred = totalCredits / resultsList.size();
            // If the average is positive, then the player has been, on average, winning more credits than losing. 
            // If it is negative, then the player has been, on average, losing more credits than winning.
            if (totalLosses > totalWins)
                avgCred = 0 - avgCred;  
            lbInfo.setText("<html>Total Wins: "+Integer.toString(totalWins)+"<br/>Total Losses: "+Integer.toString(totalLosses)+"<br/>Average of Credits: "+Integer.toString(avgCred)+"</html>");
        }
        btnSave.addActionListener(this);
        btnReset.addActionListener(this);
        btnClose.addActionListener(this);        
        
        panelBtns.add(btnSave);
        panelBtns.add(btnReset);
        panelBtns.add(btnClose);
        
        panelTable.add(jScrollPane);
        mainPanel.add(lbTitle);
        mainPanel.add(panelTable);
        mainPanel.add(lbInfo);
        mainPanel.add(panelBtns);
        
        add(mainPanel);
        setVisible(true);
    }    
            
    public void actionPerformed(ActionEvent e){  
        if(e.getSource()==btnSave){ 
            if(resultsList.size() == 0){
                JOptionPane.showMessageDialog(new JFrame(),"Your statistics is empty!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try{ // This will create a folder statistics (if it does not exit) and a file including the statistcs                
                File statistDir= new File(System.getProperty("user.dir")+"/src/statistics");
                statistDir.mkdir(); // This creates the statistics folder
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                File statistFile= new File(System.getProperty("user.dir")+"/src/statistics/"+timeStamp + ".txt");            
                FileWriter out = new FileWriter(statistFile);
                
                out.write("Total Wins: " + totalWins);
                out.write(System.getProperty("line.separator"));
                out.write("Total Losses: " + totalLosses);
                out.write(System.getProperty("line.separator"));
                out.write("Average of Credits: " + avgCred);   
                out.write(System.getProperty("line.separator"));
                out.write(System.getProperty("line.separator"));
                
                int i=1;
                for (GameResult currGame: resultsList){                    
                    if(currGame.getResult() == 'w')
                        out.write("Game " + i + " played at " + currGame.getDateTimeStarted() + " - You won. Number of credits netted " + currGame.getCredits() + ".");
                                        
                    else if (currGame.getResult() == 'l')
                        out.write("Game " + i + " played at " + currGame.getDateTimeStarted() + " - You lose. Number of credits netted " + currGame.getCredits() + ".");
                    
                    out.write(System.getProperty("line.separator"));
                    i++;
                } 
                out.close();  
            }
            catch(IOException ex){
                JOptionPane.showMessageDialog(new JFrame(),"An error has occured when saving the statistics","Error",JOptionPane.ERROR_MESSAGE);
            }  
            JOptionPane.showMessageDialog(new JFrame(),"Statistics saved!");
        }
        else if(e.getSource()==btnReset){
            if(resultsList.size() == 0){
                JOptionPane.showMessageDialog(new JFrame(),"Your statistics is empty!","Message", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int n = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset the statistcs?","Reset confirmation",JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION){ // This will empty the resultsList and the table
                resultsList.clear();
                DefaultTableModel dtm = (DefaultTableModel) scrollableTable.getModel();
                dtm.setRowCount(0);
                lbInfo.setText("<html>Total Wins: 0<br/>Total Losses: 0<br/>Average of Credits: 0</html>");
            }
        }
        else if(e.getSource()==btnClose)
            setVisible(false);        
    }
}