package lab10.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class LogMenuApp {

    private static final Logger logger = Logger.getLogger(LogMenuApp.class.getName());
    private static final String BUNDLE_BASE = "location.messages";

    private static Locale currentLocale = new Locale("uk");
    private static ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE, currentLocale);

    public static void main(String[] args) {
        configureLogging();
        logger.info("Application started");

        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();

            System.out.print(bundle.getString("prompt.choice"));
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    logger.info("User chose: process file");
                    System.out.print(bundle.getString("prompt.enterFile"));
                    String path = sc.nextLine();
                    String best = findLineWithMostWords(path);
                    if (best != null) {
                        System.out.println(bundle.getString("result.bestLine"));
                        System.out.println(best);
                    }
                    break;

                case "2":
                    logger.info("User chose: change language");
                    changeLanguage(sc);
                    break;

                case "0":
                    logger.info("User chose: exit");
                    System.out.println("Bye!");
                    return;

                default:
                    logger.warning("Unknown menu choice: " + choice);
                    System.out.println("???");
            }
        }
    }


    private static void configureLogging() {
        logger.setUseParentHandlers(false);   
        logger.setLevel(Level.ALL);

        try {
            ConsoleHandler console = new ConsoleHandler();
            console.setLevel(Level.INFO);    

            FileHandler file = new FileHandler("lab10.log", true);
            file.setLevel(Level.WARNING);     
            file.setFormatter(new SimpleFormatter());

            logger.addHandler(console);
            logger.addHandler(file);

        } catch (IOException e) {
            System.err.println("Cannot init logging: " + e.getMessage());
        }
    }


    private static void printMenu() {
        System.out.println("\n=== " + bundle.getString("menu.title") + " ===");
        System.out.println("1 - " + bundle.getString("menu.option.processFile"));
        System.out.println("2 - " + bundle.getString("menu.option.changeLang"));
        System.out.println("0 - " + bundle.getString("menu.option.exit"));
    }

    private static void changeLanguage(Scanner sc) {
        System.out.println(bundle.getString("lang.choose"));
        String choice = sc.nextLine().trim();

        switch (choice) {
            case "1":
                currentLocale = new Locale("en");
                bundle = ResourceBundle.getBundle(BUNDLE_BASE, currentLocale);
                logger.info("Language changed to EN");
                System.out.println(bundle.getString("lang.changed.en"));
                break;
            case "2":
                currentLocale = new Locale("uk");
                bundle = ResourceBundle.getBundle(BUNDLE_BASE, currentLocale);
                logger.info("Language changed to UK");
                System.out.println(bundle.getString("lang.changed.uk"));
                break;
            default:
                logger.warning("Unknown language choice: " + choice);
        }
    }



    private static String findLineWithMostWords(String path) {
        logger.fine("Opening file: " + path);
        String best = null;
        int maxWords = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                int cnt = countWords(line);
                if (cnt > maxWords) {
                    maxWords = cnt;
                    best = line;
                }
            }
            logger.info("File processed, maxWords = " + maxWords);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + path, e);
            System.out.println(bundle.getString("error.file"));
        }

        return best;
    }

    private static int countWords(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return 0;
        return trimmed.split("\\s+").length;
    }
}
