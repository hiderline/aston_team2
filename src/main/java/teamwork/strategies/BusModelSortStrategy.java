package teamwork.strategies;

import teamwork.models.Bus;
import teamwork.utils.BubbleSorter;

import java.util.Comparator;
import java.util.List;

public class BusModelSortStrategy implements SortStrategy{
    @Override
    public void sort(List<Bus> data, boolean ascending) {
        Comparator<Bus> modelComparator = Comparator.comparing(Bus::getModel);
        if (!ascending)
            modelComparator = modelComparator.reversed();
        BubbleSorter.sort(data, modelComparator);
    }
}
