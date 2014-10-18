package enuns;

public enum EnumTipoDeLancamento {

    ENTRADA("Entrada comum"), SAIDA("Saída comum"), PAGAMENTO("Pagamento de Funcionário"), RECEBIMENTO("Recebimento do Fornecedor/Pagamento do Lote");

    private final String label;

    EnumTipoDeLancamento(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
