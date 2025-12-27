package teamwork.strategies;

import teamwork.models.Sortable;

import java.util.List;

public interface SortStrategy {
    List<Sortable> sort(List<Sortable> data, int field);
}
