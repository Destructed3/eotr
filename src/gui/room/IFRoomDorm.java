/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.room;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.Inhabitants.InhStu;
import resources.rooms.RoomDorm;

/**
 *
 * @author Die Axt
 */
public class IFRoomDorm extends JInternalFrame {
    public IFRoomDorm(RoomDorm dorm, gui.Mainframe dsk) {
        this.dsk=dsk;
        this.dorm=dorm;
        run();
    }
    gui.Mainframe dsk;
    RoomDorm dorm;
    JPanel topP;
    JPanel mainP;
    JPanel toolP;
    JList list;
    private void run() {
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(400,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle(dorm.getRoomName());
        this.setLayout(new java.awt.BorderLayout());
        setTopP();
        setMainP();
        setToolP();
        this.add(topP, BorderLayout.NORTH);
        this.add(mainP, BorderLayout.CENTER);
        this.add(toolP, BorderLayout.SOUTH);
    }
    private void setTopP() {
        topP = new JPanel(new java.awt.FlowLayout());
        JLabel lbsize = new JLabel(dorm.getAllInhabitants().size()+"/"+dorm.getRoomSize());
        topP.add(lbsize);
    }
    private void setMainP() {
        mainP = new JPanel(new java.awt.GridLayout(1,1));
        DefaultListModel lm = new DefaultListModel();
        dorm.getAllInhabitants().stream().forEach(stuNr -> {
            InhStu stu = dsk.getRes().getLStu().stream().filter(stud -> stud.getNumber()==stuNr).findFirst().get();
            int sNr = stu.getNumber();
            String sname = stu.getName();
            int sem = stu.getSemester();
            int phy = stu.getAttribute(0);
            int men = stu.getAttribute(1);
            int soc = stu.getAttribute(2);
            int mag = stu.getAttribute(3);
            String output = sNr+"| "+sname+" -- "+sem+". Semester"+" -- Physical: "+phy+" | Mental: "+men
                    +" | Social: "+soc+" | Magical: "+mag;
            lm.addElement(output);
        });
        list = new JList(lm);   
        mainP.add(new JScrollPane(list));
    }
    public void setToolP() {
        toolP = new JPanel(new java.awt.FlowLayout());
        if(dorm.getAllInhabitants().isEmpty()) {            
        } else {            
            toolP.add(btRoom());
        }
        toolP.add(btClose());
    }
    private JButton btRoom() {
        JButton bt = new JButton("Display Inhabitant");
        bt.addActionListener((ActionEvent e) -> {
            int i = list.getSelectedIndex();
            dsk.getMenuInh().showIFStu(i);
        });
        return bt;
    }
    private JButton btClose() {
        JButton bt = new JButton("Close");
        bt.addActionListener((ActionEvent e) -> {
            this.hide();
        });
        return bt;
    }
    public RoomDorm getDorm() {
        return dorm;
    }
}
