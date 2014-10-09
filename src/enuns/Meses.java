package enuns;

public enum Meses {

    JANEIRO("Janeiro"), FEVEREIRO("Fevereiro"), MARCO("Março"), ABRIL("Abril"), MAIO("Maio"), JUNHO("Junho"), JULHO("Julho"), AGOSTO("Agosto"), SETEMBRO("Setembro"), OUTUBRO("Outubro"), NOVEMBRO("Novembro"), DEZEMBRO("Dezembro");

    private final String label;

    Meses(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
