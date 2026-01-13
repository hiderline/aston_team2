package teamwork.strategies;

import teamwork.models.Bus;

import java.util.List;

public interface SortStrategy {
    int compare(Bus b1, Bus b2, boolean ascending);
}
