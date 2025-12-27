package teamwork.utils;

import teamwork.models.Bus;
import teamwork.strategies.SortStrategy;

import java.util.ArrayList;
import java.util.List;

/* Пузырьковая сортировка
Многократно проходит по списку, сравнивая соседние элементы
и меняя их местами, если они в неверном порядке.
"Всплывают" наименьшие или наибольшие элементы.
 */
public class BubbleSorter implements SortStrategy {
    @Override
    public List<Bus> sort(List<Bus> data, int field, boolean ascending) {
        List<Bus> sorted = new ArrayList<>(data);
        int n = sorted.size();
        int multiplier = ascending ? 1 : -1;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                Bus bus1 = sorted.get(j);
                Bus bus2 = sorted.get(j + 1);

                int comparison = switch (field) {
                    case 1 -> bus1.compareByNumber(bus2);
                    case 2 -> bus1.compareByModel(bus2);
                    case 3 -> bus1.compareByMileage(bus2);
                    default -> 0;
                };

                if (comparison * multiplier > 0) {
                    // Меняем местами
                    sorted.set(j, bus2);
                    sorted.set(j + 1, bus1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Пузырьковая сортировка";
    }


}
