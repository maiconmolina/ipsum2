package Material;

import Util.Util;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteMaterial {

    public TesteMaterial() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDescricaoTrue() {
        System.out.println("Testando descrição true: ");
        String str = "Botão branco";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test
    public void testDescricaoTrue2() {
        System.out.println("Testando descrição true: ");
        String str = "Botão branco";
        boolean resultado = Util.VerificaTamanhoStr(str);
        assertTrue(resultado);
    }

    @Test
    public void testDescricaoFalse() {
        System.out.println("Testando descrição false: ");
        String str = "";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertFalse(resultado);
    }

    @Test
    public void testDescricaoFalse2() {// 4 digitos minimos
        System.out.println("Testando descrição false: ");
        String str = "a";
        boolean resultado = Util.VerificaTamanhoStr(str);
        assertFalse(resultado);
    }

    @Test
    public void testQuantidadeTrue() {// valor valido
        System.out.println("Testando quantidade true: ");
        Integer i = 100;
        boolean resultado = Util.VerificaQuantidade(i);
        assertTrue(resultado);
    }

    @Test
    public void testQuantidadeFalse() {// valor negativo não é válido para quantidade
        System.out.println("Testando quantidade false: ");
        Integer i = -1;
        boolean resultado = Util.VerificaQuantidade(i);
        assertFalse(resultado);
    }

    @Test
    public void testQuantidadeFalse2() {
        System.out.println("Testando quantidade false: ");
        Integer i = 999999999;
        boolean resultado = Util.VerificaQuantidade(i);
        assertFalse(resultado);
    }

    @Test
    public void testQuantidadeFalse3() {
        System.out.println("Testando quantidade false: ");
        String s = "Teste";
        boolean resultado = Util.VerificaQuantidade(s);
        assertFalse(resultado);
    }

    @Test
    public void testQuantidadeFalse4() {
        System.out.println("Testando quantidade false: ");
        Double d = 10.01;
        boolean resultado = Util.VerificaQuantidade(d);
        assertFalse(resultado);
    }

    @Test
    public void testQuantidadeFalse5() {
        System.out.println("Testando quantidade false: ");
        String s = "";
        boolean resultado = !Util.isNullOrEmpty(s);
        assertFalse(resultado);
    }
}
