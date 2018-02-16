/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.Mainframe;
import gui.region.IFRegionMenu;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Die Axt
 */
public class MenuRegion extends JMenu {
    public MenuRegion(Mainframe pdsk) {
        super("Region");
        this.dsk=pdsk;
        run();
    }
    private void run() {
        this.add(miRegion());
    }
    
    private JMenuItem miRegion() {
        if(miRegion==null) {
            miRegion=new JMenuItem("Known Regions");
            miRegion.addActionListener((ActionEvent e) -> {
                showIFRegionMenu();
            });
        }
        return miRegion;
    }
    
    public IFRegionMenu getIFRegionMenu() {
        if(ifRM==null) {
            ifRM = new IFRegionMenu(dsk);
            dsk.addJIF(ifRM);
        }
        return ifRM;
    }
    public void showIFRegionMenu()  {
        if(getIFRegionMenu().isShowing()) {
            ifRM.toFront();
        } else {
            ifRM.show();
        }
    }
    
    Mainframe dsk = null;
    
    JMenuItem miRegion;
    
    IFRegionMenu ifRM;
}
