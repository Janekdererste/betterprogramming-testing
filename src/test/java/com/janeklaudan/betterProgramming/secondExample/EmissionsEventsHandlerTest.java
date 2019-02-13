package com.janeklaudan.betterProgramming.secondExample;

import org.junit.Test;
import org.matsim.contrib.emissions.events.ColdEmissionEvent;
import org.matsim.contrib.emissions.types.Pollutant;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmissionsEventsHandlerTest {

    private static Map<Pollutant, Double> createRandomPollution() {
        return Arrays.stream(Pollutant.values()).collect(Collectors.toMap(p -> p, p -> Math.random()));
    }

    @Test
    public void testHandleColdEmissionsEvent() {

        Map<Pollutant, Double> pollution = createRandomPollution();
        Map<String, Double> weaklyTypedPollution = pollution.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getText(), Map.Entry::getValue));

        ColdEmissionEvent event = new ColdEmissionEvent(1, null, null, weaklyTypedPollution);
        EmissionsEventsHandler handler = new EmissionsEventsHandler(10);

        handler.handleEvent(event);

        TimeBins<Map<Pollutant, Double>> result = handler.getBins();

        Collection<TimeBins.TimeBin<Map<Pollutant, Double>>> bins = result.getAllBins();

        assertEquals(1, bins.size());
        Map<Pollutant, Double> pollutionForOneBin = bins.iterator().next().getValue();

        pollution.forEach((pollutant, expectedValue) -> {
            assertTrue(pollutionForOneBin.containsKey(pollutant));
            assertEquals(expectedValue, pollutionForOneBin.get(pollutant));
        });
        assertTrue(pollutionForOneBin.entrySet().stream().allMatch(entry -> pollution.containsKey(entry.getKey())));
    }
}
