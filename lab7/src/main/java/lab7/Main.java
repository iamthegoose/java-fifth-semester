package lab7;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String input = "hello абв apple test world queue sky bcdfg soup";

        String[] result = findWords(input);

        System.out.println("Результат:");
        System.out.println(Arrays.toString(result));
    }

    public static String[] findWords(String input) {

        List<String> vowels = Arrays.asList("a","e","i","o","u");

        return Arrays.stream(input.split("\\s+"))                        
                .filter(w -> w.matches("[A-Za-z]+"))                     
                .filter(w -> {
                    long v = w.toLowerCase().chars()
                            .filter(ch -> vowels.contains(String.valueOf((char) ch)))
                            .count();

                    long c = w.length() - v;                             
                    return v == c;                                       
                })
                .toArray(String[]::new);
    }
}
