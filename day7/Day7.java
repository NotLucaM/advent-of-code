import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Node {

    String name;
    int size;
    List<Node> children;
    Node parent;

    public Node(String name, int size, Node parent) {
        this.name = name;
        this.size = size;
        this.parent = parent;
        this.children = new ArrayList<>();
    }
}

public class Day7 {

    public static int ans = 0;
    public static int ans2 = Integer.MAX_VALUE;
    public static int needed = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day5/input"));

        Node root = new Node("/", 0, null);
        Node cur = root;

        while (br.ready()) {
            String line = br.readLine();
            if (line.charAt(0) == '$') {
                if (line.charAt(2) == 'c') {
                    String[] e = line.split(" ");
                    String target = e[2];
                    if (target.equals("/")) {
                        cur = root;
                    } else if (target.equals("..")) {
                        cur = cur.parent;
                    } else {
                        boolean found = false;
                        for (var child : cur.children) {
                            if (child.name.equals(target)) {
                                found = true;
                                cur = child;
                                break;
                            }
                        }
                        if (!found) {
                            Node next = new Node(target, 0, cur);
                            cur.children.add(next);
                            cur = next;
                        }
                    }
                }
            } else {
                String[] e = line.split(" ");
                if (e[0].equals("dir")) {
                    Node next = new Node(e[1], 0, cur);
                    cur.children.add(next);
                } else {
                    cur.children.add(new Node(e[1], Integer.parseInt(e[0]), cur));
                }
            }
        }

        int total = dfs(root);
        System.out.println(ans);

        needed = 30000000 - (70000000 - total);
        dfs(root);
        System.out.println(ans2);
    }

    public static int dfs(Node root) {
        if (root.children.isEmpty()) {
            return root.size;
        }
        int size = 0;
        for (var child :
                root.children) {
            size += dfs(child);
        }
        if (size <= 100000) {
            ans += size;
        }
        if (needed != 0) {
            if (size >= needed) {
                ans2 = Math.min(ans2, size);
            }
        }
        return size;
    }
}
