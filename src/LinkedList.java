import java.time.temporal.IsoFields;

public class LinkedList <E> {
  // Only field of a linked list is the head
  private Node mHead;

  public long size()
          
  {
    long count = 0;
    // Loop through all the Nodes, until we hit null
    // Linked List starts at the head
    // temp variable starts at the head
    Node temp = mHead;
    // As long as the temp != null
    while (temp != null)
    {
      count++;
      // Move the temp by 1
      temp = temp.mNext;
    }
    return count;
  }

  public boolean add(long index, E element) {

    if (index < 0 || index > size())
      throw new IllegalArgumentException("Index must be between 0 and " + size());

    if (index == 0)
      //This adds a new head to the linked list, so it's basically like we're adding something before the old mHead
      //That means this new element needs to point to the old mHead.
      mHead = new Node(element, mHead);
    else {
      //1) Create temp node starting at head
      Node temp = mHead;
      //2) Move temp node to node before index
      for (long i = 0; i < index - 1; i++) {
        temp = temp.mNext;
      }
      //3) Create a new node (it's next is the next of temp)
      Node newNode = new Node(element, temp.mNext);
      //4) Reassign next of temp = new node
      temp.mNext = newNode;
    }
    return true;
  }

  //Appends at the end of LinkedList
  public boolean add(E element) {
    return add(size(), element);
  }

  public void addFirst(E element) {
    add(0, element);
  }

  public void addLast(E element) {
    add(element);
  }

  public E get(long index) {
    if (index < 0 || index >= size())
      throw new IllegalArgumentException("Index must be between 0 and " + (size() - 1));
    //1) Start a temp node at the head
    Node temp = mHead;
    //2) Move it to index
    for (long i = 0; i < index ; i++)
      temp = temp.mNext;

    return temp.mData;
  }

  public E set(long index, E element) {
    if (index < 0 || index >= size())
      throw new IllegalArgumentException("Index must be between 0 and " + (size() - 1));
    //1) Start a temp node at head
    Node temp = mHead;
    //2) Move it to index
    for (long i = 0; i < index; i++) {
      temp = temp.mNext;
    }

    //3) Save the old data
    E oldData = temp.mData;
    //4) Change/set the node's data
    temp.mData = element;
    //5) Return the old data
    return oldData;
  }

  public long indexOf(Object element) {
    long index = 0;
    //Start temp at the head
    Node temp = mHead;
    while (temp != null) {

      if (temp.mData.equals(element))
        return index;
      else
        index++;
      //Move the temp no matter what
      temp = temp.mNext;
    }
    //If we make it out of the while loop, we didn't find the element
    return -1;
  }

  public boolean contains(Object element) {
    return indexOf(element) != -1;
  }

  public E remove(long index) {
    if (index < 0 || index >= size())
      throw new IllegalArgumentException("Index must be between 0 and " + (size() - 1));

    Node temp = mHead;
    if (index == 0) {
      mHead = mHead.mNext;
      return temp.mData;
    }
      for (long i = 0; i < index - 1; i++) {
        temp = temp.mNext;
      }

      E oldData = temp.mNext.mData;
      temp.mNext = temp.mNext.mNext;

    return oldData;
  }

  public boolean remove(E element) {
    long index = indexOf(element);
    if (index == -1)
      return false;
    else
    {
      remove(index);
      return true;
    }

  }

  public void clear() {
    mHead = null;
  }

  @Override
  public String toString() {
    String output = "LinkedList [";
    Node temp = mHead;
    //If the head != null, append its data
    if (mHead != null) {
      output += mHead.mData;
      temp = temp.mNext;
    }
    while (temp != null) {
      output += ", " + temp.mData;
      temp = temp.mNext;
    }

    return output += "]";
  }

  public class ListIterator {
    // 3 Fields
    private Node mPrev;
    private long mCursor;
    private Node mNext;

    //Constructor
    public ListIterator() {
      mPrev = null;
      mCursor = 0;
      mNext = mHead;
    }

    public boolean hasNext() {
      return mNext != null;
    }

    public boolean hasPrevious() {
      return (mPrev != null);
    }

    public E next() {
      mCursor++;
      mPrev = mNext;
      mNext = mNext.mNext;
      return (E) mPrev.mData;
    }

    public E previous() {
      mCursor--;
      mNext = mPrev;
      //At the head
      if (mCursor == 0)
        mPrev = null;
      else {
        //Start at the head and move the Node where prev livesd
        Node temp = mHead;
        //Start at 1 so that you don't go too far.
        for (int i = 1; i < mCursor; i++) {
          temp = temp.mNext;
        }
        mPrev = temp;
      }
     return mNext.mData;
    }

    public long nextIndex() {
      return mCursor;
    }

    public long previousIndex() {
      return mCursor - 1;
    }

    public void add(E element) {
      LinkedList.this.add(mCursor, element);
    }

    public void remove() {
      LinkedList.this.remove(mCursor);
    }

    public void set (E e) {
      LinkedList.this.set(mCursor, e);
    }
  }



  // Nested inner class for Node
  private class Node
  {
    // 2 fields - data and who comes next
    private E mData;
    private Node mNext;

    Node(E data, Node next)
    {
      mData = data;
      mNext = next;
    }

    Node(E data)
    {
      this(data, null);
    }
  }
}
