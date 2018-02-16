/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Die Axt
 */
public class StartMenu extends JDialog {
    NamesMenu nn = new NamesMenu();
    
    public StartMenu() {
        super();
        start();
    }
    private void start() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Esperance of the Realm");
        this.setSize(400,400);
        this.setContentPane(setMain());
    }
    private JPanel setMain() {
        JPanel p = new JPanel(new java.awt.BorderLayout());
        p.add(new JPanel(),BorderLayout.NORTH);
        p.add(new JPanel(),BorderLayout.EAST);
        p.add(new JPanel(),BorderLayout.SOUTH);
        p.add(new JPanel(),BorderLayout.WEST);
        p.add(setPanel(),BorderLayout.CENTER);
        return p;
    }
    private JPanel setPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new java.awt.GridLayout(6,1));
        jp.add(btStart("New Game"));
        jp.add(btLoad());
        jp.add(btNames());
        jp.add(btCheckNames());
        jp.add(btFeats());
        jp.add(btQuit());
        return jp;
    }
    private JButton btStart(String s) {
        JButton jb = new JButton(s);
        jb.setSize(100,50);
        jb.addActionListener(listenerStart());
        return jb;
    }
    private JButton btLoad() {
        JButton bt = new JButton("Load Game");
        bt.addActionListener((ActionEvent e) -> {
            new LoadMenu().setVisible(true);
            closeWindow();
        });
        return bt;
    }
    private JButton btNames() {
        JButton jb = new JButton("Manage Syllables");
        jb.addActionListener((ActionEvent e) -> {
            nn.setVisible(true);
            closeWindow();
        });
        return jb;
    }
    private JButton btCheckNames() {
        JButton jb = new JButton("Check Names");
        jb.addActionListener((ActionEvent e) -> {
            new NameGeneratorDemo().setVisible(true);
            closeWindow();
        });
        return jb;
    }
    private JButton btFeats() {
        JButton bt = new JButton("Manage Feats");
        bt.addActionListener((ActionEvent e) -> {
            new Feature_Managing().setVisible(true);
            closeWindow();
        });
        return bt;
    }
    private JButton btQuit() {
        JButton jb = new JButton("Quit");
        jb.addActionListener((ActionEvent e) -> {
            int mOK=JOptionPane.showConfirmDialog(null, "Wirklich beenden?", "", JOptionPane.YES_NO_OPTION);
            if(mOK == JOptionPane.YES_NO_OPTION) {
                System.exit(0);
            }
        });
        return jb;
    }
    private ActionListener listenerStart() {
        ActionListener l=(ActionEvent e) -> {
            openCC();
            closeWindow();
        };
                return l;
    }
    private void openCC() {
        new CharCreation().setVisible(true);        
    }
    private void closeWindow() {
        this.setVisible(false);
        this.dispose();
    }


    
}
