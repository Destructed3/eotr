/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endYear;

import gui.Mainframe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import resources.Accounting;
import resources.Inhabitants.InhStu;
import resources.Inhabitants.Inhabitants;
import resources.rooms.RoomDorm;

/**
 *
 * @author Die Axt
 */
public class ManageStudents {
    public ManageStudents(Mainframe pdsk) {
        this.dsk=pdsk;
        this.year=dsk.getData().year;
        this.acc_new = new Accounting(year+1);
        this.r = new Random();
    }
    
    public Accounting manageStudents() {
        endStudy();
        newStudents();
        collectStudyFees();
        return acc_new;
    }
    
    private void collectStudyFees() {
        dsk.getData().lStu.stream().filter(pStu -> 
                !pStu.isFormer()).forEach(pStu -> {
            if(pStu.getGold()>=dsk.getData().studyFee) {
                pStu.payFees(dsk.getData().studyFee);
                pStu.setSemester(pStu.getSemester()+1);
                acc_new.setTuitions(acc_new.getTuitions()+dsk.getData().studyFee);
            } else {
                pStu.setFormer(true);
                pStu.setLeaveReason(Inhabitants.NO_GOLD);
                dsk.getData().reputation -= r.nextInt(2) + 3;
                leavingStuds.add(pStu.getNumber());
            }
        });
        dsk.getData().lRoomDorm.forEach(pRD -> clearDorm(pRD));
    }
    
    private void endStudy() {
        dsk.getData().lStu.forEach(pStu -> {
            checkStudyAims(pStu);
            checkHappyness(pStu);
        });
        dsk.getData().lRoomDorm.forEach(pRD -> clearDorm(pRD));
    }
    private void checkHappyness(InhStu pStu) {
        if(pStu.getHappiness()<50) {
            int diff = (50-pStu.getHappiness())*2+1;
            if(r.nextInt(100)<diff) {
                pStu.setFormer(true);
                pStu.setLeaveReason(Inhabitants.UNHAPPY);
                pStu.setLeaveTime(year);
            }
        }
    }
    private void checkStudyAims(InhStu pStu) {
        int reachedGoals=0;
        if(dsk.getData().isPhy && pStu.getAttribute(0) >= dsk.getData().phyVal) {
            reachedGoals++;
        }
        if(dsk.getData().isMen && pStu.getAttribute(1) >= dsk.getData().menVal) {
            reachedGoals++;
        }
        if(dsk.getData().isSoc && pStu.getAttribute(2)>=dsk.getData().socVal) {
            reachedGoals++;
        }
        if(dsk.getData().isMag && pStu.getAttribute(3)>=dsk.getData().magVal) {
            reachedGoals++;
        }
        if(dsk.getData().isTotal 
                && pStu.getAttribute(0)+pStu.getAttribute(1)+pStu.getAttribute(2)+pStu.getAttribute(3) >= dsk.getData().total) {
            reachedGoals++;
        }
        if(dsk.getData().isDuration && pStu.getSemester() >= dsk.getData().duration) {
            reachedGoals++;
        }
        if(dsk.getData().isNrGoals && reachedGoals >= dsk.getData().nrGoals) {
            pStu.setFormer(true);
            pStu.setLeaveReason(Inhabitants.MET_STUDY_GOALS);
            pStu.setStayTime(pStu.getStayTime()+1);
            dsk.getData().reputation += r.nextInt(5)+5;
            finishedStuds.add(pStu.getNumber());
        }
    }
    private void clearDorm(RoomDorm rd) {
        List<Integer> lRemove = new ArrayList();
        rd.getAllInhabitants().stream().filter(nrStu -> 
                stu_isFormer(nrStu)).forEach(nrStu -> 
                        lRemove.add(nrStu));
        lRemove.forEach(nrStu -> rd.removeInhabitant(nrStu));
    }
    private boolean stu_isFormer(int nrS) {
        return dsk.getData().lStu.stream().anyMatch(pStu -> pStu.getNumber()==nrS && pStu.isFormer());
    }
    
    private void newStudents() {
        int pSNr;
        if(5<dsk.getData().reputation) {
            pSNr = dsk.getData().reputation / 2;
        } else {
            pSNr = 5;
        }
        int sNr = r.nextInt(pSNr)+pSNr;
        int availableSpace = 0;
        for(int i=0;i<sNr;i++) {
            try {
                InhStu pStu = new generators.StuGen(dsk, 1);
                dsk.getData().lStu.add(pStu);
            } catch(Exception e) {
            }
        }
        for(RoomDorm pRD : dsk.getData().lRoomDorm) {
            if(pRD.getRoomSize()>pRD.getAllInhabitants().size()) {
                availableSpace = availableSpace+pRD.getRoomSize()-pRD.getAllInhabitants().size();
            }
        }
    }
    
    
    Mainframe dsk;
    
    Accounting acc_new;
    
    Random r;
    
    int year;
    
    List<Integer> leavingStuds;
    List<Integer> finishedStuds;
}
