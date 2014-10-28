package Lancamento;

import Util.Util;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class TesteLancamento {

    public TesteLancamento() {
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
        String str = "Compra de linha azul #1d00ff";
        boolean resultado = !Util.isNullOrEmpty(str);
        assertTrue(resultado);
    }

    @Test
    public void testDescricaoTrue2() {
        System.out.println("Testando descrição true: ");
        String str = "Compra de linha azul #1d00ff";
        boolean resultado = Util.VerificaTamanhoStr(str);
        assertTrue(resultado);
    }

    @Test
    public void testDescricaoFalse() {
        System.out.println("Testando descrição false: ");
        String str = "";
        boolean resultadoEsperado = false;
        boolean resultado = !Util.isNullOrEmpty(str);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testDescricaoFalse2() {
        System.out.println("Testando descrição false: ");
        String str = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent tempor consequat nisl et placerat. In rhoncus neque ullamcorper, cursus massa sit amet, malesuada lacus. Morbi vel ante at purus ornare luctus non a tortor. Duis elementum tincidunt nisl quis rhoncus. Pellentesque pharetra lobortis mauris, ut bibendum urna viverra sed. Duis consequat tempus velit, vel sollicitudin leo viverra ut. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Cras dictum dui et massa sodales, ac malesuada nibh volutpat. Nunc vitae venenatis dolor. Quisque vulputate tortor tortor, nec venenatis dolor blandit et. Etiam nisl lacus, consequat et orci sed, dictum ultrices odio. Sed erat lacus, ultricies in tortor ac, condimentum tincidunt eros.";
        boolean resultadoEsperado = false;
        boolean resultado = !Util.VerificaTamanhoStr(str);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValorMenorQueZeroTrue() {
        System.out.println("Testando valor true: ");
        String s = "R$ 10,51";//Como o valor vem da view
        boolean resultadoEsperado = true;
        boolean resultado = !Util.VerificaValorLancPositivo(s);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test

    public void testValorMenorQueZeroFalse() {
        System.out.println("Testando valor false: ");
        Double d = -10.51;
        boolean resultadoEsperado = false;
        boolean resultado = !Util.VerificaValorLancPositivo(String.valueOf(d));
        assertEquals(resultadoEsperado, resultado);
    }

    @Test

    public void testValorValidoTrue() {
        System.out.println("Testando valor true: ");
        String s = "R$ 10,51";//String como vem da view
        boolean resultadoEsperado = true;
        boolean resultado = !Util.VerificaValorLancValido(s);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValorValidoFalse() {
        System.out.println("Testando valor false: ");
        String s = "Valor Inválido";//String como vem da view
        boolean resultadoEsperado = false;
        boolean resultado = Util.VerificaValorLancValido(s);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValorValidoFalse2() {
        System.out.println("Testando valor false: ");
        Double d = 999999999.99;
        boolean resultadoEsperado = false;
        boolean resultado = Util.VerificaValorLancValido(d);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValorValidoFalse3() {
        System.out.println("Testando valor false: ");
        String s = "abcdefgh";
        boolean resultadoEsperado = false;
        boolean resultado = Util.VerificaValorNumerico(s);
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void testValorValidoFalse4() {
        System.out.println("Testando valor false: ");
        String s = "";
        boolean resultadoEsperado = false;
        boolean resultado = !Util.isNullOrEmpty(s);
        assertEquals(resultadoEsperado, resultado);
    }
}
