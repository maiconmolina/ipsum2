/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.Constante;
import Util.Util;
import controller.FornecedorJpaController;
import controller.UfJpaController;
import controller.UsuarioJpaController;
import enuns.Permissoes;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Fornecedor;
import model.Funcionario;
import model.Uf;
import model.Usuario;

/**
 *
 * @author Lucas
 */
public class FornecedorCadastro extends javax.swing.JInternalFrame {

    private Fornecedor editaFornecedor = null;

    /**
     * Creates new form FornecedorCadastro
     */
    public FornecedorCadastro() {
        initComponents();
        InterfaceUtils.preparaTela(this);
        jInativar.setEnabled(false);
    }

    public FornecedorCadastro(Fornecedor forn) {
        initComponents();
        editaFornecedor = forn;
        InterfaceUtils.preparaTela(this);

        razao.setText(forn.getRazao());
        fantasia.setText(forn.getFantasia());
        cnpj.setText(forn.getCnpj());
        telefone.setText(forn.getTelefone());
        email.setText(forn.getEmail());
        cidade.setText(forn.getCidade());
        numero.setText(forn.getNumero().toString());
        endereco.setText(forn.getEndereco());
        campoUF.setSelectedItem(forn.getCoduf());
        cep.setText(forn.getCep());
        if (forn.getAtivo() == 0) {
            jInativar.setText("Ativar");
        } else {
            jInativar.setText("Inativar");

        }
        List<Usuario> usu;
        Usuario usua = null;
        UsuarioJpaController usucontroller = new UsuarioJpaController(ipsum2.Ipsum2.getFactory());
        usu = usucontroller.getEntityManager().createNamedQuery("Usuario.findByCodigo").setParameter("codigo", forn.getCodfornec()).getResultList();
        for (Usuario usuario : usu) {
            try {
                if (usuario.getTipo() == Fornecedor.class) {
                    usua = usuario;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Usuário só pode ser Funcionário ou Fornecedor");
            }
        }

        login.setText(usua.getLogin());
        senha.setText(usua.getSenha());
        confirmaSenha.setText(usua.getSenha());

        UfJpaController ufct = new UfJpaController(ipsum2.Ipsum2.getFactory());

        List<Uf> estados = ufct.getEntityManager().createNamedQuery("Uf.findAll").getResultList();
        ComboBoxModel teste = new DefaultComboBoxModel(estados.toArray());
        campoUF.setModel(teste);

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
        razao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fantasia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cnpj = new javax.swing.JFormattedTextField(Constante.CnpjMask);
        jLabel4 = new javax.swing.JLabel();
        telefone = new javax.swing.JFormattedTextField(Constante.TelefoneMask);
        jLabel5 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cidade = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        endereco = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        campoUF = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cep = new javax.swing.JFormattedTextField(Constante.CepMask);
        login = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Salvar = new javax.swing.JButton();
        jInativar = new javax.swing.JButton();
        senha = new javax.swing.JPasswordField();
        confirmaSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setTitle("Cadastro de Fornecedor");

        jLabel1.setText("Razão Social");

        jLabel2.setText("Fantasia");

        jLabel3.setText("CNPJ");

        jLabel4.setText("Telefone");

        jLabel5.setText("E-mail");

        jLabel6.setText("Cidade");

        jLabel7.setText("Numero");

        jLabel8.setText("Endereço");

        jLabel9.setText("UF");

        campoUF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoUFActionPerformed(evt);
            }
        });

        jLabel10.setText("CEP");

        jLabel11.setText("Login");

        jLabel12.setText("Senha");

        jLabel13.setText("Confirmar senha");

        Salvar.setText("Salvar");
        Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalvarActionPerformed(evt);
            }
        });

        jInativar.setText("Inativar");
        jInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInativarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(confirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel9)
                            .addGap(10, 10, 10)
                            .addComponent(campoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cep, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                                .addComponent(cnpj, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(telefone, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(fantasia, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(razao, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jInativar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Salvar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(razao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(campoUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Salvar)
                    .addComponent(jInativar))
                .addContainerGap())
        );

        List<Uf> UF = null;
        UfJpaController ufcontroller = new UfJpaController(ipsum2.Ipsum2.getFactory());
        UF = ufcontroller.getEntityManager().createNamedQuery("Uf.findAll").getResultList();
        for (Uf estados: UF){
            this.campoUF.addItem(estados);
        }
        if (!Funcionario.permite(Permissoes.INATIVAR_FUNCAO)){
            jInativar.setVisible(false);
        }

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoUFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoUFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoUFActionPerformed

    private void SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalvarActionPerformed
        if (!razao.getText().isEmpty()) {
            if (senha.getText().equals(confirmaSenha.getText())) {
                if (this.editaFornecedor == null) {
                    List<Fornecedor> listForn;

                    Fornecedor forn;
                    FornecedorJpaController controllerForn = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());
                    listForn = controllerForn.getEntityManager().createNamedQuery("Fornecedor.findAll").getResultList();

                    int ProxCodigo;
                    if (!listForn.isEmpty()) {
                        forn = listForn.get(listForn.size() - 1);
                        ProxCodigo = forn.getCodfornec();
                        ProxCodigo++;
                    } else {
                        ProxCodigo = 1;
                    }
                    List<Usuario> listUsu;

                    forn = new Fornecedor();
                    forn.setCodfornec(ProxCodigo);

                    forn.setRazao(razao.getText());
                    forn.setFantasia(fantasia.getText());
                    if (Util.ValidateCnpj(cnpj.getText())) {
                        forn.setCnpj(cnpj.getText());
                    } else {
                        JOptionPane.showMessageDialog(this, "CNPJ inválido");
                        return;
                    }
                    forn.setTelefone(telefone.getText());
                    forn.setEmail(email.getText());
                    forn.setCidade(cidade.getText());
                    forn.setNumero(Integer.parseInt(numero.getText()));
                    forn.setEndereco(endereco.getText());
                    forn.setCoduf((Uf) campoUF.getSelectedItem());
                    forn.setCep(cep.getText());
                    forn.setAtivo((short) 1);
                    try {
                        controllerForn.create(forn);
                    } catch (Exception ex) {
                        Logger.getLogger(FornecedorCadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Usuario usu;
                    UsuarioJpaController controllerUsu = new UsuarioJpaController(ipsum2.Ipsum2.getFactory());
                    listUsu = controllerUsu.getEntityManager().createNamedQuery("Usuario.findAll").getResultList();
                    int ProxCodigo2;
                    if (!listUsu.isEmpty()) {
                        usu = listUsu.get(listUsu.size() - 1);
                        ProxCodigo2 = usu.getCodigo();
                        ProxCodigo2++;
                    } else {
                        ProxCodigo2 = 1;
                    }
                    try {
                        usu = new Usuario(Fornecedor.class);
                        usu.setCodigo(forn.getCodfornec());
                        usu.setLogin(login.getText());
                        usu.setSenha(senha.getText());
                        controllerUsu.create(usu);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }

                } else {
                    if (!Funcionario.permite(Permissoes.ALTERAR_FORNECEDOR)) {
                        JOptionPane.showMessageDialog(this, "Você não tem permissão.");
                        return;
                    }
                    Fornecedor forn;
                    forn = this.editaFornecedor;
                    FornecedorJpaController controllerForn = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());

                    forn.setRazao(razao.getText());
                    forn.setFantasia(fantasia.getText());
                    forn.setCnpj(cnpj.getText());
                    forn.setTelefone(telefone.getText());
                    forn.setEmail(email.getText());
                    forn.setCidade(cidade.getText());
                    forn.setNumero(Integer.parseInt(numero.getText()));
                    forn.setEndereco(endereco.getText());
                    forn.setCoduf((Uf) campoUF.getSelectedItem());
                    forn.setCep(cep.getText());

                    try {
                        controllerForn.edit(forn);
                    } catch (Exception ex) {
                        Logger.getLogger(FornecedorCadastro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Usuario usu = null;
                    List<Usuario> usuList = null;
                    UsuarioJpaController usuController = new UsuarioJpaController(ipsum2.Ipsum2.getFactory());
                    usuList = usuController.getEntityManager().createNamedQuery("Usuario.findAll").getResultList();
                    for (Usuario u : usuList) {
                        try {
                            if (u.getCodigo() == forn.getCodfornec() && u.getTipo() == Fornecedor.class) {
                                usu = u;
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(FornecedorCadastro.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    try {
                        usu.setLogin(login.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }
                    try {
                        usu.setSenha(senha.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage());
                    }

                    try {
                        usuController.edit(usu);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Usuário só pode ser Funcionário ou Fornecedor");
                    }
                }
                this.dispose();
                new FornecedorListagem();
            } else {
                JOptionPane.showMessageDialog(this, "As senhas não conferem!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Existe um campo obrigatorio vazio!");
        }
    }//GEN-LAST:event_SalvarActionPerformed

    private void jInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInativarActionPerformed
        if (editaFornecedor.getAtivo() == 1) {
            if (JOptionPane.showConfirmDialog(this, "Deseja mesmo inativar?", "Confirmação", 1) == 0) {
                FornecedorJpaController ctr = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());
                try {
                    editaFornecedor.setAtivo((short) 0);
                    ctr.edit(editaFornecedor);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Um erro ocorreu: " + ex.getMessage());
                }
            }
        } else {
            FornecedorJpaController ctr = new FornecedorJpaController(ipsum2.Ipsum2.getFactory());
            try {
                editaFornecedor.setAtivo((short) 1);
                ctr.edit(editaFornecedor);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Um erro ocorreu: " + ex.getMessage());
            }
        }
        new FornecedorListagem();
        this.dispose();
    }//GEN-LAST:event_jInativarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Salvar;
    private javax.swing.JComboBox campoUF;
    private javax.swing.JFormattedTextField cep;
    private javax.swing.JTextField cidade;
    private javax.swing.JFormattedTextField cnpj;
    private javax.swing.JPasswordField confirmaSenha;
    private javax.swing.JTextField email;
    private javax.swing.JTextField endereco;
    private javax.swing.JTextField fantasia;
    private javax.swing.JButton jInativar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField login;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField razao;
    private javax.swing.JPasswordField senha;
    private javax.swing.JFormattedTextField telefone;
    // End of variables declaration//GEN-END:variables
}
