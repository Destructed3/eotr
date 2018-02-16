/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.Journal;

import java.util.Arrays;
import java.util.Random;
import resources.JournalEntry;
import resources.activity.*;
import resources.Inhabitants.Inhabitants;

/**
 *
 * @author Die Axt
 */
public class JournalEntryGenerator {
    
    public JournalEntryGenerator() {
        this.r = new Random();
    }
    public JournalEntry generateCourseEntry(ActivityCourse pac, Inhabitants pInh) {
        System.out.println("Generate Journal Entry for Course "+pac.getID());
        String script;
        this.ac=pac;
        this.inh=pInh;
        if(inh.getNumber()>=20000) {
            script = string_startC_stu();
        } else {
            script = string_startC_tea();
        }
        return generateEntry(aa,script);
    }
    private String string_startC_stu() {
        return "I visited a course to improve my "+ac.getTopicN()+" abilities";
    }
    private String string_startC_tea() {
        return "I held a course about "+ac.getTopicN()+" abilities";
    }
    
    public String generateJobEntry(Inhabitants pInh, ActivityJob aj) {
        System.out.println("Generate Journal Entry for Job "+aj.getID());
        this.inh=pInh;
        this.jej = new JournalEntryJob();
        System.out.println("Year "+aj.getYear()+": "+jej.getEntry(aj));
        return "Year "+aj.getYear()+": "+jej.getEntry(aj);
    }
    
    public JournalEntry generateAdvString(ActivityAdventure pAdv) {
        this.aa=pAdv;
        this.start=r.nextBoolean();
        return generateEntry(aa,"");
    }
    
    private JournalEntry generateEntry(Activity a, String script) {
        JournalEntry je;
        try {
            System.out.print("Activity Date: "+a.getYear());
            int[]date={a.getYear(),0};
            System.out.print("Saving date: "+Arrays.toString(date));
            je = new JournalEntry(null,date,script,false);
        } catch (NullPointerException e) {
            int[]date={322,0};
            je = new JournalEntry(null,date,script,false);
        }
        
        return je;
    }
    
    
    ActivityAdventure aa;
    ActivityCourse ac;
    ActivityJob aj;
    
    JournalEntryAdv jea;
    JournalEntryCou jec;
    JournalEntryJob jej;
    
    Inhabitants inh;
    
    Random r;
    
    int money;
    boolean start;
}
