/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.Serializable;

/**
 *
 * @author Die Axt
 */
public abstract class Feats implements Serializable {
    public Feats() {
        
    }
    
    String name;
    String description;
    int[] attributes; //0 phy | 1 men | 2 soc | 3 mag
    double learn;
    int featNr;
    public void setName(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
    public void setDescription(String desc) {
        this.description=desc;
    }
    public String getDescription() {
        return description;
    }
    public void setAttribute(int aNr, int aVal) {
        this.attributes[aNr]=aVal;
    }
    public void setAttributes(int[] pattributes) {
        this.attributes=pattributes;
    }
    public int getAttribute(int aNr) {
        return attributes[aNr];
    }
    public int[] getAttributes() {
        return attributes;
    }
    public void setLearn(double bonus) {
        this.learn=bonus;
    }
    public double getLearn() {
        return learn;
    }
    public void setNr(int nr) {
        this.featNr=nr;
    }
    public int getNr() {
        return featNr;
    }
}
