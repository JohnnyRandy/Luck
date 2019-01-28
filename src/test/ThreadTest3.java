package test;

import java.util.Date;

class Mythread extends Thread {
    Boolean flag = false;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println(new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadTest3 {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.setFlag(true);
        mythread.start();
    }
}
