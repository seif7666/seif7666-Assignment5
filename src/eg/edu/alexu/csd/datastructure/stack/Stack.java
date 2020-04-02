package eg.edu.alexu.csd.datastructure.stack;

public class Stack implements IStack {
    private Node head;
    private int size=0;

    /**
     * Constructor:
     * head is initialized.
     */
    public Stack(){
        head=new Node();
    }

    /**
     * Removes the head of the stack if its not empty and throws an error if it it.
     * Make the next element the new head.
     * @return
     * The Top element.
     */
    @Override
    public Object pop() {
        if(isEmpty())
            throw new RuntimeException("List is empty");
        size--;
        Node temp=head;
        head=head.getNext();
        if(head==null){
            head=new Node();
        }
        return temp.getElement();
    }

    /**
     * Gets the top of stack if its not empty and throws an error if it is.
     * @return
     */
    @Override
    public Object peek(){
        if(isEmpty())
            throw new RuntimeException("List is empty");
        return  head.getElement();
    }

    /**
     * Push element to stack.
     * if stack was empty then the element is the head.
     * if not then the element will be the new head and points to old head.
     * @param element
     */
    @Override
    public void push(Object element) {
        if(isEmpty())
            head.setElement(element);
        else{
            Node temp=new Node();
            temp.setElement(element);
            temp.setNext(head);
            head=temp;
        }
        size++;

    }

    /**
     *
     * @return
     * The size of stack.
     */

    @Override
    public int size() {
        return size;
    }

    /**
     *
     * @return
     * True:If list is empty.
     * False:If not.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The class representing the stack nodes.
     */
    private class Node{
        /**
         * Data to be saved in node.
         */
        private Object element;
        /**
         * Pointer to next node.
         */
        private Node next;

        /**
         *
         * @return Element saved in node.
         */
        public Object getElement() {

            return element;
        }

        /**
         * Assigns the element of node.
         * @param element
         * The object to be saved in node.
         */
        public void setElement(Object element) {
            this.element = element;
        }

        /**
         * Getting the next node.
         *
         */
        public Node getNext()
        {
            return next;
        }

        /**
         * Assigns pointer to next node.
         * @param next
         */
        public void setNext(Node next) {

            this.next = next;
        }
    }

}
