/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class InterfaceUtils {
    public static void insereTable(JTable table, Object[][] dados) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (Object[] linha : dados) {
            model.addRow(linha);
        }
    }
}
