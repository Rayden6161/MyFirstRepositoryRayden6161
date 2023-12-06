public class Main {
    public static void main(String[] args) {
        Counter c = new Counter();
        for(int i = 0; i < 10; i++) {
            (new CounterThread(c, "Nit " + i)).start();
        }

    }
}