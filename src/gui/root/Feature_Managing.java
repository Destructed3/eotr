/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.root;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import resources.FeatsBackground;
import resources.FeatsDeeds;
import root.LoadDat;
import root.SaveDat;

/**
 *
 * @author Die Axt
 */
public class Feature_Managing extends JFrame {
    public Feature_Managing() {
        super("Create/Delete Features!");  
        run();
    }
    
    JPanel pTop;
    JPanel pMain;
    JPanel pTool;
    JPanel pLeft;
    JPanel pRight;
    JTextField name;
    JTextArea desc;
    JTextField phy;
    JTextField men;
    JTextField soc;
    JTextField mag;
    JTextField other;
    JTextField learn;
    JLabel lother;
    JLabel index;
    JComboBox cb;
    JList list;
    List<resources.FeatsBackground> bgList = new ArrayList();
    List<FeatsDeeds> dList = new ArrayList();
    DefaultListModel lm;
    ListSelectionModel lsm;
    
    private void run() {  
        setFeatL();
        setPTop();
        setPMain();
        setPTool();
        setFrame();
    }
    private void setPTop() {     
        setCB();
        pTop = new JPanel(new java.awt.FlowLayout());
        pTop.add(new javax.swing.JLabel("Select Itemtype"));
        pTop.add(cb);
    }
    private void setPMain() {
        setList();
        setInOutp();
        getBG();
        pMain = new JPanel(new java.awt.GridLayout(1,2));
        pMain.add(pLeft);
        pMain.add(pRight);
        
    }
    private void setPTool() {
        index=new JLabel("Index: "+list.getSelectedIndex());
        pTool = new JPanel(new java.awt.FlowLayout());
        pTool.add(index);
        pTool.add(btSave());
        pTool.add(btDelete());
        pTool.add(btClear());
        pTool.add(btClose());
    }    
    private void setCB() {        
        String[] feature = {"Backgrounds","Deeds"};
        cb = new javax.swing.JComboBox(feature);        
        cb.addItemListener((ItemEvent e) -> {
            if(cb.getSelectedItem()=="Backgrounds") {
                getBG();
            } else {
                getD();
            }
        });
    }
    private void setList() {
        lm = new DefaultListModel();
        list = new JList(lm);
        list.setSelectedIndex(0);
        lsm = list.getSelectionModel();
        lsm.addListSelectionListener((ListSelectionEvent e) -> {
            if(list.getSelectedIndex()>=0) {
                int i=list.getSelectedIndex();
                if(index!=null) {
                    index.setText("Index: "+i);                        
                    }
                if(i==0) {                    
                    name.setText("");
                    desc.setText("");
                    phy.setText("");
                    men.setText("");
                    soc.setText("");
                    mag.setText("");
                    other.setText("");
                    learn.setText("");
                } else if(cb.getSelectedItem()=="Backgrounds" && !bgList.isEmpty()) {
                    FeatsBackground bg = bgList.get(i-1);
                    name.setText(bg.getName());
                    desc.setText(bg.getDescription());
                    phy.setText(bg.getAttribute(0)+"");
                    men.setText(bg.getAttribute(1)+"");
                    soc.setText(bg.getAttribute(2)+"");
                    mag.setText(bg.getAttribute(3)+"");
                    other.setText(bg.getGold()+"");
                    learn.setText(bg.getLearn()+"");
                } else if(cb.getSelectedItem()=="Deeds" && !dList.isEmpty()){
                    FeatsDeeds de = dList.get(i-1);
                    name.setText(de.getName());
                    desc.setText(de.getDescription());
                    phy.setText(de.getAttribute(0)+"");
                    men.setText(de.getAttribute(1)+"");
                    soc.setText(de.getAttribute(2)+"");
                    mag.setText(de.getAttribute(3)+"");
                    other.setText(de.getFame()+"");
                    learn.setText(de.getLearn()+"");
                }
            }
        });
        pLeft = new JPanel(new java.awt.GridLayout());
        pLeft.add(new javax.swing.JScrollPane(list));
    }
    private void setInOutp() {
        name = new JTextField();
        desc = new JTextArea();
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        phy = new JTextField();
        men = new JTextField();
        soc = new JTextField();
        mag = new JTextField();
        other = new JTextField();
        learn = new JTextField();
        lother = new JLabel("");
        pRight = new JPanel(new java.awt.BorderLayout());
        JPanel left =new JPanel(new java.awt.GridLayout(8,1));
        JPanel main = new JPanel(new java.awt.GridLayout(8,1));
        left.add(new JLabel("Name"));
        main.add(name);
        left.add(new JLabel("Description"));
        main.add(desc);
        left.add(new JLabel("Physical"));
        main.add(phy);
        left.add(new JLabel("Mental"));
        main.add(men);
        left.add(new JLabel("Social"));
        main.add(soc);
        left.add(new JLabel("Magical"));
        main.add(mag);
        left.add(lother);
        main.add(other);
        left.add(new JLabel("Learn"));
        main.add(learn);
        pRight.add(left, java.awt.BorderLayout.WEST);
        pRight.add(main, BorderLayout.CENTER);
    }
    private void setFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800,500);
        this.setLayout(new java.awt.BorderLayout());
        this.add(pTop, java.awt.BorderLayout.NORTH);
        this.add(pMain, java.awt.BorderLayout.CENTER);
        this.add(pTool, java.awt.BorderLayout.SOUTH);
    }
    private void setFeatL() {
        try {
            bgList = (List<resources.FeatsBackground>) new LoadDat().getObject1(".\\Data\\bgr.fe");
        } catch (IOException ex) { 
        }        
        
        try {
            dList = (List<FeatsDeeds>) new LoadDat().getObject1(".\\Data\\deeds.fe");
        } catch (IOException ex) { 
        }
    }
    private void getBG() {
        lm.clear();
        lm.addElement("New");        
        list.setSelectedIndex(0);
        if(!bgList.isEmpty()) {
            bgList.stream().forEach(fb -> {
                String output = fb.getNr()+"| "+fb.getName()+" | Phy: "+fb.getAttribute(0)+" | Men: "
                        +fb.getAttribute(1)+" | Soc: "+fb.getAttribute(2)+" | Mag: "+fb.getAttribute(3)
                        +" | Gold: "+fb.getGold()+" | Learn: "+fb.getLearn();
                lm.addElement(output);
            });
        }
        lother.setText("Gold");
    }
    private void getD() {
        lm.clear();
        lm.addElement("New");
        if(!dList.isEmpty()) {
            dList.stream().forEach(de -> {
                String output = de.getNr()+"| "+de.getName()+" | Phy: "+de.getAttribute(0)+" | Men: "+de.getAttribute(1)
                        +" | Soc: "+de.getAttribute(2)+" | Mag: "+de.getAttribute(3)
                        +" | Fame: "+de.getFame()+" | Learn: "+de.getLearn();            
                lm.addElement(output);  
            });
        }
        lother.setText("Fame");
        list.setSelectedIndex(0);
    }
    private boolean bgNr(int nr) {
        return bgList.stream().anyMatch(bg -> bg.getNr()==nr);
    }
    private boolean dNr(int nr) {
        return dList.stream().anyMatch(d -> d.getNr()==nr);
    }
    private JButton btClose() {
        JButton bt = new JButton("Close");
        bt.addActionListener((ActionEvent e) -> {
            this.dispose();
            new StartMenu().setVisible(true);
        });
        return bt;
    }
    private JButton btClear() {
        JButton bt = new JButton("Clear");
        bt.addActionListener((ActionEvent e) -> {
            list.setSelectedIndex(0);
            name.setText("");
            desc.setText("");
            phy.setText("");
            men.setText("");
            soc.setText("");
            mag.setText("");
            other.setText("");
            learn.setText("");
        });
        return bt;
    }
    private JButton btSave() {
        JButton bt = new JButton("Save");
        bt.addActionListener((ActionEvent e) -> {
            int i = list.getSelectedIndex();
            int mOK=JOptionPane.showConfirmDialog(null, "Want to save new Feat?", "", JOptionPane.YES_NO_OPTION);
            if(mOK == JOptionPane.YES_NO_OPTION) {
                if(cb.getSelectedIndex()==0 && i==0) {
                    String newName = name.getText();
                    String newDesc = desc.getText();
                    int[] newAttr = {Integer.parseInt(phy.getText()), Integer.parseInt(men.getText()),
                    Integer.parseInt(soc.getText()),Integer.parseInt(mag.getText())};
                    int newGold = Integer.parseInt(other.getText());
                    double newLearn = Double.parseDouble(learn.getText());
                    int[] newInter = {};
                    int nr = 1;
                    while(bgNr(nr)) {
                        nr++;
                    }
                    bgList.add(new FeatsBackground(newName,newDesc,newAttr,newGold,newLearn,newInter,nr));
                    new SaveDat().saveDat(".\\Data\\bgr.fe", bgList);
                    FeatsBackground fb = bgList.get(bgList.size()-1);
                    String output = fb.getNr()+"| "+fb.getName()+" | Phy: "+fb.getAttribute(0)+" | Men: "+fb.getAttribute(1)
                            +" | Soc: "+fb.getAttribute(2)+" | Mag: "+fb.getAttribute(3)
                            +" | Gold: "+fb.getGold()+" | Learn: "+fb.getLearn();
                    lm.addElement(output);
                    list.setSelectedIndex(lm.size()-1);
                } else if (cb.getSelectedIndex()==0 && i!=0) {
                    int nr = bgList.get(i-1).getNr();
                    bgList.remove(list.getSelectedIndex()-1);
                    String newName = name.getText();
                    String newDesc = desc.getText();
                    int[] newAttr = {Integer.parseInt(phy.getText()),Integer.parseInt(men.getText()),
                        Integer.parseInt(soc.getText()),Integer.parseInt(mag.getText())};
                    int newGold = Integer.parseInt(other.getText());
                    double newLearn = Double.parseDouble(learn.getText());
                    int[] newInter = {};
                    bgList.add(i-1, new FeatsBackground(newName,newDesc,newAttr,newGold,newLearn,newInter,nr));
                    new SaveDat().saveDat(".\\Data\\bgr.fe", bgList);
                    FeatsBackground fb = bgList.get(i-1);
                    String output = fb.getNr()+"| "+fb.getName()+" | Phy: "+fb.getAttribute(0)+" | Men: "+fb.getAttribute(1)
                            +" | Soc: "+fb.getAttribute(2)+" | Mag: "+fb.getAttribute(3)
                            +" | Gold: "+fb.getGold()+" | Learn: "+fb.getLearn();
                    lm.remove(i);
                    lm.add(i, output);
                    list.setSelectedIndex(i);
                } else if (cb.getSelectedIndex()==1 && i==0){
                    String newName = name.getText();
                    String newDesc = desc.getText();
                    int[] newAttr = {Integer.parseInt(phy.getText()), Integer.parseInt(men.getText()),
                    Integer.parseInt(soc.getText()), Integer.parseInt(mag.getText())};
                    int newFame = Integer.parseInt(other.getText());
                    double newLearn = Double.parseDouble(learn.getText());
                    int nr=1;
                    while(dNr(nr)) {
                        nr++;
                    }
                    dList.add(new FeatsDeeds(newName, newDesc, newAttr, newFame, newLearn, nr));
                    new SaveDat().saveDat(".\\Data\\deeds.fe", dList);
                    FeatsDeeds de = dList.get(dList.size()-1);
                    String output = de.getNr()+"| "+de.getName()+" | Phy: "+de.getAttribute(0)+" | Men: "+de.getAttribute(1)
                            +" | Soc: "+de.getAttribute(2)+" | Mag: "+de.getAttribute(3)
                            +" | Fame: "+de.getFame()+" | Learn: "+de.getLearn();
                    lm.addElement(output);
                    list.setSelectedIndex(lm.size()-1);
                } else if (cb.getSelectedIndex()==1 && i!=0) {
                    int nr = dList.get(i-1).getNr();
                    dList.remove(i-1);
                    String newName = name.getText();
                    String newDesc = desc.getText();
                    int[] newAttr = {Integer.parseInt(phy.getText()), Integer.parseInt(men.getText()),
                            Integer.parseInt(soc.getText()), Integer.parseInt(mag.getText())};
                    int newFame = Integer.parseInt(other.getText());
                    double newLearn = Double.parseDouble(learn.getText());                        
                    dList.add(i-1, new FeatsDeeds(newName, newDesc, newAttr, newFame, newLearn, nr));
                    new SaveDat().saveDat(".\\Data\\deeds.fe", dList);
                    FeatsDeeds de = dList.get(i-1);
                    String output = de.getNr()+"| "+de.getName()+" | Phy: "+de.getAttribute(0)+" | Men: "+de.getAttribute(1)
                            +" | Soc: "+de.getAttribute(2)+" | Mag: "+de.getAttribute(3)
                            +" | Fame: "+de.getFame()+" | Learn: "+de.getLearn();
                    lm.remove(i);
                    lm.add(i, output);
                    list.setSelectedIndex(i);
                }
            }
        });
        return bt;
    }
    private JButton btDelete() {
        JButton bt = new JButton("Delete");
        bt.addActionListener((ActionEvent e) -> {
            int i = list.getSelectedIndex();
            int mOK=JOptionPane.showConfirmDialog(null, "Want to save new Feat?", "", JOptionPane.YES_NO_OPTION);
                if(mOK == JOptionPane.YES_NO_OPTION) {
                    if(cb.getSelectedIndex()==0) {
                        bgList.remove(i-1);
                        new SaveDat().saveDat(".\\Data\\bgr.fe", bgList);
                        lm.remove(i);
                    } else {
                        dList.remove(i-1);
                        new SaveDat().saveDat(".\\Data\\deed.fe", dList);
                        lm.remove(i);
            }
                }
        });
        return bt;
    }
}
