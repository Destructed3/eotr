/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin.recapTabs;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Die Axt
 */
public class IFAcc extends root.IFTemplate {
    
    public IFAcc(Mainframe pdsk) {
        super("super");
        this.dsk=pdsk;
        run();
    }
    
    private void run() {
        this.setSize(500,500);
        this.setContentPane(setMain());
    }
    
    private JPanel setMain() {
        if(main==null) {
            main = new JPanel(new java.awt.BorderLayout());
            main.add(stuff(),BorderLayout.CENTER);
            main.add(btClose(),BorderLayout.SOUTH);
        }
        return main;
    }
    private JTabbedPane stuff() {
        if((stuff==null)) {
            stuff = new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
            getLAcc().forEach(panel -> {
                String tab = "Year "+panel.getAccounting().getYear();
                stuff.addTab(tab, panel);
            });
        }
        return stuff;
    }
    
    private List<P_Acc> getLAcc() {
        if(lAcc==null) {
            lAcc = new ArrayList();
            addTo_LAcc();
        }
        return lAcc;
    }
    private void addTo_LAcc() {
        dsk.getData().lAccounting.forEach(acc -> {
            P_Acc p = new P_Acc(acc,dsk);
            getLAcc().add(p);
        });
    }
    
    public P_Acc getThisYear() {
        return lAcc.stream().filter(jif -> jif.getAccounting().getYear()==dsk.getData().year).findAny().get();
    }
    
    Mainframe dsk;
    
    JPanel main;
    
    JTabbedPane stuff;
    
    List<P_Acc> lAcc;
    
}
