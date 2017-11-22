package org.examples.algorithms.dp;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class StockMaximize {

    public static final int MIN_PRICE = 1;
    public static final int MAX_PRICE = 100000;

    private static int[] buildMaxPriceLookup(int[] prices) {
        int N = prices.length;
        int[] lookup = new int[N];
        lookup[N - 1] = MIN_PRICE - 1;

        for (int t = N - 1; t >= 1; --t) {
            lookup[t - 1] = Math.max(prices[t], lookup[t]);
        }

        return lookup;
    }

    public static long optimize(int[] prices) {
        int N = prices.length;
        int[] maxPriceLookup = buildMaxPriceLookup(prices);
        long shares = 0;
        long profit = 0;

        for(int t = 0; t < N; ++t) {
            if(prices[t] < maxPriceLookup[t]) {
                // BUY
                shares++;
                profit -= prices[t];
                System.out.println(String.format("time=%d: buying @ %d, profit = %d", t, prices[t], profit));
            } else if(prices[t] > maxPriceLookup[t] && shares > 0) {
                System.out.println(String.format("time=%d: selling %d shares @ %d, profit = %d ", t, shares, prices[t], profit));
                // SELL
                profit += Math.multiplyExact(shares, prices[t]);
                shares = 0;
            } else {
                System.out.println(String.format("time=%d: holding ", t));
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        int[] prices = { 1, 3, 1, 2 };
        //int[] prices = { 1, 2, 100 };
        //int[] prices = { 5, 3, 2 };
        int N = prices.length;

        int[] maxPriceLookup = buildMaxPriceLookup(prices);

        System.out.println(Arrays.toString(maxPriceLookup));

        long maxProfit = optimize(prices);
        System.out.println(String.format("max profit = %d", maxProfit));
    }

    /*public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileInputStream("/Users/panarky/Projects/tmp/stockMaximize.input.txt"));
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }

            if (a0 == 3) {
                long maxProfit = optimize(arr);
                System.out.println(maxProfit);
            }
        }
        in.close();
    }*/
}
