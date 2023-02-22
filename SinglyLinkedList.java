//Cason Nichols

public class SinglyLinkedList {
    protected Node head;
    protected long size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(Node node) {
        if (node == null) {
            return;
        }
        if (head == null) {
            head = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
    }

    //method to get a node from the linked list
    public Node getNode(SinglyLinkedList linkedList, int listIndex) {
        //initializing a count variable
        int count = 0;
        //setting the current not to the head node
        Node current = linkedList.head;
        //checking that count is less than the listIndex
        while (count < listIndex) {
            //moving current
            current = current.getNext();
            //incrementing count
            count++;
        }
        //returning the current node
        return current;
    }
}