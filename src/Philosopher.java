import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final int eatingCount;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, int eatingCount) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatingCount = eatingCount;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " ест.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < eatingCount; i++) {
                think();

                leftFork.acquire();
                System.out.println("Философ " + id + " взял левую вилку.");

                rightFork.acquire();
                System.out.println("Философ " + id + " взял правую вилку.");

                eat();

                rightFork.release();
                System.out.println("Философ " + id + " положил правую вилку.");

                leftFork.release();
                System.out.println("Философ " + id + " положил левую вилку.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Философ " + id + " был прерван.");
        }
    }
}
