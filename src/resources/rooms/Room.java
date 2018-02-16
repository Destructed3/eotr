/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.rooms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Die Axt
 */
public abstract class Room implements Serializable {
    
    public Room() {
        this.maintenance=500;
        this.isPayed=true;
    }
    
    String name;
    int roomSize;
    int roomNr;
    int buildCost;
    int maintenance;
    boolean isPayed;
    List furniture = Collections.synchronizedList(new ArrayList());
    
    public void setRoomName(String s) {
        this.name=s;
    }
    public String getRoomName() {
        return name;
    }
    public void setRoomSize(int i) {
        this.roomSize=i;
    }
    public int getRoomSize() {
        return roomSize;
    }
    public void setRoomNr(int newNr) {
        this.roomNr=newNr;
    }
    public int getRoomNr() {
        return roomNr;
    }
    public void setBuildCost(int bc) {
        this.buildCost=bc;
    }
    public int getBuildCost() {
        return buildCost;
    }
    public int getMaintenance() {
        return maintenance;
    }
    public void setMaintenance(int maint) {
        this.maintenance=maint;
    }
    public boolean isMaintained() {
        return isPayed;
    }
    public void setMaintained(boolean pay) {
        this.isPayed=pay;
    }
    public void addRoomFurniture(int eq) {
        this.furniture.add(eq);
    }
    public int getRoomFurniture(int eqNr) {
        return (int)furniture.get(eqNr);
    }
    public List getAllRoomFurniture() {
        return furniture;
    }
}
