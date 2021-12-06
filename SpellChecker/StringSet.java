/**
 * This is a string set data structure, that is implemented as a Hash Table. 
 * This data structure supports operations insert, find and print - that insert a new 
 * String, finds a String key and prints the contents of the data structure resp.
 */
public class StringSet 
{

  StringNode [] table;	// Hash table - collisions resolved through chaining.
  int numelements;	// Number of elements actually stored in the structure.
  int size;					// Allocated memory (size of the hash table).

  /** 
   * Constructur: initilaizes numelements, size and initial table size.
   */
  public StringSet() {
    numelements = 0;
    size = 100;
    table = new StringNode[size];
  }

  /*
   *  inserts a new key into the set. Inserts it at the head of the linked list given by its hash value.
   *  @param String key: key to be inserted
   */
  public void insert(String key) 
  {
      //checks if numElements have reached size and will increase
     if (numelements == size) 
     {
        StringNode[] temp = table;
        numelements = 0;
        int oldsize = size;
        size = size * 2;
        table = new StringNode[size];
        for(int i = 0; i < oldsize ; i++)
        {
            //iterate through the list
            for(StringNode cur = temp[i]; cur != null; cur = cur.getNext())
            {
                insert(cur.getKey()); //recursive call to hash in
            }
        }
     }
        
   // Code for actual insert.
    int h = hash(key);
    table[h] = new StringNode(key, table[h]);
    numelements++; 
    
  }

  /**
   *  finds if a String key is present in the data structure. Returns true if found, else false.
   *  @param String key: key to be found
   *  @return bool
   **/
  public boolean find(String key) 
  {
      int h = hash(key);
      for(StringNode cur = table[h]; cur != null; cur = cur.getNext())
       {
            if(key.equals(cur.getKey()))
            {
                return true;
            }
     }
    return false;
  }

  /**
   * Prints the contents of the hash table.
   **/
  public void print() 
  {
    for(int i = 0; i < size; i++)
    {
        for (StringNode cur = table[i]; cur != null; cur = cur.getNext())
        {
            System.out.println("table index: " + i + cur.getKey());
        }
    }

  }

  /*
   * The hash function that returns the index into the hash table for a string k.
   *  @param String k: string to be hashed
   *  @return int hash: value of hash
   **/
  private int hash(String k) 
  {
    int p = 29;
    int hash = 0;
    for (int i = 0; i <k.length(); i++)
    {
        hash = ((hash * p) + k.charAt(i)) % size;
    }
    return hash;
  }

}
