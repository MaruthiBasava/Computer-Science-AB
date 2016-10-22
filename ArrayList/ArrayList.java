/**
 * @author FernanDK18
 * @version (9/23/16)
 * @param <E>
 */
public class ArrayList<E> {
    
    private Object[] objects;
	private int size;
	
	/**
	 * @throws Exception
	 * A default constructor
	 */
	public ArrayList() {
	    this( 10 );
	}

	/**
	 * @param initialCapacity - the initial capacity 
	 * of the array on its first create
	 * @throws Exception
	 * A constructor with one parameter requiring initial capacity.
	 */
	
	public ArrayList( int initialCapacity ) {
		/*if ( initialCapacity < 0 ) {
			throw new Exception( "Size cannot be less than 0" );
		}*/
		
		objects = new Object[initialCapacity]; 
		size = 0;
	}

	/**
	 * A method that checks if the array boundaries should be expanded or not
	 */
	private void checkToExpand() { 

		if ( size + 1 < objects.length ) {
			return; 
		}

		int temporarySize = size << 1;

		if ( temporarySize > Integer.MAX_VALUE ) {
			return; 
		}

		Object[] temp = new Object[temporarySize];

		for ( int i = 0; i < size; i++ ) {
			temp[i] = objects[i];
		}

		objects = temp;
	}

	
	/**
	 * @param index - the index to be checked
	 * A method that throws an exception to check if the 
	 * index to be used is out of bounds
	 */
	/*private void preventsIndexBounds( int index ) {
		if ( index < 0 || index > size ) {
			throw new IndexOutOfBoundsException();
		}
	}*/

	/**
	 * @param o - the object to add to the ArrayList
	 * @return if the add was successful
	 * A method that adds to the end of the ArrayList
	 */
	public boolean add( E o ) {
		return add( size , o );
	}

	/**
	 * @param index - the index to be added to
	 * @param o - the object to be added
	 * @return - if the add was successful
	 * A method that adds to the ArrayList at a certain index.
	 */
	public boolean add( int index , E o ) {
		checkToExpand();
		if ( index < 0 || index > size ) {
            throw new IndexOutOfBoundsException();
        }

		for ( int i = size; i > index; i-- ) {
			objects[i] = objects[i - 1];
		}

		objects[index] = o; 
		size++;
		
		return true;
	}


	/**
	 * @param index - the index of the object to be removed
	 * @return - the object removed
	 * A method that removes an object at a certain index
	 */
	@SuppressWarnings("unchecked")
    public E remove( int index ) {
		if ( index >= size || index < 0 ) { 			
			throw new IndexOutOfBoundsException();
		}
		Object object = objects[index]; 
		objects[index] = null;
		for ( int i = index; i < size; i++ ) {
			objects[i] = objects[i + 1];
		}

		size--;
		return ( E ) object;
	}

	
	/**
	 * @param o - the object to be removed
	 * @return if the object is removed or not
	 * A method that removes an object
	 */
	public boolean remove( Object o ) {
		if ( indexOf( o ) > 0 ) {
			remove( indexOf( o ) );
			return true;
		}
		return false;
		/*return remove( indexOf( o ) ) != null;*/
	}

	
	/**
	 * @param index - the index of the object to be changed
	 * @param o - the new value of the index
	 * @return the object changed
	 * A method that sets the value at a certain index to 
	 * the value of the second parameter
	 */
	@SuppressWarnings("unchecked")
    public E set( int index , E o ) {
 
		if ( index >= size || index < 0 ) { 			
			throw new IndexOutOfBoundsException();
		}
		
		E oldObject = ( E )objects[index];

		objects[index] = o;

		return oldObject;
	}

	/**
	 * @param index - the index to be retrieved
	 * @return the object at the index
	 * A method that retrieves an object from a certain index
	 */
	@SuppressWarnings("unchecked")
    public E get( int index ) {
		if ( index < 0 || index >= size ) {
			throw new IndexOutOfBoundsException();
		}
		/*if ( objects == null ) {
			throw new IndexOutOfBoundsException();
		}*/
		return ( E ) objects[index]; 
	}

	/**
	 * @return the size of the ArrayList
	 * A method that returns the size of the ArrayList
	 */
	public int size() {
		return size;
	}

	/**
	 * @param o - the object to be checked
	 * @return - the index of the object in the ArrayList; 
	 * returns -1 if the object is not in the ArrayList
	 */
	public int indexOf(Object o) {
		for ( int i = 0 ; i < size; i++ ) {
			if ( o.equals(objects[i] ) ) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Clears the arraylist
	 */
	public void clear() {
		size = 0;
	}
 
	/**
	 * @param o - the object to be checked
	 * @return if the object is in the ArrayList or not
	 * A method that checks if an object is contained within the ArrayList
	 */
	public boolean contains( Object o )	{
		return indexOf( o ) != -1;
	}

	/**
	 * @param o - the object to be checked
	 * @return the index of the last occurrence of the object
	 * A method that checks the last occurrence of the object
	 */
	public int lastIndexOf( Object o ) {
		int index = -1; 

		for ( int i = 0; i < size; i++ ) {
			if ( o.equals( objects[i] ) ) {
				index = i;
			}
		}

		return index;
	}

	/**
	 * @return an array version of the ArrayList
	 * A method that returns an array version of the arraylist
	 */
    public Object[] toArray() {
		
		Object[] temp = new Object[size]; 

		for ( int i = 0; i < size; i++ ) { 

			temp[i] = objects[i]; 

		}

		return temp;
	}

	/**
	 * @return a string variety of the arraylist
	 */
	public String toString() {
		if ( objects == null ) {
			return "[]";
		}
		
		StringBuilder ans = new StringBuilder("[");
		for ( int i = 0; i < size; i++ ) {
			ans.append( objects[i] );
			if ( i != size - 1 ) {
				ans.append( ", " );
			}
		}
		ans.append( "]" );
		
		return ans.toString();
	}

	/**
	 * @param o - the object to be compared to
	 * @return true or false depending on if the two
	 * objects are equals
	 * checks if the objects are equal
	 */
	public boolean equals( Object o ) {
		if ( ( o instanceof ArrayList ) ) {
			ArrayList temp = ( ArrayList )o;
			if ( temp.size == size ) {
				
				for ( int i = 0; i < size; i++ ) {
					if ( !objects[i].equals( temp.get( i ) ) ) {
						return false;
					}
				}
				return true;
			} 
		}
		
		return false;
	}

	/**
	 * @return if the string is empty or not
	 * A method that returns true only if the arraylist
	 * is empty
	 */
	public boolean isEmpty() {
		return size == 0; 
	}

}	