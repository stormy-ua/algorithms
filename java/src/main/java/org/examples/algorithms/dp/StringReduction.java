package org.examples.algorithms.dp;

import java.util.*;

public class StringReduction {

    private static Map<String, Set<String>> memo = new HashMap<String, Set<String>>(){{
        put("ab", new HashSet<String>() {{  add("c"); }});
        put("ba", new HashSet<String>() {{  add("c"); }});
        put("ac", new HashSet<String>() {{  add("b"); }});
        put("ca", new HashSet<String>() {{  add("b"); }});
        put("bc", new HashSet<String>() {{  add("a"); }});
        put("cb", new HashSet<String>() {{  add("a"); }});
        put("aa", new HashSet<String>() {{  add("aa"); }});
        put("bb", new HashSet<String>() {{  add("bb"); }});
        put("cc", new HashSet<String>() {{  add("cc"); }});
    }};

    private static int elementSize(Set<String> list) {
        if(list.isEmpty()) {
            return -1;
        }

        return list.iterator().next().length();
    }

    private static Set<String> takeMinimum(Set<String> set1, Set<String> set2) {
        if(set1.isEmpty()) {
            return set2;
        }
        else if (set2.isEmpty()) {
            return set1;
        }

        int size1 = elementSize(set1);
        int size2 = elementSize(set2);

        if(size1 > size2) {
            return set2;
        }
        else if (size1 < size2) {
            return set1;
        }

        set1.addAll(set2);

        return set1;
    }

    public static Set<String> reduce(String s) {
        totalIters++;

        if(memo.containsKey(s)) {

            Set<String> cached = memo.get(s);

            System.out.println(String.format("found in cache - %s -> %s", s, cached));

            return cached;
        }

        System.out.println(String.format("not found in cache - %s", s));

        iters++;

        Set<String> reduced = new HashSet<>();

        for(int i = 0; i < s.length() - 1; ++i) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(i + 1);

            if(c1 != c2) {
                String prefix = s.substring(0, i);
                String suffix = s.substring(i + 2, s.length());
                String replacement = new String(new char[] { c1, c2 });

                String replaced = prefix + memo.get(replacement).iterator().next() + suffix;
                Set<String> windowReduced = reduce(replaced);

                reduced = takeMinimum(reduced, windowReduced);

                if(elementSize(reduced) == 1) {
                    System.out.println(String.format("reduced to minimum, breaking - %s", reduced));

                    break;
                }

                if(elementSize(reduced) == 2 && reduced.size() == 3) {
                    System.out.println(String.format("reduced to minimum, breaking - %s", reduced));

                    break;
                }
            }
        }

        if(reduced.isEmpty()) {
            reduced.add(s);
        }

        memo.put(s, reduced);

        return reduced;
    }

    private static long iters = 0L;
    private static long totalIters = 0L;

    public static void main(String[] args) {

        System.out.println(reduce("ccccc"));
        System.out.println(
                String.format("total iterations - %s, heavy iterations - %s, ratio - %s, memo size - %s",
                        totalIters, iters, (float)iters/totalIters, memo.size()));
    }

}