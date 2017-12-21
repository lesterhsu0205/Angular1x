package test;

public class SyncThread implements Runnable {

    public static int count = 0;

    public SyncThread() {
//        count = 0;
    }

    public synchronized void run() {
        method();
//        for (int i = 0; i < 5; i ++) {
//            try {
//                System.out.println(Thread.currentThread().getName() + ":" + (count++));
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public synchronized static void method() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] arg) throws Exception {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }

}
