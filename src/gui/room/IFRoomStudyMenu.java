/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.room;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import resources.rooms.RoomStudy;

/**
 *
 * @author Die Axt
 */
public class IFRoomStudyMenu extends JInternalFrame {
    public IFRoomStudyMenu(gui.Mainframe pdsk) {
        super();
        this.dsk=pdsk;
        run();
    }
    gui.Mainframe dsk = null;
    JPanel mainP = null;
    JPanel toolP = null;
    JList list = null;
    DefaultListModel lm = null;
    List<IFRoomStudy> ifS = null;
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
        this.setTitle("Study Rooms");
        this.setLayout(new java.awt.BorderLayout());
        this.add(mainP, BorderLayout.CENTER);
        this.add(toolP, BorderLayout.SOUTH);
    }
    private void createIF() {
        ifS = new ArrayList();
        lm = new DefaultListModel();
        dsk.getRes().getLRS().stream().forEach(rs -> {
            IFRoomStudy jif = new IFRoomStudy(rs,dsk);
            dsk.addJIF(jif);
            ifS.add(jif);
            String output = rs.getRoomNr()+" | "+rs.getRoomName()+" | "+rs.getRoomSize();
            lm.addElement(output);
        });
    }
    private void setMainP() {        
        list = new JList(lm);
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
        if(ifS.get(i).isShowing()) {
            ifS.get(i).toFront();
        } else {
            ifS.get(i).show();
        }
    }
    public void addRoom(RoomStudy newR) {
        IFRoomStudy jif = new IFRoomStudy(newR,dsk);
        dsk.addJIF(jif);
        ifS.add(jif);
        String output = newR.getRoomNr()+" | "+newR.getRoomName()+" | Size: "+newR.getRoomSize();
        lm.addElement(output);
    }
    public IFRoomStudy getRoom(int rNr) {
        return ifS.stream().filter(rs -> rs.getRoom().getRoomNr()==rNr).findFirst().get();
    }
}
