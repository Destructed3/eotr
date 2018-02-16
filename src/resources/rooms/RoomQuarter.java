/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.rooms;

/**
 *
 * @author Die Axt
 */
public class RoomQuarter extends Room {
    
    public RoomQuarter(String name, int nr) {
        super();
        this.roomSize=1;
        this.name=name;
        this.roomNr=nr;
        this.resident=0;
        this.furniture=null;
        this.maintenance=maintenance+500;
    }
    int resident;
    public void setResident(int pTeacher) {
        this.resident=pTeacher;
    }
    public int getResident() {
        return resident;
    }
}
