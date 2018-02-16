/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import gui.Mainframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import resources.Inhabitants.InhTea;

/**
 *
 * @author Die Axt
 */
public class LoadMenu extends JDialog {
    public LoadMenu() {  
        run();
    }
    private void run() {
        this.setTitle("Load Game");
        this.setLayout(new java.awt.GridLayout(7,1));
        this.setSize(400,400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.add(btLoadLY());
        this.add(btLoadNY());
        for(int i=0;i<3;i++) {
            this.add(setBTLoad(String.valueOf(i+1)));
        }
        setBTClose(this);
        this.add(close);
    }
    private JButton close;
    ActionListener alSave;
    ActionListener alLoad;
    
    private JButton btLoadNY() {
        String label = getBTLabel("NewYear");
        JButton load = new JButton("<html><center>Beginning of last played year<br>"+label+"</center></html>");
        load.setHorizontalAlignment(JLabel.CENTER);
        if(!label.equals("No save here")) {
            load.addActionListener(alLoad("NewYear"));
        }
        return load;
    }
    private JButton btLoadLY() {
        String label = getBTLabel("LastYear");
        JButton load = new JButton("<html><center>End of last played year<br>"+label+"</center></html>");
        if(!label.equals("No save here")) {
            load.addActionListener(alLoad("LastYear"));
        }
        return load;
    }
    private JButton setBTLoad(String i) {
        String label = getBTLabel(i);
        JButton load = new JButton(label);
        if(!label.equals("No save here")) {
            load.addActionListener(alLoad(i));
        }
        return load;
    }
    private void setBTClose(JDialog jd) {
        close = new JButton("Close");
        close.addActionListener((ActionEvent e) -> {
            new StartMenu().setVisible(true);
            jd.dispose();
        });
    }   
    
    private ActionListener alLoad(String i) {
        ActionListener al = (ActionEvent e) -> {
            try {
                new Mainframe(i).setVisible(true);
                this.dispose();
            }catch(Exception ex) {
                JOptionPane.showConfirmDialog(null, "Save corrupted", "Save corrupted", JOptionPane.OK_OPTION);
            }
                
        };
        return al;
    }
    private String getBTLabel(String save) {
        return getLD().getLoadLabel(save);
    }
    private root.LoadDat getLD() {
        if(ld==null) {
            ld = new root.LoadDat();
        }
        return ld;
    }
    
    root.LoadDat ld;
}
