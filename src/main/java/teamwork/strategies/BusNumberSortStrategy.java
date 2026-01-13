package teamwork.strategies;

import teamwork.models.Bus;

public class BusNumberSortStrategy implements SortStrategy{
    @Override
    public int compare(Bus b1, Bus b2, boolean ascending) {
        int result = Integer.compare(b1.getNumber(), b2.getNumber());
        return ascending ? result : -result;
    }
}
