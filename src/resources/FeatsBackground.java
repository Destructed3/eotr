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
public class FeatsBackground extends Feats {
    public FeatsBackground(String pname, String desc, int[] pattributes, int pgold, double plearn,int[] pinterests, int nr) {
        this.name=pname;
        this.description=desc;
        this.attributes=pattributes;
        this.gold=pgold;
        this.learn=plearn;
        this.interests=pinterests;
        this.featNr=nr;
    }
    int gold;
    int[] interests;
    public void setGold(int bonus) {
        this.gold=bonus;
    }
    public int getGold() {
        return gold;
    }
    public void setInterest(int iNr, int iVal) {
        this.interests[iNr]=iVal;
    }
    public void setInterests(int[] pinterests) {
        this.interests=pinterests;
    }
    public int getInterest(int iNr) {
        return interests[iNr];
    }
    public int[] getInterests() {
        return interests;
    }
}
