/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.Journal;

import java.util.Random;
import resources.activity.ActivityAdventure;

/**
 *
 * @author Die Axt
 */
public class JournalEntryAdv {
    
    private String adv_GetQuest(ActivityAdventure paa) {
        this.aa=paa;
        this.r = new Random();
        this.start=true;
        switch(aa.getTopic()) {
            default:
                return adv_start_Exp();
            case ActivityAdventure.DUNGEON:
                return adv_start_Dun();
            case ActivityAdventure.HUNT:
                return adv_start_Hun();
            case ActivityAdventure.RECONQUER:
                return adv_start_Rec();
        }
    }
    
    private String adv_start_Exp() {
        if(aa.getAdvRegion().isKnown()) {
            return adv_Exp_known();
        } else {
            return adv_Exp_unknown();
        }
        
    }
    private String adv_Exp_known() {
        int i=r.nextInt(3);
        if(start) {
            return "The region of "+aa.getAdvRegion().getName()+" was our aim again."+getComplications();
        } else {
            return "Today we proceeded to explore "+aa.getAdvRegion().getName()+"."+getComplications();
        }
    }
    private String adv_Exp_unknown() {
        int i=r.nextInt(3);
        if(start) {
            return "The region of "+aa.getAdvRegion().getName()+" was explored by us."+getComplications();
        } else {
            return "Today we explored "+aa.getAdvRegion().getName()+"."+getComplications();
        }
    }
    
    private String adv_start_Dun() {
        if(start) {
            return "We went to the dungeon in "+aa.getAdvRegion().getName()+"."+getComplications();
        } else {
            return "The dungeon we visited today was in "+aa.getAdvRegion().getName()+"."+getComplications();
        }        
    }
    private String adv_start_Hun() {
        if(start) {
            return "We hunted "+aa.getMonster().getName()+" in "+aa.getAdvRegion().getName()+" down."+getComplications();
        } else {
            return "This damn beast "+aa.getMonster().getName()+"! We hunted it in "+aa.getAdvRegion().getName()+"."+getComplications();
        }        
    }
    private String adv_start_Rec() {
        if(start) {
            return "We tried to fight back the armies of darkness in "+aa.getAdvRegion().getName()+"."+getComplications();
        } else {
            return "We went to "+aa.getAdvRegion().getName()+"to free it from the evil that pleagues the land."+getComplications();
        }        
    }
    
    private String getComplications() {
        return "";
    }
    
    ActivityAdventure aa;
    
    Random r;
    boolean start;
    
}
