/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4 - Fair Management
 File: Node.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Node.java has the purpose of defining the Node Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Node {
    private Vendor [] Vendors;
    private final int max_size = 8;
    private int curr_size;
    private int block_ID;

    private Node next;

    //Argument Constructor
    public Node(int block_ID) {
        this.next = null;
        this.Vendors = new Vendor[max_size];
        this.curr_size = 0;
        this.block_ID = block_ID;
    }

    //Function used to see if previous vendor has the same name or food type, takes a String as input.
    public boolean compare_neighbor(String name)
    {
        if(curr_size == 0)
        {
            return false;
        }
        else
        {
            return Vendors[curr_size - 1].containsString(name);
        }
    }

    //Function used to add a vendor to the array, takes a String, and vendor_type as input.
    public boolean add_vendor(String name, vendor_type type)
    {
        int id = 0;
        boolean result = true;
        if(compare_neighbor(name))
        {
            result = false;
            System.out.println("\nCannot add a vendor here, same name or food type");
        }
        else if(curr_size < max_size) {
            id = (this.block_ID * 100) + (curr_size + 1);
            if(type == vendor_type.FOOD)
            {
                Vendors[curr_size] = new Food(name, id, type);
                ++curr_size;
            }
            else if(type == vendor_type.GAME)
            {
                Vendors[curr_size] = new Game(name, id, type);
                ++curr_size;
            }
            else if(type == vendor_type.TALENT)
            {
                Vendors[curr_size] = new Talent(name, id, type);
                ++curr_size;
            }
            else{
                result = false;
            }
            result = true;
        }
        else
        {
            result = false;
            System.out.println("\nBLOCK FULL!");
        }
        return result;
    }

    //Function used to add a vendor to the array, takes a Vendor reference as input.
    public boolean add_vendor(Vendor to_add)
    {
        int id = 0;
        boolean result = true;
        if(compare_neighbor(to_add.getName()))
        {
            System.out.println("\nCannot add a vendor here, same name or food type.");
            return false;
        }
        else if(curr_size < max_size) {
            id = (this.block_ID * 100) + (curr_size + 1);
            if(to_add.getType() == vendor_type.FOOD)
            {
                Vendors[curr_size] = to_add;
                to_add.setVendor_id(id);
                ++curr_size;
            }
            else if(to_add.getType() == vendor_type.GAME)
            {
                Vendors[curr_size] = to_add;
                to_add.setVendor_id(id);
                ++curr_size;
            }
            else if(to_add.getType() == vendor_type.TALENT)
            {
                Vendors[curr_size] = to_add;
                to_add.setVendor_id(id);
                ++curr_size;
            }
            else{
                result = false;
            }
            result = true;
        }
        else
        {
            result = false;
            System.out.println("\nBLOCK FULL!");
        }
        return result;
    }

    //Function used to get the next reference to a node, returns a next reference.
    public Node getNext()
    {
        return next;
    }

    //Function used to set the next reference to a node, takes a Node reference as input.
    public void setNext(Node next)
    {
        this.next = next;
    }

    //Function used to determine if the array is empty or not, returns a boolean.
    public boolean is_empty()
    {
        if(curr_size > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Function used to determine if the the array contains a certain type, takes a enum vendor_type as input. Returns
    //a boolean.
    public boolean contains_type(vendor_type type)
    {
        return contains_type(Vendors, 0,type);
    }

    //Recursive Function used to determine if the the array contains a certain type, takes a enum vendor_type,
    //Vendor array, and an integer as inputs. Returns a boolean.
    private boolean contains_type(Vendor [] Vendors, int num, vendor_type type)
    {
        if(num == curr_size)
        {
            return false;
        }
        if(Vendors[num].compareType(type)) return true;
        return contains_type(Vendors, ++num, type);
    }

    //Function used to display vendors from the array.
    public boolean display_vendors()
    {
        return display_vendors(Vendors, 0);
    }

    //Recursive Function used to display vendors from the array. Takes a Vendor array and an integer as inputs.
    private boolean display_vendors(Vendor [] Vendors, int num)
    {
        if(num == curr_size)
        {
            return true;
        }
        Vendors[num].display_vendor();
        if(curr_size > 1 && num < (curr_size - 1))
        {
            System.out.println();
        }
        return display_vendors(Vendors, ++num);
    }
}
