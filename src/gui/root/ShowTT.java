/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import resources.Inhabitants.Inhabitants;
import resources.Inhabitants.InhTea;
import resources.Inhabitants.InhStu;
import resources.activity.ActivityAdventure;
import resources.activity.Activity;
import resources.activity.ActivityJob;
import resources.activity.ActivityCourse;
import gui.Mainframe;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import resources.*;

/**
 *
 * @author Die Axt
 */
public class ShowTT extends root.IFTemplate {
    public ShowTT(Mainframe pdsk) {
        super("");
        this.dsk=pdsk;
        run();
    }
    
    private void run() {
        setIF(pTop(),pMain(),pTool());
    }
    private JPanel pTop() {
        JPanel p = new JPanel(new java.awt.FlowLayout());
        p.add(cbChoseType());
        p.add(setCBChose_Item());
        return p;
    }
    private JComboBox cbChoseType() {
        if(cbChose_Type==null) {
            String[] s = {"Teacher","Students","Adventures","Courses","Jobs"};
            cbChose_Type = new JComboBox(s);
            cbChose_Type.setSelectedIndex(0);
            cbChose_Type.addItemListener(ilChose_Type());
        }
        return cbChose_Type;
    }
    private ItemListener ilChose_Type() {
        ItemListener il = (ItemEvent e) -> { 
            String selection = (String) cbChose_Type.getSelectedItem();
            setCBChose_Item_List(selection);
        };
        return il;
    }
    private JComboBox setCBChose_Item() {
        if(cbChose_Item==null) {
            cbChose_Item = new JComboBox();
            setCBChose_Item_List("Teacher");
            cbChose_Item.addItemListener(ilChose_Item());
            cbChose_Item.setSelectedIndex(0);
        }
        return cbChose_Item;
    }
    private ItemListener ilChose_Item() {
        ItemListener il = (ItemEvent e) -> {
            Object o = cbChose_Item.getSelectedItem();
            String selection = String.valueOf(o);
            refreshTable(selection);
        };
        return il;
    }
    private void setCBChose_Item_List(String s) {
        cbChose_Item.removeAllItems();
        switch(s) {
            default:
                setCBChose_Item_Tea();
            case "Students":
                setCBChose_Item_Stu();
            case "Adventures":
                setCBChose_Item_AA();
            case "Courses":
                setCBChose_Item_AC();
            case "Jobs":
                setCBChose_Item_AJ();
        }
        cbChose_Item.setSelectedIndex(0);
    }
    private void setCBChose_Item_Tea() {
        dsk.getRes().lTea.forEach(pTea -> {
            String output = pTea.getNumber()+" | "+pTea.getName();
            cbChose_Item.addItem(output);
        });
    }
    private void setCBChose_Item_Stu() {
        dsk.getRes().lStu.forEach(pStu -> {
            String output = pStu.getNumber()+" | "+pStu.getName();
            cbChose_Item.addItem(output);
        });
    }
    private void setCBChose_Item_AA() {
        dsk.getRes().lAdv.forEach(pAA -> {
            String output = pAA.getID()+" | "+pAA.getName();
            cbChose_Item.addItem(output);
        });
    }
    private void setCBChose_Item_AC() {
        dsk.getRes().lCourse.forEach(pAC -> {
            String output = pAC.getID()+" | "+pAC.getName();
            cbChose_Item.addItem(output);
        });
    }
    private void setCBChose_Item_AJ() {
        dsk.getRes().lJob.forEach(pAJ -> {
            String output = pAJ.getID()+" | "+pAJ.getName();
            cbChose_Item.addItem(output);
        });
    }
    
    private JPanel pMain() {
        JPanel p = new JPanel(new java.awt.GridLayout(1, 1));
        p.add(new JScrollPane(setTable()));
        return p;
    }
    private JTable setTable() {
        if(table==null) {
            table = new JTable(setTM());
        }
        return table;
    }
    private DefaultTableModel setTM() {
        if(tm==null) {
            String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
            tm=new DefaultTableModel(setDat(),days);
        }
        return tm;
    }
    private String[][] setDat() {
        dat=new String[10][7];
        for(int h=0;h<10;h++) {
            for(int d=0;d<7;d++) {
                dat[h][d]="";
            }
        }
        return dat;
    }
    private void refreshTable(String selection) {
        switch(selection){
            default:
                setTable_Tea();
            case "Students":
                setTable_Stu();
            case "Adventures":
                setTable_AA();
            case "Courses":
                setTable_AC();
            case "Jobs":
                setTable_AJ();
        }
    }
    private void setTable_Tea() {
        String selection = String.valueOf(cbChose_Item.getSelectedItem());
        InhTea tea = dsk.getRes().lTea.stream().filter(pTea -> 
                pTea.getNumber()==Integer.parseInt(selection.substring(0,5))
            ).findAny().get();
        drawTable_Inh(tea);
    }
    private void setTable_Stu() {
        String selection = (String)cbChose_Item.getSelectedItem();
        InhStu stu = dsk.getRes().lStu.stream().filter(pStu -> 
                pStu.getNumber()==Integer.parseInt(selection.substring(0, 5))
            ).findAny().get();
        drawTable_Inh(stu);
    }
    private void setTable_AA() {
        String selection = (String)cbChose_Item.getSelectedItem();
        ActivityAdventure aa = dsk.getRes().lAdv.stream().filter(pAA -> 
                pAA.getID().equals(selection.substring(0,7))
            ).findAny().get();
        drawTable_Activity(aa);
    }
    private void setTable_AC() {
        String selection = (String)cbChose_Item.getSelectedItem();
        ActivityCourse ac = dsk.getRes().lCourse.stream().filter(pAC -> 
                pAC.getID().equals(selection.substring(0,7))
            ).findAny().get();
        drawTable_Activity(ac);
    }
    private void setTable_AJ() {
        String selection = (String)cbChose_Item.getSelectedItem();
        ActivityJob aj = dsk.getRes().lJob.stream().filter(pAJ -> 
                pAJ.getID().equals(selection.substring(0,7))
            ).findAny().get();
        drawTable_Activity(aj);
    }
    private void drawTable_Inh(Inhabitants inh) {
        for(int hour=0;hour<10;hour++) {
            for(int day=0;day<7;day++) {
                String cell = inh.getTimeTableHour(hour, day);
                tm.setValueAt(cell, hour, day);
            }
        }
    }
    private void drawTable_Activity(Activity act) {
        for(int hour=0;hour<10;hour++) {
            for(int day=0;day<7;day++) {
                String cell = String.valueOf(act.getTimeTableHourUsed(hour, day));
                tm.setValueAt(cell, hour, day);
            }
        }
    }
    
    private JPanel pTool() {
        JPanel p = new JPanel(new java.awt.FlowLayout());
        p.add(btClose());
        return p;
    }
    
    Mainframe dsk;
    
    JComboBox cbChose_Type=null;
    JComboBox cbChose_Item=null;
    JTable table=null;
    DefaultTableModel tm=null;
    String[][] dat=null;
}
