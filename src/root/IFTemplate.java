/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author Die Axt
 */
public abstract class IFTemplate extends JInternalFrame {
    public IFTemplate(String title, boolean pdispose) {
        super(title, true,true,true,true);
        this.dispose=pdispose;  
    }
    public IFTemplate(String title) {
        super(title, true,true,true,true);
        this.dispose=false;
    }
    
    boolean dispose;
    JPanel topP = null;
    JPanel mainP = null;
    JPanel toolP = null;
    
    public void setIF(JPanel top, JPanel main, JPanel tool) {
        setTopP(top);
        setMainP(main);
        setToolP(tool);
        setup();
    }
    public void setIF(JPanel main, JPanel tool) {
        setMainP(main);
        setToolP(tool);
        setup();
    }
    private void setup() {
        if(dispose) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        }        
        this.setSize(400,400);
        this.setLayout(new java.awt.BorderLayout());
        if(topP!=null) {
            this.add(topP, BorderLayout.NORTH);            
        }
        if(mainP!=null) {
            this.add(mainP, BorderLayout.CENTER);            
        }
        if(toolP!=null) {
            this.add(toolP, BorderLayout.SOUTH);
        }
    }
    private void setTopP(JPanel p) {
        this.topP=p;
    }
    private void setMainP(JPanel p) {
        this.mainP=p;
    }
    private void setToolP(JPanel p) {
        this.toolP=p;
    }
    public JButton btClose() {
        JButton bt = new JButton("Close");
        bt.addActionListener((ActionEvent e) -> {
            if(dispose) {
                this.dispose();                
            } else {
                this.hide();
            }
        });
        return bt;
    }
}
