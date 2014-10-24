package Fornecedor;

import Util.Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteCadastroFornecedor {

    public TesteCadastroFornecedor() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRazaoTrue() {
        System.out.println("Testando razao true");
        String str = "Delgado e Pereira LTDA";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test
    public void testRazaoFalse() {
        System.out.println("Testando razao false");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test
    public void testFantasiaTrue() {
        System.out.println("Testando fantasia true");
        String str = "Lumax";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test
    public void testFantasiaFalse() {
        System.out.println("Testando fantasia false");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test
    public void testCNPJTrue() {
        System.out.println("Testando cnpj true");
        String str = "62.647.261/0001-55";
        boolean resultado = Util.ValidateCnpj(str);
        assertTrue(resultado);
    }

    @Test
    public void testCNPJFalse() {
        System.out.println("Testando cnpj false");
        String str = "62.647.161/0001-55";
        boolean resultado = Util.ValidateCnpj(str);
        assertFalse(resultado);
    }

    @Test
    public void testTelefoneTrue() {
        System.out.println("Testando telefone true");
        String str = "(44)3225-3211";
        boolean resultado = !Util.VerificaTelefone(str);
        assertTrue(resultado);
    }

    @Test
    public void testTelefoneFalse() {
        System.out.println("Testando telefone false");
        String str = "";
        boolean resultado = !Util.VerificaTelefone(str);
        assertFalse(resultado);
    }

    @Test
    public void testEmailTrue() {
        System.out.println("Testando email true");
        String str = "teste@hotmail.com";
        boolean resultado = !Util.VerificaEmail(str);
        assertTrue(resultado);
    }

    @Test
    public void testEmailFalse() {
        System.out.println("Testando email false");
        String str = "teste@hotmailcom";
        boolean resultado = !Util.VerificaEmail(str);
        assertFalse(resultado);
    }

    @Test
    public void testEmailFalse2() {
        System.out.println("Testando email false");
        String str = "testehotmail.com";
        boolean resultado = !Util.VerificaEmail(str);
        assertFalse(resultado);
    }

    @Test
    public void testEmailFalse3() {
        System.out.println("Testando email false");
        String str = "";
        boolean resultado = !Util.VerificaEmail(str);
        assertFalse(resultado);
    }

    @Test
    public void testCidadeTrue() {
        System.out.println("Testando cidade true");
        String str = "Maringá";
        boolean resultado = !Util.VerificaCidade(str);
        assertTrue(resultado);
    }

    @Test

    public void testCidadeFalse() {
        System.out.println("Testando cidade false");
        String str = "";
        boolean resultado = !Util.VerificaCidade(str);
        assertFalse(resultado);
    }

    @Test

    public void testNumeroTrue() {
        System.out.println("Testando numero true");
        String str = "205-A";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test

    public void testEnderecoTrue() {
        System.out.println("Testando endereco true");
        String str = "Av Rio Branco";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test

    public void testCEPTrue() {
        System.out.println("Testando cep true");
        String str = "87060-000";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    public void testLoginTrue() {
        System.out.println("Testando login true");
        String str = "Lucas";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    public void testSenhaTrue() {
        System.out.println("Testando senha true");
        String str = "8fj3A2s";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test

    public void testNumeroFalse() {
        System.out.println("Testando numero true");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test

    public void testEnderecoFalse() {
        System.out.println("Testando endereco true");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test

    public void testCEPFalse() {
        System.out.println("Testando cep true");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test

    public void testLoginFalse() {
        System.out.println("Testando login true");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test

    public void testSenhaFalse() {
        System.out.println("Testando senha true");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test

    public void testSenha12True() {
        System.out.println("Testando senha 1 e 2 true");
        String str1 = "v923nnba!";
        String str2 = "v923nnba!";
        boolean resultado = Util.ComparaSenhas(str1, str2);
        assertTrue(resultado);
    }

    @Test

    public void testSenha12False() {
        System.out.println("Testando senha 1 e 2 false");
        String str1 = "v923nnba!";
        String str2 = "v923nnba@";
        boolean resultado = Util.ComparaSenhas(str1, str2);
        assertFalse(resultado);
    }

    @Test

    public void testSenha12False2() {
        System.out.println("Testando senha 1 e 2 false");
        String str1 = "v923nnba!";
        String str2 = "";
        boolean resultado = Util.ComparaSenhas(str1, str2);
        assertFalse(resultado);
    }

    @Test

    public void testSenha12False3() {
        System.out.println("Testando senha 1 e 2 false");
        String str1 = "";
        String str2 = "v923nnba!";
        boolean resultado = Util.ComparaSenhas(str1, str2);
        assertFalse(resultado);
    }

}
