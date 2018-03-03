/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import gui.Mainframe;
import resources.activity.*;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public class ActivityGenerator {
    public ActivityGenerator(Mainframe pdsk) {
        this.dsk=pdsk;
    }
    
    public ActivityAdventure generateAdventure() {
        return null;
    }
    
    public ActivityCourse generateCourse() {
        return null;
    }
    
    public void generateJobs(Region reg) {
        this.venues=reg.getVenues();
        this.avJobs=rollJobs();
        for(int venue=0;venue<venues.length;venue++) {
            for(int jobType=0;jobType<4;jobType++) {
                for(int jobCount=0;jobCount<avJobs[venue][jobType];jobCount++) {
                    System.out.println("It's year "+dsk.getRes().year);
                    aj = new ActivityJob(getHeritage_string(venue),jobType,reg,dsk.getRes().year);
                    createID_job();
                    aj.set_AObject();
                    System.out.println("Activity is set for year "+aj.getYear());
                    addJob();
                }
            }
        }
    }
    private void addJob() {
        dsk.getRes().lJob.add(aj);
        if(dsk.getMenuCourse()!=null) {
            dsk.getMenuCourse().setCourseNew(null);
        }
        if(dsk.getMenuInh()!=null) {
            dsk.getMenuInh().refreshTT();
        }
    }
    
    private int[][] rollJobs() {
        java.util.Random r = new java.util.Random();
        int[][] pavJobs={{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        if(venues[0]) {
            for(int i=0;i<pavJobs[0].length;i++) {
                pavJobs[0][i]=r.nextInt(1);
            }
            pavJobs[0][r.nextInt(3)]++;
            pavJobs[0][r.nextInt(3)]++;
        }
        if(venues[1]) {
            for(int i=0;i<pavJobs[1].length;i++) {
                pavJobs[1][i]=r.nextInt(1);
            }
        }
        for(int venue=2;venue<pavJobs[venue].length;venue++) {
            if(venues[venue]) {
                pavJobs[2][venue-2]=r.nextInt(1)+2;
            }
        }
        return pavJobs;
    }
    private String getHeritage_string(int heritage) {
        switch(heritage) {
            default:
                return "Town";
            case 1:
                return "Homestead";
            case 2:
                return "Trade Post";
            case 3:
                return "Castle";
            case 4:
                return "Tavern";
            case 5:
                return "Monastery";
        }
    }
    
    public void createID_job() {
        String beginn = "AJ"+aj.getRegion().getName().substring(0,2);
        System.out.println("Check ID beginning: "+beginn);
        if(checkBeginn(beginn,dsk)) {
            int nr=0;
            while(checkNr(nr,dsk)) {
                nr++;
            }
            System.out.println("Finding ID: "+beginn+checkNrDigits(nr));
            aj.setID(beginn+checkNrDigits(nr));            
        } else {
            System.out.println("Finding ID: "+beginn+"00000");
            aj.setID(beginn+"00000");  
            
        }
    }
    private String checkNrDigits(int nr) {
        if(10000<=nr) {
            return String.valueOf(nr);
        }
        if(1000<=nr) {
            return "0"+nr;
        }
        if(100<=nr) {
            return "00"+nr;
        }
        if(10<=nr) {
            return "000"+nr;
        }
        return "0000"+nr;
    }
    private boolean checkBeginn(String beg, Mainframe dsk) {
        System.out.println("Compare with");
        return dsk.getRes().lRegion.stream().anyMatch(
                region -> region.getID().substring(0,4).equals(beg));
    }
    private boolean checkNr(int nr, Mainframe dsk) {
        return dsk.getRes().lRegion.stream().anyMatch(
                region -> Integer.parseInt(region.getID().substring(5,9))==nr);
    }
    
    Mainframe dsk;
    
    int[][] avJobs;
    boolean[] venues;
    
    ActivityJob aj;
    
    
    
}
