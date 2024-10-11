import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    private final int philosophersCount;
    private final int eatingCount;
    private final Semaphore[] forks;
    private final Thread[] philosophers;

    public DiningPhilosophers(int philosophersCount, int eatingCount) {
        this.philosophersCount = philosophersCount;
        this.eatingCount = eatingCount;
        this.forks = new Semaphore[philosophersCount];
        this.philosophers = new Thread[philosophersCount];

        for (int i = 0; i < philosophersCount; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public void startDining() {
        for (int i = 0; i < philosophersCount; i++) {
            Semaphore leftFork = forks[i];
            Semaphore rightFork = forks[(i + 1) % philosophersCount];

            philosophers[i] = new Thread(new Philosopher(i, leftFork, rightFork, eatingCount));
            philosophers[i].start();
        }

        for (Thread philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
