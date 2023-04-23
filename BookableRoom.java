/**
 * @author Katie Hopkins
 * @since 26/02/2021
 * This class creates objects which time rooms to times and dates, and collects up some of the 
 * information from the room that we will need later, like occupancy and availability (status)
 */
public class BookableRoom {
    Room room;
    int time;
    String date;
    int occupancy;
    
    enum occupancyStatus{
        EMPTY,
        AVALIABLE,
        FULL
    }
    occupancyStatus status;
    /**
     * 
     * @param room
     * @param time
     * @param date
     */
    public BookableRoom(Room room, int time, String date){
        this.room = room;
        this.time = time;
        this.date = date;
        this.occupancy = 0;
        this.status = occupancyStatus.EMPTY;
    }
    /**
     * 
     * @return the current status of the bookable room
     */
    public occupancyStatus getStatus(){
        return this.status;
    }
    /**
     * 
     * @return true if the status is empty and false otherwise
     */
    public boolean checkStatusEMPTY(){
        if(this.status == occupancyStatus.EMPTY){
            return true;
        }
        return false;
    }
    public boolean checkStatusFULL(){
        if(this.status == occupancyStatus.FULL){
            return true;
        }
        return false;
    }

    /**
     * @return String of objects information in format required
     */
    public String toString(){
        return "| " + this.date + " 0" + String.valueOf(time) + ":00 | " + this.status + " | " + this.room.getCode() + " | occupancy: " + String.valueOf(occupancy) + " |";
    }
    /**
     * 
     * @return the date 
     */
    public String getDate(){
        return this.date;
    }
    /**
     * 
     * @return the int hour of the time the test is being performed
     */
    public int getTime(){
        return this.time;
    }
    /**
     * changes the status by increasing the capacity and seeing where this puts the availability
     */
    public void changeStatus(){
        int capacity = this.room.getCapacity();
        this.occupancy++;
        if(capacity == occupancy){
            this.status = occupancyStatus.FULL;
        }
        else{
            this.status = occupancyStatus.AVALIABLE;
        }
    }
    /**
     * 
     * @return the code of the room
     */
    public String getCode(){
        return this.room.getCode();
    }
    
}
