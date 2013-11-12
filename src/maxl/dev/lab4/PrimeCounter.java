package maxl.dev.lab4;

public class PrimeCounter {
    private volatile long mCount;
    private LamportsBackeryLock mLock = new LamportsBackeryLock(Lab4.THREADS_COUNT);

    public PrimeCounter(long count) {
        mCount = count;
    }

    public LamportsBackeryLock getLock() {
        return mLock;
    }

    public long getAndIncrement() {
        return mCount++;
    }

    public boolean isPrime(long num) {
        long n = (long)Math.sqrt(num);
        for(long i = 2; i <= n; ++i)
            if(num % i == 0) return false;
        return true;
    }
}
