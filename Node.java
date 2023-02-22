//Cason Nichols

public class Node {
    private String element;
    private Node next;
    public int weight;
    public Node p;

    public Node(String element) {
        this.element = element;
        this.next = null;
        this.weight = 0;
        this.p = null;
    }

    public Node(Node node) {
        this.element = node.getElement();
        this.next = node.getNext();
        this.weight = node.getWeight();
        this.p = node.getP();
    }

    public Node getNext() {
        return next;
    }

    public String getElement() {
        return element;
    }

    public int getWeight() {
        return weight;
    }

    public Node getP() {
        return p;
    }

    public void setP(Node p) {
        this.p = p;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
