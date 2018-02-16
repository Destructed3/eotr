/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.room;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import root.TimeTable;

/**
 *
 * @author Die Axt
 */
public class IFRoomStudy extends JInternalFrame {
    public IFRoomStudy(resources.rooms.RoomStudy prs, gui.Mainframe pdsk) {
        this.rs=prs;
        this.dsk=pdsk;
        run();
    }
    gui.Mainframe dsk;
    resources.rooms.RoomStudy rs = null;
    JPanel topP;
    JPanel mainP;
    JPanel toolP;
    JTable timeTable;
    TimeTable tm;
    private void run() {
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(400,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle(rs.getRoomName());
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
        topP.add(new JLabel("Room number "+rs.getRoomNr()+" | Size: "+rs.getRoomSize()+" People"));
    }
    private void setMainP() {        
        tm = new TimeTable(rs.getRoomUsageFull(),dsk);
        timeTable = new JTable(tm);
        mainP = new JPanel();
        mainP.add(new javax.swing.JScrollPane(timeTable));
    }
    private void setToolP() {
        toolP = new JPanel(new java.awt.FlowLayout());
        toolP.add(btClose());
    }
    private JButton btClose() {
        JButton bt = new JButton("Close");
        bt.addActionListener((ActionEvent e) -> {
            this.hide();
        });
        return bt;
    }
    public void changeTT() {
        tm.refresh(rs.getRoomUsageFull());
    }
    public resources.rooms.RoomStudy getRoom() {
        return rs;
    }
}
