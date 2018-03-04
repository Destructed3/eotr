/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.activity;

import gui.Mainframe;
import resources.Inhabitants.InhStu;
import resources.Inhabitants.InhTea;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public class ActivityJob extends Activity {
    public ActivityJob(int doerNr, int pTopic,int year) {
        super();
        this.hostNr=doerNr;
        this.topic=pTopic;
        this.name=setName();
        this.year=year;
    }
    public ActivityJob(String pher, int pTopic, Region reg, int pyear) {
        super();
        this.heritage=pher;
        this.topic=pTopic;
        this.name=setName();
        this.region=reg;
        this.available=true;
        this.year=pyear;
    }
    
    public static final int GUARD=0;
    public static final int ADVISOR=1;
    public static final int BARD=2;
    public static final int SORCERER=3;
    
    int topic;
    int income;
    String heritage;
    String description;
    
    boolean available;
    
    public int getTopic()  {
        return topic;
    }
    
    public void setTimeTable(int day) {
        for(int hour=0;hour<10;hour++) {
            this.timeTable[hour][day]=true;
        }
    }
    public int getDay() {
        for(int d=0;d<7;d++) {
            if(this.timeTable[0][d]) {
                return d;
            }
        }
        return 10;
    }

    public void setIncome(Mainframe dsk) {
        System.out.println("Set income of Job "+this.id+" for "+this.hostNr);
        if(isHostTea()) {
            InhTea tea = dsk.getData().lTea.stream().filter(pTea -> 
                    pTea.getNumber()==this.getHostNr()).findAny().get();
            this.income=tea.getAttribute(topic)*50;
        } else {
            InhStu stu = dsk.getData().lStu.stream().filter(pStu -> 
                    pStu.getNumber()==hostNr).findAny().get();
            this.income=stu.getAttribute(topic)*25;
        }
        System.out.println("Income for Job "+this.id+" is "+this.income);
    }
    public int getIncome() {
        return income;
    }
    public void createID(Mainframe dsk) {
        String pNR="AJ"+region.getName().substring(0, 2);
        int pNr=0;
        while(isAv(dsk,pNR,pNr)) {
            pNr++;
        }
        if(pNr<10) {
            pNR=pNR+"0000"+pNr;
        } else if(pNr<100) {
            pNR=pNR+"000"+pNr;
        } else if(pNr<1000) {
            pNR=pNR+"00"+pNr;
        } else if(pNr<10000) {
            pNR=pNR+"0"+pNr;
        } else {
            pNR=pNR+pNr;
        }
        this.id=pNR;
    }
    
    private boolean isAv(Mainframe dsk,String partID, int id) {
        return dsk.getData().lRegion.stream().filter(pReg -> 
                pReg.getID().equals(region.getID())).findAny().get().getL_Act().stream().filter(pAct -> 
                        pAct.getID().substring(0,4).equals(partID)).anyMatch(pAct -> 
                                Integer.parseInt(pAct.getID().substring(5,9))==id);
    }
    
    private String setName() {
        switch(topic) {
            default:
                System.out.println("No job type found at ???");
                return "NO TYPE FOUND";
            case ActivityJob.GUARD:
                return "Guard duty";
            case ActivityJob.ADVISOR:
                return "Advisoring";
            case ActivityJob.BARD:
                return "Entertaining";
            case ActivityJob.SORCERER:
                return "Casting spells";
        }
    }
    
    public void setAvailable(boolean av) {
        this.available=av;
    }
    public boolean getAvailable() {
        return available;
    }
    
    public boolean isHostTea() {
        String hNr = String.valueOf(hostNr);
        return hNr.substring(0, 1).equals(String.valueOf(1));
    }
    
    public void set_AObject() {
        aObject=new generators.strings.Objects_job().get_JobObject(heritage, topic)+" in the "+heritage+" of "+region.getName();
    }
    public String get_AObject() {
        return aObject;
    }
    
    public String getJob_string() {
        switch(topic) {
            default:
                return "Guardian";
            case 1:
                return "Advisor";
            case 2:
                return "Bard";
            case 3:
                return "Sorcerer";
        }
    }
    
//    public Region getRegion() {
//        return region;
//    }
    
}
