/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author Die Axt
 */
public class FeatsDeeds extends Feats{
    public FeatsDeeds(String pname, String desc, int[] pattributes,int pfame, double plearn, int nr) {
        this.name=pname;
        this.description=desc;
        this.attributes=pattributes;
        this.fame=pfame;
        this.learn=plearn;
        this.grade=1;
        this.featNr=nr;
    }
    
    int fame;
    int grade;
    String[] epicness = {"Minor","Heroic","Legendary"};
    double[] multiplier = {0.8,1,1.2};
    public void setFame(int bonus) {
        this.fame=bonus;
    }
    public int getFame() {
        return (int) (fame*multiplier[grade]);
    }
    public void setGrade(int i) {
        this.grade=i;
    }
    public int getGradeOfEpicness() {
        return grade;
    }
    public String getEpicness() {
        return epicness[grade];
    }    
    public double getMultiplier() {
        return multiplier[grade];
    }
    @Override
    public int getAttribute(int aNr) {
        return (int) (attributes[aNr]*multiplier[grade]);
    }
    @Override
    public double getLearn() {
        return learn*multiplier[grade];
    }
}
