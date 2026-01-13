package teamwork.utils;

import teamwork.models.Bus;
import teamwork.strategies.SortStrategy;

import java.util.List;

public class BubbleSorter implements Sorter{

    @Override
    public void sort(List<Bus> persons, SortStrategy strategy, boolean ascending) {
        int n = persons.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Bus bus1 = persons.get(j);
                Bus bus2 = persons.get(j + 1);

                if (strategy.compare(bus1, bus2, ascending) > 0) {
                    // Меняем местами
                    persons.set(j, bus2);
                    persons.set(j + 1, bus1);
                }
            }
        }
    }
}
