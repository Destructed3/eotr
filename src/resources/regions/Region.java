/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.regions;

import gui.Mainframe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import resources.activity.Activity;

/**
 *
 * @author Die Axt
 */
public class Region implements Serializable {
    
    public Region() {
        this.journal=new ArrayList();
        this.generatesEvents=new ArrayList();
        setupVenues();
    }
    
    public void setID(String newID) {
        this.id=newID;
    }
    public String getID() {
        return id;
    }
    
    public void setName(String newName) {
        this.name=newName;
    }
    public String getName(){
        return name;
    }
    
    public void setStatus(int newStatus) {
        this.status=newStatus;
    }
    public int getStatus() {
        return status;
    }
    public String getStatus_String() {
        switch(status) {
            default:
                return "Mankind claimed this region. It's safe.";
            case WILDERNESS:
                return "It's a dangerous region. Even though you find people, you also find a lot of dangers, too...";
            case LAIR:
                return "Beware from this place! Dangerous creatures have settled here and there's nobody here to help you...";
            case INVADED:
                return "The forces of evil conquered this region!";
        }
    }
    
    
    public void setVenue(int venue, boolean value) {
        this.venues[venue]=value;
    }
    public boolean[] getVenues() {
        return venues;
    }
    public boolean getVenue(int venue) {
        return venues[venue];
    }
    
    public boolean hasTown() {
        return venues[0];
    }
    public void addTown() {
        this.venues[0]=true;
    }
    
    public boolean hasHomestead() {
        return venues[1];
    }
    public void addHomestead() {
        this.venues[1]=true;
    }
    
    public boolean hasCastle() {
        return venues[2];
    }
    public void addCastle() {
        this.venues[2]=true;
    }
    
    public boolean hasTavern() {
        return venues[3];
    }
    public void addTavern() {
        this.venues[3]=true;
    }
    
    public boolean hasTradePost() {
        return venues[4];
    }
    public void addTradePost() {
        this.venues[4]=true;
    }
    
    public boolean hasMonastery() {
        return venues[5];
    }
    public void addMonastery() {
        this.venues[5]=true;
    }

    public String getHeritage_string(int heritage) {
        switch(heritage) {
            default:
                return "Town";
            case 1:
                return "Homestead";
            case 2:
                return "Trade Post";
            case 3:
                return "Castle";
            case 4:
                return "Tavern";
            case 5:
                return "Monastery";
        }
    }
    public List<Activity> getL_Act() {
        if(lAct==null) {
            lAct = new ArrayList();
        }
        return lAct;
    }
    
    
    public void setKnown(boolean know) {
        this.known=know;
    }
    public boolean isKnown() {
        return known;
    }
    
    public void setExplored(boolean explore) {
        this.isExplored=explore;
    }
    public boolean isExplored() {
        return isExplored;
    }
    
    public void setHiddenPlaces(int places) {
        this.hiddenPlaces=places;
    }
    public void removeHiddenPlaces(int places) {
        if(this.hiddenPlaces>=places) {
            this.hiddenPlaces=hiddenPlaces-places;
        } else {
            JOptionPane.showConfirmDialog(null, "There s no Place to remove...", "Damn it!!", JOptionPane.OK_OPTION);
        }
    }
    public int getHiddenPlaces() {
        return hiddenPlaces;
    }
    
    public int explore() {
        Random r = new Random();
        int i = r.nextInt(30);
        double val = i/100+0.1;
        int unveiled = (int) (hiddenPlaces*val);
        if(unveiled==0) {
            unveiled = 1;
        }
        if(hiddenPlaces>=unveiled) {
            this.hiddenPlaces=hiddenPlaces-unveiled;
        } else {
            this.isExplored=true;
            return 0;
        }
        if(hiddenPlaces==0) {
            this.isExplored=true;
        }
        return unveiled;
    }
    
    public void addJournalEntry(String entry) {
        this.journal.add(entry);
    }
    public List<String> getJournal() {
        return journal;
    }
    
    
    public void addEvent_generated(String eID) {
        this.generatesEvents.add(eID);
    }
    public List<String> getL_possibleEvents() {
        return generatesEvents;
    }
    
    private void setupVenues() {
        venues=new boolean[6];
        for(int i=0;i<venues.length;i++) {
            venues[i]=false;
        }
    }
    
    public void createID(Mainframe dsk) {
        String beginn = "Re"+name.substring(0,2);
        System.out.println("Check ID beginning: "+beginn);
        if(!checkBeginn(beginn,dsk)) {
            System.out.println("Finding ID: "+beginn+"00000");
            this.id=beginn+"00000";            
        } else {
            int nr=0;
            while(checkNr(nr,dsk)) {
                nr++;
            }
            System.out.println("Finding ID: "+beginn+checkNrDigits(nr));
            this.id=beginn+checkNrDigits(nr);
        }
    }
    private String checkNrDigits(int nr) {
        if(10000<=nr) {
            return String.valueOf(nr);
        }
        if(1000<=nr) {
            return "0"+nr;
        }
        if(100<=nr) {
            return "00"+nr;
        }
        if(10<=nr) {
            return "000"+nr;
        }
        return "0000"+nr;
    }
    private boolean checkBeginn(String beg, Mainframe dsk) {
        System.out.println("Compare with");
        return dsk.getRes().lRegion.stream().anyMatch(reg -> reg.getID().substring(0,4).equals(beg));
    }
    private boolean checkNr(int nr, Mainframe dsk) {
        return dsk.getRes().lRegion.stream().anyMatch(reg -> Integer.parseInt(reg.getID().substring(5,9))==nr);
    }
    
    String id;
    String name;
    String flavour;
    int status;    
    
    boolean[] venues; //0 - Towns | 1 - Homestead | 2 - Castle | 3 - Tavern | 4 - Trading Post | 5 - Monastery
    List<Activity> lAct;
    int[] avJobs;     //0-Guard | 1-Advisor | 2-Bard | 3-Sorcerer
    
    
    boolean known;
    boolean isExplored;
    int hiddenPlaces;
    
    List<String> journal;
    
    List<String> generatesEvents;
    
    public static final int SAVE=0;         //Orte möglich, keine Abenteuer, keine negativen Ereignisse
    public static final int WILDERNESS=1;   //Orte möglich, Abenteuer möglich, normal Ereignisse
    public static final int LAIR=2;         //keine Orte, Abenteuer möglich, nur negative Ereignisse
    public static final int INVADED=3;      //Region ist blockiert, keine Orte, keine Abenteuer, nur negative Ereignisse
}
