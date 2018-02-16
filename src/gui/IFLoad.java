/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

/**
 *
 * @author Die Axt
 */
public class IFLoad extends JInternalFrame {
    public IFLoad(Mainframe dsk) {
        run(dsk);
    }
    private void run(Mainframe pdsk) {
        this.dsk=pdsk;
        this.setTitle("Load Game");
        this.setLayout(new java.awt.GridLayout(6,1));
        this.setSize(400,400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.add(btLoadNY());
        this.add(btLoadLY());
        for(int i=1;i<4;i++) {
            this.add(setBTLoad(String.valueOf(i)));
        }
        setBTClose();
        this.add(close);
    }
    private Mainframe dsk;
    private JButton close;
    ActionListener alLoad;
    
    private JButton btLoadNY() {
        String label = getBTLabel("NewYear");
        JButton load = new JButton("<html>Beginning of last played year<br>"+label+"</html>");
        if(!label.equals("No save here")) {
            load.addActionListener(alLoad("NewYear"));
        }
        return load;
    }
    private JButton btLoadLY() {
        String label = getBTLabel("LastYear");
        JButton load = new JButton("<html>End of last played year<br>"+label+"</html>");
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
    private void setBTClose() {
        close = new JButton("Close");
        close.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
    }   
    
    private ActionListener alLoad(String i) {
        ActionListener al = (ActionEvent e) -> {
            new Mainframe(i).setVisible(true);
            dsk.dispose();
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
    
    root.LoadDat ld = null;
    
}
