package maks.ter.geocalc.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

@Component
public class DataUtils {

    public Double getStandardDoubleValue(List<Double> allValue) {
        Collections.sort(allValue);
        return getDoubleAverage(allValue) / (allValue.get(allValue.size() - 1) - allValue.get(0));
    }

    public Double getStandardLongValue(List<Long> allValue) {
        Collections.sort(allValue);
        return getLongAverage(allValue) / (allValue.get(allValue.size() - 1) - allValue.get(0));
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
