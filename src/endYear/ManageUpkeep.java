/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endYear;

import gui.Mainframe;
import java.util.Random;

/**
 *
 * @author Die Axt
 */
public class ManageUpkeep {
    public ManageUpkeep(Mainframe pdsk) {
        this.dsk=pdsk;
        this.year=dsk.getRes().getVariables().getYear();
        this.r = new Random();
    }
    
    public void payUpkeep() {
        payTeacher();
        payRooms();
    }
    
    private void payTeacher() {
        dsk.getRes().getLTea().forEach(pTea -> {
            try {
                dsk.getRes().getVariables().substractGold(pTea.getCost());
                dsk.getRes().getAcc_year(year).addTeaWages(pTea.getCost());
                pTea.setStayTime(pTea.getStayTime()+1);
            } catch (Exception e) {
                dsk.getRes().getLTea().removeIf(rTea -> rTea.getNumber()==pTea.getNumber());
                dsk.getRes().getVariables().substractReputation(r.nextInt(5)+5);
            }
        });
    }
    private void payRooms() {
        dsk.getRes().getAcc_year(year).setMaintRS(0);
        dsk.getRes().getAcc_year(year).setMaintRQ(0);
        dsk.getRes().getAcc_year(year).setMaintRD(0);
        maintainRS();
        maintainRQ();
        maintainRD();
    }
    private void maintainRS() {
        dsk.getRes().getLRS().stream().filter(pRS -> pRS.isMaintained()).forEach(pRS -> {
            if(pRS.getMaintenance()<dsk.getRes().getVariables().getGold()) {
                dsk.getRes().getVariables().substractGold(pRS.getMaintenance());
                dsk.getRes().getAcc_year(year).addMaintRS(pRS.getMaintenance());
            } else {
                pRS.setMaintained(false);
                dsk.getRes().getVariables().substractReputation(r.nextInt(3)+5);
            }
        });
    }
    private void maintainRQ() {
        dsk.getRes().getLRQ().stream().filter(pRQ -> pRQ.isMaintained()).forEach(pRQ -> {
            try {
                dsk.getRes().getVariables().substractGold(pRQ.getMaintenance());
                dsk.getRes().getAcc_year(year).addMaintRQ(pRQ.getMaintenance());
            } catch(Exception e) {
                pRQ.setMaintained(false);
                dsk.getRes().getVariables().substractReputation(r.nextInt(3)+5);
            }
        });
    }
    private void maintainRD() {
        dsk.getRes().getLRD().stream().filter(pRD -> 
                pRD.isMaintained()).forEach(pRD -> {
            if(pRD.getMaintenance()<dsk.getRes().getVariables().getGold()) {
                dsk.getRes().getVariables().substractGold(pRD.getMaintenance());
                dsk.getRes().getAcc_year(year).addMaintRD(pRD.getMaintenance());
            } else {
                pRD.setMaintained(false);
                dsk.getRes().getVariables().substractReputation(r.nextInt(3)+5);
            }
        });
    }
    
    Mainframe dsk;
    int year;
    Random r;
}
