/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import gui.Mainframe;
import java.util.*;
import resources.Inhabitants.InhStu;

/**
 *
 * @author Die Axt
 */
public class AI_Stu_choseActivity {
    public AI_Stu_choseActivity() {
        r = new Random();
    }
    public int choseTopic(List<double[]> startPref, InhStu pstu, Mainframe pdsk) {
        this.stu=pstu;
        this.dsk=pdsk;
        List<double[]> lPref = eliminateOptions(startPref);
        System.out.println("Final Array:");
        System.out.println(Arrays.toString(lPref.get(0)));
        return (int)lPref.get(0)[0];
    }
    private List<double[]> eliminateOptions(List<double[]> lPref) {
        System.out.println("Eliminate option");
        System.out.println(lPref.size()+" Options left");
        double testTopic=lPref.get(0)[1];
        int lSize = lPref.size();
        for(int i=0;i<lSize;i++) {
            if(testTopic<lPref.get(i)[1]) {
                return eliminateOptions(getCleanedPref(testTopic,lPref));
            } 
            if(lPref.get(i)[1]<testTopic) {
                return eliminateOptions(getCleanedPref(lPref.get(i)[1],lPref));
            }
        }
        if(lPref.size()==1) {
            return lPref;
        }
        return solveTies(lPref);
    }
    private List<double[]> solveTies(List<double[]> lPref) {
        System.out.println("Solve ties");
        List<long[]> lFinalized;    
        lFinalized = eliminateOptions_2ndOrder(convertLPref(lPref));
        return getFinal(lFinalized);
    }
    private List<double[]> getFinal(List<long[]> lPref) {
        List<double[]> l = new ArrayList();
        double[] d = {lPref.get(0)[0],lPref.get(0)[1]};
        l.add(d);
        return l;
    }
    private List<long[]> eliminateOptions_2ndOrder(List<long[]> lPref) {
        System.out.println("Eliminating options...");
        System.out.println(lPref.size()+" options remain");
        long p = lPref.get(0)[1];
        for(int i=0;i<lPref.size();i++) {
            if(p<lPref.get(i)[1]) {
                return eliminateOptions_2ndOrder(cleanList(lPref,p));
            }
            if(lPref.get(i)[1]<p) {
                return eliminateOptions_2ndOrder(cleanList(lPref,lPref.get(i)[1]));
            }
        }
        if(lPref.size()==1) {
            return lPref;
        } else {
            return randomPref(lPref);
        }
    }
    private List<long[]> randomPref(List<long[]> lPref) {
        System.out.println("Randoming topic...");
        System.out.println(lPref.size()+" options remain");
        int rdm = r.nextInt(lPref.size()-1);
        List<long[]> l = new ArrayList();
        l.add(lPref.get(rdm));
        return l;
    }
    private List<long[]> cleanList(List<long[]> lPref, long obsoleteVal) {
        List<long[]> l = new ArrayList();
        lPref.stream().filter(pL -> obsoleteVal<pL[1]).forEach(pL -> {
            l.add(pL);
        });
        return l;
    }
    private List<long[]> convertLPref(List<double[]> lPref) {
        List<long[]> l = new ArrayList();
        lPref.forEach(d -> {
            long[] pL = {(long)d[0],nrCourses(d[0])};
            l.add(pL);
        });
        return l;
    }
    private List<double[]> getCleanedPref(double obsoleteVal, List<double[]> lPref) {
        List<double[]> l = new ArrayList();
        lPref.stream().filter(d -> d[1]>obsoleteVal).forEach(d -> l.add(d));
        return l;
    }
    
    private long nrCourses(double topic) {
        try {
            return stu.getCourses().stream().filter(cNr -> 
                dsk.getRes().lCourse.stream().filter(pAC -> 
                        pAC.getID().equals(cNr)).findAny().get().getTopic()==topic).count();
        } catch(NoSuchElementException e) {
            return 0;
        }
    }
    
    Random r;
    InhStu stu;
    Mainframe dsk;
}
