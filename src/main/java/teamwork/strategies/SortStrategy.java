package teamwork.strategies;

import teamwork.models.Bus;

import java.util.List;

public interface SortStrategy {
    void sort(List<Bus> data, boolean ascending);
}
