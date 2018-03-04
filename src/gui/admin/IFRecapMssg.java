/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin;

import gui.Mainframe;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.Accounting;
import resources.Inhabitants.Inhabitants;

/**
 *
 * @author Die Axt
 */
public class IFRecapMssg extends root.IFTemplate {
    
    public IFRecapMssg(Mainframe pdsk) {
        super("Year "+(pdsk.getData().year - 1)+" recap");
        this.dsk=pdsk;
        this.year=(pdsk.getData().year - 1);
        this.accounting=null;
        if(pdsk.getSave()==null) {
            runNG();
        } else {
            run();
        }
    }
    
    
    
    private void runNG() {
        if(pMain==null) {
            pMain = new JPanel();
            pMain.add(new JLabel("Have fun!!"));
        }
        if(pTool==null) {
            pTool = new JPanel();
            pTool.add(btClose());
        }
        this.setIF(pMain,pTool);
        this.setSize(200, 100);
    }
    
    private void run() {
        //this.accounting = null;
        this.setIF(setPM(),setPT());
    }
    
    private JPanel setPM() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.GridLayout(2, 2));
            pMain.add(mainPM());
            pMain.add(new JScrollPane(mssgList()));
        }
        return pMain;
    }
    private JPanel setPT() {
        if(pTool==null) {
            pTool = new JPanel(new java.awt.FlowLayout());
            pTool.add(this.btClose());
        }
        return pTool;
    }
    
    private JPanel mainPM() {
        if(mainPM==null) {
            
            mainPM = new JPanel(new java.awt.GridLayout(5,2));
            mainPM.add(lIncome());
            mainPM.add(lUpkeep());
            mainPM.add(lNGold());
            mainPM.add(new JPanel());
            mainPM.add(lLeaver());
            mainPM.add(lFinnish());
            mainPM.add(new JPanel());
            mainPM.add(new JPanel());
            mainPM.add(lNrStu());
        }
        return mainPM;
    }
    private JLabel lIncome() {
        if(lIncome==null) {
            lIncome = new JLabel("You made "+getIncome()+"g through tuitions");
        }
        return lIncome;
    }
    private JLabel lUpkeep() {
        if(lUpkeep==null) {
            lUpkeep = new JLabel("You payed "+getUpkeep()+"g");
        }
        return lUpkeep;
    }
    private JLabel lNGold() {
        if(lNGold==null) {
            lNGold = new JLabel("New Treasure: "+dsk.getData().gold);
        }
        return lNGold;
    }
    private JLabel lLeaver() {
        if(lLeaver==null) {
            lLeaver = new JLabel("Leaving Students: "+getLeaver());
        }
        return lLeaver;
    }
    private JLabel lFinnish() {
        if(lFinnish==null) {
            lFinnish = new JLabel("Students Finished: "+getFinnisher());
        }
        return lFinnish;
    }
    private JLabel lNrStu() {
        if(lNrStu==null) {
            lNrStu = new JLabel("Students now: "+getNrStu());
        }
        return lNrStu;
    }
    
    private String getIncome() {
        return String.valueOf(0);
    }
    private String getUpkeep() {
        return String.valueOf(0);
    }
    private String getLeaver() {
        long l = dsk.getData().lStu.stream().filter(pStu 
                -> (pStu.getLeaveTime()==year) 
                && (pStu.getLeaveReason()!=Inhabitants.MET_STUDY_GOALS)).count();
        return String.valueOf(l);
    }
    private String getFinnisher() {
        long l=dsk.getData().lStu.stream().filter(pStu -> 
                pStu.getLeaveTime()==year &&
                pStu.getLeaveReason()==Inhabitants.MET_STUDY_GOALS).count();
        return String.valueOf(l);
    }
    private String getNrStu() {
        long l = dsk.getData().lStu.stream().filter(pStu -> !pStu.isFormer()).count();
        return String.valueOf(l);
    }
    
    private JList mssgList() {
        if(mssgList==null) {
            mssgList = new JList(lm());
        }
        return mssgList;
    }
    private DefaultListModel lm() {
        if(lm==null) {
            lm = new DefaultListModel();
        }
        return lm;
    }
    
    Mainframe dsk;
    
    int year;
    
    JPanel pMain = null;
    JPanel pTool = null;
    
    JPanel mainPM = null;
    JLabel lIncome = null;
    JLabel lUpkeep = null;
    JLabel lNGold = null;
    JLabel lLeaver = null;
    JLabel lFinnish = null;
    JLabel lNewStud = null;
    JLabel lRejected = null;
    JLabel lNrStu = null;
    JList mssgList = null;
    DefaultListModel lm = null;
    Accounting accounting = null;
    
}
