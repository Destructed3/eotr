/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.room;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import resources.Inhabitants.InhTea;
import resources.rooms.RoomQuarter;

/**
 *
 * @author Die Axt
 */
public class IFRoomQuarter extends JInternalFrame {
    public IFRoomQuarter(RoomQuarter prq, Mainframe pdsk) {
        this.dsk=pdsk;
        this.rq=prq;
        run();
    }
    Mainframe dsk;
    RoomQuarter rq = null;
    JPanel mainP;
    JPanel toolP;
    JLabel jlI = null;
    List listF = new ArrayList();
    JList jListF = null;
    private void run() {
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(400,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle(rq.getRoomName());
        this.setLayout(new java.awt.BorderLayout());
        setMainP();
        setToolP();
        this.add(mainP, BorderLayout.CENTER);
        this.add(toolP, BorderLayout.SOUTH);
    }
    private void setMainP() {
        jListF = new JList();
        if(listF!=null) {
            for(int i=0;i<listF.size();i++) {
                JLabel str = new JLabel((String)listF.get(i));
                jListF.add(str);
            }
        }
        if(rq.getResident()==0) {
            jlI = new JLabel("Room is Empty");
        } else {
            int nr = rq.getResident();
            InhTea teac = dsk.getRes().getLTea().stream().filter(tea -> tea.getNumber()==nr).findFirst().get();
            jlI = new JLabel(teac.getName());
        }
        
        mainP = new JPanel(new java.awt.GridLayout(5,2));
        mainP.add(jlI);
        mainP.add(jListF);
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
    public void obtainRoom(resources.Inhabitants.InhTea tea) {
        if(tea!=null) {
            jlI.setText(tea.getName());            
        } else {
            jlI.setText("Room is Empty");
        }
    }
    public void addListItem(String s) {
        listF.add(s);
    }
}
