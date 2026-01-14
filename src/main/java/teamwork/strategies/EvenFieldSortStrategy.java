package teamwork.strategies;

import teamwork.models.Bus;
import teamwork.utils.BubbleSorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class EvenFieldSortStrategy implements SortStrategy{
    private final Function<Bus, Integer> fieldExtractor;
    private final Predicate<Bus> evenChecker;

    public EvenFieldSortStrategy(Function<Bus, Integer> fieldExtractor,
                                 Predicate<Bus> evenChecker) {
        this.fieldExtractor = fieldExtractor;
        this.evenChecker = evenChecker;
    }

    @Override
    public void sort(List<Bus> data, boolean ascending) {
        List<Bus> evenBuses = new ArrayList<>();
        List<Integer> evenIndexes = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (evenChecker.test(data.get(i))) {
                evenBuses.add(data.get(i));
                evenIndexes.add(i);
            }
        }
        Comparator<Bus> busComparator = Comparator.comparing(fieldExtractor);
        if (!ascending)
            busComparator = busComparator.reversed();
        BubbleSorter.sort(evenBuses, busComparator);
        for (int i = 0; i < evenIndexes.size(); i++) {
            data.set(evenIndexes.get(i), evenBuses.get(i));
        }
    }
}
