/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import generators.name.RegionNameGenerator;
import gui.Mainframe;
import java.util.Random;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public class RegionGenerator extends resources.regions.Region {
    public RegionGenerator(Mainframe dsk, boolean startRegion) {
        super();
        Random r = new Random();
        setStatus(startRegion,r);
        generate(dsk, r);
        
    }
    
    private void setStatus(boolean startRegion, Random r) {
        this.setKnown(startRegion);
        if(startRegion) {
           this.setStatus(SAVE);
        } else {
           int rdm = r.nextInt(2);
           this.setStatus(rdm);
        }
        if(this.getStatus()==SAVE) {
            this.setExplored(true);
        }
    }
    private void generate(Mainframe dsk, Random r) {
        System.out.println("Generate Region");
        String[] pName=new RegionNameGenerator().generateName();
        this.setName(pName[0]);
        this.createID(dsk);
        setVenues(r);
        setHiddenPlaces(r);
        System.out.println("Generated Region "+this.getID());
    }
    
    private void setVenues(Random r) {
        if(this.getStatus()!=Region.INVADED || this.getStatus()!=Region.LAIR) {
            int nrVen=r.nextInt(3)+1;
            for(int i=0;i<nrVen;i++) {
                assignVen(r);
            }
        }    
    }
    private void assignVen(Random r) {
        int selectedVen = r.nextInt(5);
        switch(selectedVen) {
            default:
                if(this.hasTown()) {
                    assignVen(r);
                    break;
                } else {
                    this.addTown();
                    break;
                }
            case 1:
                if(this.hasHomestead()) {
                    assignVen(r);
                    break;
                } else {
                    this.addHomestead();
                    break;
                }
            case 2:
                if(this.hasTradePost()) {
                    assignVen(r);
                    break;
                } else {
                    this.addTradePost();
                    break;
                }
            case 3:
                if(this.hasCastle()) {
                    assignVen(r);
                    break;
                } else {
                    this.addCastle();
                    break;
                }
            case 4:
                if(this.hasTavern()) {
                    assignVen(r);
                    break;
                } else {
                    this.addTavern();
                    break;
                }
            case 5:
                if(this.hasMonastery()) {
                    assignVen(r);
                    break;
                } else {
                    this.addMonastery();
                    break;
                }
        }
    }
    
    private void setHiddenPlaces(Random r) {
        int places;
        switch(this.getStatus()) {
            default:
                this.setHiddenPlaces(0);
            case Region.WILDERNESS:
                places=r.nextInt(6)+3;
                this.setHiddenPlaces(places);
            case Region.LAIR:
                places=r.nextInt(8)+5;
                this.setHiddenPlaces(places);
            case Region.INVADED:
                places=r.nextInt(15);
                this.setHiddenPlaces(places);
        }
    }
    
    
}
