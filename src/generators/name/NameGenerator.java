/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.name;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Die Axt
 */
public class NameGenerator {
    
    public NameGenerator() {
    }
    
    List<String> syllable1 = null;
    List<String> syllable2 = null;
    List<String> monsterSy1 = null;
    List<String> monsterSy2 = null;
    String[] title1 = {"Rescuer","Hero","Saviour","Hope","Preserver","Builder","Master"}; //6
    String[] title1Monster = {"Slayer","Devourer","Digester","Destroyer","Polluter","Master", "Bane"}; //6
    String[] title1Attribute = {"one","two","three","four","five"}; //4
    String[] title1Skill = {"Barbarian","Warrior","Chosen","Legendary Swordsman","Legendary Archer","Ranger"}; //5
    String[] title1SkillMonster = {"Barbarian","Monstrousity","Kingslayer","Slaher","Ripper"}; //4
    String[] title2 = {"pesants","kings","queens","borderlands"}; //3
    String[] title2Attribute = {"Legged","Armed","Eyed"};
    //List<String> title1 = null;
    //List<String> title2 =null;
    String name;
    Random r = new Random();    
    boolean monsterSy;
    
    public String randomName(boolean monster) {
        int nrS=r.nextInt(5);
        int coin;
        getLists();
        monsterSy=!monster;
        if(monster) {
            name=random1stSy_Monster();
        } else {
            name=random1stSy();
        }
        for(int i = 0; i<nrS ; i++) {
            coin=r.nextInt(99);
            checkSy(coin,monster);
        }
        if(monster && !monsterSy) {
            name=name+random2ndSy_Monster();
        }
        coin = r.nextInt(99);
        if(coin>90) {
            name = name+randomTitle(monster);
        }
        return name;
    }
    public String randomName(boolean monster, int nrS) {
        int coin;
        getLists();
        monsterSy=!monster;
        if(monster) {
            name=random1stSy_Monster();
        } else {
            name=random1stSy();
        }
        for(int i = 0; i<nrS ; i++) {
            coin=r.nextInt(99);
            checkSy(coin,monster);
        }
        if(monster && !monsterSy) {
            name=name+random2ndSy_Monster();
        }
        coin = r.nextInt(99);
        if(coin>90) {
            name = name+randomTitle(monster);
        }
        return name;
    }
    private void getLists() {
        syllable1 = new ArrayList();
        syllable2 = new ArrayList();
        String s;
        try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\NameGen\\1stSyllable.txt"))) {
            while((s=br.readLine()) != null){
                syllable1.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error "+e.getMessage());
        }        
        try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\NameGen\\Syllable.txt"))) {
            while((s=br.readLine()) != null){
                syllable2.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error "+e.getMessage());
        }
    }
    private String random1stSy() {
        int rd=r.nextInt(syllable1.size()-1);
        return syllable1.get(rd);
    }
    private String random2ndSy() {
        int rd=r.nextInt(syllable2.size()-1);
        return syllable2.get(rd); 
    }
    private String random1stSy_Monster() {
        if(monsterSy1==null) {
            monsterSy1 = new ArrayList();
            try (BufferedReader reader = new BufferedReader(new FileReader(".\\Data\\NameGen\\monsterSy1.txt"))) {
                while (reader.readLine() != null) {
                    monsterSy1.add(reader.readLine());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error"+e.getMessage());
            }
        }
        int rd=r.nextInt(monsterSy1.size()-1);
        return monsterSy1.get(rd);
    }
    private String random2ndSy_Monster() {
        if(monsterSy2==null) {
            monsterSy2 = new ArrayList();
            try (BufferedReader reader = new BufferedReader(new FileReader(".\\Data\\NameGen\\monsterSy2.txt"))) {
                while (reader.readLine() != null) {
                    monsterSy2.add(reader.readLine());
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error"+e.getMessage());
            }
        }
        int rd=r.nextInt(monsterSy2.size()-1);
        return monsterSy2.get(rd);
    }
    private String randomTitle(boolean monster) {
        int coin = r.nextInt(99);
        return checkTitle(coin,monster);
    }
    
    private void checkSy(int coin, boolean monster) {
        if(coin>86) {
            if(monster) {
                name=name+" "+checkSy1_Monster();
            } else {
                name=name+" "+random1stSy();
            }
        } else if(coin>70) {
            if(monster) {
                name=name+"-"+checkSy1_Monster();
            } else {
                name=name+"-"+random1stSy();
            }
        } else if(coin>54) {
            if(monster) {
                name=name+"'"+checkSy1_Monster();
            } else {
                name=name+"'"+random1stSy();
            }
        } else {
            if(monster) {
                name=name+checkSy2_Monster();
            } else {
                name=name+random2ndSy();
            }
        }
    }
    private String checkSy1_Monster() {
        if(r.nextBoolean()) {
            return checkSy1_Monster_M();
        } else {
            return checkSy1_Monster_N();
        }
    }
    private String checkSy1_Monster_M() {
        if(r.nextBoolean()) {
            return random1stSy_Monster();
        } else {
            return random1stSy_Monster()+random2ndSy_Monster();
        }
    }
    private String checkSy1_Monster_N() {
        if(r.nextBoolean()) {
            return random1stSy();
        } else {
            return random1stSy()+random2ndSy_Monster();
        }
    }
    private String checkSy2_Monster() {
        if(r.nextBoolean()) {
            return checkSy2_Monster_M();
        } else {
            return checkSy2_Monster_N();
        }
    }
    private String checkSy2_Monster_M() {
        if(r.nextBoolean()) {
            return checkSy2_Monster_M_pure();
        } else {
            return random2ndSy_Monster()+random2ndSy();
        }
    }
    private String checkSy2_Monster_M_pure() {
        if(r.nextBoolean()) {
            return random2ndSy_Monster();
        } else {
            return random2ndSy_Monster()+random2ndSy_Monster();
        }
    }
    private String checkSy2_Monster_N() {
        if(r.nextBoolean()) {
            return random2ndSy();
        } else {
            return random2ndSy()+random2ndSy_Monster();
        }
    }
    
    private String checkTitle(int coin, boolean monster) {
        if(coin>75) {
            return ", "+checkTitle_deed(coin,monster);
        } else if(coin>5) {
            if(monster) {
                return " the "+title1SkillMonster[r.nextInt(4)];
            } else {
                return " the "+title1Skill[r.nextInt(5)];
            }
        } else {
            return " the "+checkTitle_Attribute(monster);
        }
    }
    private String checkTitle_deed(int coin, boolean monster) {
        if(monster) {
            if(coin>66) {
                return title1Monster[r.nextInt(5)]+" of "+title2[r.nextInt(3)];
            } else if(coin>33) {
                return title1Monster[r.nextInt(5)]+" of "+randomName(false);
            } else {
                return title1[r.nextInt(6)]+" of "+randomName(true);
            }
        } else {
            if(coin>94) {
                return title1[r.nextInt(6)]+" of "+randomName(false);
            } else {
                return title1[r.nextInt(6)]+" of "+title2[r.nextInt(3)];
            }
        }
    }
    private String checkTitle_Attribute(boolean monster) {
       if(monster) {
           return title1Attribute[r.nextInt(4)]+" "+title2Attribute[r.nextInt(2)]+" "+title1SkillMonster[4];
       } else {
           return title1Attribute[r.nextInt(4)]+" "+title2Attribute[r.nextInt(2)]+" "+title1Skill[5];
    }
    }
    
}
