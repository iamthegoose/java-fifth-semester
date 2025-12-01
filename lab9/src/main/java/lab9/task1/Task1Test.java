package lab9.task1;

import java.util.*;

public class Task1Test {

    public static void main(String[] args) throws InterruptedException {

        Bank bank = new Bank();
        Random rnd = new Random();

        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            accounts.add(new Account(i, rnd.nextInt(10_000)));
        }

        int before = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("Before: " + before);

        int threads = 3000;
        Thread[] workers = new Thread[threads];

        for (int i = 0; i < threads; i++) {
            workers[i] = new Thread(() -> {
                Account a = accounts.get(rnd.nextInt(accounts.size()));
                Account b = accounts.get(rnd.nextInt(accounts.size()));
                if (a == b) return;

                int amount = rnd.nextInt(100);
                bank.transfer(a, b, amount);
            });
            workers[i].start();
        }

        for (Thread t : workers) t.join();

        int after = accounts.stream().mapToInt(Account::getBalance).sum();
        System.out.println("After:  " + after);

        System.out.println(before == after
                ? "OK — гроші збереглись"
                : "ERROR — десь пропали гроші!");
    }
}
