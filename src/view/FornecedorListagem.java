/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FornecedorJpaController;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Fornecedor;

/**
 *
 * @author Lucas
 */
public class FornecedorListagem extends javax.swing.JInternalFrame {

    /**
     * Creates new form FornecedorListagem
     */
    public FornecedorListagem() {
        initComponents();
        InterfaceUtils.preparaTela(this);
        List<Fornecedor> forn;
        FornecedorJpaController lancamentoController = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());
        forn = lancamentoController.getEntityManager().createNamedQuery("Fornecedor.findAll").getResultList();
        this.insereTabela(forn);
        
    }
    
     private void atualizaLista() {
       /* List<Fornecedor> forn = new FornecedorJpaController()
                .getEntityManager()
                .createNamedQuery("Fornecedor.findByAtivo")
                .setParameter("ativo", jAtivo.isSelected() ? 1 : 0)
                .getResultList();
        DefaultTableModel table = (DefaultTableModel) TabelaBusca.getModel();
        table.setRowCount(0);
        List<Object> buffer = new ArrayList<>();
        for (Fornecedor funcionario : forn) {//PAU NESSA LINHA
            buffer.clear();
            buffer.add(fornecedor.get());
            buffer.add(fornecedor.get());
            buffer.add(fornecedor.get());
            table.addRow(buffer.toArray());
        }
         */
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
        TabelaBusca = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        Visualizar = new javax.swing.JButton();
        jAtivo = new javax.swing.JCheckBox();

        setClosable(true);
        setTitle("Listagem Fornedor");

        TabelaBusca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CNPJ", "Razão", "Telefone"
            }
        ));
        jScrollPane1.setViewportView(TabelaBusca);

        jButton1.setText("Inserir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Visualizar.setText("Visualizar");
        Visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VisualizarActionPerformed(evt);
            }
        });

        jAtivo.setText("Ativo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Visualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jAtivo))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Visualizar)
                    .addComponent(jButton1)
                    .addComponent(jAtivo))
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VisualizarActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) TabelaBusca.getModel();  
            Fornecedor forn = (Fornecedor) model.getValueAt(TabelaBusca.getSelectedRow(), 1); //Pega o objeto na tabela
            new FornecedorCadastro(forn); //Abre a tela de cadastro com os dados do objeto pesquisado 
            this.dispose();
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Item selecionado inválido!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Um erro aconteceu!\n" + ex.getMessage());
        }
    }//GEN-LAST:event_VisualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new FornecedorCadastro();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void insereTabela(List<Fornecedor> data) {
        DefaultTableModel model = (DefaultTableModel) TabelaBusca.getModel();
        List<Object> dados = new ArrayList<>();
        for (Fornecedor o : data) {
            dados.add(o.getCnpj());
            dados.add(o);
            dados.add(o.getTelefone());
            model.addRow(dados.toArray());
            dados.clear();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaBusca;
    private javax.swing.JButton Visualizar;
    private javax.swing.JCheckBox jAtivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
