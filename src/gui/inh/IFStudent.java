/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.inh;

import gui.course.IFCourseMenu;
import gui.Mainframe;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import resources.Inhabitants.InhStu;

/**
 *
 * @author Die Axt
 */
public class IFStudent extends root.IFTemplate {
    public IFStudent(InhStu pstu, Mainframe pdsk) {
        super(pstu.getName());
        this.stu=pstu;
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk = null;
    JPanel top = null;
    JPanel main = null;
    JPanel tool = null;
    JPanel mainT = null;
    JPanel mainM = null;
    JPanel mainB = null;
    JButton btCourse = null;
    JButton btDorm = null;
    JButton btJournal = null;
    
    InhStu stu = null;
    IFCourseMenu ifc = null;
    IFJournal ifJournal = null;
    
    private void run() {
        setIF(setTop(),setMain(),setTool());
    }
    private JPanel setTop() {
        if(top==null) {
            JLabel bkg = new JLabel(stu.getBckg().getName());
            bkg.setToolTipText(stu.getBckg().getDescription());
            top = new JPanel(new java.awt.GridLayout(1,1));
            top.add(new JLabel("<html><body>"
                    + "<table>"
                    + "<tr><td>Name: </td><td>"+stu.getName()+" </td><td> </td><td>Heritage: </td><td> "+stu.getBckg().getName()+"</td></tr>"
                    + "<tr><td>Year: </td><td>"+stu.getSemester()+" </td><td> </td><td>Dorm: </td><td> "+getDorm().getRoomName()+"</td></tr>"
                    + "</table>"
                    + "</body></html>"));
        }
        return top;
    }
    private JPanel setMain() {
        if(main==null) {
            String learn = stu.getLearn()*100+"";
            main = new JPanel(new java.awt.GridLayout(4, 4));
            main.add(new JLabel("Physical:"));
            main.add(new JLabel(stu.getAttribute(0)+""));
            main.add(new JLabel("Mental:"));
            main.add(new JLabel(stu.getAttribute(1)+""));
            main.add(new JLabel("Social:"));
            main.add(new JLabel(stu.getAttribute(2)+""));
            main.add(new JLabel("Magical:"));
            main.add(new JLabel(stu.getAttribute(3)+""));
            main.add(new JLabel("Learning:"));
            main.add(new JLabel(learn.substring(0,2)+"%"));
            main.add(new JLabel("Workload:"));
            main.add(new JLabel(stu.getWorkTime()+""));
            main.add(new JLabel("Happyness:"));
            main.add(new JLabel(stu.getHappiness()+""));
        }
        return main;
    }
    private JPanel setTool() {
        if(tool==null) {
            tool = new JPanel(new java.awt.GridLayout(2,2));
            tool.add(btJournal());
            tool.add(btCourse());
            tool.add(btDorm());
            tool.add(btClose());
        }
        return tool;
    }
    private JButton btCourse() {
        if(btCourse==null) {
            btCourse = new JButton("Show Courses");
            btCourse.addActionListener((ActionEvent e) -> {
                if(ifc==null) {
                    ifc = new IFCourseMenu(stu,dsk);
                    dsk.addJIF(ifc);
                    ifc.show();
                } else {
                    if(ifc.isShowing()) {
                        ifc.toFront();
                    } else {
                        ifc.show();
                    }
                }
            });
        }
        return btCourse;
    }
    private JButton btDorm() {
        if(btDorm==null) {
            btDorm = new JButton("Show Dorm");
            btDorm.addActionListener((ActionEvent e) -> {
                if(this.getDorm()!=null) {
                    dsk.getMenuRooms().getDorm().showRoom(this.getDorm());
                }
            });
        }
        return btDorm;
    }
    private JButton btJournal() {
        if(btJournal==null) {
            btJournal = new JButton("Show Journal");
            btJournal.addActionListener((ActionEvent e) -> {
                if(getIFJournal().isShowing()) {
                    ifJournal.toFront();
                } else {
                    ifJournal.show();
                }
            });
        }
        return btJournal;
    }
    private IFJournal getIFJournal() {
        if(ifJournal==null) {
            ifJournal = new IFJournal(stu);
            dsk.addJIF(ifJournal);
        }
        return ifJournal;
    }
    
    private resources.rooms.RoomDorm getDorm() {
        return dsk.getRes().lRoomDorm.stream().filter(pRD -> pRD.getRoomNr()==stu.getDorm()).findAny().get();
    }
}
