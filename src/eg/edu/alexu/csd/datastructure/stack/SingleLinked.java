package eg.edu.alexu.csd.datastructure.stack;


import java.util.LinkedList;

public class SingleLinked {
    private Node head;
    private int size=0;


    public SingleLinked(){
        head= new Node();
    }
    //Now the List is initialized.

    public void add(Object element) {
        size++;
        if(isEmpty())
            head.setElement(element);
        else{
            Node temp;
            temp=head;
            while(temp.getNext()!=null){
                temp=temp.getNext();
            }
            temp.setNext(new Node());
            temp.getNext().setElement(element);
        }
    }

    public void add(int index, Object element) {
        if(index==size)
            add(element);
        checkIndex(index);
        size++;
        if(index==0){
            //Insert at head.
            Node temp= new Node();
            temp.setElement(element);
            temp.setNext(head);
            head=temp;
            return;
        }
        Node before=getNode(index-1);
        Node after=getNode(index);
        Node temp= new Node();
        temp.setElement(element);
        temp.setNext(after);
        before.setNext(temp);
    }
    private Node getNode(int index){
        int i=0;
        Node temp=head;
        while(i!=index){
            i++;
            temp=temp.getNext();
        }
        return temp;
    }

    public Object get(int index) {
        checkIndex(index);
        return getNode(index).getElement();
    }


    public void set(int index, Object element)  {
        checkIndex(index);
        getNode(index).setElement(element);
    }

    public void clear() {
        size=0;
        if(isEmpty())
            return;
        head.setNext(null);
        head.setElement(null);

    }
    public void checkIndex(int index)  {
        if(index<0)
            throw new ArithmeticException("Index is negative.");
        if(index>size-1)
            throw new NullPointerException("Index out of bounds");
    }


    public boolean isEmpty() {
        return head.getElement() == null;
    }


    public void remove(int index) {
        checkIndex(index);
        size--;
        if(index==0)//The head node
        {
            head=head.getNext();
            return;
        }
        Node before=getNode(index-1);
        if(index+1<size) {
            Node after = getNode(index + 1);
            before.setNext(after);
        }
        else
            before.setNext(null);
        //At the end of the list
    }


    public int size() {
        return size;
    }


    public SingleLinked sublist(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex);
        if(toIndex<fromIndex)
            throw new ArithmeticException("Wrong Interval");
        //Now the indexes are correct.
        SingleLinked newList=new SingleLinked();
        for(int i=fromIndex;i<=toIndex;i++){
            newList.add(get(i));
        }
        return newList;
    }


    public boolean contains(Object o) {
        Node temp=head;
        while(temp.getNext()!=null){
            if(temp.getElement().equals(o))
                return true;
            temp=temp.getNext();
        }
        return false;
    }


    public String toString() {
        String list="{ ";
        if(isEmpty())
            throw new NullPointerException("List is empty .");
        Node temp=head;
        while(temp!=null){
            list+=temp.getElement().toString();
            list+=" ,";
            temp=temp.getNext();
        }
        list+=" }";
        return list;
    }
    public boolean hasNext (int x){
        if(getNode(x).getNext() != null)
            return true;
        else
            return false;
    }


    static class Node{//The list's element.

        private Object element;
        private Node next;


        public void setElement(Object element) {

            this.element = element;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Object getElement() {
            return element;
        }

        public Node getNext() {
            return next;
        }
    }
}

