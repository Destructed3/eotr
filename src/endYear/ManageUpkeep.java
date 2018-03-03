/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endYear;

import gui.Mainframe;
import java.util.Random;
import java.util.function.Consumer;
import resources.Accounting;
import resources.Inhabitants.InhTea;

/**
 *
 * @author Die Axt
 */
public class ManageUpkeep {
    public ManageUpkeep(Mainframe pdsk) {
        this.dsk=pdsk;
        this.year=dsk.getRes().year;
        this.r = new Random();
    }
    
    public void payUpkeep() {
        payTeacher();
        payRooms();
    }
    
    private void payTeacher() {
        dsk.getRes().lTea.forEach((InhTea pTea) -> {
            try {
                dsk.getRes().lAccounting.stream().filter(pAcc -> pAcc.getYear()==year).findAny().get();
                dsk.getRes().gold -= pTea.getCost();
                ManageUpkeep.this.getAccountingYear().addTeaWages(pTea.getCost());
                pTea.setStayTime(pTea.getStayTime()+1);
            }catch (Exception e) {
                dsk.getRes().lTea.removeIf(rTea -> rTea.getNumber()==pTea.getNumber());
                dsk.getRes().reputation -= r.nextInt(5)+5;
            }
        });
    }
    private void payRooms() {
        this.getAccountingYear().setMaintRS(0);
        this.getAccountingYear().setMaintRQ(0);
        this.getAccountingYear().setMaintRD(0);
        this.maintainRoomsStudy();
        this.maintainRoomsQuarter();
        this.maintainRoomsDorm();
    }
    private void maintainRoomsStudy() {
        dsk.getRes().lRoomStudy.stream().filter(pRS -> pRS.isMaintained()).forEach(pRS -> {
            if(pRS.getMaintenance()<dsk.getRes().gold) {
                dsk.getRes().gold -= pRS.getMaintenance();
                this.getAccountingYear().addMaintRS(pRS.getMaintenance());
            } else {
                pRS.setMaintained(false);
                dsk.getRes().reputation -= r.nextInt(3) + 5;
            }
        });
    }
    private void maintainRoomsQuarter() {
        dsk.getRes().lRoomQuarter.stream().filter(pRQ -> pRQ.isMaintained()).forEach(pRQ -> {
            try {
                dsk.getRes().gold -= pRQ.getMaintenance();
                this.getAccountingYear().addMaintRQ(pRQ.getMaintenance());
            } catch(Exception e) {
                pRQ.setMaintained(false);
                dsk.getRes().reputation -= r.nextInt(3)+5;
            }
        });
    }
    private void maintainRoomsDorm() {
        dsk.getRes().lRoomDorm.stream().filter(pRD -> 
                pRD.isMaintained()).forEach(pRD -> {
            if(pRD.getMaintenance()<dsk.getRes().gold) {
                dsk.getRes().gold -= pRD.getMaintenance();
                this.getAccountingYear().addMaintRD(pRD.getMaintenance());
            } else {
                pRD.setMaintained(false);
                dsk.getRes().reputation -= r.nextInt(3) + 5;
            }
        });
    }
    
    private Accounting getAccountingYear() {
        return dsk.getRes().lAccounting.stream().filter(pAcc -> pAcc.getYear()==year).findAny().get();
    } 
    
    Mainframe dsk;
    int year;
    Random r;
}
