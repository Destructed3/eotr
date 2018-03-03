/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Die Axt
 */
public class IFStuMenu extends JInternalFrame {
    public IFStuMenu(Mainframe pdsk) {
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk = null;
    JPanel mainP = null;
    JPanel toolP = null;
    JList list = null;
    String output = null;
    List<JInternalFrame> ifStu = new ArrayList();
    
    private void run() { 
        createIF();
        setThis();
        setMainP();
        setToolP();
        this.add(mainP, BorderLayout.CENTER);
        this.add(toolP, BorderLayout.SOUTH);
    }
    private void setThis() {
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(400,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        this.setClosable(true);
        this.setLayout(new java.awt.BorderLayout());
        this.setTitle("Students");
    }
    private void setMainP() {
        mainP = new JPanel(new java.awt.GridLayout(1,1));
        DefaultListModel lm = new DefaultListModel();
        dsk.getRes().lStu.stream().filter(stu -> !stu.isFormer()).forEach((stu) -> {
            int stuNr = stu.getNumber();
            String sname = stu.getName();
            int sem = stu.getSemester();
            int phy = stu.getAttribute(0);
            int men = stu.getAttribute(1);
            int soc = stu.getAttribute(2);
            int mag = stu.getAttribute(3);
            output = stuNr+"-"+sname+" - "+sem+". Year"+" -- Physical: "+phy+" | Mental: "+men+" | Social: "+soc+" | Magical: "+mag;
            lm.addElement(output);
        });
        list = new JList(lm);
        mainP.add(new JScrollPane(list));
    }
    private void setToolP() {
        toolP = new JPanel(new java.awt.FlowLayout());
        toolP.add(btStu());
        toolP.add(btClose());        
    }
    private void createIF() {
        dsk.getRes().lStu.stream().filter(stu -> !stu.isFormer()).forEach(stu -> {
            JInternalFrame jif = new IFStudent(stu,dsk);
            dsk.addJIF(jif);
            ifStu.add(jif);
        });
    }
    private JButton btStu() {
        JButton bt = new JButton("Display Student");
        bt.addActionListener((ActionEvent e) -> {
            int index=list.getSelectedIndex();
            JInternalFrame jif = ifStu.get(index);
            if(jif.isShowing()) {
                jif.toFront();
            } else {
                jif.show();
            }
        });
        return bt;
    }
    private JButton btClose() {
        JButton btClose = new JButton("Close");
        btClose.addActionListener((ActionEvent e) -> {
            this.hide();
        });
        return btClose;
    }
    public void showIFStu(int index) {
        JInternalFrame jif = ifStu.get(index);
            if(jif.isShowing()) {
                jif.toFront();
            } else {
                jif.show();
            }
    }
}
