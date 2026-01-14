package teamwork.strategies;

import teamwork.models.Bus;

import java.util.List;

public class SortManager {

    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(List<Bus> data, boolean ascending) {
        strategy.sort(data, ascending);
    }

    public String getStrategyName() {
        if (strategy == null) {
            return "no_strategy";
        }
        return strategy.getClass().getSimpleName();
    }
}
