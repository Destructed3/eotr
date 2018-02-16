/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.rooms;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Die Axt
 */
public class RoomDorm extends Room {
    public RoomDorm(String s, int size, int nr) {
        super();
        this.name=s;
        this.roomSize=size;
        this.roomNr=nr;
        this.quality=1;
        this.inhabitants = new ArrayList();
        this.maintenance = maintenance+roomSize*25;
    }
    
    int quality;
    List<Integer> inhabitants = null;
    
    public void setQuarterQuality(int i) {
        this.quality=i;
    }
    public int getQuarterQuality() {
        return quality;
    }
    public void addStudent(int stu) {
        if(inhabitants.size()<this.roomSize) {
            inhabitants.add(stu);
        } else {
            JOptionPane.showConfirmDialog(null, "Room already full");
        }
    }
    public int getInhabitant(int i) {
        return inhabitants.get(i);
    }
    public List<Integer> getAllInhabitants() {
        return inhabitants;
    }
    public int getNrInhabitants() {
        return inhabitants.size();
    }
    public void removeInhabitant(Integer sNr) {
        this.inhabitants.remove(sNr);
    }
}
