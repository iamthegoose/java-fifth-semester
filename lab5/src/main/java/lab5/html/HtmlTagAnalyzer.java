package lab5.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;

public class HtmlTagAnalyzer {

    public static Map<String, Integer> analyze(String url) {
        Map<String, Integer> freq = new HashMap<>();

        try {
            Document doc = Jsoup.connect(url).get();

            for (Element e : doc.getAllElements()) {
                String tag = e.tagName();
                freq.put(tag, freq.getOrDefault(tag, 0) + 1);
            }

        } catch (Exception e) {
            System.out.println("Помилка завантаження HTML: " + e.getMessage());
        }

        return freq;
    }

    public static void printSortedAlphabetically(Map<String, Integer> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    public static void printSortedByCount(Map<String, Integer> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }
}
