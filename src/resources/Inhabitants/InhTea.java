/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.Inhabitants;

import gui.Mainframe;
import java.io.Serializable;

/**
 *
 * @author Die Axt
 */
public class InhTea extends Inhabitants implements Serializable {
    
    public InhTea(boolean iP,boolean fh) {    
        super();
        this.isPlayer=iP;
        this.forHire=fh;
        if(forHire) {
            timeAvailable = new java.util.Random().nextInt(5)+1;
        } else {
            timeAvailable = 0;
        }
    }
    
    private int cost;
    private int fame;
    private double teaching;
    private int quarter;
    private boolean isPlayer;
    private boolean forHire;
    private int timeAvailable;
    private int teaFHNr;
    
    public int getCost() {
        return cost;
    }
    public void setCost(int i) {
        this.cost=i;
    }
    public void calcCost() {
        int aSum=0;
        for(int attribute : attributes) {
            aSum=aSum+attribute;
        }
        this.cost = (aSum+(int)teaching)*10/2;
    }
    public int getFame() {
        return fame;
    }
    public void setFame(int fame) {
        this.fame=fame;
    }
    public double getTeaching() {
        return teaching;
    }
    public void setTeaching(double ptea) {
        this.teaching=ptea;
    }
    public int getQuarter() {
        return quarter;
    }
    public void setQuarter(int rq) {
        this.quarter=rq;
    }
    public boolean getPlayer() {
        return isPlayer;
    }
    public void setPlayer(boolean b) {
        this.isPlayer=b;
    }
    public boolean isFH() {
        return forHire;
    }
    public void setFH(boolean fh) {
        this.forHire=fh;
    }
    public int getAv() {
        return timeAvailable;
    }
    public void setAv(int i) {
        this.timeAvailable=i;
    }
    public int getTeaFHNr() {
        return teaFHNr;
    }
    public void setTeaFHNr(int pTeaNr) {
        this.teaFHNr=pTeaNr;
    }
    public String getLeaveReasonString() {
        switch (leaveReason) {
            case NO_GOLD:
                return "Didn t get payed";
            case FIRED:
                return "Was fired";
            case STORY:
                return "Went away to find glory";
            default:
                return "";
        }
    }
    public String getLeaveReasonString(int pLeaveReason) {
        switch (pLeaveReason) {
            case NO_GOLD:
                return "Didn t get payed";
            case FIRED:
                return "Was fired";
            case STORY:
                return "Went away to find glory";
            default:
                return "";
        }
    }
    public void createNr(Mainframe dsk) {
        System.out.println("Create new number for TFH "+this.teaFHNr);
        this.number=findNr(10000,dsk);        
        System.out.println("New number for TFH "+this.teaFHNr+": "+this.number);
    }
    private int findNr(int nr, Mainframe dsk) {
        while(checkNr(nr,dsk)) {
            nr++;
        }
        return nr;
    }
    private boolean checkNr(int nr, Mainframe dsk) {
        return dsk.getRes().getLTea().stream().anyMatch(pTea -> pTea.getNumber()==nr);
    }
}
