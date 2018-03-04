/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import gui.Mainframe;

/**
 *
 * @author Die Axt
 */
public class GetDemand {
    public GetDemand(Mainframe pdsk) {
        this.dsk=pdsk;
    }
    
    public String getDemand(int attr) {
        if(dsk.getData().lStu.isEmpty()) {
            return "No demand yet";
        } else {
            switch(attr) {
                default:
                    return getDem(getDem_Phy());
                case 1:
                    return getDem(getDem_Men());
                case 2:
                    return getDem(getDem_Soc());
                case 3:
                    return getDem(getDem_Mag());
            }
        }
    }
    
    private String getDem(int attrDem) {
        int divider = dsk.getData().lStu.size();
        double avgDem=attrDem/divider;
        if(30<avgDem) {
            return "Maximal demand";
        } else if(25<avgDem) {
            return "Massive demand";
        } else if(20<avgDem){
            return "High demand";
        } else if(15<avgDem) {
            return "Normal demand";
        } else if(10<avgDem) {
            return "Low demand";
        } else if(5<avgDem) {
            return "Minimal demand";
        } else {
            return "No demand";
        }
    }
    
    private int getDem_Phy() {
        dem_Phy=0;
        dsk.getData().lStu.stream().filter(pStu -> !pStu.isFormer()).forEach(pStu -> {
            dem_Phy=dem_Phy+pStu.getInterest(0);
        });
        return dem_Phy;
    }
    private int getDem_Men() {
        dem_Men=0;
        dsk.getData().lStu.stream().filter(pStu -> !pStu.isFormer()).forEach(pStu -> {
            dem_Men=dem_Men+pStu.getInterest(1);
        });
        return dem_Men;
    }
    private int getDem_Soc() {
        dem_Soc=0;
        dsk.getData().lStu.stream().filter(pStu -> !pStu.isFormer()).forEach(pStu -> {
            dem_Soc=dem_Soc+pStu.getInterest(2);
        });
        return dem_Soc;
    }
    private int getDem_Mag() {
        dem_Mag=0;
        dsk.getData().lStu.stream().filter(pStu -> !pStu.isFormer()).forEach(pStu -> {
            dem_Mag=dem_Mag+pStu.getInterest(3);
        });
        return dem_Mag;
    }
    
    Mainframe dsk;
    
    int dem_Phy;
    int dem_Men;
    int dem_Soc;
    int dem_Mag;
    
}
