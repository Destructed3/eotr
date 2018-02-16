/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;
import resources.Inhabitants.InhStu;
import resources.Inhabitants.InhTea;
import resources.activity.ActivityAdventure;
import resources.activity.ActivityCourse;
import resources.activity.ActivityJob;
import resources.regions.Region;
import resources.rooms.RoomDorm;
import resources.rooms.RoomQuarter;
import resources.rooms.RoomStudy;
import root.Goals;
import root.Variables;

/**
 *
 * @author Die Axt
 */
public class Resources {
    public Resources() {        
    }
    
    public void setVariables(Variables pVar) {
        this.var=pVar;
    }
    public Variables getVariables() {
        if(var==null) {
            var = new Variables();
        }
        return var;
    }
    
    
    public void setGoals(Goals pGoals) {
        this.goals=pGoals;
    }
    public Goals getGoals() {
        return goals;
    }
    
    
    public void setLTea(List<InhTea> pLTea) {
        this.lTea=pLTea;
    }
    public void addTea(InhTea pTea) {
        lTea.add(pTea);
    }
    public List<InhTea> getLTea() {
        return lTea;
    }
    
    public void setLStu(List<InhStu> pLStu) {
        this.lStu=pLStu;
    }
    public void addStu(InhStu pStu) {
        this.lStu.add(pStu);
    }
    public List<InhStu> getLStu() {
        return lStu;
    }
    
    
    public void setLRS(List<RoomStudy> pLRS) {
        this.lRoomStudy=pLRS;
    }
    public void adRS(RoomStudy pRS) {
        this.lRoomStudy.add(pRS);
    }
    public List<RoomStudy> getLRS() {
        return lRoomStudy;
    }
    
    public void setLRQ(List<RoomQuarter> pLRQ) {
        this.lRoomQuarter=pLRQ;
    }
    public void addRQ(RoomQuarter pRQ) {
        this.lRoomQuarter.add(pRQ);
    }
    public List<RoomQuarter> getLRQ() {
        return lRoomQuarter;
    }
    
    public void setLRD(List<RoomDorm> pLRD) {
        this.lRoomDorm=pLRD;
    }
    public void addRD(RoomDorm pRD) {
        this.lRoomDorm.add(pRD);
    }
    public List<RoomDorm> getLRD() {
        return lRoomDorm;
    }
    
        
    public void setLAA(List<ActivityAdventure> pLAA) {
        this.lAdv=pLAA;
    }
    public ActivityAdventure getAdv(String id) {
        return lAdv.stream().filter(pAdv -> pAdv.getID().equals(id)).findAny().get();
    }
    public List<ActivityAdventure> getLAA() {
        return lAdv;
    }
    public void addAdventure(ActivityAdventure adv) {
        this.lAdv.add(adv);
    }
    
    public void setLAC(List<ActivityCourse> pLAC) {
        this.lCourse=pLAC;
    }
    public void addAC(ActivityCourse pAC) {
        this.lCourse.add(pAC);
    }
    public List<ActivityCourse> getLAC() {
        return lCourse;
    }
    
    public void setLAJ(List<ActivityJob> pLAJ) {
        this.lJob=pLAJ;
    }
    public void addJob(ActivityJob pAJ) {
        this.lJob.add(pAJ);
    }
    public ActivityJob getJob(String jobID) {
        return lJob.stream().filter(pJob -> pJob.getID().equals(jobID)).findAny().get();
    }
    public List<ActivityJob> getLAJ() {
        return lJob;
    }
    
    
    public void setLReg(List<Region> pReg) {
        this.lReg=pReg;
    }
    public Region getRegion(String id) {
        return lReg.stream().filter(pReg -> pReg.getID().equals(id)).findAny().get();
    }
    public List<Region> getLReg() {
        return lReg;
    }
    public void addRegion(Region reg) {
        lReg.add(reg);
    }
    
    
    public void setAcc(List<Accounting> pAcc) {
        this.lAccounting=pAcc;
    }
    public void addAcc(Accounting acc) {
        this.lAccounting.add(acc);
    }
    public List<Accounting> getLAcc() {
        return lAccounting;
    }
    public Accounting getAcc_year(int year) {
        return lAccounting.stream().filter(pAcc -> pAcc.getYear()==year).findAny().get();
    }
    
    
    public List<Monster> getLMon() {
        return lMon;
    }
    public void setLMon(List<Monster> pMon) {
        this.lMon=pMon;
    }
    public Monster getMonster(String mID) {
        return lMon.stream().filter(pMon -> pMon.getID().equals(mID)).findAny().get();
    }
    public void addMonster(Monster mon) {
        lMon.add(mon);
    }
    
    public void setLMssg(List<Messages> pMes) {
        this.lMssg=pMes;
    }
    public Messages getMssg(int mssgNr) {
        return lMssg.get(mssgNr);
    }
    public List<Messages> getMssgs() {
        return lMssg;
    }
    
    public void setTfH(InhTea[] pTFH) {
        this.teaFH=pTFH;
    }
    public InhTea[] getTfH() {
        return teaFH;
    }
    
    public void setLDeeds(List<FeatsDeeds> pDee) {
        this.lFeatsDeeds=pDee;
    }
    public List<FeatsDeeds> getLDeeds() {
        return lFeatsDeeds;
    }
    
    public List<FeatsBackground> getLBckg() {
        return lFeatsBackground;
    }
    public void setLBckg(List<FeatsBackground> pBckg) {
        this.lFeatsBackground=pBckg;
    }
    
    Variables var = null;
    Goals goals = null;
    
    List<InhTea> lTea = null;
    List<InhStu> lStu = null;
    
    List<RoomStudy> lRoomStudy = null;
    List<RoomQuarter> lRoomQuarter = null;
    List<RoomDorm> lRoomDorm = null;
    
    List<ActivityCourse> lCourse = null;
    List<ActivityAdventure> lAdv = null;
    List<ActivityJob> lJob = null;
    
    List<Region> lReg = null;
    
    List<Messages> lMssg = null;
    List<Monster> lMon = null;
    
    List<resources.Accounting> lAccounting = null;
    
    InhTea[] teaFH = null;
    
    List<FeatsDeeds> lFeatsDeeds = null;
    List<FeatsBackground> lFeatsBackground = null;
}
