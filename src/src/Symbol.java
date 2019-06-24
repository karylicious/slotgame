/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.swing.ImageIcon;

/**
 *
 * @author Carla
 */

//This class represents one of the symbols in a reel and implements the ISymbol interface
public class Symbol implements ISymbol {
    private int value;
    private ImageIcon symbImage;
    
    Symbol(int _value, ImageIcon _symbImage){
        value = _value;
        symbImage = _symbImage;        
    }
    
    public void setImage(ImageIcon _symbImage){symbImage = _symbImage;}
    
    public ImageIcon getImage(){return symbImage;}
    
    public void setValue(int _value){value = _value;}
    
    public int getValue(){return value;}
    
    public boolean compareTo(String imgPath){
        return (symbImage.toString().equals(imgPath))? true:false;
    }
    
    public String toString(){
        return "Symbol: " + symbImage.toString() + " Value: " + Integer.toString(value);
    }
}