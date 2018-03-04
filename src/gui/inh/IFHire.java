/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import generators.TeaGen;
import gui.Mainframe;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Die Axt
 */
public class IFHire extends root.IFTemplate {
    public IFHire(Mainframe pdsk) {
        super("Available Heroes",false);
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    JPanel main;
    JPanel tool;
    IFTeacher[] ifTfH = new IFTeacher[12];
    JButton[] btTfH = new JButton[12];
    
    
    private void run() {
        setIF(setPMain(),setPTool());
    }
    private JPanel setPMain() {
        main = new JPanel(new java.awt.GridLayout(4,3));
        for(int i=0;i<dsk.getData().teacherForHire.length;i++) {
            if(dsk.getData().teacherForHire[i]!=null) {
                ifTfH[i]=new IFTeacher(dsk.getData().teacherForHire[i],dsk);
                btTfH[i]=btShow(ifTfH[i]);
                dsk.addJIF(ifTfH[i]);
                main.add(btTfH[i]);
            } else {
                main.add(new JPanel());
            }
        }
        return main;
    }
    private JPanel setPTool() {
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(btClose());
        return tool;
    }
    private JButton btShow(IFTeacher ift) {
        String name = "<html><center>"
                +ift.getTea().getName()
                + "<br>"+ift.getTea().getDeeds().size()+" Deeds<br>"
                +ift.getTea().getCost()*6+"g<br>"
                +ift.getTea().getCost()+"g/Month"
                + "</center></html>";
        JButton bt = new JButton(name);
        bt.addActionListener((ActionEvent e) -> {
            if(ift.isShowing()) {
                ift.toFront();
            } else {
                ift.show();
            }
        });
        return bt;
    }
    public void fillTfH() {
        for(int i=0;i<dsk.getData().teacherForHire.length;i++) {
            if(dsk.getData().teacherForHire[i]==null) {
                dsk.getData().teacherForHire[i] = new TeaGen(false,true,dsk);
                dsk.getData().teacherForHire[i].setTeaFHNr(i);
                ifTfH[i] = new IFTeacher(dsk.getData().teacherForHire[i],dsk);
                main.add(btShow(ifTfH[i]));
            } else {
                if(dsk.getData().teacherForHire[i].getAv()>0) {
                    dsk.getData().teacherForHire[i].setAv(dsk.getData().teacherForHire[i].getAv()-1);
                } else {
                    dsk.getData().teacherForHire[i]=null;
                }
            } 
            
        }
    }
    public void removeTfH(int index) {
        main.remove(btTfH[index]);
        main.add(new JPanel());
        ifTfH[index]=null;
        btTfH[index]=null;
    }
}
