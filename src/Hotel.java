package src;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> findAvailableRooms(LocalDate startDate, LocalDate endDate, 
                                          boolean hasTV, boolean hasFridge, 
                                          String classType, double minArea, 
                                          List<String> requiredServices) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean matchesCriteria = (hasTV == room.hasTV()) && 
                                      (hasFridge == room.hasFridge()) &&
                                      (classType.equals(room.getClassType()) || classType.isEmpty()) &&
                                      (room.getArea() >= minArea) &&
                                      (requiredServices.stream().allMatch(room.getAdditionalServices()::contains));

            Booking tempBooking = new Booking(room, startDate, endDate);
            if (matchesCriteria && tempBooking.isRoomAvailable(bookings)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void bookRoom(Room room, LocalDate startDate, LocalDate endDate) {
        Booking booking = new Booking(room, startDate, endDate);
        
        // Проверка на доступность номера
        if (booking.isRoomAvailable(bookings)) {
            // Проверка на 2 часа после освобождения
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endOfLastBooking = bookings.stream()
                .filter(b -> b.getRoom().equals(room))
                .map(b -> LocalDateTime.of(b.getEndDate(), LocalTime.MIDNIGHT))
                .max(LocalDateTime::compareTo)
                .orElse(now.minusHours(3)); // Если нет предыдущих бронирований

            if (now.isAfter(endOfLastBooking.plusHours(2))) {
                bookings.add(booking);
                int days = (int) (endDate.toEpochDay() - startDate.toEpochDay());
                double totalCost = room.calculatePrice(days, startDate);
                System.out.println("Номер " + room.getRoomNumber() + " забронирован с " + startDate + " по " + endDate +
                                   ". Общая стоимость: " + totalCost + " рублей.");
            } else {
                throw new IllegalStateException("Номер " + room.getRoomNumber() + " можно забронировать только через 2 часа после освобождения.");
            }
        } else {
            throw new IllegalStateException("Номер " + room.getRoomNumber() + " не доступен для бронирования.");
        }
    }

    public void showBookings() {
        if (bookings.isEmpty()) {
            System.out.println("Нет активных бронирований.");
        } else {
            System.out.println("Существующие бронирования:");
            for (Booking booking : bookings) {
                System.out.println("Номер: " + booking.getRoom().getRoomNumber() +
                                   ", Даты: " + booking.getStartDate() + " - " + booking.getEndDate());
            }
        }
    }
}