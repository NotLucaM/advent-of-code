import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day8 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day8/input"));

        int size = 99;
        int[][] trees = new int[size][size];
        for (int i = 0; i < size; i++) {
            String line = br.readLine();
            trees[i] = new int[size];
            for (int j = 0; j < line.length(); j++) {
                trees[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }

        int ans1 = 0;
        int ans2 = 0;
        int seen = 0;
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                boolean[] visables = new boolean[4];
                for (int k = 0; k < 4; k++) {
                    visables[k] = true;
                }
                int ans = 1;
                seen = 0;
                for (int k = i + 1; k < size; k++) {
                    seen++;
                    if (trees[i][j] <= trees[k][j]) {
                        visables[0] = false;
                        break;
                    }
                }
                ans *= seen;
                seen = 0;
                for (int k = i - 1; k >= 0; k--) {
                    seen++;
                    if (trees[i][j] <= trees[k][j]) {
                        visables[1] = false;
                        break;
                    }
                }
                ans *= seen;
                seen = 0;
                for (int k = j + 1; k < size; k++) {
                    seen++;
                    if (trees[i][j] <= trees[i][k]) {
                        visables[2] = false;
                        break;
                    }
                }
                ans *= seen;
                seen = 0;
                for (int k = j - 1; k >= 0; k--) {
                    seen++;
                    if (trees[i][j] <= trees[i][k]) {
                        visables[3] = false;
                        break;
                    }
                }
                ans *= seen;
                seen = 0;

                ans2 = Integer.max(ans2, ans);
                boolean visable = false;
                for (int k = 0; k < 4; k++) {
                    visable = visable || visables[k];
                }
                if (visable) {
                    ans1++;
                }
            }
        }
        System.out.println(ans1 + size * 4 - 4);
        System.out.println(ans2);
    }
}
