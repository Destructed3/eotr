/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.Mainframe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Die Axt
 */
public class EndYear extends JMenu {
    public EndYear(Mainframe pdsk) {
        super("End Year");
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    root.SaveDat sd = new root.SaveDat();
    ActionListener endYear;
    
    private void run() {
        this.add(mi());
    }
    
    private JMenuItem mi() {
        JMenuItem mi = new JMenuItem("End IT!");
        mi.addActionListener(alEY());
        return mi;
    }
    private ActionListener alEY() {
        endYear = (ActionEvent e) -> {
            int mOk = JOptionPane.showConfirmDialog(null, "Do you really want to end the year?", "End Year", JOptionPane.YES_NO_OPTION);
            if(mOk==JOptionPane.YES_NO_OPTION) {
                endYear.EndYear ey = new endYear.EndYear(dsk);
                ey.run();
            }
        };
        return endYear;
    }
}
