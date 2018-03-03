/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.region;

import gui.region.resources.VenueDescriptions;
import gui.Mainframe;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import resources.regions.Region;
import root.IFTemplate;

/**
 *
 * @author Die Axt
 */
public class IFRegion extends IFTemplate {
    public IFRegion(Region reg,Mainframe dsk) {
        super(reg.getName());
        this.vd=new VenueDescriptions();
        this.reg=reg;
        this.dsk=dsk;
        run();
        this.jobsSelected=true;
    }
    
    private void run() {
        setIF(pTop(),pMain(),pTool());
        this.setSize(421, 400);
        lVenues.setSelectedIndex(0);
    }
    
    private JPanel pTop() {
        if(pTop==null) {
            pTop = new JPanel(new java.awt.GridLayout(1,2));
            pTop.add(getLName());
            pTop.add(getLStatus());
        }
        return pTop;
    }
    private JPanel pMain() {
        if(pMain==null) {
            pMain = new JPanel(new java.awt.BorderLayout());
            pMain.add(getP_Region(),BorderLayout.CENTER);
            pMain.add(new JScrollPane(lJournal),BorderLayout.SOUTH);
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
    
    private JLabel getLName() {
        if(lName==null) {
            lName = new JLabel(reg.getName());
        }
        return lName;
    }
    private JLabel getLStatus() {
        if(lStatus==null) {
            lStatus = new JLabel(reg.getStatus_String());
        }
        return lStatus;
    }
    
    private JPanel getP_Region() {
        if(pRegion==null) {
            pRegion = new JPanel(new BorderLayout());
            pRegion.add(lVenues(),BorderLayout.WEST);
            pRegion.add(pRight(),BorderLayout.CENTER);
        }
        return pRegion;
    }
    private JPanel pRight() {
        if(pRight==null) {
            pRight = new JPanel(new BorderLayout());
            pRight.add(pRight_name(),BorderLayout.NORTH);
            pRight.add(getP_Reg_Attributes(),BorderLayout.CENTER);
        }
        return pRight;
    }
    
    private JList lVenues() {
        if(lVenues==null) {
            lVenues = new JList(lm());
            lVenues.addListSelectionListener(slVen());
        }
        return lVenues;
    }
    private DefaultListModel lm() {
        if(lm_Ven==null) {
            lm_Ven = new DefaultListModel();
            setVenueList();
        }
        return lm_Ven;
    }
    private void setVenueList() {
        for(int i=0;i<reg.getVenues().length;i++) {
            if(reg.getVenue(i)) {
                lm_Ven.addElement(newEntry(i));
            }
        }
    }
    private String newEntry(int veNr) {
        switch(veNr) {
            default:
                return "Town";
            case 1:
                return "Homestead";
            case 2:
                return "Trade Post";
            case 3:
                return "Castle";
            case 4:
                return "Tavern";
            case 5:
                return "Monastery";
        }
    }
    private ListSelectionListener slVen() {
        slVen = (ListSelectionEvent e) -> {
            String selection = String.valueOf(lVenues.getSelectedValue());
            switch(selection) {
                default:
                    getL_VenDesc().setText(vd.getDescTown());
                    break;
                case "Homestead":
                    getL_VenDesc().setText(vd.getDescHomestead());
                    break;
                case "Trade Post":
                    getL_VenDesc().setText(vd.getDescTradePost());
                    break;
                case "Castle":
                    getL_VenDesc().setText(vd.getDescCastle());
                    break;
                case "Tavern":
                    getL_VenDesc().setText(vd.getDescTavern());
                    break;
                case "Monastery":
                    getL_VenDesc().setText(vd.getDescMonastery());
                    break;
            }
        };
        return slVen;
    }
    
    private JPanel pRight_name() {
        if(pRight_name==null) {
            pRight_name = new JPanel(new java.awt.GridLayout(1, 2));
            pRight.add(getLName());
            pRight.add(getLStatus());
        }
        return pRight_name;
    }
    
    private JPanel getP_Reg_Attributes() {
        if(pReg_attributes==null) {
            pReg_attributes = new JPanel(new java.awt.GridLayout(2, 1));
            pReg_attributes.add(new JScrollPane(getL_VenDesc()));
            pReg_attributes.add(getP_Activities());
        }
        return pReg_attributes;
    }
    private JLabel getL_VenDesc() {
        if(lVenDesc==null) {
            lVenDesc = new JLabel();
        }
        return lVenDesc;
    }
    
    private JPanel getP_Activities() {
        if(pChoseActivities==null) {
            pChoseActivities = new JPanel(new BorderLayout());
            pChoseActivities.add(getP_Buttons(),BorderLayout.WEST);
            pChoseActivities.add(new JScrollPane(getL_activities()),BorderLayout.CENTER);
            pChoseActivities.add(getBT_chose(),BorderLayout.SOUTH);
        }
        return pChoseActivities;
    }
    
    private JPanel getP_Buttons() {
        if(pButtons==null) {
            pButtons = new JPanel(new java.awt.GridLayout(2, 1));
            pButtons.add(getBT_ShowJobs());
            pButtons.add(getBT_ShowAdv());
        }
        return pButtons;
    }    
    private JButton getBT_ShowJobs() {
        if(btShowJobs==null) {
            btShowJobs = new JButton("Show Jobs");
            btShowJobs.addActionListener(alSetList_active_job());
        }
        return btShowJobs;
    }
    private JButton getBT_ShowAdv() {
        if(btShowAdv==null) {
            btShowAdv = new JButton("Show Adventures");
            btShowAdv.addActionListener(alSetList_active_adv());
        }
        return btShowAdv;
    }
    private ActionListener alSetList_active_job() {
        if(alSetList_active_job==null) {
            alSetList_active_job = (ActionEvent e) -> {
                jobsSelected=true;
                setLM_activities_job();
            };
        }
        return alSetList_active_job;
    }
    private ActionListener alSetList_active_adv() {
        jobsSelected=false;
        if(alSetList_active_adv==null) {
            alSetList_active_adv = (ActionEvent e) -> {
                setLM_activities_adv();
            };
        }
        return alSetList_active_adv;
    }
    
    private JList getL_activities() {
        if(lAct==null) {
            lAct = new JList(getLM_activities());
        }
        return lAct;
    }
    private DefaultListModel getLM_activities() {
        if(lmAct==null) {
            lmAct = new DefaultListModel();
            refreshList();
        }
        return lmAct;
    }
    private void setLM_activities_job() {
        lmAct.clear();
        refreshList();
    }
    private void setLM_activities_adv() {
        lmAct.clear();
    }
    private String getAct_String(int jobNr) {
        switch(jobNr) {
            default:
                return "Guard ";
            case 1:
                return "Advisor ";
            case 2:
                return "Bard ";
            case 3:
                return "Sorcerer ";
        }
    }
    
    private JButton getBT_chose() {
        if(btChose==null) {
            btChose = new JButton("Chose activity");
            btChose.addActionListener(getAL_chose());
        }
        return btChose;
    }
    private ActionListener getAL_chose() {
        if(alChose==null) {
            alChose = (ActionEvent e) -> {
                System.out.println("Chosing Job or Adventure!");
                if(jobsSelected) {
                    getJob();
                } else {
                    getAdv();
                }
            };
        }
        return alChose;
    }
    
    private void getJob() {
        try {
            openIFCreateJob();
        } catch(Exception e) {
            String s="Please chose one of the available jobs!";
            String s2="ERROR!";
            JOptionPane.showMessageDialog(null, s, s2, JOptionPane.OK_OPTION);
        }
    }
    private void openIFCreateJob() {
        int index = lAct.getSelectedIndex();
        IF_Job jif = getL_IFJob().get(index);
        if(jif.isShowing()) {
            jif.toFront();
        } else {
            jif.show();
        }
    }
    private void getAdv() {
        System.out.println("Wrong turn...");
    }
    
    private List<IF_Job> getL_IFJob() {
        if(l_IFJob==null) {
            l_IFJob = new ArrayList();
            getLM_activities().clear();
            dsk.getRes().lJob.stream().filter(pAJ -> pAJ.getAdvRegion().equals(reg)).forEach(pAJ -> {
                IF_Job jif = new IF_Job(reg,dsk,pAJ);
                dsk.addJIF(jif);
                l_IFJob.add(jif);
            });
        }
        return l_IFJob;
    }
    public void removeJob(IF_Job cj) {
        try {
            l_IFJob.remove(cj);
        } catch(NoSuchElementException e) {
            IF_Job paj = l_IFJob.stream().filter(pIF -> pIF.get_TheJob().getID().equals(cj.get_TheJob().getID())).findAny().get();
            l_IFJob.remove(paj);
        }
        refreshList();
    }
    private void refreshList() {
        lmAct.clear();
        getL_IFJob().forEach(pIF -> {
            int topic = pIF.get_TheJob().getTopic();
            String output = getAct_String(topic)+"-Job";
            lmAct.addElement(output);
        });
    }
    public void refreshTables() {
        this.l_IFJob.forEach(pIF -> {
            pIF.refreshTT();
        });
    }
    public void refreshCBTea() {
        this.l_IFJob.forEach(pIF -> {
            pIF.renewCBTea();
        });
    }
    
    public Region getRegion() {
        return reg;
    }
    
    
    Mainframe dsk;
    Region reg;
    private final VenueDescriptions vd;
    
    private boolean jobsSelected;
    
    private JPanel pTop;
    private JPanel pMain;
    private JPanel pTool;
    
    private JPanel pRegion;
    private JPanel pRight;
    
    private JPanel pRight_name;
    private JPanel pReg_attributes;
    
    private JPanel pChoseActivities;
    
    private JPanel pButtons;
    
    private JList lAct;
    private DefaultListModel lmAct;
    
    private JLabel lVenDesc;
    
    private JLabel lName;
    private JLabel lStatus;
    
    private JList lVenues;
    private DefaultListModel lm_Ven;
    private ListSelectionListener slVen;
    
    private JButton btShowJobs;
    private JButton btShowAdv;
    
    private ActionListener alSetList_active_job;
    private ActionListener alSetList_active_adv;
        
    private JList lJournal;
            
    private JButton btChose;
    private ActionListener alChose;
    
    List<IF_Job> l_IFJob;
    
}
