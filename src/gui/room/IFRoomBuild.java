/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.room;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import resources.Accounting;
import resources.rooms.RoomDorm;
import resources.rooms.RoomQuarter;
import resources.rooms.RoomStudy;

/**
 *
 * @author Die Axt
 */
public class IFRoomBuild extends JInternalFrame {
    public IFRoomBuild(Mainframe pdsk) {
        this.dsk=pdsk;
        run();
    }
    Mainframe dsk = null;
    
    JPanel mainP = null;
    JPanel toolP = null;
    
    JPanel pRS_small = null;
    JPanel pRS_auditory = null;
    JPanel pRQ = null;
    JPanel pInfo = null;
    JPanel pRD_small = null;
    JPanel pRD_big = null;
    
    JLabel lRS_small = null;
    JLabel lRS_big = null;
    JLabel lRQ = null;
    JLabel lRD_small = null;
    JLabel lRD_big = null;
    JLabel lMaint = null;
    
    int maintenance;
    
    private void run() {
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(500,400);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setResizable(true);
        this.setTitle("Build Rooms");
        this.setLayout(new java.awt.BorderLayout());
        this.add(setMainP(), BorderLayout.CENTER);
        this.add(setToolP(), BorderLayout.SOUTH);
    }
    
    private JPanel setMainP() {
        if(mainP==null) {
            mainP = new JPanel(new java.awt.GridLayout(3,2));
            mainP.add(setP_RS_small());
            mainP.add(setP_RS_auditory());
            mainP.add(setP_RQ());
            mainP.add(setP_Info());
            mainP.add(setP_RD_small());
            mainP.add(setP_RD_big());
        }
        return mainP;
    }
    private JPanel setToolP() {
        if(toolP==null) {
            toolP = new JPanel(new java.awt.FlowLayout());
            toolP.add(btClose()); 
        }
        return toolP;
    }
    
    private JPanel setP_RS_small() {
        if(pRS_small==null) {
            pRS_small = new JPanel(new java.awt.GridLayout(4,1));
            pRS_small.setBorder(new LineBorder(Color.BLACK));
            pRS_small.add(new JLabel("Small Course Room"));
            pRS_small.add(new JLabel("Size: 30 Pupil"));
            int maint = new resources.rooms.RoomStudy("", 30, 1).getMaintenance();
            pRS_small.add(new JLabel("Maintenance: "+String.valueOf(maint)+"g/Year"));
            pRS_small.add(btBRS(30,300));
        }
        return pRS_small;
    }
    private JPanel setP_RS_auditory() {
        if(pRS_auditory==null) {
            Border bo = new LineBorder(Color.BLACK);
            pRS_auditory = new JPanel(new java.awt.GridLayout(4,1));
            pRS_auditory.add(new JLabel("Auditory"));
            pRS_auditory.add(new JLabel("Size: 100 Pupil"));
            int maint = new resources.rooms.RoomStudy("", 100, 1).getMaintenance();
            pRS_auditory.add(new JLabel("Maintenance: "+String.valueOf(maint)+"g/Year"));
            pRS_auditory.add(btBRS(100,1000));
            pRS_auditory.setBorder(bo);
        }
        return pRS_auditory;
    }
    private JPanel setP_RQ() {
        if(pRQ==null) {
            Border bo = new LineBorder(Color.BLACK);
            pRQ = new JPanel(new java.awt.GridLayout(4,1));
            pRQ.add(new JLabel("Hero's Quarter"));
            pRQ.add(new JLabel("A room for a once heroic Teacher"));
            int maint = new resources.rooms.RoomQuarter("", 1).getMaintenance();
            pRQ.add(new JLabel("Maintenance: "+String.valueOf(maint)+"g/Year"));
            pRQ.add(btBRQ());
            pRQ.setBorder(bo);
        }
        return pRQ;
    }
    private JPanel setP_Info() {
        if(pInfo==null) {
            pInfo = new JPanel(new java.awt.GridLayout(6,2));
            pInfo.add(new JLabel("Course Rooms: "));
            pInfo.add(setL_RS_small());
            pInfo.add(new JLabel("Auditorys: "));
            pInfo.add(setL_RS_big());
            pInfo.add(new JLabel("Hero's Quarters: "));
            pInfo.add(setL_RQ());
            pInfo.add(new JLabel("Small Dorms: "));
            pInfo.add(setL_RD_small());
            pInfo.add(new JLabel("Big Dorms: "));
            pInfo.add(setL_RD_big());
            pInfo.add(new JLabel("Total maintenance: "));
            pInfo.add(setL_Maint());
        }
        return pInfo;
    }
    private JPanel setP_RD_small() {
        if(pRD_small==null) {
            pRD_small = new JPanel(new java.awt.GridLayout(4,1));
            pRD_small.add(new JLabel("Small Dorm"));
            pRD_small.add(new JLabel("Size: 30 Students"));
            int maint = new resources.rooms.RoomDorm("", 30, 1).getMaintenance();
            pRD_small.add(new JLabel("Maintenance: "+String.valueOf(maint)+"g/Year"));
            pRD_small.add(btBRD(30,300));
            pRD_small.setBorder(new LineBorder(Color.BLACK));
        }
        return pRD_small;
    }
    private JPanel setP_RD_big() {
        if(pRD_big==null) {
            pRD_big = new JPanel(new java.awt.GridLayout(4,1));
            pRD_big.add(new JLabel("Big Sleeping hall"));
            pRD_big.add(new JLabel("Size: 100 Students"));
            int maint = new resources.rooms.RoomDorm("", 100, 1).getMaintenance();
            pRD_big.add(new JLabel("Maintenance: "+String.valueOf(maint)+"g/Year"));
            pRD_big.add(btBRD(100,1000));
            pRD_big.setBorder(new LineBorder(Color.BLACK));
        }
        return pRD_big;
    }
    
    private JLabel setL_RS_small() {
        if(lRS_small==null) {
            lRS_small = new JLabel(String.valueOf(getNr_RS_small()), JLabel.CENTER);
        }
        return lRS_small;
    }
    private JLabel setL_RS_big() {
        if(lRS_big==null) {
            lRS_big = new JLabel(String.valueOf(getNr_RS_big()),JLabel.CENTER);
        }
        return lRS_big;
    }
    private JLabel setL_RQ() {
        if(lRQ==null) {
            lRQ = new JLabel(String.valueOf(getNr_Q()),JLabel.CENTER);
        }
        return lRQ;
    }
    private JLabel setL_RD_small() {
        if(lRD_small==null) {
            lRD_small = new JLabel(String.valueOf(getNr_RD_small()),JLabel.CENTER);
        }
        return lRD_small;
    }
    private JLabel setL_RD_big() {
        if(lRD_big==null) {
            lRD_big = new JLabel(String.valueOf(getNr_RD_big()),JLabel.CENTER);
        }
        return lRD_big;
    }
    private JLabel setL_Maint() {
        if(lMaint==null) {
            lMaint = new JLabel();
            lMaint.setHorizontalAlignment(JLabel.CENTER);
            getMaint();
        }
        return lMaint;
    }
    
    private JButton btClose() {
        JButton bt = new JButton("Close");
        bt.addActionListener((ActionEvent e) -> {
            this.hide();
        });
        return bt;
    }
    private JButton btBRS(int size, int price) {
        JButton bt = new JButton(price+"g");
        bt.addActionListener((ActionEvent e) -> {
            if(dsk.getRes().gold >= price) {
                dsk.getRes().gold -= price;
                if(size==30) {
                    lRS_small.setText(String.valueOf(getNr_RS_small()));
                } else {
                    lRS_big.setText(String.valueOf(getNr_RS_big()));
                }
                getMaint();
                addRoomStudy(size);
                JOptionPane.showMessageDialog(null, "Room built", "Lucky Bastard...", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough gold!", "Unlucky Bastard...", JOptionPane.OK_OPTION); 
            }
        });
        return bt;
    }
    private void addRoomStudy(int size) {
        System.out.println("Creating stuy room");
        System.out.println("Size: "+size);
        System.out.println("Creating name... ");
        String name = new generators.name.RoomNameGenerator().getRoomName();
        System.out.println(name);
        System.out.println("Creating number... ");        
        int nr=30000;
        while(getSRoomNr(nr)) {
            nr++;
        }
        System.out.println(nr);
        System.out.println("Study Room Created. Save!");
        RoomStudy rs = new RoomStudy(name,size,nr);
        this.getAccountingYear().addRS(rs.getMaintenance(), rs.getBuildCost());
        dsk.getRes().lRoomStudy.add(rs);
        resetMenues();
        if(dsk.getMenuRooms()!=null) {
            dsk.getMenuRooms().getStudy().addRoom(rs);
        }
        if(dsk.getMenuCourse()!=null) {
            if(dsk.getMenuCourse().getCourseNew_vanilla()!=null) {
                dsk.getMenuCourse().getCourseNew().fillCB_Room();
            }
        }
    }
    private boolean getSRoomNr(int nr) {
        return dsk.getRes().lRoomStudy.stream().anyMatch(pRS -> pRS.getRoomNr()==nr);
    }
    private JButton btBRQ() {
        JButton bt = new JButton("400g");
        bt.addActionListener((ActionEvent e) -> {
            if(dsk.getRes().gold>=400) {
                addRoomQuarter();
                dsk.getRes().gold -= 400;
                lRQ.setText(String.valueOf(getNr_Q()));
                getMaint();
                JOptionPane.showMessageDialog(null, "Room built", "Lucky Bastard...", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough gold!", "Unlucky Bastard...", JOptionPane.OK_OPTION); 
            }
        });
        return bt;
    }
    private void addRoomQuarter() {
        String name = new generators.name.RoomNameGenerator().getRoomName();
        int nr=40000;
        while(getQNr(nr)) {
            nr++;
        }
        RoomQuarter rq = new RoomQuarter(name,nr);
        dsk.getRes().lRoomQuarter.add(rq);
        this.getAccountingYear().addRQ(rq.getMaintenance(), rq.getBuildCost());
        if(dsk.getMenuRooms()!=null) {
            dsk.getMenuRooms().getQu().addRoom(rq);
        }
        resetMenues();
    }
    private boolean getQNr(int nr) {
        return dsk.getRes().lRoomQuarter.stream().anyMatch(ppRQ -> ppRQ.getRoomNr()==nr);
    }
    private JButton btBRD(int size, int price) {
        JButton bt = new JButton(price+"g");
        bt.addActionListener((ActionEvent e) -> {
            if(dsk.getRes().gold>=price) {
                addRoomDorm(size);
                dsk.getRes().gold -= price;
                if(size==30) {
                    lRD_small.setText(String.valueOf(getNr_RD_small()));
                } else {
                    lRD_big.setText(String.valueOf(getNr_RD_big()));
                }
                getMaint();
                JOptionPane.showMessageDialog(null, "Room built", "Lucky Bastard...", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Not enough gold!", "Unlucky Bastard...", JOptionPane.OK_OPTION); 
            }
        });
        return bt;
    }
    public void addRoomDorm(int size) {
        String name = new generators.name.RoomNameGenerator().getRoomName();
        int nr=50000;
        try {
            while(getRDNr(nr)) {
                nr++;
            }
        } catch(java.lang.NullPointerException e) {
            nr=1;
        }
        RoomDorm rd = new RoomDorm(name, size,nr);
        dsk.getRes().lRoomDorm.add(rd);
        this.getAccountingYear().addRD(rd.getMaintenance(), rd.getBuildCost());
        resetMenues();
        if(dsk.getMenuRooms()!=null) {
            dsk.getMenuRooms().getDorm().addRoom(rd);
        }
    }
    private boolean getRDNr(int nr) {
        return dsk.getRes().lRoomDorm.stream().anyMatch(pRD -> pRD.getRoomNr()==nr);
    }
    
    public void getMaint() {
        maintenance = 0;
        if(!dsk.getRes().lRoomDorm.isEmpty()) {
            dsk.getRes().lRoomDorm.forEach(rD -> addMaint(rD));
        }
        if(!dsk.getRes().lRoomQuarter.isEmpty()) {
            dsk.getRes().lRoomQuarter.forEach(rQ -> addMaint(rQ));
        }
        if(!dsk.getRes().lRoomStudy.isEmpty()) {
            dsk.getRes().lRoomStudy.forEach(rS -> addMaint(rS));
        }
        lMaint.setText(String.valueOf(maintenance)+"g/Year");
    }
    private void addMaint(resources.rooms.Room room) {
        maintenance = maintenance+room.getMaintenance();
    }
    private long getNr_RS_small() {
        return dsk.getRes().lRoomStudy.stream().filter(pRS -> pRS.getRoomSize()==30).count();
    }
    private long getNr_RS_big() {
        return dsk.getRes().lRoomStudy.stream().filter(pRS -> pRS.getRoomSize()==100).count();
    }
    private int getNr_Q() {
        return dsk.getRes().lRoomQuarter.size();

    }
    private long getNr_RD_small() {
        return dsk.getRes().lRoomDorm.stream().filter(pRD -> 
                    pRD.getRoomSize()==30).count();
    }
    private long getNr_RD_big() {
        return dsk.getRes().lRoomDorm.stream().filter(pRD -> 
                    pRD.getRoomSize()==100).count();
    }
    
    private void resetMenues() {
        if(dsk.getRB()!=null) {
            dsk.getRB().setJLStu_addSpace();
            dsk.getRB().setJLUpkeep();
        }
        if(dsk.getMenuAdmin()!=null) {
            dsk.getMenuAdmin().getIFAcc().getThisYear().renewTables();
        }
    }
    
    private Accounting getAccountingYear() {
        return dsk.getRes().lAccounting.stream().filter(pAcc -> pAcc.getYear()==dsk.getRes().year).findAny().get();
    }
}
