package com.janeklaudan.betterProgramming.secondExample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TimeBins<T> {

    private final Map<Integer, TimeBin<T>> bins = new HashMap<>();
    private final double binSize;

    public TimeBins(double binSize) {
        this.binSize = binSize;
    }

    public TimeBin<T> getBin(double time) {
        if (time < 0) throw new IllegalArgumentException("only times greater zero allowed");

        int binIndex = (int) (time / binSize);
        if (!bins.containsKey(binIndex))
            bins.put(binIndex, new TimeBin<>(binIndex * binSize));
        return bins.get(binIndex);
    }

    public Collection<TimeBin<T>> getAllBins() {
        return bins.values();
    }

    static class TimeBin<T> {

        private final double startTime;
        private T value;

        TimeBin(double startTime) {
            this.startTime = startTime;
        }

        double getStartTime() {
            return startTime;
        }

        T getValue() {
            return value;
        }

        void setValue(T value) {
            this.value = value;
        }

        boolean hasValue() {
            return value != null;
        }
    }
}
