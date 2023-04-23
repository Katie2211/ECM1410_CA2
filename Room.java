/**
 * @author Katie Hopkins
 * @since 26/02/2021
 * This class creates objects which represent the rooms where the tests are being performed
 */
public class Room {
    String code;
    int capacity;
    /**
     * 
     * @param code String representing the code of this room
     * @param capacity int of the maximum number of test which can be performed in that room 
     * 
     */
    public Room(String code, int capacity){
        this.code = code;
        this.capacity = capacity;
    }
    /**
     * @return String of obejcts information in format required
     */
    public String toString(){
        return "| " + this.code + " | capacity: " + String.valueOf(this.capacity) + " |";
    }
    /**
     * 
     * @return String of the rooms code
     */
    public String getCode(){
        return this.code;
    }
    /**
     * 
     * @return int of the capacity of the room
     */
    public int getCapacity(){
        return this.capacity;
    }
}
