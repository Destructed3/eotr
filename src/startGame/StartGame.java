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
        dsk.setRes((Resources) ld.getObject(".\\Saves\\save"+save+"\\data.sav"));
    }
    
    private void setRes() {
        getRes().year = 1;
        getRes().gold = 10000;
        getRes().reputation = 0;
        getRes().studyFee = 200;
    }
    private void setGoals() {
        getRes().isPhy = true;
        getRes().isMen = true;
        getRes().isSoc = true;
        getRes().isMag = true;
        getRes().isNrGoals = true;
        getRes().isCP = true;
        getRes().isDuration = true;
        
        getRes().phyVal = 50;
        getRes().menVal = 50;
        getRes().socVal = 50;
        getRes().magVal = 50;
        getRes().nrGoals = 50;
        getRes().nrCP = 25;
        getRes().duration = 6;
    }
    private void setLists() {
        dsk.getRes().lTea = new ArrayList();
        dsk.getRes().lStu = new ArrayList();
        
        dsk.getRes().lRoomStudy = new ArrayList();
        dsk.getRes().lRoomQuarter = new ArrayList();
        dsk.getRes().lRoomDorm = new ArrayList();
        
        dsk.getRes().lAdv = new ArrayList();
        dsk.getRes().lCourse = new ArrayList();
        dsk.getRes().lJob = new ArrayList();
        
        dsk.getRes().lRegion = new ArrayList();
        
        dsk.getRes().lMonster = new ArrayList();
        dsk.getRes().lMssg = new ArrayList();
        
        dsk.getRes().teacherForHire = new InhTea[12];
        
        dsk.getRes().lAccounting = new ArrayList();
    }
    private void addResources(InhTea character) {
        getRes().lTea.add(character);
        getRes().lRoomQuarter.add(startQuarter(character));
        getRes().lAccounting.add(new Accounting(1));
        getRes().lRegion.add(startRegion());
        getRes().lRegion.add(new RegionGenerator(dsk,false));
        getRes().lRegion.add(new RegionGenerator(dsk,false));
        getRes().lRegion.add(new RegionGenerator(dsk,false));
    }
    private RoomQuarter startQuarter(InhTea character) {
        RoomQuarter rs = new RoomQuarter(character.getName()+"'s Hut",40000);
        rs.setResident(character.getNumber());
        getRes().lTea.get(0).setQuarter(40000);
        return rs;
    }
    private Region startRegion() {
        Region reg = new RegionGenerator(dsk,true);
        reg.addTown();
        return reg;
    }
    
    private void fillTfH() {
        loadCharStuff();
        int nrReg = (int) getRes().lRegion.stream().filter(pReg -> 
                pReg.isKnown() && pReg.getStatus()!=Region.INVADED).count();
        int nrTfH_av = nrReg;
        for(int i=0;i<nrReg;i++) {
            nrTfH_av=nrTfH_av+r.nextInt(2);
        }
        for(int i=0;i<getRes().teacherForHire.length;i++) {
            if(getRes().teacherForHire==null && 0<nrTfH_av) {
                getRes().teacherForHire[i]=new TeaGen(false,true,dsk);
                getRes().teacherForHire[i].setTeaFHNr(i);
                nrTfH_av--;
            }
        }
    }
    private void loadCharStuff() {
        getRes().lFeatsDeeds = 
                (List<resources.FeatsDeeds>) new root.LoadDat().getObject(".\\Data\\deeds.fe");
        getRes().lFeatsBackground = 
                (List<FeatsBackground>) new LoadDat().getObject(".\\Data\\bgr.fe");
    }
    private void createJobs() {
        getRes().lJob.removeIf(pAJ -> 
                pAJ.isActive() 
                        && pAJ.getHostNr()==0);
        getRes().lRegion.stream().filter(pReg -> 
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
