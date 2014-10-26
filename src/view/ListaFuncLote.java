/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FuncionarioDoLoteJpaController;
import controller.FuncionarioJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import model.FuncionarioDoLote;
import model.Lote;

/**
 *
 * @author Maicon
 */
public class ListaFuncLote extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaFuncLote
     */
    private Lote lote;
    private FuncionarioJpaController jpaf;
    private FuncionarioDoLoteJpaController jpafl;

    public ListaFuncLote(Lote plote) {
        initComponents();
        loadLote(plote);
        loadFuncionarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tfun = new javax.swing.JTable();
        bsalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Funcionários do Lote");

        tfun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "No Lote"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tfun);
        if (tfun.getColumnModel().getColumnCount() > 0) {
            tfun.getColumnModel().getColumn(0).setResizable(false);
            tfun.getColumnModel().getColumn(0).setPreferredWidth(20);
            tfun.getColumnModel().getColumn(1).setResizable(false);
            tfun.getColumnModel().getColumn(1).setPreferredWidth(150);
            tfun.getColumnModel().getColumn(2).setResizable(false);
            tfun.getColumnModel().getColumn(2).setPreferredWidth(20);
        }

        bsalvar.setText("Salvar");
        bsalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsalvarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Lote:");

        jLabel2.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bsalvar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bsalvar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bsalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsalvarActionPerformed
        DefaultTableModel model = (DefaultTableModel) tfun.getModel();

        List<FuncionarioDoLote> listToDestroy = lote.getFuncionarioDoLoteList();

        //Deletar tudo
        for (FuncionarioDoLote x : listToDestroy) {
            try {
                jpafl.destroy(x.getFuncionarioDoLotePK());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ListaFuncLote.class.getName()).log(Level.WARNING, " *** Nao encoontrado " + x.getFuncionario().getNome());
            }
        }

        int i;

        for (i = 0; i < model.getRowCount(); i++) {
            if ((Boolean) model.getValueAt(i, 2)) {
                Integer fs = (Integer) model.getValueAt(i, 0);
                Funcionario f = jpaf.findFuncionario(fs);
                FuncionarioDoLote fl = new FuncionarioDoLote();
                fl.setFuncionario(f);
                fl.setAtivo(Util.Util.boolToShort(true));
                fl.setLote(this.lote);
                try {
                    jpafl.create(fl);
                } catch (Exception ex) {
                    Logger.getLogger(ListaFuncLote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ListaLote ll = new ListaLote();
        Start.addFrame(ll);
        ll.setLocation(10, 10);
        ll.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bsalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bsalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tfun;
    // End of variables declaration//GEN-END:variables

    private void loadLote(Lote plote) {
        if (plote == null) {
            JOptionPane.showMessageDialog(rootPane, "Lote invalido");
        }
        this.lote = plote;

    }

    private void loadFuncionarios() {
        if (jpaf == null) {
            jpaf = new FuncionarioJpaController(ipsum2.Ipsum2.getFactory());
        }

        if (jpafl == null) {
            jpafl = new FuncionarioDoLoteJpaController(ipsum2.Ipsum2.getFactory());
        }
        Boolean entrou;
        List<Funcionario> lfun = jpaf.getAll();
        List<FuncionarioDoLote> lfl = this.lote.getFuncionarioDoLoteList();
        List<Object> dados = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) tfun.getModel();

        for (Funcionario f : lfun) {
            dados.add(f.getCodfunc());
            dados.add(f.getNome());
            entrou = false;
            for (FuncionarioDoLote fl : lfl) {
                if (f.equals(fl.getFuncionario())) {
                    dados.add(true);
                    entrou = true;
                }
            }
            if (!entrou) {
                dados.add(false);
            }
            model.addRow(dados.toArray());
            dados.clear();
        }

    }
}
