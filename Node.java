/**
*  Name: Nikki Liu
*  PennKey: nikkiliu
*  Execution: N/A, this class is meant to be used by other classes
*
*  A simple Node class to be used in a linked list by the Snake class.
*  Each Node refers to the next Node in the list and contains 
*  an x and y coordinate.
**/

public class Node {
    public Node next;
    public int x;
    public int y;
    
    public Node(int x, int y) {
        next = null;
        this.x = x;
        this.y = y;
    }
    
    public Node(Node n, int x, int y) {
        next = n;
        this.x = x;
        this.y = y;
    }
}