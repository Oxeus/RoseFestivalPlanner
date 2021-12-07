//Not Part of program 4.

package rose_festival_planner;

public class Tree23 {
    private Tree23_Node root;

    public Tree23()
    {
        root = null;
    }

    public int add_vendor(Vendor to_add)
    {
        if(root == null) {
            root = new Tree23_Node();
        }
        root.addNode(to_add);
        return 1;
    }

    public int find_vendor() {

        return 1;
    }

    public void display()
    {
        if(root != null)
        {
            System.out.println("Vendor Catalog");
            System.out.println("---------------------------------");
            root.printTree23_Node();
        }
        else
        {
            System.out.println("Empty Catalog!");
        }
    }
}
