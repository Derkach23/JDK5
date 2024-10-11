public class Main {
    public static void main(String[] args) {
        int philosophersCount = 5;
        int eatingCount = 3;

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers(philosophersCount, eatingCount);
        diningPhilosophers.startDining();
    }
}