/**
 * @author Katie Hopkins
 * @since 26/02/2021
 * the assistant class is meant to create the people assisting. They have an email and a name
 */
public class Assistant {
    String email;
    String name;
    /**
     * 
     * @param name a String of the name of the assistant 
     * @param email String of the email of the assistant
     */
    public Assistant(String name, String email){
        this.email = email+"@uok.ac.uk";
        this.name = name;
    }
    /**
     * @return String of the object's information in the format required
     */
    public String toString(){
        return "| " + this.name + " | " + this.email + " |";
    }
    /**
     * 
     * @return String of the assistants email
     */
    public String getEmail(){
        return this.email;
    }
}
