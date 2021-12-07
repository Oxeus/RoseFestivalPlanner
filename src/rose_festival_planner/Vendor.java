/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4/5 - Fair Management
 File: Vendor.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Vendor.java has the purpose of defining the Vendor class, its fields and methods. Abstract for
 dynamic binding.
 ******************************************************************************************/

package rose_festival_planner;

public abstract class Vendor extends Type {

    private int vendor_id;

    //Default Constructor.
    public Vendor()
    {
        super();
        this.vendor_id = 0;
    }

    //Argument Constructor, takes a String, integer, and enum vendor_type as input.
    public Vendor(String name, int vendor_id, vendor_type type) {
        super(name, type);
        this.vendor_id = vendor_id;
    }

    //Copy Constructor, takes a Vendor reference as input.
    public Vendor(Vendor to_copy)
    {
        super(to_copy);
        this.vendor_id = to_copy.vendor_id;
    }

    //Function used to get the vendor_id for a vendor, returns a vendor_id.
    public int getVendor_id() {
        return vendor_id;
    }

    //Function used to set the vendor_id, takes a integer as an argument.
    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    //Function used to compare an argument to the current vendor_id, takes an integer as input and returns a boolean.
    public boolean compareID(int to_compare)
    {
        if(vendor_id == to_compare)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Function used to print the vendor_id.
    public void printID()
    {
        System.out.println("ID: " + this.vendor_id);
    }

    //Abstract Function defining the dynamic type_toString function.
    public abstract String type_toString();

    //Abstract Function defining the dynamic display_vendor function.
    public abstract void display_vendor();

}
