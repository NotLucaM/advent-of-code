import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day10 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day10/input"));

        Queue<Integer> adds = new ArrayDeque<>();
        int x = 1;
        int i = 0;
        int ans = 0;
        while (br.ready()) {
            var line = br.readLine();
            var split = line.split(" ");

            if (split[0].equals("addx")) {
                for (int j = 0; j < 2; j++) {
                    i++;
                    if (Math.abs(x - (i - 1) % 40) <= 1) {
                        System.out.print('#');
                    } else {
                        System.out.print('.');
                    }
                    if (i % 40 == 0) {
                        System.out.println();
                    }
                    if ((i + 20) % 40 == 0) {
                        ans += x * i;
                    }
                }
                x += Integer.parseInt(split[1]);
            } else {
                for (int j = 0; j < 1; j++) {
                    i++;
                    if (Math.abs(x - (i - 1) % 40) <= 1) {
                        System.out.print('#');
                    } else {
                        System.out.print('.');
                    }
                    if (i % 40 == 0) {
                        System.out.println();
                    }
                    if ((i + 20) % 40 == 0) {
                        ans += x * i;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}