package src;
import java.time.LocalDate;
import java.util.List;

public class Booking {
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(Room room, LocalDate startDate, LocalDate endDate) {
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isRoomAvailable(List<Booking> existingBookings) {
        for (Booking booking : existingBookings) {
            if (booking.room.equals(this.room) && 
                (startDate.isBefore(booking.endDate) && endDate.isAfter(booking.startDate))) {
                return false; // номер занят
            }
        }
        return true; // номер доступен
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}