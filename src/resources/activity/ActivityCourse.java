/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.activity;

import gui.Mainframe;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Die Axt
 */
public class ActivityCourse extends Activity {
    public ActivityCourse(String pName, int pTeacher, int pRoom, int dur, int pSize, int top, String[][] pTimeTable, Mainframe dsk) {
        super(pTeacher,dsk.getRes().year);
        this.name=pName;
        this.room=pRoom;
        this.duration=dur;
        this.courseSize=pSize;
        this.topic=top;
        this.timeTable=convertTT(pTimeTable);
        this.id=createID(dsk);
        this.courseAccounting = new ArrayList();
    }
    public ActivityCourse() {
        super();
    }
    
    int room;
    int courseSize;   
    int topic;
    int duration;   
    int progressValue;
    List<int[]> courseAccounting;
    
    public void setRoom(int r) {
        this.room=r;
    }
    public int getRoom() {
        return room;
    }
    public void setCourseSize(int i) {
        this.courseSize=i;
    }
    public int getCourseSize() {
        return courseSize;
    }
    public void setProgressValue(int addProgress) {
        this.progressValue=addProgress;
    }
    public int getProgressValue() {
        return progressValue;
    }
    public void addStudent(int stuNr) {
        int[] pAcc = {stuNr,0,0,0}; // 0 - StudNr | 1 - Attribute before | 2 - Attribute after | 3 - Learn-Factor
        this.courseAccounting.add(pAcc);
    }
    public int getStudentNr(int i) {
        return courseAccounting.get(i)[0];
    }
    public List<int[]> getStudents() {
        return courseAccounting;
    }
    public void setTopic(int i) {
        this.topic=i;
    }
    public int getTopic() {
        return topic;
    }
    public String getTopicN() {
        String top;
        switch (topic) {
            case 0:
                top = "Physical";
                break;
            case 1:
                top = "Mental";
                break;
            case 2:
                top = "Social";
                break;
            default:
                top = "Magical";
                break;
        }
        return top;
    }
    public void setDuration(int dur) {
        this.duration=dur;
    }
    public int getDuration() {
        return duration;
    }
    
    private String createID(Mainframe dsk) {
        System.out.println("Creating nr...");
        String pNR;
        int pnr=0;
        while(isID_used(dsk,pnr)) {
            pnr++;
        }
        if(pnr<10) {
            pNR="0000"+pnr;
        } else if(pnr<100) {
            pNR="000"+pnr;
        } else if(pnr<1000) {
            pNR="00"+pnr;
        } else if(pnr<10000) {
            pNR="0"+pnr;
        } else {
            pNR=String.valueOf(pnr);
        }
        System.out.println("AC"+pNR);
        return "AC"+pNR;
    }
    private boolean isID_used(Mainframe dsk, int id) {
        return dsk.getRes().lCourse.stream().anyMatch(pAC -> Integer.parseInt(pAC.getID().substring(3,7))==id);
    }
    private boolean[][] convertTT(String[][] dat) {
        System.out.println("Create Course: convert String dat to booelan TimeTable");
        String nothing="";
        boolean[][] ba = new boolean[10][7];
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                boolean b =!nothing.equals(dat[row][column]);
                System.out.println(row+"|"+column+": "+dat[row][column]+"|"+b);
                ba[row][column] = b;
            }
        }
        return ba;
    }
}
