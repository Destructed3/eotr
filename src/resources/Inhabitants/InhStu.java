/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Inhabitants;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Die Axt
 */
public class InhStu extends Inhabitants implements Serializable {
    
    public InhStu() {   
        super();
        this.speed = new java.util.Random().nextInt(100);
        this.personalGoal = (new java.util.Random().nextInt(350))+150;
        this.metGoals=false;
        this.happiness=75;
    }
    
    double learn;
    int dorm;
    int progress;
    int semester;
    int gold;
    int speed;
    int[] interests;
    int personalGoal;
    boolean metGoals;    
    
    public void setLearn(double learn) {
        this.learn=learn;
    }
    public double getLearn() {
        return learn;
    }
    public void setDorm(int rd) {
        this.dorm=rd;
    }
    public int getDorm() {
        return dorm;
    }
    public void addProgress(int i) {
        this.progress=progress+i;
    }
    public int getProgress() {
        return progress;
    }
    public void setSemester(int psem) {
        this.semester=psem;
    }
    public void progressSemester() {
        this.semester=semester+1;
    }
    public int getSemester() {
        return semester;
    }
    public void payFees(int fees) {
        this.gold = gold-fees;
    }
    public void addGold(int income) {
        this.gold = gold+income;
    }
    public int getGold() {
        return gold;
    }
    public int getSpeed() {
        return speed;
    }
    public void setInterest(int iNr, int iVal) {
        this.interests[iNr]=iVal;
    }
    public void setInterests(int[] pinterests) {
        this.interests = pinterests;
    }
    public int getInterest(int i) {
        return interests[i];
    }
    public int[] getInterests() {
        return interests;
    }
    public void setJobDesire(int studyFees) {
        java.util.Random r = new java.util.Random();
        if(gold<studyFees) {
            interests[4]=interests[4]+r.nextInt(30)+60;
        }
        else if(gold==studyFees) {
            interests[4]=interests[4]+r.nextInt(30)+40;
        }
        else if(gold<studyFees*2 && gold>studyFees) {
            interests[4]=interests[4]+r.nextInt(15)+10;
        }
    }
    public void setAdvDesire() {
        int sum=0; 
        Random r = new Random();
        for(int i=0;i<4;i++) {
            sum=sum+attributes[i];
        }
        interests[5]=(r.nextInt(sum/5)+sum/3)+15;
    }
    public boolean metGoals(int goal) {
        int sumAttributes=0;
        for(int attribute : attributes) {
            sumAttributes = sumAttributes+attribute;
        }
        metGoals = goal<=sumAttributes || personalGoal<=sumAttributes;
        return metGoals;
    }
    public String getLeaveResonString() {
        switch (leaveReason) {
            case MET_PERSONAL_GOALS:
                return "Met personal goals";
            case MET_STUDY_GOALS:
                return "Finished studys";
            case NO_GOLD:
                return "Ran out of gold";
            case UNHAPPY:
                return "Was unhappy and left";
            case FIRED:
                return "Was considered unfitting and thrown out";
            case STORY:
                return "Left to find greater glory";
            default:
                return "";
        }
    }
    public String getLeaveResonString(int pLeaveReason) {
        switch (pLeaveReason) {
            case MET_PERSONAL_GOALS:
                return "Met personal goals";
            case MET_STUDY_GOALS:
                return "Finished studys";
            case NO_GOLD:
                return "Ran out of gold";
            case UNHAPPY:
                return "Was unhappy and left";
            case FIRED:
                return "Was considered unfitting and thrown out";
            case STORY:
                return "Left to find greater glory";
            default:
                return "";
        }
    }
}
