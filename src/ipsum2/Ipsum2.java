/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ipsum2;

import javax.persistence.*;
import persistencia.ConexaoBanco;
import view.Splash;
import view.Start;

/**
 *
 * @author Maicon
 */
public class Ipsum2 {

    /**
     * @param args the command line arguments
     */
    
    private static ConexaoBanco con;
    private static Start st;
    
    public static void main(String[] args) {
        // TODO code application logic here
        Splash sp = new Splash();
        sp.setVisible(true);
        con = new ConexaoBanco();  // iniciar banco
        st = new Start();
        sp.dispose();
        st.setVisible(true);
    }
    
    public static EntityManagerFactory getFactory(){
        return con.getEntityManager().getEntityManagerFactory();
    }
    
}
