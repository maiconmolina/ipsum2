package Util;

public enum PagEnum {
    DINHEIRO("Dinheiro"),
    CHEQUE("Cheque");
    
    private final String label;
     
    private PagEnum(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
