/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Carla
 */

// This class represents the information of each game played by the user. 
// It informs when the user starts the game, whether the user won or lost the game
// It also informs the credits netted 
public class GameResult{
    private char result; // This informs whether the user won or lost the game
    private int credits = 0;
    private String dateTimeStarted ="";
    
    GameResult(int _credits, char _result){
        credits = _credits;
        result = _result;
        dateTimeStarted = new SimpleDateFormat("HH:mm dd/MM/YYYY").format(Calendar.getInstance().getTime());
    }    
    public void setResult(char _result){ result = _result; }  
    
    public char getResult(){ return result; }
    
    public void setCredits(int _credits){ credits = _credits; } 
    
    public int getCredits(){ return credits; }    
    
    public String getDateTimeStarted(){ return dateTimeStarted; }  
    
    public String toString(){
        return "DateTime: " + dateTimeStarted + " Result: " + result + " Credits: " + credits;
    }
}