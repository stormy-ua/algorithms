package org.examples.algorithms.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RodCut {


    public static Integer bottomUpCutRod(int n, Map<Integer, Integer> prices, int[] cuts) {
        int[] memo = new int[n + 1];

        memo[0] = 0;

        for (int j = 1; j <= n; ++j) {
            int q = -1;
            for (int i = 0; i <= j; ++i) {
                int current = prices.get(i) + memo[j - i];
                if ( q < current) {
                    q = current;
                    cuts[j] = i;
                }
            }
            memo[j] = q;
        }

        return memo[n];
    }

    public static String cutsToString(int n, int[] cuts) {
        StringBuilder sb = new StringBuilder();

        while(n > 0) {
            int leading = cuts[n];
            int remainder = n - leading;

            n -= leading;
            sb.append(String.format("(%d, %d)", leading, remainder));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 10;
        Map<Integer, Integer> prices = new HashMap<Integer, Integer>() {{
           put(0, 0);
           put(1, 1);
           put(2, 5);
           put(3, 8);
           put(4, 9);
           put(5, 10);
           put(6, 17);
           put(7, 17);
           put(8, 20);
           put(9, 24);
           put(10, 30);
        }};
        int[] cuts = new int[n + 1];

        int optimalCutPrice = bottomUpCutRod(10, prices, cuts);
        String cutsAsString = cutsToString(10, cuts);
        System.out.println(String.format("optimal cut for n = 10: price = %d, cuts = [%s]", optimalCutPrice, cutsAsString));

        optimalCutPrice = bottomUpCutRod(9, prices, cuts);
        cutsAsString = cutsToString(9, cuts);
        System.out.println(String.format("optimal cut for n = 9: price = %d, cuts = [%s]", optimalCutPrice, cutsAsString));

        optimalCutPrice = bottomUpCutRod(5, prices, cuts);
        cutsAsString = cutsToString(5, cuts);
        System.out.println(String.format("optimal cut for n = 5: price = %d, cuts = [%s]", optimalCutPrice, cutsAsString));
    }

}
