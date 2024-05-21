package org.ksr.DataController;

public class FuzzySetDTO {
    private Double a, b, c, d;
    private String name;
    private int type;

    public FuzzySetDTO() {}

    public FuzzySetDTO(Double a, Double b, Double c, Double d, String name, int type) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;
        this.type = type;
    }

    public Double getA() {
        return a;
    }
    public Double getB() {
        return b;
    }
    public Double getC() {
        return c;
    }
    public Double getD() {
        return d;
    }
    public String getName() {
        return name;
    }
    public int getType() {
        return type;
    }
}
