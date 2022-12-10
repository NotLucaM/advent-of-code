import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day9 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day9/input"));

        Set<String> positions = new HashSet<>();
        int[][] ropes = new int[10][2];
        while (br.ready()) {
            var line = br.readLine();

            String[] split = line.split(" ");
            char dir = line.charAt(0);
            int rep = Integer.parseInt(split[1]);

            for (int i = 0; i < rep; i++) {
                switch (dir) {
                    case 'R' -> ropes[0][0]++;
                    case 'L' -> ropes[0][0]--;
                    case 'U' -> ropes[0][1]++;
                    case 'D' -> ropes[0][1]--;
                }
                for (int j = 1; j < 10; j++) {
                    int[] tail = ropes[j];
                    int[] head = ropes[j - 1];

                    if (tail[0] == head[0]) {
                        if (tail[1] < head[1] - 1) {
                            tail[1]++;
                        }
                        if (tail[1] > head[1] + 1) {
                            tail[1]--;
                        }
                    } else if (tail[1] == head[1]) {
                        if (tail[0] > head[0] + 1) {
                            tail[0]--;
                        }
                        if (tail[0] < head[0] - 1) {
                            tail[0]++;
                        }
                    }
                    if (tail[1] < head[1] - 1) {
                        if (tail[0] < head[0]) {
                            tail[1]++;
                            tail[0]++;
                        } else {
                            tail[1]++;
                            tail[0]--;
                        }
                    }
                    if (tail[1] > head[1] + 1) {
                        if (tail[0] < head[0]) {
                            tail[1]--;
                            tail[0]++;
                        } else {
                            tail[1]--;
                            tail[0]--;
                        }
                    }
                    if (tail[0] > head[0] + 1) {
                        if (tail[1] < head[1]) {
                            tail[1]++;
                            tail[0]--;
                        } else {
                            tail[1]--;
                            tail[0]--;
                        }
                    }
                    if (tail[0] < head[0] - 1) {
                        if (tail[1] < head[1]) {
                            tail[1]++;
                            tail[0]++;
                        } else {
                            tail[1]--;
                            tail[0]++;
                        }
                    }
                }
                positions.add(ropes[9][0] + " " + ropes[9][1]);
            }
        }
        System.out.println(positions.size());
    }
}