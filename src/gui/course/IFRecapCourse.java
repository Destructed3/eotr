/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import resources.Inhabitants.InhStu;

/**
 *
 * @author Die Axt
 */
public class IFRecapCourse extends root.IFTemplate {
    public IFRecapCourse(resources.activity.ActivityCourse pac, gui.Mainframe pdsk) {
        super(pac.getName(),false);
        this.dsk=pdsk;
        this.ac=pac;
        run();
    }
    
    gui.Mainframe dsk;
    JPanel main;
    JPanel tool;
    resources.activity.ActivityCourse ac;
    JList lStu;
    DefaultListModel lmStu;
    
    private void run() {
        this.setIF(setMain(),setTool());
    }
    private JPanel setMain() {
        main = new JPanel();
        setListStu();
        main.add(new javax.swing.JScrollPane(lStu));
        return main;
    }
    private JPanel setTool() {
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(this.btClose());
        return tool;
    }
    private void setListStu() {
        lmStu = new DefaultListModel();
        ac.getStudents().forEach(cAcc -> {
            InhStu stu = dsk.getRes().getLStu().stream().filter(pStu -> pStu.getNumber()==cAcc[0]).findFirst().get();
            String output = stu.getNumber()+" | "+stu.getName()+" | "+ac.getTopicN()+": "+cAcc[1]+" -> "+cAcc[2]+" ("+cAcc[3]+"% Learn-Efficiency)"+" | "+stu.getWorkTime()+"h";
            lmStu.addElement(output);
        });
        lStu = new JList(lmStu);
    }
}
