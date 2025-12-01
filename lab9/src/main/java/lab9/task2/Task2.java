package lab9.task2;

public class Task2 {

    public static void main(String[] args) throws InterruptedException {

        RingBuffer<String> buffer1 = new RingBuffer<>(20);
        RingBuffer<String> buffer2 = new RingBuffer<>(20);


        for (int i = 0; i < 5; i++) {
            int id = i;
            Thread producer = new Thread(() -> {
                int msg = 1;
                while (true) {
                    buffer1.put("Потік " + id + " згенерував повідомлення " + msg++);
                }
            });
            producer.setDaemon(true);
            producer.start();
        }


        for (int i = 0; i < 2; i++) {
            int id = i;
            Thread worker = new Thread(() -> {
                while (true) {
                    String msg = buffer1.get();
                    buffer2.put("Потік " + id + " переклав: " + msg);
                }
            });
            worker.setDaemon(true);
            worker.start();
        }


        for (int i = 0; i < 100; i++) {
            System.out.println(buffer2.get());
        }

        System.out.println("Готово.");
    }
}
