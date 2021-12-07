/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 7, 2019
 Class: CS202 - Programming Systems
 Program: #4/5 - Fair Management
 File: Park.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Park.java has the purpose of defining the Park Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Park extends Util {
    private Circular_List management;
    private Tree23 customer;

    //Default Constructor
    public Park()
    {
        super();
        management = new Circular_List(0);
        customer = new Tree23();
        control_menu();
    }

    //Assignment Constructor, takes an integer as input.
    public Park(int size)
    {
        super();
        management = new Circular_List(size);
        customer = new Tree23();
        control_menu();
    }

    //Function used to control the program menu.
    public void control_menu() {
        control_menu(true);
    }

    //Recursive function used to control the program menu, takes a boolean as input.
    private void control_menu(boolean control) {
        if (!control) {
            return;
        }
        int choice = 0;
        System.out.println("\n------ Management Menu ------");
        System.out.println(" 01) Add a Vendor. ");
        System.out.println(" 02) Display Vendors. ");
        System.out.println("-----------------------------");
        System.out.println("\n------- Customer Menu -------");
        System.out.println(" 03) Display Vendors. ");
        System.out.println(" 04) Find Vendors. ");
        System.out.println("-----------------------------");
        System.out.println("\n 00) Exit. \n");
        System.out.print("Enter a Choice: ");
        choice = sc.nextInt();
        sc.nextLine();
        if(choice != 0)
        {
            System.out.println();
        }

        switch (choice) {
            case 0:
                control = false;
                break;
            case 1:
                add_vendors();
                break;
            case 2:
                management.display();
                break;
            case 3:
                System.out.print("Please enter 1 for pre-order, 2 for in-order, or 3 for post-order: ");
                choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                customer.display(choice - 1);
                break;
            case 4:
                System.out.print("Please enter a keyword: ");
                String keyword = sc.nextLine();
                System.out.println();
                customer.retrieve(keyword);
                break;
            default:
                System.out.println("Invalid Choice! ");
                break;
        }
        control_menu(control);
    }

    //Function used to create a vendor and send it to both data structures, returns a boolean.
    private void add_vendors() {
        Vendor to_add = null;
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Vendor Section: ");
        int section = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Vendor Type: ");
        String type = sc.nextLine();

        if (type.toUpperCase().contains("FOOD")) {
            to_add = new Food(name, 0, vendor_type.FOOD);
        } else if (type.toUpperCase().contains("GAME")) {
            to_add = new Game(name, 0, vendor_type.GAME);
        } else if (type.toUpperCase().contains("TALENT")) {
            to_add = new Talent(name, 0, vendor_type.TALENT);
        } else {
            return;
        }
        if(management.add_vendor(to_add, section)) {
            customer.add_vendor(to_add);
        }
    }
}
