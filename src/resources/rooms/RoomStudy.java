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
public class RoomStudy extends Room {
    public RoomStudy(String s, int size, int nr) {
        super();
        this.name=s;
        this.roomSize=size;
        this.roomNr=nr;
        for(int hour=0; hour<10;hour++) {
            for(int day=0;day<7;day++) {
                usage[hour][day]="";
            }
        }
        this.maintenance = maintenance+roomSize*15;
    }

    String[][] usage = new String[10][7];
    
    public void setRoomUsage(int hour,int day, String cID) {
        this.usage[hour][day]=cID;
    }
    public void setRoomUsageFull(String[][] dat) {
        this.usage=dat;
    }
    public String getRoomUsage(int hour, int day) {
        return usage[hour][day];
    }
    public String[][] getRoomUsageFull() {
        return usage;
    }
    public void resetRoomUsage() {
        for(int hour=0;hour<10;hour++) {
            for(int day=0;day<7;day++) {
                this.usage[hour][day]="";
            }
        }
    }
}
