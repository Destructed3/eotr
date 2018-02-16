/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.Inhabitants.InhTea;

/**
 *
 * @author Die Axt
 */
public class SaveDat {
    public SaveDat() {
        
    }
    public void saveGame(String datName, gui.Mainframe dsk) {
        this.saveDat(".\\Saves\\save"+datName+"\\teacher.sav", dsk.getRes().getLTea());
        this.saveDat(".\\Saves\\save"+datName+"\\tfh.sav", dsk.getRes().getTfH());
        this.saveDat(".\\Saves\\save"+datName+"\\student.sav", dsk.getRes().getLStu());
        this.saveDat(".\\Saves\\save"+datName+"\\roomStudy.sav", dsk.getRes().getLRS());
        this.saveDat(".\\Saves\\save"+datName+"\\roomQua.sav", dsk.getRes().getLRQ());
        this.saveDat(".\\Saves\\save"+datName+"\\roomDorm.sav", dsk.getRes().getLRD());
        this.saveDat(".\\Saves\\save"+datName+"\\actAdv.sav", dsk.getRes().getLAA());
        this.saveDat(".\\Saves\\save"+datName+"\\actCourse.sav", dsk.getRes().getLAC());
        this.saveDat(".\\Saves\\save"+datName+"\\actJob.sav", dsk.getRes().getLAJ());
        this.saveDat(".\\Saves\\save"+datName+"\\monster.sav", dsk.getRes().getLMon());
        this.saveDat(".\\Saves\\save"+datName+"\\var.sav", dsk.getRes().getVariables());
        this.saveDat(".\\Saves\\save"+datName+"\\goals.sav", dsk.getRes().getGoals());
        this.saveDat(".\\Saves\\save"+datName+"\\accounting.sav", dsk.getRes().getLAcc());
        this.saveDat(".\\Saves\\save"+datName+"\\regions.sav", dsk.getRes().getLReg());
    }
    public void saveDat(String datName, Object inputObj) {
        ObjectOutputStream ois = null;
        try{
            ois = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(datName)));
            ois.writeObject(inputObj);
        } catch (IOException ex) {
            Logger.getLogger(SaveDat.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            try {
                if(ois!=null) {
                    ois.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SaveDat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void saveVecTea(String datName, Vector<InhTea> vecTea) {
        ObjectOutputStream ois = null;
        try {
            ois = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(datName)));
            ois.writeObject(vecTea);
        } catch (IOException ex) {
            Logger.getLogger(SaveDat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ois!=null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(SaveDat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    
}
