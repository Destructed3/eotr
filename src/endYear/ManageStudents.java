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
        this.year=dsk.getRes().getVariables().getYear();
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
        dsk.getRes().getLStu().stream().filter(pStu -> 
                !pStu.isFormer()).forEach(pStu -> {
            if(pStu.getGold()>=dsk.getRes().getVariables().getStudyFee()) {
                pStu.payFees(dsk.getRes().getVariables().getStudyFee());
                pStu.setSemester(pStu.getSemester()+1);
                acc_new.setTuitions(acc_new.getTuitions()+dsk.getRes().getVariables().getStudyFee());
            } else {
                pStu.setFormer(true);
                pStu.setLeaveReason(Inhabitants.NO_GOLD);
                dsk.getRes().getVariables().substractReputation(r.nextInt(2)+3);
                leavingStuds.add(pStu.getNumber());
            }
        });
        dsk.getRes().getLRD().forEach(pRD -> clearDorm(pRD));
    }
    
    private void endStudy() {
        dsk.getRes().getLStu().forEach(pStu -> {
            checkStudyAims(pStu);
            checkHappyness(pStu);
        });
        dsk.getRes().getLRD().forEach(pRD -> clearDorm(pRD));
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
        if(dsk.getRes().getGoals().isPhy() && pStu.getAttribute(0)>=dsk.getRes().getGoals().getValPhy()) {
            reachedGoals++;
        }
        if(dsk.getRes().getGoals().isMen() && pStu.getAttribute(1)>=dsk.getRes().getGoals().getValMen()) {
            reachedGoals++;
        }
        if(dsk.getRes().getGoals().isSoc() && pStu.getAttribute(2)>=dsk.getRes().getGoals().getValSoc()) {
            reachedGoals++;
        }
        if(dsk.getRes().getGoals().isMag() && pStu.getAttribute(3)>=dsk.getRes().getGoals().getValMag()) {
            reachedGoals++;
        }
        if(dsk.getRes().getGoals().isTotal() && pStu.getAttribute(0)+pStu.getAttribute(1)+pStu.getAttribute(2)+pStu.getAttribute(3)>=dsk.getRes().getGoals().getValTotal()) {
            reachedGoals++;
        }
        if(dsk.getRes().getGoals().isDuration() && pStu.getSemester()>dsk.getRes().getGoals().getDuration()) {
            reachedGoals++;
        }
        if(dsk.getRes().getGoals().isNrGoals() && reachedGoals>=dsk.getRes().getGoals().getNrGoals()) {
            pStu.setFormer(true);
            pStu.setLeaveReason(Inhabitants.MET_STUDY_GOALS);
            pStu.setStayTime(pStu.getStayTime()+1);
            dsk.getRes().getVariables().addReputation(r.nextInt(5)+5);
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
        return dsk.getRes().getLStu().stream().anyMatch(pStu -> pStu.getNumber()==nrS && pStu.isFormer());
    }
    
    private void newStudents() {
        int pSNr;
        if(5<dsk.getRes().getVariables().getReputation()) {
            pSNr = dsk.getRes().getVariables().getReputation()/2;
        } else {
            pSNr = 5;
        }
        int sNr = r.nextInt(pSNr)+pSNr;
        int availableSpace = 0;
        for(int i=0;i<sNr;i++) {
            try {
                InhStu pStu = new generators.StuGen(dsk, 1);
                dsk.getRes().getLStu().add(pStu);
            } catch(Exception e) {
            }
        }
        for(RoomDorm pRD : dsk.getRes().getLRD()) {
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
