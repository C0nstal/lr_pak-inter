package src;
public class Service {
    private String name;
    public double dailyCost; // Изменить на public

    public Service(String name, double dailyCost) {
        this.name = name;
        this.dailyCost = dailyCost;
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public double getDailyCost() {
        return dailyCost;
    }
}