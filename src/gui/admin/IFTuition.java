/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin;

import gui.Mainframe;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Die Axt
 */
public class IFTuition extends root.IFTemplate {
    public IFTuition(Mainframe pdsk) {
        super("Tituition",true);
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk = null;
    JPanel top = null;
    JPanel main = null;
    JPanel tool = null;
    
    JPanel pTit = null;
    JSlider slTit = null;
    JTextField tfTit = null;
    JButton btTit = null;
    
    JLabel lFees;
    JLabel lExpFees;
    JLabel lCantPay_upcoming;
    JLabel lCantPay_longterm;
    
    private void run() {
        setIF(topP(),mainP(),toolP());
    }
    
    private JPanel topP() {
        if(top==null) {
            top = new JPanel(new java.awt.GridLayout(3,1));
            top.add(new JLabel("Study tuition adjustment:"));
            top.add(slTit());
            top.add(pTit());
        }
        return top;
    }
    private JPanel mainP() {
        if(main==null) {
            main = new JPanel(new java.awt.GridLayout(4, 2));
            main.add(new JLabel("Expected Fees: "));
            main.add(lFees());
            main.add(new JLabel("Expected upcoming tuitions"));
            main.add(lExpFees());
            main.add(new JLabel("<html>Students that can't<br>pay this year</html>"));
            main.add(cantPay_upcoming());
            main.add(new JLabel("<html>Students that can't<br>pay to end study</html>"));
            main.add(cantPay_longterm());
        }
        return main;
    }
    private JPanel toolP() {
        if(tool==null) {
            tool = new JPanel(new java.awt.FlowLayout());
            tool.add(btClose());
        }
        return tool;
    }
    //topP
    private JSlider slTit() {
        if(slTit==null) {
            slTit = new JSlider(100,1000,dsk.getRes().getVariables().getStudyFee());
            slTit.setMinorTickSpacing(100);
            slTit.setMajorTickSpacing(250);
            slTit.setPaintTicks(true);
            slTit.addChangeListener((ChangeEvent e) -> {
                tfTit.setText(slTit.getValue()+"");
                actualiseLabel();
            });
        }
        return slTit;
    }
    private JPanel pTit() {
        if(pTit==null) {
            pTit = new JPanel();
            pTit.add(new JLabel("Study tuition is "));
            pTit.add(tfTit());
            pTit.add(new JLabel("g/Year"));
            pTit.add(btTit());
        }
        return pTit;
    }
    private JTextField tfTit() {
        if(tfTit==null) {
            tfTit = new JTextField(String.valueOf(dsk.getRes().getVariables().getStudyFee()));
            tfTit.setPreferredSize(new java.awt.Dimension(35, 20));
            tfTit.addActionListener(inputTF());
        }
        return tfTit;
    }
    private JButton btTit() {
        if(btTit==null) {
            btTit = new JButton("Change Study tuition");
            btTit.addActionListener(changeTit());
        }
        return btTit;
    }
    private ActionListener inputTF() {
        ActionListener al = (ActionEvent e) -> {
            try {
                int newV = Integer.parseInt(tfTit.getText());
                if(newV<=1000 && newV>=100) {
                    slTit.setValue(newV);
                    actualiseLabel();
                } else if(newV>1000){
                    JOptionPane.showMessageDialog(null, "Maximum 1000g/Year!", "Maximum 1000g/Year!", JOptionPane.OK_OPTION);
                    tfTit.setText("1000");
                } else {
                    JOptionPane.showMessageDialog(null, "Minimum 100g/Year!", "Minimum 1000g/Year!", JOptionPane.OK_OPTION);
                    tfTit.setText("100");
                }
            } catch(NumberFormatException | HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Numbers Only!", "Numbers Only!", JOptionPane.OK_OPTION);
                tfTit.setText(slTit.getValue()+"");
            }
        };
        return al;
    }
    private ActionListener changeTit() {
        ActionListener al = (ActionEvent e) -> {
            try {
                int newV = Integer.parseInt(tfTit.getText());
                if(newV<=1000 && newV>=100) {
                    int mOK=JOptionPane.showConfirmDialog(null, "Really wanna change study tuition?", "Change stuff...", JOptionPane.YES_NO_OPTION);
                    if(mOK==JOptionPane.YES_NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Changes saved!", "Changes saved", JOptionPane.INFORMATION_MESSAGE);
                        slTit.setValue(newV);
                        dsk.getRes().getVariables().setStudyFee(newV);
                        actualiseLabel();
                    }
                } else if(newV>1000){
                    JOptionPane.showMessageDialog(null, "Maximum 1000g/Year!", "Maximum 1000g/Year!", JOptionPane.OK_OPTION);
                    slTit.setValue(1000);
                    tfTit.setText("1000");
                } else {
                    JOptionPane.showMessageDialog(null, "Minimum 100g/Year!", "Minimum 1000g/Year!", JOptionPane.OK_OPTION);
                    slTit.setValue(100);
                    tfTit.setText("100");
                }
            } catch(NumberFormatException | HeadlessException ex) {
                JOptionPane.showMessageDialog(null, "Numbers Only!", "Numbers Only!", JOptionPane.OK_OPTION);
                tfTit.setText(slTit.getValue()+"");
            }
        };
    return al;
    }
    //mainP
    private JLabel lFees() {
        if(lFees==null) {
            lFees = new JLabel(String.valueOf(dsk.getRes().getVariables().getStudyFee()*nrStud()));
        }
        return lFees;
    }
    private JLabel lExpFees() {
        if(lExpFees==null) {
            lExpFees = new JLabel(String.valueOf(expFees()));
        }
        return lExpFees;
    }
    private JLabel cantPay_upcoming() {
        if(lCantPay_upcoming==null) {
            lCantPay_upcoming = new JLabel(String.valueOf(getCantPay_upcoming()));
        }
        return lCantPay_upcoming;
    }
    private JLabel cantPay_longterm() {
        if(lCantPay_longterm==null) {
            lCantPay_longterm = new JLabel(String.valueOf(getCantPay_longterm()));
        }
        return lCantPay_longterm;
    }
    private long nrStud() {
        return dsk.getRes().getLStu().stream().filter(pStu -> !pStu.isFormer()).count();
    }
    private long getCantPay_upcoming() {
        return dsk.getRes().getLStu().stream().filter(pStu -> 
                pStu.getGold()<dsk.getRes().getVariables().getStudyFee()).count();
    }
    private long getCantPay_longterm() {
        return dsk.getRes().getLStu().stream().filter(pStu -> 
                pStu.getGold()<yearsLeft(pStu)).count();
    }
    private int expFees() {
        return (dsk.getRes().getLStu().size()+dsk.getRes().getVariables().getReputation()*3/4-(int)getCantPay_upcoming())*slTit.getValue();
    }
    private int yearsLeft(resources.Inhabitants.InhStu stu) {
        return dsk.getRes().getVariables().getStudyFee()*(dsk.getRes().getGoals().getDuration()-stu.getSemester());
    }
    private void actualiseLabel() {
        lFees.setText(String.valueOf(dsk.getRes().getVariables().getStudyFee()*nrStud()));
        lExpFees.setText(String.valueOf(expFees()));
        lCantPay_upcoming.setText(String.valueOf(getCantPay_upcoming()));
        lCantPay_longterm.setText(String.valueOf(getCantPay_longterm()));
    }
}
