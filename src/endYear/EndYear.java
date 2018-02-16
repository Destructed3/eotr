/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endYear;

import java.util.Random;
import resources.Accounting;

/**
 *
 * @author Die Axt
 */
public class EndYear {
    public EndYear(gui.Mainframe pdsk) {
        this.dsk=pdsk;
        this.save = new root.SaveDat();
        this.pA = new ManageActivities(dsk);
        this.mU = new ManageUpkeep(dsk);
        this.mS = new ManageStudents(dsk);
        this.year=dsk.getRes().getVariables().getYear();
    }
    
    public void run() {
        this.save.saveGame("LastYear", dsk);
        pA.processAct();
        mU.payUpkeep();
        this.acc_new = mS.manageStudents();
        saveNewY();
        new gui.Mainframe("NewYear",true).setVisible(true);
        dsk.dispose();
    }
    
    private void saveNewY() {
        dsk.getRes().getVariables().setYear(year+1);
        dsk.getRes().getLAcc().add(acc_new);
        this.save.saveGame("NewYear", dsk);
    }
    
    gui.Mainframe dsk;
    root.SaveDat save = null;
    
    ManageActivities pA;
    ManageUpkeep mU;
    ManageStudents mS;
    
    Random r = new Random();
    
    int year;
    Accounting acc_new;
       
}
