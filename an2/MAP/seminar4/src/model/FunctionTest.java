package model;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args){
        Function<String, Integer> converter = x -> Integer.valueOf(x);

        System.out.println(converter.apply("42069"));

        Function<String, Integer> converter1 = Integer::valueOf;

        System.out.println();
        System.out.println(converter1.apply("69420"));

        Function<Integer, Integer> patrat = x -> x*x;
        System.out.println();
        System.out.println(converter1.andThen(patrat).apply("4"));

    }
}
