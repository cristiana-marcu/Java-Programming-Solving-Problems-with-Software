import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public int getNumPoints (Shape s) {
        // Put code here
        // Start totalPoints = 0
        int totalPoints = 0;
        for (Point p : s.getPoints()) {
            totalPoints = totalPoints + 1;
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double perimeter = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = perimeter / numPoints;
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for(Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(currDist > largestSide) {
                largestSide = currDist;
            }
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for(Point p : s.getPoints()) {
            double currX = p.getX();
            if(currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeter = 0.0;
        
        for (File f: dr.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            
            double currPeri = getPerimeter(shape);
            
            if(currPeri > largestPerimeter) {
                largestPerimeter = currPeri;
            }
        }
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        double largestPerimeter = 0.0;
        
        for (File f: dr.selectedFiles()) {
            
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            
            double currPeri = getPerimeter(shape);
            
            if(currPeri > largestPerimeter) {
                largestPerimeter = currPeri;
                temp = f;
            }
        }
        return temp.getName();
    }
    
    public void testGetNumPoints() {
        Shape triangle = new Shape();
        Point a = new Point(3, 4);
        Point b = new Point(2, 5);
        Point c = new Point(1, 1);
        triangle.addPoint(a);
        triangle.addPoint(b);
        triangle.addPoint(c);
        int numPoints = getNumPoints(triangle);
        System.out.println(numPoints);
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int numPoints = getNumPoints(s);
        System.out.println("number of points: " + numPoints);
        
        double averageLength = getAverageLength(s);
        System.out.println("Average Length = " + averageLength);
        
        double largestSide = getLargestSide(s);
        System.out.println("Largest side: " + largestSide);
        
        double largestX = getLargestX(s);
        System.out.println("Largest x: " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter: " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String file = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter: " + file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
