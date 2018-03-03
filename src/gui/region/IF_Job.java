/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.region;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import resources.activity.ActivityJob;
import resources.Inhabitants.InhTea;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public class IF_Job extends root.IFTemplate {
    public IF_Job(Region preg,Mainframe pdsk,ActivityJob paj) {
        super(paj.getJob_string()+"-Job in "+preg.getName());
        this.dsk=pdsk;
        this.tea = dsk.getRes().lTea.get(0);
        this.reg=preg;
        this.selectedDay=0;
        this.aj=paj;
        run();
    }
    
    private void run() {
        setIF(pMain(),pTool());
        this.setSize(600,400);
    }
    //Main JPanels
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.BorderLayout());
            pMain.add(cbTea(),BorderLayout.NORTH);
            pMain.add(pTable(),BorderLayout.CENTER);
            pMain.add(pExplain(),BorderLayout.SOUTH);
        }
        return pMain;
    }
    private JPanel pTool() {
        if(pTool==null) {
            pTool = new JPanel(new java.awt.FlowLayout());
            pTool.add(btGetJob());
            pTool.add(btClose());
        }
        return pTool;
    }
    //ComboBox to chose Teacher
    private JComboBox cbTea() {
        if(cbTea==null) {
            cbTea = new JComboBox();
            dsk.getRes().lTea.forEach(pTea -> {
                String output = 
                        pTea.getNumber()+" | "
                        +pTea.getName()+" | "
                        +pTea.getAttribute(0)+" | "
                        +pTea.getAttribute(1)+" | "
                        +pTea.getAttribute(2)+" | "
                        +pTea.getAttribute(3);
                cbTea.addItem(output);
            });
            cbTea.addItemListener(ilTea());
        }
        return cbTea;
    }
    //set up Table-Panel
    private JPanel pTable() {
        if(pTable==null) {
            pTable = new JPanel(new java.awt.BorderLayout());
            pTable.add(new JScrollPane(timeTable()), BorderLayout.CENTER);
        }
        return pTable;
    }
    //set up Table
    private JTable timeTable() {
        if(timeTable==null) {
            timeTable = new JTable(tm_TT());
            timeTable.addMouseListener(maSelectCell());
        }
        return timeTable;
    }
    private root.TimeTable tm_TT() {
        if(tm_TT==null) {
            dat = tea.getTimeTable();
            tm_TT = new root.TimeTable(dat, dsk);
        }
        return tm_TT;
    }
    private MouseAdapter maSelectCell() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int pSelectedDay = timeTable.columnAtPoint(evt.getPoint());
                if(dayAv(pSelectedDay)) {
                    for(int hour=0;hour<10;hour++) {
                        String s = String.valueOf(timeTable.getValueAt(hour, selectedDay));
                        if(!s.equals("") && s.equals(getJob_String())) {
                            timeTable.setValueAt("", hour, selectedDay);
                        }
                        timeTable.setValueAt(getJob_String(), hour, pSelectedDay);
                    }
                    selectedDay = pSelectedDay;
                }
            } 
        };
        return ma;
    }
    private boolean dayAv(int pSelectedDay) {
        for(int hour=0;hour<10;hour++) {
            if(selectedDay==pSelectedDay) {
                return false;
            }
            if(!timeTable.getValueAt(hour, pSelectedDay).equals("")) {
                JOptionPane.showMessageDialog(null, "<html>Day not available.<b>Chose another day!</html>", "Day not available", JOptionPane.OK_OPTION);
                return false;
            }
        }
        return true;
    }
    //Set up Buttons to chose Jobtype
    private boolean isDayAv() {
        String s;
        for(int hour=0;hour<10;hour++) {
            s = String.valueOf(tea.getTimeTableHour(hour, selectedDay));
            if(!s.equals("")) {
                return false;
            }
        }
        return true;
    }
    
    private JPanel pExplain() {
        if(pExplain==null) {
            pExplain = new JPanel(new GridLayout(2,1));
            pExplain.add(new JLabel("To do a job, one has to use a whole days time.", JLabel.CENTER));                
            pExplain.add(new JLabel("Any jobs remaining when you end the year will be available to your students", JLabel.CENTER));
        }
        return pExplain;
    }
    
    private ItemListener ilTea() {
        if(ilTea==null) {
            ilTea = ((ItemEvent e) -> {
                String tNr = String.valueOf(cbTea.getSelectedItem());
                tea = dsk.getRes().lTea.stream().filter(pTea -> 
                        pTea.getNumber()==Integer.parseInt(tNr.substring(0, 5))).findAny().get();
                renewTT();
            });
        }
        return ilTea;
    }
    private void renewTT() {
        if(timeTable==null) {
            for(int hour=0;hour<10;hour++) {
                for(int day=0;day<7;day++) {
                    timeTable().setValueAt(checkOccupied(hour,day), hour, day);
                }
            }
        }
    }
    
    private JButton btGetJob() {
        if(btGetJob==null) {
            btGetJob = new JButton("Get Job");
            btGetJob.addActionListener(alGetJob());
        }
        return btGetJob;
    }
    private ActionListener alGetJob() {
        ActionListener al = (ActionEvent e) -> {
            if(isDayAv() && isJobAv()) {
                getJob();
                removeJob();
                dsk.getMenuInh().getTea().showTea(tea.getNumber());
                dsk.getMenuRegion().getIFRegionMenu().getIFReg(reg.getID()).refreshTables();
                this.dispose();
            } else if(!isDayAv()){
                JOptionPane.showMessageDialog(null, "Please select a free day", "Day occupied", JOptionPane.OK_OPTION);
            } else if(!isJobAv()) {
                JOptionPane.showMessageDialog(null, "This job isn t acutally available...?", "Feeling unlucky?", JOptionPane.OK_OPTION);
            }
        };
        return al;
    }
    private boolean isJobAv() {
        return dsk.getRes().lRegion.stream().anyMatch(pReg -> pReg.getID().equals(reg.getID()));
    }
    private void getJob() {
        tea.addActivity(takeJob());
        dsk.getMenuInh().refreshTT(tea.getNumber());
    }
    private ActivityJob takeJob() {
        String s = aj.getID();
        int teaNr = tea.getNumber();
        theJob(s).setHost(teaNr);
        theJob(s).setAdvRegion(reg); 
        theJob(s).createID(dsk);
        theJob(s).setTimeTable(selectedDay);
        theJob(s).setIncome(dsk);
        return theJob(s);
    }
    private ActivityJob theJob(String id) {
        return dsk.getRes().lJob.stream().filter(pJob -> 
                pJob.getID().equals(id)).findAny().get();
    }
    private void removeJob() {
        dsk.getMenuRegion().getIFRegionMenu().getIFReg(reg.getID()).removeJob(this);
    }
    
    public void renewCBTea() {
        if(cbTea==null) {
            cbTea();
        } else {
            cbTea.removeAllItems();
            dsk.getRes().lTea.forEach(pTea -> {
                String output = pTea.getNumber()+" | "
                        +pTea.getName()+" | "
                        +pTea.getAttribute(0)+" | "
                        +pTea.getAttribute(1)+" | "
                        +pTea.getAttribute(2)+" | "
                        +pTea.getAttribute(3);
                cbTea.addItem(output);
            });
        }
    }
    
    
    private String checkOccupied(int hour, int day) {
        if(tea.getTimeTableHour(hour, day).equals("")) {
            return "";
        } else {
            return "Occupied";
        }
    }
    private String getJob_String() {
        switch(selectedJob) {
            default:
                return "Guarding Duty";
            case 1:
                return "Advising";
            case 2:
                return "Entertaining";
            case 3:
                return "Casting spells";
        }
    }
    
    public void refreshTT() {
        for(int h=0;h<10;h++) {
            for(int d=0;d<7;d++) {
                if(tea.getTimeTableHour(h, d).equals("")) {
                    tm_TT.setValueAt("", h, d);
                } else {
                    tm_TT.setValueAt("Occupied", h, d);
                }
            }
        }
    }
    
    public ActivityJob get_TheJob() {
        return aj;
    }
    
    Mainframe dsk = null;
    Region reg = null;
    InhTea tea = null;
    int selectedJob;
    
    ActivityJob aj = null;
    
    int selectedDay;
    String oldJob;
    
    JPanel pMain = null;
    JPanel pTool = null;
    
    JPanel pTable = null;
    
    JPanel pExplain = null;
       
    JComboBox cbTea = null;
    
    JTable timeTable = null;
    String[][] dat = null;
    root.TimeTable tm_TT = null;
    
    ItemListener ilTea = null;
    
    JButton btGetJob = null;
}
