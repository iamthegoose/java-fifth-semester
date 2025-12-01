package lab5.utils;

import java.util.List;

public class StringUtils {

    public static String findLineWithMostWords(List<String> lines) {
        String best = "";
        int max = 0;

        for (String line : lines) {
            int count = line.trim().split("\\s+").length;
            if (count > max) {
                max = count;
                best = line;
            }
        }

        return best;
    }
}
