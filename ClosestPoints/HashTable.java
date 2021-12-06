public class HashTable
{
    private Node[][] table;
	int numelements;
    private int size;
	
    /**
    *	constructor for HashTable
    *	@param int s: size given
    **/
    public HashTable(int s)
    {
	numelements = 0;
        size = s;
        table = new Node[size][size];
    }
    
    /**
    *	insert method that takes two doubles and hashes into table
    *	@param double x: double number given
    *	@param double y: double nubmer given
    **/
    public void insert(double x, double y)
    {
        int i = hash(x);
        int j = hash(y);
        String coor = Double.toString(x) + "," + Double.toString(y);
        table[i][j] = new Node(coor, table[i][j]);
	    numelements++;
    }

    /**
    *	hash method that uses prime number modulo to hash.
    *	@param coor: double number to be hashed
    **/
    private int hash(double coor)
    {   
        return (int)(coor * 19219) % size;
    }
	
    /**
    *	find method to check if given numbers are located in the hashtable and returns true or false.
    *	@param double x: double value given
    *	@param double y: double number given for second point
    *	@param bool
    **/
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

    /**
    *	getter method for size
    *	@return size
    **/
    public int getSize()
    {
        return size;
    }
    
    /**
    *	getTable method to grab current value at position [i][j]
    *	@param int i, int j: given coors for table
    *	@return table[i][j]: value at thise points.
    **/
    public Node getTable(int i, int j)
    {
        return table[i][j];
    }

    /**
    *	print method
    **/
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
