/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.Journal;

import java.util.Random;
import resources.activity.ActivityJob;

/**
 *
 * @author Die Axt
 */
public class JournalEntryJob {
    public JournalEntryJob() {
        this.r= new Random();
    }
    
    public String getEntry(ActivityJob pjob) {
        System.out.println("Generate Script");
        this.aj=pjob;
        if(r.nextBoolean()) {
            return "I made "+aj.getIncome()+"g by "+getDeed()+aj.get_AObject();
        } else {
            return "By "+getDeed()+aj.get_AObject()+" I made "+aj.getIncome()+"g";
        }
    }
    private String getDeed() {
        int rdm;
        switch(aj.getTopic()) {
            default:
                rdm=r.nextInt(string_Guard.length-1);
                return string_Guard[rdm];
            case ActivityJob.ADVISOR:
                rdm=r.nextInt(string_Advisor.length-1);
                return string_Advisor[rdm];
            case ActivityJob.BARD:
                rdm=r.nextInt(string_Bard.length-1);
                return string_Bard[rdm];
            case ActivityJob.SORCERER:
                rdm=r.nextInt(string_Sorcerer.length-1);
                return string_Sorcerer[rdm];
        }
    }
    
    Random r;
    
    ActivityJob aj;
    
    String[] string_Advisor = {
        "sharing my wisdom with ",
        "providing advise to ",
        "enlightening ",
        "advising ",
        "consulting ",
        "deliberating with"
    };
    String[] string_Bard = {
        "enternaining ",
        "singing for ",
        "joking for ",
        "cheering up ",
        "amusing ",
        "diverting "
    };
    String[] string_Guard = {
        "guarding ",
        "protecting ",
        "watching over ",
        "standing watch for ",
        "standing sentinel for ",
        "sentinelling "
    };
    String[] string_Sorcerer = {
        "enchanting ",
        "conjuring for ",
        "casting a charm for ",
        "blessing ",
        "bewitching ",
        "casting a spell for"
    };
    
}
