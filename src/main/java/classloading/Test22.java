package classloading;

public class Test22 {
    static class DeadLoopClass {
        static {
            if (true) {
                System.out.println(Thread.currentThread() + " init DeadLoopClass");
                while(true) {}
            }
        }
    }

    public static void main(String[] args) {
        Runnable scritp = () -> {
            System.out.println(Thread.currentThread() + " start");
            new DeadLoopClass();
            System.out.println(Thread.currentThread() + " run over");
        };

        Thread thread1 = new Thread(scritp);
        Thread thread2 = new Thread(scritp);
        thread1.start();
        thread2.start();
    }
}
