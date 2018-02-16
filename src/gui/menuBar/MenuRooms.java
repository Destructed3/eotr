/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.room.IFRoomBuild;
import gui.room.IFRoomDormMenu;
import gui.room.IFRoomStudyMenu;
import gui.room.IFRoomQuarterMenu;
import gui.Mainframe;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/** 
 *
 * @author Die Axt
 */
public class MenuRooms extends JMenu {
    public MenuRooms(Mainframe pdsk) {
        this.dsk=pdsk;
        run();
    }
    Mainframe dsk;
    IFRoomStudyMenu study;
    IFRoomQuarterMenu quarter;
    IFRoomDormMenu dorm;
    IFRoomBuild build;
    
    private void run() {
        study = new IFRoomStudyMenu(dsk);
        quarter = new IFRoomQuarterMenu(dsk);
        dorm = new IFRoomDormMenu(dsk);
        build = new IFRoomBuild(dsk);
        dsk.addJIF(dorm);
        dsk.addJIF(quarter);
        dsk.addJIF(study);
        dsk.addJIF(build);
        this.setText("Rooms");
        this.add(miOpenIF(study,"Study Rooms"));
        this.add(miOpenIF(quarter,"Teacher Quarters"));
        this.add(miOpenIF(dorm, "Dorms"));
        this.add(miOpenIF(build,"Build Room"));
        //this.add(miOpenIF(null, "Stats"));
        
    }
    private JMenuItem miOpenIF(JInternalFrame jif, String name) {
        JMenuItem miOpen = new JMenuItem(name);
        miOpen.addActionListener((ActionEvent e) -> {
            showJIF(jif);
        });
        return miOpen;
    }
    public void showJIF(JInternalFrame jif) {
        if(jif!=null) {
            if(jif.isShowing()) {
                jif.toFront();
            } else {
                jif.show();
            }
        }
    }
    public void showRS() {
        if(study.isShowing()) {
            study.toFront();
        } else {
            study.show();
        }
    }
    public void showRQ() {
        if(quarter.isShowing()) {
            quarter.toFront();
        } else {
            quarter.show();
        }
    }
    public void showRD() {
        if(dorm.isShowing()) {
            dorm.toFront();
        } else {
            dorm.show();
        }
    }
    public void showRB() {
        if(build.isShowing()) {
            build.toFront();
        } else {
            build.show();
        }
    }
    public IFRoomStudyMenu getStudy() {
        return study;
    }
    public IFRoomQuarterMenu getQu() {
        return quarter;
    }
    public IFRoomDormMenu getDorm() {
        return dorm;
    }
    public IFRoomBuild getBuild() {
        return build;
    }
    
}
