package teamwork.strategies;

import teamwork.models.Bus;

public class MultiFieldSortStrategy implements SortStrategy{
    @Override
    public int compare(Bus b1, Bus b2, boolean ascending) {
        // Сортируем сначала по номеру, затем по модели, затем по пробегу
        int numberCompare = Integer.compare(b1.getNumber(), b2.getNumber());
        if (numberCompare != 0) {
            return ascending ? numberCompare : -numberCompare;
        }

        int modelCompare = b1.getModel().compareToIgnoreCase(b2.getModel());
        if (modelCompare != 0) {
            return ascending ? modelCompare : -modelCompare;
        }

        int odometerCompare = Integer.compare(b1.getOdometer(), b2.getOdometer());
        return ascending ? odometerCompare : -odometerCompare;
    }
}
