package lab5.file;

import java.io.*;
import java.util.*;

public class FileUtils {

    public static List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null)
                lines.add(line);

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }

        return lines;
    }
}
