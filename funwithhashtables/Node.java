public class Node
{
    public Node next;
    public String data;

    public Node()
    {
        next = null;
        data = "";
    }

    public Node(String  d, Node n)
    {
        data = d;
        next = n;
    }
    
    public boolean equals(int d, Node n)
    {
        return (next == n);
    }   
    public void printNodes(Node head)
    {
        Node current = head;
        while (current != null)
        {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
