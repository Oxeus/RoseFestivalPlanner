/******************************************************************************************
 Program by: Adam LaFleur
 Date: June 7, 2019
 Class: CS202 - Programming Systems
 Program: #5 - Fair Management
 File: List_Node.java
 Purpose: The purpose of this program is to keep track of vendors for the Rose Festival. The
 program keeps track of their info and location and prevents booths of the same type or name
 from being close to each other, also spaces out music venues. The Tree structure catalogs the
 vendors by names and types, it's a self balancing tree.
 List_Node.java has the purpose of defining the List_Node Class, its fields and methods.
 ******************************************************************************************/

package rose_festival_planner;

public class List_Node {
    private Vendor store;
    private List_Node next;

    //Default Constructor
    public List_Node()
    {
        this.store = null;
        this.next = null;
    }

    //Argument Constructor, takes a Vendor reference as input.
    public List_Node(Vendor to_store)
    {
        this.store = to_store;
        this.next = null;
    }

    //Second Argument Constructor, takes a Vendor reference and List_Node reference as inputs.
    public List_Node(Vendor to_store, List_Node to_next)
    {
        this.store = to_store;
        this.next = to_next;
    }

    //Function to set the Vendor for a node, takes a Vendor reference as input.
    public void setStore(Vendor to_store)
    {
        store = to_store;
    }

    //Function used to get a Vendor from a node, returns a Vendor reference.
    public Vendor getStore()
    {
        return this.store;
    }

    //Function used to set the next reference for a node, takes a List_Node reference as input.
    public void setNext(List_Node next)
    {
        this.next = next;
    }

    //Function used to get the next List_Node from a node, returns a List_Node reference.
    public List_Node getNext()
    {
        return this.next;
    }

    //Function used to print the Vendor info.
    public void printList_Node()
    {
        store.display_vendor();
        if(next != null)
        {
            System.out.println();
        }
    }
}
