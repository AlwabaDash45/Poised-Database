import java.sql.*;
//Change ProjectReader ProjectPoised
class ProjectReader {

    // Creating an object of the ReadingFromDatabase class
    // Creating a connection to the database with the variable 'connection'
    // 'rowsAffected' is a variable that executes the Prepared SQL statements
    ReadingFromDatabase readFromDataBase = new ReadingFromDatabase();
    Connection connection = readFromDataBase.connect();

    // createProject is a method that creates a new project
    public void createProject() {

        //Gathering the project information using the getUserNum() and getString() methods for user input
        System.out.println("\nPlease enter the name of the project: ");
        String projectName = readFromDataBase.getString(" ");


        System.out.println("\nPlease enter the type of this building: ");
        String buildingType = readFromDataBase.getString(" ");

        System.out.println("\nPlease enter the address of this building: ");
        String buildingAddress = readFromDataBase.getString(" ");

        System.out.println("\nPlease enter the project's ERF number: ");
        int erf = (int) readFromDataBase.getUserNum(" ");

        System.out.println("\nPlease enter the cost of this project: R");
        float totalFee = readFromDataBase.getUserNum(" ");

        System.out.println("\nPlease enter the amount paid by the customer to date: R");
        float amountPaidToDate = readFromDataBase.getUserNum(" ");

        System.out.println("\nPlease enter the project's due date in yyyy-mm-dd format: ");
        String projectDeadline = readFromDataBase.createDate();

        // By default, a new project is incomplete

        String projectCompletion = "Incomplete";

        //Gathering the architect's information using the getString() methods for input.
        System.out.println("\nPlease enter the Architect's details");

        System.out.println("\nArchitect's first name: ");
        String architectName = readFromDataBase.getString(" ");

        System.out.println("\nArchitect's surname: ");
        String architectSurname = readFromDataBase.getString(" ");

        System.out.println("\nArchitect's phone number: ");
        String architectPhoneNumber = readFromDataBase.getString(" ");

        System.out.println("\nArchitect's email address: ");
        String architectEmail = readFromDataBase.getString(" ");

        System.out.println("\nArchitect's Address: ");
        String architectAddress = readFromDataBase.getString(" ");

        // Gathering the customer's information using the getString() methods for input.
        System.out.println("\nPlease enter the customer's details");

        System.out.println("\nCustomer's first name: ");
        String customerName = readFromDataBase.getString(" ");

        System.out.println("\nCustomer's surname: ");
        String customerSurname = readFromDataBase.getString(" ");

        System.out.println("\nCustomer's phone number: ");
        String customerPhoneNumber = readFromDataBase.getString(" ");

        System.out.println("\nCustomer's email address: ");
        String customerEmail = readFromDataBase.getString(" ");

        System.out.println("\nCustomer's Address: ");
        String customerAddress = readFromDataBase.getString(" ");

        //Adding code for if the user fails to enter a project name.
        if (projectName.equals("")) {
            projectName = buildingType + " " + customerSurname;
        }

        //Gathering the Project Manager's information using the getString() methods for input.
        System.out.println("\nPlease enter the Project Manager's details");

        System.out.println("\nProject Manager's first name: ");
        String pManagerName = readFromDataBase.getString(" ");

        System.out.println("\nProject Manager's surname: ");
        String pManagerSurname = readFromDataBase.getString(" ");

        System.out.println("\nProject Manager's phone number: ");
        String pManagerPhoneNumber = readFromDataBase.getString(" ");

        System.out.println("\nProject Manager's Email Address: ");
        String pManagerEmail = readFromDataBase.getString(" ");

        System.out.println("\nProject Manager's Address: ");
        String pManagerAddress = readFromDataBase.getString(" ");

        //Gathering the Structural Engineer's information using the getStr() methods for input.
        System.out.println("\nPlease enter the Structural Engineer's details");

        System.out.println("\nStructural Engineer's first name: ");
        String structuralEngineerName = readFromDataBase.getString(" ");

        System.out.println("\nStructural Engineer Surname: ");
        String structuralEngineerSurname = readFromDataBase.getString(" ");

        System.out.println("\nStructural Engineer's phone number: ");
        String structuralEngineerPhoneNumber = readFromDataBase.getString(" ");

        System.out.println("\nStructural Engineer's email address: ");
        String structuralEngineerEmail = readFromDataBase.getString(" ");

        System.out.println("\nStructural Engineer's Address: ");
        String structuralEngineerAddress = readFromDataBase.getString(" ");

        // Using a try-catch block to handle the SQL queries
        try {
            // Using a statement to connect to the database in order to use ResultSet
            Statement statement = connection.createStatement();

            // Declaring variables for the project number and person IDs
            int projectNumber = 0;
            int archID = 1000;
            int custID = 2000;
            int projManID = 3000;
            int stEngID = 4000;

            //Getting the number of rows in each table based on how many project/IDs there are already in the table
            // and increasing the count of the project or the ID */

            ResultSet prResults = statement.executeQuery("SELECT count(poisepms.poisedproject.ProjectNumber) " +
                    "AS pr_id FROM poisepms.poisedproject");
            prResults.next();
            int projCount = prResults.getInt("pr_id");
            projCount++;
            projectNumber += projCount;

            ResultSet archResults = statement.executeQuery("SELECT count(poisepms.architect.ID) " +
                    "AS arch_id FROM poisepms.architect");
            archResults.next();

            int archCount = archResults.getInt("arch_id");
            archCount++;
            archID += archCount;

            ResultSet custResults = statement.executeQuery("SELECT count(poisepms.customer.ID) " +
                    "AS cust_id FROM poisepms.customer");
            custResults.next();
            int custCount = custResults.getInt("cust_id");
            custCount++;
            custID += custCount;

            ResultSet projManResults = statement.executeQuery("SELECT count(poisepms.projectmanager.ID) " +
                    "AS projMan_id FROM poisepms.projectmanager");
            projManResults.next();
            int projManCount = projManResults.getInt("projMan_id");
            projManCount++;
            projManID += projManCount;

            ResultSet stEngResults = statement.executeQuery("SELECT count(poisepms.structuralengineer.ID) " +
                    "AS stEng_id FROM poisepms.structuralengineer");
            stEngResults.next();
            int stEngCount = stEngResults.getInt("stEng_id");
            stEngCount++;
            stEngID += stEngCount;


            // Creating queries to insert rows into each of the different tables

            String archQuery = "INSERT INTO poisepms.architect (ID , " +
                    " Name, Surname, TelephoneNumber, " +
                    "EmailAddress, PhysicalAddress) VALUES (?,?,?,?,?,?)";

            String custQuery = "INSERT INTO poisepms.customer (ID, Name, Surname, " +
                    "TelephoneNumber, " +
                    "EmailAddress, PhysicalAddress) VALUES (?,?,?,?,?,?)";

            String projManQuery = "INSERT INTO poisepms.projectmanager (ID, " +
                    " Name, Surname, TelephoneNumber, " +
                    "EmailAddress, PhysicalAddress) VALUES (?,?,?,?,?,?)";

            String stEngQuery = "INSERT INTO poisepms.structuralengineer (ID, " +
                    " Name, Surname, TelephoneNumber, " +
                    "EmailAddress, PhysicalAddress) VALUES (?,?,?,?,?,?)";

            String prQuery = "INSERT INTO poisepms.poisedproject (ProjectNumber, " +
                    "ProjectName, type_of_building, " +
                    "PhysicalAddress, ErfNumber, TotalFee, TotalAmountPaidDate, Deadline, " +
                    "ArchitectID, StructuralEngineerID, CustomerID, ProjectManagerID, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // Using PreparedStatements to execute each query
             // and then defining the different parameters for each query

            PreparedStatement archPreparedStatement = connection.prepareStatement(archQuery);
            archPreparedStatement.setInt(1, archID);
            archPreparedStatement.setString(2, architectName);
            archPreparedStatement.setString(3, architectSurname);
            archPreparedStatement.setString(4, architectPhoneNumber);
            archPreparedStatement.setString(5, architectEmail);
            archPreparedStatement.setString(6, architectAddress);

            PreparedStatement custPreparedStatement = connection.prepareStatement(custQuery);
            custPreparedStatement.setInt(1, custID);
            custPreparedStatement.setString(2, customerName);
            custPreparedStatement.setString(3, customerSurname);
            custPreparedStatement.setString(4, customerPhoneNumber);
            custPreparedStatement.setString(5, customerEmail);
            custPreparedStatement.setString(6, customerAddress);

            PreparedStatement projManPreparedStatement = connection.prepareStatement(projManQuery);
            projManPreparedStatement.setInt(1, projManID);
            projManPreparedStatement.setString(2, pManagerName);
            projManPreparedStatement.setString(3, pManagerSurname);
            projManPreparedStatement.setString(4, pManagerPhoneNumber);
            projManPreparedStatement.setString(5, pManagerEmail);
            projManPreparedStatement.setString(6, pManagerAddress);

            PreparedStatement stEngPreparedStatement = connection.prepareStatement(stEngQuery);
            stEngPreparedStatement.setInt(1, stEngID);
            stEngPreparedStatement.setString(2, structuralEngineerName);
            stEngPreparedStatement.setString(3, structuralEngineerSurname);
            stEngPreparedStatement.setString(4, structuralEngineerPhoneNumber);
            stEngPreparedStatement.setString(5, structuralEngineerEmail);
            stEngPreparedStatement.setString(6, structuralEngineerAddress);

            PreparedStatement projPreparedStatement = connection.prepareStatement(prQuery);
            projPreparedStatement.setInt(1, projectNumber);
            projPreparedStatement.setString(2, projectName);
            projPreparedStatement.setString(3, buildingType); //buildingType
            projPreparedStatement.setString(4, buildingAddress); // buildingAddress
            projPreparedStatement.setInt(5, erf); // erf
            projPreparedStatement.setString(6, String.valueOf(totalFee)); // totalFee / customerFee
            projPreparedStatement.setString(7, String.valueOf(amountPaidToDate)); // TotalAmountPaidDate
            projPreparedStatement.setString(8, projectDeadline); // deadline
            projPreparedStatement.setInt(9, archID); // architectId
            projPreparedStatement.setInt(10, stEngID); // structuralEngineerId
            projPreparedStatement.setInt(11, custID); // customerId
            projPreparedStatement.setInt(12, projManID); // projectManagerId
            projPreparedStatement.setString(13, projectCompletion); // status

            boolean insertArchitectsSuccess =  archPreparedStatement.execute();
            boolean insertCustomerSuccess =  custPreparedStatement.execute();
            boolean insertStEngSuccess =  stEngPreparedStatement.execute();
            boolean insertProjManSuccess =  projManPreparedStatement.execute();
            boolean insertProjectsSuccess =  projPreparedStatement.execute();


            // Using ResultSets again to extract the rows from the different tables
			// based on the project number for the new entry and
			// printing out the project and personal details to the console
            System.out.println("Project details: " + "\n");
            ResultSet projectsResult = statement.executeQuery("SELECT * FROM poisepms.poisedproject" +
                    " WHERE ProjectNumber = " + projectNumber + "");
            readFromDataBase.toStringProject(projectsResult);

            System.out.println("Customer Details: " + "\n");
            ResultSet customerResult = statement.executeQuery("SELECT * FROM poisepms.customer" +
                    " WHERE ID = " + custID + "");
            readFromDataBase.toStringStruEng(customerResult);

            System.out.println("Architect Details: " + "\n");
            ResultSet architectResult = statement.executeQuery("SELECT * FROM poisepms.architect" +
                    " WHERE ID = " + archID + "");
            readFromDataBase.toStringArchitect(architectResult);


            System.out.println("Structural Engineer Details: " + "\n");
            ResultSet structuralEngineerResult = statement.executeQuery("SELECT * FROM poisepms.structuralengineer" +
                    " WHERE ID = " + stEngID + "");
            readFromDataBase.toStringProMan(structuralEngineerResult);

            System.out.println("Project Manager Details: " + "\n");
            ResultSet projectManagerResult = statement.executeQuery("SELECT * FROM poisepms.projectmanager" +
                    " WHERE ID = " + projManID + "");
            readFromDataBase.toStringCustomer(projectManagerResult);

        } catch (SQLException eSQL) {
            // Printing the stack trace for any error that might occur
            eSQL.printStackTrace();
        }
    }
}
