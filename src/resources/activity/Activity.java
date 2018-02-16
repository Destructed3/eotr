/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.activity;

import java.io.Serializable;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public abstract class Activity implements Serializable {
    
    public Activity(int pHost, int pyear) {
        resetTimeTable();
        this.hostNr=pHost;
        this.active=true;
        this.year=pyear;
    }
    public Activity() {
        resetTimeTable();
        this.active=true;
    }
    
    public void setRegion(Region re) {
        this.region=re;
    }
    public Region getRegion() {
        return region;
    }
        
    private void resetTimeTable() {
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                timeTable[row][column]=false;
            }
        }
    }
    public void setName(String s) {
        this.name=s;
    }
    public String getName() {
        return name;
    }
    public void setHost(int t) {
        this.hostNr=t;
    }
    public int getHostNr() {
        return hostNr;
    }
    public void setTimeTable(int hour, int day, boolean value) {
        this.timeTable[hour][day]=value;
    }
    public boolean[][] getTimeTable() {
        return timeTable;
    }
    public boolean getTimeTableHourUsed(int hour, int day) {
        return timeTable[hour][day];
    }
    public String getID() {
        return id;
    }
    public void setID(String aNr) {
        this.id=aNr;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean pAct) {
        this.active=pAct;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int pyear) {
        this.year=pyear;
    }
    public String get_JobHeritage() {
        return aObject;
    }
    public void set_AObject(String obj) {
        this.aObject=obj;
    }
    
    String id;
    String name;
    Region region;
    int hostNr;
    boolean[][] timeTable = new boolean[10][7]; //1 -> used | 0 -> free
    boolean active;
    int year;
    public String aObject; //For journal entry
}
