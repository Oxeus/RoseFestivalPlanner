/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4/5 - Fair Management
 File: Food.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Food.java has the purpose of defining the Food class, its fields and methods. Dynamic binding
 with Vendor class.
 ******************************************************************************************/

package rose_festival_planner;

public class Food extends Vendor {

    //Default Constructor
    public Food()
    {
        super();
    }

    //Argument Constructor, takes a String, integer, enum vendor_type as arguments.
    public Food(String name, int vendor_id, vendor_type type)
    {
        super(name, vendor_id, type);
    }

    //Function used to change vendor type into a string. Returns a String.
    public String type_toString()
    {
        if(compareType(vendor_type.FOOD))
        {
            return "Food";
        }
        else
        {
            return "Blank";
        }
    }

    //Function used to print info for food class.
    public void display_vendor() {
        printName();
        printType();
        printID();
        System.out.println("Message: " + "Hot and Ready to Eat.");
    }
}
