/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import resources.Inhabitants.InhTea;
import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import root.TableModel_Uneditable;

/**
 *
 * @author Die Axt
 */
public class IFTeacher extends root.IFTemplate {
    public IFTeacher(InhTea ptea, Mainframe pdsk) {
        super(ptea.getName());
        this.dsk=pdsk;
        this.tea=ptea;
        this.isFH=ptea.isFH();
        run();
    }
    
    private void run() {
        setIF(setP_Main(),setPTool());
    }
    
    private JPanel setP_Main() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.GridLayout(2, 1));
            pMain.add(setP_Attributes());
            if(isFH) {
                
            } else {
                pMain.add(new JScrollPane(setTableTT()));
                refreshTT();
            }
            
        }
        return pMain;
    }
    
    private JPanel setP_Attributes() {
        if(pAttributes==null) {
            pAttributes = new JPanel(new java.awt.BorderLayout());
            pAttributes.add(setP_Attr_1stRow(), BorderLayout.NORTH);
            pAttributes.add(setP_Attr_Center(), BorderLayout.CENTER);
            
            
        }
        return pAttributes;
    }
    private JPanel setP_Attr_1stRow() {
        JPanel p = new JPanel(new java.awt.GridLayout(1, 2));
        p.add(setL_Name());
        p.add(setL_Bckg());
        return p;
    }
    private JPanel setP_Attr_Center() {
        JPanel p = new JPanel(new java.awt.GridLayout(3,4));
        p.add(new JLabel("Physical:"));
        p.add(setL_Phy());
        p.add(new JLabel("Mental:"));
        p.add(setL_Men());
        p.add(new JLabel("Social:"));
        p.add(setL_Soc());
        p.add(new JLabel("Magical:"));
        p.add(setL_Mag());
        p.add(new JLabel("Teaching:"));
        p.add(setL_Teach());
        p.add(new JLabel("Wage:"));
        p.add(setL_Wage());
        return p;
    }
    private JLabel setL_Name() {
        if(lName==null) {
            lName = new JLabel(tea.getName());
        }
        return lName;
    }
    private JLabel setL_Bckg() {
        if(lBckg==null) {
            lBckg = new JLabel();
        }
        return lBckg;
    }
    private JLabel setL_Phy() {
        if(lPhy==null) {
            String attribute = String.valueOf(tea.getAttribute(0));
            lPhy = new JLabel(attribute);
        }
        return lPhy;
    }
    private JLabel setL_Men() {
        if(lMen==null) {
            String attribute = String.valueOf(tea.getAttribute(1));
            lMen = new JLabel(attribute);
        }
        return lMen;
    }
    private JLabel setL_Soc() {
        if(lSoc==null) {
            String attribute = String.valueOf(tea.getAttribute(2));
            lSoc = new JLabel(attribute);
        }
        return lSoc;
    }
    private JLabel setL_Mag() {
        if(lMag==null) {
            String attribute = String.valueOf(tea.getAttribute(3));
            lMag = new JLabel(attribute);
        }
        return lMag;
    }
    private JLabel setL_Teach() {
        if(lTeach==null) {
            String teach = String.valueOf(tea.getTeaching()*100);
            lTeach = new JLabel(teach.substring(0,2)+"%");
        }
        return lTeach;
    }
    private JLabel setL_Wage() {
        if(lWage==null) {
            String wage = tea.getCost()+"g/Month";
            lWage = new JLabel(wage);
        }
        return lWage;
    }
    
    private JTable setTableTT() {
        if(tableTT==null) {
            tableTT = new JTable(setTM_TT());
        }
        return tableTT;
    }
    private TableModel_Uneditable setTM_TT() {
        if(tmTT==null) {
            String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
            tmTT = new TableModel_Uneditable(setDat(),days);
        }
        return tmTT;
    }
    private String[][] setDat() {
        if(dat==null) {
            this.dat=tea.getTimeTable();
        }
        return dat;
    }
    
    private JPanel setPTool() {
        if(pTool==null) {
            pTool = new JPanel(new java.awt.FlowLayout());
            if(isFH) {
                pTool.add(btHire());
            } else {
                pTool.add(btJournal());
            }
            pTool.add(btClose());
        }
        return pTool;
    }
    private JButton btHire() {
        if(btHire==null) {
            btHire = new JButton("Hire for "+tea.getCost()*6+"g");
            btHire.addActionListener(alHire());
        }
        return btHire;
    }
    private ActionListener alHire() {
        if(alHire==null) {
            alHire = (ActionEvent e) -> {
                if(canPay() && isRoomAv()) {
                    dsk.addTeacher(tea);
                    dsk.getMenuInh().getTea().showTea(tea.getNumber());
                    this.dispose();
                } else {
                    if(!canPay()) {
                        JOptionPane.showMessageDialog(null, "Need more Money!", "Can't hire "+tea.getName(), JOptionPane.OK_OPTION);
                    }else if(!isRoomAv()) {
                        JOptionPane.showMessageDialog(null, "No quarter available!", "Can't hire "+tea.getName(), JOptionPane.OK_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "Can't hire teacher!", "Can't hire "+tea.getName(), JOptionPane.OK_OPTION);
                    }
                }
            };
        }
        return alHire;
    }
    private boolean canPay() {
        return dsk.getRes().gold>tea.getCost()*6;
    }
    private boolean isRoomAv() {
        return dsk.getRes().lRoomQuarter.stream().anyMatch(pRQ -> pRQ.getResident()==0);
    }
    
    private JButton btJournal() {
        if(btJournal==null) {
            btJournal = new JButton("Journal");
            btJournal.addActionListener(alJournal());
        }
        return btJournal;
    }
    private ActionListener alJournal() {
        if(alJournal==null) {
            alJournal = (ActionEvent e) -> {
                this.hide();
                showJournal();
            };
        }
        return alJournal;
    }
    
    public void refreshTT() {
        System.out.println("Refresh TimeTable Teacher "+tea.getNumber());
        this.tea=refreshTea();
        this.dat=tea.getTimeTable();
        for(int h=0;h<10;h++) {
            for(int d=0;d<7;d++) {
                tableTT.setValueAt(writeCell(h,d), h, d);
            }
        }
    }
    private InhTea refreshTea() {
        int nr = tea.getNumber();
        if(tea.isFH()) {
            for(InhTea pTea : dsk.getRes().teacherForHire) {
                if(pTea.getNumber()==nr) {
                    return pTea;
                }
            }
            return null;
        } else {
            return dsk.getRes().lTea.stream().filter(pTea -> pTea.getNumber()==nr).findAny().get();
        }
    }
    private String writeCell(int h, int d) {
        try {
            String cell = dat[h][d];
            if(!cell.equals("")) {
            System.out.println(h+"|"+d+": |"+cell+"|");
            }
            return fillCell(cell);
        } catch(NullPointerException e) {
            return "ERROR";
        }
    }
    private String fillCell(String cell) {
        if("".equals(cell)) {
            return "";
        } else {
            return getActivity(cell);
        }
    }
    private String getActivity(String aID) {
        String decider = aID.substring(0, 2);
        switch(decider) {
            default:
                JOptionPane.showMessageDialog(null, ""
                        + "<html>"
                        + "IFTeacher_error<br>"
                        + "can t find course"
                        +"ID: "+aID+"<br>"
                        +"Substring: "+decider+"<br>"
                        + "</html>",
                        "Much error",JOptionPane.OK_OPTION);
                return "Occupied";
            case "AA":
                return getAA(aID);
            case "AC":
                return getAC(aID);
            case "AJ":
                return getAJ(aID);
        }
    }
    private String getAA(String id) {
        return dsk.getRes().lAdv.stream().filter(pAA -> pAA.getID().equals(id)).findAny().get().getName();
    }
    private String getAC(String id) {
        return dsk.getRes().lCourse.stream().filter(pAC -> pAC.getID().equals(id)).findAny().get().getName();
    }
    private String getAJ(String id) {
        return dsk.getRes().lJob.stream().filter(pAJ -> pAJ.getID().equals(id)).findAny().get().getName();
    }
    
    private IFJournal journal() {
        if(journal==null) {
            journal = new IFJournal(tea);
            dsk.addJIF(journal);
        }
        return journal;
    }
    private void showJournal() {
        if(journal().isShowing()) {
            journal.toFront();
        } else {
            journal.show();
        }
    }
    
    
    public InhTea getTea() {
        return tea;
    }
    
    Mainframe dsk = null;
    InhTea tea = null;
    IFJournal journal = null;
    
    JPanel pMain = null;
    JPanel pTool = null;
    
    JPanel pAttributes = null;
    JLabel lName = null;
    JLabel lBckg = null;
    JLabel lPhy = null;
    JLabel lMen = null;
    JLabel lSoc = null;
    JLabel lMag = null;
    JLabel lTeach = null;
    JLabel lWage = null;
    
    JTable tableTT = null;
    TableModel_Uneditable tmTT = null;
    
    JButton btJournal = null;
    ActionListener alJournal = null;
    
    JButton btHire = null;
    ActionListener alHire = null;
    
    String[][] dat = null;
    
    boolean isFH;
    
    
}
