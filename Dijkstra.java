//Cason Nichols

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Dijkstra {

    //global boolean array
    public Node[] isVisited;
    //global linked list
    public SinglyLinkedList result;
    //global minHeap variable
    public MinHeap minHeap;


    public static void main(String[] args) throws FileNotFoundException {
        //creating a new instance of Dijkstra
        Dijkstra dijkstra = new Dijkstra();
        //creating an array list to use to add the data from the file
        ArrayList<String> arrayList = new ArrayList<>();
        //scanner to read in the file
        Scanner sc = new Scanner(new File(args[0]));
        //while loop to ensure we go through the whole data file
        while (sc.hasNextLine()) {
            //adding the data into the arraylist
            arrayList.add(sc.nextLine());
        }
        //creating a new singly linked list array to store the data from the array list
        SinglyLinkedList[] singlyLinkedList = new SinglyLinkedList[arrayList.size()];
        //for loop to iterate through the data
        for (int i = 0; i < arrayList.size(); i++) {
            // getting the index from the data
            String index = arrayList.get(i);
            //splitting the string array at the index and the rest of the data
            String[] indexs = index.split(":");
            //parsing the index's to integers
            int listindex = Integer.parseInt(indexs[0]);
            //splitting the other data set of numbers by commas
            if (indexs.length > 1) {
                String[] data = indexs[1].split(",");
                //creating a new linked list
                SinglyLinkedList singlyLinkedList1 = new SinglyLinkedList();
                //checking to make sure that the indexes aren't empty
                if (!data[0].equals("")) {
                    //splitting the numbers by semicolons
                    String[] data2 = indexs[1].split(";");
                    //iterating through data2
                    for (int x = 0; x < data2.length; x++) {
                        //creating a new node
                        Node node = new Node(String.valueOf(data2[x].charAt(0)));
                        //setting the weight based on the split of the semicolon
                        node.setWeight(Integer.parseInt(String.valueOf(data2[x].charAt(2))));
                        //adding it to the singlylinkedlist
                        singlyLinkedList1.addFirst(node);
                    }
                }
                //creating new node
                Node newNode = new Node(String.valueOf(listindex));
                //adding it to the singlylinkedlist
                singlyLinkedList1.addFirst(newNode);
                //setting the original linked list to the new linked list
                singlyLinkedList[listindex] = singlyLinkedList1;
            }
            else {
                //creating a new singlylinkedlist
                SinglyLinkedList singlyLinkedList2 = new SinglyLinkedList();
                //creating a new node
                Node node = new Node(indexs[0]);
                //adding the indexes
                singlyLinkedList2.addFirst(node);
                //setting the original linked list to the new linked list
                singlyLinkedList[listindex] = singlyLinkedList2;
            }
        }
        //creating a new instance of minHeap
        dijkstra.minHeap = new MinHeap();
        //creating a new linked list for result
        dijkstra.result = new SinglyLinkedList();
        //creating a new boolean array based on the singly linked list length
        dijkstra.isVisited = new Node[singlyLinkedList.length];
        //setting the weight to an unreachable number
        for (int i = 0; i < dijkstra.isVisited.length; i++) {
            dijkstra.isVisited[i] = new Node(String.valueOf(i));
            dijkstra.isVisited[i].setWeight(200000);
        }
        //variable to hold given argument
        int list = Integer.parseInt(args[1]);
        //calling the  method
        dijkstra.dijkstra(singlyLinkedList, singlyLinkedList[list].head);
        //printing out the list using a print method
        dijkstra.print(list);
    }

    //dijkstra method
    public void dijkstra(SinglyLinkedList[] singlyLinkedList, Node q) {

        //setting isVisited to a given weight for the 0 argument
        isVisited[Integer.parseInt(q.getElement())].setWeight(0);
        //creating a int array called heapArray with isVisited length plus 1
        int[] heapArray = new int[isVisited.length + 1];
        //setting index 0 to be 0
        heapArray[0] = 0;
        //iterating through the heapArray
        for (int j = 1; j < heapArray.length; j++) {
            heapArray[j] = j - 1;
        }
        //setting the heap
        minHeap.setHeap(heapArray);
        //calling build min heap
        minHeap.Build_Min_Heap(heapArray, isVisited);
        //while loop to check if the heap size is greater than 0
        while (minHeap.getHeap_size() > 0) {
            //minIndex variable to hold the min
            int minIndex = minHeap.getMin(heapArray, isVisited);
            //creating a new node with the min
            Node node = new Node(isVisited[minIndex]);
            //for loop to iterate through the data set
            for (int x = 0; x < singlyLinkedList[Integer.parseInt(node.getElement())].size; x++) {
                //int index to get the node element
                int index = Integer.parseInt(node.getElement());
                //node u to get the node at the index
                Node u = singlyLinkedList[index].getNode(singlyLinkedList[index], x);
                //int index2 to get the element for node u
                int index2 = Integer.parseInt(u.getElement());
                //if loop to compare the weights of the graph this will help us when relaxing the edges
                if (isVisited[index2].getWeight() > node.getWeight() + u.getWeight()) {
                    //function to update index2
                    isVisited[index2].setWeight(node.getWeight() + u.getWeight());
                    //heapify the heapArray into isVisited
                    minHeap.Min_Heapify(heapArray, minHeap.getHeap_size() / 2 , isVisited);
                    //setting the parent node
                    isVisited[index2].setP(node);
                }
            }
        }
    }

    //print method to print out the answer
    public void print(int list) {

        //for loop to iterate through isVisited
        for (int i = 0; i < isVisited.length; i++) {
            //checking to make sure i isn't the same as list
            if (i != list) {
                //setting node current to equal isVisited
                Node current = isVisited[i];
                //int weight to be the current node's weight
                int weight = current.weight;
                //checking to see if the weight equals the unreachable number if it does than the number is unreachable
                if (weight == 200000) {
                    //printing out the index then unreachable
                    System.out.println("[" + i + "] unreachable");
                }
                else {
                    //creating a new integer stack
                    Stack<Integer> stack = new Stack<>();
                    //checking to make sure that the parent of current isn't null
                    while (current.getP() != null) {
                        //stack push to get the element into the stack
                        stack.push(Integer.parseInt(current.getElement()));
                        //setting current to equal the parent of current
                        current = current.getP();
                    }
                    //pushing those numbers into list
                    stack.push(list);
                    //checking to make sure the stack size is 2
                    if (stack.size() == 2) {
                        //integer to pop out of the stack
                        int val = stack.pop();
                        //a second integer to pop out of the stack
                        int index = stack.pop();
                        //print statement
                        System.out.println("[" + index + "] shortest path: (" + val + "," + index + ") shortest distance: " + weight);
                    }
                    else {
                        //print statement
                        System.out.print("[" + i + "] shortest path: (");
                        //checking to make sure the stack size is greater than 0
                        while (stack.size() > 1) {
                            //print statement
                            System.out.print(stack.pop() + ",");
                        }
                        //the rest of the print statement
                        System.out.print(stack.pop() + ") shortest distance: " + weight + "\n");
                    }
                }
            }
        }
    }
}