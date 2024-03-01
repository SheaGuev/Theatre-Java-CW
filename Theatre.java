package JavaCoursework;

import java.util.Date; 

import java.util.Scanner;

import javax.lang.model.element.Element;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;



public class Theatre {

    
    private static ArrayList<Ticket> ticketList = new ArrayList<>();
    static int[] row1 = new int[12];
  static  int[] row2 = new int[16];  // create 3 arrays to store seat info 
  static  int[] row3 = new int[20];

      // Declaring jagged array with 3 rows to be accessed by all methods // storing aboce array in jagged array to be accessed easily later
     static int allrows[][] = new int[][]{
 
      // Making the above array Jagged

      // First row has 12 columns
      row1 = new int[12],

      // Second row has 16 columns
      row2 = new int[16],

      row3 = new int[20]

     };
      
   // declaring input in class so can be accessed through all methods
  static Scanner Input = new Scanner(System.in);

    public static void main (String[] args ) {

        



        System.out.println("Welcome to the New Theatre!");

     
       // System.out.println(row1[0])

       
       

          //MENU TEMPLATE 
             
       Multi("-", 20);
       System.out.println();
       System.out.println("Please select an option:");
       System.out.println("\n" + "1) Buy a ticket");
       System.out.println("\n" + "2) Print seating area");
       System.out.println("\n" + "3) Cancel ticket ");
       System.out.println("\n" + "4) List available seats");
       System.out.println("\n" + "5) Save to file ");
       System.out.println("\n" + "6) Load from file");
       System.out.println("\n" + "7) Print ticket information and total price ");
       System.out.println("\n" + "8) Sort tickets by price ");
       System.out.println("\n" + "0) Quit ");
       System.out.println();
       Theatre.Multi("-", 20);
       System.out.println();

       

        //GETTING USER INPUT FOR MENU SELECTION
        
        try{
        
        int menu_option = Input.nextInt();

        // if(menu_option == 0) {
        //     System.out.println("Terminating...");
        //     System.exit(0);
        // }
        switch (menu_option){
            case  0:
            System.out.println("Terminating...");
            System.exit(1);

            case 1: 
            buy_ticket();
            break;

            case 2:
            print_seating_area();
            break;

            case 3:
            cancel_ticket();
            break;

            case 4:
            show_available();
            break;
            
            case 5:
            save_file();
            break;

            case 6:
            file_read();
            break;

            case 7:
            show_ticket_info();
            break;

            case 8:
            sort_tickets();
            break;

            default:
            System.out.println("Incorrect selection");
            main(null);
            break;
        }
    } catch (InputMismatchException e) {// CATCH INPUT ERROR 
        System.out.println("Input is invalid, please try again...");
       // System.out.println("Enter any character to continue...");
       String next = Input.next(); // stop input from repeating upon main call
        main(null);
                        }
        }   
    

    //STRING MULTIPLIER CALLED FOR MENU
     static void Multi(String a, int b){

        for(int i =0; i<b; i++){
            System.out.print(a);
        }

    }

    //METHOD TO BUY TICKETS 
    public static void buy_ticket(){

        try{

        System.out.println("Please enter the row you'd like to book:");

        //Scanner Input = new Scanner(System.in);

        int row_numb_in = Input.nextInt(); //get row input

        System.out.println("Now enter the seat number:");

        int seat_numb = Input.nextInt(); // get seat input

        int sys_seat = seat_numb-1; // creating 2 variables used to access the array

        int sys_row = row_numb_in-1;

        
        //HashMap<int 1, row1> ye = new HashMap<int, int[]>();

        //CHECKING ROW IS VALID BEFORE ATTEMPTING TO ACCESS ARRAY TO AVOID ERROR 
        //HELPS SEPERATE ERRORS TO DISTINGUISH BETWEEN INCORRECT ROW OR SEAT
        if((sys_row == 0) || (sys_row == 1) || (sys_row == 2)){
        if((seat_numb <= allrows[sys_row].length) && seat_numb > 0){

            if(allrows[sys_row][sys_seat] == 0) { //IF THE SEAT IS NOT BOOKED THEN CHANGE ITS STATUS
            allrows[sys_row][sys_seat] = 1;

            System.out.println("Enter your name:");
            String name = Input.next(); 
            System.out.println("Now your surname:");
            String surname = Input.next();
            System.out.println("Finally, please enter your email:");
            String email = Input.next();
            Person person = new Person(name, surname, email); // adds all variables to the person to be stored 
            double price = fixprice(sys_row); // calculates price 
            // double price = calculatePrice(sys_row, sys_seat);
            Ticket ticket = new Ticket(sys_row, sys_seat, price, person);
            ticketList.add(ticket);
    

             System.out.println("Seat has now been booked, below is the new seating arrangement as follows...");

            System.out.println(Arrays.toString(allrows[0]) + "\n" + Arrays.toString(allrows[1]) + "\n" + Arrays.toString(allrows[2]));

            ReturnOr("If you would like to book another ticket, press '1', or to go back to the main menu, press '0'. ");
            buy_ticket();
           // System.out.println(Arrays.toString(row1) + "\n" + Arrays.toString(row2) + "\n" + Arrays.toString(row3));
            }

            else{ //IF ARRAY IS NOT 0 IT IS BOOKED 
                System.out.println("Seat is already booked");
                ReturnOr("If you would like to choose another option, press '1', or to go back to the main menu, press '0'. ");
                //CALLS A METHOD WITH CUSTOM MESSAGE TO GIVE USER OPTION TO GO BACK OR TRY AGAIN
                buy_ticket();   
            }
       
    }
            
    else{//ELSE FOR SEAT NUMBER NOT EXISTING WITHIN THE CONDITIONAL
        System.out.println("Seat Number is not found in row");
        ReturnOr("If you would like to choose another option, press '1', or to go back to the main menu, press '0'.");
        //CALLS A METHOD WITH CUSTOM MESSAGE TO GIVE USER OPTION TO GO BACK OR TRY AGAIN
        buy_ticket();
          }   
        }

        else{//FINAL ELSE FOR IF THE ROW NUMBER IS NOT 1, 2, OR 3
            System.out.println("Row number is not valid");
            ReturnOr("If you would like to choose another option, press '1', or to go back to the main menu, press '0'.");
            buy_ticket();
        }



    } catch (InputMismatchException e) {// CATCH INPUT ERROR 
        System.out.println("Input is invalid, please try again.");
        Input.nextLine();
        buy_ticket();
    }
    }//END OF METHOD
    private static double fixprice(int row){ // method to calculate price of ticket based on row
        double price = 0;
        switch(row){
            case 0:
            price = 39.99;
            return price;
            case 1:
            price = 27.99;
            return price;
            case 2:
            price = 16.99;
            return price;
            default:
            return price;
            
        }
        
        
    }

 //NEW METHO TO RETURN TO MENU OR CHOOSE ANOTHER OPTIION IF INVALID
 static void ReturnOr(String Message_part1){

    System.out.println(Message_part1);
    while (true){
        try{
        int option = Input.nextInt(); 
        if (option == 1){
            // BELOW EACH CALL OF THE METHOD WILL BE THE CALL OF THE METHOD IT IS CONTAINED WITHIN, ALLOWING THE USER TO TRY AGAIN WHEN RETURN IS CALLED
            return;
        }
        else if(option == 0){
            main(null);
            
        }

        else{
            System.out.println("Please enter only 1, or 0.");
            continue;
             }
        } catch (InputMismatchException e) {
            System.out.println("Input is invalid, please try again.");
            Input.nextLine();
            continue;
        }
    }
 }//END OF METHOD

//Print seating area method
static void print_seating_area(){
    
    Multi(" ", 3);
    Multi("*", 15); // CREATES REQUIRED SPACE TO ALIGN TO MIDDLE
    System.out.println("\n");
    Multi(" ", 3);
    System.out.println("*    " + "STAGE" + "    *" + "\n");
    Multi(" ", 3);
    Multi("*", 15);
    System.out.println();//CREATING STAGE LAYOUT UP FRONT

    for(int i = 0; i < allrows.length; i++){ // ACCESS ALL 3 ROWS IN ARRAY
        System.out.println(); //PRINT SPACE BETWEEN ROWS
        int space_numb = (20 - allrows[i].length)/2; //GET DIFFERENCE BEWTEEN 20 (MAX LENGTH)  AND THE LENGTH OF THE ROW TO FIND HOW MANY SPACES NEEDED
        Multi(" ", space_numb); //WILL PRINT DESIRED SPACE FOR EACH ROW TO ALIGN EACH ROW TO THE MIDDLE
        for(int j = 0; j < allrows[i].length; j++){
            if(j == allrows[i].length/2){
                System.out.print(" ");
            }
            if(allrows[i][j] == 0){
                System.out.print("O");
            }
            else{
                System.out.print("X");
            }
        }
    }
    System.out.println("\n");
    ReturnOr("Press 1 or 0 to return to menu.");
   main(null);
}


static void cancel_ticket(){
    try{

        System.out.println("Please enter the row of your ticket:");

        //Scanner Input = new Scanner(System.in);

        int row_numb_in = Input.nextInt(); //get row input

        System.out.println("Now enter the seat number:");

        int seat_numb = Input.nextInt(); // get seat input

        int sys_seat = seat_numb-1; // creating 2 variables used to access the array

        int sys_row = row_numb_in-1;

        
    

        //CHECKING ROW IS VALID BEFORE ATTEMPTING TO ACCESS ARRAY TO AVOID ERROR 
        //HELPS SEPERATE ERRORS TO DISTINGUISH BETWEEN INCORRECT ROW OR SEAT
        if((sys_row == 0) || (sys_row == 1) || (sys_row == 2)){
        if((seat_numb <= allrows[sys_row].length) && seat_numb > 0){

            if(allrows[sys_row][sys_seat] == 1) { //IF THE SEAT IS  BOOKED THEN CHANGE ITS STATUS
            allrows[sys_row][sys_seat] = 0;

             System.out.println("Seat has now been cancelled, below is the new seating arrangement as follows...");

            System.out.println(Arrays.toString(allrows[0]) + "\n" + Arrays.toString(allrows[1]) + "\n" + Arrays.toString(allrows[2]));

            for (Ticket ticket : ticketList) {
                if (ticket.getRow() == sys_row && ticket.getSeat() == sys_seat) {
                    ticketList.remove(ticket);}}
    

            ReturnOr("If you would like to cancel another ticket, press '1', or to go back to the main menu, press '0'. ");
            buy_ticket();

            
           // System.out.println(Arrays.toString(row1) + "\n" + Arrays.toString(row2) + "\n" + Arrays.toString(row3));
            }

            else{ //IF ARRAY IS NOT 0 IT IS BOOKED 
                System.out.println("Seat does not have a booking.");
                ReturnOr("If you would like to choose another option, press '1', or to go back to the main menu, press '0'. ");
                //CALLS A METHOD WITH CUSTOM MESSAGE TO GIVE USER OPTION TO GO BACK OR TRY AGAIN
                cancel_ticket();   
            }
       
    }
            
    else{//ELSE FOR SEAT NUMBER NOT EXISTING WITHIN THE CONDITIONAL
        System.out.println("Seat Number is not found in row");
        ReturnOr("If you would like to choose another option, press '1', or to go back to the main menu, press '0'.");
        //CALLS A METHOD WITH CUSTOM MESSAGE TO GIVE USER OPTION TO GO BACK OR TRY AGAIN
        cancel_ticket();
          }   
        }

        else{//FINAL ELSE FOR IF THE ROW NUMBER IS NOT 1, 2, OR 3
            System.out.println("Row number is not valid");
            ReturnOr("If you would like to choose another option, press '1', or to go back to the main menu, press '0'.");
            cancel_ticket();
        }



    } catch (InputMismatchException e) {// CATCH INPUT ERROR 
        System.out.println("Input is invalid, please try again.");
        Input.nextLine();
        buy_ticket();
    }

}//END OF CANCEL METHOD

static void show_available(){


    for(int i =0; i<allrows.length; i++){ // cycles through arrays to print out the items equal to 0
       int g = i + 1;
        System.out.println();
        System.out.println("Seat available in row " + g + ": ");
        for(int j=0; j<allrows[i].length; j++){
            if(allrows[i][j] == 0){
               int f = j +1;
                System.out.print(f + ", ");
            }

        }
    }
    System.out.println();
    ReturnOr("Press 1 or 0 to return to menu.");
   main(null);

}


static void save_file(){
   

try{
    File file = new File("text.txt");

    boolean file_created = file.createNewFile();
    
    if (file_created){
        System.out.println("File created: " + file.getName());
        file_writer(file);
    }
   
    else{
        if (file.exists()){
        System.out.println("File already exists.");
        main_delay();
        }
        else{
            System.out.println("Error while creating file: " + file.getName());
            main_delay();
            }
    }
    
}
catch (IOException e) {
    System.out.println("Error while accessing file...");
    e.printStackTrace();
    main_delay();

}


}

static void file_writer(File file){
   try{ 
    FileWriter filewriter = new FileWriter("text.txt");
    for(int i = 0; i<row1.length; i++){
        String t = Integer.toString(row1[i]); 
        filewriter.write(t);
        filewriter.write("\n");
    }

        for(int j = 0; j<row2.length; j++){
            String l = Integer.toString(row2[j]); 
            filewriter.write(l);
            filewriter.write("\n");
        }

            for(int k = 0; k<row3.length; k++){
                String m = Integer.toString(row3[k]); 
                filewriter.write(m);
                filewriter.write("\n");
            }
        
            filewriter.close();
            main_delay();

    } catch(IOException e){
        System.out.println("Error while accessing file...");
        e.printStackTrace();
        main_delay();
    }
    
    
   
    
}
static void file_read(){
    File file = new File("text.txt");
    if(file.exists()){
    Scanner file_reader;
    try {
        file_reader = new Scanner(file);
        while(file_reader.hasNextLine()){
            for( int loop1 = 0 ; loop1 <12; loop1++){
            int file_array = Integer.valueOf(file_reader.nextLine());
            row1[loop1] = file_array;
          
            }
            for( int loop2 = 0; loop2 < 16; loop2++){
            int file_array = Integer.valueOf(file_reader.nextLine());
            row2[loop2] = file_array;
           
            }

            for( int loop3 = 0; loop3 < 20; loop3++){
                int file_array = Integer.valueOf(file_reader.nextLine());
                row3[loop3] = file_array;
                
                }
                main(null);
        }
    } catch (FileNotFoundException e) {
        // catch block
        e.printStackTrace();
    }}

    else{
        System.out.println();
        System.out.println("No save file has been found...");
        //REMMEBER TO DELETE THE FILE HERE
        
        main_delay();
    }
            
}
static void main_delay(){ // METHOD TO BE USED WHEN ERRORS OCCUR AND PROGRAM HAS TO RETURN TO THE MAIN MENU
    try{
        System.out.println();
        Thread.sleep(625);
    System.out.println("returning to menu...");
    Thread.sleep(1250);
    System.out.println("...");
    Thread.sleep(1250);
    main(null);
    } catch(InterruptedException ex){
        ex.printStackTrace();
        System.out.print("Error returning to menu - trying again");
        main(null);


    }

}

static void show_ticket_info(){
    double price_total = 0;
for (Ticket ticket : ticketList){
    System.out.println();
    ticket.print();
    price_total += ticket.getPrice();
}
System.out.println("The total price of all tickets is £" + price_total);
System.out.println("Enter any input to return to menui...");
String next = Input.next();
main(null);
}

static void sort_tickets(){

    ArrayList<Ticket> cloneArray = (ArrayList<Ticket>)ticketList.clone(); //Clone tickets so we dont change usual order

    // Sort tickets by price in ascending order
        int bottom = cloneArray.size() - 2;
        Ticket temp;
        Ticket temp2;

        boolean exchanged = true;
         while (exchanged) {
             exchanged = false;

            for (int i = 0; i <= bottom; i++) {
                 if ((cloneArray.get(i)).getPrice() > cloneArray.get(i + 1).getPrice()) {
                     temp = cloneArray.get(i);
                     temp2 = cloneArray.get(i+1);
                     cloneArray.set(i, temp2);
                     cloneArray.set(i + 1, temp);

                     exchanged = true;  
                    }
    }
    bottom--;
    }

    for(Ticket ticket : cloneArray){
        System.out.println();
        ticket.print();
    }
    System.out.println();

    System.out.println("Enter any input to return to menui...");
String next = Input.next();
main(null);

}
}


