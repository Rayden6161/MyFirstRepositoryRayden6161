public class Counter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public synchronized int value() { return count; }
}
