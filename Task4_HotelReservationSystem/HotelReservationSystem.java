import java.util.*;

class Room {
    int roomNumber;
    String category;
    double price;
    boolean available;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }
}

class Reservation {
    String customerName;
    Room room;

    Reservation(String customerName, Room room) {
        this.customerName = customerName;
        this.room = room;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void initializeRooms() {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));

        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));

        rooms.add(new Room(301, "Suite", 4000));
        rooms.add(new Room(302, "Suite", 4000));
    }

    public static void displayAvailableRooms() {

        System.out.println("\n===== Available Rooms =====");

        for (Room room : rooms) {

            if (room.available) {
                System.out.println(
                        "Room: " + room.roomNumber +
                                " | Category: " + room.category +
                                " | Price: ₹" + room.price);
            }
        }
    }

    public static void bookRoom(Scanner sc) {

        System.out.print("Enter Customer Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        displayAvailableRooms();

        System.out.print("Enter Room Number: ");
        int roomNo = sc.nextInt();

        for (Room room : rooms) {

            if (room.roomNumber == roomNo && room.available) {

                System.out.println(
                        "Payment of ₹" + room.price +
                                " Successful!");

                room.available = false;

                reservations.add(
                        new Reservation(name, room));

                System.out.println(
                        "Room Booked Successfully!");
                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    public static void cancelReservation(Scanner sc) {

        System.out.print("Enter Customer Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        Iterator<Reservation> iterator =
                reservations.iterator();

        while (iterator.hasNext()) {

            Reservation reservation =
                    iterator.next();

            if (reservation.customerName
                    .equalsIgnoreCase(name)) {

                reservation.room.available = true;

                iterator.remove();

                System.out.println(
                        "Reservation Cancelled!");
                return;
            }
        }

        System.out.println(
                "Reservation Not Found!");
    }

    public static void viewReservations() {

        System.out.println(
                "\n===== Reservations =====");

        if (reservations.isEmpty()) {
            System.out.println(
                    "No Reservations Found!");
            return;
        }

        for (Reservation reservation :
                reservations) {

            System.out.println(
                    "Customer: "
                            + reservation.customerName
                            + " | Room: "
                            + reservation.room.roomNumber
                            + " | Category: "
                            + reservation.room.category);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        initializeRooms();

        while (true) {

            System.out.println(
                    "\n===== HOTEL RESERVATION SYSTEM =====");

            System.out.println(
                    "1. View Available Rooms");
            System.out.println(
                    "2. Book Room");
            System.out.println(
                    "3. Cancel Reservation");
            System.out.println(
                    "4. View Reservations");
            System.out.println(
                    "5. Exit");

            System.out.print(
                    "Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayAvailableRooms();
                    break;

                case 2:
                    bookRoom(sc);
                    break;

                case 3:
                    cancelReservation(sc);
                    break;

                case 4:
                    viewReservations();
                    break;

                case 5:
                    System.out.println(
                            "Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println(
                            "Invalid Choice!");
            }
        }
    }
}