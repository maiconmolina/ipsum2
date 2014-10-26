/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

//import com.sun.org.apache.bcel.internal.generic.AALOAD;
import controller.LoteJpaController;
import controller.SituacaoLoteJpaController;
import enuns.Permissoes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.event.PrintJobEvent;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Funcionario;
import model.Lote;
import model.SituacaoLote;

/**
 *
 * @author Maicon
 */
public class ListaLote extends javax.swing.JInternalFrame {

    /**
     * Creates new form ListaLote
     */
    private LoteJpaController jpa;
    private Lote lote;
    
    public ListaLote() {
        initComponents();
        jpa = new LoteJpaController(ipsum2.Ipsum2.getFactory());
        loadSit();
        loadLotes();
    }

    private void loadLotes() {
        List<Lote> llotes = jpa.findLoteEntities();
        DefaultTableModel model = (DefaultTableModel) lotes.getModel();
        int i;
        
        for (i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
        }
        
        List<Object> dados = new ArrayList<>();
        for (Lote lote : llotes) {
            //JOptionPane.showMessageDialog(rootPane, llotes.size());
            if (lote.getSitlote().equals((SituacaoLote) situacoes.getSelectedItem())) {
                dados.add(lote.getCodlote());
                dados.add(lote.getCodfornec().getRazao());
                dados.add(lote.getSitlote());

                model.addRow(dados.toArray());
                dados.clear();
            }

        }
    }

    private void loadSit() {
        SituacaoLoteJpaController sitc = new SituacaoLoteJpaController(ipsum2.Ipsum2.getFactory());
        List<SituacaoLote> listSit = sitc.findSituacaoLoteEntities();
        if (listSit.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Situações do lote não foram cadastradas!");
            bnovo.setEnabled(false);
        }
        ComboBoxModel combo = new DefaultComboBoxModel(listSit.toArray());
        situacoes.setModel(combo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        lotes = new javax.swing.JTable();
        bnovo = new javax.swing.JButton();
        baltera = new javax.swing.JButton();
        situacoes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        bprodutos = new javax.swing.JButton();
        bfunc = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lotes");

        lotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lote", "Fornecedor", "Situação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lotesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lotes);
        if (lotes.getColumnModel().getColumnCount() > 0) {
            lotes.getColumnModel().getColumn(0).setMinWidth(80);
            lotes.getColumnModel().getColumn(0).setPreferredWidth(80);
            lotes.getColumnModel().getColumn(0).setMaxWidth(80);
            lotes.getColumnModel().getColumn(2).setMinWidth(120);
            lotes.getColumnModel().getColumn(2).setPreferredWidth(120);
            lotes.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        bnovo.setText("Novo");
        bnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnovoActionPerformed(evt);
            }
        });

        baltera.setText("Alterar");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lotes, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), baltera, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        baltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balteraActionPerformed(evt);
            }
        });

        situacoes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                situacoesKeyReleased(evt);
            }
        });

        jLabel1.setText("Situação:");

        bprodutos.setText("Produtos");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lotes, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), bprodutos, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        bprodutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprodutosActionPerformed(evt);
            }
        });

        bfunc.setText("Funcionários");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lotes, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), bfunc, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        bfunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bfuncActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bnovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(baltera)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bprodutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bfunc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(situacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(situacoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bprodutos)
                        .addComponent(bfunc)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baltera)
                    .addComponent(bnovo))
                .addContainerGap())
        );

        if (!Funcionario.permite(Permissoes.INSERIR_LOTE)){
            bnovo.setVisible(false);
        }

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnovoActionPerformed
        CadastroLote cl = new CadastroLote();
        Start.addFrame(cl);
        cl.setLocation(10, 10);
        cl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bnovoActionPerformed

    private void balteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balteraActionPerformed
        if (this.lote == null) {
            return;
        }
        CadastroLote cl = new CadastroLote(lote);
        Start.addFrame(cl);
        cl.setLocation(10, 10);
        cl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_balteraActionPerformed

    private void lotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lotesMouseClicked
        try {
            this.lote = jpa.findLote((Integer) lotes.getModel().getValueAt(lotes.getSelectedRow(), 0));
        } catch (Exception ex) {
            Logger.getLogger(CadastroLote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lotesMouseClicked

    private void bprodutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprodutosActionPerformed
        if (this.lote == null) {
            return;
        }
        CadastroProdutosLote cpl = new CadastroProdutosLote(lote);
        Start.addFrame(cpl);
        cpl.setLocation(10, 10);
        cpl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bprodutosActionPerformed

    private void bfuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bfuncActionPerformed
        if (this.lote == null){
            return;
        }
        ListaFuncLote lfl = new ListaFuncLote(this.lote);
        Start.addFrame(lfl);
        lfl.setLocation(10, 10);
        lfl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bfuncActionPerformed

    private void situacoesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_situacoesKeyReleased
        loadLotes();
    }//GEN-LAST:event_situacoesKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaLote.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaLote().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baltera;
    private javax.swing.JButton bfunc;
    private javax.swing.JButton bnovo;
    private javax.swing.JButton bprodutos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable lotes;
    private javax.swing.JComboBox situacoes;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
