/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import gui.Mainframe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import resources.activity.*;
import resources.Inhabitants.InhStu;

/**
 *
 * @author Die Axt
 */
public class AI_Stu {
    
    public AI_Stu(Mainframe pdsk) {
        this.dsk=pdsk;
    }
    
    Mainframe dsk;
    InhStu stu;
    
    AI_Stu_choseActivity chose;
    AI_Stu_choseJob createJob;
    
    List<ActivityCourse> lCourses;
    List<ActivityJob> lJobs;
    List<ActivityAdventure> lAdventures;
    
    Random r;
    
    double[] div = {0.5,0.5,0.5,0.5,0.5,0.5};
    List<double[]> lPref; // 0 - prefNr | 1 - prefValue
    int topic;
    int unlucky;
    int jobs;
    
    public void choseActivity(InhStu pstu) {
        System.out.println("Chosing courses for "+pstu.getNumber());
        
        unlucky = 0;
        jobs = 0;
        
        r = new Random();
        
        chose = new AI_Stu_choseActivity();
        createJob = new AI_Stu_choseJob();
        
        this.stu = pstu;
        this.lCourses = dsk.getData().lCourse;
        this.lJobs = dsk.getData().lJob;
        this.lAdventures = dsk.getData().lAdv;
        
        this.lPref = new ArrayList();
        
        stu.setJobDesire(dsk.getData().studyFee);
        stu.setAdvDesire();
        
        for(int i=0;i<6;i++) {
            double[] d = {i,dividePref(i)};
            System.out.println("Array pref "+i);
            System.out.println(Arrays.toString(d));
            lPref.add(d);
        }
        
        while(stu.getWorkTime()<50 && isActivityAvailable() && unlucky<5) {
            topic = chose.choseTopic(lPref,stu,dsk);
            findActivity();
            actualizeLPref(dividePref(topic));
        }
        
    }
    
    private void actualizeLPref(double newV) {
        lPref.stream().filter(d -> d[0]==topic).findAny().get()[1]=newV;
        System.out.println("Array pref "+topic);
        System.out.println(Arrays.toString(lPref.stream().filter(d -> d[0]==topic).findAny().get()));
    }
    
    private void findActivity() {
        switch(topic) {
            case 4:
                findJob();
                break;
            case 5:
                findAdv();
                break;
            default:
                findCourse();
                break;
        }
    }
    
    private void findAdv() {
        System.out.println("Trying to find an adventure for"+stu.getNumber()+"...");
        try {
            ActivityAdventure adventure = lAdventures.stream().filter(pAA -> 
                    compareTT(pAA.getTimeTable()) 
                            && checkActivity_inUse(pAA.getID())).findAny().get();
            stu.addActivity(adventure);
            stu.setHappiness(stu.getHappiness()+15);
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No adventure available");
            unlucky++;
            try {
                stu.setHappiness(stu.getHappiness()-10);
            } catch (Exception ex){
                stu.setHappiness(0);
            }
        }
    }
    private void findCourse() {
        System.out.println("Trying to find a course for"+stu.getNumber()+"...");
        try {
            ActivityCourse course = lCourses.stream().filter(pAC -> 
                    pAC.getTopic()==(topic) 
                            && compareTT(pAC.getTimeTable()) 
                            && !checkActivity_inUse(pAC.getID())).findAny().get();
            course.addStudent(stu.getNumber());
            stu.addActivity(course);
            stu.setHappiness(stu.getHappiness()+15);
        } catch (java.util.NoSuchElementException e) {
            System.out.println("No fitting course available");
            unlucky++;
            try {
                stu.setHappiness(stu.getHappiness()-10);
            } catch (Exception ex){
                stu.setHappiness(0);
            }
        }
    }
    private void findJob() {
        System.out.println("Trying to find a job for"+stu.getNumber()+"...");
        ActivityJob job = createJob.findJob(stu,dsk);
        if(job == null) {
            System.out.println("No Job available");
            unlucky++;
            try {
                stu.setHappiness(stu.getHappiness()-10);
            } catch (Exception ex){
                stu.setHappiness(0);
            }
        } else {
            job.setHost(stu.getNumber());
            job.setTimeTable(getDay());
            job.setIncome(dsk);
            stu.addActivity(job);
            stu.setHappiness(stu.getHappiness()+15);
            dsk.getData().lJob.removeIf(pAJ -> pAJ.getID().equals(job.getID()));
            dsk.getData().lJob.add(job);
        }
    }
    
    private boolean isActivityAvailable() {
        return (isAdvAv() || isCourseAv() || isJobAv());
    }
    
    private boolean isAdvAv() {
        return lAdventures.stream().anyMatch(aa -> 
                !checkActivity_inUse(aa.getID()) && 
                compareTT(aa.getTimeTable()));
    }
    private boolean isCourseAv() {
        return lCourses.stream().anyMatch(pAC -> 
                !checkActivity_inUse(pAC.getID()) && 
                compareTT(pAC.getTimeTable()) && 
                lPref.get(pAC.getTopic())[1]>0);
    }
    private boolean isJobAv() {
        return checkJobAv() && dayAv(stu.getTimeTable()) && jobs<2;
    }
    
    private boolean checkActivity_inUse(String id) {
        return stu.getActivitys().stream().anyMatch(aID -> aID.equals(id));
    }
    private boolean compareTT(boolean[][] cTT) {
        for(int hour=0;hour<10;hour++) {
            for(int day=0;day<7;day++) {
                if(cTT[hour][day] && !stu.getTimeTableHour(hour, day).equals("")) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean checkJobAv() {
        return dsk.getData().lJob.stream().anyMatch(pAJ -> pAJ.isActive() && pAJ.getHostNr()==0);
    }
    private boolean dayAv(String[][] cTT) {
        for(int d=0;d<7;d++) {
            for(int h=0;h<10;h++) {
                if(!cTT[h][d].equals("")) {
                    break;
                }
                if(h==9) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private int getDay() {
        for(int day=0;day<7;day++) {
            for(int h=0;h<10;h++) {
                if(!stu.getTimeTableHour(h, day).equals("")) {
                    break;
                }
                if(h==9) {
                    return day;
                }
            }
        }
        return 10;
    }
    
    private double dividePref(int i) {
        double d =(int)(stu.getInterest(i)/div[i]);
        div[i]++;
        return d;
    }
}

