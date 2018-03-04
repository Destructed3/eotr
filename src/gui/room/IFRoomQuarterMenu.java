/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.room;

import gui.Mainframe;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import resources.Inhabitants.InhTea;
import resources.rooms.RoomQuarter;

/**
 *
 * @author Die Axt
 */
public class IFRoomQuarterMenu extends JInternalFrame {
    public IFRoomQuarterMenu(Mainframe pdsk) {
        super();
        this.dsk=pdsk;
        run();
    }
    Mainframe dsk = null;
    JPanel mainP = null;
    JPanel toolP = null;
    JList list = null;
    DefaultListModel lm = null;
    List<JInternalFrame> ifQ = new ArrayList();

    private void run() {        
        createIF();
        setMainP();
        setToolP();
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(400,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle("Quarter Rooms");
        this.setLayout(new java.awt.BorderLayout());
        this.add(mainP, java.awt.BorderLayout.CENTER);
        this.add(toolP, java.awt.BorderLayout.SOUTH);
    }
    private void createIF() {
        lm = new DefaultListModel();
        dsk.getData().lRoomQuarter.stream().forEach(rq -> {
            JInternalFrame jif = new IFRoomQuarter(rq,dsk);
            dsk.addJIF(jif);
            ifQ.add(jif);
            String name;
            if(rq.getResident()==0) {
                name="Empty";
            } else {
                int nr=rq.getResident();
                InhTea tea=dsk.getData().lTea.stream().filter(teac -> teac.getNumber()==nr).findFirst().get();
                name=tea.getName();
            }
            String output = rq.getRoomName()+" | Resident: "+name;
            lm.addElement(output);
        });
    }
    private void setMainP() {
        list =new JList(lm);
        mainP = new JPanel(new java.awt.GridLayout(1,1));
        mainP.add(new javax.swing.JScrollPane(list));
    }
    private void setToolP() {
        toolP = new JPanel(new java.awt.FlowLayout());
        toolP.add(btRoom());
        toolP.add(btClose());
    }
    private JButton btRoom() {
        JButton bt = new JButton("Display Room");
        bt.addActionListener((ActionEvent e) -> {
            showRoom(list.getSelectedIndex());
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
    public void showRoom(int i) {
        if(ifQ.get(i).isShowing()) {
            ifQ.get(i).toFront();
        } else {
            ifQ.get(i).show();
        }
    }
    public void addRoom(RoomQuarter rq) {
        String res;
            if(rq.getResident()==0) {
                res="Empty";
            } else {
                int i=rq.getResident();
                InhTea teac = dsk.getData().lTea.stream().filter(tea -> tea.getNumber()==i).findFirst().get();
                res = teac.getName();
            }
            String output = rq.getRoomName()+" | Resident: "+res;
            lm.addElement(output);
    }
}
