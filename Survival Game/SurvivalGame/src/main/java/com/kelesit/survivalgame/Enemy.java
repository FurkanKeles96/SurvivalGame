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
public class Enemy implements EnemyInterface{
    
    private int hp;
    private int distance;
    private boolean isDead = false;
    private int power;
    private String name;
    public static int enemyCount = 0;

    public Enemy(String name, int distance) {
        this.name=name;
        this.distance = distance;
        enemyCount++;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int getHp() {
        return hp;
    }
    
    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }
    
    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }
    
    @Override
    public int hasarAl(int dmg){
        this.hp = hp - dmg;
        return hp;
    }
    
    @Override
    public boolean olduMu(){
        
        if(this.hp==0||this.hp<0){
            isDead=true;
        }
        else
            isDead=false;
        
        return isDead;
    }

   
    
}
