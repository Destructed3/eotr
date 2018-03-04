/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;
import resources.Inhabitants.InhStu;
import resources.Inhabitants.InhTea;
import resources.activity.ActivityAdventure;
import resources.activity.ActivityCourse;
import resources.activity.ActivityJob;
import resources.regions.Region;
import resources.rooms.RoomDorm;
import resources.rooms.RoomQuarter;
import resources.rooms.RoomStudy;

/**
 *
 * @author Die Axt
 */
public class Data {
    public Data() {        
    }
    
    // Variables
    public int year;
    public int gold;
    public int reputation;    
    public int studyFee;
    
    // Goals
    public boolean isPhy;
    public boolean isMen;
    public boolean isSoc;
    public boolean isMag;
    public boolean isTotal;
    public boolean isCP;
    public boolean isNrGoals;
    public boolean isDuration;
    
    public int phyVal;
    public int menVal;
    public int socVal;
    public int magVal;
    public int total;
    public int nrCP;
    public int nrGoals;
    public int duration;
    
    // Lists    
    public List<InhTea> lTea = null;
    public List<InhStu> lStu = null;
    
    public List<RoomStudy> lRoomStudy = null;
    public List<RoomQuarter> lRoomQuarter = null;
    public List<RoomDorm> lRoomDorm = null;
    
    public List<ActivityCourse> lCourse = null;
    public List<ActivityAdventure> lAdv = null;
    public List<ActivityJob> lJob = null;
    
    public List<Region> lRegion = null;
    
    public List<Messages> lMssg = null;
    public List<Monster> lMonster = null;
    
    public List<resources.Accounting> lAccounting = null;
    
    public InhTea[] teacherForHire = null;
    
    public List<FeatsDeeds> lFeatsDeeds = null;
    public List<FeatsBackground> lFeatsBackground = null;
    
}
