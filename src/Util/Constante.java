package Util;

import javax.swing.text.MaskFormatter;

public class Constante {

    public static final MaskFormatter DateMask = Util.setMascara("##/##/####");
    public static final MaskFormatter TelefoneMask = Util.setMascara("(##)####-####");
    public static final MaskFormatter CpfMask = Util.setMascara("###.###.###-##");
    public static final MaskFormatter CnpjMask = Util.setMascara("##.###.###/####-##");
    public static final MaskFormatter CepMask = Util.setMascara("#####-###");
    
    public static final String razao = "Ipsum";
    public static final UfEnum uf = UfEnum.PR;
    public static final String cnpj = "13.528.093/0001-73";
    public static final String inscricao = "210.60339-54";
}
