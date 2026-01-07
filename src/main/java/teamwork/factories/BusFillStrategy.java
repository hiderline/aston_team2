package teamwork.factories;
import teamwork.models.Bus;

import java.util.List;

public interface BusFillStrategy {
    List<Bus> fillBuses(int size);
}