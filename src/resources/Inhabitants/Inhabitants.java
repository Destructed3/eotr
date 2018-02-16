/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Inhabitants;


import resources.activity.ActivityAdventure;
import resources.activity.ActivityJob;
import resources.activity.ActivityCourse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import resources.FeatsBackground;
import resources.JournalEntry;

/**
 *
 * @author Die Axt
 */
public abstract class Inhabitants implements Serializable {
    public Inhabitants() {
        this.setInhabitant();
    }
    
    private void setInhabitant() {
        int[] pI = {0,0,0,0};
        attributes = pI;
        this.resetTimeTable();
        this.stayTime=0;
        this.workload=0;
        this.isFormer=false;
        this.isAlive=true;
        this.leaveReason=NONE;
        this.activities=new ArrayList();
        this.deeds=new ArrayList();
        this.journal=new ArrayList();
    }
    
    public void resetTimeTable() {
        if(timeTable==null) {
            timeTable = new String[10][7];
        }
        for(int h=0;h<10;h++) {
            for(int d=0;d<7;d++) {
                timeTable[h][d]="";
            }
        }
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setBckg(FeatsBackground bkg) {
        this.background=bkg;
    }
    public FeatsBackground getBckg() {
        return background;
    }
    public List<int[]> getDeeds() {
        return deeds;
    }
    public void addDeed(int[] d) {
        deeds.add(d);
    }
    public void setAttribute(int aNr, int aVal) {
        this.attributes[aNr]=aVal;
    }
    public void setAttributes(int[] pattributes) {
        this.attributes=pattributes;
    }
    public int getAttribute(int aNr) {
        return attributes[aNr];
    }
    public int[] getAttributes() {
        return attributes;
    }
    public String getTimeTableHour(int h, int day) {
        return timeTable[h][day];
    }
    public void setTimeTable(String[][] dat) {
        this.timeTable=dat;
    }
    public void setTimeTableHour(int h, int day, String nr) {
        this.timeTable[h][day]=nr;
    }
    public String[][] getTimeTable() {
        return timeTable;
    }
    
    public void addActivity(ActivityAdventure aa) {
        System.out.println("Add Adventure "+aa.getID()+" to Character "+this.getNumber());
        if(activities==null) {
            activities = new ArrayList();
        }
        activities.add(aa.getID());
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                this.timeTable[row][column]=aa.getID();
                System.out.println("Saved "+this.timeTable[row][column]+" at "+row+"|"+column);
            }
        }
        this.workload=50;
        System.out.println("Added activity "+aa.getID()+" to "+this.getNumber());
    }
    public void addActivity(ActivityCourse ac) {
        System.out.println("Add activity"+ac.getID()+" to "+this.getNumber());
        if(activities==null) {
            activities = new ArrayList();
        }
        activities.add(ac.getID());
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                if(ac.getTimeTableHourUsed(row, column)) {
                    this.timeTable[row][column]=ac.getID();
                    System.out.println("Saved "+this.timeTable[row][column]+" at "+row+"|"+column);
                } 
            }
        }
        this.workload=this.workload+ac.getDuration()*3;
        System.out.println("Added activity "+ac.getID()+" to "+this.getNumber());
        System.out.println("Workload of "+this.getNumber()+" is now "+this.workload);
    }
    public void addActivity(ActivityJob aj) {
        System.out.println("Add job "+aj.getID()+" to Tea "+this.getNumber()+" at day "+aj.getDay());
        if(activities==null) {
            activities = new ArrayList();
        }
        activities.add(aj.getID());
        int day = aj.getDay();
        for(int h=0;h<10;h++) {
            this.setTimeTableHour(h, day, aj.getID());
            System.out.println("Saved "+this.timeTable[h][day]+" at "+h+"|"+day);
        }
        this.workload=this.workload+10;
        System.out.println("Added activity "+aj.getID()+" to "+this.getNumber());
        System.out.println("Workload of "+this.name+ " is now "+this.workload);
    }
    public List<String> getActivitys() {
        return activities;
    }
    
    public String getCourseID(int index) {
        return activities.get(index);
    }
    public void removeCourse(int i) {
        this.activities.remove(i);
    }
    public List<String> getCourses() {
        return activities;
    }
    
    public void addEntry(String entry) {
        journal.add(entry);
    }
    public String getEntry(int index) {
        return journal.get(index);
    }
    public List<String> getJournal() {
        return journal;
    }
    
    public void setWorkTime(int time) {
        this.workload=time;
    }
    public int getWorkTime() {
        return workload;
    }
    public void setHappiness(int happy) {
        this.happiness=happy;
    }
    public int getHappiness() {
        return happiness;
    }
    public void setStayTime(int time) {
        this.stayTime=time;
    }
    public int getStayTime() {
        return stayTime;
    }
    public void setLeaveTime(int time) {
        this.leaveTime=time;
    }
    public int getLeaveTime() {
        return leaveTime;
    }
    public void setLeaveReason(int i) {
        this.leaveReason=i;
    }
    public int getLeaveReason() {
        return leaveReason;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int newNr) {
        this.number=newNr;
    }
    public boolean isFormer() {
        return isFormer;
    }
    public void setFormer(boolean state) {
        this.isFormer=state;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean status) {
        this.isAlive=status;
    }
    
    String name;
    int number;
    FeatsBackground background;
    List<int[]> deeds = null; //0 -> DeedNr | 1 -> Epicness
    int[] attributes = null; //0 phy | 1 men | 2 soc | 3 mag
    String[][] timeTable = null; //Dayly Hours | Weekdays
    List<String> activities;
    List<String> journal;
    int workload;
    int happiness;
    int stayTime;
    int leaveTime;
    int leaveReason;
    boolean isFormer;
    boolean isAlive;
    
    public static final int NONE=0;
    public static final int MET_PERSONAL_GOALS=1;
    public static final int MET_STUDY_GOALS=2;
    public static final int NO_GOLD=3;
    public static final int UNHAPPY=4;
    public static final int FIRED=5;
    public static final int STORY=6;
}
