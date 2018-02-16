/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.admin.IFRecapMssg;
import gui.admin.IFGoals;
import gui.admin.IFTuition;
import gui.admin.recapTabs.IFAcc;
import gui.Mainframe;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Die Axt
 */
public class MenuAdmin extends JMenu {
    public MenuAdmin(Mainframe pdsk) {
        super("Administration");
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    
    IFRecapMssg mssg;
    IFGoals goals;
    IFTuition tuition;
    IFAcc acc;
    
    JMenuItem miRecap;
    JMenuItem miTuition;
    JMenuItem guv;
    JMenuItem miGoals;
    
    private void run() {
        this.add(miGoals());
        this.add(miTit());
        this.add(miGuV());
        this.add(miRecap());
    }
    private JMenuItem miRecap() {
        if(miRecap==null) {
            miRecap = new JMenuItem("Show Recapitulation");
            miRecap.addActionListener((ActionEvent e) -> {
                showIFMssg();
            });
        }
        return miRecap;
    }
    private JMenuItem miGuV(){
        if(guv==null) {
            guv = new JMenuItem("GuV");
            guv.addActionListener((ActionEvent e) -> showIFStats());
        }
        return guv;
    }
    private JMenuItem miGoals() {
        if(miGoals==null)  {
            miGoals = new JMenuItem("Study Goals");
            miGoals.addActionListener((ActionEvent e) -> showIFAdmin());
        }
        return miGoals;
    }
    private JMenuItem miTit() {
        if(miTuition==null) {
            miTuition = new JMenuItem("Tituition");
            miTuition.addActionListener((ActionEvent e) -> showIFTit());
        }
        return miTuition;
    }
    
    public void showIFAdmin() {
        if(goals==null) {
            goals = new IFGoals(dsk);
            dsk.addJIF(goals);
            goals.show();
        } else {
            showIF(goals);
        }
    }
    public void showIFTit() {
        if(tuition==null || !tuition.isDisplayable()) {
            tuition = new IFTuition(dsk);
            dsk.addJIF(tuition);
            tuition.show();
        } else {
            showIF(tuition);
        }
    }
    
    public void showIFAcc() {
        if(getIFAcc().isShowing()) {
            acc.toFront();
        } else {
            acc.show();
        }
    }
    public IFAcc getIFAcc() {
        if(acc==null) {
            acc = new IFAcc(dsk);
            dsk.addJIF(acc);
        }
        return acc;
    }
    
    public void showIFStats() {
        showIFAcc();
    }
    public void showIFMssg() {
        if(mssg==null) {
            mssg = new IFRecapMssg(dsk);
            dsk.addJIF(mssg);
            mssg.show();
        } else {
            if(mssg.isShowing()) {
                mssg.toFront();
            } else {
                mssg.show();
            }
        }
    }
    
    private void showIF(JInternalFrame jif) {
        if(jif.isShowing()) {
            jif.toFront();
        } else {
            jif.show();
        }
    }
}
