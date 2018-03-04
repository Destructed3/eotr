/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.menuBar.*;
import gui.admin.IFRecapMssg;
import resources.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import resources.Inhabitants.InhTea;
import startGame.StartGame;

/**
 *
 * @author Die Axt
 */
public class Mainframe extends JFrame {
    public Mainframe(InhTea character) {
        super("Esperance of the Realm");
        System.out.println("Start game with hero "+character.getNumber());        
        r = new Random();
        res = new Data();
        sG = new StartGame(this);
        sG.newGame(character);
        res.lTea.stream().forEach((pTea) -> {
            System.out.println("Teacher nr: "+pTea.getNumber());
        });
        run();
        System.out.println("It's year "+res.year);
        System.out.println("Go!");
    }
    public Mainframe(String psave) {
        super("Esperance of the Realm");
        r = new Random();
        res = new Data();
        sG = new StartGame(this);
        sG.loadGame(psave);
        run();
        System.out.println("It's year "+res.year);
        System.out.println("Go!");
    }
    public Mainframe(String psave, boolean newRound) {
        super("Esperance of the Realm");
        r = new Random();
        res = new Data();
        sG = new StartGame(this);
        sG.newRound();
        run();
        menuAdmin.showIFMssg();
        System.out.println("It's year "+res.year);
        System.out.println("Go!");
    }
    
    Data res = null;
    
    public void setData(Data pRes) {
        this.res = pRes;
    }
    public Data getData() {
        return res;
    }
    
    Random r = null;
    StartGame sG;
    //Desktop Elements
    JMenuBar mBar = null;
    JDesktopPane dsk = null;
    JPanel pMain = null; 
    ResourceBar rB = null;

    IFRecapMssg rmsg = null;
    JMenu menuFile;
    MenuInh menuInh = null;
    MenuRooms menuRoom = null;
    MenuCourse menuCourse = null;
    MenuAdmin menuAdmin = null;
    MenuRegion menuRegion = null;
    EndYear endYear = null;
    
    String save = null;
   
    private void run() {
        setDesktop();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new java.awt.BorderLayout());
        this.setSize(800,600);  
        this.setJMenuBar(setMenuBar());
        this.setContentPane(pMain);
    }

    //Methods to set GUI Elements
    private void setDesktop() {
        pMain = new JPanel(new BorderLayout());
        dsk = new JDesktopPane(); 
        rB = new ResourceBar(this);
        pMain.add(dsk, BorderLayout.CENTER);
        pMain.add(rB, BorderLayout.SOUTH);
    }
    private JMenuBar setMenuBar() {
        menuFile = new MenuFile(this);
        menuInh = new MenuInh(this);
        menuRoom = new MenuRooms(this);
        menuCourse = new MenuCourse(this);
        menuAdmin = new MenuAdmin(this);
        menuRegion = new MenuRegion(this);
        endYear = new EndYear(this);
        mBar = new JMenuBar();
        mBar.add(menuFile);
        mBar.add(menuInh);
        mBar.add(menuRoom);
        mBar.add(menuCourse);
        mBar.add(menuAdmin);
        mBar.add(menuRegion);
        mBar.add(endYear);
        Border bo = new LineBorder(Color.yellow);
        mBar.setBorder(bo); 
        return mBar;
    }
    
    //Public Methods
    //To Handle Inhabitants
    public MenuInh getMenuInh() {
        return menuInh;
    }  
    public gui.inh.IFTeaMenu getTM() {
        return menuInh.getTea();
    }
    public gui.inh.IFHire teacherForHireM() {
        return menuInh.getTfH();
    }
    public gui.inh.IFStuMenu getSM() {
        return menuInh.getStu();
    }    
    
    
    public void addTeacher(InhTea ptea) {
        int cost = ptea.getCost()*6;
        res.gold -= cost;
        res.lAccounting.stream().filter(pAcc -> 
                pAcc.getYear()== res.year).findAny().get().addTea(ptea.getCost());
        ptea.setFH(false);
        ptea.createNr(this);
        res.lTea.add(ptea);
        if(menuCourse.getCourseNew()!=null) {
            menuCourse.getCourseNew().addTea(ptea);
        }
        if(menuInh!=null) {
            menuInh.getTea().addTea(ptea);
            menuInh.getTfH().removeTfH(ptea.getTeaFHNr());
        }
        if(menuAdmin!=null) {
            menuAdmin.getIFAcc().getThisYear().renewTables();
        }
        if(menuRegion!=null) {
            menuRegion.getIFRegionMenu().refreshCBTea();
        }
        rB.setJLTea_addTea();
        rB.setJLUpkeep();
    }
    
    public void showIFTea(int i) {
        menuInh.getTea().showTea(i);
    }
    public void showIFStu(int i) {
        menuInh.showIFStu(i);
    }
    //To handle Rooms
    public MenuRooms getMenuRooms() {
        return menuRoom;
    }
    
    //To handle courses
    public MenuAdmin getMenuAdmin() {
        return menuAdmin;
    }
    public MenuCourse getMenuCourse() {
        return menuCourse;
    }
    
    public String getSave() {
        return save;
    }
    
    public void addJIF(JInternalFrame jif) {
        dsk.add(jif);
    }
    public String repString() {
        int rep = res.reputation;
        if(200<rep) {
            return "World Famous";
        } else if(150<rep) {
            return "Famous";
        } else if(100<rep) {
            return "Recognised";
        } else if(50<rep) {
            return "Known";
        } else if(25<rep) {
            return "Barely Known";
        } else {
            return "Unknown";
        }
    }
    
    public MenuRegion getMenuRegion() {
        return menuRegion;
    }
    
    public ResourceBar getRB() {
        return rB;
    }
}
