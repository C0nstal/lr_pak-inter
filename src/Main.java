package src;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Добавление номеров с указанием стоимости дополнительных услуг
        hotel.addRoom(new Room("101", "люкс", 30, true, true, 100, 20, Arrays.asList("WiFi", "Завтрак")));
        hotel.addRoom(new Room("102", "эконом", 20, false, true, 70, 10, Arrays.asList("WiFi")));
        hotel.addRoom(new Room("103", "люкс", 35, true, true, 120, 25, Arrays.asList("WiFi", "Бассейн")));

        // Установим даты
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 5);

        // Параметры поиска
        boolean hasTV = true;
        boolean hasFridge = true;
        String classType = "люкс";
        double minArea = 25;
        List<String> requiredServices = Arrays.asList("WiFi");

        // Поиск доступных номеров
        var availableRooms = hotel.findAvailableRooms(startDate, endDate, hasTV, hasFridge, classType, minArea, requiredServices);

        System.out.println("Доступные номера:");
        for (Room room : availableRooms) {
            System.out.println(room.getRoomNumber());
        }

        // Бронирование номера
        if (!availableRooms.isEmpty()) {
            hotel.bookRoom(availableRooms.get(0), startDate, endDate);
        }

        // Показать текущие бронирования
        hotel.showBookings();
    }
}