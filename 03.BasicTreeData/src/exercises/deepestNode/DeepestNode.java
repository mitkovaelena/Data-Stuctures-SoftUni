package exercises.deepestNode;

import lab.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DeepestNode {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Tree<Integer>> treeMap = new HashMap<>();
        int n = Integer.parseInt(reader.readLine());
        Tree<Integer> tree = new Tree<Integer>(0);

        for (int i = 0; i < n - 1; i++) {
            int[] intArr = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            treeMap.putIfAbsent(intArr[0], new Tree<Integer>(intArr[0]));
            treeMap.putIfAbsent(intArr[1], new Tree<Integer>(intArr[1]));
            treeMap.get(intArr[0]).addChild(treeMap.get(intArr[1]));
        }

        for (Tree<Integer> t : treeMap.values()) {
            if (t.getParent() == null) {
                tree = t;
                break;
            }
        }

        System.out.println("Deepest node: " + findDeepestNode(tree));
    }

    private static int findDeepestNode(Tree<Integer> tree) {
        Map<Integer, Integer> nodeDepth = new LinkedHashMap<>();
        DFS(tree, nodeDepth, 0);
        return Collections.max(nodeDepth.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static void DFS(Tree<Integer> parentNode, Map<Integer, Integer> nodeDepth, int depth) {
        for (Tree<Integer> child : parentNode.getChildren()) {
            DFS(child, nodeDepth, depth + 1);
        }
        nodeDepth.putIfAbsent(parentNode.getValue(), depth);
    }
}
