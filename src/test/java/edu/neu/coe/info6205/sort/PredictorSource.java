package edu.neu.coe.info6205.sort;


        import java.lang.reflect.Array;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.List;
        import java.util.Random;
        import java.util.function.Supplier;


class PredictorSource {
    public PredictorSource(int N, int M, Random random) {
        n = N;
        m = M;
        this.random = random;
    }

    public PredictorSource(int N, int M, long seed) {
        this(N, M, new Random(seed));
    }

    public PredictorSource(int N, int M) {
        this(N, M, new Random());
    }

    /**
     * Method to return a Supplier of an int array where there are n ints and each int is in range -m thru m-1.
     *
     * @param safetyFactor a factor to ensure that we do in fact return sufficient elements (n).
     * @return a Supplier which provides a sorted list of distinct ints.
     */
    public Supplier<Integer[]> intsSupplier(int safetyFactor) {
        return () -> {
            int[] ints = (int[]) Array.newInstance(int.class, safetyFactor * n);
            for (int i = 0; i < ints.length; i++) ints[i] = random.nextInt(safetyFactor * m) - safetyFactor * m / 2;
            Arrays.sort(ints);
            int[] distinct = Arrays.stream(ints).distinct().toArray();
            int[] result = (int[]) Array.newInstance(int.class, n);
            for (int i = 0; i < n; i++)
                result[i] = distinct[i * (distinct.length / n)];

            Integer[] r = Arrays.stream( result ).boxed().toArray( Integer[]::new );
            List<Integer> list= Arrays.asList(r);
            Collections.shuffle(list);
            list.toArray(r);
            //list.toArray(array);
            return r;
        };
    }

    private final int n;
    private final int m;
    private final Random random;
}