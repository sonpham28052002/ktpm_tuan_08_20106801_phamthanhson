package vn.edu.iuh.fit;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String polynomial = "5x^3 - 6x^4 + 6x^9 + 7x + 2";
        int x =2;
        Function<String,String> init = init();
        Function<String,List<Term>> parseTerm = parseTerm();
        Function<List<Term>,List<Term>> derivative = derivative();
        Function<List<Term>,List<Term>> showDerivative = showDerivative();
        Function<List<Term>,Double> cal = cal(x);
        Function<String, Double> pipeLine = init.andThen(parseTerm).andThen(derivative).andThen(showDerivative).andThen(cal);
        double rs = pipeLine.apply(polynomial);
        System.out.println("Giá trị của đa thức với x = " + 2 + " là: " + rs);

    }


    private static Function<String,String> init() {
        return s -> s.replaceAll(" ", "");
    }


    private static Function<String, List<Term>> parseTerm() {
        return s ->  Arrays.stream(s.split("(?=[+-])"))
                    .map(tern -> Term.parseTerm(tern))
                    .collect(Collectors.toList());
    }

    private static Function<List<Term>, List<Term>> derivative() {
        return s -> s.stream()
                .map(term -> term.derivative())
                .collect(Collectors.toList());
    }
    private static Function<List<Term>, List<Term>> showDerivative() {
        return s -> {
            System.out.println("dao hàm của đa thức:");
            for (Term term : s) {
                System.out.println(term);
            }
            return s;
        };
    }

    private static Function<List<Term>, Double> cal(int x){
        return s ->   s.stream()
                .mapToDouble(term -> term.evaluate(x))
                .sum();
    }
}