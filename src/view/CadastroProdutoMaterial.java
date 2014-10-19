/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.MaterialDoProdutoJpaController;
import controller.MaterialJpaController;
import controller.ProdutoJpaController;
import controller.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Material;
import model.MaterialDoProduto;
import model.Produto;

/**
 *
 * @author Luis
 */
public class CadastroProdutoMaterial extends javax.swing.JInternalFrame {

    private ProdutoJpaController produtoController = new ProdutoJpaController(ipsum2.Ipsum2.getFactory());
    private MaterialJpaController materialController = new MaterialJpaController(ipsum2.Ipsum2.getFactory());
    private MaterialDoProdutoJpaController materialProdController = new MaterialDoProdutoJpaController(ipsum2.Ipsum2.getFactory());
    private Produto prod = null;

    /**
     * Creates new form CadastroProdutoMaterial
     */
    public CadastroProdutoMaterial() {
        initComponents();
        InterfaceUtils.preparaTela(this);

    }

    public CadastroProdutoMaterial(Produto prod) {
        initComponents();
        InterfaceUtils.preparaTela(this);
        this.prod = prod;

        produto.setText(prod.getDescricao());
        List<Material> listMat = this.materialController.getAtivos();
        insereTabela(listMat);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        produto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        btnSalvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Material do produto");

        jLabel1.setText("Produto:");

        produto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição","Quantidade" ,"Pertence ao produto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabela);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel2.setText("Materiais");

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(produto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 436, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(produto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(jButton2))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        MaterialDoProduto matProd = new MaterialDoProduto();
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        List<MaterialDoProduto> listMatProd = this.materialProdController.getMateriaisDoProduto(this.prod);
        if (listMatProd != null) {
            for (MaterialDoProduto mp : listMatProd) {
                try {
                    this.materialProdController.destroy(mp.getMaterialDoProdutoPK());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(CadastroProdutoMaterial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        int i;
        matProd.setProduto(this.prod);
        for (i = 0; i < model.getRowCount(); i++) {
            if ((boolean) model.getValueAt(i, 3) == true) {
                matProd.setMaterial(this.materialController.getById((Integer) model.getValueAt(i, 0)));
                matProd.setQtde(Integer.valueOf(model.getValueAt(i, 2).toString()));
                try {
                    this.materialProdController.create(matProd);
                } catch (Exception ex) {
                    Logger.getLogger(CadastroProdutoMaterial.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.dispose();
        new ListaProdutos();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        new ListaProdutos();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroProdutoMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutoMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutoMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProdutoMaterial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroProdutoMaterial().setVisible(true);
            }
        });
    }

    private void insereTabela(List<Material> data) {
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.setRowCount(0);
        List<Object> dados = new ArrayList<>();
        for (Material o : data) {
            dados.add(o.getCodmat());
            dados.add(o);
            boolean selecionado = false;
            List<MaterialDoProduto> listMatP = this.materialProdController.getMateriaisAtivosDoProduto(this.prod);
            for (MaterialDoProduto lm : listMatP) {
                if (lm.getMaterial().equals(o)) {
                    dados.add(lm.getQtde().toString());
                    dados.add(true);
                    selecionado = true;
                    break;
                }
            }
            if (selecionado != true) {
                dados.add(0);
                dados.add(false);
            }
            model.addRow(dados.toArray());
            dados.clear();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel produto;
    // End of variables declaration//GEN-END:variables
}
