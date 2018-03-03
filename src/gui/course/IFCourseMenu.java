/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import gui.Mainframe;
import root.IFTemplate;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import resources.activity.ActivityCourse;
import resources.Inhabitants.InhStu;

/**
 *
 * @author Die Axt
 */
public class IFCourseMenu extends IFTemplate {
    public IFCourseMenu(Mainframe pdsk) {
        super("Courses");
        this.dsk=pdsk;
        run();
    }
    public IFCourseMenu(InhStu stu, Mainframe pdsk) {
        super(stu.getName()+"'s Courses",true);
        this.dsk=pdsk;
        run(stu.getCourses());
    }
    
    Mainframe dsk = null;
    JPanel main = null;
    JPanel tool = null;
    JList list = null;
    DefaultListModel lm = null;
    List<IFCourse> lifC = null;
    
    private void run() {
        createIFC();
        setList();
        setIF(setPMain(),setToolP());
    }
    private void run(List<String> cList) {
        createIFC();
        setList(cList);
        setIF(setPMain(),setToolP());
    }
    private JPanel setPMain() {
        main = new JPanel(new java.awt.BorderLayout());
        main.add(list);
        return main;
    }
    private JPanel setToolP() {
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(btShowAC());
        tool.add(btNewAC());
        tool.add(btClose());
        return tool;
    }
    private void setList() {
        lm = new DefaultListModel();
        try {
            dsk.getRes().lCourse.stream().forEach(ac -> {
                String output = ac.getID()+"| "+ac.getName()+" | "+ac.getTopicN();
                lm.addElement(output);
            });
        } catch(Exception e) {
            lm.addElement("Create Course");
        }
        list = new JList(lm);
        list.setSelectedIndex(0);
    }
    private void setList(List<String> cList) {
        lm = new DefaultListModel();
        try {
            cList.forEach(cNr -> dsk.getRes().lCourse.stream().filter(ac -> 
                    ac.getID().equals(cNr)).forEach(ac -> {
                        String output = ac.getID()+"| "+ac.getName()+" | "+ac.getTopicN();
                        lm.addElement(output);
                    }));
        } catch(Exception e) {
            lm.addElement("Create Course");
        }
        list = new JList(lm);
        list.setSelectedIndex(0);
    }
    private void createIFC() {
        if(dsk.getRes().lCourse.size()>0) {
            lifC = new ArrayList();
            dsk.getRes().lCourse.forEach(pAC -> {
                IFCourse jif = new IFCourse(pAC,dsk);
                dsk.addJIF(jif);
                lifC.add(jif);
            });
        }
    }
    JButton btShowAC() {
        JButton bt = new JButton("Show Course");
        bt.addActionListener((ActionEvent e) -> {
            try {
                if("Create Course".equals((String) list.getSelectedValue())) {   
                    IFCourse jif = lifC.get(list.getSelectedIndex());
                    dsk.addJIF(jif);
                    jif.show();
                } else {
                    IFCourse jif = lifC.get(list.getSelectedIndex());
                    if(jif.isShowing()) {
                        jif.toFront();
                    } else {
                        jif.show();
                    }
                }
            } catch(NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "No courses available", "No courses error", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return bt;
    }
    JButton btNewAC() {
        JButton bt = new JButton("Create Course");
        bt.addActionListener((ActionEvent e) -> {
            if(!dsk.getRes().lRoomStudy.isEmpty()) {
                javax.swing.JInternalFrame jif = new IFCourseNew(dsk);
                dsk.addJIF(jif);
                jif.show();
            } else {
                JOptionPane.showMessageDialog(null, "Build a course room first", "No room available", JOptionPane.OK_OPTION);
            }
        });
        return bt;
    }
    public void addCourse() {
        if(lifC==null) {
            lifC = new ArrayList();
        }
        try {
            if(lm.getElementAt(0).equals("Create Course")) {
                lm.removeElementAt(0);
            }
        } catch(java.lang.ArrayIndexOutOfBoundsException e) {}
        ActivityCourse ac = dsk.getRes().lCourse.get(dsk.getRes().lCourse.size()-1);
        IFCourse jif = new IFCourse(ac,dsk);
        dsk.addJIF(jif);
        lifC.add(jif);
        String output = ac.getID()+"| "+ac.getName()+" | "+ac.getTopicN()+" | "+ac.getDuration()*2+"h";
        lm.addElement(output);
    }
}
