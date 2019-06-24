/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Carla
 */

// This class contains a list of 6 instances of Symbol objects 
public class Reel{    
    private static Symbol[] symbList = new Symbol[6];
    private int[] numAlreadyGeneratedList = {-1,-1,-1,-1,-1,-1};
    private boolean emptyList = true;  
    
    Reel(){ 
        // This randomly populate the array and allows the position of the symbols change every time a new reel is created
        symbList[generateRandNum()] = new Symbol(6,new ImageIcon(System.getProperty("user.dir")+"/src/images/bell.png"));
        symbList[generateRandNum()] = new Symbol(2,new ImageIcon(System.getProperty("user.dir")+"/src/images/cherry.png"));
        symbList[generateRandNum()] = new Symbol(3,new ImageIcon(System.getProperty("user.dir")+"/src/images/lemon.png"));
        symbList[generateRandNum()] = new Symbol(4,new ImageIcon(System.getProperty("user.dir")+"/src/images/plum.png"));
        symbList[generateRandNum()] = new Symbol(7,new ImageIcon(System.getProperty("user.dir")+"/src/images/redseven.png"));
        symbList[generateRandNum()] = new Symbol(5,new ImageIcon(System.getProperty("user.dir")+"/src/images/watermelon.png"));
    }
    
    public Symbol[] spin(){return symbList;}
   
    private int generateRandNum(){
        int randNumber = new Random().nextInt(6); // This will generate a random number between 0-5    
        if(emptyList == true){             
            numAlreadyGeneratedList[0] = randNumber;
            emptyList = false;
            return randNumber;
        }
        else{
            boolean validNumb = false;
            while(!validNumb){ // For every generated number, it will check whether the number was already generated. If so, it will generate again
                randNumber = new Random().nextInt(6); // This will generate a random number between 0-5    
                boolean alreadyGenerated = false;
                int i = 0; // This will restart the for loop
                for(;i < numAlreadyGeneratedList.length; i++){
                    if(numAlreadyGeneratedList[i] == randNumber){
                        alreadyGenerated = true;
                        break;
                    }
                    if(numAlreadyGeneratedList[i] == -1) // This means the for loop has reached an unsigned position
                        break;
                }
                if(!alreadyGenerated){
                    numAlreadyGeneratedList[i] = randNumber;
                    validNumb = true;
                }
            }
        }
        return randNumber;            
    }
    // This will return the value of the matched symbol
    public static int getPayout(String imgPath){
        for(int i = 0; i < symbList.length; i++){
            if (symbList[i].compareTo(imgPath))
                return symbList[i].getValue();
        }
        return -1;
    }
}