/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import generators.name.NameGenerator;
import gui.Mainframe;
import java.util.List;
import java.util.Random;
import resources.FeatsDeeds;
import resources.Inhabitants.InhTea;
import root.LoadDat;

/**
 *
 * @author Die Axt
 */
public class TeaGen extends resources.Inhabitants.InhTea {
    
    public TeaGen(boolean iP, boolean fh, Mainframe pdsk) {
        super(iP,fh);
        if(fh) {
            generate_FH();
        } else {
            generate(pdsk);
        }
    }

    private void generate(Mainframe dsk) { 
        Random r = new Random();
        NameGenerator ng = new NameGenerator();
        int plearn = r.nextInt(20);
        double learn=plearn;   
        learn=learn/100;
        int teaNr=10000;                
        while(getNrTea(dsk.getRes().lTea,teaNr))         {
            teaNr++;
        } 
        this.setName(ng.randomName(false));
        this.setNumber(teaNr);
        this.setAttribute(0, 10+r.nextInt(15));
        this.setAttribute(1, 10+r.nextInt(15));
        this.setAttribute(2, 10+r.nextInt(15));
        this.setAttribute(3, 10+r.nextInt(15));
        this.setTeaching(learn);
        this.setFame(10+r.nextInt(15));
        this.addDeeds(r);
        if(this.isFH()) {
            this.calcCost();
        }
    }
    private void generate_FH() { 
        Random r = new Random();
        NameGenerator ng = new NameGenerator();
        int plearn = r.nextInt(20);
        double learn=plearn;   
        learn=learn/100;
        this.setName(ng.randomName(false));
        this.setAttribute(0, 10+r.nextInt(15));
        this.setAttribute(1, 10+r.nextInt(15));
        this.setAttribute(2, 10+r.nextInt(15));
        this.setAttribute(3, 10+r.nextInt(15));
        this.setTeaching(learn);
        this.setFame(10+r.nextInt(15));
        this.addDeeds(r);
        if(this.isFH()) {
            this.calcCost();
        }
    }
    
    private boolean getNrTea(List<InhTea> lTea, int nr) {
        return lTea.stream().anyMatch(tea -> tea.getNumber()==nr);
    }
    private void addValues(FeatsDeeds d) {
        this.setAttribute(0, this.getAttribute(0)+d.getAttribute(0));
        this.setAttribute(1, this.getAttribute(1)+d.getAttribute(1));
        this.setAttribute(2, this.getAttribute(2)+d.getAttribute(2));
        this.setAttribute(3, this.getAttribute(3)+d.getAttribute(3));
        this.setFame(this.getFame()+d.getFame());
        this.setTeaching(this.getTeaching()+d.getLearn());
    }
    private void addDeeds(Random r) {
        List<FeatsDeeds> lfd = (List<FeatsDeeds>) new LoadDat().getObject(".\\Data\\deeds.fe");
        FeatsDeeds d;
        int epicness;
        int nrD=0;
        try {
            int rdn = r.nextInt(lfd.size()-1); 
            while(rdn<lfd.size() && this.getTeaching()<0.8) {
                epicness = r.nextInt(2);
                d = lfd.get(rdn);
                d.setGrade(epicness);
                int[] il={d.getNr(),epicness};
                addValues(d);
                this.addDeed(il);
                rdn = r.nextInt(lfd.size()-1)+nrD;
                nrD++;
            }
            if(this.getTeaching()>1) {
                this.setTeaching(1);
            }
        } catch(java.lang.IllegalArgumentException e) {
                epicness = new Random().nextInt(3);
                d = lfd.get(0);
                d.setGrade(epicness);
                int[] il={d.getNr(),epicness};
                addValues(d);
                this.addDeed(il);
        }
        
    }
}
