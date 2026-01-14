package teamwork.strategies;

import teamwork.models.Bus;
import teamwork.utils.BubbleSorter;

import java.util.Comparator;
import java.util.List;

public class BusOdometerSortStrategy implements SortStrategy{
    @Override
    public void sort(List<Bus> data, boolean ascending) {
        Comparator<Bus> odometerComparator = Comparator.comparing(Bus::getOdometer);
        if (!ascending)
            odometerComparator = odometerComparator.reversed();
        BubbleSorter.sort(data, odometerComparator);
    }
}
