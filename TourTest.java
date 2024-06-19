/**
 * Name: Nikki Liu
 * Pennkey: nikkiliu
 * Execution: N/A, TourTest is meant to be used to run the JUnit
 *
 * Description: Contains JUnit tests for Tour and checks each heuristic
 * when run on the tour as well as size, distance, and toString
**/
import org.junit.Test;
import static org.junit.Assert.*;

public class TourTest {
    
    // Use DELTA as the tolerance for your assertEquals statements.
    public static final double DELTA = 1e-2;
    
    // toString tests
    @Test
    public void testToStringEmpty() {
        Tour t = new Tour();
        
        // Check that toString outputs correctly on an empty tour
        String expected = "";
        String actual = t.toString();
        assertEquals(expected, actual);

    }
    
    @Test
    public void testToStringOnePoint() {
        Tour t = new Tour();
        
        // Check that toString outputs correctly on a tour with 1 point
        Point pt1 = new Point(0, 0);
        t.insertInOrder(pt1);

        String expected = "(0.0, 0.0)\n(0.0, 0.0)\n";
        String actual = t.toString();
        assertEquals(expected, actual);

    }
    
    @Test
    public void testToStringSquare() {
        Tour t = new Tour();
        
        /* Check that toString outputs correctly
        * on a tour of a 1x1 square as stated in the writeup
        */
        Point pt1 = new Point(0, 0);
        t.insertInOrder(pt1);
        Point pt2 = new Point(1, 0);
        t.insertInOrder(pt2);
        Point pt3 = new Point(1, 1);
        t.insertInOrder(pt3);
        Point pt4 = new Point(0, 1);
        t.insertInOrder(pt4);

        String expected = 
            "(0.0, 0.0)\n(1.0, 0.0)\n(1.0, 1.0)\n(0.0, 1.0)\n(0.0, 0.0)\n";
        String actual = t.toString();
        assertEquals(expected, actual);
    }
    
    // Distance tests
    @Test
    public void testDistanceEmpty() {
        Tour t = new Tour();
        
        // Check that distance outputs correctly on an empty tour
        double expected = 0.0;
        double actual = t.distance();
        assertEquals(expected, actual, DELTA);
    }
    
    @Test
    public void testDistanceSquare() {
        Tour t = new Tour();
        
        /* Check that distance() outputs correctly
        * on a tour of a 1x1 square as stated in the writeup
        */
        Point pt1 = new Point(0, 0);
        t.insertInOrder(pt1);
        Point pt2 = new Point(1, 0);
        t.insertInOrder(pt2);
        Point pt3 = new Point(1, 1);
        t.insertInOrder(pt3);
        Point pt4 = new Point(0, 1);
        t.insertInOrder(pt4);
                
        double expected = 4.0;
        double actual = t.distance();
        assertEquals(expected, actual, DELTA);
    }
    
    // Size tests
    @Test
    public void testSizeEmpty() {
        Tour t = new Tour();
        
        // Check that size is correct on an empty tour
        int expected = 0;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testSizeSquare() {
        Tour t = new Tour();
        
        /* Check that size() outputs correctly
        * on a tour of a 1x1 square as stated in the writeup
        */
        Point pt1 = new Point(0, 0);
        t.insertInOrder(pt1);
        Point pt2 = new Point(1, 0);
        t.insertInOrder(pt2);
        Point pt3 = new Point(1, 1);
        t.insertInOrder(pt3);
        Point pt4 = new Point(0, 1);
        t.insertInOrder(pt4);
                
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
        
    }
    
    // insertInOrder tests
    @Test
    public void testInsertInOrderTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
        }
        
        // Check that the size is correct for these 4 points
        // These are the points contained in the file "tsp4.txt".
        int[] sizeAfterInsert = { 1, 2, 3, 4 };
    
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertInOrderTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        /*
        * We use two parallel arrays and a for loop to insert points a through e using
        * Tour.insertInOrder(). After each insertion, we check that the distance of the
        * tour was computed correctly. These are the points contained in the file
        * "tsp4.txt".
        */
        Point[] toInsert = { a, b, c, d };
        double[] distanceAfterInsert = { 0, 632.46, 832.46, 963.44 };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertInOrder(toInsert[i]);
            double expected = distanceAfterInsert[i];
            double actual = t.distance();
            // System.out.println(expected + " " + actual);
            assertEquals("mismatch after calling insertInOrder on point at index " + i,
            expected, actual, DELTA);
        }
    }
    
    // insertNearest tests
    @Test
    public void testInsertNearestTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        // Insert points a through d using Tour.insertNearest()
        // These are the points contained in the file "tsp4.txt".
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertNearest(toInsert[i]);
        }
        // Check that the size is correct for these 4 points
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertNearestTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        /*
        * Insert points a through d using Tour.insertNearest()
        * After each insertion, check that the distance of the tour was
        * computed correctly.
        * These are the points contained in the file "tsp4.txt".
        */
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertNearest(toInsert[i]);
        }

        double expected = 956.06;
        double actual = t.distance();
        assertEquals(expected, actual, DELTA);
    }
    
    // insertSmallest tests
    @Test
    public void testInsertSmallestTSP4CorrectSize() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        // Insert points a through d using Tour.insertSmallest()
        // These are the points contained in the file "tsp4.txt".
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertSmallest(toInsert[i]);
        }
        // Check that the size is correct for these 4 points
        int expected = 4;
        int actual = t.size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInsertSmallestTSP0Through4CorrectDistance() {
        Tour t = new Tour();
        
        Point a = new Point(200, 400);
        Point b = new Point(300, 100);
        Point c = new Point(100, 100);
        Point d = new Point(300, 200);
        
        /*
        * Insert points a through d using Tour.insertSmallest()
        * After each insertion, check that the distance of the tour was
        * computed correctly.
        * These are the points contained in the file "tsp4.txt".
        */
        Point[] toInsert = { a, b, c, d };
        
        for (int i = 0; i < toInsert.length; i++) {
            t.insertSmallest(toInsert[i]);
        }
        double expected = 839.83;
        double actual = t.distance();
        assertEquals(expected, actual, DELTA);
    }
}