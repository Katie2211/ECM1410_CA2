import java.util.*;
/**
 * @author Katie Hopkins
 * @since 26/02/2021
 */
public class University {
    List<Assistant> assistants = new ArrayList<Assistant>();
    List<Room> rooms = new ArrayList<Room>();
    List<BookableRoom> bookableRoomList = new ArrayList<BookableRoom>();
    List<AssistantOnShift> assistantOnShiftsList = new ArrayList<AssistantOnShift>();
    List<Booking> bookings = new ArrayList<Booking>();
    int bookingCode;
    /**
     * this simply creates a bunch of default rooms, assistant, bookable rooms, assistants on shift and bookings
     */
    public University(){
        assistants.add(new Assistant("Dave","o_0@uok.ac.uk" ));
        rooms.add(new Room("IC215",1)); 
        assistants.add(new Assistant("George","George286@uok.ac.uk" ));
        rooms.add(new Room("IC216",2)); 
        assistants.add(new Assistant("Daisy","Tremors@uok.ac.uk" ));
        rooms.add(new Room("IC217",3)); 
        bookableRoomList.add(new BookableRoom(rooms.get(0), 7, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(0), 8, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(0), 9, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(1), 7, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(1), 8, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(1), 9, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(2), 7, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(2), 8, "26/03/2021"));
        bookableRoomList.add(new BookableRoom(rooms.get(2), 9, "26/03/2021"));
        assistantOnShiftsList.add(new AssistantOnShift("26/03/2021", assistants.get(0), 7));
        assistantOnShiftsList.add(new AssistantOnShift("26/03/2021", assistants.get(0), 8));
        assistantOnShiftsList.add(new AssistantOnShift("26/03/2021", assistants.get(0), 9));
        assistantOnShiftsList.add(new AssistantOnShift("26/03/2021", assistants.get(1), 7));
        assistantOnShiftsList.add(new AssistantOnShift("26/03/2021", assistants.get(1), 8));
        assistantOnShiftsList.add(new AssistantOnShift("26/03/2021", assistants.get(1), 9));
        
        this.bookingCode = 0;
        
    }
    /**
     * increases the booking code by one, run each time a new booking is added so the code is always unique
     */
    public void incrementBookingCode(){
        this.bookingCode++;
    }
    /**
     * 
     * @return int of booking code
     */
    public int getBookingCode(){
        return this.bookingCode;
    }
    public List<Room> getRooms(){
        return rooms;
    }
    /**
     * 
     * @param temp bookable room to be added to the list of bookable rooms
     */
    public void addBookableRoom(BookableRoom temp){
        bookableRoomList.add(temp);
    }
    /**
     * 
     * @return the list containing all the bookable rooms
     */
    public List<BookableRoom> getBookableRooms(){
        return bookableRoomList;
    }
    /**
     * 
     * @return the list containing all the assistants on shift
     */
    public List<AssistantOnShift> getAssistantOnShifts(){
        return assistantOnShiftsList;
    }
    /**
     * 
     * @return a list containing all the assistants
     */
    public List<Assistant> getAssistant(){
        return assistants;
    }
    /**
     * 
     * @param temp assistant on shift to be added to the list of assistants on shift
     */
    public void addAssistantOnShift(AssistantOnShift temp){
        assistantOnShiftsList.add(temp);
    }
    /**
     * 
     * @return a list containing all the bookings
     */
    public List<Booking> getBookingList(){
        return bookings;
    }
    /**
     * 
     * @param booking booking to be added to the list of booking
     */
    public void setBooking(Booking booking){
        this.bookings.add(booking);
        booking.getAssistantOnShift().changeStatus();
        booking.getBookableRoom().changeStatus();
    }
    
}
