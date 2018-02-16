/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.Mainframe;
import gui.inh.IFFormer;
import gui.inh.IFHire;
import gui.inh.IFStuMenu;
import gui.inh.IFTeaMenu;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Die Axt
 */ 
public class MenuInh extends JMenu {
    public MenuInh(Mainframe pdsk) {
        this.dsk=pdsk;
        setMenuInh();
    }
    
    Mainframe dsk;
    IFTeaMenu teaMenu;
    IFHire hireMenu;
    IFStuMenu stuMenu;
    IFFormer formerMenu;
    
    private void setMenuInh() {
        this.teaMenu = new IFTeaMenu(dsk);
        this.hireMenu = new IFHire(dsk);
        this.stuMenu = new IFStuMenu(dsk);
        this.formerMenu = new IFFormer(dsk);
        dsk.addJIF(teaMenu);
        dsk.addJIF(hireMenu);
        dsk.addJIF(stuMenu);
        dsk.addJIF(formerMenu);
        this.setText("Inhabitants");
        this.add(miOpenIF(teaMenu));
        this.add(miOpenIF(hireMenu));
        this.add(miOpenIF(stuMenu));
        this.add(miOpenIF(formerMenu));
    }
    private JMenuItem miOpenIF(JInternalFrame jif) {
        JMenuItem miOpen = new JMenuItem(jif.getTitle());
        miOpen.addActionListener((ActionEvent e) -> {
            if(jif.isShowing()) {
                jif.toFront();
            } else {
                jif.show();
            }
        });
        return miOpen;
    }
    public void showTM() {
        if(teaMenu.isShowing()) {
                teaMenu.toFront();
            } else {
                teaMenu.show();
            }
    }
    public IFTeaMenu getTea() {
        return teaMenu;
    }
    public void showHM() {
        if(hireMenu.isShowing()) {
                hireMenu.toFront();
            } else {
                hireMenu.show();
            }
    }
    public IFHire getTfH() {
        return hireMenu;
    }
    public void showSM() {
        if(stuMenu.isShowing()) {
                stuMenu.toFront();
            } else {
                stuMenu.show();
            }
    }
    public IFStuMenu getStu() {
        return stuMenu;
    }
    public void showIFStu(int index) {
        stuMenu.showIFStu(index);
    }
    public IFFormer getFormer() {
        return formerMenu;
    }
    public void refreshTT() {
        teaMenu.refreshTT();
    }
    public void refreshTT(int tNr) {
        teaMenu.refreshTT(tNr);
    }
}
