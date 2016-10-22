
/**
 * 
 * @author Mar
 * @version 2013-01-31
 * 
 * @param <E>
 */
public class LinkedList<E> {

    private Node head;
    private int size;

    /**
     * YWRT
     */
    public LinkedList() {

        this.head = null;
        this.size = 0;

    }

    /**
     * YWRT
     * 
     * @param index
     *            YWRT
     */
    private void checkAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * YWRT
     * 
     * @param index
     *            YWRT
     */
    private void check(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * YWRT
     */
    private void checkHead() {
        if (head == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * YWRT
     * 
     * @param data
     *            YWRT
     * @return true YWRT
     */
    public boolean add(E data) {

        add(size, data);
        return true;

    }

    /**
     * YWRT
     * 
     * @param index
     *            YWRT
     * @param data
     *            YWRT
     */
    public void add(int index, E data) {
        checkAdd(index);

        if (size == 0) {
            head = new Node(data);
            size++;
            return;
        }

        if (index == 0) {

            head = new Node(data, head);
            size++;

            return;
        }

        Node current = head;
        int location = 0;

        while (current.next != null && location < index - 1) {
            current = current.next;
            location++;
        }

        current.next = new Node(data, current.next);
        size++;

    }

    /**
     * YWRT
     * 
     * @param index
     *            YWRT
     * @return YWRT
     */
    public Object remove(int index) {

        check(index);
        checkHead();

        Node current = head;
        int location = 0;

        if (size == 1 || index == 0) {
            Object cData = current.data;
            current = null;
            size--;

            return cData;
        }

        while (current.next != null && location <= index - 1) {
            current = current.next;
            location++;
        }

        Object currentData = current.next.data;
        current.next = current.next.next;
        size--;

        return currentData;

    }

    /**
     * YWRT
     * 
     * @param o
     *            YWRT
     * @return YWRT
     */
    public boolean remove(Object o) {
        checkHead();
        int index = indexOf(o);

        if (index > -1) {
            remove(index);
            return true;
        }

        return false;
    }

    /**
     * YWRT
     * 
     * @param index
     *            YWRT
     * @param o
     *            YWRT
     * @return YWRT
     */
    public E set(int index, E o) {

        check(index);

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        E tObject = current.data;
        current.data = o;

        return tObject;
    }

    /**
     * YWRT
     * 
     * @param index
     *            YWRT
     * @return YWRT
     */
    public E get(int index) {

        check(index);

        Node node = head;
        int location = 0;

        for (; node.next != null && location < index; node = node.next) {
            location++;
        }

        return (E) node.data;
    }

    /**
     * YWRT
     * 
     * @param o
     *            YWRT
     * @return YWRT
     */
    public int indexOf(Object o) {

        Node current = head;

        for (int location = 0; location < size; location++) {

            if (o.equals(current.data)) {
                return location;
            }

            current = current.next;

        }

        return -1;
    }

    /**
     * YWRT
     */
    public void clear() {
        this.size = 0;
    }

    /**
     * YWRT
     * 
     * @param o
     *            YWRT
     * @return YWRT
     */
    public boolean contains(Object o) {

        return indexOf(o) > -1;

    }

    /**
     * YWRT
     * 
     * @param o
     *            YWRT
     * @return YWRT
     */
    public int lastIndexOf(E o) {

        int lastIndex = -1;
        Node current = head;

        for (int i = 0; i < size; i++) {

            if (current.data.equals(o)) {
                lastIndex = i;
            }

            current = current.next;

        }

        return lastIndex;
    }

    /**
     * YWRT
     * 
     * @return YWRT
     */
    public Object[] toArray() {

        Object[] objects = new Object[size];

        Node current = head;

        for (int i = 0; i < size; i++) {
            objects[i] = current.data;
            current = current.next;
        }

        return objects;
    }

    /**
     * YWRT
     * 
     * @return String
     */
    public String toString() {

        if (size == 0) {
            return "[]";
        }

        Node current = head;
        String string = "[" + current.data;

        if (current.next != null) {
            current = current.next;
        }

        for (int i = 1; i < size; i++) {

            string += ", " + current.data;
            current = current.next;

        }

        return string + "]";
    }

    /**
     * YWRT
     * 
     * @param o
     *            LOL
     * @return LOL
     */
    public boolean equals(Object o) {

        boolean neg = false;

        if (!(o instanceof LinkedList)) {
            return neg;
        }

        @SuppressWarnings("unchecked")
        LinkedList<E> tList = (LinkedList<E>) o;

        if (this.size != tList.size) {
            return neg;
        }

        Node node = head;
        int location = 0;

        for (; node.next != null && location < size; node = node.next) {

            if (!(node.data.equals(tList.get(location)))) {
                return neg;
            }

            location++;

        }

        return true;
    }

    /**
     * YWRT
     * 
     * @return YWRT
     */
    public int size() {

        return size;

    }

    /**
     * YWRT
     * 
     * @return YWRT
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * YWRT
     * 
     * @author Mar
     *
     */
    class Node {

        private Node next;
        private E data;

        /**
         * YWRT
         * 
         * @param data
         *            YWRT
         */
        public Node(E data) {

            this(data, null);

        }

        /**
         * YWRT
         * 
         * @param data
         *            YWRT
         * @param next
         *            YWRT
         */
        public Node(E data, Node next) {

            this.data = data;
            this.next = next;

        }

    }

}