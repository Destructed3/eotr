/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import generators.name.NameGenerator;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Die Axt
 */
public class NameGeneratorDemo extends JFrame {
    public NameGeneratorDemo() {
        super("MonsterNames");
        run();
    }
    
    private void run() {
        ng = new NameGenerator();
        JList l = new JList(lm());
        JPanel p = new JPanel(new java.awt.BorderLayout());
        JPanel subP = new JPanel(new java.awt.FlowLayout());
        subP.add(btRenew());
        subP.add(btClose());
        p.add(cb(), BorderLayout.NORTH);
        p.add(new JScrollPane(l), BorderLayout.CENTER);
        p.add(subP, BorderLayout.SOUTH);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(p);
    }
    private DefaultListModel lm() {
        lm = new DefaultListModel();
        setLM_Pers();
        return lm;
    }
    private JButton btRenew() {
        JButton bt = new JButton("New Names");
        bt.addActionListener((ActionEvent e) -> {
            decider();
        });
        return bt;
    }
    private JButton btClose() {
        JButton bt = new JButton("Close");
        bt.addActionListener((ActionEvent e) -> {
            new StartMenu().setVisible(true);
            this.dispose();
        });
        return bt;
    }
    
    private JComboBox cb() {        
        String[] feature = {"Persons","Monster","Rooms","Regions"};
        cb = new javax.swing.JComboBox(feature);        
        cb.addItemListener((ItemEvent e) -> {
            decider();
        });
        return cb;
    }
    
    private void decider() {
        if(cb.getSelectedItem()=="Persons") {
            setLM_Pers();
        } else if(cb.getSelectedItem()=="Monster") {
            setLM_Monster();
        } else if(cb.getSelectedItem()=="Rooms") {
            setLM_Rooms();
        } else {
            setLM_Regions();
        }
    }
    private void setLM_Pers() {
        lm.clear();
        for(int i=0;i<10;i++) {
            String output=ng.randomName(false);
            lm.addElement(output);
        }
    }
    private void setLM_Monster() {
        lm.clear();
        for(int i=0;i<10;i++) {
            String output=ng.randomName(true);
            lm.addElement(output);
        }
    }
    private void setLM_Rooms() {
        lm.clear();
        generators.name.RoomNameGenerator rng = new generators.name.RoomNameGenerator();
        for(int i=0;i<10;i++) {
            String output=rng.getRoomName();
            lm.addElement(output);
        }
    }
    private void setLM_Regions() {
        lm.clear();
        generators.name.RegionNameGenerator rng = new generators.name.RegionNameGenerator();
        for(int i=0;i<10;i++) {
            String output=rng.generateName()[0];
            lm.addElement(output);
        }
    }
    
    JComboBox cb;
    DefaultListModel lm;
    generators.name.NameGenerator ng;
    
}
