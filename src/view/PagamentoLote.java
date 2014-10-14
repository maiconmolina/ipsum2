/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.FornecedorJpaController;
import controller.LoteJpaController;
import controller.PagamentoLoteJpaController;
import controller.TipoPagamentoJpaController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Fornecedor;
import model.Lote;
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

        cancelar.setText("Cancelar");

        jLabel3.setText("Valor Total");

        jLabel5.setText("Tipo de Pagamento");

        jLabel6.setText("Descrição");

        descricao.setColumns(20);
        descricao.setRows(5);
        jScrollPane2.setViewportView(descricao);

        jLabel7.setText("Data");

        campoData.setEditable(false);

        List<PagamentoLote> paglote;
        PagamentoLoteJpaController pagamentoLoteController = new PagamentoLoteJpaController(ipsum2.Ipsum2.getFactory());
        paglote = pagamentoLoteController.getEntityManager().createNamedQuery("PagamentoLote.findAll").getResultList();

        List<Lote> Listlote;
        LoteJpaController LoteController = new LoteJpaController(ipsum2.Ipsum2.getFactory());
        Listlote = LoteController.getEntityManager().createNamedQuery("Lote.findAll").getResultList();

        List<Fornecedor> forn;
        FornecedorJpaController lancamentoController = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());
        forn = lancamentoController.getEntityManager().createNamedQuery("Fornecedor.findAll").getResultList();
        for (Lote l:Listlote){
            if (l.getPagamentoLoteList()==null){
                this.lote.addItem(l.getDescricao()+" "+l.getCodfornec().getRazao());
            }
        }

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
