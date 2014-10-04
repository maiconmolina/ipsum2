/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ipsum2;

import javax.persistence.*;
import persistencia.ConexaoBanco;

/**
 *
 * @author Maicon
 */
public class Ipsum2 {

    /**
     * @param args the command line arguments
     */
    
    private static ConexaoBanco con;
    
    public static void main(String[] args) {
        // TODO code application logic here
        con = new ConexaoBanco();  // iniciar banco
    }
    
    public static EntityManagerFactory getFactory(){
        return con.getEntityManager().getEntityManagerFactory();
    }
    
}
