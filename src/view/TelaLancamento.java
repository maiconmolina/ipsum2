/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.DecimalFormattedField;
import controller.CaixaJpaController;
import controller.LancamentoEntradaJpaController;
import controller.LancamentoJpaController;
import controller.LancamentoSaidaJpaController;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import enuns.EnumTipoDeLancamento;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import model.Caixa;
import model.Lancamento;
import model.LancamentoEntrada;
import model.LancamentoPagfunc;
import model.LancamentoRecforn;
import model.LancamentoSaida;

/**
 *
 * @author Luis
 */
public class TelaLancamento extends javax.swing.JInternalFrame {

    private Lancamento editandoLanc = null;

    /**
     * Creates new form TelaLancamento
     */
    public TelaLancamento() {

        initComponents();
        InterfaceUtils.preparaTela(this);
        int MaxIdLanc = ultimoLancId();
        codigo.setText(String.valueOf(MaxIdLanc));
        estorno.setEnabled(false);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dataAgora.setText(dateFormat.format(date));
    }

    public TelaLancamento(Lancamento lanc) {
        initComponents();
        InterfaceUtils.preparaTela(this);

        this.editandoLanc = lanc;
        estorno.setEnabled(true);

        descricao.setText(lanc.getDescricao());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        if (lanc.getLancamentoEntrada() != null) {
            tipo.setSelectedIndex(0);
            date = lanc.getLancamentoEntrada().getData();
        }
        if (lanc.getLancamentoSaida() != null) {
            date = lanc.getLancamentoSaida().getData();
            tipo.setSelectedIndex(1);
        }

        dataAgora.setText(dateFormat.format(date));
        if (lanc.getEstorno() == 0) {
            estorno.setSelectedIndex(1);
        }
        if (lanc.getEstorno() == 1) {
            estorno.setSelectedIndex(0);
        }

        DecimalFormattedField val = new DecimalFormattedField(DecimalFormattedField.REAL);
        val.setValue(lanc.getValor());
//        JOptionPane.showMessageDialog(this, val.getText());
        valor.setText(lanc.getValor().toString());
        codigo.setText(lanc.getCodlanc().toString());
    }

    private int ultimoLancId() {
        List<Lancamento> lanc;
        Lancamento l;
        LancamentoJpaController LancController = new LancamentoJpaController(ipsum2.Ipsum2.getFactory());
        lanc = LancController.getEntityManager().createNamedQuery("Lancamento.findAll").getResultList();
        if (!lanc.isEmpty()) {
            l = lanc.get(lanc.size() - 1);
            if (l.getCodlanc() > 0) {
                return l.getCodlanc() + 1;
            }
        }
        return 1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        descricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();
        valor =   valor = new DecimalFormattedField(DecimalFormattedField.REAL);
        //new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        codigo = new javax.swing.JTextField();
        excluir = new javax.swing.JButton();
        salvar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        estorno = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        dataAgora = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lançamento");

        jLabel1.setText("Descrição:");

        descricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descricaoActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });
        for(EnumTipoDeLancamento t : EnumTipoDeLancamento.values()){
            this.tipo.addItem(t);

        }

        valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor:");

        jLabel4.setText("Código:");

        codigo.setEditable(false);

        excluir.setText("Voltar");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        salvar.setText("Salvar");
        salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });

        jLabel5.setText("Estorno:");

        estorno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sim", "Não" }));

        jLabel6.setText("Data:");

        dataAgora.setEditable(false);
        dataAgora.setText("00/00/0000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(estorno, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                                .addComponent(excluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salvar)))
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(tipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(descricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                        .addComponent(valor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dataAgora, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dataAgora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(estorno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excluir)
                    .addComponent(salvar))
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void descricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_descricaoActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        this.dispose();
        new TelaCaixa();
    }//GEN-LAST:event_excluirActionPerformed

    private void salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarActionPerformed

        List<Caixa> caixa = null;
        CaixaJpaController CC = new CaixaJpaController(ipsum2.Ipsum2.getFactory());
        caixa = CC.getEntityManager().createNamedQuery("Caixa.findByCodcaixa").setParameter("codcaixa", 1).getResultList();

        //Consulta pra ver se nao esta editando
        Lancamento lanc = null;
        if (this.editandoLanc != null) {
            lanc = this.editandoLanc;

        } else {
            lanc = new Lancamento();
        }
        //Consulta pra ver se nao esta editando

        for (Caixa c : caixa) {
            lanc.setCodcaixa(c);
        }
        lanc.setDescricao(descricao.getText());

        lanc.setCodlanc(Integer.parseInt(codigo.getText()));
        DecimalFormattedField val = new DecimalFormattedField(DecimalFormattedField.REAL);
        lanc.setValor(val.converteDouble(valor.getText()));

        LancamentoJpaController lancController = new LancamentoJpaController(ipsum2.Ipsum2.getFactory());
        LancamentoEntradaJpaController lancEntrController = new LancamentoEntradaJpaController(ipsum2.Ipsum2.getFactory());
        LancamentoSaidaJpaController lancSaidController = new LancamentoSaidaJpaController(ipsum2.Ipsum2.getFactory());
        Date gambData = null;

        if (this.editandoLanc != null) {
            if (estorno.getSelectedIndex() == 0) {
                lanc.setEstorno((short) 1);
            } else {
                lanc.setEstorno((short) 0);
            }
            try {
                lancController.edit(lanc);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (lanc.getLancamentoEntrada() != null) {
                gambData = lanc.getLancamentoEntrada().getData();
                try {
                    lancEntrController.destroy(lanc.getLancamentoEntrada().getCodlanc());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                lanc.setLancamentoEntrada(null);
            }
            if (lanc.getLancamentoSaida() != null) {
                gambData = lanc.getLancamentoSaida().getData();
                try {
                    lancSaidController.destroy(lanc.getLancamentoSaida().getCodlanc());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
                }
                lanc.setLancamentoSaida(null);
            }
        } else {
            gambData = new Date();
            try {
                lancController.create(lanc);
            } catch (Exception ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (tipo.getSelectedItem().toString() == "Entrada comum") {
            LancamentoEntrada entrada = new LancamentoEntrada();
            entrada.setCodlanc(lanc.getCodlanc());
            entrada.setData(gambData);
            entrada.setLancamento(lanc);
            try {
                lancEntrController.create(entrada);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (tipo.getSelectedItem().toString() == "Saída comum") {
            LancamentoSaida saida = new LancamentoSaida();
            saida.setCodlanc(lanc.getCodlanc());
            saida.setLancamento(lanc);
            saida.setData(gambData);
            try {
                lancSaidController.create(saida);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(TelaLancamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        if (tipo.getSelectedItem().toString() == "Pagamento de Funcionário") {
//            LancamentoPagfunc pagFunc = new LancamentoPagfunc();
//            pagFunc.setLancamento(lanc);
//            lanc.setLancamentoPagfunc(pagFunc);
//        }
//        if (tipo.getSelectedItem().toString() == "Recebimento do Fornecedor/Cliente") {
//            LancamentoRecforn recForn = new LancamentoRecforn();
//            recForn.setLancamento(lanc);
//            lanc.setLancamentoRecforn(recForn);
//        }
        this.dispose();

        new TelaCaixa();
    }//GEN-LAST:event_salvarActionPerformed

    private void valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLancamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLancamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLancamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLancamento.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLancamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JTextField codigo;
    private javax.swing.JTextField dataAgora;
    private javax.swing.JTextField descricao;
    private javax.swing.JComboBox estorno;
    private javax.swing.JButton excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton salvar;
    private javax.swing.JComboBox tipo;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}
