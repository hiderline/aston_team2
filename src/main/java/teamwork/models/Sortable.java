package teamwork.models;

public interface Sortable extends Comparable<Sortable> {
    String getField1();
    String getField2();
    String getField3();

    @Override
    default int compareTo(Sortable other) {
        return 0; //Реализуем в конкретных стратегиях
    }
}
