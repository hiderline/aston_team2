package teamwork.strategies;

import teamwork.models.Bus;
import teamwork.utils.BubbleSorter;

import java.util.Comparator;
import java.util.List;

public class BusNumberSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Bus> data, boolean ascending) {
        Comparator<Bus> numberComparator = Comparator.comparing(Bus::getNumber);
        if (!ascending)
            numberComparator = numberComparator.reversed();
        BubbleSorter.sort(data, numberComparator);
    }
}
