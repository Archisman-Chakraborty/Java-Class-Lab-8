import java.util.Scanner;

class CounterThread extends Thread {
    int lower, upper;

    CounterThread(String name, int l, int u) {
        super(name);
        lower = l;
        upper = u;
    }

    public void run() {
        System.out.println("Thread - " + getName());
        System.out.print("Counter - ");

        try {
            for (int i = lower; i <= upper; i++) {
                System.out.print(i + "\n");
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class CounterProgram {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter thread name: ");
        String name = sc.nextLine();

        System.out.print("Enter lower range: ");
        int lower = sc.nextInt();

        System.out.print("Enter upper range: ");
        int upper = sc.nextInt();

        CounterThread t1 = new CounterThread(name, lower, upper);
        t1.start();
    }
}