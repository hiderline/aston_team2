package teamwork.strategies;

import teamwork.models.Sortable;

import java.util.List;

/* Пузырьковая сортировка
Многократно проходит по списку, сравнивая соседние элементы
и меняя их местами, если они в неверном порядке.
"Всплывают" наименьшие или наибольшие элементы.
 */
public class BubbleSort implements SortStrategy{
    @Override
    public List<Sortable> sort(List<Sortable> data, int field) {
        return List.of();
    }
}
