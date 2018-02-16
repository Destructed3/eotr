/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Die Axt
 */
public class TableModel_Uneditable extends DefaultTableModel {
    public TableModel_Uneditable(Object[][] dat, Object[] days) {
        super(dat,days);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; 
    }
    
}
