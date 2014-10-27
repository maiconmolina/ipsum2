/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.Util;
import controller.LancamentoJpaController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Lancamento;
import model.LancamentoEntrada;
import model.LancamentoPagfunc;
import model.LancamentoRecforn;
import model.LancamentoSaida;

/**
 *
 * @author LuisFernando
 */
public class MostraRelatorioFinanceiro extends javax.swing.JInternalFrame {

    /**
     * Creates new form MostraRelatorioFinanceiro
     */
    public MostraRelatorioFinanceiro() {
        initComponents();
        InterfaceUtils.preparaTela(this);
    }

    public MostraRelatorioFinanceiro(Date data, String dat) {
        initComponents();
        InterfaceUtils.preparaTela(this);
        mesAno.setText(dat);
        LancamentoJpaController controllerLanc = new LancamentoJpaController(ipsum2.Ipsum2.getFactory());
        Lancamento lanc = null;
        List<LancamentoEntrada> listLEntrada = new ArrayList<LancamentoEntrada>();
        List<LancamentoSaida> listLSaida = new ArrayList<LancamentoSaida>();
        List<LancamentoPagfunc> listLPagFunc = new ArrayList<LancamentoPagfunc>();
        List<LancamentoRecforn> listLRecForn = new ArrayList<LancamentoRecforn>();
        List<Lancamento> listLanc = controllerLanc.getEntityManager().createNamedQuery("Lancamento.findAll").getResultList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        String DataParametro = Util.DateToString(data);
        String DataLanc = "";
        String[] arrayData = DataParametro.split("/");
        int MesParam = 0;
        int AnoParam = 0;
        MesParam = Integer.parseInt(arrayData[1]);
        AnoParam = Integer.parseInt(arrayData[2].substring(2));
        int mesLanc = 0;
        int anoLanc = 0;
        for (Lancamento lanca : listLanc) {
            if (lanca.getLancamentoEntrada() != null) {
                DataLanc = Util.DateToString2(lanca.getLancamentoEntrada().getData());
                arrayData = DataLanc.split("/");
                mesLanc = Integer.parseInt(arrayData[1]);
                anoLanc = Integer.parseInt(arrayData[2]);
//                JOptionPane.showMessageDialog(this, lanca.getDescricao() + " MesParam " + MesParam + " AnoParam " + AnoParam + " MesLanc " + mesLanc + " Ano lanc" + anoLanc);
                if (MesParam == mesLanc && AnoParam == anoLanc && lanca.getEstorno() == 0) {
                    listLEntrada.add(lanca.getLancamentoEntrada());
                }
            }
            if (lanca.getLancamentoSaida() != null) {
                DataLanc = Util.DateToString2(lanca.getLancamentoSaida().getData());
                arrayData = DataLanc.split("/");
                mesLanc = Integer.parseInt(arrayData[1]);
                anoLanc = Integer.parseInt(arrayData[2]);
                if (MesParam == mesLanc && AnoParam == anoLanc && lanca.getEstorno() == 0) {
                    listLSaida.add(lanca.getLancamentoSaida());
                }
            }
            if (lanca.getLancamentoPagfunc() != null) {
                DataLanc = Util.DateToString(lanca.getLancamentoPagfunc().getData());
                arrayData = DataLanc.split("/");
                mesLanc = Integer.parseInt(arrayData[1]);
                anoLanc = Integer.parseInt(arrayData[2].substring(2));
//                JOptionPane.showMessageDialog(this, lanca.getDescricao() + " MesParam " + MesParam + " AnoParam " + AnoParam + " MesLanc " + mesLanc + " Ano lanc" + anoLanc);

                if (MesParam == mesLanc && AnoParam == anoLanc && lanca.getEstorno() == 0) {
                    listLPagFunc.add(lanca.getLancamentoPagfunc());
                }
            }
            if (lanca.getLancamentoRecforn() != null) {
                DataLanc = Util.DateToString2(lanca.getLancamentoRecforn().getData());
                arrayData = DataLanc.split("/");
                mesLanc = Integer.parseInt(arrayData[1]);
                anoLanc = Integer.parseInt(arrayData[2]);
                if (MesParam == mesLanc && AnoParam == anoLanc && lanca.getEstorno() == 0) {
                    listLRecForn.add(lanca.getLancamentoRecforn());
                }
            }

        }
        String txtEntrada = "";
        String txtSaida = "";
        for (LancamentoEntrada entrada : listLEntrada) {
            txtEntrada = txtEntrada + entrada.getLancamento().getDescricao() + ": R$" + String.valueOf(entrada.getLancamento().getValor()) + "\n";
        }
        for (LancamentoSaida saida : listLSaida) {
            txtSaida = txtSaida + saida.getLancamento().getDescricao() + ": R$" + String.valueOf(saida.getLancamento().getValor()) + "\n";
        }
        for (LancamentoPagfunc pagfunc : listLPagFunc) {
            txtSaida = txtSaida + pagfunc.getLancamento().getDescricao() + ": R$" + String.valueOf(pagfunc.getLancamento().getValor()) + "\n";
        }
        for (LancamentoRecforn recforn : listLRecForn) {
            txtEntrada = txtEntrada + recforn.getLancamento().getDescricao() + ": R$" + String.valueOf(recforn.getLancamento().getValor()) + "\n";

        }
        insereTabela(listLEntrada, listLSaida, listLPagFunc, listLRecForn);

    }

    private void insereTabela(List<LancamentoEntrada> listLEntrada, List<LancamentoSaida> listLSaida, List<LancamentoPagfunc> listLPagFunc, List<LancamentoRecforn> listLRecForn) {
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.setRowCount(0);

//        List<Object> dados = new ArrayList<>();
        int aa = 0;
        int bb = 0;
        double valorEntrada = 0.0;
        double valorSaida = 0.0;
        double valorTotal = 0.0;
        String[][][] arrayString = new String[3][1000][1000];
        for (LancamentoEntrada entrada : listLEntrada) {
            arrayString[0][aa][0] = entrada.getLancamento().getDescricao();
            arrayString[0][aa][1] = "R$ " + entrada.getLancamento().getValor();
            valorEntrada += entrada.getLancamento().getValor();
            aa++;
        }
        for (LancamentoSaida saida : listLSaida) {
            arrayString[1][bb][0] = saida.getLancamento().getDescricao();
            arrayString[1][bb][1] = "R$ " + saida.getLancamento().getValor();
            valorSaida += saida.getLancamento().getValor();
            bb++;
        }
        for (LancamentoPagfunc pagfunc : listLPagFunc) {
            arrayString[1][bb][0] = pagfunc.getLancamento().getDescricao();
            arrayString[1][bb][1] = "R$ " + pagfunc.getLancamento().getValor();
            valorSaida += pagfunc.getLancamento().getValor();
            bb++;
        }
        for (LancamentoRecforn recforn : listLRecForn) {
            arrayString[0][aa][0] = recforn.getLancamento().getDescricao();
            arrayString[0][aa][1] = "R$ " + recforn.getLancamento().getValor();
            valorEntrada += recforn.getLancamento().getValor();
            aa++;
        }
        int i = 0;
        int max = 0;
        List<Object> dados = new ArrayList<>();
        if (aa > bb) {
            max = aa;
        } else {
            max = bb;
        }
        for (i = 0; i <= max; i++) {
            if (arrayString[0][i][0] != "") {
                dados.add(arrayString[0][i][0]);
                dados.add(arrayString[0][i][1]);
            }
            if (arrayString[1][i][0] != "") {
                dados.add(arrayString[1][i][0]);
                dados.add(arrayString[1][i][1]);
                dados.add("-");
            }
            model.addRow(dados.toArray());
            dados.clear();
        }
        valorTotal = valorEntrada - valorSaida;
        dados.add("");
        dados.add("Lucro liquido: " + valorEntrada);
        dados.add("");
        dados.add("Total saida: " + valorSaida);
        dados.add("Lucro bruto: " + valorTotal);

        model.addRow(dados.toArray());
        dados.clear();
//        dados.add("");
//        dados.add("");
//        dados.add("");
//        dados.add("");
////        dados.add("Saldo em caixa:");

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
        mesAno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        imprimir = new javax.swing.JButton();
        imprimir1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório Financeiro");
        setToolTipText("");

        jLabel1.setText("Data:");

        mesAno.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        mesAno.setText("none");

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Entrada", "Valor", "Saída", "Valor", "Total"
            }
        ));
        jScrollPane1.setViewportView(Tabela);

        imprimir.setText("Imprimir");

        imprimir1.setText("Imprimir");

        jButton1.setText("Imprimir");

        jButton2.setText("Nova consulta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mesAno)
                        .addGap(99, 910, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(3, 3, 3)
                .addComponent(jButton2)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(imprimir)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(imprimir1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mesAno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 252, Short.MAX_VALUE)
                    .addComponent(imprimir)
                    .addGap(0, 253, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 252, Short.MAX_VALUE)
                    .addComponent(imprimir1)
                    .addGap(0, 253, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new RelatorioFinanceiro();
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
            java.util.logging.Logger.getLogger(MostraRelatorioFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostraRelatorioFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostraRelatorioFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostraRelatorioFinanceiro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostraRelatorioFinanceiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabela;
    private javax.swing.JButton imprimir;
    private javax.swing.JButton imprimir1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mesAno;
    // End of variables declaration//GEN-END:variables
}
