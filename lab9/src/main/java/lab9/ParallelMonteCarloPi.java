package lab9;

import java.util.Random;

public class ParallelMonteCarloPi {


    private static final long ITERATIONS = 1_000_000_000L;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ParallelMonteCarloPi <threads>");
            return;
        }

        int threads = Integer.parseInt(args[0]);
        Thread[] workers = new Thread[threads];
        long[] results = new long[threads];

        long start = System.nanoTime();

        long perThread = ITERATIONS / threads;

        for (int i = 0; i < threads; i++) {
            final int idx = i;

            workers[i] = new Thread(() -> {
                Random rnd = new Random();

                long inside = 0;

                for (long j = 0; j < perThread; j++) {
                    double x = rnd.nextDouble();
                    double y = rnd.nextDouble();

                    if (x * x + y * y <= 1.0) inside++;
                }

                results[idx] = inside;
            });

            workers[i].start();
        }


        for (Thread t : workers) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long totalInside = 0;
        for (long c : results) totalInside += c;


        double pi = 4.0 * totalInside / ITERATIONS;

        long end = System.nanoTime();
        double ms = (end - start) / 1_000_000.0;

        System.out.println("PI is " + pi);
        System.out.println("THREADS " + threads);
        System.out.println("ITERATIONS " + String.format("%,d", ITERATIONS));
        System.out.println("TIME " + String.format("%.2f ms", ms));
    }
}
