/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import gui.Mainframe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import resources.Inhabitants.InhStu;
import resources.activity.ActivityJob;

/**
 *
 * @author Die Axt
 */
public class AI_Stu_choseJob {
    
    public AI_Stu_choseJob() {
        r = new Random();
    }

    public ActivityJob findJob(InhStu pstu, Mainframe pdsk) {
        System.out.println("Trying to create job...");
        
        this.stu=pstu;
        this.dsk=pdsk;
        
        return find_best_job(setupList());
        
    }
    
    //Create List of available jobs
    private List<int[]> setupList() {
        System.out.println("Setting up list of possible jobs");
        List<int[]>l=new ArrayList();
        for(int i=0;i<4;i++) {
            if(jobAv(i)) {
                int[] ar = {i,stu.getAttribute(i)};
                l.add(ar);
            }
        }
        return l;
    }
    //Check wether or not a job is available
    private boolean jobAv(int topic) {
        try {
            return dsk.getRes().lJob.stream().anyMatch(pAJ -> pAJ.getTopic()==topic);
        }catch(NullPointerException ex) {
            System.out.println(dsk.getRes().lJob);
            System.out.println("-----");
            return false;
        }
    }
    
    //
    private ActivityJob find_best_job(List<int[]> l) {
        List<int[]> lPref = l;
        while(!lPref.isEmpty()) {
            int attr=sortOutAttribute(l);
            if(isJobAv(attr)) {
                return dsk.getRes().lJob.stream().filter(pAJ -> pAJ.isActive() && pAJ.getHostNr()==0 && pAJ.getTopic()==attr).findAny().get();
            } else {
                lPref=eliminateAttribute(attr,lPref);
            }
        }
        return null;
    }
    private boolean isJobAv(int attr) {
        return dsk.getRes().lJob.stream().anyMatch(pAJ -> pAJ.isActive() && pAJ.getTopic()==attr);
    }
    
    private int sortOutAttribute(List<int[]> attr) {
        int p=attr.get(0)[1];
        for(int i=0;i<attr.size();i++) {
            if(p<attr.get(i)[1]) {
                System.out.println("Eliminating attribute "+attr.get(0)[0]);
                return sortOutAttribute(eliminateValue(p,attr));
            }
            if(attr.get(i)[1]<p) {
                System.out.println("Eliminating attribute "+attr.get(i)[0]);
                return sortOutAttribute(eliminateValue(attr.get(i)[1],attr));
            }
        }
        if(1<attr.size()) {
            return solveTie(attr);
        }
        return attr.get(0)[0];
    }
    private int solveTie(List<int[]> attr) {
        int rdm = r.nextInt(attr.size()-1);
        return attr.get(rdm)[0];
    }
    private List<int[]> eliminateValue(int obsoleteVal,List<int[]> attr) {
        List<int[]> l = new ArrayList();
        attr.stream().filter(a -> obsoleteVal<a[1]).forEach(a -> l.add(a));
        return l;
    }
    private List<int[]> eliminateAttribute(int obsoleteAttr,List<int[]> attr) {
        List<int[]> l = new ArrayList();
        attr.stream().filter(i -> i[0]!=obsoleteAttr).forEach(i -> l.add(i));
        return l;
    }
    
    Random r;
    
    InhStu stu;
    Mainframe dsk;
    
    
}
