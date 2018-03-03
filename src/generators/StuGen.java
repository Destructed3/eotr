/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import generators.name.NameGenerator;
import gui.Mainframe;
import java.util.List;
import resources.Inhabitants.InhStu;
import java.util.Random;
import resources.FeatsBackground;
import resources.rooms.RoomDorm;

/**
 *
 * @author Die Axt
 */
public class StuGen extends InhStu {
    
    public StuGen(Mainframe pdsk, int semester) {  
        super();
        studGenerator(pdsk, semester);
    }
    private void studGenerator(Mainframe dsk, int semester) {
        Random r = new Random();
        NameGenerator ng = new NameGenerator();
        List<FeatsBackground> lfb = dsk.getRes().lFeatsBackground;
        FeatsBackground fb;
        try{
            fb = lfb.get(r.nextInt(lfb.size()-1));
        } catch (java.lang.IllegalArgumentException e) {
            fb = lfb.get(0);
        }
        RoomDorm rd = checkDorm(dsk.getRes().lRoomDorm);
        String pname = ng.randomName(false);
        this.setName(pname);
        this.setAttribute(0, 5+r.nextInt(10)+fb.getAttribute(0));
        this.setAttribute(1, 5+r.nextInt(10)+fb.getAttribute(1));
        this.setAttribute(2, 5+r.nextInt(10)+fb.getAttribute(2));
        this.setAttribute(3, 5+r.nextInt(10)+fb.getAttribute(3));
        this.addGold(50+r.nextInt(100)+fb.getGold());
        this.setSemester(semester);
        this.setLearn(((double) r.nextInt(40)/100)+fb.getLearn());
        this.setBckg(fb);
        this.setDorm(rd.getRoomNr());
        this.setInterests(defineInterests(r));
        int stuNr = 20000;
        while(checkStuNr(dsk, stuNr)) {
            stuNr++;
        }
        this.setNumber(stuNr);
        rd.addStudent(stuNr);
    }
    private boolean checkStuNr(Mainframe dsk, int nr) {
        return dsk.getRes().lStu.stream().anyMatch(pstu -> pstu.getNumber()==nr);
    }    
    private RoomDorm checkDorm(List<RoomDorm> lrd) {
        return lrd.stream().filter(prd -> prd.getNrInhabitants()<prd.getRoomSize()).findFirst().get();
    }
    public int[] defineInterests(Random r) {
        int[] interests;
        interests = new int[6]; //0 phy | 1 men | 2 soc | 3 mag | 4 job | 5 adv
        for(int i=0;i<6;i++) {
            interests[i] = r.nextInt(34);
        }
        return interests;
    }
        
}
