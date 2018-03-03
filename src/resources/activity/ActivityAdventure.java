/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.activity;

import gui.Mainframe;
import java.util.ArrayList;
import java.util.List;
import resources.Monster;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public class ActivityAdventure extends Activity {
    public ActivityAdventure() {
        super();
        this.lStu = new ArrayList();
    }
    
    public static final int EXPLORE=0;
    public static final int DUNGEON=1;
    public static final int HUNT=2;
    public static final int RECONQUER=3;
    
    int available;
    String description;
    int income;
    Region pRegion;
    Monster monster;
    int difficulty;
    int topic;
    List<Integer> lStu = null;
    
    public void setAvailable(int av) {
        this.available=av;
    }
    public int getAbailable() {
        return available;
    }
    public void setDescription(String desc) {
        this.description=desc;
    }
    public String getDescription() {
        return description;
    }
    public void setIncome(int inc) {
        this.income=inc;
    }
    public int getIncome() {
        return income;
    }
    public void setAdvRegion(Region reg) {
        this.pRegion=reg;
    }
    public Region getAdvRegion() {
        return pRegion;
    }
    public void setMonster(Monster evil) {
        this.monster=evil;
    }
    public Monster getMonster() {
        return monster;
    }
    public void setTopic(int top) {
        this.topic=top;
    }
    public int getTopic() {
        return topic;
    }
    public String getTopicString() {
        switch (topic) {
            case DUNGEON:
                return "";
            case HUNT:
                return"";
            case RECONQUER:
                return "";
            default:
                return"";
        }
    }
    public void createID(Mainframe dsk) {
        String pNR;
        int pnr = 0;
        while(idUsed(pnr,dsk)) {
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
        this.id="AA"+pNR;
        dsk.getRes().lMonster.stream().filter(pMon -> pMon.getID().equals(this.monster)).findAny().get().setCourse(this.id);
    }
    private boolean idUsed(int id, Mainframe dsk){
        return dsk.getRes().lAdv.stream().anyMatch(pAdv -> Integer.parseInt(pAdv.getID().substring(2,7))==id);
    }
}
