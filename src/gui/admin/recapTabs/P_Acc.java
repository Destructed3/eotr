/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.recapTabs;

import gui.Mainframe;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import resources.Inhabitants.InhTea;
import resources.activity.ActivityJob;
import resources.rooms.RoomDorm;
import resources.rooms.RoomQuarter;
import resources.rooms.RoomStudy;
import resources.Accounting;

/**
 *
 * @author Die Axt
 */
public class P_Acc extends JPanel {
    
    public P_Acc(Accounting pacc, Mainframe pdsk) {
        super();
        this.acc=pacc;
        this.dsk=pdsk;
        this.thisYear=pdsk.getRes().year == acc.getYear();
        run();
    }
    
    private void run() {
        this.setLayout(new java.awt.GridLayout(1, 1));
        this.add(pMain());
    }
    
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new BorderLayout());
            pMain.add(pHeader(),BorderLayout.NORTH);
            pMain.add(pTables(),BorderLayout.CENTER);
        }
        return pMain;
    }
    
    private JPanel pHeader() {
        if(pHeader==null) {
            pHeader = new JPanel(new java.awt.GridLayout(1,2));
            pHeader.add(new JLabel("Income",JLabel.LEFT));
            pHeader.add(new JLabel("Expenses",JLabel.RIGHT));
        }
        return pHeader;
    }
    private JPanel pTables() {
        if(pTables==null) {
            pTables = new JPanel(new java.awt.GridLayout(1, 2));
            pTables.add(pIncome());
            pTables.add(pExpenses());
        }
        return pTables;
    }
    
    private JPanel pIncome() {
        if(pIncome==null) {
            pIncome = new JPanel(new java.awt.BorderLayout());
            pIncome.add(new JScrollPane(tableIncome()),BorderLayout.CENTER);
            pIncome.add(pSum_inc(),BorderLayout.SOUTH);
        }
        return pIncome;
    }
    
    private JTable tableIncome() {
        if(tbIncome==null) {
            tbIncome = new JTable(tm_inc());
            tbIncome.getColumnModel().getColumn(1).setCellRenderer(getRightRenderer());
        }
        return tbIncome;
    }
    private DefaultTableModel tm_inc() {
        if(tm1==null) {
            if(thisYear) {
                tm1 = new DefaultTableModel(getIncome_ty(),columns);
            } else {
                tm1 = new DefaultTableModel(getIncome(),columns);
            }
        }
        return tm1;
    }
    private String[][] getIncome() {
        if(income==null) {
            String[][] s = {
                {"Tuitions*",String.valueOf(acc.getTuitions())},
                {"Jobs",String.valueOf(acc.getIncomeJob_Tea())},
                {"Adventure",String.valueOf(acc.getIncomeAdv())},
                {"Other","0"}
            };
            income = s;
        }
        return income;
    }
    private String[][] getIncome_ty() {
        if(income==null) {
            String[][] s = {
                {"Tuitions*",String.valueOf(acc.getTuitions())},
                {"Jobs",String.valueOf(getInc_Jobs())},
                {"Adventure",String.valueOf(getInc_Adv())},
                {"Other","0"}
            };
            income = s;
        }
        return income;
    }
    
    private int getInc_Jobs() {
        int inc_job = 0;
        for(ActivityJob aj : dsk.getRes().lJob) {
            inc_job=inc_job+aj.getIncome();
        }
        return inc_job;
    }
    private int getInc_Adv() {
        int inc_adv=0;
        return inc_adv;
    }
    private int getSum_inc_ty() {
        return acc.getTuitions()+getInc_Jobs()+getInc_Adv();
    }
    
    private JPanel pSum_inc() {
        if(pSum_inc==null) {
            pSum_inc = new JPanel(new java.awt.GridLayout(1,2));
            pSum_inc.add(new JLabel("Sum income",JLabel.LEFT));
            pSum_inc.add(lInc());
        }
        return pSum_inc;
    }
    private JLabel lInc() {
        if(lSum_inc==null) {
            lSum_inc = new JLabel(String.valueOf(acc.getIncome_Total())+" ",JLabel.RIGHT);
        }
        return lSum_inc;
    }    
    
    private JPanel pExpenses() {
        if(pExpenses==null) {
            pExpenses = new JPanel(new java.awt.BorderLayout());
            pExpenses.add(new JScrollPane(tableExpenses()),BorderLayout.CENTER);
            pExpenses.add(pSum_exp(),BorderLayout.SOUTH);
        }
        return pExpenses;
    }
    
    private JTable tableExpenses() {
        if(tbExpenses==null) {
            tbExpenses = new JTable(tm_exp());
            tbExpenses.getColumnModel().getColumn(1).setCellRenderer(getRightRenderer());
        }
        return tbExpenses;
    }
    private DefaultTableModel tm_exp() {
        if(tm2==null) {
            if(thisYear) {
                tm2 = new DefaultTableModel(getExpenses_ty(),columns);
            } else {
                tm2 = new DefaultTableModel(getExpenses(),columns);
            }
        }
        return tm2;
    }
    private String[][] getExpenses() {
        if(expenses==null) {
            String[][] s ={
                {"Teacher Wages",String.valueOf(acc.getTeaWages())},
                {"Upkeep studys",String.valueOf(acc.getMaintRS())},
                {"Upkeep Quarters",String.valueOf(acc.getMaintRQ())},
                {"Upkeep Dorms",String.valueOf(acc.getMaintRD())},
                {"Build costs",""},
                {"-> Studys",String.valueOf(acc.getCostNewRS())},
                {"-> Quarters",String.valueOf(acc.getCostNewRQ())},
                {"-> Dorms",String.valueOf(acc.getCostNewRD())},
                {"Adventure preparations",String.valueOf(acc.getExpensesAdv())},
                {"Other","0"}};
            expenses=s;
        }
        return expenses;
    }
    private String[][] getExpenses_ty() {
        if(expenses==null) {
            String[][] s ={
                {"Teacher Wages",String.valueOf(getTeaWages())},
                {"Upkeep studys",String.valueOf(getMaintRoomsStudy())},
                {"Upkeep Quarters",String.valueOf(getMaintRoomsQuarter())},
                {"Upkeep Dorms",String.valueOf(getMaintRoomsDorm())},
                {"Build costs",""},
                {"-> Studys",String.valueOf(acc.getCostNewRS())},
                {"-> Quarters",String.valueOf(acc.getCostNewRQ())},
                {"-> Dorms",String.valueOf(acc.getCostNewRD())},
                {"Adventure preparations",String.valueOf(getExp_Adv())},
                {"Other","0"}};
            expenses=s;
        }
        return expenses;
    }
    
    private int getTeaWages() {
        int tea_wages=0;
        for(InhTea tea : dsk.getRes().lTea) {
            tea_wages=tea_wages+tea.getCost();
        }
        return tea_wages;
    }
    private int getMaintRoomsStudy() {
        int maint_rs=0;
        for(RoomStudy rs : dsk.getRes().lRoomStudy) {
            maint_rs=maint_rs+rs.getMaintenance();
        }
        return maint_rs;
    }
    private int getMaintRoomsQuarter() {
        int maint_rq=0;
        for(RoomQuarter rq : dsk.getRes().lRoomQuarter) {
            maint_rq=maint_rq+rq.getMaintenance();
        }
        return maint_rq;
    }
    private int getMaintRoomsDorm() {
        int maint_rd=0;
        for(RoomDorm rd : dsk.getRes().lRoomDorm) {
            maint_rd=maint_rd+rd.getMaintenance();
        }
        return maint_rd;
    }
    private int getExp_Adv() {
        int exp_adv=0;
        return exp_adv;
    }
    private int getSum_exp_ty() {
        return getTeaWages()
                +getMaintRoomsStudy()+getMaintRoomsQuarter()+getMaintRoomsDorm()+getExp_Adv()
                +acc.getCostNewRS()+acc.getCostNewRQ()+acc.getCostNewRD();
    }
    
    private JPanel pSum_exp() {
        if(pSum_exp==null) {
            pSum_exp = new JPanel(new java.awt.GridLayout(1,2));
            pSum_exp.add(new JLabel("Sum expenses:",JLabel.LEFT));
            pSum_exp.add(lSum_exp());
        }
        return pSum_exp;
    }
    private JLabel lSum_exp() {
        if(lSum_exp==null) {
            if(thisYear) {
                lSum_exp = new JLabel(String.valueOf(getSum_exp_ty())+" ",JLabel.RIGHT);
            } else {
                lSum_exp = new JLabel(String.valueOf(acc.getExpenses_total())+" ",JLabel.RIGHT);
            }
        }
        return lSum_exp;
    }
    
    private DefaultTableCellRenderer getRightRenderer() {
        if(rightRenderer==null) {
            rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        }
        return rightRenderer;
    }
    
    public Accounting getAccounting() {
        return acc;
    }
    
    public void renewTables() {
        renewInc();
        renewExp();
    }
    private void renewInc() {
        tbIncome.setValueAt(getInc_Jobs(), 1, 1);
        tbIncome.setValueAt(getInc_Adv(), 2, 1);
        tbIncome.setValueAt(String.valueOf(0), 3, 1);
        lSum_inc.setText(String.valueOf(getSum_inc_ty())+" ");
    }
    private void renewExp() {
        tbExpenses.setValueAt(getTeaWages(),0,1);
        tbExpenses.setValueAt(getMaintRoomsStudy(), 1, 1);
        tbExpenses.setValueAt(getMaintRoomsQuarter(), 2, 1);
        tbExpenses.setValueAt(getMaintRoomsDorm(), 3, 1);
        tbExpenses.setValueAt(acc.getCostNewRS(), 5, 1);
        tbExpenses.setValueAt(acc.getCostNewRQ(), 6, 1);
        tbExpenses.setValueAt(acc.getCostNewRD(), 7, 1);
        tbExpenses.setValueAt(getExp_Adv(), 8, 1);
        lSum_exp.setText(String.valueOf(getSum_exp_ty())+" ");
    }
    
    Mainframe dsk;
    Accounting acc;
    
    boolean thisYear;
    
    JPanel pMain;
    
    JPanel pHeader;
    JPanel pTables;
    JPanel pTool;
    
    JPanel pIncome;
    JPanel pExpenses;
    
    JPanel pSum_inc;
    JPanel pSum_exp;
    
    JTable tbIncome;
    JTable tbExpenses;
    
    DefaultTableModel tm1;
    DefaultTableModel tm2;
    
    JLabel lSum_inc;
    JLabel lSum_exp;
    
    String[] columns = {"Position","Value"};
    String[][] income;
    String[][] expenses;
    
    DefaultTableCellRenderer rightRenderer;
    
}
