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
import javax.swing.JScrollPane;
import resources.rooms.RoomDorm;

/**
 *
 * @author Die Axt
 */
public class IFRoomDormMenu extends JInternalFrame {
    public IFRoomDormMenu(gui.Mainframe dsk) {
        this.dsk=dsk;
        run();
    }
    
    private void run() {
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(400,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle("Dorms");
        this.setLayout(new java.awt.BorderLayout());
        createIF();
        setMainP();
        setToolP();
        this.add(mainP, BorderLayout.CENTER);
        this.add(toolP, BorderLayout.SOUTH);
    }
    private void createIF() {
        lm = new DefaultListModel();
        dsk.getData().lRoomDorm.stream().forEach(rd -> {
            IFRoomDorm jif = new IFRoomDorm(rd,dsk);
            ifD.add(jif);
            dsk.addJIF(jif);
            String output = rd.getRoomName()+" | "+rd.getAllInhabitants().size()+"/"+rd.getRoomSize()+" People";
            lm.addElement(output);  
        });
    }
    private void setMainP() {
        list = new JList(lm);
        mainP = new JPanel(new java.awt.GridLayout(1,1));
        mainP.add(new JScrollPane(list));
    }
    private void setToolP() {
        toolP = new JPanel(new java.awt.FlowLayout());
        if(dsk.getData().lRoomDorm.size()>0) {
            toolP.add(getBT_Room());
        }
        toolP.add(btClose());
    }
    private JButton getBT_Room() {
        if(btShow==null) {
            btShow = new JButton("Display Room");
            btShow.addActionListener((ActionEvent e) -> {
                showRoom(list.getSelectedIndex());
            });
        }
        return btShow;
    }
    private JButton btClose() {
        if(btClose==null) {
            btClose = new JButton("Close");
            btClose.addActionListener((ActionEvent e) -> {
                this.hide();
            });
        }
        return btClose;
    }
    private void showRoom(int i) {
        if(ifD.get(i).isShowing()) {
            ifD.get(i).toFront();
        } else {
            ifD.get(i).show();
        }
    }
    public void showRoom(RoomDorm rd) {
        IFRoomDorm pifd=ifD.stream().filter(ifDorm -> ifDorm.getDorm()==rd).findFirst().get();
        showRoom(ifD.indexOf(pifd));
    }
    public void addRoom(RoomDorm rd) {
        if(btShow==null) {
            toolP.remove(btClose);
            toolP.add(getBT_Room());
            toolP.add(btClose());
        }
        IFRoomDorm jif = new IFRoomDorm(rd,dsk);
        dsk.addJIF(jif);
        ifD.add(jif);
        String output = rd.getRoomName()+" | "+rd.getAllInhabitants().size()+"/"+rd.getRoomSize()+" People";
        lm.addElement(output);
    }
    
    gui.Mainframe dsk;
    
    JPanel mainP = null;
    JPanel toolP = null;
    
    JList list = null;
    DefaultListModel lm = null;
    
    List<IFRoomDorm> ifD = new ArrayList();
    
    JButton btClose;
    JButton btShow;
}
