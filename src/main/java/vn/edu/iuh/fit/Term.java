package vn.edu.iuh.fit;

public class Term {
    int coefficient;
    int exponent;

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }
    public double evaluate(double x) {
        return coefficient * Math.pow(x, exponent);
    }
    public Term derivative() {

        if (exponent == 0) {
            return new Term(0, 0);
        } else {
            return new Term(coefficient * exponent, exponent - 1);
        }
    }

    public static Term parseTerm(String termStr) {
        termStr = termStr.trim();
        int coefficient, exponent;
        String[] parts = termStr.split("x\\^");

        if (parts.length == 1) {
            if (parts[0].contains("x")) {
                if (parts[0].length() > 1){
                    coefficient = Integer.parseInt(parts[0].substring(0,parts[0].lastIndexOf("x")));
                }else {
                    coefficient = termStr.startsWith("-") ? -1 : 1;
                }
                exponent = 1;
            } else {
                coefficient = Integer.parseInt(parts[0]);
                exponent = 0;
            }
        } else {
            coefficient = Integer.parseInt(parts[0]);
            exponent = Integer.parseInt(parts[1]);
        }
        return new Term(coefficient, exponent);
    }


    @Override
    public String toString() {
        return "Term{" +
                "coefficient=" + coefficient +
                ", exponent=" + exponent +
                '}';
    }
}
