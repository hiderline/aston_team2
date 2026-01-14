package teamwork.utils;

import java.util.Comparator;
import java.util.List;

public class BubbleSorter {

    public static <T> void sort(List<T> list, Comparator<T> c) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (c.compare(list.get(i), list.get(j)) > 0) {
                    swap(list, i, j);
                }
            }
        }
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }
}
