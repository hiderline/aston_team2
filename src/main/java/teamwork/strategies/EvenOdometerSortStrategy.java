package teamwork.strategies;

import teamwork.models.Bus;
import teamwork.utils.BubbleSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenOdometerSortStrategy implements SortStrategy{
    @Override
    public void sort(List<Bus> data, boolean ascending) {
        List<Bus> busesWithEvenOdometers = new ArrayList<>();
        List<Integer> busesWithEvenOdometersIndexes = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isOdometerEven()) {
                busesWithEvenOdometers.add(data.get(i));
                busesWithEvenOdometersIndexes.add(i);
            }
        }

        BubbleSorter.sort(busesWithEvenOdometers, Comparator.comparing(Bus::getOdometer));
        for (int i = 0; i < busesWithEvenOdometersIndexes.size(); i++) {
            data.set(busesWithEvenOdometersIndexes.get(i), busesWithEvenOdometers.get(i));
        }
    }
}
