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
public interface ISymbol {
    public void setImage(ImageIcon symbImage);
    public ImageIcon getImage();
    public void setValue(int value); 
    public int getValue();
}