package com.janeklaudan.betterProgramming.secondExample;

import org.matsim.contrib.emissions.events.ColdEmissionEvent;
import org.matsim.contrib.emissions.events.ColdEmissionEventHandler;
import org.matsim.contrib.emissions.events.WarmEmissionEvent;
import org.matsim.contrib.emissions.events.WarmEmissionEventHandler;
import org.matsim.contrib.emissions.types.Pollutant;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmissionsEventsHandler implements ColdEmissionEventHandler, WarmEmissionEventHandler {

    private final TimeBins<Map<Pollutant, Double>> bins;

    public EmissionsEventsHandler(double timeBinSize) {
        this.bins = new TimeBins<>(timeBinSize);
    }

    public TimeBins<Map<Pollutant, Double>> getBins() {
        return bins;
    }

    public void handleEvent(ColdEmissionEvent event) {
        handleEmissionsEvent(event.getTime(), event.getColdEmissions());
    }

    public void handleEvent(WarmEmissionEvent event) {
        handleEmissionsEvent(event.getTime(), event.getWarmEmissions());
    }

    private void handleEmissionsEvent(double time, Map<String, Double> pollution) {

        Map<Pollutant, Double> typedPollution = pollution.entrySet().stream()
                .collect(Collectors.toMap(entry -> Pollutant.valueOf(entry.getKey()), Map.Entry::getValue));

        TimeBins.TimeBin<Map<Pollutant, Double>> bin = bins.getBin(time);

        if (!bin.hasValue()) {
            bin.setValue(typedPollution);
        } else {
            Map<Pollutant, Double> summedUpPollution = Stream.concat(bin.getValue().entrySet().stream(), typedPollution.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Double::sum));
            bin.setValue(summedUpPollution);
        }
    }
}
