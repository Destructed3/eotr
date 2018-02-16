/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import gui.Mainframe;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Die Axt
 */
public class IFDemand extends root.IFTemplate {
    public IFDemand(Mainframe pdsk) {
        super("Demand");
        this.dsk=pdsk;
        this.gDem=new GetDemand(dsk);
        run();
    }
    
    private void run() {
        setIF(pMain(),pTool());
    }
    
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.GridLayout(5,1));
            pMain.add(pRow0());
            pMain.add(pRow1());
            pMain.add(pRow2());
            pMain.add(pRow3());
            pMain.add(pRow4());
        }
        return pMain;
    }
    private JPanel pTool() {
        if(pTool==null) {
            pTool = new JPanel(new java.awt.FlowLayout());
            pTool.add(btClose());
        }
        return pTool;
    }
    
    private JPanel pRow0() {
        JPanel p = new JPanel(new GridLayout(1,1));
        p.add(new JLabel("Demand for courses"));
        return p;
    }
    private JPanel pRow1() {
        JPanel p = new JPanel(new GridLayout(1,2));
        p.add(new JLabel("Phisics:"));
        p.add(lDem_Phy());
        return p;
    }
    private JPanel pRow2() {
        JPanel p = new JPanel(new GridLayout(1,2));
        p.add(new JLabel("Mental:"));
        p.add(lDem_Men());
        return p;
    }
    private JPanel pRow3() {
        JPanel p = new JPanel(new GridLayout(1,2));
        p.add(new JLabel("Social:"));
        p.add(lDem_Soc());
        return p;
    }
    private JPanel pRow4() {
        JPanel p = new JPanel(new GridLayout(1,2));
        p.add(new JLabel("Magic:"));
        p.add(lDem_Mag());
        return p;
    }
    
    private JLabel lDem_Phy() {
        if(lDem_Phy==null) {
            lDem_Phy = new JLabel(gDem.getDemand(0));
            lDem_Phy.setToolTipText("Demand for Courses that improve the Physical-Attribute");
        }
        return lDem_Phy;
    }
    private JLabel lDem_Men() {
        if(lDem_Men==null) {
            lDem_Men = new JLabel(gDem.getDemand(1));
            lDem_Men.setToolTipText("Demand for Courses that improve the Mental-Attribute");
        }
        return lDem_Men;
    }
    private JLabel lDem_Soc() {
        if(lDem_Soc==null) {
            lDem_Soc = new JLabel(gDem.getDemand(2));
            lDem_Soc.setToolTipText("Demand for courses that improve the ability to flirt");
        }
        return lDem_Soc;
    }
    private JLabel lDem_Mag() {
        if(lDem_Mag==null) {
            lDem_Mag = new JLabel(gDem.getDemand(3));
            lDem_Mag.setToolTipText("Demand for... hey, who doesn t want to be able to cast spells?");
        }
        return lDem_Mag;
    }
    
    
    
    Mainframe dsk = null;
    GetDemand gDem = null;
    
    JPanel pMain = null;
    JPanel pTool = null;
    
    JLabel lDem_Phy = null;
    JLabel lDem_Men = null;
    JLabel lDem_Soc = null;
    JLabel lDem_Mag = null;
    
    int dem_Phy;
    int dem_Men;
    int dem_Soc;
    int dem_Mag;
}
