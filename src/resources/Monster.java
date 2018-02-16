/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Die Axt
 */
public class Monster implements Serializable{
    public Monster(int year, String advNr) {
        this.firstAppearence=year;
        this.slayer=NONE;
        this.defeated=false;
        this.connectedQuests=new ArrayList();
        this.connectedQuests.add(advNr);
        this.killed = new ArrayList();
    }
    
    public static final int NONE = 0;
    
    String id;
    String name = null;
    String course;
    int slayer;
    int firstAppearence;
    int[] threshold; //0 phy | 1 men | 2 soc | 3 mag | 4 reached goals necessary to defeat Monster
    boolean[] thresholdsReached={false,false,false,false};
    boolean defeated;
    List<String> connectedQuests = null;
    List<Integer> killed = null;
    
    public void setID(String newID) {
        this.id=newID;
    }
    public String getID() {
        return id;
    }
    public void setName(String pName) {
        this.name=pName;
    }
    public String getName() {
        return name;
    }
    public void setCourse(String cNr) {
        this.course=cNr;
    }
    public String getCourseNr() {
        return course;
    }
    public void setSlayer(int hero) {
        this.slayer=hero;
    }
    public int getSlayer() {
        return slayer;
    }
    public void setFirstSighting(int year) {
        this.firstAppearence=year; 
    }
    public int getFirstSightign() {
        return firstAppearence;
    }
    public void slay() {
        this.defeated=true;
    }
    public void setThreshold(int tNr, int threshold) {
        this.threshold[tNr]=threshold;
    }
    public void setThresholds(int[] thresholds) {
        this.threshold=thresholds;
    }
    public int getThreshold(int tNr) {
        return threshold[tNr];
    }
    public int[] getThresholds() {
        return threshold;
    }
    public void achieveThreshold(int tNr, boolean achieve) {
        this.thresholdsReached[tNr]=achieve;
    }
    public boolean isThresholdReached(int tNr) {
        return thresholdsReached[tNr];
    }
    public boolean[] getAllThresholdReached() {
        return thresholdsReached;
    }
    public boolean isSlayed() {
        return defeated;
    }
    public void addQuest(String questNr) {
        this.connectedQuests.add(questNr);
    }
    public String getQuest(int index) {
        return connectedQuests.get(index);
    }
    public List<String> getQuests() {
        return connectedQuests;
    }
    public void addKill(int hero) {
        this.killed.add(hero);
    }
    public int getKill(int hero) {
        return killed.get(hero);
    }
    public List<Integer> getKills() {
        return killed;
    }
    
}
