/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import gui.Mainframe;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.Inhabitants.Inhabitants;

/**
 *
 * @author Die Axt
 */
public class ShowLists extends root.IFTemplate {
    public ShowLists(Mainframe pdsk) {
        super("",false);
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    JPanel top = null;
    JPanel main = null;
    JPanel tool = null;
    JList l1;
    JList l2;
    DefaultListModel lm1;
    DefaultListModel lm2;
    JComboBox cb1;
    JComboBox cb2;
    
    private void run() {
        setLists();
        setCBs();
        setShowLists();
        setIF(top,main,tool);
    }
    private void setLists() {
        lm1 = new DefaultListModel();
        lm2 = new DefaultListModel();
        activateTea(lm1);
        activateStu(lm2);
        l1 = new JList(lm1);
        l2 = new JList(lm2);
    }
    private void setCBs() {
        String[] s = {"Teacher","Students","Study Rooms","Dorm Rooms","Quarter Rooms","Courses"};
        cb1 = new JComboBox(s);
        cb1.setSelectedItem("Teacher");
        cb1.addItemListener(il(1));
        cb2 = new JComboBox(s);
        cb2.setSelectedItem("Students");
        cb2.addItemListener(il(2));
    }
    private void setShowLists() {
        top = new JPanel(new java.awt.GridLayout(1, 2));
        JPanel top1 = new JPanel(new java.awt.FlowLayout());
        JPanel top2 = new JPanel(new java.awt.FlowLayout());
        top1.add(cb1);
        top2.add(cb2);
        top.add(top1);
        top.add(top2);
        main = new JPanel(new java.awt.GridLayout(1, 2));
        JScrollPane sc1 = new JScrollPane(l1);
        JScrollPane sc2 = new JScrollPane(l2);
        main.add(sc1);        
        main.add(sc2);
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(btClose());
    }
    private ItemListener il(int i) {
        ItemListener il = (ItemEvent e) -> {
            JComboBox pCB;
            DefaultListModel plm;
            if(i==1) {
                pCB = cb1;
                plm = lm1;
            } else {
                pCB = cb2;
                plm = lm2;
            }
            switch ((String)pCB.getSelectedItem()) {
                case "Teacher": 
                    activateTea(plm);
                    break;
                case "Students":
                    activateStu(plm);
                    break;
                case "Study Rooms":
                    activateRStudy(plm);
                    break;
                case "Dorm Rooms":
                    activateRDorm(plm);
                    break;
                case "Quarter Rooms":
                    activateRQuarter(plm);
                    break;
                default:
                    activateACourse(plm);
                    break;
            }
        };
        return il;
    }
    private void activateTea(DefaultListModel lm) {
        lm.clear();
        dsk.getRes().getLTea().forEach(pTea -> {
            String output = pTea.getNumber()+" | "+pTea.getName();
            lm.addElement(output);
        });
    }
    private void activateStu(DefaultListModel lm) {
        lm.clear();
        dsk.getRes().getLStu().forEach(pStu -> {
            String output = pStu.getNumber()+" | "+pStu.getName()+" | "+isFormer(pStu);
            lm.addElement(output);
        });
    }
    private String isFormer(resources.Inhabitants.InhStu pStu) {
        if(pStu.isFormer()) {
            return "Left because "+getReason(pStu.getLeaveReason());
        } else {
            return "Still there";
        }
    }
    private String getReason(int reason) {
        switch (reason) {
            case Inhabitants.UNHAPPY:
                return "unhappyness";
            case Inhabitants.MET_PERSONAL_GOALS:
                return "personal goals were achieved";
            case Inhabitants.MET_STUDY_GOALS:
                return "studys were finished";
            case Inhabitants.STORY:
                return "there was something more important than studying";
            default:
                return "the study fees were too expensive";
        }
    }
    private void activateRStudy(DefaultListModel lm) {
        lm.clear();
        dsk.getRes().getLRS().forEach(pRS -> {
            String output = pRS.getRoomNr()+" | "+pRS.getRoomName();
            lm.addElement(output);
        });
    }
    private void activateRDorm(DefaultListModel lm) {
        lm.clear();
        dsk.getRes().getLRD().forEach(pRD -> {
            String output = pRD.getRoomNr()+" | "+pRD.getRoomName();
            lm.addElement(output);
        });
    }
    private void activateRQuarter(DefaultListModel lm) {
        lm.clear();
        dsk.getRes().getLRQ().forEach(pRQ -> {
            String output = pRQ.getRoomNr()+" | "+pRQ.getRoomName();
            lm.addElement(output);
        });
    }
    private void activateACourse(DefaultListModel lm) {
        lm.clear();
        dsk.getRes().getLAC().forEach(pAC -> {
            String output = pAC.getID()+" | "+pAC.getName()+" | "
                    +pAC.getTopicN()+" | Still active: "+pAC.isActive();
            lm.addElement(output);
        });
    }
    
}
