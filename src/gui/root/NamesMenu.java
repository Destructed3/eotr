/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import root.LoadDat;

/**
 *
 * @author Die Axt
 */
public class NamesMenu extends JFrame {
    
    public NamesMenu() {
        super();
        newNames();
    }
    
    //GUI Elements
    JPanel jpMain = new JPanel();    
    
    JLabel jlInfo = new JLabel("Type first syllable");
    JLabel jlInfo2 = new JLabel("Type later syllable");
    JTextField tf1 = new JTextField();
    JTextField tf2 = new JTextField(); 
    Vector <String> vecS1 = new Vector<String>();
    Vector <String> vecS2 = new Vector<String>();
    
    private void newNames() {
        this.setSize(400,400);
        setPInput();
        setPOutput();
        setPMain();
        this.setContentPane(jpMain);
    }
    
    private JPanel setPInput() {
        JPanel jpInput = new JPanel();
        jpInput.setLayout(new java.awt.GridLayout(4,2));
        jpInput.add(jlInfo);
        jpInput.add(tf1);
        jpInput.add(jlInfo2);
        jpInput.add(tf2);
        jpInput.add(setBtSave(tf1,"Save 1st syllable","1stSyllable.txt"));
        jpInput.add(setBtSave(tf2,"Save later syllable","syllable.txt")); 
        return jpInput;
    }
    private JPanel setPOutput() {
        JPanel jpOutput = new JPanel(new java.awt.GridLayout(2,2)); 
        JLabel inDat1 = new JLabel("1st Syllables:");
        JLabel inDat2 = new JLabel("Further Syllables:");
        new LoadDat().loadTxtIntoVec(vecS1,".\\Data\\NameGen\\1stSyllable.txt");
        JList lS1 = new JList(vecS1);
        JScrollPane sc1 = new JScrollPane(lS1);
        new LoadDat().loadTxtIntoVec(vecS2,".\\Data\\NameGen\\syllable.txt");
        JList lS2 = new JList(vecS2);
        JScrollPane sc2 = new JScrollPane(lS2);
        jpOutput.add(inDat1);
        jpOutput.add(inDat2);
        jpOutput.add(sc1);         
        jpOutput.add(sc2);
        return jpOutput;
    }
    private void setPMain() {
        jpMain.setLayout(new java.awt.BorderLayout());
        jpMain.add(setPInput(), java.awt.BorderLayout.NORTH);
        jpMain.add(setPOutput(), java.awt.BorderLayout.CENTER);        
        jpMain.add(setBtBack(), java.awt.BorderLayout.SOUTH); 
    }
    private JButton setBtSave(JTextField tf,String btTxt, String dat) {
        JButton btS1 = new JButton();
        btS1.setText(btTxt);
        btS1.setSize(100, 50);
        btS1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(tf, dat);
                renew();
            }
        });        
        return btS1;
    }
    private JButton setBtBack() {
        JButton btBack=new JButton();
        btBack.setBounds(20,20,20,20);
        btBack.setText("Back");
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                back();
            }
        });
        return btBack;
    }
    private void save(JTextField tf, String dat) {
        //Saves TextField entries into a *.txt
        try {
            String s=tf.getText();
            FileWriter fw = new FileWriter(dat,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s);
            bw.newLine();
            bw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error"+ex.getMessage());
        }
    }
    private void renew() {
        this.setVisible(false);
        new NamesMenu().setVisible(true);
    }
    private void back() {
        new StartMenu().setVisible(true);
        this.dispose();
    }
}
