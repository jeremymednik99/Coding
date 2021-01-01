import java.util.Vector;
import java.util.Iterator;

/**
 * Uses an iterator that returns each of the 2^n subsets of a Vector with
 * n elements.
 *
 * @author Jeremy Mednik, Kyle Wiblishauser
 */
public class SubsetIterator<E> implements Iterator<Vector<E>> {
   //Member variables
   private Vector<E> set;
   private int subsetMask;

   /**
    * A constructor where set stores the input vector and subset mask is used
    * to determine which subset of the set should be returned next.
    *
    * @param a vector.
    */
   public SubsetIterator(Vector<E> vector1) {
     set = vector1;
     subsetMask = (1 << set.size()) - 1;
   }

   /**
    * Checks to see if there are any subsets left.
    *
    * @return a boolean of whether the subset contains all elements.
    */
   public boolean hasNext() {
     return subsetMask >= 0;
   }

   /**
    * Goes to the next subset if possible.
    *
    * @return a vecotor of a subset.
    */
   public Vector<E> next() {
     //A vector of a set.
     Vector<E> subset = new Vector<E>(set.size());
     for (int i = 0; i < set.size(); i++) {
       int temp = (subsetMask >>> i) & 1;
       //int temp = (i >> subsetMask) & 1;
       /*Adds the ith element of set to the new Vector only if the ith digit of
       * subset mask is 1.
       */
       if (temp == 1) {
         subset.add(set.get(i));
       }
     }
     //The number of subsets decrease by 1.
     subsetMask = subsetMask - 1;
     return subset;
   }

}
