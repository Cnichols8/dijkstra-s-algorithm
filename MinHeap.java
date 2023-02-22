//Cason Nichols

public class MinHeap {

    private int heap_size;
    private int[] Heap;

    public MinHeap(int heap_size, int[] Heap) {
        this.heap_size = heap_size;
        this.Heap = Heap;
    }

    //empty constructor to call in the dijkstra class
    public MinHeap() {

    }

    //getHeap method
    public int[] getHeap() {
        return Heap;
    }

    //setting the Heap
    public void setHeap(int[] heap) {
        this.Heap = heap;
    }

    //getting the heap size
    public int getHeap_size() {
        return heap_size;
    }

    //setting the heap size
    public void setHeap_size(int heap_size) {
        this.heap_size = heap_size;
    }

    // leftchild method
    private int leftChild(int pos) {

        return (2 * pos);
    }

    //rightchild method
    private int rightChild(int pos) {

        return (2 * pos) + 1;
    }

    //method to build the min heap
    //psuedocode was used to help create this method
    public void Build_Min_Heap(int[] A, Node[] heapArray) {

        //setting heap_size to be the length of array minus 1
        heap_size = A.length - 1;
        //waling through the array
        for(int i = A.length / 2; i >= 1; i--) {
            //calling min heapify
            Min_Heapify(A, i, heapArray);
        }
    }

    //min heapify method
    //psuedocode was used to help create this method
    public void Min_Heapify(int[] A, int pos, Node[] heapArray) {

        //making the "leftchild" have an unreachable value
        int left = Integer.MAX_VALUE;
        //making the "rightchild" have an unreachable value
        int right = Integer.MAX_VALUE;

        //checking if the leftchild is less than the heap_size
        if (leftChild(pos) <= heap_size) {
            //setting the max value to the array size of the leftchiild
            left = A[leftChild(pos)];
        }
        //checking if the rightchild is less than the heap_size
        if (rightChild(pos) <= heap_size) {
            //setting the max value to the array size of the rightchild
            right = A[rightChild(pos)];
        }
        //if loop to work on the right side of the heap
        if (right != Integer.MAX_VALUE && left != Integer.MAX_VALUE) {
            if (heapArray[right].getWeight() < heapArray[A[pos]].getWeight() && heapArray[right].getWeight() <= heapArray[left].getWeight()) {
                //calling swap method to change numbers around
                swap(A, pos, rightChild(pos));
                //recalling the min heapify method to keep it a heap
                Min_Heapify(A, rightChild(pos), heapArray);
            }
            //else if loop to work on the left side of the heap
            else if (heapArray[left].getWeight() < heapArray[A[pos]].getWeight() && heapArray[left].getWeight() < heapArray[right].getWeight()) {
                //calling the swap method to change numbers around
                swap(A, pos, leftChild(pos));
                //recalling the min heapify method to keep it a heap
                Min_Heapify(A, leftChild(pos), heapArray);
            }
        }
        //checking to make sure that left is not equal to the max value
        else if (left != Integer.MAX_VALUE) {
            //checking to make sure that the left child is less than the array
            if (heapArray[left].getWeight() < heapArray[A[pos]].getWeight()) {
                //calling the swap method to change numbers around
                swap(A, pos, leftChild(pos));
                //recalling the min heapify method to keep it a heap
                Min_Heapify(A, leftChild(pos), heapArray);
            }
        }
        //checking to make sure that right is not equal to the max value
        else if (right != Integer.MAX_VALUE) {
            //checking to make sure that the right child is less than the array
            if (heapArray[right].getWeight() < heapArray[A[pos]].getWeight()) {
                //calling swap method to change numbers around
                swap(A, pos, rightChild(pos));
                //recalling the min heapify method to keep it a heap
                Min_Heapify(A, rightChild(pos), heapArray);
            }
        }
    }

    //swap method to swap numbers around in the heap
    public void swap(int[] A, int x, int y) {

        //setting a variable temp
        int temp = A[x];
        //array x equals array y
        A[x] = A[y];
        //array y equals temp
        A[y] = temp;
    }

    //getMin method to find the minimum node and extract it from the min heap
    public int getMin(int[] heap, Node[] heapArray) {
        if (heap_size <= 0) {
            return -1;
        }
        int current = heap[1];
        heap[1] = heap[getHeap_size()];
        heap_size--;
        Min_Heapify(heap, 1, heapArray);
        return current;
    }
}
