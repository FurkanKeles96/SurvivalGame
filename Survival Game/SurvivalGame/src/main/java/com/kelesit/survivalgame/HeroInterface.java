/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelesit.survivalgame;

/**
 *
 * @author furkan
 */
public interface HeroInterface {
       
    public int move();
    public int damaged(int hasar);
    public int getDistance();
    public void setDistance(int distance);
    public int getKonum();
    public int getHp();
    public void setHp(int hp);
    public int getPower();
    public void setPower(int power);
    public boolean olduMu();
    
}
