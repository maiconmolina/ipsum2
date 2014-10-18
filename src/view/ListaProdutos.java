
package view;

import static Util.Util.doubleDuasCasasDecimais;
import controller.ProdutoJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Produto;

public class ListaProdutos extends javax.swing.JInternalFrame {

    public static List<Produto> listProdutosAtivos = null;
    public static List<Produto> listProdutos = null;

    public ListaProdutos() {
        initComponents();
        InterfaceUtils.preparaTela(this);

        ProdutoJpaController produtoController = new ProdutoJpaController(ipsum2.Ipsum2.getFactory());
        this.listProdutos = produtoController.getEntityManager().createNamedQuery("Produto.findAll").getResultList();
        this.listProdutosAtivos = produtoController.getEntityManager().createNamedQuery("Produto.findByAtivo").setParameter("ativo", 1).getResultList();
        insereTabela(this.listProdutosAtivos);
    }

    public ListaProdutos(Produto p) {
        initComponents();
        InterfaceUtils.preparaTela(this);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        btNovo = new javax.swing.JButton();
        btAltera = new javax.swing.JButton();
        btAltera1 = new javax.swing.JButton();
        jAtivo2 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos");

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço", "Ativo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabela);

        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btAltera.setText("Alterar");
        btAltera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlteraActionPerformed(evt);
            }
        });

        btAltera1.setText("Materiais");
        btAltera1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAltera1ActionPerformed(evt);
            }
        });

        jAtivo2.setSelected(true);
        jAtivo2.setText("Ativos");
        jAtivo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAtivo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAltera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jAtivo2)
                        .addGap(27, 27, 27)
                        .addComponent(btAltera1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovo)
                    .addComponent(btAltera)
                    .addComponent(btAltera1)
                    .addComponent(jAtivo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
        new CadastroProdutos();
        this.dispose();;
    }//GEN-LAST:event_btNovoActionPerformed

    private void btAlteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlteraActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
            Produto p = (Produto) model.getValueAt(Tabela.getSelectedRow(), 1); //Pega o objeto na tabela
            new CadastroProdutos(p);
            this.dispose();
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Item selecionado inválido!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Um erro aconteceu!\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btAlteraActionPerformed

    private void btAltera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAltera1ActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
            Produto prod = (Produto) model.getValueAt(Tabela.getSelectedRow(), 1); //Pega o objeto na tabela
            new CadastroProdutoMaterial(prod);
            this.dispose();
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Item selecionado inválido!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Um erro aconteceu!\n" + ex.getMessage());
        }
    }//GEN-LAST:event_btAltera1ActionPerformed

    private void jAtivo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAtivo2ActionPerformed
        if (jAtivo2.isSelected() == true) {
            this.insereTabela(this.listProdutosAtivos);
        } else {
            this.insereTabela(this.listProdutos);
        }
    }//GEN-LAST:event_jAtivo2ActionPerformed


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
            java.util.logging.Logger.getLogger(ListaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaProdutos().setVisible(true);
            }
        });
    }

    private void insereTabela(List<Produto> data) {
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.setRowCount(0);
        List<Object> dados = new ArrayList<>();
        for (Produto o : data) {
            dados.add(o.getCodprod());
            dados.add(o);
            dados.add("R$ " + String.valueOf(doubleDuasCasasDecimais(o.getPreco())).replace(".", ","));
            dados.add((o.getAtivo() == 1));
            model.addRow(dados.toArray());
            dados.clear();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btAltera;
    private javax.swing.JButton btAltera1;
    private javax.swing.JButton btNovo;
    private javax.swing.JCheckBox jAtivo2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
