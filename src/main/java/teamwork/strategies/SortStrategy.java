package teamwork.strategies;

import teamwork.models.Bus;

import java.util.List;

public interface SortStrategy {
    List<Bus> sort(List<Bus> data, int field, boolean ascending);
}
