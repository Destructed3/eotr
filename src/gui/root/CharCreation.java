/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import gui.Mainframe;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;
import resources.Inhabitants.InhTea;

/**
 *
 * @author Die Axt
 */
public class CharCreation extends JFrame {
    public CharCreation() {
       super();
       run();
    }  
    JPanel panel = new JPanel();
    JTextField tf = new JTextField(new generators.name.NameGenerator().randomName(false));
    JRadioButton rbClassW;
    JRadioButton rbClassT;
    JRadioButton rbClassB;
    JRadioButton rbClassS;
    InhTea character;
    private void run() {
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setTitle("Character Creation");
        this.setSize(500,500);        
        JLabel lb = new JLabel("Please insert your name:");   
        JLabel lb2 = new JLabel("Chose your Class!");
        JLabel lb0 = new JLabel("");
        panel.setLayout(new java.awt.GridLayout(10,1));
        panel.add(lb);
        panel.add(tf);
        panel.add(lb2);
        panel.add(setBtClass());
        panel.add(lb0);
        panel.add(setBtPanel());
        this.getContentPane().add(panel);
    }    
    private JPanel setBtClass() {
        JPanel jp = new JPanel();
        ButtonGroup rg = new ButtonGroup();
        rbClassW = new JRadioButton("Strong Warrior", true);
        rbClassW.setMnemonic(KeyEvent.VK_W);
        rg.add(rbClassW);
        rbClassT = new JRadioButton("Cunning Thief", false);
        rbClassT.setMnemonic(java.awt.event.KeyEvent.VK_T);
        rg.add(rbClassT);
        rbClassB = new JRadioButton("Folksy Bard", false);
        rbClassB.setMnemonic(java.awt.event.KeyEvent.VK_B);
        rg.add(rbClassB);
        rbClassS = new JRadioButton("Mighty Sorcerer", false);
        rbClassS.setMnemonic(java.awt.event.KeyEvent.VK_S);
        rg.add(rbClassS);
        jp.setLayout(new java.awt.GridLayout(2,2));
        jp.add(rbClassW);
        jp.add(rbClassT);
        jp.add(rbClassB);
        jp.add(rbClassS);
        return jp;
    }
    private JPanel setBtPanel() {
        JPanel jp = new JPanel();
        jp.setLayout(new java.awt.GridLayout(1,2));
        jp.add(btCreate());
        jp.add(btAbort());
        return jp;
    }
    private JButton btCreate() {
        JButton bt = new JButton("CREATE!!!");
        bt.addActionListener((ActionEvent e) -> {
            setChar();
            startGame();
        });
        return bt;
    }
    private JButton btAbort() {
        JButton jb = new JButton("Abort");
        jb.addActionListener((ActionEvent e) -> {
            abort();
        });
        return jb;
    }
    private void startGame() {
        new Mainframe(character).setVisible(true);
        this.dispose();        
    }
    private void abort() {        
        new StartMenu().setVisible(true);
        this.dispose();
    }
    private void setChar() {
        Random r = new Random();
        String s = tf.getText();
        int i = 50+r.nextInt(49);
        double teach = i;
        teach=teach/100;
        if(rbClassW.isSelected()==true) {
            character = new InhTea(true,false);
            character.setName(s);
            character.setAttribute(0, 15+r.nextInt(30));
            character.setAttribute(1, 7+r.nextInt(17));
            character.setAttribute(2, 13+r.nextInt(18));
            character.setAttribute(3, 5+r.nextInt(15));
            character.setFame(9+r.nextInt(16));
            character.setTeaching(teach);
            character.setNumber(10000);
        }
        if(rbClassT.isSelected()==true) {
            character = new InhTea(true,false);
            character.setName(s);
            character.setAttribute(0, 10+r.nextInt(18));
            character.setAttribute(1, 14+r.nextInt(29));
            character.setAttribute(2, 10+r.nextInt(18));
            character.setAttribute(3, 6+r.nextInt(15));
            character.setFame(9+r.nextInt(16));
            character.setTeaching(teach);
            character.setNumber(10000);
        }
        if(rbClassB.isSelected()==true) {
            character = new InhTea(true,false);
            character.setName(s);
            character.setAttribute(0, 7+r.nextInt(17));
            character.setAttribute(1, 8+r.nextInt(18));
            character.setAttribute(2, 14+r.nextInt(27));
            character.setAttribute(3, 11+r.nextInt(18));
            character.setFame(9+r.nextInt(16));
            character.setTeaching(teach);
            character.setNumber(10000);
        }
        if(rbClassS.isSelected()==true) {
            character = new InhTea(true,false);
            character.setName(s);
            character.setAttribute(0, 6+r.nextInt(14));
            character.setAttribute(1, 13+r.nextInt(22));
            character.setAttribute(2, 6+r.nextInt(14));
            character.setAttribute(3, 30+r.nextInt(26));
            character.setFame(9+r.nextInt(16));
            character.setTeaching(teach);
            character.setNumber(10000);
        }
    }
}
