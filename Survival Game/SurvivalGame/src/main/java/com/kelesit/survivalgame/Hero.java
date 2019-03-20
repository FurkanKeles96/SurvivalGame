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
public class Hero implements HeroInterface {
    
    private int hp;
    private int power;
    private int distance;
    private int konum;
    private boolean isDead=false;
    
    public Hero(int hp, int power, int distance){
        this.hp=hp;
        this.power=power;
        this.distance=distance;
        this.konum=0;
    }

    public Hero() {
        this.konum=0;
    }

    @Override
    public int move() {
        return konum++;
    }

    @Override
    public int damaged(int hasar) {
        this.hp = hp - hasar;
        return hp;
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public void setDistance(int distance) {
        this.distance=distance;
    }

    @Override
    public int getKonum() {
        return konum;
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
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public boolean olduMu() {
        if(this.hp<=0){
            isDead=true;
        }
        else
            isDead=false;
        
        return isDead;
    }

}
