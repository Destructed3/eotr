/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import gui.Mainframe;
import gui.inh.IFStudent;
import gui.inh.IFTeacher;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import resources.activity.ActivityCourse;
import resources.Inhabitants.InhStu;
import root.IFTemplate;
import root.TimeTable;

/**
 *
 * @author Die Axt
 */
public class IFCourse extends IFTemplate {
    public IFCourse(ActivityCourse pco, Mainframe pdsk) {
        super(pco.getName(),false);
        this.ac=pco;
        this.dsk=pdsk;
        run();
    }
    Mainframe dsk = null;
    JPanel top = null;
    JPanel main = null;
    JPanel tool = null;
    ActivityCourse ac = null;
    String[][] inUse = new String[10][7];
    JTable table = null;
    TimeTable timeTable = null;
    JList list = null;
    DefaultListModel lm = null;

    private void run() {
        setTable();
        setList();
        setIF(setPTop(),setPMain(),setPTool());
    }
    private JPanel setPTop() {
        resources.Inhabitants.InhTea tea = dsk.getData().lTea.stream().filter(ptea -> 
                ptea.getNumber()==ac.getHostNr()).findFirst().get();
        int topval;
        switch (ac.getTopicN()) {
            case "Physical": 
                topval=tea.getAttribute(0);
                break;
            case "Mental":
                topval=tea.getAttribute(1);
                break;
            case "Social":
                topval=tea.getAttribute(2);
                break;
            default:
                topval=tea.getAttribute(3);
                break;
        }
        top = new JPanel(new java.awt.GridLayout(2,1));
        top.add(new JLabel(ac.getName()+" | Topic: "+ac.getTopicN()+" | Duration: "+ac.getDuration()));
        JPanel p2 = new JPanel(new java.awt.FlowLayout());
        p2.add(new javax.swing.JLabel("Teacher: "+tea.getName()+" | "+ac.getTopicN()+": "+topval+" | Teaching: "+tea.getTeaching()));
        p2.add(btTea(tea));
        top.add(p2);
        return top;
    }
    private JPanel setPMain() {
        main = new JPanel(new java.awt.GridLayout(2,1));
        main.add(new javax.swing.JScrollPane(table));
        return main;
    }
    private JPanel setPTool() {
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(btStu());
        tool.add(btClose());
        return tool;
    }
    private void setTable() {
        timeTable = new TimeTable(ac.getTimeTable(),dsk);
        table = new JTable(timeTable);
    }
    private void setList() {
        lm = new DefaultListModel();
        List<resources.Inhabitants.InhStu> lstu = dsk.getData().lStu;
        ac.getStudents().stream().forEach(cos -> {
            int sNr = cos[0];
            resources.Inhabitants.InhStu stu = lstu.stream().filter(stud -> stud.getNumber()==sNr).findFirst().get();
            String sname = stu.getName();
            int sem = stu.getSemester();
            int phy = stu.getAttribute(0);
            int men = stu.getAttribute(1);
            int soc = stu.getAttribute(2);
            int mag = stu.getAttribute(3);            
            String output = sNr+"| "+sname+" -- "+sem+". Semester"+" -- Physical: "+phy+" | Mental: "+men+" | Social: "+soc+" | Magical: "+mag;
            lm.addElement(output);
        });
        list = new JList(lm);
    }
    private JButton btTea(resources.Inhabitants.InhTea tea) {
        JButton bt = new JButton("Show");
        bt.addActionListener((ActionEvent e) -> {
            javax.swing.JInternalFrame jif;
            jif = new IFTeacher(tea, dsk);
            jif.show();
        });
        return bt;
    }
    private JButton btStu() {
        JButton bt = new JButton("Display Participant");
        bt.addActionListener((ActionEvent e) -> {
            int index = list.getSelectedIndex();
            InhStu stu = dsk.getData().lStu.get(index);
            JInternalFrame jif = new IFStudent(stu,dsk);
            dsk.addJIF(jif);
            jif.show();
        });
        return bt;
    }
}
