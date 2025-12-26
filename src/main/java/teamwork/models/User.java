package teamwork.models;

public class User implements Sortable {
    private final String name;     // Имя
    private final String password; // Пароль
    private final String email;    // Почта

    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    @Override
    public String getField1() { return name; }
    @Override
    public String getField2() { return password; }
    @Override
    public String getField3() { return email; }

    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Пользователь [Имя: %s, Пароль: %s, Email: %s]",
                name, "*****", email);
    }

    // Builder класс
    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}