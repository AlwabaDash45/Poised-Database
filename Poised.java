public class Poised {

    public static void main(String[] args){

        // Creating objects of the ProjectReader, FindProjects and ReadingFromDatabase Classes
        ProjectReader reader = new ProjectReader(); // ProjectPoised is going to replace ProjectReader
        FindProjects editProjects = new FindProjects(); // FindProjects is going to Replace FindProjects
        ReadingFromDatabase method = new ReadingFromDatabase(); // ReadingFromDatabase

        // Adding the lineSeparator variable
        String lineSeparator = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";


        // Using a while loop to enter the menu
        while(true){

            // Presenting the user with a menu
            System.out.println("""
					Please select one of the following options:\s
					1 - View All Projects
					2 - Add a new Project.
					3 - Edit Projects.
					4 - View Incomplete Projects
					5 - View Overdue Projects
					6 - Quit.""");

            // gathering the user's choice using the getNum() method from the ReadingFromDatabase Class
            float menuOption = method.getUserNum(" ");

            /* If the user chooses to exit the program an exit message is printed and the program exits
             * This is outside the switch-case statement to ensure the program quits */
            if(menuOption == 6){
                System.out.println("Thanks for using our program!");
                System.out.println(lineSeparator);
                break;
            }

            // Using a switch-case statement for each user choice

            switch ((int) menuOption) {

                // Allowing a user to view all the projects in the table
                case 1 -> {
                    System.out.println("You have chosen to view all projects.");

                    // Calling the viewAll() method from the FindProjects class
                    editProjects.viewAll();
                    System.out.println(lineSeparator);
                }

                // Allowing the user to add a new project
                case 2 -> {
                    System.out.println("You have chosen to add a new project.");

                    // Calling the createProject() method from the ProjectReader Class
                    reader.createProject();
                    System.out.println("New Project added.");

                }

                /* If the user chooses to edit a project
                 * they are presented with a second menu.
                 * This menu is called from the FindProjects Class */
                case 3 -> {
                    editProjects.userMenu();
                    System.out.println(lineSeparator);
                }

                /* If the user asks to view all incomplete projects
                 * I call the ViewIncomplete method from the FindProjects Class */
                case 4 -> {
                    System.out.println("You have chosen to view all incomplete projects.");
                    editProjects.viewIncomplete();
                    System.out.println(lineSeparator);
                }

                /* If the user asks to view all incomplete projects
                 * I call the Overdue method from the FindProjects Class */
                case 5 -> {
                    System.out.println("You have chosen to view all overdue projects.");
                    editProjects.viewOverdue();
                    System.out.println(lineSeparator);
                }

                /* If the user does not enter a valid option,
                 * an appropriate message is printed.*/
                default -> System.out.println("Please enter a valid option.");
            }
        }
    }
}