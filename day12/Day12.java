import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day12 {

    public static int tx, ty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day12/input"));

        int sx = 41, sy = 167;
//        int sx = 5, sy = 8;
        int[][] map = new int[sx][sy];
        boolean[][] visit;
        visit = new boolean[sx][sy];
        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        for (int i = 0; i < sx; i++) {
            String line = br.readLine();
            map[i] = new int[sy];
            for (int j = 0; j < sy; j++) {
                map[i][j] = line.charAt(j) == 'S' ? 0 : line.charAt(j) == 'E' ? 25 : line.charAt(j) - 'a';
                if (line.charAt(j) == 'E') {
                    tx = i;
                    ty = j;
                } else if (line.charAt(j) == 'S') {
                    xs.add(i);
                    ys.add(j);
                } else if (line.charAt(j) == 'a') {
                    xs.add(i);
                    ys.add(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < xs.size(); i++) {
            for (int k = 0; k < map.length; k++) {
                visit[k] = new boolean[map[k].length];
                for (int j = 0; j < map[k].length; j++) {
                    visit[k][j] = false;
                }
            }
            min = Math.min(min, dist(map, visit, xs.get(i), ys.get(i)));
        }
        System.out.println(min);
    }

    static class In {
        int cost, x, y;

        In(int cost, int x, int y) {
            this.cost = cost;
            this.x = x;
            this.y = y;
        }
    }

    private static int dist(int[][] map, boolean[][] visit, int x, int y) {
        Queue<In> bfs = new ArrayDeque<>();
        bfs.add(new In(0, x, y));
        while (!bfs.isEmpty()) {
            var cur = bfs.poll();
            if (visit[cur.x][cur.y])
                continue;
            if (cur.x == tx && cur.y == ty) {
                return cur.cost;
            }
            visit[cur.x][cur.y] = true;
            x = cur.x;
            y = cur.y;

            if (x - 1 >= 0 && map[x - 1][y] <= map[x][y] + 1) {
                bfs.add(new In(cur.cost + 1, x - 1, y));
            }
            if (x + 1 < map.length && map[x + 1][y] <= map[x][y] + 1) {
                bfs.add(new In(cur.cost + 1, x + 1, y));
            }
            if (y - 1 >= 0 && map[x][y - 1] <= map[x][y] + 1) {
                bfs.add(new In(cur.cost + 1, x, y - 1));
            }
            if (y + 1 < map[x].length && map[x][y + 1] <= map[x][y] + 1) {
                bfs.add(new In(cur.cost + 1, x, y + 1));
            }
        }
        return Integer.MAX_VALUE;
    }
}