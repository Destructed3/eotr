/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menuBar;

import gui.course.IFRecap;
import gui.course.IFDemand;
import gui.course.IFCourseMenu;
import gui.course.IFCourseNew;
import gui.Mainframe;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * 
 * @author Die Axt
 */
public class MenuCourse extends JMenu {
    public MenuCourse(Mainframe pdsk) {
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk;
    IFCourseMenu course;
    IFCourseNew courseNew;
    IFDemand demand;
    IFRecap recap = null;
    
    private void run() {
        this.setText("Courses");
        this.add(miOpenCourse());
        this.add(miOpenCourseNew());
        this.add(miOpenDemand());
        this.add(miOpenRecap());
        
    }
    
    private JMenuItem miOpenCourse() {
        JMenuItem mi = new JMenuItem("Courses");
        mi.addActionListener((ActionEvent e) -> {
            if(course==null) {
                course = new IFCourseMenu(dsk);
                dsk.addJIF(course);
                course.show();
            } else {
                showIF(course);
            }
        });
        return mi;
    }
    private JMenuItem miOpenCourseNew() {
        JMenuItem mi = new JMenuItem("Create Course");
        mi.addActionListener((ActionEvent e) -> {
            if(dsk.getData().lRoomStudy.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Build a Room to do courses first", "No room available...", JOptionPane.OK_OPTION);
            } else {
                showCourseNew();
            }
        });
        return mi;
    }
    private JMenuItem miOpenDemand() {
        JMenuItem mi = new JMenuItem("Show Demand");
        mi.addActionListener((ActionEvent e) -> {
            showDemand();
        });
        return mi;
    }
    private JMenuItem miOpenRecap() {
        JMenuItem mi = new JMenuItem("Recap Courses");
        mi.addActionListener((ActionEvent e) -> {
            if(recap==null) {
                recap = new IFRecap(dsk);
                dsk.addJIF(recap);
                recap.show();
            } else {
                showIF(recap);
            }
        });
        return mi;
    }
    
    public void showIF(JInternalFrame jif) {
        if(jif.isShowing()) {
            jif.toFront();
        } else {
            jif.show();
        }
    }
    
    public IFCourseMenu getCourse() {
        return course;
    }
    private IFCourseNew courseNew() {
        if(courseNew==null) {
            courseNew = new IFCourseNew(dsk);
            dsk.addJIF(courseNew);
        }
        return courseNew;
    }
    
    public IFDemand getDemand() {
        if(demand==null) {
            demand = new IFDemand(dsk);
            dsk.addJIF(demand);
        }
        return demand;
    }
    public void showDemand() {
        if(dsk.getData().lStu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students -> no demand!", "No students!", JOptionPane.YES_OPTION);
        } else {
            showIF(getDemand());
        }
    }
    
    private void showCourseNew() {
        if(courseNew().isShowing()) {
            courseNew.toFront();
        } else {
            courseNew.show();
        }
    }
    public IFCourseNew getCourseNew() {
        if(dsk.getData().lRoomStudy.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Build a Room to do courses first", "No room available...", JOptionPane.OK_OPTION);
                return null;
            } else {
                return courseNew();
            }
    }
    public IFCourseNew getCourseNew_vanilla() {
        return courseNew;
    }
    public void setCourseNew(IFCourseNew ifc) {
        if(courseNew!=null && courseNew.isShowing()) {
            courseNew.dispose();
        }
        this.courseNew=ifc;
    }
}
