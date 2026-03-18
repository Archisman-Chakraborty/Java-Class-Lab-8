class Shared {

    synchronized void printMessage(String threadName) {
        String[] words = {"I", "Love", "java", "Very", "Much"};

        for (String word : words) {
            System.out.println(threadName + ": " + word);

            try {
                Thread.sleep(200); // small delay for clarity
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread extends Thread {
    Shared obj;

    MyThread(Shared obj, String name) {
        super(name);
        this.obj = obj;
    }

    public void run() {
        obj.printMessage(getName());
    }
}

public class SyncDemo {
    public static void main(String[] args) {

        Shared obj = new Shared();

        MyThread t1 = new MyThread(obj, "Thread 1");
        MyThread t2 = new MyThread(obj, "Thread 2");

        t1.start();
        t2.start();
    }
}