/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import gui.Mainframe;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Die Axt
 */
public class TimeTable extends DefaultTableModel {

    public TimeTable(String pdat[][], Mainframe pdsk,boolean isNewCourse) {
        System.out.println("Table_NewCourse_String");
        this.dsk=pdsk;
        this.sDat=pdat;
        setDataVector();
    }
    public TimeTable(String pdat[][], Mainframe pdsk) {
        System.out.println("Table_String");
        this.dsk=pdsk;
        this.sDat=new String[10][7];
        convertDat(pdat);
        setDataVector();
    }
    public TimeTable(boolean pdat[][], Mainframe pdsk) {
        System.out.println("Table_boolean");
        this.dsk=pdsk;
        this.sDat=new String[10][7];
        convertDat(pdat);
        setDataVector();
    }
    
    Mainframe dsk = null;
    String[][] sDat = null;
    String[] days={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
    
    public void setDataVector(String[][] dat) {
        this.setDataVector(dat,days);
    }
    
    private void setDataVector() {
        this.setDataVector(sDat, days);        
    }
    private void convertDat(String[][] dat) {
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                sDat[row][column]=setCell(dat[row][column]);
            }
        }
    }
    private void convertDat(boolean[][] dat) {
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                sDat[row][column]=setCell(dat[row][column]);
            }
        }
    }
    
    private String setCell(String cell) {
        String nothing="";
        if(cell==null || nothing.equals(cell)) {
            return ""; 
        } else {
            System.out.println(cell);
            return determin_A(cell);
        }
        
    }
    private String setCell(boolean b) {
        if(b) {
            return "Occupied";
        } else {
            return "";
        }
    }
    
    public String determin_A(String cell) {
        String decider = cell.substring(0,2);
        System.out.println(decider);
        try {
            switch(decider) {
            default:
                return findAA(cell);
            case "AC":
                return findAC(cell);
            case "AJ":
                return findAJ(cell);
        }
        } catch(java.util.NoSuchElementException e) {
            JOptionPane.showMessageDialog(null, e+"|"+cell, "Wut?", JOptionPane.OK_OPTION);
            return "";
        }
    }
    public String findAC(String id) {
        return dsk.getData().lCourse.stream().filter(ac -> 
                ac.getID().equals(id)).findAny().get().getName();
    }
    public String findAA(String id) {
        return dsk.getData().lAdv.stream().filter(pAdv -> 
                pAdv.getID().equals(id)).findAny().get().getName();
    }
    public String findAJ(String id) {
        return dsk.getData().lJob.stream().filter(pAJ -> 
                pAJ.getID().equals(id)).findAny().get().getName();
    }
    public void refresh(String[][] dat) {
        convertDat(dat);
        setDataVector(sDat, days);
    }
}
