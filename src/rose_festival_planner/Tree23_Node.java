//Not Part of program 4.

package rose_festival_planner;

public class Tree23_Node {
    private List_Node [] head;
    private int [] count;
    private Tree23_Node [] child;
    private final int size = 2;

    public Tree23_Node()
    {
        head = new List_Node[size];
        head[0] = head[1] = null;
        child = new Tree23_Node[size + 1];
        child[0] = child[1] = child[2] = null;
        count = new int[size];
    }

    public int addNode(Vendor to_add)
    {
        List_Node ref = new List_Node(to_add);
        ref.setNext(head[0]);
        head[0] = ref;
        return 1;
    }

    public void printTree23_Node()
    {
        if(head[0] == null && head[1] == null)
        {
            System.out.println("Empty Node! ");
            return;
        }
        else
        {
            printTree23_Node(head[0]);
            printTree23_Node(head[1]);
        }
    }

    private int printTree23_Node(List_Node head)
    {
        if(head == null) return 1;
        head.printList_Node();
        return printTree23_Node(head.getNext());
    }
}
