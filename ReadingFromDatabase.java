import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
//Changed ReadingFromDatabase to ReadingFromDatabase
public class ReadingFromDatabase {
    // creating a static scanner for all user inputs
    static Scanner userInput = new Scanner(System.in);
    // Creating a method called getUserNum() to get
    // numbers from the user using a try and catch block.
    public float getUserNum(String instruction) {
        while (true) {
            try {
                System.out.println(instruction);
                return Float.parseFloat(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Enter the correct value.");
            }
        }
    }
    // Creating a method called getString() to get
    // string values from the user using a try and catch block.
    public String getString(String instruction) {

        while(true) {
            try {
                System.out.println(instruction);
                return userInput.nextLine();
            } catch(Exception e) {
                System.out.println("Please enter a string of letters.");
            }
        }
    }

    // Creating a method called dateCreator() to add
    // the date in a set format for each project.*/
    public String createDate(){
        String dateValue;
        // Creating an object of the SimpleDateFormat method and passing the format
        //that the user should enter.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        while(true){
            // Collecting the date from the user
            // to be able to do the necessary comparisons for the datetime
            // then returning the project deadline
            try{
                Date deadline = dateFormat.parse(getString(" "));
                dateValue = dateFormat.format(deadline);
                break;
            }
            catch(ParseException pe){
                System.out.println("Please enter the date in the format stated above.");
            }
        }

        return dateValue;
    }

    // Creating a method to connect to the MySQL database being poisepms with
    // the url, username and password
    public static Connection connect() {

        //  connecting to the poisepms database
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/PoisePMS?useSSL=false",
                    "structural engineer",
                    "poised"
            );
        }
        catch(SQLException eSQL){
            System.out.println("Connection Error.");
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
        return connection;
    }
    // Creating a statement method to create a statement which connects to the database
    public static Statement statement(){
        Statement statement = null;

        try{
            statement = connect().createStatement();
        }
        catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
        return statement;
    }
    // Created a toStringProject method to print out the records of the projects table to the user's console.
    public static void toStringProject(ResultSet projectResults) throws SQLException {
        while(projectResults.next()){
            System.out.println("Project Number: " + projectResults.getInt("ProjectNumber") + " | "
                    + "Project Name: " + projectResults.getString("ProjectName") + " | "
                    + "Type Of Building: " + projectResults.getString("type_of_building") + " | "
                    + "Physical Address: " + projectResults.getString("PhysicalAddress") + " | "
                    + "ERF Number: " + projectResults.getInt("ErfNumber") + " | "
                    + "Total Fee: " + projectResults.getString("TotalFee") + " | "
                    + "Total Amount Paid to Date: " + projectResults.getString("TotalAmountPaidDate") + " | "
                    + "Deadline : " + projectResults.getString("Deadline") + " | "
                    + "Architect ID: " + projectResults.getString("ArchitectID") + " | "
                    + "Structural Engineer ID: " + projectResults.getInt("StructuralEngineerID") + " | "
                    + "Customer ID: " + projectResults.getInt("CustomerID") + " | "
                    + "ProjectManager ID: " + projectResults.getInt("ProjectManagerID") + " | "
                    + "Status : " + projectResults.getString("Status") + " | "

            );
        }
    }
    // Created a toStringArchitect method to print out the records of the projects table to the user's console.
    public static void toStringArchitect(ResultSet architectResults) throws SQLException {
        while(architectResults.next()){
            System.out.println("ID: " + architectResults.getInt("ID") + " | "
                    + "Name: " + architectResults.getString("Name") + " | "
                    + "Surname: " + architectResults.getString("Surname") + " | "
                    + "Phone Number: " + architectResults.getString("TelephoneNumber") + " | "
                    + "Email Address: " + architectResults.getString("EmailAddress") + " | "
                    + "Physical Address: " + architectResults.getString("PhysicalAddress") + " | "
            );
        }
    }
    // Created a toStringStruEng method to print out the records of the projects table to the user's console.
    public static void toStringStruEng(ResultSet customerResults) throws SQLException {
        while(customerResults.next()){
            System.out.println("ID: " + customerResults.getInt("ID") + " | "
                    + "Name: " + customerResults.getString("Name") + " | "
                    + "Surname: " + customerResults.getString("Surname") + " | "
                    + "Phone Number: " + customerResults.getString("TelephoneNumber") + " | "
                    + "Email Address: " + customerResults.getString("EmailAddress") + " | "
                    + "Physical Address: " + customerResults.getString("PhysicalAddress") + " | "
            );
        }
    }
    // Created a toStringCustomer method to print out the records of the projects table to the user's console.
    public static void toStringCustomer(ResultSet projectManagerResults) throws SQLException {
        while(projectManagerResults.next()){
            System.out.println("ID: " + projectManagerResults.getInt("ID") + " | "
                    + "Name: " + projectManagerResults.getString("Name") + " | "
                    + "Surname: " + projectManagerResults.getString("Surname") + " | "
                    + "Phone Number: " + projectManagerResults.getString("TelephoneNumber") + " | "
                    + "Email Address: " + projectManagerResults.getString("EmailAddress") + " | "
                    + "Physical Address: " + projectManagerResults.getString("PhysicalAddress") + " | "
            );
        }
    }
    // Created a toStringProMan method to print out the records of the projects table to the user's console.
    public static void toStringProMan(ResultSet structuralEngineerResults) throws SQLException {
        while(structuralEngineerResults.next()){
            System.out.println("ID: " + structuralEngineerResults.getInt("ID") + " | "
                    + "Name: " + structuralEngineerResults.getString("Name") + " | "
                    + "Surname: " + structuralEngineerResults.getString("Surname") + " | "
                    + "Phone Number: " + structuralEngineerResults.getString("TelephoneNumber") + " | "
                    + "Email Address: " + structuralEngineerResults.getString("EmailAddress") + " | "
                    + "Physical Address: " + structuralEngineerResults.getString("PhysicalAddress") + " | "
            );
        }
    }
}
