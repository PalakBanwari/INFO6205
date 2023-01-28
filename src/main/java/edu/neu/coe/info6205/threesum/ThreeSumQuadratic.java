package edu.neu.coe.info6205.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of ThreeSum which follows the approach of dividing the solution-space into
 * N sub-spaces where each sub-space corresponds to a fixed value for the middle index of the three values.
 * Each sub-space is then solved by expanding the scope of the other two indices outwards from the starting point.
 * Since each sub-space can be solved in O(N) time, the overall complexity is O(N^2).
 * <p>
 * NOTE: The array provided in the constructor MUST be ordered.
 */
public class ThreeSumQuadratic implements ThreeSum {
    /**
     * Construct a ThreeSumQuadratic on a.
     * @param a a sorted array.
     */
    public ThreeSumQuadratic(int[] a) {
        this.a = a;
        length = a.length;
    }

    public Triple[] getTriples() {
        List<Triple> triples = new ArrayList<>();
        for (int i = 0; i < length; i++) triples.addAll(getTriples(i));
        Collections.sort(triples);
        return triples.stream().distinct().toArray(Triple[]::new);
    }

    /**
     * Get a list of Triples such that the middle index is the given value j.
     *
     * @param j the index of the middle value.
     * @return a Triple such that
     */
    public List<Triple> getTriples(int j) {
        System.out.println("j is" + j);
        System.out.println("a[j] is" + a[j]);
        List<Triple> triples = new ArrayList<>();
        // FIXME : for each candidate, test if a[i] + a[j] + a[k] = 0.

        for (int i = 0; i < length - 2; i++)
        {
            if (i == 0 || (i > 0 && a[i] != a[i - 1])) {
                //System.out.println("came inside 1");
                int low = i + 1;
                int high = length - 1;
                int sum = 0 - a[i];
                //System.out.println("a low:" + a[low]);
                //System.out.println("a high:" + a[high]);
                //System.out.println("a i:" + a[i]);

                while (low < high) {
                    if (a[low] + a[high] == sum  && (a[low]== a[j] || a[high]== a[j] || a[i]== a[j])) {
                        System.out.println("came inside 2");
                        Triple tripleObj = new Triple(a[low], a[high], a[i]);
                        triples.add(tripleObj);



                        while (low < high && a[low] == a[low + 1])
                            low++;
                        while (low < high && a[high] == a[high - 1])
                            high--;

                        low++;
                        high--;
                    } else if (a[low] + a[high] > sum) {
                        high--;
                    } else {
                        low++;
                    }
                }


            }
        }



        // END
        //System.out.println("size of trip"+triples.size());
        //for(Triple t : triples){
           // System.out.println("each element is"+t);

        //}
        return triples;
    }

    private final int[] a;
    private final int length;
}
