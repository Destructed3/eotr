/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import resources.Inhabitants.Inhabitants;

/**
 *
 * @author Die Axt
 */
public class IFJournal extends root.IFTemplate {
    public IFJournal(Inhabitants pInh) {
        super(pInh.getName()+"'s Journal");
        this.inh=pInh;
        run();
    }
    
    private void run() {
        setIF(pMain(),pTool());
    }
    
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.GridLayout(1, 0));
            pMain.add(new JScrollPane(entries()));
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
    
    private JList entries() {
        if(entries==null) {
            entries = new JList(lm());
        }
        return entries;
    }
    private DefaultListModel lm() {
        if(lm==null) {
            lm = new DefaultListModel();
            fillLM();
        }
        return lm;
    }
    private void fillLM() {
        if(inh.getJournal().isEmpty()) {
            String output = "I have a pretty boring live";
            lm.addElement(output);
        } else {
            inh.getJournal().forEach(je -> {
                lm.addElement(je);
            });
        }
    }
    
    Inhabitants inh;
    
    JPanel pMain;
    JPanel pTool;
    
    JList entries;
    DefaultListModel lm;
}
