/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 3, 2019
 Class: CS202 - Programming Systems
 Program: #4 - Fair Management
 File: Circular_List.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues.
 Circular_List.java has the purpose of defining the Circular_List Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Circular_List {
    private Node rear;
    private int blocks;

    //Nested Class used to hold counts and num values, mainly to allow pass by reference
    //like functionality for recursive functions.
    public class count {
        public int num = 0;
        public int talent_count = 0;
        public int corndog_count = 0;
        public int hamburger_count = 0;
        public int hotdog_count = 0;
    }
    private count here;

    //Default Constructor
    public Circular_List()
    {
        super();
        this.rear = null;
        this.blocks = 0;
        this.here = new count();
    }

    //Argument Constructor, takes an integer as input.
    public Circular_List(int blocks)
    {
        super();
        this.rear = null;
        this.blocks = blocks;
        this.here = new count();
        add_blocks(blocks, 1);
    }

    //Recursive Function used to create nodes (blocks) in the list, takes two integers as input.
    private boolean add_blocks(int blocks, int count)
    {
        if(blocks == 0)
        {
            return true;
        }
        add_block(count);
        return add_blocks(--blocks, ++count);
    }

    //Function used to add nodes to the list, takes an integer as input, and returns a boolean.
    private boolean add_block(int block_ID)
    {
        if(rear == null)
        {
            rear = new Node(block_ID);
            rear.setNext(rear);
        }
        else
        {
            Node ptr = new Node(block_ID);
            ptr.setNext(rear.getNext());
            rear.setNext(ptr);
            rear = ptr;
        }
        return true;
    }

    //Function used to add a vendor to a block, takes a Vendor reference and an integer as inputs, returns an boolean.
    public boolean add_vendor(Vendor to_add, int block)
    {
        if(to_add != null && (block > 0 && block <= this.blocks))
        {
            if(to_add.compareType(vendor_type.TALENT)) //to_add.getType() == vendor_type.TALENT)
            {
                if(here.talent_count < 2)
                {
                    here.num = 0;
                    if(find_type(rear.getNext(), vendor_type.TALENT, here))
                    {
                        block = here.num + (this.blocks / 2);
                        if(block > blocks)
                        {
                            block -= blocks;
                        }
                    }
                    ++here.talent_count;
                }
                else
                {
                    System.out.println("\nTalent Count is max, cannot add new vendor!");
                    return false;
                }
            }
            else if(to_add.compareType(vendor_type.FOOD))
            {
                String temp = to_add.getName().toUpperCase();
                if(temp.contains("CORNDOG"))
                {
                    if(here.corndog_count >= 7)
                    {
                        System.out.println("\nCorn Dog Count is max, cannot add new vendor!");
                        return false;
                    }
                    ++here.corndog_count;
                }
                else if(temp.contains("HAMBURGER"))
                {
                    if(here.hamburger_count >= 7)
                    {
                        System.out.println("\nHamburger Count is max, cannot add new vendor!");
                        return false;
                    }
                    ++here.hamburger_count;
                }
                else if(temp.contains("HOTDOG"))
                {
                    if(here.hotdog_count >= 7)
                    {
                        System.out.println("\nHotdog Count is max, cannot add new vendor!");
                        return false;
                    }
                    ++here.hotdog_count;
                }
            }
            return add_vendor(rear.getNext(), to_add, block);
        }
        return false;
    }

    //Recursive Function used to add a vendor to a block, takes a Node reference, Vendor reference and an integer as inputs.
    private boolean add_vendor(Node rear, Vendor to_add, int block)
    {
        if(block == 1)
        {
            return rear.add_vendor(to_add);
        }
        return add_vendor(rear.getNext(), to_add, --block);
    }

    //Recursive function used to determine if a block contains the type needed to be found. Takes a Node reference,
    //enum vendor_type, and a count reference as arguments; returns a boolean.
    private boolean find_type(Node rear, vendor_type type, count to_count)
    {
        if(rear == this.rear)
        {
            to_count.num = 0;
            if(rear.contains_type(type))
            {
                return true;
            }
            return false;
        }
        ++to_count.num;
        if(rear.contains_type(type))
        {
            return true;
        }
        return find_type(rear.getNext(), type, to_count);
    }

    //Function used to display the list, returns boolean.
    public boolean display()
    {
        if(rear != null)
        {
            System.out.println("Vendor List: ");
            System.out.println("---------------------------------");
            display(rear.getNext(), 1);
            return true;
        }
        else
        {
            System.out.println("Empty Vendor List!");
            return false;
        }
    }

    //Recursive function used to display the list, takes a Node reference and integer as arguments. Returns
    //a boolean.
    private boolean display(Node rear, int block)
    {
        if(this.rear == rear)
        {
            if(rear.is_empty()) {
                System.out.println("Block: " + block);
                System.out.println("*********");
                rear.display_vendors();
            }
            return true;
        }
        if(rear.is_empty()) {
            System.out.println("Block: " + block);
            System.out.println("*********");
            rear.display_vendors();
            System.out.println();
        }
        return display(rear.getNext(), ++block);
    }
}
