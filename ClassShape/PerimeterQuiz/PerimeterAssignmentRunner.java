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
        int number = 0;
        for (Point element : s.getPoints()) {
            number++;
        }
        return number;
    }

    public double getAverageLength(Shape s) {
        double average = getPerimeter(s) / (double) getNumPoints(s);
        return average;
    }

    public double getLargestSide(Shape s) {
        double currentSide = 0;
        double max = 0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) { 
            currentSide = prevPt.distance(currPt);
            if (currentSide > max) {
                max = currentSide;
            }
            prevPt = currPt;
        }
        
        
        return max;
    }

    public double getLargestX(Shape s) {
        double currentX = 0;
        double max = 0;
        for (Point currPt : s.getPoints()) {
            currentX = currPt.getX();
            if (currentX > max) {
                max = currentX;
            }
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        double max = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double largestPerimMulti = getPerimeter(s);
            if (largestPerimMulti > max) {
                max = largestPerimMulti;
            }
        }
        return max;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double max = 0;
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double largestPerimMulti = getPerimeter(s);
            if (largestPerimMulti > max) {
                max = largestPerimMulti;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double average = getAverageLength(s);
        double longestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("the average length of a side in your shape is = " + average);
        System.out.println("the longest length of a side in your shape is = " + longestSide);
        System.out.println("the largest X in your shape is = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimMulti = getLargestPerimeterMultipleFiles();
        System.out.println("the largest perimi in selected files = " + largestPerimMulti);
    }

    public void testFileWithLargestPerimeter() {
        String name = getFileWithLargestPerimeter();
        System.out.println("name of the file with largest shape is = " + name);
        
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
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
