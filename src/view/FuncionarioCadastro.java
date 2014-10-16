/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Util.Util;
import controller.FuncaoJpaController;
import controller.FuncionarioJpaController;
import javax.swing.JOptionPane;
import model.Funcao;
import model.Funcionario;
import Util.Constante;
import controller.UsuarioJpaController;
import model.Usuario;

public class FuncionarioCadastro extends javax.swing.JInternalFrame {

    public FuncionarioCadastro() {
        initComponents();
        InterfaceUtils.preparaTela(this);
        this.setEditando(false);
        func = null;
    }

    public FuncionarioCadastro(Funcionario f) {
        initComponents();
        InterfaceUtils.preparaTela(this);
        this.func = f;
        this.setEditando(true);
    }

    private final Funcionario func;

    private void setEditando(boolean edit) {
        if (edit) {
            //Setar campos
            jNome.setText(func.getNome());
            jDataNasc.setText(func.getDatanasc().toString());
            jEndereco.setText(func.getEndereco());
            jCPF.setText(func.getCpf());
            jRg.setText(func.getRg());
            jTelefone.setText(func.getTelefone());
            jSalario.setText(func.getSalario().toString());
            jDataNasc.setText(Util.DateToString(func.getDatanasc()));
            jSenha1.setText("");
            jPasswordField1.setText("");
            jLogin.setText("");
            jTemporario.setSelected(func.getTemporario() != 0);

            try {
                Usuario usu = func.getUsuario();
                jLogin.setText(usu.getLogin());
                jSenha1.setText(usu.getSenha());
                jPasswordField1.setText(usu.getSenha());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }

            this.jInativar.setText(func.isAtivo() ? "Inativar" : "Reativar");
            this.jSalvar.setVisible(func.isAtivo());
            if (!func.isAtivo()) {
                jNome.setEditable(false);
                jDataNasc.setEditable(false);
                jEndereco.setEditable(false);
                jCPF.setEditable(false);
                jRg.setEditable(false);
                jTelefone.setEditable(false);
                jSalario.setEditable(false);
                jSenha1.setEditable(false);
                jPasswordField1.setEditable(false);
                jLogin.setEditable(false);
                jTemporario.setEnabled(false);
                jComboBox1.setEnabled(false);
            }
        }
        this.jInativar.setVisible(edit);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Labels = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jNome = new javax.swing.JTextField();
        jDataNasc = new javax.swing.JFormattedTextField(Constante.DateMask);
        jLabel3 = new javax.swing.JLabel();
        jCPF = new javax.swing.JFormattedTextField(Constante.CpfMask);
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSalario = new javax.swing.JTextField();
        jRg = new javax.swing.JTextField();
        jTelefone = new javax.swing.JFormattedTextField(Constante.TelefoneMask);
        jLabel7 = new javax.swing.JLabel();
        jEndereco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLogin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jSenha1 = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jTemporario = new javax.swing.JCheckBox();
        jSalvar = new javax.swing.JButton();
        jInativar = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        jLabel1.setText("Nome:");

        jLabel2.setText("Data nasc:");

        jLabel3.setText("CPF:");

        jLabel4.setText("RG:");

        jLabel5.setText("Telefone:");

        jLabel6.setText("Salário:");

        jLabel7.setText("Endereço:");

        jLabel8.setText("Login:");

        jLabel9.setText("Senha:");

        jLabel10.setText("Confirmar senha:");

        jTemporario.setText("Temporário");

        jSalvar.setText("Salvar");
        jSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalvarActionPerformed(evt);
            }
        });

        jInativar.setText("Inativar");
        jInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInativarActionPerformed(evt);
            }
        });

        jLabel11.setText("Função:");

        javax.swing.GroupLayout LabelsLayout = new javax.swing.GroupLayout(Labels);
        Labels.setLayout(LabelsLayout);
        LabelsLayout.setHorizontalGroup(
            LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabelsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(LabelsLayout.createSequentialGroup()
                        .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(LabelsLayout.createSequentialGroup()
                                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jNome)
                                    .addComponent(jCPF)
                                    .addComponent(jTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDataNasc)
                                    .addComponent(jSalario, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                    .addComponent(jRg)))
                            .addComponent(jEndereco)
                            .addGroup(LabelsLayout.createSequentialGroup()
                                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSenha1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(jLogin, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(LabelsLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jTemporario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                        .addComponent(jInativar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSalvar)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        LabelsLayout.setVerticalGroup(
            LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LabelsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(LabelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTemporario)
                    .addComponent(jSalvar)
                    .addComponent(jInativar))
                .addContainerGap())
        );

        FuncaoJpaController ctr = new FuncaoJpaController();
        for (Funcao f : ctr.getAllActive()){
            jComboBox1.addItem(f);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Labels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Labels, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSalvarActionPerformed
        if (Util.CharArrayToString(jSenha1.getPassword()).equals(
                Util.CharArrayToString(jPasswordField1.getPassword()))) {
            try {
                Funcionario f = new Funcionario();
                Usuario u = new Usuario(Funcionario.class);
                f.setNome(jNome.getText());
                f.setDatanasc(Util.StringToDate(jDataNasc.getText()));
                if (Util.ValidateCpf(jCPF.getText())) {
                    f.setCpf(jCPF.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "CPF inválido");
                    return;
                }
                if (Util.isNumeric(jRg.getText().replace(".", "").replace("-", ""))) {
                    f.setRg(jRg.getText());
                } else {
                    JOptionPane.showMessageDialog(this, "RG inválido");
                    return;
                }
                f.setEndereco(jEndereco.getText());
                f.setTelefone(jTelefone.getText());
                f.setSalario(jSalario.getText());
                f.setCodfuncao((Funcao) jComboBox1.getSelectedItem());
                f.setTemporario(jTemporario.isSelected() ? (short) 1 : 0);
                u.setLogin(jLogin.getText());
                u.setSenha(Util.CharArrayToString(jSenha1.getPassword()));

                UsuarioJpaController ctrUsu = new UsuarioJpaController();
                FuncionarioJpaController ctr = new FuncionarioJpaController();
                if (this.func == null) {
                    int handle = ctrUsu.getUsuarioCount() + 1;
                    u.setCodigo(handle);
                    f.setCodfunc(ctr.getFuncionarioCount() + 1);
                    ctrUsu.create(u);
                    f.setUsuario(u);
                    ctr.create(f);
                } else {
                    ctrUsu.destroy(func.getUsuario().getLogin());
                    Usuario editado = new Usuario(Funcionario.class);
                    editado.setLogin(jLogin.getText());
                    editado.setSenha(Util.CharArrayToString(jSenha1.getPassword()));
                    editado.setCodigo(ctrUsu.getUsuarioCount() + 1);
                    f.setCodfunc(func.getCodfunc());
                    f.setUsuario(editado);
                    ctrUsu.create(editado);
                    ctr.edit(f);
                }
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Um erro ocorreu: \n" + ex.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(this, "As senhas não conferem.");
        }
    }//GEN-LAST:event_jSalvarActionPerformed

    private void jInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInativarActionPerformed

        if (func.isAtivo()) {
            if (JOptionPane.showConfirmDialog(this, "Deseja mesmo inativar?", "Confirmação", 1) == 0) {
                FuncionarioJpaController ctr = new FuncionarioJpaController();
                try {
                    ctr.setAtivo(func, false);
                    new FuncionarioCadastro(func);
                    this.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Um erro ocorreu: " + ex.getMessage());
                }
            }
        } else {
            FuncionarioJpaController ctr = new FuncionarioJpaController();
            try {
                ctr.setAtivo(func, true);
                new FuncionarioCadastro(func);
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Um erro ocorreu: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_jInativarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Labels;
    private javax.swing.JFormattedTextField jCPF;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jDataNasc;
    private javax.swing.JTextField jEndereco;
    private javax.swing.JButton jInativar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLogin;
    private javax.swing.JTextField jNome;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jRg;
    private javax.swing.JTextField jSalario;
    private javax.swing.JButton jSalvar;
    private javax.swing.JPasswordField jSenha1;
    private javax.swing.JFormattedTextField jTelefone;
    private javax.swing.JCheckBox jTemporario;
    // End of variables declaration//GEN-END:variables
}
