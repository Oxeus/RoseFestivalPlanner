//Not Part of program 4.

package rose_festival_planner;

public class List_Node {
    private Vendor store;
    private List_Node next;

    public List_Node()
    {
        store = null;
        next = null;
    }

    public List_Node(Vendor to_store)
    {
        store = to_store;
        next = null;
    }

    public void setStore(Vendor to_store)
    {
        store = to_store;
    }

    public Vendor getStore()
    {
        return this.store;
    }

    public void setNext(List_Node next)
    {
        this.next = next;
    }

    public List_Node getNext()
    {
        return this.next;
    }

    public void printList_Node()
    {
        store.display_vendor();
    }
}
