package lab6;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Translator translator = new Translator();


        translator.addWord("hello", "привіт");
        translator.addWord("world", "світ");
        translator.addWord("my", "мій");
        translator.addWord("name", "імʼя");
        translator.addWord("is", "є");

        System.out.println("=== Англо-український перекладач ===");

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1 — Додати слово в словник");
            System.out.println("2 — Перекласти фразу");
            System.out.println("0 — Вихід");

            System.out.print("Ваш вибір: ");
            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    System.out.print("Введіть англійське слово: ");
                    String eng = sc.nextLine().trim().toLowerCase();

                    System.out.print("Введіть український переклад: ");
                    String ukr = sc.nextLine().trim().toLowerCase();

                    translator.addWord(eng, ukr);
                    System.out.println("Додано!");
                    break;

                case "2":
                    System.out.print("Введіть фразу англійською: ");
                    String phrase = sc.nextLine();

                    String translated = translator.translatePhrase(phrase);
                    System.out.println("Переклад: " + translated);
                    break;

                case "0":
                    System.out.println("Вихід.");
                    return;

                default:
                    System.out.println("Невідомий пункт.");
            }
        }
    }
}
