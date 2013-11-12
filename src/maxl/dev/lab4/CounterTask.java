package maxl.dev.lab4;

public class CounterTask implements Runnable {
    private final PrimeCounter mCounter;
    private final int mThreadId;
    private final long mLimit = (long) Math.pow(10, 4);

    public CounterTask(int threadId, PrimeCounter counter) {
        this.mCounter = counter;
        this.mThreadId = threadId;
    }

    @Override
    public void run() {
        long i = 0;
        while (i < mLimit)
        {
            mCounter.getLock().lock(mThreadId);
            try{
                i = mCounter.getAndIncrement();
            } finally {
                mCounter.getLock().unlock(mThreadId);
            }
            if(mCounter.isPrime(i))
                System.out.println(mThreadId + ": prime number: " + i);
        }
    }
}
