import java.sql.*;
public class FindProjects {

    // Creating an object called projectMethods of the ReadingFromDatabase Class
    // Creating an object of the connection 'projectMethods' from the ReadingFromDatabase Class and statement.
    // 'rowsAffected' is a variable that executes the Prepared SQL statements
    ReadingFromDatabase projectMethods = new ReadingFromDatabase();
    Connection connection = projectMethods.connect();
    Statement statement = projectMethods.statement();


    //The 'Menu' method is to present the user with a menu to Update projects.
     // and performs necessary functions based on user Input.

    public void userMenu(){

        System.out.println("You chosen to edit existing projects.");
        System.out.println("""
						Please select one of the following options:\s
						1 - Update deadline.
						2 - Update the amount paid to date by the customer.
						3 - Update the Structural Engineer's details.
						4 - Finalise project and Issue an invoice for the customer.
						5 - Delete an existing project.""");
        float menuOption2 = projectMethods.getUserNum(" ");

        // Using switch-case statements for each menu option
        // The first menu option is allowing the user to edit a deadline
        // The second is to allow a user to update the amount paid by the customer
        // lastly to allow the user to finalise a project and print an invoice for the project
        switch ((int) menuOption2) {
            case 1 -> UpdateDeadline();
            case 2 -> UpdateAmount();
            case 3 -> editStructEngDetails();
            case 4 -> finalise();
            case 5 -> DeleteProject();
            default -> System.out.println("Enter the correct value.");
        }
    }

    // The method UpdateDeadline allows the user to Update the deadline by
    public void UpdateDeadline(){

        // first asking the user to enter the project number of the project they want to update
        // Using the dateCreator() method to update the project deadline
        // and using the getString() method for user inputs*/
        System.out.println("\nUpdate the project deadline by following the instructions below\n.");
        System.out.println("Enter the project number: ");
        float projectNumber = projectMethods.getUserNum(" ");

        System.out.println("\nEnter the new deadline in yyyy-mm-dd format: ");
        String newProjectDeadline = projectMethods.createDate();

        // Using a try-catch block to handle SQL queries
        try{
            // Creating a query to update the ProjectDeadline column based on the project number
            // using PreparedStatement to execute the query
            // setting the parameters for the new deadline and project number in the PreparedStatement
            String setNewDeadlineQuery  = "UPDATE poisepms.poisedproject SET Deadline=? WHERE ProjectNumber=?";
            PreparedStatement setDeadline = connection.prepareStatement(setNewDeadlineQuery);
            setDeadline.setString(1, newProjectDeadline);
            setDeadline.setFloat(2, projectNumber);
            boolean success =  setDeadline.execute();
            System.out.println("Row Affected" + success);
            
        }
        // Printing the stack trace for any error that might occur
        catch(SQLException eSQL){
            System.out.println("Wrong SQL Statement");
            eSQL.printStackTrace();
        }
    }

    // Creating an UpdateAmount() method to change the amount paid by the customer.
    public void UpdateAmount(){

        // Asking the user to enter the project number of the project they want to update
        // Asking the user to enter the amount paid by the customer
        System.out.println("\nUpdate the customer amount by following the instructions below:\n");
        System.out.println("Enter project number: ");
        float projNum = projectMethods.getUserNum(" ");
        System.out.println("\nEnter the amount paid by the customer: R");
        float newAmountPaid = projectMethods.getUserNum(" ");

        // Using a try-catch block to handle SQL queries
        try{
            // Creating a query to update the AmountPaidToDate column based on the project number
            // using PreparedStatement to execute the query
            // setting the parameters in the PreparedStatement
            String setAmountPaidQuery  = "UPDATE poisepms.poisedproject SET TotalAmountPaidDate=? WHERE ProjectNumber=?";
            PreparedStatement setAmount = connection.prepareStatement(setAmountPaidQuery);
            setAmount.setFloat(1, newAmountPaid);
            setAmount.setFloat(2, projNum);
            boolean amount = setAmount.execute();


            // Creating a query to extract the AmountPaidToDate value based on the project number
            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            String findAmountPaidQuery = "SELECT poisepms.poisedproject.TotalAmountPaidDate FROM poisepms.poisedproject " +
                    "WHERE ProjectNumber=?";
            PreparedStatement findAmountPaidPrepStmnt = connection.prepareStatement(findAmountPaidQuery);
            findAmountPaidPrepStmnt.setFloat(1, projNum);
            boolean foundAmount = findAmountPaidPrepStmnt.execute();
            ResultSet amountResult = findAmountPaidPrepStmnt.executeQuery();
            amountResult.next();

            // Creating an integer variable of the value in the column
            // Creating a query to extract the CustomerFee value based on the project number
            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            float paid = Float.parseFloat(String.valueOf(amountResult.getString("TotalAmountPaidDate")));
            String findCustFeeQuery = "SELECT TotalFee FROM poisepms.poisedproject WHERE ProjectNumber=?";
            PreparedStatement customerInv = connection.prepareStatement(findCustFeeQuery);
            customerInv.setFloat(1, projNum);
            boolean customerFee = customerInv.execute();
            ResultSet custFeeResult = customerInv.executeQuery();
            custFeeResult.next();

            // creating an integer variable of the value in the column
            // Printing a message for the user if the customer has paid in full
            float fee = Float.parseFloat(String.valueOf(custFeeResult.getString("TotalFee")));
            if (paid == fee) {
                System.out.println("\nCustomer has paid in full.");
            }

        } catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }
    // Creating an UpdateContractor() method to update the structuralEngineer's details
    public void editStructEngDetails(){
        // Asking the user to enter the ID of the Structural Engineer that they want to edit
        // Using the getString() method to get the new information from the user to populate the Structural Engineer details
        System.out.println("\nUpdate the Structural Engineer's details");
        System.out.println("Enter the ID of the Structural Engineer : ");
        float stEngID = projectMethods.getUserNum(" ");

        System.out.println("Structural Engineer's first name: ");
        String structuralEngineerName = projectMethods.getString(" ");

        System.out.println("\nStructural Engineer's surname: ");
        String structuralEngineerSurname = projectMethods.getString(" ");

        System.out.println("\nStructural Engineer's phone number: ");
        String structuralEngineerPhoneNumber = projectMethods.getString(" ");

        System.out.println("\nStructural Engineer's email address: ");
        String structuralEngineerEmail = projectMethods.getString(" ");

        System.out.println("\nStructural Engineer's Address: ");
        String structuralEngineerAddress = projectMethods.getString(" ");

        System.out.println("Structural Engineer Details updated.");

        // Using a try-catch block to handle SQL queries
        try{
            // Creating a query to update the AmountPaidToDate column based on the project number
            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            String updateStEngQuery  = "UPDATE poisepms.structuralengineer SET Name=?, Surname=?, " +
                    "TelephoneNumber=?, " +
                    "EmailAddress=?, PhysicalAddress=? WHERE ID=?";
            PreparedStatement sturcEngDetails = connection.prepareStatement(updateStEngQuery);
            sturcEngDetails.setString(1, structuralEngineerName);
            sturcEngDetails.setString(2, structuralEngineerSurname);
            sturcEngDetails.setString(3, structuralEngineerPhoneNumber);
            sturcEngDetails.setString(4, structuralEngineerEmail);
            sturcEngDetails.setString(5, structuralEngineerAddress);
            sturcEngDetails.setFloat(6, stEngID);
            boolean newSEDetails = sturcEngDetails.execute();

        } catch(SQLException eSQL){
            eSQL.printStackTrace();
        }
    }

    // Creating a FinaliseProject() method to finalise a project and print an invoice for that project
    public void finalise(){

        System.out.println("\n Finalise project by printing out an invoice for the customer\n.");

        // Asking the user to enter the project number of the project they want to finalise
        System.out.println("Enter project number of the project: ");
        float proNumber = projectMethods.getUserNum(" ");

        // Asking the user to enter the amount paid by the customer
        System.out.println("\nEnter the amount paid by the customer: R");
        float newAmountPaid = projectMethods.getUserNum(" ");

        // Adding the completion date using the dateCreator() method
        System.out.println("\nEnter the completion date in yyyy-mm-dd format: ");
        String newCompletionDate = projectMethods.createDate();

        // Using a try-catch block to handle SQL queries
        try{
            // Creating ResultSet objects of queries to find the information about the project and the customer
             //from each of their tables based on the project number entered by the user
            // Creating a query to update the projects table
            ResultSet projectResults = statement.executeQuery("SELECT * FROM poisepms.poisedproject " +
                    " WHERE ProjectNumber = "+proNumber+"");
            String finalProjQuery  = "UPDATE poisepms.poisedproject SET Status=?, Deadline=?," +
                    " TotalAmountPaidDate=? WHERE ProjectNumber=?";

            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            PreparedStatement finalProject = connection.prepareStatement(finalProjQuery);
            finalProject.setString(1, "Complete");
            finalProject.setString(2, newCompletionDate);
            finalProject.setFloat(3, newAmountPaid);
            finalProject.setFloat(4, proNumber);
            boolean completeProject = finalProject.execute();


            // Creating a query to extract the AmountPaidToDate value based on the project number
            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            String findAmountPaidQuery = "SELECT poisepms.poisedproject.TotalAmountPaidDate FROM poisepms.poisedproject WHERE ProjectNumber=?";
            PreparedStatement findAmountPaidPrepStmnt = connection.prepareStatement(findAmountPaidQuery);
            findAmountPaidPrepStmnt.setFloat(1, proNumber);
            boolean paidAmount = findAmountPaidPrepStmnt.execute();
            ResultSet amountResult = findAmountPaidPrepStmnt.executeQuery();
            amountResult.next();

            // Creating an integer variable of the value in the column
            // Creating a query to extract the CustomerFee value based on the project number
            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            float paid = Float.parseFloat(String.valueOf(amountResult.getString("TotalAmountPaidDate")));
            String findCustFeeQuery = "SELECT poisepms.poisedproject.TotalFee FROM poisepms.poisedproject WHERE ProjectNumber=?";
            PreparedStatement findCustFeePrepStmnt = connection.prepareStatement(findCustFeeQuery);
            findCustFeePrepStmnt.setFloat(1, proNumber);
            boolean getCustomerFee = findCustFeePrepStmnt.execute();
            ResultSet custFeeResult = findCustFeePrepStmnt.executeQuery();
            custFeeResult.next();

            // creating an integer variable of the value in the column
            // Calculating the outstanding amount to be paid by the customer
            float fee = Float.parseFloat(String.valueOf(custFeeResult.getString("TotalFee")));
            float outstandingAmount = fee - paid;

            // Printing out a customer invoice
            System.out.println("Project details\n: ");
            projectMethods.toStringProject(projectResults);
            System.out.println("\nAMOUNT OUTSTANDING: R" + outstandingAmount);

        } catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }

    // Creating a DeleteProject() method to delete a project.
    public void DeleteProject(){
        // Asking the user to enter the project number of the project they want to delete.
        System.out.println("You have chosen to delete a project.");
        System.out.println("Please enter the project number of the project you wish to delete: ");
        float prNum = projectMethods.getUserNum(" ");

        // Using a try-catch block to handle SQL queries
        try{
            // Creating a query to delete the row from the projects table based on the project number
            // Using PreparedStatement to execute the query
            // Setting the parameters in the PreparedStatement
            String prQuery  = "DELETE FROM poisepms.poisedproject WHERE ProjectNumber=?";
            PreparedStatement projPreparedStatement = connection.prepareStatement(prQuery);
            projPreparedStatement.setFloat(1, prNum);
            boolean drop = projPreparedStatement.execute();
            System.out.println("Project Deleted.");

        } catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }
    // Creating a viewAll() method to allow a user to see all the entries in the projects table
    public void viewAll(){

        try{
            // Using ResultSet to select and print out all the entries in the projects table
            ResultSet projectsResult = statement.executeQuery("SELECT * FROM poisepms.poisedproject");
            projectMethods.toStringProject(projectsResult);
        }

        catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }
    // Creating a viewIncomplete() method to allow a user to see all the incomplete entries in the projects table
    public void viewIncomplete(){

        try{
            // Using ResultSet to select and print out all the entries in the projects table based on
            // if the project is incomplete or not
            ResultSet projectsResult = statement.executeQuery("SELECT * FROM poisepms.poisedproject " +
                    "WHERE Status = 'Incomplete'");
            projectMethods.toStringProject(projectsResult);
        } catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }
    // Creating a viewOverdue() method to allow a user to see all the overdue entries in the projects table
    public void viewOverdue(){

        try{
            //Using ResultSet to select and print out all the entries in the projects table based on
            // if the project is overdue or not
            ResultSet projectsResult = statement.executeQuery("SELECT * FROM poisepms.poisedproject " +
                    "WHERE Deadline < date(now()) AND Status = 'Incomplete'");
            projectMethods.toStringProject(projectsResult);

        } catch(SQLException eSQL){
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }
}