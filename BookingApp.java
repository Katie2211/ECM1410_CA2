import java.util.*;
public class BookingApp {

    /**
     * 
     * @param args 
     * this will create the university as well as some default bookings and then call the system
     */
    public static void main(String[] args) {
        University university = new University();
        university.setBooking(new Booking(7, "26/03/2021", "studentEmail@uok.ac.uk", university, university.getBookableRooms().get(0), university.getAssistantOnShifts().get(0)));
        university.setBooking(new Booking(8, "26/03/2021", "studentEmail2@uok.ac.uk", university, university.getBookableRooms().get(3), university.getAssistantOnShifts().get(1)));
        university.getBookingList().get(0).completeStatus();
        system(university); 
    }
    /**
     * @param univerity this is where most of the data is handled. The object contains lists
     * of the different objects being created and is used to recall this information often
     */
    public static void system(University university){
        
        Scanner input = new Scanner(System.in); // this scanner will become very useful for handling user inputs
        boolean correctInput = false;
        String userInput = "";
        while(!correctInput){ // this is basic input validation to make sure that the users input is correct
            userInput = displayMainMenu(input);
            List<String> possibleInputs = Arrays.asList(new String[]{"-1","0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
            if(possibleInputs.contains(userInput)){
                correctInput = true;
            }
            else{
                System.out.println("Error!");
            }
        }
        switch(userInput){
            case "-1":
                return;
            case "0":
                system(university);
                break;
            case "1":
                displayListBookableRoomsMenu(university, input);
                break;
            case "2":
                displayAddBookableRoomsMenu(university, input);
                break;
            case "3":
                displayRemoveBookableRoomMenu(university, input);
                break;
            case "4":
                displayListAssistantOnShiftMenu(university, input);
                break;
            case "5":
                displayAddAssistantOnShiftMenu(university, input);
                break;
            case "6":
                displayRemoveAssistantOnShiftMenu(university, input);
                break;
            case "7":
                displayListBookingMenu(university, input);
                break;
            case "8":
                displayAddBookingMenu(university, input);
                break;
            case "9":
                displayRemoveBookingMenu(university, input);
                break;
            case "10":
                displayConcludeBookingMenu(university, input);
                break;
        }
        
        input.close();
        
    }
    /**
     * 
     * @param items this will be a list of string created by using the corresponding toString method for that object
     * 
     * This is a generic routine that will present the list of objects in a way to fit the specification
     */
    public static void list(List<String> items){
        
        for (int i = 0; i < items.size(); i ++){
            System.out.println(String.valueOf(i + 11) + ". " + items.get(i));
        }
    }
    /**
     * 
     * @param input will be used to handle the user inputs
     * @return Stirng which will be the user input 
     */
    public static String displayMainMenu(Scanner input){
        
        System.out.println("University of Knowledge - COVID test\n\nManage Bookings\n");
        System.out.println("Please, enter the number to select your option:\n");
        System.out.println("To manage Bookable Rooms :\n    1. List\n    2. Add\n    3. Remove");
        System.out.println("To manage Assistants on Shift:\n    4. List\n    5. Add\n    6. Remove");
        System.out.println("To manage Bookings:\n    7. List\n    8. Add\n    9. Remove\n    10. Conclude");
        String userInput = input.nextLine();
        
        return userInput;
    }
    /**
     * 
     * @param university this holds all the data to be listed for the BookableRooms 
     * @param input this will handle the user inputs
     * This simply gets the information to be printed then calls the list funtion to print it all out
     */
    public static void displayListBookableRoomsMenu(University university, Scanner input){
        
        System.out.println("University of Knowledge - COVID test\n");
        
        List<String> items=new ArrayList<String>();
        for(BookableRoom i : university.getBookableRooms()){
            items.add(i.toString());
        }
        list(items);
        
        displayDefaultMenu(input, university);
        
        
    }
    /**
     * 
     * @param university this has a list of all the Bookable Rooms and will be altered to add the new BookableRoom
     * @param input this will handle user inputs
     * The idea of this method is that it will output all of the neccessary menu and then handle the inputs to ensure they
     * contain the information required for a new booking input.
     * It will split the input up and check the date is an actual date and then that the time is in the right range and finally
     *  that the id is also correct
     */
    public static void displayAddBookableRoomsMenu(University university, Scanner input){ // unfinished
        System.out.println("University of Knowledge - COVID test\n\nAdding bookable room\n");
        List<String> items=new ArrayList<String>(); 
        for(Room i : university.getRooms()){
            items.add(i.toString());
        }
        list(items);
        System.out.println("Please, enter one of the following:\n");
        System.out.println("The sequential ID listed to a room, a date (dd/mm/yyyy), and a time (HH:MM), separated by a white space.");
        String userInput = displayDefaultMenu(input, university);

        String[] userInputList = userInput.split(" ");
        Room room = university.getRooms().get(0); // only a temp data
        // checks input is correct
        if (userInputList.length == 3){
            String inputID = userInputList[0];
            String inputDate = userInputList[1];
            String inputTime = userInputList[2];
            boolean idCorrect = false;
            
            boolean timeCorrect = false;
            
            // checks date is correct
            boolean dateCorrect = checkDate(inputDate, university, input);
            // checks time is correct
            if(dateCorrect){
                int time = -1;
                if(inputTime.equals("09:00")){
                    time = 9;
                    timeCorrect = true;
                }
                else if(inputTime.equals("08:00")){
                    time = 8;
                    timeCorrect = true;
                }
                else if(inputTime.equals("07:00")){
                    time = 7;
                    timeCorrect = true;
                }
                
                else{
                    errorMenu("Your time was incompatable.");
                    displayAddBookableRoomsMenu(university, input);
                    return;
                }
                int id = 0;
                if (timeCorrect){
                    try{
                        id = Integer.parseInt(inputID);
                        
                    }
                    catch(NumberFormatException e){
                        errorMenu("The Id you entered was incomaptable.");
                        displayAddBookableRoomsMenu(university, input);
                        return;
                    }
                    if(id >= 11 && id <= university.getRooms().size()+10){
                        idCorrect = true;
                    }
                    else{
                        errorMenu("Your Id you entered was incompatable.");
                        displayAddBookableRoomsMenu(university, input);
                        return;
                    }

                
                    if(idCorrect){
                        BookableRoom bookRoom = new BookableRoom(university.getRooms().get(id-11), time, inputDate);
                        university.addBookableRoom(bookRoom);
                        System.out.println("Bookable Room added successfully:\n" + bookRoom.toString());
                        displayAddBookableRoomsMenu(university, input);
                        return;
                    }
                }
            }
        }
        errorMenu("Your input was invalid");
        displayAddBookableRoomsMenu(university, input);
    }
    /**
     * 
     * @param university this will have a list of all Bookable rooms
     * @param input this will handle the user inputs
     * This will provide a list of potential empty rooms that can be deleted and send these to be listed, 
     * it will then handle the user inputs and remove the Bookable room from the university if the input is valid,
     *  otherwise it will call the error menu and recursively call itself
     */
    public static void displayRemoveBookableRoomMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n");
        List<String> items = new ArrayList<String>();
        List<BookableRoom> possibleBookableRooms= new ArrayList<BookableRoom>();
        for(BookableRoom i : university.getBookableRooms()){
            if(i.checkStatusEMPTY()){
                items.add(i.toString());
                possibleBookableRooms.add(i);
            }
        }
        list(items);
        System.out.println("Removing bookable room \nPlease, enter one of the following:\nThe sequential ID to select the bookable room to be removed.");
        String userInput = displayDefaultMenu(input, university);
        int id = 0;
        try{
            id = Integer.parseInt(userInput);
            
        }
        catch(NumberFormatException e){
            errorMenu("The ID you entered was incomaptable.");
            displayRemoveBookableRoomMenu(university, input);
            return;
        }
        if(id >= 11 && id <= university.getBookableRooms().size() +10){
            BookableRoom temp = possibleBookableRooms.get(id-11);
            university.getBookableRooms().remove(temp);
            System.out.println("Bookable Room remove successfully:");
            System.out.println(temp.toString());
            displayRemoveBookableRoomMenu(university, input);
            return;
        }
        else{
            errorMenu("Your Id you entered was incompatable.");
            displayRemoveBookableRoomMenu(university, input);
            return; // this return will be to prevent it from doing the rest of the function when it goes through the stack call
        }
        
    }
    /**
     * 
     * @param university this contain a list of Assistants on shift to be listed
     * @param input this will handle the user inputs
     * This will call the list method and toString method to output all the assistant on shift in the right format
     */
    public static void displayListAssistantOnShiftMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n");
        
        List<String> items=new ArrayList<String>();
        for(AssistantOnShift i: university.getAssistantOnShifts()){
            items.add(i.toString());
        }
        list(items);
        
        
        displayDefaultMenu(input, university);
        
    }
    /**
     * 
     * @param university This will contain a a list of Assistants on shift to be added to and a list of the assistants to run through
     * to get an assistant to make the shift
     * @param input this will handle all the user inputs
     * this is very simular to the other add fucntions and it will run throught the asssitants and have the user enter
     * all the information to make a new assistant on shift , it will then validate all this inforamtion and check it is correct, if so
     * it will add the new assitant on shift
     * 
     */
    public static void displayAddAssistantOnShiftMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n\nAdding assistant on shift");
        List<String> items = new ArrayList<String>();
        for(Assistant i : university.getAssistant()){
            items.add(i.toString());
        }
        list(items);
        System.out.println("Please, enter one of the following:\n\nThe sequential ID of an assistant and date (dd/mm/yyyy), separated by a white space.");
        String userInput = displayDefaultMenu(input, university);
        String[] inputList = userInput.split(" ");
        String inputId = "";
        String inputDate = "";

        int id = 0;
        try{
            inputId = inputList[0];
            inputDate = inputList[1];
            id = Integer.parseInt(inputId);
        }
        catch(NumberFormatException e){
            errorMenu("The ID you entered was incompatable.");
            displayAddAssistantOnShiftMenu(university, input);
            return;
        }
        catch(ArrayIndexOutOfBoundsException e ){
            errorMenu("The ID you entered was incompatable.");
            displayAddAssistantOnShiftMenu(university, input);
            return;
        }
        if(checkDate(inputDate, university, input)){
            if( id >= 11 && id <= university.getAssistant().size()+10){
                for (int i = 0; i <=2; i ++){

                    AssistantOnShift temp = new AssistantOnShift(inputDate, university.getAssistant().get(id-11), i+7);
                    university.addAssistantOnShift(temp);
                    System.out.println("Assistant on shift added successfully:");
                    System.out.println(temp.toString());
                    
                }
                
                displayAddAssistantOnShiftMenu(university, input);
            }
            else{
                errorMenu("The Id you entered was incompatable.");
                displayAddAssistantOnShiftMenu(university, input);
                return;
            }
        }
        else{
            errorMenu("The date you entered was incompatable.");
            displayAddAssistantOnShiftMenu(university, input);
            return;
        }
        
        
    }
    /**
     * 
     * @param university contains list of assistants on shift to be deleted from
     * @param input handles all the user inputs
     * This will run through all of the assistants on shift which are avaliable to be deleted and then allow the user to enter which one the user wants to delete
     * The function will then handle this input and if valid will delete it from the list in university
     */
    public static void displayRemoveAssistantOnShiftMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n");
        List<String> items = new ArrayList<String>();
        List<AssistantOnShift> possibleAssistantOnShifts = new ArrayList<AssistantOnShift>();
        for(AssistantOnShift i : university.getAssistantOnShifts()){
            if(i.checkStatusFREE()){
                items.add(i.toString());
                possibleAssistantOnShifts.add(i);
            }
        }
        list(items);
        System.out.println("Removing assistant on shift \nPlease, enter one of the following:\nThe sequential ID to select the assistant on shift to be removed.");
        String userInput = displayDefaultMenu(input, university);
        int id = 0;
        try{
            id = Integer.parseInt(userInput);
            
        }
        catch(NumberFormatException e){
            errorMenu("The ID you entered was incomaptable.");
            displayRemoveAssistantOnShiftMenu(university, input);
            return;
        }
        if(id >= 11 && id <= university.getAssistantOnShifts().size() +10){
            AssistantOnShift temp = possibleAssistantOnShifts.get(id-11);
            university.getAssistantOnShifts().remove(temp);
            System.out.println("Assistant on Shift removed successfully:");
            System.out.println(temp.toString());
            displayRemoveAssistantOnShiftMenu(university, input);
            return;
        }
        else{
            errorMenu("Your Id you entered was incompatable.");
            displayRemoveAssistantOnShiftMenu(university, input);
            return;
        }
    

    }
    /**
     * 
     * @param university this will contain a list of Bookings to output
     * @param input this will handle any user input
     * Uses the list function to list out the current bookings for the university, the outputs
     * can be specified by the user to include all of the Bookings, the completed ones or the scheduled ones,
     * by default the function will print out all
     */
    public static void displayListBookingMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n\nSelect which booking to list:");
        System.out.println("1. All\n2. Only bookings status:SCHEDULED\n3. Only bookings status:COMPLETED");
        String userInput = displayDefaultMenu(input, university);
        if(userInput.equals("1")){
            List<String> items = new ArrayList<String>();
            for(Booking i: university.getBookingList()){
                items.add(i.toString());
            }
            list(items);
        }
        else if(userInput.equals("2")){
            List<String> items = new ArrayList<String>();
            for(Booking i: university.getBookingList()){
                if(!i.checkStatus()){
                   items.add(i.toString()); 
                }
                
            }
            list(items);
        }
        else if(userInput.equals("3")){
            List<String> items = new ArrayList<String>();
            for(Booking i: university.getBookingList()){
                if(i.checkStatus()){
                   items.add(i.toString()); 
                }
                
            }
            list(items);
        }
        else{
            errorMenu("The input was incompatable, printing all bookings");
            List<String> items = new ArrayList<String>();
            for(Booking i: university.getBookingList()){
                items.add(i.toString());
            }
            list(items);
        }
        displayDefaultMenu(input, university);
        return;
        
        
        
    }
    /**
     * 
     * @param university contains a list of booking to be added to and a list of Bookable rooms and aassitants on shift to assign to the booking
     * @param input this will handle the user inputs
     * This will run throught the bookable rooms and assistants on shift which have the same time or date and are avaliable and get the user to select on from each list,
     * if these are valid then the booking will be created with the correct information
     */
    public static void displayAddBookingMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n\nAdding booking (appointment for a COVID test) to the system\nList of available time-slots:");
        // print out and get list of avaliable items
        List<String> items = new ArrayList<String>();
        List<BookableRoom> rooms = new ArrayList<BookableRoom>();
        List<AssistantOnShift> assistants = new ArrayList<AssistantOnShift>();
        for(BookableRoom i : university.getBookableRooms()){
            for(AssistantOnShift m : university.getAssistantOnShifts()){
                
                if(i.getDate() == m.getDate()){
                    if(i.getTime() == m.getTime()){
                        if(!i.checkStatusFULL() && m.checkStatusFREE()){
                            items.add(i.getDate() +" 0" + String.valueOf(i.getTime()) + ":00");
                            rooms.add(i);
                            assistants.add(m);
                        } 
                    }
                }
            }
        }
        System.out.println("Please enter one of the following:\n\nThe sequential ID of an avaliable time-slot and the student email, separated by a white space.");
        list(items);
        String userInput = displayDefaultMenu(input, university);
        String[] uInput = userInput.split(" ");
        String dateTimeInput = "";
        String email = "";
        int temp = 0;
        String emailTest = "";
        try{
            dateTimeInput = uInput[0];
            email = uInput[1];
            temp =Integer.parseInt(dateTimeInput);
            emailTest = email.split("@")[1];
            System.out.println(emailTest);
            
        }
        catch(NumberFormatException e){
            errorMenu("The time was incomapatable.");
            displayAddBookingMenu(university, input);
            return;
        }
        catch(ArrayIndexOutOfBoundsException e){
            errorMenu("The input was incomapatable.");
            displayAddBookingMenu(university, input);
            return;
        }
        if(emailTest.equals("uok.ac.uk")){

        
            if(temp >= 11 && temp <= items.size()+10){
                BookableRoom bookableRoom = rooms.get(temp - 11);
                AssistantOnShift assistantOnShift = assistants.get(temp - 11);
                String date = bookableRoom.getDate();
                int time = bookableRoom.getTime();
                Booking booking = new Booking(time, date, email, university, bookableRoom, assistantOnShift);
                university.setBooking(booking);
                System.out.println("Booking added successfully:");
                System.out.println(booking.toString() + "\n");
                displayAddBookingMenu(university, input);
                return;
            }


        }
        else{
            errorMenu("The input was incompatable.");
            displayAddBookingMenu(university, input);
            return;
        }
        
    

        
    }
    /**
     * 
     * @param university this will have a list of Bookings to be removed by this function
     * @param input this will handle the user inputs
     * this will call the list function and get the user to select on from the list of Bookings to be removed,
     * like the functions for removing Bookable rooms and removing assitants on shift
     */
    public static void displayRemoveBookingMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n");
        List<String> items = new ArrayList<String>();
        List<Booking> possibleBookings = new ArrayList<Booking>();
        for(Booking i :university.getBookingList()){
            if(!i.checkStatus()){
              items.add(i.toString());  
              possibleBookings.add(i);
            }
            
        }
        list(items);
        System.out.println("Removing booking from the system\n\nPlease, enter one of the following:\n");
        System.out.println("The sequential ID to select the booking to be removed from the listed bookings above.");
        String userInput = displayDefaultMenu(input, university);
        int temp = 0;
        try{
            temp = Integer.parseInt(userInput);
        }
        catch(NumberFormatException e){
            errorMenu("The number you entered was incompatable.");
            displayRemoveBookingMenu(university, input);
            return;
        }
        if(temp >= 11 && temp <= items.size() + 10){
            Booking booking = possibleBookings.get(temp-11);
            university.getBookingList().remove(booking);
            System.out.println("Booking removed successfully:");
            System.out.println(booking.toString());
            displayRemoveBookingMenu(university, input);
            return;
        }
        else{
            errorMenu("The number you entered was incompatable");
            displayRemoveBookingMenu(university, input);
            return;
        }
    }
    /**
     * 
     * @param university this contains a list of Bookings to be completed
     * @param input this will handle the user inputs.
     * This funtion will allow the user to select from a list of Bookings, if valid it will then make the status complete
     */
    public static void displayConcludeBookingMenu(University university, Scanner input){
        System.out.println("University of Knowledge - COVID test\n");
        List<String> items = new ArrayList<String>();
        List<Booking> possibleBookings = new ArrayList<Booking>();
        for(Booking i :university.getBookingList()){
            if(!i.checkStatus()){
                items.add(i.toString());
                possibleBookings.add(i);
            }
            
        }
        list(items);
        System.out.println("Conclude booking\nPlease, enter one of the following:\n\nThe sequential ID to select the booking to be completed");
        String userInput = displayDefaultMenu(input, university);
        int temp = 0;
        try{
            temp = Integer.parseInt(userInput);
        }
        catch(NumberFormatException e){
            errorMenu("The number you entered was incompatable.");
            displayConcludeBookingMenu(university, input);
            return;
        }
        if(temp >= 11 && temp <= items.size()+10){
            Booking booking = possibleBookings.get(temp-11);
            booking.completeStatus();
            System.out.println("Booking completed successfully:");
            System.out.println(booking);
            displayConcludeBookingMenu(university, input);
            return;
            
        }
        else{
            errorMenu("The number you entered was incompatable");
            displayConcludeBookingMenu(university, input);
            return;
        }
    }
    /**
     * 
     * @param input this will handle user inputs
     * @param university this is used to recall the system 
     * @return the String this returns will be the user inputs as a response
     * This function is a generic fucntion for most user inputs in the system to output the 
     * default back to main menu or quit application options and then allow the user to input a response
     */
    public static String displayDefaultMenu(Scanner input, University university){
        System.out.println("0. Back to main menu");
        System.out.println("-1. Quit application\n");
        String userInput = input.nextLine();
        switch(userInput){
            case "0":
                system(university);
                System.exit(0); // this just ensure no mistakes are made with the stack calls
            case "-1":
                System.exit(0);
                break;
            
        }
        
        return userInput;

    }
    /**
     * 
     * @param errorMessage this String will be the specifics of what was wrong with the input to prevent it from happening again
     * This function will output a defult Error! and then the String input errorMessage
     */
    public static void errorMenu(String errorMessage){
        System.out.println("Error!");
        System.out.println(errorMessage);
    }
    /**
     * 
     * @param inputDate This will the the date that the user has input or what should have been the date
     * @param university 
     * @param input 
     * @return The return of this obejct will True if the date exists in the gregorian calendar (including dates before its invention for ease),
     * and false if it does not, like if is a leap day on a year that doesnt have a leap day.
     */
    public static boolean checkDate(String inputDate, University university, Scanner input){
        boolean leap = false;
        boolean dateCorrect = false;
        try{
            
            String[] date = inputDate.split("/");
            if (date.length == 3){

                if(Integer.valueOf(date[1]) == 2){
                    if (Integer.parseInt(date[2]) % 4 == 0){
                        leap = true;
                        if (Integer.parseInt(date[2]) % 100 == 0){
                            leap = false;
                            if(Integer.parseInt(date[2]) % 400 == 0){
                                leap = true;
                            }
                        }
                    }
                
                    if(leap){
                        if(Integer.parseInt(date[0]) >= 0 && Integer.parseInt(date[0]) <= 29){
                            dateCorrect = true;
                        }
                    }
                    else{
                        if(Integer.parseInt(date[0]) >= 0 && Integer.parseInt(date[0]) <= 28){
                            dateCorrect = true;
                        }
                    }
                }
                if (Integer.valueOf(date[1]) == 4 || Integer.valueOf(date[1]) == 6 || Integer.valueOf(date[1]) == 9 || Integer.valueOf(date[1]) == 11 ){
                    if(Integer.parseInt(date[0]) >= 0 && Integer.parseInt(date[0]) <= 30){
                        dateCorrect = true;
                    }
                }
                else if(Integer.valueOf(date[1]) == 1 || Integer.valueOf(date[1]) == 3 || Integer.valueOf(date[1]) == 5 || Integer.valueOf(date[1]) == 7 || Integer.valueOf(date[1]) == 8 || Integer.valueOf(date[1]) == 10 || Integer.valueOf(date[1]) == 12){
                    if(Integer.parseInt(date[0]) >= 0 && Integer.parseInt(date[0]) <= 31){
                        dateCorrect = true;
                    }
                }
            }
        }
        catch(NumberFormatException e){
            errorMenu("The date was incompatable.");
            displayAddBookableRoomsMenu(university, input);
            return false;
        } 
        catch(NullPointerException e){
            errorMenu("The date was incompatable.");
            displayAddBookableRoomsMenu(university, input);
            return dateCorrect = false;
        }
        return dateCorrect;
    }
   
}


