/**
 * @author Katie Hopkins
 * @since 26/02/2021
 * This create an object that connects an assitant to a time and date
 */
public class AssistantOnShift {
    int time;
    String date;
    Assistant assistant;
    enum status{
        FREE,
        BUSY
    }
    status status;
    /**
     * 
     * @param date String representing a date in format dd/mm/yyyy
     * @param assistant The assitant performing the test
     * @param time integer representing the hour which the test is being performed
     */
    public AssistantOnShift( String date, Assistant assistant, int time){
        this.date = date;
        this.assistant = assistant;
        this.time = time;
        this.status = status.FREE;

    }
    /**
     * @return String of the objects information in the format required
     */
    public String toString(){
        return "| " + date + " 0" + time + ":00 | " + status + " | " + assistant.getEmail() + " |";
    }
    /**
     * @return a boolean if the assitant on shift is free(true) or not(false)
     */
    public boolean checkStatusFREE(){
        if(this.status == status.FREE){
            return true;
        }
        return false;
    }
    /**
     * 
     * @return String of the date
     */
    public String getDate(){
        return this.date;
    }
    /**
     * 
     * @return integer of the hour test is being performed
     */
    public int getTime(){
        return this.time;
    }
    /**
     * changes the status to busy
     */
    public void changeStatus(){
        this.status = status.BUSY;
    }
    /**
     * 
     * @return String of the assistants email
     */
    public String getEmail(){
        return this.assistant.getEmail();
    }
}
