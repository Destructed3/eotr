/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import resources.rooms.RoomQuarter;
import resources.rooms.RoomStudy;
import resources.rooms.RoomDorm;
import resources.Inhabitants.InhTea;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Die Axt 
 */
public class ResourceBar extends JPanel {
    public ResourceBar(Mainframe pdsk) {  
        this.dsk=pdsk;
        resTea = new int[2];
        resTea[0] = dsk.getRes().year;
        resTea[1] = avSpaceRQ();
        resStu = new int[2];
        resStu[0] = (int)dsk.getRes().lStu.stream().filter(pStu -> 
                !pStu.isFormer()).count();
        resStu[1] = avSpaceRD();
        run();
    }
    Mainframe dsk;
    int[] resTea = null;
    int[] resStu = null;
    
    JLabel year = null;
    JLabel gold = null;
    JLabel income = null;
    JLabel upkeep = null;
    JLabel tea = null;
    JLabel stu = null;
    JLabel courses = null;
    JLabel lRep = null;
    
    private void run() {
        this.setLayout(new java.awt.FlowLayout());
        if(year==null) {
            year = new JLabel("Year: " + dsk.getRes().year + " | ");
            this.add(year);
        }
        if(gold==null) {
            gold = new JLabel("Gold: " + dsk.getRes().gold + " | ");
            this.add(gold);
        }
        if(income==null) {
            income = new JLabel("Income: " + getIncome() + " | ");
            this.add(income);
        }
        if(upkeep==null) {
            upkeep = new JLabel("Upkeep: "+getUpkeep()+" | ");
            this.add(upkeep);
        }
        if(tea==null) {      
            tea = new JLabel("Teacher: "+getNrTea()+"/"+getNrRQ()+" | ");
            this.add(tea);
        }
        if(stu==null) {
            stu = new JLabel("Students: "+resStu[0]+"/"+resStu[1]+" | ");
            this.add(stu);
        }
        if(courses==null) {
            courses = new JLabel("Courses: "+0+" | ");
            this.add(courses);
        }
        if(this.lRep==null) {
            lRep = new JLabel("Reputation: "+dsk.repString());
            this.add(lRep);
        }
    }
    private int avSpaceRD() {
        int space=0;
        for(resources.rooms.RoomDorm pRD : dsk.getRes().lRoomDorm) {
            space=space+pRD.getRoomSize();
        }
        return space;
    }
    private int avSpaceRQ() {
        int space=0;
        for(resources.rooms.RoomQuarter pRQ : dsk.getRes().lRoomQuarter) {
            space=space+pRQ.getRoomSize();
        }
        return space;
    }
    private int getIncome() {
        return dsk.getRes().gold * (int)dsk.getRes().lStu.stream().filter(pStu -> 
                !pStu.isFormer()).count();
    }
    private int getUpkeep() {
        int upk=0;
        for(InhTea pTea : dsk.getRes().lTea) {
            upk=upk+pTea.getCost();
        }
        for(RoomDorm pRD : dsk.getRes().lRoomDorm) {
            upk=upk+pRD.getMaintenance();
        }
        for(RoomQuarter pRQ : dsk.getRes().lRoomQuarter) {
            upk=upk+pRQ.getMaintenance();
        }
        for(RoomStudy pRS : dsk.getRes().lRoomStudy) {
            upk=upk+pRS.getMaintenance();
        }
        return upk;
    }
    private int getNrTea() {
        return dsk.getRes().lTea.size();
    }
    private int getNrRQ() {
        return dsk.getRes().lRoomQuarter.size();
    }
    
    public void setJLGold(int newG) {
        gold.setText("Gold: "+newG+" | ");
    }
    public void setJLIncome() {
        if(income==null) {
            income = new JLabel("Income: "+getIncome()+" | ");
        } else {
            income.setText("Income: "+getIncome()+" | ");
        }
    }
    public void setJLUpkeep() {
        if(upkeep==null) {
            upkeep = new JLabel("Upkeep: "+getUpkeep()+" | ");
        } else {
            upkeep.setText("Upkeep: "+getUpkeep()+" | ");
        }
    }
    public void setJLYear(int newY) {        
        year.setText("Year: "+newY+" | ");
    }
    public void setJLTea_addTea() {        
        resTea[0]=dsk.getRes().lTea.size();
        tea.setText("Teacher: "+resTea[0]+"/"+resTea[1]+" | ");
    }
    public void setJLTea_addSpace() {
        tea.setText("Teacher: "+getNrTea()+"/"+getNrRQ()+" | ");
    }
    public void setJLStu_addStu() {
        resStu[0]=dsk.getRes().lStu.size();
        stu.setText("Students: "+resStu[0]+"/"+resStu[1]+" | ");
    }
    public void setJLStu_addSpace() {
        resStu[1]=avSpaceRD();
        stu.setText("Students: "+resStu[0]+"/"+resStu[1]+" | ");
    }
    public void setJLCour_addCourse() {
        courses.setText(("Courses: "+dsk.getRes().lCourse.size()));
    }
}
