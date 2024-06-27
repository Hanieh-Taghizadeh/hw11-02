public class DeadlockExample {
    public static void main(String[] args) {
    final Object resource1 = "Resource 1";
    final Object resource2 = "Resource 2";

    Thread thread1 = new Thread(() -> {
        synchronized (resource1) {
            System.out.println("Thread 1: locked resource 1");

            try { Thread.sleep(50); } catch (InterruptedException e) {}

            synchronized (resource2) {
                System.out.println("Thread 1: locked resource 2");
            }
        }
    });

    Thread thread2 = new Thread(() -> {
        synchronized (resource2) {
            System.out.println("Thread 2: locked resource 2");

            try { Thread.sleep(50); } catch (InterruptedException e) {}

            synchronized (resource1) {
                System.out.println("Thread 2: locked resource 1");
            }
        }
    });

    thread1.start();
    thread2.start();
}
}
