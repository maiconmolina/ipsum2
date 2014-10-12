import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.Util;


public class UtilTest {

    public UtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void testValidateCpfTrue() {
        System.out.println("ValidateCpfTrue");
        String cpf = "068.692.315-40";
        assertTrue("O CPF é válido. No entanto, a rotina de validação acusa"
                + " ser inválido.", Util.ValidateCpf(cpf));
    }

    @Test
    public void testValidateCpfFalse() {
        System.out.println("ValidateCpfFalse");
        String cpf = "068.692.315-41";
        assertFalse("O CPF é inválido. No entanto, a rotina de validação acusa"
                + " ser válido.", Util.ValidateCpf(cpf));
    }

    @Test
    public void testValidateCpfFalseString() {
        System.out.println("ValidateCpfFalseString");
        String cpf = "068.6a2.315-40";
        assertFalse("Caracteres não numéricos não devem ser permitidos.", Util.ValidateCpf(cpf));
    }

    @Test
    public void testValidateCpfFalseNull() {
        System.out.println("ValidateCpfFalseNull");
        String cpf = null;
        assertFalse("Null não suportado.", Util.ValidateCpf(cpf));
    }

    @Test
    public void testStringToGregorian() {
        System.out.println("StringToGregorian");
        GregorianCalendar gc = new GregorianCalendar(1994, 9, 18);
        Calendar teste = Util.StringToCalendar("18/10/1994");
        assertEquals(gc.get(Calendar.DAY_OF_MONTH), teste.get(Calendar.DAY_OF_MONTH));
        assertEquals(gc.get(Calendar.MONTH), teste.get(Calendar.MONTH));
        assertEquals(gc.get(Calendar.YEAR), teste.get(Calendar.YEAR));
    }

    @Test
    public void testStringToGregorianError() {
        System.out.println("StringToGregorianError");
        Calendar date = Util.StringToCalendar("32/08/2014");
        assertEquals(null, date);
    }

    @Test
    public void testStringToGregorianVazio() {
        System.out.println("StringToGregorianVazio");
        Calendar date = Util.StringToCalendar("  /  /    ");
        assertEquals(null, date);
    }

    @Test
    public void testIsNumericTrue() {
        System.out.println("IsNumericTrue");
        assertTrue(Util.isNumeric("104311334"));
    }

    @Test
    public void testIsNumericFalse() {
        System.out.println("IsNumericFalse");
        assertFalse(Util.isNumeric("1043a1334"));
    }

    @Test
    public void testIsNumericEmpty() {
        System.out.println("IsNumericEmpty");
        assertTrue(Util.isNumeric(""));
    }

    @Test
    public void testIsNumericNull() {
        System.out.println("IsNumericNull");
        assertTrue(Util.isNumeric(null));
    }

    @Test
    public void testValidateCnpjTrue() {
        System.out.println("ValidateCnpjTrue");
        String cnpj = "60.871.406/0001-71";
        assertTrue(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjFalse() {
        System.out.println("ValidateCnpjFalse");
        String cnpj = "60.871.406/0001-72";
        assertFalse(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjNull() {
        System.out.println("ValidateCnpjNull");
        String cnpj = null;
        assertFalse(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjEmpty() {
        System.out.println("ValidateCnpjEmpty");
        String cnpj = "";
        assertFalse(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjCharacter() {
        System.out.println("ValidateCnpjCharacter");
        String cnpj = "asfgh";
        assertFalse(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjEspaco() {
        System.out.println("ValidateCnpjEspaco");
        String cnpj = "  .   .   /    -  ";
        assertFalse(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjTrueNoMask() {
        System.out.println("ValidateCnpjNoMask");
        String cnpj = "60871406000171";
        assertTrue(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testValidateCnpjFalseNoMask() {
        System.out.println("ValidateCnpjNoMask");
        String cnpj = "60871406000172";
        assertFalse(Util.ValidateCnpj(cnpj));
    }

    @Test
    public void testIsNullOrEmptyTrueNull() {
        System.out.println("IsNullOrEmptyTrueNull");
        assertTrue(Util.isNullOrEmpty(null));
    }

    @Test
    public void testIsNullOrEmptyTrueEmpty() {
        System.out.println("IsNullOrEmptyTrueEmpty");
        assertTrue(Util.isNullOrEmpty(""));
    }

    @Test
    public void testIsNullOrEmptyFalse() {
        System.out.println("IsNullOrEmptyFalse");
        assertFalse(Util.isNullOrEmpty("Teste"));
    }

    @Test
    public void testCalendarToStringTrue() {
        System.out.println("CalendarToStringTest");
        Calendar date = Util.StringToCalendar("12/12/2012");
        assertEquals("12122012", Util.CalendarToString(date));
    }

    @Test
    public void testCharArrayToString() {
        System.out.println("CharArrayToStringTest");
        char[] array = new char[3];
        array[0] = 'a';
        array[1] = 'b';
        array[2] = 'c';
        assertTrue("abc".equals(Util.CharArrayToString(array)));
    }

    @Test
    public void testCharArrayToStringFail() {
        System.out.println("CharArrayToStringFailTest");
        char[] array = new char[3];
        array[0] = 'a';
        array[1] = 'b';
        array[2] = 'c';
        assertFalse("ab".equals(Util.CharArrayToString(array)));
    }

    @Test
    public void testCharArrayToStringNull() {
        System.out.println("CharArrayToStringNullTest");
        char[] array = null;
        boolean error = false;
        try {
            Util.CharArrayToString(array);
        } catch (NullPointerException ex) {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void testDateToString() {
        System.out.println("DateToString");
        Date data = new Date(1994, 9, 18);
        assertEquals("18/10/1994", Util.DateToString(data));
    }

    @Test
    public void testDateToStringFail() {
        System.out.println("DateToStringFail");
        Date data = new Date(1994, 9, 18);
        assertFalse("16/10/1994".equals(Util.DateToString(data)));
    }

    @Test
    public void testStringToDate() {
        System.out.println("StringToDate");
        assertEquals(new Date(1994, 9, 18), Util.StringToDate("18/10/1994"));
    }

    @Test
    public void testStringToDateFail() {
        System.out.println("StringToDateFail");
        assertFalse(new Date(1994, 9, 16).equals(Util.StringToDate("18/10/1994")));
    }
}
