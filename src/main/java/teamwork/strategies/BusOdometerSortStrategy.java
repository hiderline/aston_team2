package teamwork.strategies;

import teamwork.models.Bus;

public class BusOdometerSortStrategy implements SortStrategy{
    @Override
    public int compare(Bus b1, Bus b2, boolean ascending) {
        int result = Integer.compare(b1.getOdometer(), b2.getOdometer());
        return ascending ? result : -result;
    }
}
