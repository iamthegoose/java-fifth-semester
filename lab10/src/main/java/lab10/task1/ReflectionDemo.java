package lab10.task1;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String literal = "Hello world";
        System.out.println("Літерал до зміни: " + literal);
        changeStringValue(literal, "CHANGED");
        System.out.println("Літерал після зміни: " + literal);

        System.out.print("\nВведіть рядок: ");
        String input = sc.nextLine();

        System.out.print("На що замінюємо: ");
        String replacement = sc.nextLine();

        System.out.println("Рядок до зміни: " + input);
        changeStringValue(input, replacement);
        System.out.println("Рядок після зміни: " + input);

        System.out.println("\nУвага: такий підхід ламає \"immutability\" String і "
                + "залежить від версії JVM. Використовувати тільки для демонстрації.");
    }

    private static void changeStringValue(String target, String newValue) throws Exception {
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);

        Class<?> type = valueField.getType();

        if (type == char[].class) {

            char[] value = (char[]) valueField.get(target);
            char[] newChars = newValue.toCharArray();
            int len = Math.min(value.length, newChars.length);
            System.arraycopy(newChars, 0, value, 0, len);
            if (len < value.length) {
                Arrays.fill(value, len, value.length, '\0');
            }
        } else if (type == byte[].class) {
            byte[] value = (byte[]) valueField.get(target);
            byte[] newBytes = newValue.getBytes(StandardCharsets.UTF_8);
            int len = Math.min(value.length, newBytes.length);
            System.arraycopy(newBytes, 0, value, 0, len);
            if (len < value.length) {
                Arrays.fill(value, len, value.length, (byte) 0);
            }
        } else {
            throw new IllegalStateException("Невідомий тип поля value: " + type);
        }
    }
}
