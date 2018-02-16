/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import resources.activity.ActivityCourse;

/**
 *
 * @author Die Axt
 */
public class IFRecap extends root.IFTemplate {
    public IFRecap(Mainframe pdsk) {
        super("Year "+(pdsk.getRes().getVariables().getYear()-1)+" recap",false);
        this.dsk = pdsk;
        run();
    }
    
    Mainframe dsk;
    JPanel main;
    JPanel tool;
    JList lcour;
    JTable tbTea;
    DefaultListModel lm;
    DefaultTableModel tm;   
    List<ActivityCourse> l_PastCour;
    List<IFRecapCourse> lIFCour;
    
    private void run() {
        setLIFCour();
        setIF(setMain(),setTool());
    }
    private List<ActivityCourse> setlPCour() {
        l_PastCour = new ArrayList();
        dsk.getRes().getLAC().stream().filter(pAC -> 
                !pAC.isActive()).forEach((ActivityCourse pAC) -> {
            l_PastCour.add(pAC);
        });
        return l_PastCour;
    }
    private DefaultListModel setLIFCour() {
        if(lm==null) {
            lm = new DefaultListModel();
            lIFCour = new ArrayList();
            if(!setlPCour().isEmpty()) {
                l_PastCour.stream().forEach(ac -> {
                    lIFCour.add(addCourse(ac));
                    add_toLM(ac);
                });
            } else {
                String output = "Nothing here";
                lm.addElement(output);
            }
        }
        return lm;
    }
    private IFRecapCourse addCourse(ActivityCourse ac) {
        IFRecapCourse ifrc = new IFRecapCourse(ac,dsk);
        dsk.addJIF(ifrc);
        return ifrc;
    }
    private void add_toLM(ActivityCourse ac) {
        String avgL = getAvgL(ac)*100+"";
                String output ="<html>"+ac.getID()+" | "+ac.getName()
                        +"Year: "+ac.getYear()+" | "+ac.getTopicN()
                        +" | "+ac.getHostNr()+" | "+ac.getStudents().size()
                        +"/"+ac.getCourseSize()+" Students"
                        +"<br>Avg. Learn: "+avgL.substring(0,2)+"%</html>";
                lm.addElement(output);
    }
    
    private JPanel setMain() {
        main = new JPanel(new GridLayout(3,1));
        main.add(setPCourse());
        return main;
    }
    private JPanel setTool() {
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(btClose());
        return tool;
    }
    private double getAvgL(ActivityCourse ac) {
        double nr = 0;
        for (int[] stuNr : ac.getStudents()) {
            resources.Inhabitants.InhStu stu = dsk.getRes().getLStu().stream().filter(pstu -> pstu.getNumber()==stuNr[0]).findAny().get();
            nr=nr+stu.getLearn();
        }
        nr = nr/ac.getStudents().size();
        return nr;
    }
    private JPanel setPCourse() {
        JPanel jp = new JPanel(new java.awt.BorderLayout());
        jp.add(new JScrollPane(setList()), BorderLayout.CENTER);
        jp.add(btShowAC(), BorderLayout.SOUTH);
        return jp;
    }
    private JList setList() {
        if(lcour==null) {
            lcour=new JList(setLIFCour());
        }
        return lcour;
    }
    private JButton btShowAC() {
        JButton bt;
        bt = new JButton("Show");
        bt.addActionListener((ActionEvent e) -> {
            int i = lcour.getSelectedIndex();
            IFRecapCourse ifrc = null;
            try {
                ifrc = lIFCour.get(i);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "No Courses", "No courses", JOptionPane.OK_OPTION);
            }
            if(ifrc!=null && ifrc.isShowing()) {
                ifrc.toFront();
            } else if(ifrc!=null) {
                ifrc.show();
            }

        });
        return bt;
    }
}
