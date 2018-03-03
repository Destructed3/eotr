/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import gui.Mainframe;
import root.IFTemplate;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import resources.Inhabitants.InhTea;

/**
 *
 * @author Die Axt
 */
public class IFTeaMenu extends IFTemplate {
    public IFTeaMenu(Mainframe pdsk) {
        super("Teacher",false);
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    JList list = null;
    DefaultListModel lm = null;
    List<IFTeacher> ifTea = new ArrayList();
    
    private void run() {
        createIF_Tea();
        setIF(mainPanel(),toolPanel());
    }
    private JPanel mainPanel() {
        lm = new DefaultListModel();
        dsk.getRes().lTea.stream().forEach(tea -> {
            String output = tea.getNumber()+" | "+tea.getName()+" | Physical: "+tea.getAttribute(0)+" | Mental: "+tea.getAttribute(1)
                    +" | Social: "+tea.getAttribute(2)+" | Magical: "
                    +tea.getAttribute(3)+" | Teaching: "+tea.getTeaching();
            lm.addElement(output);
        });
        list = new JList(lm);   
        list.setSelectedIndex(0);
        JPanel p = new JPanel(new java.awt.GridLayout(1,1));
        p.add(new javax.swing.JScrollPane(list));
        return p;
    }
    private JPanel toolPanel() {
        JPanel p = new JPanel(new java.awt.FlowLayout());
        p.add(btTea());
        p.add(btClose());
        return p;
    }
    private JButton btTea() {
        JButton bt = new JButton("Display Teacher");
        bt.addActionListener((ActionEvent e) -> {
            int index = list.getSelectedIndex();
            IFTeacher ift = ifTea.get(index);
            if(ift.isShowing()) {
                ift.toFront();
            } else {
                ift.show();
        }
        });
        return bt;
    }
    private void createIF_Tea() {
        dsk.getRes().lTea.stream().forEach(tea -> {
            IFTeacher ift = new IFTeacher(tea, dsk);
            dsk.addJIF(ift);
            ifTea.add(ift);
        });
    }
    public void addTea(InhTea tea) {
        IFTeacher jif = new IFTeacher(tea, dsk);
        dsk.addJIF(jif);
        ifTea.add(jif);
        String output = tea.getNumber()+" | "+tea.getName()+" | Physical: "+tea.getAttribute(0)+" | Mental: "+tea.getAttribute(1)
                +" | Social: "+tea.getAttribute(2)+" | Magical: "
                +tea.getAttribute(3)+" | Teaching: "+tea.getTeaching();
        lm.addElement(output);
    }
    public void showTea(int teaNr) {
        IFTeacher jif = ifTea.stream().filter(ift -> ift.getTea().getNumber()==teaNr).findFirst().get();
        if(jif.isShowing()) {
            jif.toFront();
        } else {
            jif.show();
        }
    }
    public void refreshTT() {
        ifTea.forEach(pifTea -> {
            pifTea.refreshTT();
        });
    }
    public void refreshTT(int tNr) {
        ifTea.stream().filter(pJif -> pJif.getTea().getNumber()==tNr).findAny().get().refreshTT();
    }
}
