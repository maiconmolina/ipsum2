/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CaixaJpaController;
import controller.FornecedorJpaController;
import controller.LancamentoJpaController;
import controller.LancamentoRecfornJpaController;
import controller.LoteJpaController;
import controller.PagamentoLoteJpaController;
import controller.TipoPagamentoJpaController;
import controller.exceptions.PreexistingEntityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Caixa;
import model.Fornecedor;
import model.Lancamento;
import model.LancamentoRecforn;
import model.Lote;
import model.ProdutoDoLote;
import model.TipoPagamento;

/**
 *
 * @author Lucas
 */
public class PagamentoLote extends javax.swing.JInternalFrame {

    /**
     * Creates new form PagamentoLote
     */
    public PagamentoLote() {
        initComponents();
        InterfaceUtils.preparaTela(this);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        campoData.setText(dateFormat.format(date));
//        hue();
    }

    public PagamentoLote(PagamentoLote paglote) {
        initComponents();
        InterfaceUtils.preparaTela(this);

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
        confirmarPagamento = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descricao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        campoData = new javax.swing.JTextField();
        valorTotal = new javax.swing.JTextField();
        tipoPagamento = new javax.swing.JComboBox();
        lote = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("Pagamento de Lote");

        jLabel1.setText("Lote");

        confirmarPagamento.setText("Confirmar Pagamento");
        confirmarPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarPagamentoActionPerformed(evt);
            }
        });

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Valor Total");

        jLabel5.setText("Tipo de Pagamento");

        jLabel6.setText("Descrição");

        descricao.setEditable(false);
        descricao.setColumns(20);
        descricao.setRows(5);
        jScrollPane2.setViewportView(descricao);

        jLabel7.setText("Data");

        campoData.setEditable(false);

        valorTotal.setEditable(false);

        lote.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ">> SELECIONE <<" }));
        List<LancamentoRecforn> recForn;
        LancamentoRecfornJpaController RecFornController = new LancamentoRecfornJpaController(ipsum2.Ipsum2.getFactory());
        recForn = RecFornController.getEntityManager().createNamedQuery("LancamentoRecforn.findAll").getResultList();

        List<PagamentoLote> paglote;
        PagamentoLoteJpaController pagamentoLoteController = new PagamentoLoteJpaController(ipsum2.Ipsum2.getFactory());
        paglote = pagamentoLoteController.getEntityManager().createNamedQuery("PagamentoLote.findAll").getResultList();

        List<Lote> Listlote;
        LoteJpaController LoteController = new LoteJpaController(ipsum2.Ipsum2.getFactory());
        Listlote = LoteController.getEntityManager().createNamedQuery("Lote.findAll").getResultList();

        List<Fornecedor> forn;
        FornecedorJpaController lancamentoController = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());
        forn = lancamentoController.getEntityManager().createNamedQuery("Fornecedor.findAll").getResultList();

        for (Lote lote : Listlote) {
            boolean achou = false;
            for (model.PagamentoLote pagDoLote : lote.getPagamentoLoteList()) {
                for (LancamentoRecforn LancRecForn : recForn) {
                    if (LancRecForn.getCodpaglote() == pagDoLote.getCodpaglote() && LancRecForn.getLancamento().getEstorno() == 0) {;
                        achou = true;
                    }
                }
            }
            if (achou == false) {
                this.lote.addItem(lote);

            }
        }
        lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valorTotal)
                                    .addComponent(tipoPagamento, 0, 185, Short.MAX_VALUE)))
                            .addComponent(jScrollPane2)
                            .addComponent(lote, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(campoData, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(confirmarPagamento)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(campoData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(valorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarPagamento)
                    .addComponent(cancelar))
                .addContainerGap())
        );

        List<TipoPagamento> tipopag;
        TipoPagamentoJpaController tipopagController = new TipoPagamentoJpaController(ipsum2.Ipsum2.getFactory());
        tipopag = tipopagController.getEntityManager().createNamedQuery("TipoPagamento.findAll").getResultList();
        for (TipoPagamento t: tipopag){
            this.tipoPagamento.addItem(t);
        }

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loteActionPerformed
        Lote l = (Lote) lote.getSelectedItem();
        descricao.setText(l.getDescricao());
        List<ProdutoDoLote> lpl = l.getProdutoDoLoteList();
        Double vlr = new Double(0);
        for (ProdutoDoLote pl : lpl) {
            vlr = pl.getProduto().getPreco() * pl.getQtde();
        }
        valorTotal.setText(vlr.toString());
    }//GEN-LAST:event_loteActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelarActionPerformed

    private void confirmarPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarPagamentoActionPerformed
        PagamentoLoteJpaController jpgl = new PagamentoLoteJpaController(ipsum2.Ipsum2.getFactory());
        Lote lot = null;
        model.PagamentoLote pagLote = new model.PagamentoLote();
        pagLote.setTipopag((TipoPagamento) tipoPagamento.getSelectedItem());
        pagLote.setDatpag(new Date());
        pagLote.setNfeList(null);
        pagLote.setCodpaglote(jpgl.getPagamentoLoteCount() + 1);
        pagLote.setCodlote((Lote) lote.getSelectedItem());
        pagLote.setAtivo((short) 1);

        try {
            jpgl.create(pagLote);
        } catch (Exception ex) {
            Logger.getLogger(PagamentoLote.class.getName()).log(Level.SEVERE, null, ex);
        }
        {
            List<Caixa> caixas;
            Caixa caixa = null;
            CaixaJpaController caixaController = new CaixaJpaController(ipsum2.Ipsum2.getFactory());
            caixas = caixaController.getEntityManager().createNamedQuery("Caixa.findAll").getResultList();
            for (Caixa c : caixas) {
                if (c.getCodcaixa() == 1) {
                    caixa = c;
                    break;
                }
            }
            if (caixa == null) {
                caixa = new Caixa();
                try {
                    caixaController.create(caixa);
                } catch (Exception ex) {
                    Logger.getLogger(PagamentoLote.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            int codLanc = 1;
            Lancamento l;
            LancamentoJpaController LancController = new LancamentoJpaController(ipsum2.Ipsum2.getFactory());
            List<Lancamento> lanc1 = LancController.getEntityManager().createNamedQuery("Lancamento.findAll").getResultList();
            if (!lanc1.isEmpty()) {
                l = lanc1.get(lanc1.size() - 1);
                if (l.getCodlanc() > 0) {
                    codLanc = l.getCodlanc() + 1;
                }
            }
            Lancamento lanc = new Lancamento();
            LancamentoJpaController lancController = new LancamentoJpaController(ipsum2.Ipsum2.getFactory());
            lanc.setAtivo((short) 1);
            lanc.setCodcaixa(caixa);
            lanc.setDescricao("Pagamento Lote: " + pagLote.getCodlote().getCodlote().toString() + " - " + pagLote.getCodlote().getDescricao());
            lanc.setEstorno((short) 0);
            lanc.setCodlanc(codLanc);
            double valor = Double.parseDouble(valorTotal.getText());
            lanc.setValor(valor);
            try {
                lancController.create(lanc);
            } catch (Exception ex) {
                Logger.getLogger(PagamentoLote.class.getName()).log(Level.SEVERE, null, ex);
            }

            LancamentoRecfornJpaController recFornController = new LancamentoRecfornJpaController(ipsum2.Ipsum2.getFactory());
            LancamentoRecforn recForn = new LancamentoRecforn();
            lot = (Lote) lote.getSelectedItem();

            recForn.setLancamento(lanc);
            recForn.setCodfornec(lot.getCodfornec());
            recForn.setCodpaglote(pagLote.getCodpaglote());
            recForn.setCodlanc(codLanc);
            recForn.setData(new Date());
            try {
                recFornController.create(recForn);
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(PagamentoLote.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(PagamentoLote.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        this.dispose();
        new Nfeview(pagLote);

    }//GEN-LAST:event_confirmarPagamentoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoData;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton confirmarPagamento;
    private javax.swing.JTextArea descricao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox lote;
    private javax.swing.JComboBox tipoPagamento;
    private javax.swing.JTextField valorTotal;
    // End of variables declaration//GEN-END:variables
}
