/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startGame;

import generators.ActivityGenerator;
import generators.RegionGenerator;
import generators.TeaGen;
import gui.Mainframe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import resources.Accounting;
import resources.FeatsBackground;
import resources.Inhabitants.InhTea;
import resources.Resources;
import resources.regions.Region;
import resources.rooms.RoomQuarter;
import root.Goals;
import root.LoadDat;
import root.Variables;

/**
 *
 * @author Die Axt
 */
public class StartGame {
    public StartGame(Mainframe pdsk) {
        this.dsk=pdsk;
        this.r=new Random();
        this.aG = new ActivityGenerator(dsk);
    }
    
    public void newGame(InhTea character) {
        setRes();
        setGoals();
        setLists();
        addResources(character);
        fillTfH();
        createJobs();
    }
    
    
    
    public void newRound() {
        loadGame("NewYear");
        fillTfH();
        createJobs();
    }
    
    public void loadGame(String save) {
        this.ld = new LoadDat();
        getRes().setLTea((List) ld.getObject(".\\Saves\\save"+save+"\\teacher.sav"));
        getRes().setLStu((List) ld.getObject(".\\Saves\\save"+save+"\\student.sav"));
        getRes().setTfH((InhTea[]) ld.getObject(".\\Saves\\save"+save+"\\tfh.sav"));
        getRes().setLRS((List) ld.getObject(".\\Saves\\save"+save+"\\roomStudy.sav"));
        getRes().setLRQ((List) ld.getObject(".\\Saves\\save"+save+"\\roomQua.sav"));
        getRes().setLRD((List) ld.getObject(".\\Saves\\save"+save+"\\roomDorm.sav"));
        getRes().setLAC((List) ld.getObject(".\\Saves\\save"+save+"\\actCourse.sav"));
        getRes().setLAJ((List) ld.getObject(".\\Saves\\save"+save+"\\actJob.sav"));
        getRes().setLAA((List) ld.getObject(".\\Saves\\save"+save+"\\actAdv.sav"));
        getRes().setLMon((List) ld.getObject(".\\Saves\\save"+save+"\\monster.sav"));
        getRes().setGoals((Goals) ld.getObject(".\\Saves\\save"+save+"\\goals.sav"));
        getRes().setAcc((List<Accounting>) ld.getObject(".\\Saves\\save"+save+"\\accounting.sav"));
        getRes().setLReg((List<Region>) ld.getObject(".\\Saves\\save"+save+"\\regions.sav"));
        getRes().setVariables((Variables) ld.getObject(".\\Saves\\save"+save+"\\var.sav"));
    }
    
    private void setRes() {
        getRes().getVariables().setYear(1);
        getRes().getVariables().setGold(10000);
        getRes().getVariables().setReputation(0);
        getRes().getVariables().setStudyFee(200);
    }
    private void setGoals() {
        getRes().setGoals(new Goals());
        getRes().getGoals().setIsPhy(true);
        getRes().getGoals().setIsMen(true);
        getRes().getGoals().setIsSoc(true);
        getRes().getGoals().setIsMag(true);
        getRes().getGoals().setIsNrGoals(true);
        getRes().getGoals().setIsCP(false);
        getRes().getGoals().setIsDuration(false);
        
        getRes().getGoals().setValPhy(50);
        getRes().getGoals().setValMen(50);
        getRes().getGoals().setValSoc(50);
        getRes().getGoals().setValMag(50);
        getRes().getGoals().setNrGoals(4);
        getRes().getGoals().setNrCP(25);
        getRes().getGoals().setDuration(6);
    }
    private void setLists() {
        dsk.getRes().setLTea(new ArrayList());
        dsk.getRes().setLStu(new ArrayList());
        
        dsk.getRes().setLRS(new ArrayList());
        dsk.getRes().setLRQ(new ArrayList());
        dsk.getRes().setLRD(new ArrayList());
        
        dsk.getRes().setLAA(new ArrayList());
        dsk.getRes().setLAC(new ArrayList());
        dsk.getRes().setLAJ(new ArrayList());
        
        dsk.getRes().setLReg(new ArrayList());
        
        dsk.getRes().setLMon(new ArrayList());
        dsk.getRes().setLMssg(new ArrayList());
        
        dsk.getRes().setTfH(new InhTea[12]);
        
        dsk.getRes().setAcc(new ArrayList());
    }
    private void addResources(InhTea character) {
        getRes().addTea(character);
        getRes().addRQ(startQuarter(character));
        getRes().addAcc(new Accounting(1));
        getRes().getLReg().add(startRegion());
        getRes().getLReg().add(new RegionGenerator(dsk,false));
        getRes().getLReg().add(new RegionGenerator(dsk,false));
        getRes().getLReg().add(new RegionGenerator(dsk,false));
    }
    private RoomQuarter startQuarter(InhTea character) {
        RoomQuarter rs = new RoomQuarter(character.getName()+"'s Hut",40000);
        rs.setResident(character.getNumber());
        getRes().getLTea().get(0).setQuarter(40000);
        return rs;
    }
    private Region startRegion() {
        Region reg = new RegionGenerator(dsk,true);
        reg.addTown();
        return reg;
    }
    
    private void fillTfH() {
        loadCharStuff();
        int nrReg = (int) getRes().getLReg().stream().filter(pReg -> 
                pReg.isKnown() && pReg.getStatus()!=Region.INVADED).count();
        int nrTfH_av = nrReg;
        for(int i=0;i<nrReg;i++) {
            nrTfH_av=nrTfH_av+r.nextInt(2);
        }
        for(int i=0;i<getRes().getTfH().length;i++) {
            if(getRes().getTfH()==null && 0<nrTfH_av) {
                getRes().getTfH()[i]=new TeaGen(false,true,dsk);
                getRes().getTfH()[i].setTeaFHNr(i);
                nrTfH_av--;
            }
        }
    }
    private void loadCharStuff() {
        getRes().setLDeeds((List<resources.FeatsDeeds>) new root.LoadDat().getObject(".\\Data\\deeds.fe"));
        getRes().setLBckg((List<FeatsBackground>) new LoadDat().getObject(".\\Data\\bgr.fe"));
    }
    private void createJobs() {
        getRes().getLAJ().removeIf(pAJ -> 
                pAJ.isActive() 
                        && pAJ.getHostNr()==0);
        getRes().getLReg().stream().filter(pReg -> 
                pReg.isKnown() 
                        && pReg.getStatus()!=Region.INVADED 
                        && pReg.getStatus()!=Region.LAIR).forEach(pReg -> {
            aG.generateJobs(pReg);
        });
    }
    
    private Resources getRes() {
        return dsk.getRes();
    }
    
    Mainframe dsk;
    Random r;
    ActivityGenerator aG;
    LoadDat ld;
   
}
