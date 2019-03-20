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
public interface EnemyInterface {

    public String getName();
    public void setName(String name);
    public int getHp();
    public void setHp(int hp);
    public int getDistance();
    public void setDistance(int distance);
    public void setIsDead(boolean isDead);
    public void setPower(int power);
    public int getPower();
    public int hasarAl(int dmg);
    public boolean olduMu();
    
}
