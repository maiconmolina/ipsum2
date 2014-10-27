/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import enuns.Permissoes;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Funcionario;

public class Start extends javax.swing.JFrame {

    public Start() {
        Funcionario.atualizaPermissoes();
        
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public static void addFrame(Component comp) {
        desktopPane.add(comp);
        comp.setVisible(true);
        comp.setLocation(0, 0);
    }

    public static void showPopup(String str) {
        JOptionPane.showMessageDialog(desktopPane, str);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem6 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMaterial = new javax.swing.JMenuItem();
        jProduto = new javax.swing.JMenuItem();
        jLotes = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCaixa = new javax.swing.JMenuItem();
        jPagarFunc = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jFuncaoMenu = new javax.swing.JMenu();
        jFuncaoInserir = new javax.swing.JMenuItem();
        jFuncaoListagem = new javax.swing.JMenuItem();
        jFuncioMenu = new javax.swing.JMenu();
        jFuncioCadastro = new javax.swing.JMenuItem();
        jFuncioListagem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jFornMenu = new javax.swing.JMenuItem();
        jFornPagamentoLote = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jCadastroUF = new javax.swing.JMenuItem();
        jTipoPagamento = new javax.swing.JMenuItem();
        jSituacaoLote = new javax.swing.JMenuItem();

        jMenuItem6.setText("jMenuItem6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        desktopPane.setBackground(new java.awt.Color(51, 153, 255));

        fileMenu.setMnemonic('f');
        fileMenu.setText("Ipsum");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Sair");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu1.setText("Produção");

        jMaterial.setText("Material");
        jMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMaterialActionPerformed(evt);
            }
        });
        jMenu1.add(jMaterial);
        if (!Funcionario.permite(Permissoes.MENU_MATERIAL)){
            jMaterial.setVisible(false);
        }

        jProduto.setText("Produto");
        jProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jProdutoActionPerformed(evt);
            }
        });
        jMenu1.add(jProduto);
        if (!Funcionario.permite(Permissoes.MENU_PRODUTO)){
            jProduto.setVisible(false);
        }

        jLotes.setText("Lotes");
        jLotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLotesActionPerformed(evt);
            }
        });
        jMenu1.add(jLotes);
        if (!Funcionario.permite(Permissoes.MENU_LOTE)){
            jLotes.setVisible(false);
        }

        jMenu6.setText("Produção Diária");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Atualizar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        jMenuItem3.setText("Consultar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem3);

        jMenu1.add(jMenu6);

        menuBar.add(jMenu1);

        jMenu2.setText("Financeiro");

        jCaixa.setText("Caixa");
        jCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCaixaActionPerformed(evt);
            }
        });
        jMenu2.add(jCaixa);
        if (!Funcionario.permite(Permissoes.MENU_CAIXA)){
            jCaixa.setVisible(false);
        }

        jPagarFunc.setText("Pagar Funcionário");
        jPagarFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPagarFuncActionPerformed(evt);
            }
        });
        jMenu2.add(jPagarFunc);
        if (!Funcionario.permite(Permissoes.PAGAR_FUNCIONARIO)){
            jPagarFunc.setVisible(false);
        }

        jMenuItem1.setText("Histórico de Nfe");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        menuBar.add(jMenu2);

        jMenu5.setText("Funcionário");

        jFuncaoMenu.setText("Função");

        jFuncaoInserir.setText("Cadastro");
        jFuncaoInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFuncaoInserirActionPerformed(evt);
            }
        });
        jFuncaoMenu.add(jFuncaoInserir);
        if (!Funcionario.permite(Permissoes.INSERIR_FUNCAO)){
            jFuncaoInserir.setVisible(false);
        }

        jFuncaoListagem.setText("Listagem");
        jFuncaoListagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFuncaoListagemActionPerformed(evt);
            }
        });
        jFuncaoMenu.add(jFuncaoListagem);

        jMenu5.add(jFuncaoMenu);
        if (!Funcionario.permite(Permissoes.MENU_FUNCAO)){
            jFuncaoMenu.setVisible(false);
        }

        jFuncioMenu.setText("Funcionário");

        jFuncioCadastro.setText("Cadastro");
        jFuncioCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFuncioCadastroActionPerformed(evt);
            }
        });
        jFuncioMenu.add(jFuncioCadastro);
        if (!Funcionario.permite(Permissoes.INSERIR_FUNCIONARIO)){
            jFuncioCadastro.setVisible(false);
        }

        jFuncioListagem.setText("Listagem");
        jFuncioListagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFuncioListagemActionPerformed(evt);
            }
        });
        jFuncioMenu.add(jFuncioListagem);

        jMenu5.add(jFuncioMenu);
        if (!Funcionario.permite(Permissoes.MENU_FUNCIONARIO)){
            jFuncioMenu.setVisible(false);
        }

        menuBar.add(jMenu5);

        jMenu3.setText("Fornecedor");

        jFornMenu.setText("Visualizar");
        jFornMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFornMenuActionPerformed(evt);
            }
        });
        jMenu3.add(jFornMenu);
        if (!Funcionario.permite(Permissoes.MENU_FORNECEDOR)){
            jFornMenu.setVisible(false);
        }

        jFornPagamentoLote.setText("Pagamento do Lote");
        jFornPagamentoLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFornPagamentoLoteActionPerformed(evt);
            }
        });
        jMenu3.add(jFornPagamentoLote);
        if (!Funcionario.permite(Permissoes.PAGAR_LOTE)){
            jFornPagamentoLote.setVisible(false);
        }

        menuBar.add(jMenu3);

        jMenu4.setText("Geral");

        jCadastroUF.setText("Cadastro UF");
        jCadastroUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCadastroUFActionPerformed(evt);
            }
        });
        jMenu4.add(jCadastroUF);
        if (!Funcionario.permite(Permissoes.MENU_CADASTRO_UF)){
            jCadastroUF.setVisible(false);
        }

        jTipoPagamento.setText("Tipo de Pagamento");
        jTipoPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTipoPagamentoActionPerformed(evt);
            }
        });
        jMenu4.add(jTipoPagamento);
        if (!Funcionario.permite(Permissoes.MENU_TIPO_PAGAMENTO)){
            jTipoPagamento.setVisible(false);
        }

        jSituacaoLote.setText("Situação Lote");
        jSituacaoLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSituacaoLoteActionPerformed(evt);
            }
        });
        jMenu4.add(jSituacaoLote);
        if (!Funcionario.permite(Permissoes.MENU_SITUACAO_LOTE)){
            jSituacaoLote.setVisible(false);
        }

        menuBar.add(jMenu4);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMaterialActionPerformed
        MaterialCRUD tela = new MaterialCRUD();
        addFrame(tela);
        tela.setLocation(10, 10);
        tela.setVisible(true);
    }//GEN-LAST:event_jMaterialActionPerformed

    private void jCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCaixaActionPerformed
        new TelaCaixa();
    }//GEN-LAST:event_jCaixaActionPerformed

    private void jPagarFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPagarFuncActionPerformed
        new PagamentoFuncionario();

    }//GEN-LAST:event_jPagarFuncActionPerformed

    private void jFornMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFornMenuActionPerformed
        new FornecedorListagem();
    }//GEN-LAST:event_jFornMenuActionPerformed

    private void jCadastroUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCadastroUFActionPerformed
        TelaUFView tela = new TelaUFView();
        addFrame(tela);
        tela.setLocation(10, 10);
        tela.setVisible(true);
    }//GEN-LAST:event_jCadastroUFActionPerformed

    private void jFornPagamentoLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFornPagamentoLoteActionPerformed
        new PagamentoLote();
    }//GEN-LAST:event_jFornPagamentoLoteActionPerformed

    private void jTipoPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTipoPagamentoActionPerformed
        CadastroTipoPagamentoView tela = new CadastroTipoPagamentoView();
        addFrame(tela);
        tela.setLocation(10, 10);
        tela.setVisible(true);
    }//GEN-LAST:event_jTipoPagamentoActionPerformed

    private void jFuncaoInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFuncaoInserirActionPerformed
        new FuncaoCadastro();
    }//GEN-LAST:event_jFuncaoInserirActionPerformed

    private void jFuncaoListagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFuncaoListagemActionPerformed
        new FuncaoListagem();
    }//GEN-LAST:event_jFuncaoListagemActionPerformed

    private void jFuncioCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFuncioCadastroActionPerformed
        new FuncionarioCadastro();
    }//GEN-LAST:event_jFuncioCadastroActionPerformed

    private void jFuncioListagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFuncioListagemActionPerformed
        new FuncionarioListagem();
    }//GEN-LAST:event_jFuncioListagemActionPerformed

    private void jProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jProdutoActionPerformed
        new ListaProdutos();
    }//GEN-LAST:event_jProdutoActionPerformed

    private void jSituacaoLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSituacaoLoteActionPerformed
        TelaSitLoteView tsit = new TelaSitLoteView();
        addFrame(tsit);
        tsit.setLocation(10, 10);
        tsit.setVisible(true);
    }//GEN-LAST:event_jSituacaoLoteActionPerformed

    private void jLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLotesActionPerformed
        ListaLote ll = new ListaLote();
        addFrame(ll);
        ll.setLocation(10, 10);
        ll.setVisible(true);
    }//GEN-LAST:event_jLotesActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new ListaNfe();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        AtualizaProducaoDiaria pd = new AtualizaProducaoDiaria();
        addFrame(pd);
        pd.setLocation(10, 10);
        pd.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ConsultaProducao cp = new ConsultaProducao();
        addFrame(cp);
        cp.setLocation(10, 10);
        cp.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jCadastroUF;
    private javax.swing.JMenuItem jCaixa;
    private javax.swing.JMenuItem jFornMenu;
    private javax.swing.JMenuItem jFornPagamentoLote;
    private javax.swing.JMenuItem jFuncaoInserir;
    private javax.swing.JMenuItem jFuncaoListagem;
    private javax.swing.JMenu jFuncaoMenu;
    private javax.swing.JMenuItem jFuncioCadastro;
    private javax.swing.JMenuItem jFuncioListagem;
    private javax.swing.JMenu jFuncioMenu;
    private javax.swing.JMenuItem jLotes;
    private javax.swing.JMenuItem jMaterial;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jPagarFunc;
    private javax.swing.JMenuItem jProduto;
    private javax.swing.JMenuItem jSituacaoLote;
    private javax.swing.JMenuItem jTipoPagamento;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
