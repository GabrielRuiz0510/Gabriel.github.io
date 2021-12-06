public class StringNode {

  private String key;
  private StringNode next;

  /**
  * StringNode constructor
  * @param String _key: key to be keyed on
  * @param StringNode _next pointer to next StringNode
  **/
  public StringNode(String _key, StringNode _next) 
  {
    key = _key;
    next = _next;
  }

  /**
  * getter method for key
  * @return String Key: key value
  **/
  public String getKey() 
  {
    return key;
  }

  /**
  * setter for key
  **/
  public void setKey(String _key) 
  {
    key = _key;
  }

  /**
  * method to return next StringNode
  * @return next: pointer to next StringNode
  **/
  public StringNode getNext() 
  {
    return next;
  }

  /**
  * setter for next StringNode
  * @return _next: next StringNode
  **/
  public void setNext(StringNode _next) 
  {
    next = _next;
  }

}
