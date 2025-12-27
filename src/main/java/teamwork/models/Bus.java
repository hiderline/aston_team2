package teamwork.models;

public class Bus implements Comparable<Bus> {
    private final String number;    // Номер
    private final String model;     // Модель
    private final int odometer;     // Пробег

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.odometer = builder.odometer;
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public int getOdometer() { return odometer; }

    @Override
    public String toString() {
        return String.format("Автобус [Номер: %s, Модель: %s, Пробег: %d км]",
                number, model, odometer);
    }

    @Override
    public int compareTo(Bus o) {
        return 0;
    }

    public static class Builder {
        private String number;
        private String model;
        private int odometer;

        public Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setOdometer(int odometer) {
            this.odometer = odometer;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }
}
