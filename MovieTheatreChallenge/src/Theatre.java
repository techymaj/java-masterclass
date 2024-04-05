import java.util.TreeSet;

public class Theatre {
    String name;
    int numberOfRows;
    TreeSet<Seat> seats;

    public Theatre(String name, int numberOfRows) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.seats = new TreeSet<>();
    }

    class Seat implements Comparable {
        boolean reserved;
        char row;
        int seatNumber;

        public Seat(char row, int seatNumber) {
            this.row = row;
            this.seatNumber = seatNumber;
        }

        public String seatNumber() {
            return "A005";
        }

        @Override
        public int compareTo(Object o) {
            Seat seat = (Seat) o;
            return (this.row + this.seatNumber) - (seat.row + seat.seatNumber);
        }
    }
}
