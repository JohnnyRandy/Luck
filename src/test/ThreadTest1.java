package test;

class t1 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("Thread" + i);
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {
        t1 t = new t1();
        t.start();
        for (int i = 0; i <= 50; i++) {
            System.out.println("main" + i);
        }
    }
}
