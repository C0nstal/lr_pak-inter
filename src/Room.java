package src;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Room {
    private String roomNumber;
    private String classType; // (например, "эконом", "люкс")
    private double area; // площадь в квадратных метрах
    public boolean hasTV; 
    public boolean hasFridge; 
    private double basePrice; // базовая цена за ночь
    private List<String> additionalServices; // услуги, доступные для номера
    private double serviceCost; // стоимость дополнительных услуг за день

    public Room(String roomNumber, String classType, double area, boolean hasTV, boolean hasFridge, double basePrice, double serviceCost, List<String> additionalServices) {
        this.roomNumber = roomNumber;
        this.classType = classType;
        this.area = area;
        this.hasTV = hasTV;
        this.hasFridge = hasFridge;
        this.basePrice = basePrice;
        this.serviceCost = serviceCost;
        this.additionalServices = additionalServices;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getClassType() {
        return classType;
    }

    public double getArea() {
        return area;
    }

    public boolean hasTV() {
        return hasTV;
    }

    public boolean hasFridge() {
        return hasFridge;
    }

    public double calculatePrice(int days, LocalDate startDate) {
        double totalCost = 0;
        
        for (int i = 0; i < days; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            double dailyPrice = basePrice;

            // Увеличение цены на 10% в выходные
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                dailyPrice *= 1.1;
            }
            totalCost += dailyPrice;
        }

        // Добавляем стоимость услуг за все дни
        totalCost += serviceCost * days;

        return totalCost;
    }

    public List<String> getAdditionalServices() {
        return additionalServices;
    }

    public double getServiceCost() {
        return serviceCost;
    }
}