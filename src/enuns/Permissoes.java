package enuns;
//Max: 32

public enum Permissoes {

    //Material
    MENU_MATERIAL("Acessar menu 'Material'", 1),
    INSERIR_MATERIAL("Inserir Material", 2),
    ALTERAR_MATERIAL("Alterar Material", 3),
    INATIVAR_MATERIAL("Inativar Material", 4),
    //Produto
    MENU_PRODUTO("Acessar menu 'Produto'", 5),
    INSERIR_PRODUTO("Inserir Produto", 6),
    ALTERAR_PRODUTO("Alterar Produto", 7),
    INATIVAR_PRODUTO("Inativar Produto", 8),
    MATERIAIS_PRODUTO("Acessar Materiais do Produto", 9),
    //Lote
    MENU_LOTE("Acessar menu 'Lotes'", 30),
    INSERIR_LOTE("Inserir Lote", 31),
    ALTERAR_LOTE("Alterar Lote", 32),
    //Caixa
    MENU_CAIXA("Acessar menu 'Caixa'", 10),
    LANCAMENTO_CAIXA("Fazer Lançamentos", 11),
    ALTERAR_CAIXA("Alterar Caixa", 12),
    //Pagar funcionário
    PAGAR_FUNCIONARIO("Pagar Funcionário", 13),
    //Função
    MENU_FUNCAO("Acessar menu 'Função'", 14),
    INSERIR_FUNCAO("Inserir Função", 15),
    ALTERAR_FUNCAO("Alterar Função", 16),
    INATIVAR_FUNCAO("Inativar Função", 17),
    //Funcionário
    MENU_FUNCIONARIO("Acessar menu 'Funcionário'", 18),
    INSERIR_FUNCIONARIO("Inserir Funcionário", 19),
    ALTERAR_FUNCIONARIO("Alterar Funcionário", 20),
    INATIVAR_FUNCIONARIO("Inativar Funcionário", 21),
    //Fornecedor
    MENU_FORNECEDOR("Acessar menu 'Fornecedor'", 22),
    INSERIR_FORNECEDOR("Inserir Fornecedor", 23),
    ALTERAR_FORNECEDOR("Alterar Fornecedor", 24),
    INATIVAR_FORNECEDOR("Inativar Fornecedor", 25),
    PAGAR_LOTE("Acessar menu 'Pagamento Lote'", 29),
    //Geral
    MENU_CADASTRO_UF("Acessar menu 'Cadastro UF'", 26),
    MENU_TIPO_PAGAMENTO("Acessar menu 'Tipo de Pagamento'", 27),
    MENU_SITUACAO_LOTE("Acessar menu 'Situação do Lote'", 28);

    private final String label;
    private final int value;

    private Permissoes(String label, int value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.label;
    }

    public int getValue() {
        return this.value;
    }

    public static Permissoes getByValue(int value) {
        for (Permissoes p : Permissoes.values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return null;
    }
}
