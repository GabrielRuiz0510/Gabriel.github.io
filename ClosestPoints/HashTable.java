public class HashTable
{
    private Node[][] table;
	int numelements;
    private int size;

    public HashTable(int s)
    {
	numelements = 0;
        size = s;
        table = new Node[size][size];
    }

    public void insert(double x, double y)
    {
        int i = hash(x);
        int j = hash(y);
        String coor = Double.toString(x) + "," + Double.toString(y);
        table[i][j] = new Node(coor, table[i][j]);
	    numelements++;
    }

    private int hash(double coor)
    {   
        return (int)(coor * 19219) % size;
    }

    public boolean find(double x, double y)
    {
        int i = hash(x);
        int j = hash(y);
        String coor = Double.toString(x) + "," + Double.toString(y);
        for (Node cur = table[i][j]; cur!= null; cur = cur.next)
        {
            if (cur.data == coor)
            {
                return true;
            }
        }
        return false;
    }

    public int getSize()
    {
        return size;
    }
    
    public Node getTable(int i, int j)
    {
        return table[i][j];
    }

    public void print()
    {
        int n = 0;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                for (Node cur = table[i][j]; cur != null; cur = cur.next)
                {   
                    System.out.println(cur.data);
                }
            }
        }
    }
}
