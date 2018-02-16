/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.name;

import java.util.Random;
import resources.rooms.RoomStudy;

/**
 *
 * @author Die Axt
 */
public class RoomNameGenerator  {
    Random r = new Random();
    RoomStudy room;
    NameGenerator names = new NameGenerator();
    String[] before = {"Chamber of ","Corner of ","Resort of ", "Board of ","Freedom of "};
    String[] flavor = {"Memorial","Hope"," Freedom","Rest"};
    String[] after = {"Chamber","Room","Space","Hall","Saloon","Corner","Office","Board","Asylum","Refuge","Resort","Rest","Freedom","Memorial","Residence"};
           
    public RoomNameGenerator() {
    }
    
    public String getRoomName() {
        String s;
        int coin=r.nextInt(99);
        if(coin>79) {
            s=before[r.nextInt(4)]+names.randomName(false)+"'s "+after[r.nextInt(14)];
        } else if(coin>49) {
            s=before[r.nextInt(4)]+names.randomName(false);
        } else {
            s=names.randomName(false)+"'s "+after[r.nextInt(14)];
        }
        
        return s;
    }
    
    
}
