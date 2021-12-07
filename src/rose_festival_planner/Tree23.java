/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 7, 2019
 Class: CS202 - Programming Systems
 Program: #5 - Fair Management
 File: Tree23.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues. The Tree structure catalogs the
 vendors by names and types, it's a self balancing tree.
 Tree23.java has the purpose of defining the Tree23 Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Tree23 {
    private Tree23_Node root;

    //Default Constructors
    public Tree23()
    {
        root = null;
    }

    //Wrapper Function to add a Vendor to the 2-3 Tree, takes a Vendor reference as input and returns a boolean.
    public boolean add_vendor(Vendor to_add)
    {
        if(this.root == null)
        {
            this.root = new Tree23_Node();
        }
        add_vendor(this.root, to_add, to_add.type_toString());
        add_vendor(this.root, to_add, to_add.getName());
        return add_vendor(to_add.string_toArray(), to_add, 0);
    }

    //Function to break string up into array of strings and add a Vendor to the 2-3 tree, part of wrapper. takes a String
    //array, a Vendor reference, and an integer as inputs and returns a boolean.
    private boolean add_vendor(String [] array, Vendor to_add, int num)
    {
        if(num == array.length)
        {
            return true;
        }
        add_vendor(this.root, to_add, array[num]);
        return add_vendor(array, to_add, ++num);
    }

    //Recursive Function to add a Vendor to the 2-3 tree, splits if needed, takes a Tree23_Node reference, Vendor reference,
    //and a String as inputs and returns a boolean.
    private boolean add_vendor(Tree23_Node root, Vendor to_add, String word)
    {
        boolean result = false;
        int index = -1;
        if(root.isLeaf())
        {
            return root.add(to_add, word);
        }
        if(root.findSpecifier(word)) //Need to add to current node
        {
            return root.add(to_add, word);
        }
        index = root.findChild(word);
        if(index == -1)
        {
            return false;
        }
        result = add_vendor(root.getChild(index), to_add, word);
        if(result)
        {
            return root.uplift(root.getChild(index), index);
        }
        return false;
    }

    //Wrapper Function used to retrieve a lists of results from a keyword, takes a String as inputs and returns an int.
    public boolean retrieve(String word)
    {
        return retrieve(root, word);
    }

    //Recursive function used to retrieve a lists of results from a keyword, takes a Tree23_Node and String as inputs.
    //Returns a boolean..
    private boolean retrieve(Tree23_Node root, String word)
    {
        int index = 0;
        if(root == null)
        {
            System.out.println("Result Not Found");
            return false;
        }
        if(root.print_specifier(word))
        {
            return true;
        }
        index = root.findChild(word);
        if(index == -1)
        {
            System.out.println("Result Not Found");
            return false;
        }
        return retrieve(root.getChild(index), word);
    }

    //Wrapper for Display Function, takes a integer as input.
    public void display(int choice)
    {
        if(root == null || (choice < 0 || choice > 2))
        {
            System.out.println("Empty Catalog!");
        }
        else
        {
            System.out.println("Catalog: ");
            System.out.println("---------------------------------");
            display(root, choice);
        }
    }

    //Recursive Display Function for 2-3 Tree, uses user entered integer to choose pre, in, or post order;
    //and takes a Tree23_Node reference and an integer as input.
    private void display(Tree23_Node root, int choice)
    {
        if(root == null) return;
        if(choice == 0)
        {
            root.print_specifier(0);
            root.print_specifier(1);
        }
        display(root.getChild(0), choice);
        if(choice == 1)
        {
            root.print_specifier(0);
        }
        display(root.getChild(1), choice);
        if(choice == 1)
        {
            root.print_specifier(1);
        }
        display(root.getChild(2), choice);
        if(choice == 2)
        {
            root.print_specifier(0);
            root.print_specifier(1);
        }
    }
}
