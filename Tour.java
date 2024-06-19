/*  Name: Nikki Liu
 *  PennKey: nikkiliu
 *  Execution: java Tour
 *
 *  A class implementing TourInterface that models the traveling salesman 
 *  problem using 3 different heuristics to add points to the tour. The 
 *  program also calculates the tour's size and total distance.
 *
 */
public class Tour implements TourInterface {

    private Node head;
    //added nodes are inserted before this node
    private Node lastNode;

    //creates an empty Tour
    public Tour() {
        head = null;
        lastNode = null;
    }

    //create a String representation of the Tour
    public String toString() {
        Node curr = head;
        String tourStr = "";

        if (head == null) {
            return "";
        } else {
        //calls toString() on each Point
            while (curr != null) {
                tourStr += curr.point.toString();
                curr = curr.next;
            }
        return tourStr;
        }
    }

    //call drawTo(Point p) to draw lines between points
    public void draw(Point p) {
        Node curr = head;
        if (curr == null) {
            return;
        }
        while (curr.next != null && p != null) {
            //resets it back to black if the line doesn't start/end at p
            PennDraw.setPenColor(PennDraw.BLACK);
            //draw any point starting/ending at p in a different color
            if (curr.next.point == p || curr.point == p) {
                PennDraw.setPenColor(PennDraw.RED);
            }
            curr.point.drawTo(curr.next.point);
            curr = curr.next;
            
        }
    }

    //number of Points in this Tour
    public int size() {
        Node curr = head;
        int total = 0;
        if (curr == null) {
            return total;
        }
        while (curr.next != null) {
            total++;
            curr = curr.next;
        }
        return total;
    }

    //total distance in the Tour
    public double distance() {
        Node curr = head;
        double length = 0.0;
        if (curr == null) {
            return length;
        }
        while (curr.next != null) {
            //iterate through a tour to find the distanceTo from point to point
            Point currentPt = curr.point;
            Point nextPt = curr.next.point;
            length += currentPt.distanceTo(nextPt);
            curr = curr.next;
        }
        return length;
    }

    //inserts p at the end of the Tour
    public void insertInOrder(Point p) {
        if (head == null) {
            head = new Node(p);
            lastNode = new Node(head.point);
            head.next = lastNode;
        } else {
        //when this loop ends, we stop at the second to last node
            Node curr = head;
            while (curr.next.next != null) {
                curr = curr.next;
            }
            //add node in between second to last and the last node
            curr.next = new Node(lastNode, p);
        }
    }

    //inserts p using nearest neighbor heuristic
    public void insertNearest(Point p) {
        //find the closest node in the tour to the new Node with p
        Node addNode = new Node(p);
        double currentDistance = 0.0;
        double minDistSoFar = Integer.MAX_VALUE;
        Node closestNode = null;
        if (head == null) {
            head = new Node(p);
            lastNode = new Node(p);
            head.next = lastNode;   
        } else {
            Node curr = head;
            //loop through all nodes to find distanceTo(Point p) addNode's point
            while (curr.next != null) {
                Point currPt = curr.point;
                currentDistance = currPt.distanceTo(p); 
                if (currentDistance < minDistSoFar) {
                    closestNode = curr;
                    minDistSoFar = currentDistance;
                }
                curr = curr.next;
            }
            //insert addNode after closestNode
            addNode.next = closestNode.next;
            closestNode.next = addNode;
        }
    }

    /*inserts p using smallest increase heuristic
    loop through all nodes in order to find p's 
    distanceTo the point before (first half), and the distanceTo the 
    node after in the original chain (second half)*/
    
    public void insertSmallest(Point p) {
        Node addNode = new Node(p);
        double incremDist = 0.0;
        double previousDist = 0.0;
        double afterDist = 0.0;
        double origDist = 0.0;
        double minIncremDistSoFar = Integer.MAX_VALUE;
        Node closestNode = null;
        Node nextNode = null;
        
        if (head == null) {
            head = new Node(p);
            lastNode = new Node(head.point);
            head.next = lastNode;   
        } else {
            Node curr = head;
            while (curr.next != null) {
                Point currPt = curr.point;
                Point nextPt = curr.next.point;
                previousDist = currPt.distanceTo(p);
                afterDist = nextPt.distanceTo(p);
                origDist = currPt.distanceTo(nextPt);
                incremDist = previousDist + afterDist - origDist;
                
                if (incremDist < minIncremDistSoFar) {
                    closestNode = curr;
                    nextNode = closestNode.next;
                    minIncremDistSoFar = incremDist;
                }
                curr = curr.next;
            }
            //insert addNode between previous (closestNode) and the next Node 
            addNode.next = closestNode.next;
            closestNode.next = addNode;
        }
    }

    public static void main(String[] args) {
        Tour myTour = new Tour();
        Point pt1 = new Point(0, 0.3);
        Node a = new Node(pt1);
        myTour.insertInOrder(pt1);
        System.out.println("First Point: " + myTour.toString());
        Point pt2 = new Point(0.6, 0.3);
        myTour.insertInOrder(pt2);
        System.out.println("Second Point: " + myTour.toString());
        Point pt3 = new Point(0.3, 0.6);
        myTour.insertInOrder(pt3);
        System.out.println("Third Point: " + myTour.toString());
        Point pt4 = new Point(0.3, 0);
        myTour.insertInOrder(pt4);
        System.out.println("Fourth Point: " + myTour.toString());

        myTour.draw(pt1);
    }
}
