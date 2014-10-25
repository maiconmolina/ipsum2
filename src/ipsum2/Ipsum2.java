/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipsum2;

import javax.persistence.*;
import model.Funcionario;
import persistencia.ConexaoBanco;
import view.Login;
import view.Splash;

public class Ipsum2 {

    private static ConexaoBanco con;
    private static Login st;

    public static void main(String[] args) {
        Splash sp = new Splash();
        sp.setVisible(true);
        con = new ConexaoBanco();  // iniciar banco
        st = new Login();
        sp.dispose();
        st.setVisible(true);
    }

    public static EntityManagerFactory getFactory() {
        return con.getEntityManager().getEntityManagerFactory();
    }

}
