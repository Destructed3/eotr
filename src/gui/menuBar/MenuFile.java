/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.IFLoad;
import gui.IFSave;
import gui.Mainframe;
import gui.root.CharCreation;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 
 * @author Die Axt
 */
public class MenuFile extends JMenu {
    public MenuFile(Mainframe pdsk) {
        super();
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    JMenuItem miNewG = null;
    JMenuItem miLoadG = null;
    JMenuItem miSaveG = null;
    JMenuItem miAllLists = null;
    JMenuItem miAllTables = null;
    JMenuItem miQuit = null;
    JInternalFrame showLists = null;
    JInternalFrame showTables = null;
    
    private void run() {
        this.setText("File");
        this.add(setNewG());
        this.add(setLoadG());
        this.add(setSaveG());
        this.add(setAllLists());
        this.add(setAllTables());
        this.add(setQuit());
    }
    private JMenuItem setNewG() {
        miNewG = new JMenuItem("New Game");
        miNewG.addActionListener((ActionEvent e) -> {
            dsk.dispose();
            new CharCreation().setVisible(true);
        });
        return miNewG;
    }
    private JMenuItem setLoadG() {
        miLoadG = new JMenuItem("Load Game");
        miLoadG.addActionListener((ActionEvent e) -> {
            JInternalFrame jif = new IFLoad(dsk);
            dsk.addJIF(jif);
            jif.show();
        });
        return miLoadG;
    }
    private JMenuItem setSaveG() {
        miSaveG = new JMenuItem("Save Game");
        miSaveG.addActionListener((ActionEvent e) -> {
            JInternalFrame jif = new IFSave(dsk);
            dsk.addJIF(jif);
            jif.show();
        });
        return miSaveG;
    }
    private JMenuItem setAllLists() {
        if(miAllLists==null) {
            miAllLists = new JMenuItem("Show All Lists");
            miAllLists.addActionListener((ActionEvent e) -> {
                if(showLists==null) {
                    showLists = new gui.root.ShowLists(dsk);
                    dsk.addJIF(showLists);
                    showLists.show();
                } else {
                    if(showLists.isShowing()) {
                        showLists.toFront();
                    } else {
                        showLists.show();
                    }
                }
            });
        }
        return miAllLists;
    }
    private JMenuItem setAllTables() {
        if(miAllTables==null) {
            miAllTables = new JMenuItem("Show Tables");
            miAllTables.addActionListener((ActionEvent e) -> {
                setIFTable();
            });
        }
        return miAllTables;
    }
    private JMenuItem setQuit() {
        miQuit = new JMenuItem("Quit Game");
        miQuit.addActionListener((ActionEvent e) -> {
            int mOK=JOptionPane.showConfirmDialog(null, "You really wanna go?", "", JOptionPane.YES_NO_OPTION);
            if(mOK == JOptionPane.YES_NO_OPTION) {
                System.exit(0);
            }
        });
        return miQuit;
    }
    
    private JInternalFrame setIFTable() {
        if(showTables==null) {
            showTables = new gui.root.ShowTT(dsk);
            dsk.addJIF(showTables);
            showTables.show();
        } else {
            getIFTable();
        }
        return showTables;
    }
    public void getIFTable() {
        if(showTables.isShowing()) {
            showTables.toFront();
        } else {
            showTables.show();
        }
    }
            
}
