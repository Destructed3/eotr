/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

/**
 *
 * @author Die Axt
 */
public class Variables implements java.io.Serializable {
    public Variables() {
        
    }
    
    public void setYear(int newY) {
        this.year=newY;
    }
    public int getYear() {
        return year;
    }
    
    public void setGold(int newG) {
        this.gold=newG;
    }
    public void addGold(int addG) {
        this.gold=gold+addG;
    }
    public void substractGold(int subG) {
        this.gold=gold-subG;
    }
    public int getGold() {
        return gold;
    }
    
    public void setReputation(int newRep) {
        this.reputation=newRep;
    }
    public void addReputation(int addRep) {
        this.reputation=reputation+addRep;
    }
    public void substractReputation(int subRep) {
        this.reputation=reputation-subRep;
    }
    public int getReputation() {
        return reputation;
    }
    
    public void setStudyFee(int fee) {
        this.studyFee=fee;
    }
    public int getStudyFee() {
        return studyFee;
    }
    
    int year;
    int gold;
    int reputation;
    
    int studyFee;
    
}
