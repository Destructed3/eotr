/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import root.SaveDat;

/**
 *
 * @author Die Axt
 */
public class IFSave extends JInternalFrame {
    public IFSave(Mainframe pdsk) {
        super();
        this.dsk = pdsk;
        run();
    }
    
    Mainframe dsk;
    SaveDat sd = new SaveDat();
    
    private void run() {
        this.setSize(400,400);
        this.setTitle("Save Game");
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new java.awt.GridLayout(5,1));
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.add(btSave("1"));
        this.add(btSave("2"));
        this.add(btSave("3"));
        this.add(btClose(this));
    }
    
    private JButton btSave(String datName) {
        JButton btSave = new JButton("Save "+datName);
        btSave.addActionListener(alSave(datName));
        return btSave;
    }
    private JButton btClose(JInternalFrame jif) {
        JButton btClose = new JButton("Close");
        btClose.addActionListener((ActionEvent e) -> {
            jif.hide();
        });
        return btClose;
    }
    private java.awt.event.ActionListener alSave(String datName) {
        java.awt.event.ActionListener al = (ActionEvent e) -> {
            try {
                sd.saveGame(datName, dsk);
                JOptionPane.showMessageDialog(null, "Game saved!", "Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Error while saving your game", "Saving Error", JOptionPane.OK_OPTION);
            }
        };
    return al;
    }
}
