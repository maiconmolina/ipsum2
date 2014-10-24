/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.DecimalFormattedField;
import static Util.Util.DateToString2;
import static Util.Util.doubleDuasCasasDecimais;
import controller.CaixaJpaController;
import controller.LancamentoJpaController;
import enuns.Permissoes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Caixa;
import model.Funcionario;
import model.Lancamento;

/**
 *
 * @author Luis
 */
public class TelaCaixa extends javax.swing.JInternalFrame {

    public static Caixa caixa = null;
    public static List<Lancamento> listLancamentosAtivos = null;
    public static List<Lancamento> listLancamentos = null;
    public static double saldoFinal = 0.0;

    /**
     * Creates new form TelaCaixa
     */
    public TelaCaixa() {
        initComponents();
        InterfaceUtils.preparaTela(this);

        statusCaixa.setText("Aberto");
        alterar.setEnabled(true);
        novoLanc.setEnabled(true);
        List<Lancamento> lancamentos;
        LancamentoJpaController lancamentoController = new LancamentoJpaController(ipsum2.Ipsum2.getFactory());
        lancamentos = lancamentoController.getEntityManager().createNamedQuery("Lancamento.findAll").getResultList();
        this.listLancamentos = lancamentos;
        lancamentos = lancamentoController.getEntityManager().createNamedQuery("Lancamento.findByEstorno").setParameter("estorno", (short) 0).getResultList();
        this.listLancamentosAtivos = lancamentos;
        double saldoCaixa = 0.0;
        for (Lancamento l : lancamentos) {
            if (l.getLancamentoEntrada() != null && l.getEstorno() == 0) {
                saldoCaixa += l.getValor();
            }
            if (l.getLancamentoSaida() != null && l.getEstorno() == 0) {
                saldoCaixa -= l.getValor();
            }
            if (l.getLancamentoRecforn() != null && l.getEstorno() == 0) {
                saldoCaixa -= l.getValor();
            }
            if (l.getLancamentoPagfunc() != null && l.getEstorno() == 0) {
                saldoCaixa -= l.getValor();
            }
        }
        saldo.setText("R$ " + String.valueOf(saldoCaixa).replace(".", ","));
        this.saldoFinal = saldoCaixa;
        Caixa caixa = InitCaixa();
        this.caixa = caixa;
        this.insereTabela(lancamentos);

    }

    private Caixa InitCaixa() {
        List<Caixa> caixas;
        Caixa caixa;
        CaixaJpaController caixaController = new CaixaJpaController(ipsum2.Ipsum2.getFactory());
        caixas = caixaController.getEntityManager().createNamedQuery("Caixa.findAll").getResultList();
        for (Caixa c : caixas) {
            caixa = c;
            if (caixa.getCodcaixa() == 1) {
                return caixa;
            }
        }
        caixa = new Caixa();
        caixa.setCodcaixa(1);
        caixa.setSaldo(this.saldoFinal);
        caixa.setStatus(Short.parseShort("1"));
        try {
            caixaController.create(caixa);
        } catch (Exception ex) {
            Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caixa;

    }

    private void insereTabela(List<Lancamento> data) {
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.setRowCount(0);
        List<Object> dados = new ArrayList<>();
        for (Lancamento o : data) {
            dados.add(o.getCodlanc());
            dados.add(o);
            if (o.getLancamentoSaida() != null) {
                dados.add(o.getLancamentoSaida());
            } else if (o.getLancamentoEntrada() != null) {
                dados.add(o.getLancamentoEntrada());
            } else if (o.getLancamentoPagfunc() != null) {
                dados.add(o.getLancamentoPagfunc());
            } else if (o.getLancamentoRecforn() != null) {
                dados.add(o.getLancamentoRecforn());
            }
            DecimalFormattedField val = new DecimalFormattedField(DecimalFormattedField.REAL);

            dados.add("R$ " + String.valueOf(doubleDuasCasasDecimais(o.getValor())).replace(".", ","));
            if (o.getEstorno() == 0) {
                dados.add("Não");
            } else {
                dados.add("Estornado");
            }
            if (o.getLancamentoEntrada() != null) {
                dados.add(DateToString2(o.getLancamentoEntrada().getData()));
            }
            if (o.getLancamentoSaida() != null) {
                dados.add(DateToString2(o.getLancamentoSaida().getData()));
            }

            if (o.getLancamentoRecforn() != null) {
                dados.add(DateToString2(o.getLancamentoRecforn().getData()));
            }

            model.addRow(dados.toArray());
            dados.clear();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        novoLanc = new javax.swing.JButton();
        alterar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        statusCaixa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        saldo = new javax.swing.JLabel();
        checkEstorno = new javax.swing.JCheckBox();

        jButton1.setText("Novo Lançamento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Alterar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("Abrir/Fechar Caixa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setText("jLabel3");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Caixa");

        novoLanc.setText("Novo Lançamento");
        novoLanc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoLancActionPerformed(evt);
            }
        });

        alterar.setText("Alterar");
        alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarActionPerformed(evt);
            }
        });

        jButton6.setText("Abrir/Fechar Caixa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código","Descrição", "Tipo", "Valor", "Estorno","Data"
            }
        ));
        Tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        Tabela.getColumnModel().getColumn(1).setPreferredWidth(300);
        Tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
        Tabela.getColumnModel().getColumn(4).setPreferredWidth(90);
        jScrollPane1.setViewportView(Tabela);

        jLabel1.setText("Status: ");

        statusCaixa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel2.setText("Saldo:");

        saldo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        checkEstorno.setText("Ver estornados");
        checkEstorno.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkEstornoStateChanged(evt);
            }
        });
        checkEstorno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEstornoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(novoLanc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alterar)
                        .addGap(18, 18, 18)
                        .addComponent(checkEstorno))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusCaixa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saldo)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addContainerGap())))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(383, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(statusCaixa)
                    .addComponent(jLabel2)
                    .addComponent(saldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novoLanc)
                    .addComponent(jButton6)
                    .addComponent(alterar)
                    .addComponent(checkEstorno))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(93, Short.MAX_VALUE)))
        );

        if (!Funcionario.permite(Permissoes.LANCAMENTO_CAIXA)){
            novoLanc.setVisible(false);
        }

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void novoLancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoLancActionPerformed
        this.dispose();
        new TelaLancamento();
    }//GEN-LAST:event_novoLancActionPerformed

    private void alterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
            Lancamento lanc = (Lancamento) model.getValueAt(Tabela.getSelectedRow(), 1); //Pega o objeto na tabela
            new TelaLancamento(lanc);
            this.dispose();
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Item selecionado inválido!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Um erro aconteceu!\n" + ex.getMessage());
        }
    }//GEN-LAST:event_alterarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Caixa caixa = this.caixa;
        CaixaJpaController caixaController = new CaixaJpaController(ipsum2.Ipsum2.getFactory());
        if (caixa.getStatus() == 1) {
            caixa.setStatus(0);
            statusCaixa.setText("Fechado");
            alterar.setEnabled(false);
            novoLanc.setEnabled(false);

        } else {
            caixa.setStatus(1);
            statusCaixa.setText("Aberto");
            alterar.setEnabled(true);
            novoLanc.setEnabled(true);
        }
        try {
            caixaController.edit(caixa);
        } catch (Exception ex) {
            Logger.getLogger(TelaCaixa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void checkEstornoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkEstornoStateChanged

    }//GEN-LAST:event_checkEstornoStateChanged

    private void checkEstornoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEstornoActionPerformed
        if (checkEstorno.isSelected() == true) {
            this.insereTabela(this.listLancamentos);
        } else {
            this.insereTabela(this.listLancamentosAtivos);
        }
    }//GEN-LAST:event_checkEstornoActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton alterar;
    private javax.swing.JCheckBox checkEstorno;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton novoLanc;
    private javax.swing.JLabel saldo;
    private javax.swing.JLabel statusCaixa;
    // End of variables declaration//GEN-END:variables
}
