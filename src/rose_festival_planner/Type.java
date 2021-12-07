/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4/5 - Fair Management
 File: Type.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Type.java has the purpose of defining the Type class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Type extends Name {

    private vendor_type type;

    //Default Constructor
    public Type()
    {
        super();
        this.type = vendor_type.BLANK;
    }

    //Assignment Constructor, takes a String and enum vendor_type as input.
    public Type(String name, vendor_type type)
    {
        super(name);
        this.type = type;
    }

    //Copy Constructor, takes a Type reference as input.
    public Type(Type to_copy)
    {
        super(to_copy);
        this.type = to_copy.type;
    }

    //Function to get the vendor_type, returns a vendor_type.
    public vendor_type getType() {
        return type;
    }

    //Function to set the vendor_type, takes a enum vendor_type as input.
    public void setType(vendor_type type) {
        this.type = type;
    }

    //Function used to compare argument to current type, takes a enum vendor_type as input and returns a boolean.
    public boolean compareType(vendor_type to_compare)
    {
        if(this.type == to_compare)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Function used to print the vendor_type.
    public void printType()
    {
        System.out.println("Type: " + this.type);
    }
}
