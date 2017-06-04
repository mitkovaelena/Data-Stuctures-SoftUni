package exercises.pathsWithGivenSum;

import lab.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PathsWithGivenSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<Integer, Tree<Integer>> treeMap = new HashMap<>();
        Tree<Integer> tree = new Tree<Integer>(0);

        for (int i = 0; i < n - 1; i++) {
            int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            treeMap.putIfAbsent(intArr[0], new Tree<Integer>(intArr[0]));
            treeMap.putIfAbsent(intArr[1], new Tree<Integer>(intArr[1]));
            treeMap.get(intArr[0]).addChild(treeMap.get(intArr[1]));
        }

        int sum = Integer.parseInt(reader.readLine());

        for (Tree<Integer> t : treeMap.values()) {
            if (t.getParent() == null) {
                tree = t;
                break;
            }
        }

        System.out.println("Paths of sum " + sum + ": ");

        //implement DFS
    }
}
