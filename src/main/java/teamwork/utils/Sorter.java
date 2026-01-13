package teamwork.utils;

import teamwork.models.Bus;
import teamwork.strategies.SortStrategy;

import java.util.List;

public interface Sorter {
    void sort(List<Bus> persons, SortStrategy strategy, boolean ascending);
}
