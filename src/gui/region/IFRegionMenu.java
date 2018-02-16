/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.region;

import generators.ActivityGenerator;
import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.regions.Region;

/**
 *
 * @author Die Axt
 */
public class IFRegionMenu extends root.IFTemplate {
    public IFRegionMenu(Mainframe pdsk) {
        super("");
        this.dsk = pdsk;
        this.aG = new ActivityGenerator(dsk);
        run();
    }
    
    private void run() {
        setIF(pMain(),pTool());
    }
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.BorderLayout());
            pMain.add(new JScrollPane(lRegions()),BorderLayout.CENTER);
        }
        return pMain;
    }
    private JPanel pTool() {
        if(pTool==null) {
            pTool = new JPanel(new java.awt.FlowLayout());
            pTool.add(getBTshowRegion());
            pTool.add(btClose());
        }
        return pTool;
    }
    
    private JList lRegions() {
        if(lRegions==null) {
            lRegions = new JList(lm());
        }
        return lRegions;
    }
    private DefaultListModel lm() {
        if(lm==null) {
            lm = new DefaultListModel();
            getlIF_Regions().forEach(ifr -> {
                Region reg = ifr.getRegion();
                String output = reg.getID()+" | "+reg.getName();
                lm.addElement(output);
            });
        }
        return lm;
    }
    
    private List<IFRegion> getlIF_Regions() {
        if(lIF_Regions==null) {
            lIF_Regions = new ArrayList();
            dsk.getRes().getLReg().stream().filter(pReg -> pReg.isKnown() && pReg.getStatus()!=Region.INVADED).forEach(pReg -> {
                IFRegion jif = new IFRegion(pReg,dsk);
                dsk.addJIF(jif);
                lIF_Regions.add(jif);
            });
        }
        return lIF_Regions;
    }
    
    private JButton getBTshowRegion() {
        if(btShowRegion==null) {
            btShowRegion = new JButton("Show");
            btShowRegion.addActionListener(getALshowRegion());
        }
        return btShowRegion;
    }
    private ActionListener getALshowRegion() {
        if(alShowRegion==null) {
            alShowRegion = (ActionEvent e) -> {
                showRegion();
            };
        }
        return alShowRegion;
    }
    private void showRegion() {
        try {
            String selectedVal = String.valueOf(lRegions.getSelectedValue());
            String id = selectedVal.substring(0,9);
            showIF(id);
        } catch(java.lang.ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "Select one of the available regions please!", "Select real region!", JOptionPane.OK_OPTION);
        }
    }
    public void showIF(String regID) {
        try {
            IFRegion jif = lIF_Regions.stream().filter(preg -> preg.getRegion().getID().equals(regID)).findAny().get();
            if(jif.isShowing()) {
                jif.toFront();
            } else {
                jif.show();
            }
        }catch(NoSuchElementException e) {
            JOptionPane.showConfirmDialog(null, regID+" wasn t found", "ERROR", JOptionPane.OK_OPTION);
        }
    }
    public IFRegion getIFReg(String id) {
        return lIF_Regions.stream().filter(pIF -> pIF.getRegion().getID().equals(id)).findAny().get();
    }
    
    public void refreshCBTea() {
        this.lIF_Regions.forEach(pIF -> {
            pIF.refreshCBTea();
        });
    }
    
    public ActivityGenerator getAG() {
        return aG;
    }
    
    Mainframe dsk = null;
    
    JPanel pMain = null;
    JPanel pTool = null;
    
    JList lRegions = null;
    DefaultListModel lm = null;
    
    JButton btShowRegion = null; 
    ActionListener alShowRegion = null;
    
    ActivityGenerator aG = null;
    
    List<IFRegion> lIF_Regions=null;
}
