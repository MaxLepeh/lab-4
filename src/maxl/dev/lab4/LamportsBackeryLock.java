package maxl.dev.lab4;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class LamportsBackeryLock {
    private AtomicIntegerArray mFlag;
    private AtomicIntegerArray mLabel;
    private final int mThreads;

    public LamportsBackeryLock(int threads)
    {
        mFlag = new AtomicIntegerArray(threads);
        mLabel = new AtomicIntegerArray(threads);
        this.mThreads = threads;
    }

    public void lock(int threadId)
    {
        mFlag.set(threadId, 1);
        mLabel.set(threadId, max(mLabel) + 1);
        mFlag.set(threadId, 0);

        for(int i = 0; i < mThreads; ++i)
        {
            if(i != threadId)
            {
                while (mFlag.get(i) != 0) {}
                while (mLabel.get(i) != 0 && (mLabel.get(i) < mLabel.get(threadId) ||
                        (mLabel.get(i) == mLabel.get(threadId) && threadId > i))) {}
            }
        }
    }

    public void unlock(int threadId)
    {
        mLabel.set(threadId, 0);
    }


    private int max(AtomicIntegerArray array)
    {
        int n = array.length();
        AtomicInteger max = new AtomicInteger();
        max.set(0);

        for(int i = 0; i < n; ++i)
        {
            if(max.get() < array.get(i)) max.set(array.get(i));
        }

        return max.get();
    }
}
