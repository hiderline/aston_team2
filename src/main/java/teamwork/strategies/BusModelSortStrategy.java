package teamwork.strategies;

import teamwork.models.Bus;

public class BusModelSortStrategy implements SortStrategy{
    @Override
    public int compare(Bus b1, Bus b2, boolean ascending) {
        int result = b1.getModel().compareToIgnoreCase(b2.getModel());
        return ascending ? result : -result;
    }
}
