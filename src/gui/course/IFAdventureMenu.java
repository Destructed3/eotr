/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import gui.Mainframe;
import javax.swing.JPanel;

/**
 *
 * @author Die Axt
 */
public class IFAdventureMenu extends root.IFTemplate {
    public IFAdventureMenu(Mainframe pdsk) {
        super("Available Adventures");
        this.dsk=pdsk;
        run();
    }
    
    private void run() {
        setIF(pMain(),pTool());
    }
    
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel();
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
    
    Mainframe dsk = null;
    
    JPanel pMain;
    JPanel pTool;
    
    
}
