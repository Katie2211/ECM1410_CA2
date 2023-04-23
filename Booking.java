import java.util.*;
/**
 * 
 * @author Katie Hopkins
 * @since 26/02/2021
 * 
 */




/**
 * The Booking class is the main portion of this program.
 * Booking objects will contain all the neccessary data that the program could
 * it has a single constructor and 3 methods for the booking objects and then 17 methods
 * which run the function of the system as a whole and handle all the user interfacing.
 */
public class Booking {
    String code;
    String studentEmail;
    int time;
    String date;
    BookableRoom room;
    AssistantOnShift assistant;
    enum status{
        SCHEDULED,
        COMPLETED
    }
    status status;
    /**
     * 
     * @param time an integer that represents the hour when the test is being completed
     * @param date a String in format dd/mm/yyyy representing the date for the test
     * @param studentEmail String of the first part of their email, with the @uok.ac.uk added within the function
     * @param university univeristy that holds some of the information of the bookings
     * @param room gives the Bookable room which the booking will be connected to
     * @param assistant gives the assistanton shift performing the test
     */
    public Booking(int time, String date, String studentEmail, University university, BookableRoom room, AssistantOnShift assistant){

        this.time = time;
        this.studentEmail = studentEmail;
        this.date = date;
        this.status = status.SCHEDULED;
        this.code = String.valueOf(university.getBookingCode());
        this.room = room;
        this.assistant = assistant;
        university.incrementBookingCode();
    }
    public BookableRoom getBookableRoom(){
        return this.room;
    }
    public AssistantOnShift getAssistantOnShift(){
        return this.assistant;
    }
    /**
     * 
     * @return boolean which tells if the current status of a booking is completed(true) or scheduled(false)
     */
    public boolean checkStatus(){
        if(this.status == status.COMPLETED){
            return true;
        }
        return false;
    }
    public void completeStatus(){
        this.status = status.COMPLETED;
    }
    /**
     * @return String in the correct output format for this object. Overwrites the default to match with specification.
     */
    public String toString(){
        return "| " + this.date + " 0" + String.valueOf(this.time) + ":00 | " + this.status + " | " + assistant.getEmail() + " | " + room.getCode() + " | " + studentEmail + " |";
    }
    
}