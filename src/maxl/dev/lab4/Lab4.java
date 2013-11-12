package maxl.dev.lab4;

public class Lab4 {
    public static final int THREADS_COUNT = 8;  //  Coz i have Core i7 3770

    public static void main(String[] args) {
        PrimeCounter counter = new PrimeCounter(1);


        Thread[] thread = new Thread[THREADS_COUNT];

        for(int i = 0; i < THREADS_COUNT; ++i)
        {
            thread[i] = new Thread(new CounterTask(i, counter));
            thread[i].start();
        }

        for(int i = 0; i < THREADS_COUNT; ++i)
        {
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
