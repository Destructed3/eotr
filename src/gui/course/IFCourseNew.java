/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.course;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import resources.activity.ActivityCourse;
import resources.Inhabitants.InhTea;
import resources.rooms.RoomStudy;
import root.TimeTable;

/**
 *
 * @author Die Axt
 */
public class IFCourseNew extends root.IFTemplate {
    public IFCourseNew(gui.Mainframe pdsk) {
        super("Create new course",true);
        this.dsk=pdsk;
        this.gDem=new GetDemand(dsk);
        run();
    }
    
    private void run() {
        this.tea = dsk.getRes().getLTea().get(0);
        this.rs = dsk.getRes().getLRS().get(0);
        this.duration = 2;
        setTable();
        this.setIF(setMain(),setTool());
        this.setSize(500,500);
        this.addInternalFrameListener(ifa());
        this.add(new JPanel(), BorderLayout.NORTH);
        this.add(new JPanel(), BorderLayout.WEST);
        this.add(new JPanel(), BorderLayout.EAST);
    }
    
    private JPanel setMain() {
        main = new JPanel(new java.awt.GridLayout(2,1));
        main.add(pParameters());
        main.add(new JScrollPane(table));
        return main;
    }
    private JPanel setTool() {
        tool = new JPanel(new java.awt.FlowLayout());
        tool.add(btCreate());
        tool.add(btClose());
        return tool;
    }
    
    private JPanel pParameters() {
        if(pParameters==null) {
            pParameters = new JPanel(new java.awt.GridLayout(3,1));
            pParameters.add(pPTop());
            pParameters.add(pPMid());
            pParameters.add(pPBot());
        }
        return pParameters;
    }
    private JPanel pPTop() {
        if(pPTop==null) {
            pPTop = new JPanel(new java.awt.GridLayout(2,2));
            pPTop.add(new JLabel("Name: "));
            pPTop.add(pSize());
            pPTop.add(tfName());
            pPTop.add(sSize());
        }
        return pPTop;
    }
    private JPanel pPMid() {
        if(pPMid==null) {
            pPMid = new JPanel(new java.awt.GridLayout(2,2));
            pPMid.add(new JLabel("Chose a teacher:"));
            pPMid.add(new JLabel("Chose room:"));
            pPMid.add(cbTea());
            pPMid.add(cbRoom());
        }
        return pPMid;
    }
    private JPanel pPBot() {
        if(pPBot==null) {
            pPBot = new JPanel(new java.awt.GridLayout(2,2));
            pPBot.add(new JLabel("Select Topic:"));
            pPBot.add(lDuration());
            pPBot.add(pPBotLeft());
            pPBot.add(sDuration());
        }
        return pPBot;
    }
    //Parts of pPTop()
    private JPanel pSize() {
        if(pSize==null) {
            pSize = new JPanel(new java.awt.FlowLayout());
            pSize.add(new JLabel("Size:"));
            pSize.add(tfSize());
            pSize.add(lSize());
        }
        return pSize;
    }
    private JLabel lSize() {
        if(lSize==null) {
            lSize = new JLabel("/"+rs.getRoomSize()+"Students"); 
        }
        return lSize;
    }
    private JTextField tfName() {
        if(tfName==null) {
            tfName = new JTextField("New Course");
        }
        return tfName;
    }
    private JSlider sSize() {
        if(sSize==null) {
            sSize = new javax.swing.JSlider(1, rs.getRoomSize(), rs.getRoomSize()/2);
            sSize.setMajorTickSpacing(25);
            sSize.setMinorTickSpacing(1);
            sSize.setPaintTicks(true);
            sSize.addChangeListener((ChangeEvent e) -> {
                tfSize.setText(String.valueOf(sSize.getValue()));
            });
        }
        return sSize;
    }
    private JTextField tfSize() {
        if(tfSize==null) {
            tfSize = new JTextField(String.valueOf(rs.getRoomSize()/2));
            tfSize.setPreferredSize(new Dimension(25,20));
            tfSize.addActionListener(alSize());
        }
        return tfSize;
    }
    private ActionListener alSize() {
        if(alTSize==null) {
            alTSize = (ActionEvent e) -> {
                try {
                    String s = tfSize.getText();
                    int size = Integer.parseInt(s);
                    if(size<=rs.getRoomSize()) {
                        sSize.setValue(size);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "The room has only space for "+rs.getRoomSize()+" People", "Oops!",JOptionPane.OK_OPTION);
                        tfSize.setText(sSize.getValue()+"");
                    }
                } catch(NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Insert numbers only!", "Oops!",JOptionPane.OK_OPTION);
                }
            };
        }
        return alTSize;
    }
    //Parts of pPMid()
    private JComboBox cbTea() {
        if(cbTea==null) {
            cbTea = new javax.swing.JComboBox();
            fillCB_Tea(0);
            cbTea.addItemListener(ilTea());
        }
        return cbTea;
    }
    public void fillCB_Tea(int topic) {
        cbTea.removeAllItems();
        dsk.getRes().getLTea().stream().forEach(ptea -> {
                String teach = ptea.getTeaching()*100+"";
                String output = ptea.getNumber()+" | "
                        +ptea.getName()+" | "
                        +ptea.getAttribute(topic)+" | "
                        +teach.substring(0,2)+"%";
                cbTea.addItem(output);
            });
    }
    private JComboBox cbRoom() {
        if(cbRoom==null) {
            cbRoom = new JComboBox();
            fillCB_Room();
            cbRoom.addItemListener(ilRoom());
        }
        return cbRoom;
    }
    public void fillCB_Room() {
        cbRoom.removeAllItems();
        dsk.getRes().getLRS().forEach(prs -> {
            String output = prs.getRoomNr()+" | "+prs.getRoomName();
            cbRoom.addItem(output);
        });
    }
    //Parts of pPBot()
    private JPanel pPBotLeft() {
        if(pPBotLeft==null) {
            pPBotLeft = new JPanel(new java.awt.GridLayout(2, 2));
            pPBotLeft.add(btPhy());
            pPBotLeft.add(btMen());
            pPBotLeft.add(btSoc());
            pPBotLeft.add(btMag());
            bgAttr();
        }
        return pPBotLeft;
    }
    private JSlider sDuration() {
        if(sDuration==null) {
            sDuration = new JSlider(1,5,2);
            sDuration.setMajorTickSpacing(1);
            sDuration.setMinorTickSpacing(1);
            sDuration.setPaintTicks(true);
            sDuration.addChangeListener(clDur());
        }
        return sDuration;
    }
    private JLabel lDuration() {
        if(lDuration==null) {
            lDuration = new JLabel("Duration: 2 h");
        }
        return lDuration;
    }
    private JCheckBox btPhy() {
        if(btPhy==null) {
            btPhy = new JCheckBox("Physical",true);
            btPhy.addActionListener(alAttr());
            btPhy.setToolTipText(gDem.getDemand(0));
        }
        return btPhy;
    }
    private JCheckBox btMen() {
        if(btMen==null) {
            btMen = new JCheckBox("Mental");
            btMen.addActionListener(alAttr());
            btMen.setToolTipText(gDem.getDemand(1));
        }
        return btMen;
    }
    private JCheckBox btSoc() {
        if(btSoc==null) {
            btSoc = new JCheckBox("Social");
            btSoc.addActionListener(alAttr());
            btSoc.setToolTipText(gDem.getDemand(2));
        }
        return btSoc;
    }
    private JCheckBox btMag() {
        if(btMag==null) {
            btMag = new JCheckBox("Magic");
            btMag.addActionListener(alAttr());
            btMag.setToolTipText(gDem.getDemand(3));
        }
        return btMag;
    }
    private ButtonGroup bgAttr() {
        if(bgAttr==null) {
            bgAttr = new ButtonGroup();
            bgAttr.add(btPhy);
            bgAttr.add(btMen);
            bgAttr.add(btSoc);
            bgAttr.add(btMag);
        }
        return bgAttr;
    }
    
    private void setTable() {
        table = new JTable(setTM());
        table.addMouseListener(maSelectCell());
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
    }
    private TimeTable setTM() {
        if(tm==null) {
            tm=new TimeTable(setData(),dsk,true);
        }
        return tm;
    }
    private void setCell(int h, int d) {
        if(isTeacherFree(h,d) && isRoomFree(h,d)) {
                    dat[h][d]="";
                } else {
                    dat[h][d]="Occupied";
                }
    }
    private boolean isTeacherFree(int h, int d) {
        String nothing="";
        return nothing.equals(tea.getTimeTableHour(h, d));
    }
    private boolean isRoomFree(int h, int d) {
        String nothing="";
        return nothing.equals(rs.getRoomUsage(h, d));
    }
    private String[][] setData() {
        if(dat==null) {
            this.dat = new String[10][7];
            for(int h=0;h<10;h++) {
                for(int d=0;d<7;d++) {
                    setCell(h,d);
                }
            }
        }
        return dat;
    }
    
    private String[][] setData_Course() {
        System.out.println("Prepare dat for course-creation");
        for(int h=0;h<10;h++) {
            for(int d=0;d<7;d++) {
                System.out.println("CHECKING Cell "+h+"|"+d);
                prepareData_Course_setCell(h,d);   
                System.out.println("Output: "+dat[h][d]);
                System.out.println("Cell "+h+"|"+d+" | FINISHED!");
            }
        }
        return dat;
    }
    private void prepareData_Course_setCell(int h, int d) {
        if(!isCellTagged(h,d)) {
            dat[h][d]="";
        } else {
            dat[h][d]=String.valueOf(table.getValueAt(h, d));
        }
    }

    private boolean isCellTagged(int h, int d) {
        String courseName=tfName.getText();
        String cell = String.valueOf(table.getValueAt(h,d));
        System.out.println("IsCellTagged: "+cell.equals(courseName));
        return cell.equalsIgnoreCase(courseName);
    }
    
    private JButton btCreate() {
        JButton bt = new JButton("Create");
        bt.addActionListener(alCreate());
        return bt;
    }
    private ActionListener alCreate() {
        ActionListener al = (ActionEvent e) -> {
            if(isTimeframeSelected()) {
                System.out.println("Trying to create course");
                this.tea.addActivity(createCourse());
                this.dsk.getMenuInh().refreshTT();
                this.dispose();
            } else {
                JOptionPane.showConfirmDialog(null, "Select timeframe!","ERROR",JOptionPane.OK_OPTION);
            }
        };
        return al;
    }
    private ActivityCourse createCourse() {
        ActivityCourse ac = setCourse();
        System.out.println("Course "+ac.getID()+" created");
        addCourse(ac);
        return ac;
    }
    private void addCourse(ActivityCourse ac) {
        dsk.getRes().getLAC().add(ac);
        if(dsk.getMenuCourse().getCourse()!=null) {
            dsk.getMenuCourse().getCourse().addCourse();
            dsk.getMenuCourse().setCourseNew(null);
        }
        dsk.getMenuRooms().getStudy().getRoom(ac.getRoom()).changeTT();
        dsk.getRB().setJLCour_addCourse();
        dsk.getMenuInh().refreshTT();
    }
    
    private ActivityCourse setCourse() {        
        String name = tfName.getText();
        System.out.println("Name: "+name);
        int teaNr = tea.getNumber();
        System.out.println("Teacher: "+teaNr);
        int rsNr = rs.getRoomNr();
        System.out.println("Room: "+rsNr);
        int dur = sDuration.getValue();
        System.out.println("Duration: "+dur);
        int size = sSize.getValue();
        System.out.println("Size: "+size);
        int topic=getTopic();
        System.out.println("Topic: "+topic);
        return new ActivityCourse(name,teaNr,rsNr,dur,size,topic,setData_Course(),dsk);
    }
    
    private InternalFrameAdapter ifa() {
        InternalFrameAdapter ifa = new InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                tfName.requestFocus();
            }
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                dsk.getMenuCourse().setCourseNew(null);
            }
        };
        return ifa;
    }
    private MouseAdapter maSelectCell() {
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //Find and test new Row/Column
                int newRow=table.rowAtPoint(evt.getPoint());
                int newColumn=table.columnAtPoint(evt.getPoint());
                boolean ocu=false;
                try {
                    //Is there something in the cell, is it the course and was this already discovered
                    for(int i=0;i<sDuration.getValue();i++) {
                        if(table.getValueAt(newRow+i, newColumn)!="" 
                                && table.getValueAt(newRow, newColumn)!=tfName.getText() && !ocu) {
                            JOptionPane.showMessageDialog(null, 
                                    "Not Enough Time! Select another hour!","Not Enough Time!",JOptionPane.OK_OPTION);
                            ocu=true;
                        }
                    }
                } catch(java.lang.ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, 
                            "Not Enough Time111! Select another hour!","Not Enough Time!",JOptionPane.OK_OPTION);
                    ocu=true;
                }
                if(!ocu) {
                    //Delete former selection
                    for(int i=0;i<duration;i++) {
                        if(tm.getValueAt(selectedRow+i, selectedColumn)!="Occupied") {
                            tm.setValueAt("", selectedRow+i, selectedColumn);
                        }
                    }
                    //Set new selected Row/Column and add new selection to table
                    selectedRow = newRow;
                    selectedColumn = newColumn;
                    for(int i=0;i<duration;i++) {
                        tm.setValueAt(tfName.getText(), selectedRow+i, selectedColumn);
                    }
                }
            }
        };
        return ma;
    }
    private ChangeListener clDur() {
        ChangeListener cl = (ChangeEvent e) -> {            
            duration=sDuration.getValue();
            lDuration.setText(duration+" h");
            for(int row=0;row<10;row++) {
                table.setValueAt("", row, selectedColumn);
            }
            try {
                for(int i=0;i<duration;i++) {
                tm.setValueAt(tfName.getText(), selectedRow+i, selectedColumn);
                } 
            }catch(ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, 
                            "Not enough time! Select another Hour", "Not Enough Time!", JOptionPane.OK_OPTION);
            }
        };
        return cl;
    }
    private ActionListener alAttr() {
        ActionListener al = (ActionEvent e) -> {
            int topic = getTopic();
            fillCB_Tea(topic);
        };
        return al;
    }
    private ItemListener ilTea() {
        ItemListener il = (ItemEvent e) -> {
            try {
                refillTable();
            } catch(java.lang.NullPointerException ex) {
                System.out.println(e+" -> "+ex);
            }
        };
        return il;
    }
    private ItemListener ilRoom() {
        ItemListener il = (ItemEvent e) -> {
            rs = getRS();
            clearTable();
            sSize.setMaximum(rs.getRoomSize());
            sSize.setValue(rs.getRoomSize()/2);
            tfSize.setText(rs.getRoomSize()/2+"");
            lSize.setText("/"+sSize.getMaximum()+"Students");
            for(int row=0;row<10;row++) {
                for(int column=0;column<7;column++) {
                    //Test if Cell is empty and if the room is booked at the same time. If so the cell gets blocked
                    if(table.getValueAt(row, column)=="" 
                            && !"".equals(rs.getRoomUsage(row, column))) {
                        table.setValueAt("Occupied", row, column);
                    }
                }
            }
        };
        return il;
    }
    private int getTopic() {
        int topic=0;
        if(btMen.isSelected()) {
                topic=1;
            } else if(btSoc.isSelected()) {
                topic=2;
            } else if(btMag.isSelected()) {
                topic=3;
            }            
        return topic;
    }
    private InhTea getTea() {
        String selectedItem = (String)cbTea.getSelectedItem();
        String tNr = selectedItem.substring(0, 5);
        System.out.println(tNr);
        return dsk.getRes().getLTea().stream().filter(ptea -> 
                ptea.getNumber()==Integer.parseInt(tNr)).findAny().get();
    }
    private RoomStudy getRS() {
        String s = (String)cbRoom.getSelectedItem();        
        return dsk.getRes().getLRS().stream().filter(prs -> 
                prs.getRoomNr()==Integer.parseInt(s.substring(0,5))).findAny().get();
        
    }
    public void addTea(InhTea ptea) {
        int i=0;
        if(btMen.isSelected()) {
            i=1;
        }
        if(btSoc.isSelected()) {
            i=2;
        }
        if(btMag.isSelected()) {
            i=3;
        }
        addItem_cbTea(ptea, i);   
    }
    private void addItem_cbTea(InhTea ptea, int i) {
        String teach = String.valueOf(ptea.getTeaching()*100);
        String output = ptea.getNumber()+" | "
                +ptea.getName()+" | "
                +ptea.getAttribute(i)+" | "
                +teach.substring(0,2)+"%";
        cbTea.addItem(output);
    }
    private boolean isTimeframeSelected() {
        if(tm.getValueAt(selectedRow, selectedColumn)=="") {
            JOptionPane.showMessageDialog(null,
                    "Select when the course takes place, by clicking in a cell of the table!", "JUST! DO! IT!",JOptionPane.OK_OPTION);
            return false;
        } else {
            return true;
        }
    }
    private void clearTable() {
        //Clear old selections
            for(int row=0;row<10;row++) {
                for(int column=0;column<7;column++) {
                    if(!"".equals(tea.getTimeTableHour(row, column)) 
                            && rs.getRoomUsage(row, column).equals("")) {
                        table.setValueAt("", row, column);
                    }
                }
            }
    }
    private void refillTable() {
        tea = getTea();
        clearTable();
        for(int row=0;row<10;row++) {
            for(int column=0;column<7;column++) {
                //Test if the cell is empty and if there should be something
                if(table.getValueAt(row, column).equals("") 
                        && !"".equals(tea.getTimeTableHour(row, column))) {
                    table.setValueAt("Occupied",row,column);
                }
            }
        }
    }
    
    gui.Mainframe dsk;
    GetDemand gDem;
    //Mainpanels
    JPanel main = null;
    JPanel tool = null;
    //Panels to handle parameters
    JPanel pParameters = null;
    JPanel pPTop;
    JPanel pPMid;
    JPanel pPBot;
    
    JPanel pSize;
    JPanel pPBotLeft;
    //Controlls for Parameters
    JLabel lDuration;
    JLabel lSize;
    JTextField tfName;
    JTextField tfSize;
    JSlider sSize;
    JSlider sDuration;
    JComboBox cbTea;
    JComboBox cbRoom;
    JCheckBox btPhy;
    JCheckBox btMen;
    JCheckBox btSoc;
    JCheckBox btMag;
    ButtonGroup bgAttr;
    //Table
    JTable table = null;
    TimeTable tm = null;
    ListSelectionModel lsm;
    //Parameters
    InhTea tea;
    RoomStudy rs;
    int duration;
    int maxSize;
    int selectedRow;
    int selectedColumn;
    String[][] dat =null;
    
    ActionListener alTSize;
}
