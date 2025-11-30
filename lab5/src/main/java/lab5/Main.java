package lab5;

import lab5.file.*;
import lab5.shapes.*;
import lab5.crypto.*;
import lab5.html.*;
import lab5.utils.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== LAB 5 MENU ===");
            System.out.println("1. Рядок з найбільшою кількістю слів");
            System.out.println("2. Зберегти фігури в файл");
            System.out.println("3. Прочитати фігури з файлу");
            System.out.println("4. Шифрувати файл");
            System.out.println("5. Дешифрувати файл");
            System.out.println("6. Аналіз HTML тегів");
            System.out.println("0. Вихід");

            try {
                System.out.print("Ваш вибір: ");
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        System.out.print("Введіть шлях до файлу: ");
                        String path = sc.nextLine();

                        List<String> lines = FileUtils.readLines(path);
                        System.out.println("Рядок: " +
                                StringUtils.findLineWithMostWords(lines));
                        break;

                    case 2:
                        List<Shape> shapes = Arrays.asList(
                                new Circle(2),
                                new Rectangle(3, 4)
                        );

                        System.out.print("Куди зберегти? ");
                        String savePath = sc.nextLine();
                        ShapeStorage.saveShapes(savePath, shapes);
                        break;

                    case 3:
                        System.out.print("Звідки читати? ");
                        String loadPath = sc.nextLine();

                        List<Shape> loaded = ShapeStorage.loadShapes(loadPath);
                        if (loaded != null)
                            for (Shape s : loaded)
                                System.out.println(s);
                        break;

                    case 4:
                        System.out.print("Вхідний файл: ");
                        String inEnc = sc.nextLine();

                        System.out.print("Вихідний файл: ");
                        String outEnc = sc.nextLine();

                        System.out.print("Ключ: ");
                        int encKey = Integer.parseInt(sc.nextLine());

                        try (
                                FileInputStream fis = new FileInputStream(inEnc);
                                FileOutputStream fos = new FileOutputStream(outEnc);
                                EncryptingOutputStream eos = new EncryptingOutputStream(fos, encKey)
                        ) {
                            fis.transferTo(eos);
                        }

                        System.out.println("Файл зашифровано.");
                        break;

                    case 5:
                        System.out.print("Вхідний файл: ");
                        String inDec = sc.nextLine();

                        System.out.print("Вихідний файл: ");
                        String outDec = sc.nextLine();

                        System.out.print("Ключ: ");
                        int decKey = Integer.parseInt(sc.nextLine());

                        try (
                                FileInputStream fis = new FileInputStream(inDec);
                                DecryptingInputStream dis = new DecryptingInputStream(fis, decKey);
                                FileOutputStream fos = new FileOutputStream(outDec)
                        ) {
                            dis.transferTo(fos);
                        }

                        System.out.println("Файл дешифровано.");
                        break;

                    case 6:
                        System.out.print("URL: ");
                        String url = sc.nextLine();

                        Map<String, Integer> result = HtmlTagAnalyzer.analyze(url);

                        System.out.println("\nСортування за алфавітом:");
                        HtmlTagAnalyzer.printSortedAlphabetically(result);

                        System.out.println("\nСортування за частотою:");
                        HtmlTagAnalyzer.printSortedByCount(result);
                        break;

                    case 0:
                        System.out.println("Вихід.");
                        return;

                    default:
                        System.out.println("Невідомий пункт меню.");
                }


            } catch (Exception e) {
                System.out.println("Помилка введення: " + e.getMessage());
            }
        }
    }
}
