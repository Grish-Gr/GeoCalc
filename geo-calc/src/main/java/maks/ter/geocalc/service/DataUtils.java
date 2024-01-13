package maks.ter.geocalc.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;


@Component
public class DataUtils {

    public Double getStandardDoubleValue(List<Double> valuesInRegion, List<Double> allValueInYear, List<Double> allValues) {

        Collections.sort(allValues);

        return (getDoubleAverage(valuesInRegion) - getDoubleAverage(allValueInYear)) / (allValues.get(allValues.size() - 1) - allValues.get(0));
    }

    public Double getStandardLongValue(List<Long> valuesInRegion, List<Long> allValueInYear, List<Long> allValues) {

        Collections.sort(allValues);

        return (getLongAverage(valuesInRegion) - getLongAverage(allValueInYear)) / (allValues.get(allValues.size() - 1) - allValues.get(0));
    }

    private Double getLongAverage(List<Long> value) {
        int length = value.size();
        double sum = 0.0;


        for (double num : value) {
            sum += num;
        }

        return sum / length;
    }

    private Double getDoubleAverage(List<Double> value) {
        int length = value.size();
        double sum = 0.0;


        for (double num : value) {
            sum += num;
        }

        return sum / length;
    }
}
