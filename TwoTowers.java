import java.util.Vector;
import java.util.Iterator;

/**
 * This class finds every subset of n blocks to find two towers that are closest
 * in height out of all the subsets.
 *
 * @author Jeremy Mednik, Kyle Wiblishauser
 */
public class TwoTowers {
  private static Vector<Double> height;

  /**
   * A method that determines the height of a given subset.
   *
   * @param a vector.
   * @return a double representing height.
   */
  public static Double subsetHeight (Vector<Double> subset) {
    double subsetHeights = 0;
    for (int i = 0; i < subset.size(); i++) {
      subsetHeights += subset.get(i);
    }
    return subsetHeights;

  }
  
  /**
   * A method that finds the face values of the smaller tower.
   *
   * @param a vector.
   * @return an integer array containing all the face values.
   */
  public static int[] getFaceValues(Vector<Double> bestSubset) {
    int[] faceValues = new int[bestSubset.size()];
    for (int i = 0; i < bestSubset.size(); i++) {
      int faceValue = (int) Math.round(Math.pow(bestSubset.get(i), 2));
      faceValues[i] = faceValue;
    }
    return faceValues;
  }

  /**
   * A main method to find the target height for a tower and the biggest subset
   * that is smaller than target height.
   *
   * @param args[0] determines the number of cubes.
   */
  public static void main(String args[]) {
    int numCubes = Integer.parseInt(args[0]);
    double totalHeight = 0;
    height = new Vector<Double>(numCubes);
    //Adds the respective heights of each cube into a vector.
    for (int i = 1; i <= numCubes; i++) {
      double j = i;
      height.add(i-1, Math.sqrt(j));
      totalHeight += Math.sqrt(i);
    }
    //The subset aims to be lower than targetHeight.
    double targetHeight = totalHeight / 2;
    SubsetIterator<Double> testIterator = new SubsetIterator<Double>(height);
    double biggest = 0;
    Vector<Double> biggestVector = new Vector<Double>();
    /*
    * Goes through each possible subset to determine the largest one that is
    * less than or equal to the target height.
    */
    while (testIterator.hasNext() == true) {
      Vector<Double> subsetVector = testIterator.next();
      double subsetLength = subsetHeight(subsetVector);
      if (subsetLength <= targetHeight && subsetLength > biggest) {
        biggest = subsetLength;
        biggestVector = subsetVector;
      }
      testIterator.next();
    }
    //Smaller tower height.
    System.out.println("Smaller Tower: " + biggest);
    int[] faceValues = getFaceValues(biggestVector);
    //Face values of smaller tower.
    System.out.println("Face Values of Smaller Tower: ");
    for(int j = 0; j < faceValues.length; j++) {
      System.out.println(faceValues[j]);
    }
    double otherTower = totalHeight - biggest;
    //Bigger tower height.
    System.out.println("Bigger Tower: " + otherTower);
    //Tower height difference in height.
    System.out.println("Difference Between Two Towers: " + (otherTower - biggest));
  }





}
