/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kelesit.survivalgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author furkan
 */
public class SurvivalMain {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        //Hero hero = new Hero(100, 20, 200);
        Hero hero = new Hero();
        List<String> allLines = null;
        String[] lines;
        //HashMap<Integer, String> hmap = new HashMap<Integer, String>();

        
        //int z = 0; 
        
        Scanner s = new Scanner(System.in);
        System.out.println("Okunmasini istediginiz dosya adini veya dosya yolunu giriniz(input1 veya input2)...:");
        String dosya = s.nextLine();
        
        try {   //Dosyadan okuma işlemi yapıyor. Satır satır.
            allLines = Files.readAllLines(Paths.get(dosya + ".txt"));
            /*for (String line : allLines) {
                //hmap.put(z, line);  //Sırayla her satırı hashmap içine kaydediyor.
                //System.out.println("HASH " + hmap.get(z));
                z++;
            }*/
	} catch (IOException e) {                
            System.out.println(e.toString() + " DOSYA BULUNAMADI VEYA ACILAMADI");
            return;
	}

        for(int m = 0; m<allLines.size();m++){  //Hashmap'te Hero özellikleri okuyup tanımlama işlemleri yapılıyor.
            //hmap.get(m);
            if(allLines.get(m).contains("Hero")&&allLines.get(m).contains("hp")){ //Hashmap in m. elemanı "Hero" ve "hp" kelimelerini içeriyorsa
  
                lines = allLines.get(m).split("\\s+"); //Split -> Boşluklara göre liste içindeki cümleyi diziye kadar
                
                hero.setHp(Integer.parseInt(lines[2])); //İçeren cümledeki 3.kelimeyi al "Hero"nun canı yap
                                               
            }
            if(allLines.get(m).contains("Hero")&&allLines.get(m).contains("attack")){ //"Hero ve attack içeren cümleyi al"
                lines = allLines.get(m).split("\\s+"); 
                
                hero.setPower(Integer.parseInt(lines[3])); //Cümlenin 4.kelimesini "Hero"nun canı yap
            }
            if(allLines.get(m).contains("are")&&allLines.get(m).contains("meters")){ //Resource uzaklığını almak için
                lines = allLines.get(m).split("\\s+"); 
                
                hero.setDistance(Integer.parseInt(lines[2]));
            }
 
        }
        
        List<Enemy> enemyList = new ArrayList<>(); //Enemy tipinde bir enemy listesi. Birden fazla enemy olduğu için
                                                   //Oluşturulan enemy'ler burada tutulacak.

        
        for(int m = 0; m<allLines.size();m++){  
            //hmap.get(m);
            if(allLines.get(m).contains("There is a")){
  
                lines = allLines.get(m).split("\\s+"); //Split -> Boşluklara göre liste içindeki cümleyi diziye kadar

                int distance = Integer.parseInt(lines[6]); //Enemy'lerin uzaklığını buluyor.
                
                Enemy enemy = new Enemy(lines[3],distance); //Yeni enemy oluşturup lines[3] ile enemy nin adını ekliyor.
                enemyList.add(enemy); //Listeye ekliyor.
                                   
            }           
        }

        
        for(int m = 0; m<allLines.size(); m++){
            for(int r = 0; r<enemyList.size(); r++){
                //System.out.println("LISTE...: " + list.get(r).getName() + " " + list.get(r).getEnemyCount());
                if(allLines.get(m).contains(enemyList.get(r).getName())&&allLines.get(m).contains("hp")){
                    lines = allLines.get(m).split("\\s+"); 

                    enemyList.get(r).setHp(Integer.parseInt(lines[2])); //Enemy'lerin can değerlerini ekliyor.     
                    
                }
                if(allLines.get(m).contains(enemyList.get(r).getName())&&allLines.get(m).contains("attack")){
                    lines = allLines.get(m).split("\\s+"); 

                    enemyList.get(r).setPower(Integer.parseInt(lines[3]));  //Enemy'lerin attack gücü değerlerini ekliyor.
                }                
            }
        }
        
     
        
        
        /*for(int r = 0; r<enemyList.size(); r++){
            System.out.println("DUSMAN " + (r+1) + " --> " + enemyList.get(r).getName() + " HP..: " + enemyList.get(r).getHp() + " ATTACK...: " + enemyList.get(r).getPower() + " KONUM...: " + enemyList.get(r).getDistance());
            //System.out.println("SAYI " + list.get(r).getEnemyCount()); 
        }*/

        //Dosyaya yazma işlemleri başlangıcı
        Scanner sn = new Scanner(System.in);
        System.out.println("Hangi dosyaya yazilmasini istersiniz? Dosya adi giriniz...:");
        String dosya2 = sn.nextLine();
        
        File fout = new File(dosya2 + ".txt");
	FileOutputStream fos = new FileOutputStream(fout);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        int hepsiOldu = Enemy.enemyCount; //Tüm enemy'lerin öldüğünü kontrol etmek için
        System.out.println("HERO STARTED JOURNEY WITH " + hero.getHp() + " HP!");
        bw.write("HERO STARTED JOURNEY WITH " + hero.getHp() + " HP!");
	bw.newLine();
        for(int i=0; i<=hero.getDistance(); i++){
            
            if(hero.olduMu()){ //Hero öldüyse döngü tamamlanıyor ve durduruluyor.
                break;
            }
            for(int j = 0; j<Enemy.enemyCount; j++){
                if(i==enemyList.get(j).getDistance()){
                    
                    //Bu if koşulu sağlanıyorsa Hero, Enemy'den üstün demektir ve enemy'yi öldürür.
                    if((enemyList.get(j).getHp()/hero.getPower())<(hero.getHp()/enemyList.get(j).getPower())){ //Enemy'nin canı bölü Hero'nun hasarı küçükse Hero'nun canı bölü enemy'nin gücünden
                        hero.damaged(enemyList.get(j).getPower()*(enemyList.get(j).getHp()/hero.getPower())); //Hero hasar alır. Hero enemy'ye kaç vuruş yaptıysa ölene kadar o kadar vuruş kadar da hasar alır.
                        System.out.println("HERO DEFEATED " + enemyList.get(j).getName() + " WITH " + hero.getHp() + " HP REMAINING");
                        bw.write("HERO DEFEATED " + enemyList.get(j).getName() + " WITH " + hero.getHp() + " HP REMAINING"); //Dosyaya yazar
                        bw.newLine();
                        hepsiOldu--;
                        if(hepsiOldu==0){ //Eğer bütün enemy'ler öldüyse Hero kurtulmuştur.
                            System.out.println("HERO SURVIVED");
                            bw.write("HERO SURVIVED!");
                            bw.newLine();
                        }
                    }
                    else{  //Eğer ki yukarıdaki if koşulu gerçekleşmiyorsa karşılaşılan enemy daha güçlü demektir.
                        int heroKacHasarYedi = hero.getHp()/enemyList.get(j).getPower(); //Bu değişkenle Hero'nun ölmeden önce enemy'ye kaç hasar bırakacağını tutuyoruz.
                        hero.damaged(enemyList.get(j).getPower()*(enemyList.get(j).getHp()/hero.getPower())); //Hero hasar alıyor.
                        enemyList.get(j).hasarAl(hero.getPower()*heroKacHasarYedi); //Enemy hasar alıyor.
                        System.out.println(enemyList.get(j).getName() + " DEFEATED HERO with " + enemyList.get(j).getHp() + " HP REMAINING ");
                        System.out.println("Hero is Dead!!" + " Last seen at position " + hero.getKonum() + "!!");
                        bw.write(enemyList.get(j).getName() + " DEFEATED HERO with " + enemyList.get(j).getHp() + " HP REMAINING");
                        bw.newLine();
                        bw.write("Hero is Dead!!" + " Last seen at position " + hero.getKonum() + "!!");
                        bw.newLine(); 
                    }
                }                   
            }
            hero.move(); //Hero harekt ediyor.
        }

        bw.close();
        
    }
    
}
