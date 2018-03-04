/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.admin;

import gui.Mainframe;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Die Axt
 */
public class IFGoals extends root.IFTemplate {
    
    public IFGoals(Mainframe pdsk) {
        super("Goals");
        this.dsk=pdsk;
        run();
    }
    
    Mainframe dsk = null;
    
    JPanel main = null;
    JPanel tool = null;
    
    JPanel pPhy = null;
    JPanel pMen = null;
    JPanel pSoc = null;
    JPanel pMag = null;
    JPanel pCombinedAttr = null;
    JPanel pYear = null;
    JPanel pNrGoals = null;
    
    JSlider slPhy = null;
    JSlider slMen = null;
    JSlider slSoc = null;
    JSlider slMag = null;
    JSlider slCombinedAttr = null;
    JSlider slYear = null;
    JSlider slNrGoals = null;
    
    JLabel lPhy = null;
    JLabel lMen = null;
    JLabel lSoc = null;
    JLabel lMag = null;
    JLabel lCombinedAttr = null;
    JLabel lYear = null;
    JLabel lNrGoals = null;
    
    JTextField tfPhy = null;
    JTextField tfMen = null;
    JTextField tfSoc = null;
    JTextField tfMag = null;
    JTextField tfCombinedAttr = null;
    JTextField tfYear = null;
    JTextField tfNrGoals = null;
    
    KeyAdapter kaPhy = null;
    KeyAdapter kaMen = null;
    KeyAdapter kaSoc = null;
    KeyAdapter kaMag = null;
    KeyAdapter kaCombinedAttr = null;
    KeyAdapter kaYear = null;
    KeyAdapter kaNrGoals = null;
    
    private void run() {
        setIF(pMain(),pTool());
    }
    
    private JPanel pMain() {
        if(main==null) {
            main = new JPanel(new java.awt.GridLayout(8, 2));
            main.add(new JLabel("Adapt Study-Goals:"));
            main.add(new JLabel(""));
            main.add(pPhy());
            main.add(slPhy());
            main.add(pMen());
            main.add(slMen());
            main.add(pSoc());
            main.add(slSoc());
            main.add(pMag());
            main.add(slMag());
            main.add(pCombinedAttr());
            main.add(slCombinedAttr());
            main.add(pYear());
            main.add(slYear());
            main.add(pNrGoals());
            main.add(slNrGoals());
        }
        return main;
    }
    private JPanel pTool() {
        if(tool==null) {
            tool = new JPanel(new java.awt.FlowLayout());
            tool.add(btClose());
        }
        return tool;
    }
    
    private JPanel pPhy() {
        if(pPhy==null) {
            pPhy = new JPanel(new java.awt.GridLayout(1,2));
            pPhy.add(lPhy());
            pPhy.add(tfPhy());
        }
        return pPhy;
    }
    private JPanel pMen() {
        if(pMen==null) {
            pMen = new JPanel(new java.awt.GridLayout(1,2));
            pMen.add(lMen());
            pMen.add(tfMen());
        }
        return pMen;
    }
    private JPanel pSoc() {
        if(pSoc==null) {
            pSoc=new JPanel(new java.awt.GridLayout(1,2));
            pSoc.add(lSoc());
            pSoc.add(tfSoc());
        }
        return pSoc;
    }
    private JPanel pMag() {
        if(pMag==null) {
            pMag=new JPanel(new java.awt.GridLayout(1,2));
            pMag.add(lMag());
            pMag.add(tfMag());
        }
        return pMag;
    }
    private JPanel pCombinedAttr() {
        if(pCombinedAttr==null) {
            pCombinedAttr = new JPanel(new java.awt.GridLayout(1, 2));
            pCombinedAttr.add(lCombinedAttr());
            pCombinedAttr.add(tfCombinedAttr());
        }
        return pCombinedAttr;
    }
    private JPanel pYear() {
        if(pYear==null) {
            pYear = new JPanel(new java.awt.GridLayout(1,2));
            pYear.add(lYear());
            pYear.add(tfYear());
        }
        return pYear;
    }
    private JPanel pNrGoals() {
        if(pNrGoals==null) {
            pNrGoals = new JPanel(new java.awt.GridLayout(1,2));
            pNrGoals.add(lNrGoals());
            pNrGoals.add(tfNrGoals());
        }
        return pNrGoals;
    }
    
    private JSlider slPhy() {
        if(slPhy==null) {
            slPhy=new JSlider(0,200,dsk.getData().phyVal);
            slPhy.setMajorTickSpacing(50);
            slPhy.setMinorTickSpacing(25);
            slPhy.setPaintTicks(true);
            slPhy.addChangeListener((ChangeEvent e) -> {
                int newV = slPhy.getValue();
                dsk.getData().phyVal = newV;
                tfPhy.setText(newV+"");
            });
        }
        return slPhy;
    }
    private JSlider slMen() {
        if(slMen==null) {
            slMen=new JSlider(0,200,dsk.getData().menVal);
            slMen.setMajorTickSpacing(100);
            slMen.setMinorTickSpacing(25);
            slMen.setPaintTicks(true);
            slMen.addChangeListener((ChangeEvent e) -> {
                int newV=slMen.getValue();
                dsk.getData().menVal = newV;
                tfMen.setText(newV+"");
            });
        }
        return slMen;
    }
    private JSlider slSoc() {
        if(slSoc==null) {
            slSoc=new JSlider(0,200,dsk.getData().socVal);
            slSoc.setMajorTickSpacing(100);
            slSoc.setMinorTickSpacing(25);
            slSoc.setPaintTicks(true);
            slSoc.addChangeListener((ChangeEvent e) -> {
                int newV=slSoc.getValue();
                dsk.getData().socVal = newV;
                tfSoc.setText(newV+"");
            });
        }
        return slSoc;
    }
    private JSlider slMag() {
        if(slMag==null) {
            slMag=new JSlider(0,200,dsk.getData().magVal);
            slMag.setMajorTickSpacing(100);
            slMag.setMinorTickSpacing(25);
            slMag.setPaintTicks(true);
            slMag.addChangeListener((ChangeEvent e) -> {
                int newV=slMag.getValue();
                dsk.getData().magVal = newV;
                tfMag.setText(newV+"");
            });
        }
        return slMag;
    }
    private JSlider slCombinedAttr() {
        if(slCombinedAttr==null) {
            slCombinedAttr = new JSlider(0,800,dsk.getData().total);
            slCombinedAttr.setMajorTickSpacing(200);
            slCombinedAttr.setMinorTickSpacing(100);
            slCombinedAttr.setPaintTicks(true);
            slCombinedAttr.addChangeListener((ChangeEvent e) -> {
                int newV = slCombinedAttr.getValue();
                dsk.getData().total = newV;
                tfCombinedAttr.setText(newV+"");
            });
        }
        return slCombinedAttr;
    }
    private JSlider slYear() {
        if(slYear==null) {
            slYear = new JSlider(1,10,dsk.getData().duration);
            slYear.setMajorTickSpacing(1);
            slYear.setMinorTickSpacing(1);
            slYear.setPaintTicks(true);
            slYear.addChangeListener((ChangeEvent e) -> {
                int newV = slYear.getValue();
                tfYear.setText(newV+"");
                dsk.getData().duration = newV;
            });
        }
        return slYear;
    }
    private JSlider slNrGoals() {
        if(slNrGoals==null) {
            slNrGoals = new JSlider(0,6,dsk.getData().nrGoals);
            slNrGoals.setMajorTickSpacing(1);
            slNrGoals.setMinorTickSpacing(1);
            slNrGoals.setPaintTicks(true);
            slNrGoals.addChangeListener((ChangeEvent e) -> {
                int newV = slNrGoals.getValue();
                tfNrGoals.setText(newV+"");
                dsk.getData().nrGoals = newV;
            });
        }
        return slNrGoals;
    }
    
    private JLabel lPhy() {
        if(lPhy==null) {
            lPhy = new JLabel("Physical Goal: ");
        }
        return lPhy;
    }
    private JLabel lMen() {
        if(lMen==null) {
            lMen=new JLabel("Mental Goal: ");
        }
        return lMen;
    }
    private JLabel lSoc() {
        if(lSoc==null) {
            lSoc=new JLabel("Social goal: ");
        }
        return lSoc;
    }
    private JLabel lMag() {
        if(lMag==null) {
            lMag=new JLabel("Magic goal: ");
        }
        return lMag;
    }
    private JLabel lCombinedAttr() {
        if(lCombinedAttr==null) {
            lCombinedAttr = new JLabel("Total goal: ");
        }
        return lCombinedAttr;
    }
    private JLabel lYear() {
        if(lYear==null) {
            lYear = new JLabel("Year goal: ");
        }
        return lYear;
    }
    private JLabel lNrGoals() {
        if(lNrGoals==null) {
            lNrGoals = new JLabel("Number goals: ");
            lNrGoals.setToolTipText("<html><body>The number of goals a student has to achieve<br> to finish his studies</body></html>");
        }
        return lNrGoals;
    }
    
    private JTextField tfPhy() {
        if(tfPhy==null) {
            tfPhy = new JTextField(String.valueOf(dsk.getData().phyVal));
            tfPhy.addKeyListener(kaPhy());
        }
        return tfPhy;
    }
    private JTextField tfMen() {
        if(tfMen==null) {
            tfMen = new JTextField(String.valueOf(dsk.getData().menVal));
            tfMen.addKeyListener(kaMen());
        }
        return tfMen;
    }
    private JTextField tfSoc() {
        if(tfSoc==null) {
            tfSoc = new JTextField(String.valueOf(dsk.getData().socVal));
            tfSoc.addKeyListener(kaSoc());
        }
        return tfSoc;
    }
    private JTextField tfMag() {
        if(tfMag==null) {
            tfMag = new JTextField(String.valueOf(dsk.getData().magVal));
            tfSoc.addKeyListener(kaMag());
        }
        return tfMag;
    }
    private JTextField tfCombinedAttr() {
        if(tfCombinedAttr==null) {
            tfCombinedAttr=new JTextField(String.valueOf(dsk.getData().total));
            tfCombinedAttr.addKeyListener(kaCombinedAttr());
        }
        return tfCombinedAttr;
    }
    private JTextField tfYear() {
        if(tfYear==null) {
            tfYear = new JTextField(String.valueOf(dsk.getData().duration));
            tfYear.addKeyListener(kaYear());
        }
        return tfYear;
    }
    private JTextField tfNrGoals() {
        if(tfNrGoals==null) {
            tfNrGoals = new JTextField(String.valueOf(dsk.getData().nrGoals));
            tfNrGoals.setToolTipText("The number of goals that a student has to achieve to finish the studys.");
            tfNrGoals.addKeyListener(kaNrGoals());
        }
        return tfNrGoals;
    }
    
    private KeyAdapter kaPhy() {
        if(kaPhy==null) {
            kaPhy = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try {
                        newV = Integer.parseInt(tfPhy.getText());
                    } catch(NumberFormatException ex) {
                        newV = 0;
                    }
                    slPhy.setValue(newV);
                    dsk.getData().phyVal = newV;
                }
            };
        }
        return kaPhy;
    }
    private KeyAdapter kaMen() {
        if(kaMen==null) {
            kaMen = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try{
                        newV = Integer.parseInt(tfMen.getText());
                    } catch(NumberFormatException ex) {
                        newV=0;
                    }
                    slMen.setValue(newV);
                    dsk.getData().menVal = newV;
                }
            };
        }
        return kaMen;
    }
    private KeyAdapter kaSoc() {
        if(kaSoc==null) {
            kaSoc=new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try {
                        newV = Integer.parseInt(tfSoc.getText());
                    } catch(NumberFormatException ex) {
                        newV=0;
                    }
                    slSoc.setValue(newV);
                    dsk.getData().socVal = newV;
                }
            };
        }
        return kaSoc;
    }
    private KeyAdapter kaMag() {
        if(kaMag==null) {
            kaMag=new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try {
                        newV = Integer.parseInt(tfMag.getText());
                    } catch(NumberFormatException ex) {
                        newV=0;
                    }
                    slMag.setValue(newV);
                    dsk.getData().magVal = newV;
                }
            };
        }
        return kaMag;
    }
    private KeyAdapter kaCombinedAttr() {
        if(kaCombinedAttr==null) {
            kaCombinedAttr = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try {
                        newV = Integer.parseInt(tfCombinedAttr.getText());
                    } catch(NumberFormatException ex) {
                        newV=0;
                    }
                    slCombinedAttr.setValue(newV);
                    dsk.getData().total = newV;
                }
            };
        }
        return kaCombinedAttr;
    }
    private KeyAdapter kaYear() {
        if(kaYear==null) {
            kaYear = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try {
                        newV = Integer.parseInt(tfYear.getText());
                    } catch(NumberFormatException ex) {
                        newV=0;
                    }
                    slYear.setValue(newV);
                    dsk.getData().duration = newV;
                }
            };
        }
        return kaYear;
    }
    private KeyAdapter kaNrGoals() {
        if(kaNrGoals==null) {
            kaNrGoals = new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    int newV;
                    try {
                        newV = Integer.parseInt(tfNrGoals.getText());
                    } catch(NumberFormatException ex) {
                        newV=0;
                    }
                    slNrGoals.setValue(newV);
                    dsk.getData().nrGoals = newV;
                }
            };
        }
        return kaNrGoals;
    }
    
}
