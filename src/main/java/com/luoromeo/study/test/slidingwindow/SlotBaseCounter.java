package com.luoromeo.study.test.slidingwindow;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年05月25日 13:42
 * @modified By
 */
public class SlotBaseCounter {

    private int slotSize;

    private AtomicInteger[] slotCounter;

    public SlotBaseCounter(int slotSize) {
        slotSize = slotSize < 1 ? 1 : slotSize;
        this.slotSize = slotSize;
        this.slotCounter = new AtomicInteger[slotSize];
        for (int i = 0; i < this.slotSize; i++) {
            slotCounter[i] = new AtomicInteger(0);
        }
    }

    public void increaseSlot(int slotSize) {
        slotCounter[slotSize].incrementAndGet();
    }

    public void wipeSlot(int slotSize) {
        slotCounter[slotSize].set(0);
    }

    public int totalCount() {
        return Arrays.stream(slotCounter).mapToInt(AtomicInteger::get).sum();
    }

    @Override
    public String toString() {
        return Arrays.toString(slotCounter);
    }
}
