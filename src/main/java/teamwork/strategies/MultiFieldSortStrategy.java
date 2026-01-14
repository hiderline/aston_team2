package teamwork.strategies;

import teamwork.models.Bus;
import teamwork.utils.BubbleSorter;

import java.util.Comparator;
import java.util.List;

public class MultiFieldSortStrategy implements SortStrategy {

    @Override
    public void sort(List<Bus> data, boolean ascending) {
        Comparator<Bus> multiFiledComparator = Comparator
                .comparing(Bus::getNumber)
                .thenComparing(Bus::getModel)
                .thenComparing(Bus::getOdometer);
        if (!ascending)
            multiFiledComparator = multiFiledComparator.reversed();
        BubbleSorter.sort(data, multiFiledComparator);
    }
}
