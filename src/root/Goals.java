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
public class Goals implements java.io.Serializable {
    public Goals() {
    }
    public void setIsPhy(boolean newB) {
        this.isPhy=newB;
    }
    public boolean isPhy() {
        return isPhy;
    }
    
    public void setIsMen(boolean newB) {
        this.isMen=newB;
    }
    public boolean isMen() {
        return isMen;
    }
    
    public void setIsSoc(boolean newB) {
        this.isSoc=newB;
    }
    public boolean isSoc() {
        return isSoc;
    }
    
    public void setIsMag(boolean newB) {
        this.isMag=newB;
    }
    public boolean isMag() {
        return isMag;
    }
    
    public void setIsTotal(boolean newB) {
        this.isTotal=newB;
    }
    public boolean isTotal() {
        return isTotal;
    }
    
    public void setIsCP(boolean newB) {
        this.isCP=newB;
    }
    public boolean isCP() {
        return isCP;
    }
    
    public void setIsNrGoals(boolean newB) {
        this.isNrGoals=newB;
    }
    public boolean isNrGoals() {
        return isNrGoals;
    }
    
    public void setIsDuration(boolean newB) {
        this.isDuration=newB;
    }
    public boolean isDuration() {
        return isDuration;
    }
    
    public void setValPhy(int val) {
        this.phyVal=val;
    }
    public int getValPhy() {
        return phyVal;
    }
    
    public void setValMen(int val) {
        this.menVal=val;
    }
    public int getValMen() {
        return menVal;
    }
    
    public void setValSoc(int val) {
        this.socVal=val;
    }
    public int getValSoc() {
        return socVal;
    }
    
    public void setValMag(int val) {
        this.magVal=val;
    }
    public int getValMag() {
        return magVal;
    }
    
    public void setValTotal(int val) {
        this.total=val;
    }
    public int getValTotal() {
        return total;
    }
    
    public void setNrCP(int nr) {
        this.nrCP=nr;
    }
    public int getNrCP() {
        return nrCP;
    }
    
    public void setNrGoals(int nr) {
        this.nrGoals=nr;
    }
    public int getNrGoals() {
        return nrGoals;
    }
    
    public void setDuration(int dur) {
        this.duration=dur;
    }
    public int getDuration() {
        return duration;
    }
    
    boolean isPhy;
    boolean isMen;
    boolean isSoc;
    boolean isMag;
    boolean isTotal;
    boolean isCP;
    boolean isNrGoals;
    boolean isDuration;
    
    int phyVal;
    int menVal;
    int socVal;
    int magVal;
    int total;
    int nrCP;
    int nrGoals;
    int duration;
}
