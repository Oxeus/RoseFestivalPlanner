/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 7, 2019
 Class: CS202 - Programming Systems
 Program: #5 - Fair Management
 File: Tree23_Node.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues. The Tree structure catalogs the
 vendors by names and types, it's a self balancing tree.
 Tree23_Node.java has the purpose of defining the Tree23 Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class Tree23_Node {
    private final int size = 3;

    private String [] specifier;
    private List_Node [] head;

    private int specifier_size = 0;

    private Tree23_Node [] child;
    private int child_size = 0;

    //Default Constructor
    public Tree23_Node()
    {
        specifier = new String[size];
        specifier[0] = specifier[1] = specifier[2] = null;

        head = new List_Node[size];
        head[0] = head[1] = head[2] = null;

        child = new Tree23_Node[size + 1];
        child[0] = child[1] = child[2] = child[3] = null;
    }

    //Function to determine if a node has children, returns a boolean.
    public boolean isLeaf()
    {
        if(child[0] == null && child[1] == null && child[2] == null)
        {
            return true;
        }
        return false;
    }

    //Function to add a an item to the node based on a string, if node already has string add data to head pointer
    //of the same index, if node does not have the data then look for where to insert and push other array items around.
    //Takes a Vendor reference and a String as inputs, and returns a boolean.
    public boolean add(Vendor to_add, String specifier)
    {
        int index = 0;
        if(specifier_size == 0)
        {
            setSpecifier(specifier, 0);
            index = 0;
        }
        else if(specifier_size == 1)
        {
            if(this.specifier[0].compareTo(specifier) == 0)
            {
                index = 0;
            }
            else if(this.specifier[0].compareTo(specifier) > 0)
            {
                this.specifier[1] = this.specifier[0];
                this.head[1] = this.head[0];
                this.specifier[0] = null;
                this.head[0] = null;

                setSpecifier(specifier, 0);
                index = 0;
            }
            else
            {
                setSpecifier(specifier, 1);
                index = 1;
            }
        }
        else if(specifier_size == 2)
        {
            if(this.specifier[0].compareTo(specifier) == 0)
            {
                index = 0;
            }
            else if(this.specifier[1].compareTo(specifier) == 0)
            {
                index = 1;
            }
            else if(this.specifier[0].compareTo(specifier) > 0)
            {
                this.specifier[2] = this.specifier[1];
                this.head[2] = this.head[1];
                this.specifier[1] = this.specifier[0];
                this.head[1] = this.head[0];
                this.specifier[0] = null;
                this.head[0] = null;

                setSpecifier(specifier, 0);
                index = 0;
            }
            else if(this.specifier[0].compareTo(specifier) < 0 && this.specifier[1].compareTo(specifier) > 0)
            {
                this.specifier[2] = this.specifier[1];
                this.head[2] = this.head[1];
                this.specifier[1] = null;
                this.head[1] = null;

                setSpecifier(specifier, 1);
                index = 1;
            }
            else
            {
                setSpecifier(specifier, 2);
                index = 2;
            }
        }
        List_Node temp = new List_Node(to_add);
        temp.setNext(head[index]);
        head[index] = temp;
        if(specifier_size == 3)
        {
            return split();
        }
        return false;
    }

    //Function used to merge one node with another after an split of a child node has happened, function will also copy children
    //to their correct spots and if the specifier size is 3 or children are 4 then split again and return true. Takes a
    //Tree23_Node reference and an integer as input, returns a boolean.
    public boolean uplift(Tree23_Node ref, int index)
    {
        List_Node head = ref.head[0];
        String specifier = ref.specifier[0];
        if(specifier_size == 1)
        {
            if(this.specifier[0].compareTo(specifier) > 0)
            {
                this.specifier[1] = this.specifier[0];
                this.head[1] = this.head[0];
                this.specifier[0] = null;
                this.head[0] = null;

                this.specifier[0] = specifier;
                ++this.specifier_size;
                this.head[0] = head;
            }
            else
            {
                this.specifier[1] = specifier;
                ++this.specifier_size;
                this.head[1] = head;
            }
        }
        else if(specifier_size == 2)
        {
            if(this.specifier[0].compareTo(specifier) > 0)
            {
                this.specifier[2] = this.specifier[1];
                this.head[2] = this.head[1];
                this.specifier[1] = this.specifier[0];
                this.head[1] = this.head[0];
                this.specifier[0] = null;
                this.head[0] = null;

                this.specifier[0] = specifier;
                ++this.specifier_size;
                this.head[0] = head;
            }
            else if(this.specifier[0].compareTo(specifier) < 0 && this.specifier[1].compareTo(specifier) > 0)
            {
                this.specifier[2] = this.specifier[1];
                this.head[2] = this.head[1];
                this.specifier[1] = null;
                this.head[1] = null;

                this.specifier[1] = specifier;
                ++this.specifier_size;
                this.head[1] = head;
            }
            else
            {
                this.specifier[2] = specifier;
                ++this.specifier_size;
                this.head[2] = head;
            }
        }
        this.setChild(ref.getChild(0), index); //Replace node that was split
        this.addChild(ref.getChild(1), index + 1); //Add to right of node that was split since right node is greater than added value.
        if(specifier_size == 3)
        {
            return split();
        }
        return false;
    }

    //Function used to split a node into two keeping the original node, if node is an internal node ie has 4 children
    // then the children are split into 2 and 2 children are given to the left and right side each. Returns a boolean.
    private boolean split()
    {
        Tree23_Node left = new Tree23_Node();
        left.specifier[0] = this.specifier[0];
        left.head[0] = this.head[0];
        ++left.specifier_size;

        Tree23_Node right = new Tree23_Node();
        right.specifier[0] = this.specifier[2];
        right.head[0] = this.head[2];
        ++right.specifier_size;

        if(child_size == 4)
        {
            left.child[0] = this.child[0];
            left.child[1] = this.child[1];
            left.child[2] = null;
            left.child[3] = null;
            left.child_size = 2;

            right.child[0] = this.child[2];
            right.child[1] = this.child[3];
            right.child[2] = null;
            right.child[3] = null;
            right.child_size = 2;
        }

        this.child[0] = this.child[1] = this.child[2] = this.child[3] = null;

        this.child[0] = left;
        this.child[1] = right;
        this.child_size = 2;

        String ptr = this.specifier[1];
        List_Node head = this.head[1];
        this.specifier[0] = this.specifier[1] = this.specifier[2] = null;
        this.specifier_size = 0;
        this.head[0] = this.head[1] = this.head[2] = null;
        this.specifier[0] = ptr;
        this.head[0] = head;
        ++this.specifier_size;
        return true;
    }

    //Function used to set the specifier at an index, takes a String and an integer as inputs.
    public void setSpecifier(String specifier, int index)
    {
        this.specifier[index] = specifier;
        ++specifier_size;
    }

    //Function used to get the specifier at an index, takes a integer as input and returns a String.
    public String getSpecifier(int index)
    {

        if(index >= 0 && index < 2)
        {
            return this.specifier[index];
        }
        else
        {
            return null;
        }
    }

    //Function used to find whether a specifier is in a node, if 1 item node check first index, if 2 item node then
    // check both first and second index. Takes a String as input and returns a boolean.
    public boolean findSpecifier(String word)
    {
        if(this.specifier_size == 1)
        {
            return this.specifier[0].compareTo(word) == 0;
        }
        else if(this.specifier_size == 2)
        {
            return (this.specifier[0].compareTo(word) == 0 || this.specifier[1].compareTo(word) == 0);
        }
        else
        {
            return false;
        }
    }

    //Function used to get the Specifier size for a node, returns an integer.
    public int getSpecifier_Size()
    {
        return specifier_size;
    }

    //Function used to get the child size for a node, returns a integer.
    public int getChild_Size()
    {
        return child_size;
    }

    //Function used to set the child at an index, takes a Tree23_Node reference and an integer as input.
    private void setChild(Tree23_Node new_child, int index)
    {
        child[index] = new_child;
    }

    //Function used to get the child of the node based on an index. Takes a integer as input and returns a Tree23_Node reference.
    public Tree23_Node getChild(int index)
    {

        if(index >= 0 && index < child_size)
        {
            return child[index];
        }
        else
        {
            return null;
        }
    }

    //Function used to add a child to a node through shifting, takes a Tree23_Node reference and an integer as inputs.
    private void addChild(Tree23_Node new_child, int index)
    {
        if(index >= 0 && index < 4)
        {
            if(child[index] != null)
            {
                if(index == 0)
                {
                    child[3] = child[2];
                    child[2] = child[1];
                    child[1] = child[0];
                    child[0] = new_child;
                }
                else if(index == 1)
                {
                    child[3] = child[2];
                    child[2] = child[1];
                    child[1] = new_child;
                }
                else if(index == 2)
                {
                    child[3] = child[2];
                    child[2] = new_child;
                }
            }
            else
            {
                child[index] = new_child;
            }
            ++child_size;
        }
    }

    //Function used to findChild to go to by comparing specifiers to passed in String. Takes an String as input and returns
    //an integer.
    public int findChild(String word)
    {
        if(specifier_size == 1)
        {
            if(specifier[0].compareTo(word) > 0) {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        else if(specifier_size == 2)
        {
            if(specifier[0].compareTo(word) > 0)
            {
                return 0;
            }
            else if(specifier[0].compareTo(word) < 0 && specifier[1].compareTo(word) > 0)
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        else return -1;
    }

    //Function used to print the specifier and it's list at an index. Takes a integer as input.
    public void print_specifier(int index)
    {
        if(index >= 0 && index < specifier_size)
        {
            System.out.println(specifier[index]);
            System.out.println("*********");
            print_List(head[index]);
            if(specifier[index] !=  null)
            {
                System.out.println();
            }
        }
    }

    //Function used to print the specifier if it matches the sent in String. Takes a String as inputs and returns a boolean.
    public boolean print_specifier(String find)
    {
        if(specifier_size == 1)
        {
            if(specifier[0].compareTo(find) == 0)
            {
                print_specifier(0);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(specifier_size == 2)
        {
            if(specifier[0].compareTo(find) == 0)
            {
                print_specifier(0);
                return true;
            }
            else if(specifier[1].compareTo(find) == 0)
            {
                print_specifier(1);
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    //Function used to print the list located in a node at an index. Takes a List_Node reference as an input.
    private void print_List(List_Node head)
    {
        if(head == null) {
            return;
        }
        head.printList_Node();
        print_List(head.getNext());
    }
}
