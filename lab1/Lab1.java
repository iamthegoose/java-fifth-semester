package lab1;

import java.util.*;

public class Lab1 {
    public static void main(String[] args) {
        String input = "Hello абракадабра world apPle te3st code java queue";

        String[] result = findWords(input);

        System.out.println(Arrays.toString(result));
    }

    public static String[] findWords(String input) {
        String[] words = input.split("\\s+");

        List<String> valid = new ArrayList<>();

        for (String w : words) {
            if (!w.matches("[A-Za-z]+")) continue;

            int vowels = 0;
            int consonants = 0;

            for (char c : w.toLowerCase().toCharArray()) {
                if ("aeiou".indexOf(c) >= 0) {
                    vowels++;
                } else {
                    consonants++;
                }
            }


            if (vowels == consonants) {
                valid.add(w);
            }
        }

        return valid.toArray(new String[0]);
    }
}
