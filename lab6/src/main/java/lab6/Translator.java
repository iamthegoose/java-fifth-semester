package lab6;

import java.util.HashMap;
import java.util.Map;

public class Translator {

    private Map<String, String> dictionary = new HashMap<>();


    public void addWord(String english, String ukrainian) {
        dictionary.put(english.toLowerCase(), ukrainian.toLowerCase());
    }

    public String translatePhrase(String phrase) {
        StringBuilder result = new StringBuilder();
        String[] words = phrase.toLowerCase().split("\\s+");

        for (String w : words) {
            if (dictionary.containsKey(w)) {
                result.append(dictionary.get(w));
            } else {
                result.append("[").append(w).append("]"); // слово не знайдено
            }
            result.append(" ");
        }

        return result.toString().trim();
    }
}
