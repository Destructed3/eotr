/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import resources.Inhabitants.InhStu;
import resources.Inhabitants.InhTea;
import resources.Inhabitants.Inhabitants;

/**
 *
 * @author Die Axt
 */
public class IFFormer extends root.IFTemplate {
    public IFFormer(Mainframe pdsk) {
        super("Former Inhabitants");
        this.dsk=pdsk;
        run();
    }
    
    private void run() {
        setIF(pTop(),pMain(),pTool());
        this.setSize(600, 300);
        cb.setSelectedIndex(0);
    }
    
    private JPanel pTop() {
        if(pTop==null) {
            pTop = new JPanel();
            pTop.add(cb());
        }
        return pTop;
    }
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new GridLayout(1,2));
            pMain.add(new JScrollPane(lInh()));
            pMain.add(pInh());
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
    
    private JPanel pInh() {
        if(pInh==null) {
            pInh = new JPanel(new GridLayout(2,1));
            pInh.add(pInhVal());
            pInh.add(pInhLists());
        }
        return pInh;
    }
    private JPanel pInhVal() {
        if(pInhVal==null) {
            pInhVal = new JPanel(new GridLayout(4,1));
            JPanel p0 = new JPanel(new GridLayout(1,4));
            JPanel p1 = new JPanel(new GridLayout(1,4));
            JPanel p2 = new JPanel(new GridLayout(1,4));
            JPanel p3 = new JPanel(new java.awt.BorderLayout());
            p0.add(new JLabel("Number"));
            p0.add(lNr());
            p0.add(new JLabel("Name"));
            p0.add(lName());
            p1.add(new JLabel("Physical"));
            p1.add(lPhy());
            p1.add(new JLabel("Mental"));
            p1.add(lMen());
            p2.add(new JLabel("Social"));
            p2.add(lSoc());
            p2.add(new JLabel("Magic"));
            p2.add(lMag());
            p3.add(new JLabel("Leave Reason: "), BorderLayout.WEST);
            p3.add(lReason(), BorderLayout.CENTER);
            pInhVal.add(p0);
            pInhVal.add(p1);
            pInhVal.add(p2);
            pInhVal.add(p3);
        }
        return pInhVal;
    }
    private JPanel pInhLists() {
        if(pInhLists==null) {
            pInhLists = new JPanel(new GridLayout(1,2));
            pInhLists.add(new JScrollPane(lTrait()));
            pInhLists.add(new JScrollPane(lDeed()));
        }
        return pInhLists;
    }
    
    private JLabel lNr() {
        if(lNr==null) {
            lNr = new JLabel();
        }
        return lNr;
    }
    private JLabel lName() {
        if(lName==null) {
            lName = new JLabel();
        }
        return lName;
    }
    private JLabel lPhy() {
        if(lPhy==null) {
            lPhy = new JLabel();
        }
        return lPhy;
    }
    private JLabel lMen() {
        if(lMen==null) {
            lMen = new JLabel();
        }
        return lMen;
    }
    private JLabel lSoc() {
        if(lSoc==null) {
            lSoc = new JLabel();
        }
        return lSoc;
    }
    private JLabel lMag() {
        if(lMag==null) {
            lMag = new JLabel();
        }
        return lMag;
    }
    private JLabel lReason() {
        if(lReason==null) {
            lReason = new JLabel();
        }
        return lReason;
    }
    
    private JList lInh() {
        if(lInh==null) {
            lInh = new JList(lmInh());
            lsmInh = lInh.getSelectionModel();
            lsmInh.addListSelectionListener(lslInh());
        }
        return lInh;
    }
    private JList lDeed() {
        if(lDeed==null) {
            lDeed = new JList(lmDeed());
        }
        return lDeed;
    }
    private JList lTrait() {
        if(lTrait==null) {
            lTrait = new JList(lmTrait());
        }
        return lTrait;
    }
    private DefaultListModel lmInh() {
        if(lmInh==null) {
            lmInh = new DefaultListModel();
        }
        return lmInh;
    }
    private DefaultListModel lmDeed() {
        if(lmDeed==null) {
            lmDeed = new DefaultListModel();
        }
        return lmDeed;
    }
    private DefaultListModel lmTrait() {
        if(lmTrait==null) {
            lmTrait = new DefaultListModel();
        }
        return lmTrait;
    }
    
    private JComboBox cb() {
        if(cb==null) {
            cb = new JComboBox(sLists);
            cb.addItemListener(ilCb());
        }
        return cb;
    }
    
    private ItemListener ilCb() {
        if(ilCb==null) {
            ilCb = (ItemEvent e) -> {
                if(cb.getSelectedIndex()==0) {
                    setLInh_Tea();
                } else {
                    setLInh_Stu();
                }
            };
        }
        return ilCb;
    }
    private ListSelectionListener lslInh() {
        if(lslInh==null) {
            lslInh = (ListSelectionEvent e) -> {
                if(lInh.isSelectionEmpty()) {
                    clearPInh();
                } else {
                    String sNr = (String)lInh.getSelectedValue();
                    if(cb.getSelectedIndex()==0) {
                        int teaNr = Integer.parseInt(sNr.substring(0,5));
                        tea = dsk.getData().lTea.stream().filter(pTea -> 
                                pTea.getNumber()==teaNr).findAny().get();
                        setPInh(tea);
                    } else {
                        int stuNr = Integer.parseInt(sNr.substring(0,5));
                        stu = dsk.getData().lStu.stream().filter(pStu -> 
                                pStu.getNumber()==stuNr).findAny().get();
                        setPInh(stu);
                    }
                }
            };
        }
        return lslInh;
    }
    
    private void setLInh_Tea() {
        lmInh.clear();
        dsk.getData().lTea.stream().filter(pTea -> 
                pTea.isFormer()).forEach(pTea -> {
                    String output = pTea.getNumber()+" | "
                            +pTea.getName()+" | "
                            +pTea.getLeaveReasonString();
            lmInh.addElement(output);
        });
    }
    private void setLInh_Stu() {
        lmInh.clear();
        dsk.getData().lStu.stream().filter(pStu -> 
                pStu.isFormer()).forEach(pStu -> {
                    String output = pStu.getNumber()+" | "
                            +pStu.getName()+" | "
                            +pStu.getLeaveResonString();
                    lmInh.addElement(output);
        });
    }
    private void setPInh(Inhabitants inh) {
        lNr.setText(String.valueOf(inh.getNumber()));
        lName.setText(inh.getName());
        lPhy.setText(String.valueOf(inh.getAttribute(0)));
        lMen.setText(String.valueOf(inh.getAttribute(1)));
        lSoc.setText(String.valueOf(inh.getAttribute(2)));
        lMag.setText(String.valueOf(inh.getAttribute(3)));
        if(cb.getSelectedIndex()==0) {
            lReason.setText(new InhTea(false,false).getLeaveReasonString(inh.getLeaveReason()));
        } else {
            lReason.setText(new InhStu().getLeaveResonString(inh.getLeaveReason()));
        }
    }
    private void clearPInh() {
        lNr.setText("");
        lName.setText("");
        lPhy.setText("");
        lMen.setText("");
        lSoc.setText("");
        lMag.setText("");
        lReason.setText("");
    }
    
    Mainframe dsk = null;
    resources.Inhabitants.InhStu stu = null;
    resources.Inhabitants.InhTea tea = null;
    
    JPanel pTop = null;
    JPanel pMain = null; 
    JPanel pTool = null;
    
    JPanel pInh = null;
    JPanel pInhVal = null;
    JPanel pInhLists = null;
    
    JLabel lNr = null;
    JLabel lName = null;
    JLabel lPhy = null;
    JLabel lMen = null;
    JLabel lSoc = null;
    JLabel lMag = null;
    JLabel lReason = null;
    
    JList lInh = null;
    JList lDeed = null;
    JList lTrait = null;
    DefaultListModel lmInh = null;
    DefaultListModel lmDeed = null;
    DefaultListModel lmTrait = null;
    ListSelectionModel lsmInh = null;
    ListSelectionModel lsmDeed = null;
    ListSelectionModel lsmTrait = null;
    
    JComboBox cb = null;
    String[] sLists = {"Teacher","Students"};
    
    ItemListener ilCb = null;
    ListSelectionListener lslInh = null;
    ListSelectionListener lslDeed = null;
    ListSelectionListener lslTrain = null;
    
    
}
