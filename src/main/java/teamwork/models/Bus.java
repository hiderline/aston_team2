package teamwork.models;

public class Bus implements Sortable {
    private final String number;    // Номер
    private final String model;     // Модель
    private final int odometer;     // Пробег

    public Bus(String number, String model, int odometer) {
        this.number = number;
        this.model = model;
        this.odometer = odometer;
    }
    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.odometer = builder.odometer;
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public int getMileage() { return odometer; }

    @Override
    public String getField1() {
        return number;
    }
    @Override
    public String getField2() {
        return model;
    }
    @Override
    public String getField3() {
        return String.valueOf(odometer);
    }

    @Override
    public String toString() {
        return String.format("Автобус [Номер: %s, Модель: %s, Пробег: %d км]",
                number, model, odometer);
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
