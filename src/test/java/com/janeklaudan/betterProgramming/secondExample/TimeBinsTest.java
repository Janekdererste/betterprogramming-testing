package com.janeklaudan.betterProgramming.secondExample;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TimeBinsTest {

    @Test
    public void getBin_testCorrectStartTimes() {

        TimeBins<String> bins = new TimeBins<>(10);

        TimeBins.TimeBin first = bins.getBin(3);
        TimeBins.TimeBin second = bins.getBin(4);

        TimeBins.TimeBin third = bins.getBin(156);

        assertEquals(0, first.getStartTime(), 0.0001);
        assertEquals(0, second.getStartTime(), 0.00001);

        assertEquals(150, third.getStartTime(), 0.001);
    }

    @Test
    public void getBin_correctValuesAssigned() {

        final String expextedValue = "some value which doesn't matter";
        TimeBins<String> bins = new TimeBins<>(10);
        TimeBins.TimeBin bin = bins.getBin(5);
        bin.setValue(expextedValue);

        assertEquals(expextedValue, bins.getBin(5).getValue());


    }
}
